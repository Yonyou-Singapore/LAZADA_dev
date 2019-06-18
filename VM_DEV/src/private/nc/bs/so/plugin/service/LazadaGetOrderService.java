package nc.bs.so.plugin.service;

import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
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
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;

/**
 * 
 * @author ll
 * 
 */
public class LazadaGetOrderService extends AbstractWorkPlugin {

	// /private static final Logger logger =
	// LoggerFactory.getLogger(LazadaSourceOrderMethod.class);
	DownloadMethod downloadmethod = new DownloadMethod();
	LazadaClientService lazadaClientService = new LazadaClientService();
	LazadaJsonUtils lazadaJsonUtil = new LazadaJsonUtils();
	LazadaBillTransform lazadaBillTransform = new LazadaBillTransform();
	
	private List<String> urlList = new ArrayList<String>(){{
		add("https://api.lazada.co.id/rest");
		add("https://api.lazada.sg/rest");
		add("https://api.lazada.com.my/rest");
		add("https://api.lazada.com.ph/rest");
		add("https://api.lazada.co.th/rest");
		add("https://api.lazada.vn/rest");
	}};
	
	
	private ILazadaService lazadaService;
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)throws BusinessException {

		String result = "";
		

		try {
			//当前数据源为VM
			Logger.error("===before init lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
			if(InvocationInfoProxy.getInstance().getUserDataSource() == null) {
				InvocationInfoProxy.getInstance().setUserDataSource("VM");
			}
			Logger.error("===after init lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
			String pk_group = InvocationInfoProxy.getInstance().getGroupId();
			
			SysInitVO[] sysTokenlist = NCLocator.getInstance().lookup(ISysInitQry.class).querySysInit(pk_group, "SO_LAZADA_TOKEN");//开始时间
			
			for(SysInitVO sysVO: sysTokenlist){
				
				String token = sysVO.getValue();
				String orgId = sysVO.getInitcode();
			
				for(String url: urlList){
					result = procOrders(url,token,orgId);
//					System.out.print(result);
				}
			}
//			result = procOrders();
//			System.out.print(result);

		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		} finally {
			// 下载完成之后将线程状态改为0
			// redis.set("sourceDownload" + shopInfo.getShopId(), "0",
			// threadTime);
			// AppContext.cache().set("sourceDownload0",threadTime);
			// return result;
		}

		// new So().getres();

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
	private String procOrders(String url,String token,String orgId) {

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
			 
			 
			req.setStatus("");
			List<Callable<Map<String, Object>>> taskList = new ArrayList<Callable<Map<String, Object>>>();

			do {
				page++;

				String retStr = lazadaClientService.getOrderList(url,token ,iosstartDate, isoenddate,true,null);

				Logger.info("调用数据通获取原单列表接口【getOrders】返回数据" + retStr);
				//保存请求数据
				this.saveResponseStr(url,token ,iosstartDate, isoenddate,true,null);
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
							taskList.add(new InvokeDownload(url,token,orgId,items));
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
	
	/**
	 * 返回数据先存储
	 * @param url
	 * @param token
	 * @param iosstartDate
	 * @param isoenddate
	 * @param b
	 * @param object
	 */
	private void saveResponseStr(String url, String token, String iosstartDate,
			String isoenddate, boolean b, Object object) {
		// TODO Auto-generated method stub
		
	}

	private class InvokeDownload implements Callable<Map<String, Object>> {
		
		String url;
        String token;
        String orgId;
		List<LazadaGetOrderDetailResponse> itemsList;

		public InvokeDownload(String url,String token,String orgId, List<LazadaGetOrderDetailResponse> itemsList)
				throws Exception {
			this.url = url;
			this.token = token;
			this.orgId = orgId;
			this.itemsList = itemsList;
		}

		@Override
		public Map<String, Object> call() throws Exception {

			Map<String, Object> newmap = new HashMap<String, Object>();

			//List<String> list = new ArrayList<String>();
			
			List<LazadaGetOrderDetailResponse> lazadaGetOrderDetaillist = new ArrayList<LazadaGetOrderDetailResponse>();

			for (LazadaGetOrderDetailResponse item : itemsList) {		

				String itemsByOrderId = lazadaClientService.getOrderItems(url,token,String.valueOf(item.getOrder_id())) ;
				List<LazadaProductsInfo> lazadaProductsInfoResponse = null;
				if (StringUtils.isNotEmpty(itemsByOrderId)) {
					if (itemsByOrderId.length() != 0) {
						try {
							LazadaProductsInfoDataResponse lazadaProductsInfoDataResponse = new Gson()
									.fromJson(
											itemsByOrderId,
											LazadaProductsInfoDataResponse.class);
							lazadaProductsInfoResponse = lazadaProductsInfoDataResponse
									.getData();
						} catch (Exception e) {
							Logger.error(
									"获取原单"+item.getOrder_id()+"对应的商品行接口【getOrderItems】返回数据转换json异常",
									e);
						}
					}
				}
				// 每个订单设置商品行
				if (CollectionUtils.isEmpty(lazadaProductsInfoResponse)) {
					Logger.error(
							"获取原单"+item.getOrder_id()+"【getOrderItems】返回数据为空");
					 continue;  
				}
				item.setProducts(lazadaProductsInfoResponse);
				
				
				List<String> orderids = new ArrayList<String>();				
				orderids.add(item.getOrder_id().toString());
//				List<String> existOrder = queryExistLazadaOrder(orderids);
				Logger.error("===query before lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
				if(!RuntimeEnv.getInstance().isDevelopMode()) {
					InvocationInfoProxy.getInstance().setUserDataSource("VM");
				} else if(InvocationInfoProxy.getInstance().getUserDataSource() == null){
					InvocationInfoProxy.getInstance().setUserDataSource("VM");
				}
				Logger.error("===query after lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
				List<String> existOrder = NCLocator.getInstance().lookup(ILazadaService.class).queryExistLazadaOrder(orderids);
				
				if(existOrder.size()<=0){

					NCLocator.getInstance().lookup(ILazadaService.class).insertlazadaresponse(lazadaBillTransform.convertLazadaBill(item,orgId,url,lazadaProductsInfoResponse), 
							lazadaBillTransform.convertLazadaBillItem(lazadaProductsInfoResponse));
					
					lazadaGetOrderDetaillist.add(item);

				}	
				Logger.info("原单下载，调用lazada接口 检索单个订单信息 返回数据" +itemsByOrderId);
				 //list.add(itemsByOrderId);
			}

//			System.out.print(lazadaGetOrderDetaillist);
		
			JSONArray toJSON = lazadaJsonUtil.convertJSON(lazadaGetOrderDetaillist);		
			newmap.put("result", toJSON);
			
			return newmap;
		}
	}
}
