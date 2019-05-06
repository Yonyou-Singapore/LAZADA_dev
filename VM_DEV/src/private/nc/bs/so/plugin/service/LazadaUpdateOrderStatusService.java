package nc.bs.so.plugin.service;

import java.net.URLConnection;
import org.apache.commons.collections.CollectionUtils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.DownloadMethod;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaBillTransform;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaClientService;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaDateUtils;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaJsonUtils;

import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderDetailResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderListDataResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderListResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderListsRequest;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaProductsInfo;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaProductsInfoDataResponse;
import nc.itf.uap.busibean.ISysInitQry;
import nc.pub.so.apiservice.ILazadaService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.para.SysInitVO;


import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;

import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;


public class LazadaUpdateOrderStatusService extends AbstractWorkPlugin {

	DownloadMethod downloadmethod = new DownloadMethod();
	LazadaClientService lazadaClientService = new LazadaClientService();
	LazadaJsonUtils lazadaJsonUtil = new LazadaJsonUtils();
	LazadaBillTransform lazadaBillTransform = new LazadaBillTransform();
	

	private ILazadaService lazadaService;
	
	private List<String> urlList = new ArrayList<String>(){{
		add("https://api.lazada.co.id/rest");
		add("https://api.lazada.sg/rest");
		add("https://api.lazada.com.my/rest");
		add("https://api.lazada.com.ph/rest");
		add("https://api.lazada.co.th/rest");
		add("https://api.lazada.vn/rest");
	}};
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)
			throws BusinessException {

		String result = "";
		
		try {
			
			String pk_group = InvocationInfoProxy.getInstance().getGroupId();
			
			SysInitVO[] sysTokenlist = NCLocator.getInstance().lookup(ISysInitQry.class).querySysInit(pk_group, "SO_LAZADA_TOKEN");//开始时间
		
			for(SysInitVO sysVO: sysTokenlist){
				
				String token = sysVO.getValue();
				String orgId = sysVO.getInitcode();
			
				getUpdatedRange(token,orgId);
			}
		} catch (Exception e) {

			result = "店铺下载原单出错";
		} finally {
		
		}

		return null;
	}

	@Override
	String requestSystem(URLConnection conn, String jsonstr) {

		return "";
	}

	@Override
	String constructRequestJson(List<Map<String, Object>> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	SuperVO[] handleReponse(String response) {
		return null;
	}

	/**
	 * 处理按拍下时间进行同步的订单
	 * 
	 * @param param
	 * @param request
	 * @param shop
	 * @param service
	 * @return
	 */
	private String getUpdatedRange(String token,String orgId) {
		
		StringBuffer resultString = new StringBuffer();
		//需要跟踪的订单状态
		List <String> statusList = new ArrayList<String>();		
		statusList.add("delivered");
		
		
		List<String> orderList = new ArrayList<String>();
		//取库里面上次更新的最晚时间
		orderList = queryLazadaOrderLastUpdateTime(orgId);
		
		if(!CollectionUtils.isEmpty(orderList)){
							
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date updatedDay = new Date();
				
				if(orderList.get(0)!=null && orderList.get(0).length()>0){
					updatedDay = new Date(Long.parseLong(orderList.get(0)));
				}
				
				for(String url : urlList){
					String result = procOrders(url,token,orgId,updatedDay);	
				}	
		}
		return null;
	}
	
	
	/**
	 * 处理按拍下时间进行同步的订单
	 * 
	 * @param param
	 * @param request
	 * @param shop
	 * @param service
	 * @return
	 */
	private String procOrders(String url,String token,String orgId,Date updateDay) {

		LazadaGetOrderListsRequest req = new LazadaGetOrderListsRequest();
		// long page = 0L;
		// long pageSize = 100L;
		int page = 0;
		int pageSize = 50;

		String result = "";

		int totalNum = 0;
		int pageMax = 1;
		try {
		
			//获取前台选择的时间区间
			
			String pk_group = InvocationInfoProxy.getInstance().getGroupId();
			
			SysInitVO sysvostart = NCLocator.getInstance().lookup(ISysInitQry.class).queryByParaCode(pk_group, "SOLAZADA01");//开始时间
			SysInitVO sysvoend = NCLocator.getInstance().lookup(ISysInitQry.class).queryByParaCode(pk_group, "SOLAZADA02");//结束时间
			
			
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");   
			
			Date startdate = format1.parse(sysvostart.getValue());
			Date enddate = format1.parse(sysvoend.getValue());
			
			
			String isoenddate = LazadaDateUtils.normalToIso8601((Long.toString(enddate.getTime())));
			String iosstartDate = LazadaDateUtils.normalToIso8601((Long.toString(startdate.getTime())));
			String iosupdateDate = LazadaDateUtils.normalToIso8601((Long.toString(updateDay.getTime()))); 
			 
			
			List<Callable<Map<String, Object>>> taskList = new ArrayList<Callable<Map<String, Object>>>();

			do {
				page++;

				Date updateTimestamp = new Date();
				
				String retStr = lazadaClientService.getOrderList(url,token ,iosstartDate, isoenddate,false,iosupdateDate);

				Logger.info("调用数据通获取原单列表接口【getOrders】返回数据" + retStr);
				// OrderListResult orderList = processResStr(retStr);
				LazadaGetOrderListDataResponse lazadaGetOrderListDataResponse = new Gson()
						.fromJson(retStr, LazadaGetOrderListDataResponse.class);
				LazadaGetOrderListResponse lazadaGetOrderListResponse = null;

				if (StringUtils.isNotEmpty(retStr)) {
					if (retStr.length() != 0) {
						try {
							lazadaGetOrderListResponse = lazadaGetOrderListDataResponse
									.getData();
						} catch (Exception e) {
							Logger.error("调用数据通获取原单列表接口【getOrders】返回数据转换json异常",
							e);
						}
						if (lazadaGetOrderListResponse != null) {
							// 订单总数
							totalNum = lazadaGetOrderListResponse.getCount();
							// 总页数
							pageMax = (totalNum % pageSize == 0 ? totalNum
									/ pageSize : totalNum / pageSize + 1);
							// 订单列表
							List<LazadaGetOrderDetailResponse> items = lazadaGetOrderListResponse
									.getOrders();

							if (items == null || items.size() == 0)
								break;
							taskList.add(new InvokeDownload(items,updateTimestamp));
						}
					} else {
						return "取到的数据为空!";
					}
				}

			} while (page < pageMax);

			result = downloadmethod.executeDownloadTask(taskList);

		} catch (Exception e) {

			Logger.error(e);
		}
		return result;
	}

	private class InvokeDownload implements Callable<Map<String, Object>> {
		
		List<LazadaGetOrderDetailResponse> itemsList;
		Date updateTimestamp;

		public InvokeDownload(List<LazadaGetOrderDetailResponse> itemsList,Date updateTimestamp)
				throws Exception {
		
			this.itemsList = itemsList;
			this.updateTimestamp = updateTimestamp;
		}

		@Override
		public Map<String, Object> call() throws Exception {

			Map<String, Object> newmap = new HashMap<String, Object>();
			
			dbProcessForLazadaStatusUpdate(itemsList,Long.toString(updateTimestamp.getTime()));
	
			return newmap;
		}
	}
	
	

}
