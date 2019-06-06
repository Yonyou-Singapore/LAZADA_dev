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
			
			SysInitVO[] sysTokenlist = NCLocator.getInstance().lookup(ISysInitQry.class).querySysInit(pk_group, "SO_LAZADA_TOKEN");//寮�鏃堕棿
		
			for(SysInitVO sysVO: sysTokenlist){
				
				String token = sysVO.getValue();
				String orgId = sysVO.getInitcode();
			
				getUpdatedRange(token,orgId);
			}
		} catch (Exception e) {

			result = "搴楅摵涓嬭浇鍘熷崟鍑洪敊";
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
	 * 澶勭悊鎸夋媿涓嬫椂闂磋繘琛屽悓姝ョ殑璁㈠崟
	 * 
	 * @param param
	 * @param request
	 * @param shop
	 * @param service
	 * @return
	 */
	private String getUpdatedRange(String token,String orgId) {
		
		StringBuffer resultString = new StringBuffer();
		//闇�璺熻釜鐨勮鍗曠姸鎬�
		List <String> statusList = new ArrayList<String>();		
		statusList.add("delivered");
		
		
		List<String> timeList = new ArrayList<String>();
		//鍙栧簱閲岄潰涓婃鏇存柊鐨勬渶鏅氭椂闂�
		timeList = queryLazadaOrderLastUpdateTime(orgId);
		
		if(!CollectionUtils.isEmpty(timeList)){
							
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date updatedDay = new Date();
				
				if(timeList.get(0)!=null && timeList.get(0).length()>0){
					try {
						//updatedDay = format.parse("2019-04-01 00:00:00");
						updatedDay = format.parse(timeList.get(0));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				for(String url : urlList){
					String result = procOrders(url,token,orgId,updatedDay);	
				}	
		}
		return null;
	}
	
	
	/**
	 * 澶勭悊鎸夋媿涓嬫椂闂磋繘琛屽悓姝ョ殑璁㈠崟
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
		
			//鑾峰彇鍓嶅彴閫夋嫨鐨勬椂闂村尯闂�
			
			String pk_group = InvocationInfoProxy.getInstance().getGroupId();
			
			SysInitVO sysvostart = NCLocator.getInstance().lookup(ISysInitQry.class).queryByParaCode(pk_group, "SOLAZADA01");//寮�鏃堕棿
			SysInitVO sysvoend = NCLocator.getInstance().lookup(ISysInitQry.class).queryByParaCode(pk_group, "SOLAZADA02");//缁撴潫鏃堕棿
			
			
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");   
			
			Date startdate = format1.parse(sysvostart.getValue());
			Date enddate = format1.parse(sysvoend.getValue());
			
			
			String isoenddate = LazadaDateUtils.normalToIso8601((Long.toString(enddate.getTime())));
			String iosstartDate = LazadaDateUtils.normalToIso8601((Long.toString(startdate.getTime())));
			String iosupdateDate = LazadaDateUtils.normalToIso8601((Long.toString(updateDay.getTime()))); 
			 
			
			List<Callable<Map<String, Object>>> taskList = new ArrayList<Callable<Map<String, Object>>>();

			do {
				page++;
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String updateTimestamp = format.format(new Date());
				
				String retStr = lazadaClientService.getOrderList(url,token ,iosstartDate, isoenddate,false,iosupdateDate);

				Logger.info("璋冪敤鏁版嵁閫氳幏鍙栧師鍗曞垪琛ㄦ帴鍙ｃ�getOrders銆戣繑鍥炴暟鎹" + retStr);
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
							Logger.error("璋冪敤鏁版嵁閫氳幏鍙栧師鍗曞垪琛ㄦ帴鍙ｃ�getOrders銆戣繑鍥炴暟鎹浆鎹son寮傚父",
							e);
						}
						if (lazadaGetOrderListResponse != null) {
							// 璁㈠崟鎬绘暟
							totalNum = lazadaGetOrderListResponse.getCount();
							// 鎬婚〉鏁�
							pageMax = (totalNum % pageSize == 0 ? totalNum
									/ pageSize : totalNum / pageSize + 1);
							// 璁㈠崟鍒楄〃
							List<LazadaGetOrderDetailResponse> items = lazadaGetOrderListResponse
									.getOrders();

							if (items == null || items.size() == 0)
								break;
							taskList.add(new InvokeDownload(items,updateTimestamp));
						}
					} else {
						return "鍙栧埌鐨勬暟鎹负绌�";
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
		String updateTimestamp;

		public InvokeDownload(List<LazadaGetOrderDetailResponse> itemsList,String updateTimestamp)
				throws Exception {
		
			this.itemsList = itemsList;
			this.updateTimestamp = updateTimestamp;
		}

		@Override
		public Map<String, Object> call() throws Exception {

			Map<String, Object> newmap = new HashMap<String, Object>();
			
			dbProcessForLazadaStatusUpdate(itemsList,updateTimestamp);
	
			return newmap;
		}
	}
	
	

}
