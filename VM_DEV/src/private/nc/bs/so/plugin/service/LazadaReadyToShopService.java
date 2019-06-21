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
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBatchDeliverDataResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBatchDeliverResponse;

import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaExpressCompanyDataResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaExpressCompanyResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderDetailResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderListDataResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderListResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderListsRequest;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaPackedByMarketDataResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaPackedByMarketResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaProductsInfo;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaProductsInfoDataResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaShipProviders;
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


public class LazadaReadyToShopService extends AbstractWorkPlugin {

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
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public String execute(String order_item_ids,String org, String shipProvider,String trackingNo,String country)
			throws BusinessException {

		String result = "";
		

		try {
			//当前数据源为VM
			Logger.error("===before init lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
			if(InvocationInfoProxy.getInstance().getUserDataSource() == null) {
				InvocationInfoProxy.getInstance().setUserDataSource("VM");
			}
			Logger.error("===after init lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
			String pk_group = InvocationInfoProxy.getInstance().getGroupId();
			
			
			StringBuffer url = new StringBuffer();
			
			switch (country){
			
				case "Singapore":
					url.append("https://api.lazada.sg/rest");break;
				case "Malaysia":
					url.append("https://api.lazada.com.my/rest");break;
				case "Philippines":
					url.append("https://api.lazada.com.ph/rest");break;
				case "Thailand":
					url.append("https://api.lazada.co.th/rest");break;
				case "Vietnam":
					url.append("https://api.lazada.vn/rest");break;
				case "Indonesia":
					url.append("https://api.lazada.co.id/rest");break;
				default:
					break;
				
			}
			
			
			SysInitVO[] sysTokenlist = NCLocator.getInstance().lookup(ISysInitQry.class).querySysInit(pk_group, "SO_LAZADA_TOKEN");//开始时间
			
			for(SysInitVO sysVO: sysTokenlist){
				
				String token = sysVO.getValue();
				String orgId = sysVO.getInitcode();
			
				if(orgId == org){
					result = setOrderStatus(url.toString(),token,orgId,order_item_ids,shipProvider,trackingNo, country);
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
	 * 修改订单状态至ready to ship
	 * 
	 * @param param
	 * @param request
	 * @param shop
	 * @param service
	 * @return
	 */
	private String setOrderStatus(String url,String token,String orgId,String order_item_ids, String shipProvider,String trackingNo,String country) {

		LazadaGetOrderListsRequest req = new LazadaGetOrderListsRequest();
		// long page = 0L;
		// long pageSize = 100L;
	
		try {
		
//			StringBuffer shipment_provider = new StringBuffer();
//			
//			if(shipProvider!=null && shipProvider.length()>0){
//				shipment_provider.append(shipProvider);
//			}else{
//				
//				//调用lazada接口请求供应商
//				
//				LazadaExpressCompanyResponse lazadaExpressCompanyResponse = null;
//		        LazadaShipProviders[] shipment_providers = null;
//		        try {
//		        	String retShipmentProvider = lazadaClientService.GetShipmentProviders(url, token);
//		            Logger.info("调用lazada接口请求发货商" + retShipmentProvider);
//					
//		            LazadaExpressCompanyDataResponse lazadaExpressCompanyDataResponse = new Gson().fromJson(retShipmentProvider, LazadaExpressCompanyDataResponse.class);
//		            lazadaExpressCompanyResponse = lazadaExpressCompanyDataResponse.getData();
//		            shipment_providers = lazadaExpressCompanyResponse.getShipment_providers();
//		        } catch (Exception e) {
//		        	Logger.error("调用来赞达接口【getShipmentProviders】返回数据转换json异常", e);
//		        	throw new BusinessException("调用来赞达接口【getShipmentProviders】返回数据转换json异常" + e);
//		        }
//				
//		        
//		        if(shipment_providers.length<1){
//		        	Logger.info("调用来赞达接口【getShipmentProviders】没有找到默认发货供应商");
//		        	throw new BusinessException("没有找到默认运输供应商");	
//		        }
//		        
//		        shipment_provider.append(shipment_providers[0].getName());
//			
//			}
//	        
//	        
//	        //SetStatusToPackedByMarketplace
//			//调用lazada接口请求运单号
//	        
//	        StringBuffer trackingInfo = new StringBuffer();
//	        LazadaPackedByMarketResponse lazadaPackedByMarketResponse = new LazadaPackedByMarketResponse();
//			
//	        
//	        try {
//	        	
//	        	String retPackMarket = lazadaClientService.SetStatusToPackedByMarketplace(url, token, order_item_ids,shipment_provider.toString());
//	            Logger.info("调用lazada接口SetStatusToPackedByMarketplace" + retPackMarket);
//	            if (org.apache.commons.lang.StringUtils.isNotEmpty(retPackMarket)) {
//		            LazadaPackedByMarketDataResponse lazadaPackedByMarketDataResponse = new Gson().fromJson(retPackMarket, LazadaPackedByMarketDataResponse.class);
//		            lazadaPackedByMarketResponse = lazadaPackedByMarketDataResponse.getData();
//	        	 }
//	            
//	           
//				
//				
//				if (lazadaPackedByMarketResponse != null) {
//	                if (lazadaPackedByMarketResponse.getPacked_Market_infos().length > 0) {
//	                   trackingInfo.append(lazadaPackedByMarketResponse.getPacked_Market_infos()[0].getTracking_number());
//	                    
//	                } else {
//	                	throw new BusinessException("调用来赞达接口【SetStatusToPackedByMarketplace】返回数据转换json异常");  
//	                }
//	            }
//	            
//	        } catch (Exception e) {
//	        	Logger.error("调用来赞达接口【getShipmentProviders】返回数据转换json异常", e);
//	        	throw new BusinessException("调用来赞达接口【getShipmentProviders】返回数据转换json异常" + e);
//	        }
//			
//	   
	        
	        
	        //SetStatusToReadyToShip
	        
	        //调用lazada接口发货
	        
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        LazadaBatchDeliverResponse lazadaBatchDeliverResponse = new LazadaBatchDeliverResponse();
			
	        try{
	        	
				String retStr = lazadaClientService.SetStatusToReadyToShip(url,token,order_item_ids,null, null);

				Logger.info("调用lazada接口将订单状态设置为发货" + retStr);
				
				
				if (org.apache.commons.lang.StringUtils.isNotEmpty(retStr)) {
	                LazadaBatchDeliverDataResponse lazadaBatchDeliverDataResponse = new Gson().fromJson(retStr, LazadaBatchDeliverDataResponse.class);
	                lazadaBatchDeliverResponse = lazadaBatchDeliverDataResponse.getData();
	            }
				
				
				if (lazadaBatchDeliverResponse != null) {
	                if (lazadaBatchDeliverResponse.getOrder_items().length > 0) {
	                    resultMap.put("success", true);
	                } else {
	                    resultMap.put("success", false);
	                    resultMap.put("msg","lazada平台返回发货信息错误");
	                }
	            }
				//如果报错,直接抛到前台
				if(lazadaBatchDeliverResponse == null) {
					throw new BusinessException(retStr);
				}
				//生成对应的销售订单
				//更新原单状态
				return lazadaBatchDeliverResponse.getOrder_items().toString()+"发货成功" ;
				
	        } catch (Exception e){
	        	Logger.error("调用来赞达接口【SetStatusToReadyToShip】返回数据转换json异常", e);
	        	throw new BusinessException("Lazada返回异常:" + e);
	        }
	        
		} catch (Exception e) {

			Logger.error(e);
			ExceptionUtils.wrappException(e);
		}
		
		return "";
		
	}
	
}
