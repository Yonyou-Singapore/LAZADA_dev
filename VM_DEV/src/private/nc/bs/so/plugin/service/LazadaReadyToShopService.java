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
	
	
	public String execute(String order_item_ids,String platform, String shipProvider,String trackingNo,String country)
			throws BusinessException {

		String result = "";
		

		try {
			//��ǰ����ԴΪVM
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
			
			
			SysInitVO[] sysTokenlist = NCLocator.getInstance().lookup(ISysInitQry.class).querySysInit(pk_group, "SO_LAZADA_TOKEN");//��ʼʱ��
			
			for(SysInitVO sysVO: sysTokenlist){
				
				String token = sysVO.getValue();
				String orgId = sysVO.getInitcode();
			
//				if(orgId == platform){
					
					
					result = setOrderStatus(url.toString(),token,orgId,order_item_ids,shipProvider,trackingNo, country);
//					
//				}
			}
//			result = procOrders();
//			System.out.print(result);

		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		} finally {
			// �������֮���߳�״̬��Ϊ0
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
	 * �޸Ķ���״̬��ready to ship
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
		
		
			//����lazada�ӿ�����Ӧ��
			
			LazadaExpressCompanyResponse lazadaExpressCompanyResponse = null;
	        LazadaShipProviders[] shipment_providers = null;
	        try {
	        	String retShipmentProvider = lazadaClientService.GetShipmentProviders(url, token);
	            Logger.info("����lazada�ӿ����󷢻���" + retShipmentProvider);
				
	            LazadaExpressCompanyDataResponse lazadaExpressCompanyDataResponse = new Gson().fromJson(retShipmentProvider, LazadaExpressCompanyDataResponse.class);
	            lazadaExpressCompanyResponse = lazadaExpressCompanyDataResponse.getData();
	            shipment_providers = lazadaExpressCompanyResponse.getShipment_providers();
	        } catch (Exception e) {
	        	Logger.error("�������޴�ӿڡ�getShipmentProviders����������ת��json�쳣", e);
	        	throw new BusinessException("�������޴�ӿڡ�getShipmentProviders����������ת��json�쳣" + e);
	        }
			
	        
	        if(shipment_providers.length<1){
	        	Logger.info("�������޴�ӿڡ�getShipmentProviders��û���ҵ�Ĭ�Ϸ�����Ӧ��");
	        	throw new BusinessException("û���ҵ�Ĭ�����乩Ӧ��");	
	        }
	        
	        
	        
	        
	        
	        
	        //SetStatusToPackedByMarketplace
			//����lazada�ӿ������˵���
	        String shipment_provider = shipment_providers[0].getName();
	        StringBuffer trackingInfo = new StringBuffer();
	        LazadaPackedByMarketResponse lazadaPackedByMarketResponse = new LazadaPackedByMarketResponse();
			
	        
	        try {
	        	
	        	
	        	String retPackMarket = lazadaClientService.SetStatusToPackedByMarketplace(url, token, order_item_ids,shipment_provider);
	            Logger.info("����lazada�ӿ�SetStatusToPackedByMarketplace" + retPackMarket);
	            if (org.apache.commons.lang.StringUtils.isNotEmpty(retPackMarket)) {
		            LazadaPackedByMarketDataResponse lazadaPackedByMarketDataResponse = new Gson().fromJson(retPackMarket, LazadaPackedByMarketDataResponse.class);
		            lazadaPackedByMarketResponse = lazadaPackedByMarketDataResponse.getData();
	        	 }
	            
	           
				
				
				if (lazadaPackedByMarketResponse != null) {
	                if (lazadaPackedByMarketResponse.getPacked_Market_infos().length > 0) {
	                   trackingInfo.append(lazadaPackedByMarketResponse.getPacked_Market_infos()[0].getTracking_number());
	                    
	                } else {
	                	throw new BusinessException("�������޴�ӿڡ�SetStatusToPackedByMarketplace����������ת��json�쳣");  
	                }
	            }
	            
	        } catch (Exception e) {
	        	Logger.error("�������޴�ӿڡ�getShipmentProviders����������ת��json�쳣", e);
	        	throw new BusinessException("�������޴�ӿڡ�getShipmentProviders����������ת��json�쳣" + e);
	        }
			
	   
	        
	        
	        //SetStatusToPackedByMarketplace
	        
	        //����lazada�ӿڷ���
	        
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        LazadaBatchDeliverResponse lazadaBatchDeliverResponse = new LazadaBatchDeliverResponse();
			
	        try{
	        	
				String retStr = lazadaClientService.SetStatusToReadyToShip(url,token,order_item_ids,shipment_provider, trackingInfo.toString());

				Logger.info("����lazada�ӿڽ�����״̬����Ϊ����" + retStr);
				
				
				if (org.apache.commons.lang.StringUtils.isNotEmpty(retStr)) {
	                LazadaBatchDeliverDataResponse lazadaBatchDeliverDataResponse = new Gson().fromJson(retStr, LazadaBatchDeliverDataResponse.class);
	                lazadaBatchDeliverResponse = lazadaBatchDeliverDataResponse.getData();
	            }
				
				
				if (lazadaBatchDeliverResponse != null) {
	                if (lazadaBatchDeliverResponse.getOrder_items().length > 0) {
	                    resultMap.put("success", true);
	                } else {
	                    resultMap.put("success", false);
	                    resultMap.put("msg","lazadaƽ̨���ط�����Ϣ����");
	                }
	            }
				//�������,ֱ���׵�ǰ̨
				if(lazadaBatchDeliverResponse == null) {
					throw new BusinessException(retStr);
				}
				//���ɶ�Ӧ�����۶���
				//����ԭ��״̬
				return lazadaBatchDeliverResponse.getOrder_items().toString()+"�����ɹ�" ;
				
	        } catch (Exception e){
	        	Logger.error("�������޴�ӿڡ�SetStatusToReadyToShip����������ת��json�쳣", e);
	        	throw new BusinessException("Lazada�����쳣:" + e);
	        }
	        
		} catch (Exception e) {

			Logger.error(e);
			ExceptionUtils.wrappException(e);
		}
		
		return "";
		
	}
	
}
