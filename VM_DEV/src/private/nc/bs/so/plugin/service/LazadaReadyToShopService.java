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
import nc.bs.so.component.ace.bp.AceComponentUpdateBP;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
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
import nc.vo.org.util.CloneUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.component.AggSo_ordercenter;
import nc.vo.so.component.PlatformStatusAndPara;
import nc.vo.so.component.So_ordercenter;
import nc.vo.so.component.So_ordercenter_b;

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
			
				if(orgId == org){
					result = setOrderStatus(url.toString(),token,null,order_item_ids,shipProvider,trackingNo, country);
				}
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
	 * @throws BusinessException 
	 */
	private String setOrderStatus(String url,String token,String platform,String order_item_ids, String shipProvider,String trackingNo,String country) throws BusinessException {

		LazadaGetOrderListsRequest req = new LazadaGetOrderListsRequest();
		// long page = 0L;
		// long pageSize = 100L;
	
//		try {
		
//			StringBuffer shipment_provider = new StringBuffer();
//			
//			if(shipProvider!=null && shipProvider.length()>0){
//				shipment_provider.append(shipProvider);
//			}else{
//				
//				//����lazada�ӿ�����Ӧ��
//				
//				LazadaExpressCompanyResponse lazadaExpressCompanyResponse = null;
//		        LazadaShipProviders[] shipment_providers = null;
//		        try {
//		        	String retShipmentProvider = lazadaClientService.GetShipmentProviders(url, token);
//		            Logger.info("����lazada�ӿ����󷢻���" + retShipmentProvider);
//					
//		            LazadaExpressCompanyDataResponse lazadaExpressCompanyDataResponse = new Gson().fromJson(retShipmentProvider, LazadaExpressCompanyDataResponse.class);
//		            lazadaExpressCompanyResponse = lazadaExpressCompanyDataResponse.getData();
//		            shipment_providers = lazadaExpressCompanyResponse.getShipment_providers();
//		        } catch (Exception e) {
//		        	Logger.error("�������޴�ӿڡ�getShipmentProviders����������ת��json�쳣", e);
//		        	throw new BusinessException("�������޴�ӿڡ�getShipmentProviders����������ת��json�쳣" + e);
//		        }
//				
//		        
//		        if(shipment_providers.length<1){
//		        	Logger.info("�������޴�ӿڡ�getShipmentProviders��û���ҵ�Ĭ�Ϸ�����Ӧ��");
//		        	throw new BusinessException("û���ҵ�Ĭ�����乩Ӧ��");	
//		        }
//		        
//		        shipment_provider.append(shipment_providers[0].getName());
//			
//			}
//	        
//	        
//	        //SetStatusToPackedByMarketplace
//			//����lazada�ӿ������˵���
//	        
//	        StringBuffer trackingInfo = new StringBuffer();
//	        LazadaPackedByMarketResponse lazadaPackedByMarketResponse = new LazadaPackedByMarketResponse();
//			
//	        
//	        try {
//	        	
//	        	String retPackMarket = lazadaClientService.SetStatusToPackedByMarketplace(url, token, order_item_ids,shipment_provider.toString());
//	            Logger.info("����lazada�ӿ�SetStatusToPackedByMarketplace" + retPackMarket);
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
//	                	throw new BusinessException("�������޴�ӿڡ�SetStatusToPackedByMarketplace����������ת��json�쳣");  
//	                }
//	            }
//	            
//	        } catch (Exception e) {
//	        	Logger.error("�������޴�ӿڡ�getShipmentProviders����������ת��json�쳣", e);
//	        	throw new BusinessException("�������޴�ӿڡ�getShipmentProviders����������ת��json�쳣" + e);
//	        }
//			
//	   
	        
	        
	        //SetStatusToReadyToShip
	        
	        //����lazada�ӿڷ���
	        
//	        Map<String, Object> resultMap = new HashMap<String, Object>();
//	        LazadaBatchDeliverResponse lazadaBatchDeliverResponse = new LazadaBatchDeliverResponse();
			
//	        try{
	        	
				String retStr = lazadaClientService.SetStatusToReadyToShip(url,token,order_item_ids,null, null);

				Logger.info("����lazada�ӿڽ�����״̬����Ϊ����" + retStr);
				
				
				if (org.apache.commons.lang.StringUtils.isNotEmpty(retStr)) {
					//���������쳣��Ϣ, ֱ���״�
					if(StringUtils.indexOf(retStr, "message") > 0) {
						throw new BusinessException(retStr);
					} else if(StringUtils.indexOf(retStr, "message") < 0 && StringUtils.indexOf(retStr, "order_item_id") < 0) {
						//�����ɹ� 
						return retStr;
					}
	                LazadaBatchDeliverDataResponse lazadaBatchDeliverDataResponse = new Gson().fromJson(retStr, LazadaBatchDeliverDataResponse.class);
//	                lazadaBatchDeliverResponse = lazadaBatchDeliverDataResponse.getData();
	            }
				
				
//				if (lazadaBatchDeliverResponse != null) {
//	                if (lazadaBatchDeliverResponse.getOrder_items().length > 0) {
//	                    resultMap.put("success", true);
//	                } else {
//	                    resultMap.put("success", false);
//	                    resultMap.put("msg","lazadaƽ̨���ط�����Ϣ����");
//	                }
//	            }
				//�������,ֱ���׵�ǰ̨
//				if(lazadaBatchDeliverResponse == null) {
//					throw new BusinessException(retStr);
//				}
				//���ɶ�Ӧ�����۶���
				//����ԭ��״̬
//				return lazadaBatchDeliverResponse.getOrder_items().toString()+"�����ɹ�" ;
				
//	        } catch (Exception e){
//	        	Logger.error("�������޴�ӿڡ�SetStatusToReadyToShip����������ת��json�쳣", e);
//	        	ExceptionUtils.wrappException(e);
//	        }
	        
//		} catch (Exception e) {
//
//			Logger.error(e);
//			ExceptionUtils.wrappException(e);
//		}
		
		return "";
		
	}


	public AggSo_ordercenter execute(AggSo_ordercenter agg) {

		String result = "";
		

		try {
			List<String> order_item_ids = new ArrayList<String>();;
			String shipProvider = "";
			String trackingNo = "";
			So_ordercenter_b [] bvos = agg.getChildrenVO();
			for(So_ordercenter_b vo : bvos) {
				order_item_ids.add(vo.getOrder_item_id());
				shipProvider = vo.getShipment_provider();
				trackingNo = vo.getTracking_code();
			}
			String platform = agg.getParentVO().getPlatform();
			String country = agg.getParentVO().getBilling_country();
			String pk_org = agg.getParentVO().getPk_org();
			Map<String, String> orgmap = this.getPkorgMap();
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
				
				if(orgId.equals(orgmap.get(pk_org))) {
					result = setOrderStatus(url.toString(),token,null,order_item_ids.toString(),shipProvider,trackingNo, country);
					//�����سɹ�,����
					if(StringUtils.indexOf(result, "message") < 0 && StringUtils.indexOf(result, "order_item_id") > 0) {
						return this.updateCenterOrderStatus(agg);
					}
				}
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


	private Map<String, String> getPkorgMap() {
		Map<String, String> orgmap = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		sb.append("select pk_salesorg, code from org_salesorg");
		DataAccessUtils util = new DataAccessUtils();
        IRowSet rowset = util.query(sb.toString());
        while(rowset.next()) {
        	orgmap.put(rowset.getString(0), rowset.getString(1));
        }
		return orgmap;
	}
	
	/**
	 * ����״̬��������
	 * @param agg
	 * @return 
	 */
	private AggSo_ordercenter updateCenterOrderStatus(AggSo_ordercenter agg) {
		AggSo_ordercenter srcagg = (AggSo_ordercenter) CloneUtil.deepClone(agg);
		So_ordercenter parentVO = agg.getParentVO();
		parentVO.setOrder_status(PlatformStatusAndPara.LAZADA_READYTOSHIP);
		parentVO.setStatus(VOStatus.UPDATED);
		AceComponentUpdateBP updatebp = new AceComponentUpdateBP();
		AggSo_ordercenter[] updtedagg = updatebp.update(new AggSo_ordercenter[] {agg}, new AggSo_ordercenter[]{srcagg});
		return updtedagg[0];
	}
}
