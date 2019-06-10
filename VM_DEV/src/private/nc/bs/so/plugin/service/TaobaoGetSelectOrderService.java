package nc.bs.so.plugin.service;

import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import nc.impl.so.restapi.jsonservice.vo.lazada.util.EnumPlatType;

import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaDateUtils;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaJsonUtils;

import nc.impl.so.restapi.jsonservice.vo.taobao.util.MCloudRequest;
import nc.impl.so.restapi.jsonservice.vo.taobao.util.OrderSourceRequest;
import nc.impl.so.restapi.jsonservice.vo.taobao.util.ServiceUtil;
import nc.impl.so.restapi.jsonservice.vo.taobao.util.TaobaoBillTransform;
import nc.impl.so.restapi.jsonservice.vo.taobao.util.TaobaoClientService;

import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;
import com.taobao.api.internal.parser.json.JsonConverter;
import net.sf.json.JSONObject;
import nc.itf.uap.busibean.ISysInitQry;
import nc.pub.so.apiservice.ILazadaService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import nc.impl.so.restapi.jsonservice.vo.taobao.util.TradeFullinfoGetRequest;
import com.taobao.api.response.TradeFullinfoGetResponse;
import com.taobao.api.response.TradesSoldGetResponse;

import edu.emory.mathcs.backport.java.util.Arrays;


public class TaobaoGetSelectOrderService extends AbstractWorkPlugin {

	DownloadMethod downloadmethod = new DownloadMethod();
	TaobaoClientService taobaoClientService = new TaobaoClientService();
	LazadaJsonUtils lazadaJsonUtil = new LazadaJsonUtils();
	TaobaoBillTransform taobaoBillTransform = new TaobaoBillTransform();
    private ServiceUtil serviceUtil = new ServiceUtil();
	
    
    
    
    
    public void execute(String[] platform,String[] orgs, UFDate startdate, UFDate enddate)
			throws BusinessException{

		String result = "";
		

		try {
			//��ǰ����ԴΪVM
			Logger.error("===before init lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
			if(InvocationInfoProxy.getInstance().getUserDataSource() == null) {
				InvocationInfoProxy.getInstance().setUserDataSource("VM");
			}
			Logger.error("===after init lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
			String pk_group = InvocationInfoProxy.getInstance().getGroupId();
			
			SysInitVO[] sysTokenlist = NCLocator.getInstance().lookup(ISysInitQry.class).querySysInit(pk_group, "SO_TAOBAO_TOKEN");//��ʼʱ��
			
			for(SysInitVO sysVO: sysTokenlist){
				
				String token = sysVO.getValue();
				String orgId = sysVO.getInitcode();
			
				List<String> orgList = Arrays.asList(orgs);
				
				//�ж���֯
			    if(!orgList.contains(orgId)){
					result = procOrders(token,orgId);	
	            }
			}

		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		} finally {
			// �������֮���߳�״̬��Ϊ0
			// redis.set("sourceDownload" + shopInfo.getShopId(), "0",
			// threadTime);
			// AppContext.cache().set("sourceDownload0",threadTime);
			// return result;
		}
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
	 * ��������ʱ�����ͬ���Ķ���
	 * 
	 * @param param
	 * @param request
	 * @param shop
	 * @param service
	 * @return
	 */
	private String procOrders(String token,String orgId) {
		
		
		MCloudRequest mcloudRequest = new MCloudRequest(String.valueOf(EnumPlatType.top.toString()));
		String retStr = "";
		
        try {        
           
              // ������ʱ������
                retStr = procOrdersCreated(token,mcloudRequest,orgId);
            
        } finally {
        }
        return retStr;
	}
	
	
	
	
	

    /**
     * ��������ʱ�����ͬ���Ķ���
     *
     * @param param
     * @param request
     * @param shop
     * @param service
     * @return
     */
    private String procOrdersCreated(String token,MCloudRequest request,String orgId) {

        //TradeVouch info = (TradeVouch) param.get("orderSource");
    	String pk_group = InvocationInfoProxy.getInstance().getGroupId();
		
		SysInitVO sysvostart;
		SysInitVO sysvoend;
		String isoenddate="" ;
		String iosstartDate="" ;
		try {
			sysvostart = NCLocator.getInstance().lookup(ISysInitQry.class).queryByParaCode(pk_group, "SOLAZADA01");
		
			sysvoend = NCLocator.getInstance().lookup(ISysInitQry.class).queryByParaCode(pk_group, "SOLAZADA02");//����ʱ��
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");   
			
			Date startdate = format1.parse(sysvostart.getValue());
			Date enddate = format1.parse(sysvoend.getValue());
			
			String format_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat normalFormat = new SimpleDateFormat(format_yyyyMMddHHmmss);
			
			isoenddate = normalFormat.format(enddate);
			iosstartDate = normalFormat.format(startdate);
		
		} catch (Exception e1) {
			ExceptionUtils.wrappBusinessException(e1.getMessage());
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//��ʼʱ��
		
		
			 

        //��ȡ������Ϣ
        OrderSourceRequest req = new OrderSourceRequest();
        String result = "";
        long page = 0L;
        long pageSize = 80L;
        
        

        boolean hasNext = false;
        req.setUseHasNext(true);

        String startTime = iosstartDate;
        String endTime = isoenddate;
       

        if (startTime != null) {
            req.setStartCreated(startTime);
        }
        if (endTime != null) {
            req.setEndCreated(endTime);
        }
        
        //Ĭ�����صȴ��ջ�״̬
        
        
       // req.setStatus("WAIT_SELLER_SEND_GOODS");//WAIT_SELLER_SEND_GOODS(�ȴ����ҷ���,��:����Ѹ���)
        req.setType("guarantee_trade,auto_delivery,ec,step,nopaid,tmall_i18n");

     
        List<Callable<Map<String, Object>>> taskList = new ArrayList<Callable<Map<String, Object>>>();
        
        String resultString = "";


        do {
            page++;
            req.setPageNo(page);
            req.setPageSize(pageSize);
            request.setRequest(req);
            Map<String, Object> map = new HashMap<String, Object>();
           
            String retStr = "";
            try {
            	
            	retStr = getFullinfoDetail(token,request, map);
                Logger.info("�����Ա��ӿ� taobao.trades.sold.get ��������" + retStr);
            	TradesSoldGetResponse response = null;
            	
            	
            	if (StringUtils.isNotEmpty(retStr)) {
                    if (retStr.length() != 0) {
                        try {
                            response = (TradesSoldGetResponse) trans2Obj(retStr, TradesSoldGetResponse.class);
                        } catch (Exception e) {
                            Logger.error("�����Ա��ӿ� taobao.trades.sold.get ��������ת��json�쳣", e);
                        }
                        if (response != null && null != response.getHasNext()) {
                            hasNext = response.getHasNext();
                            List<Trade> list = response.getTrades();

                            if (null != list) {
                                MCloudRequest inRequest = new MCloudRequest(String.valueOf(EnumPlatType.top.toString()));
                                taskList.add(new InvokeDownload(token,inRequest, list,orgId));
                            }
                            if (StringUtils.isNotBlank(response.getErrorCode())) {
//    							//ˢ��token
//    							getRefreshToken.getRefreshToken(request,shop,retStr,info);
//    							result="";
//    							// ���ش�����־
                            }

                        } else {
//    						//ˢ��token
//    						getRefreshToken.getRefreshToken(request,shop,retStr,info);
//    						// ���ش�����־
                        }
                    }
                }
            	
            	resultString = downloadmethod.executeDownloadTask(taskList);
                return resultString;
                
            } catch (Exception e) {
            	ExceptionUtils.wrappBusinessException(e.getMessage());
                Logger.error(e.getMessage(), e);
                return null;
            }
       } while (hasNext);
        
        
    }

    /**
     * ��ȡ�����б�
     */
    public String getFullinfoDetail(String token,MCloudRequest request, Map<String, Object> map) {
        if (request != null && request.getRequest() != null) {
            if (request.getRequest() instanceof OrderSourceRequest) {
                return getCreatedDetails(token,request, map);
            }else {
                return null;
            }
        } else {
            return null;
        }

    }
    
    
    /**
     * ���ݴ���ʱ���ѯ���ж���
     *
     * @param request
     * @param map
     * @return
     */
    private String getCreatedDetails(String token,MCloudRequest request, Map<String, Object> map) {

        try {
        	
            OrderSourceRequest orderSourceRequest = (OrderSourceRequest) request.getRequest();
            orderSourceRequest.setFields("tid,num,status,modified");
            request.setRequest(orderSourceRequest);
            request.setMethod("getTradesSold");
            request.setSession(token);
            request.setAccess("3");
            
            return serviceUtil.execute(request);
        } catch (Exception e) {
        	ExceptionUtils.wrappBusinessException(e.getMessage());
            Logger.error(e.getMessage(), e);
            return null;
        }
    }
	
    
    //JSONת��
    public Object trans2Obj(String str, Class cl) {
        try {
//			str="{\"error_response\":{\"code\":27,\"msg\":\"Invalid session\",\"sub_code\":\"invalid-sessionkey\",\"request_id\":\"439fauhj7eq2\"}}";
            str = subStr(str);
            JsonConverter converter = new JsonConverter();
            Map<String, Object> map = new HashMap<String, Object>();
            JSONObject object = JSONObject.fromObject(str);
            Iterator iterator = object.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                map.put(key, object.get(key));
            }
            return converter.fromJson(map, cl);
        } catch (Exception e) {
        	ExceptionUtils.wrappBusinessException(e.getMessage());
            Logger.error(e.getMessage(), e);
            return null;
        }
    }

    private String subStr(String cloudPlatformData) throws Exception {
        if (null == cloudPlatformData) {
            throw new Exception("��������Ϊ�գ�" + cloudPlatformData);
        }
        for (int i = 0; i < 2; i++) {
            if (cloudPlatformData.indexOf("}") == -1) {
                Logger.error("�������ݸ�ʽ�е� } �����⣺" + cloudPlatformData);
                throw new Exception("�������ݸ�ʽ�е� } �����⣺" + cloudPlatformData);
            }
            cloudPlatformData = cloudPlatformData.substring(
                    cloudPlatformData.indexOf("{") + 1,
                    cloudPlatformData.lastIndexOf("}"));
        }
        cloudPlatformData = "{" + cloudPlatformData + "}";
        return cloudPlatformData;
    }

    
    private class InvokeDownload implements Callable<Map<String, Object>> {
    	String token;
    	MCloudRequest inRequest;
    	List<Trade> list;
    	String orgId;
        

        public InvokeDownload(String token, MCloudRequest inRequest,List<Trade> list,String orgId) {
        	this.token = token;
        	this.inRequest = inRequest;
            this.list = list;
            this.orgId = orgId;
           
        }

        @Override
        public Map<String, Object> call() throws Exception {

            List<Long> tids = new ArrayList<Long>();
            for (Trade item : list) {
                tids.add(item.getTid());
            }

            //Map<String,Object> result = new HashMap<String,Object>();
            if (tids.size() > 0) {
                List<Trade> retHashMap = new ArrayList<Trade>();
                return processItems(token,inRequest, list,retHashMap,orgId);
            }

            return null;
        }
    }
    
    
    /**
     * ������½��׶�����ϸ����
     */
    private Map<String, Object> processItems(String token,MCloudRequest inRequest,List<Trade> insList,List<Trade> retHashMap,String orgId) {
    	
        try {
        	Map<String, Object> returnval=new HashMap<String, Object>();
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < insList.size(); i++) {

            	
            	//ȡ����Tid
                String tid = String.valueOf(insList.get(i).getTid());
                
                String retStr1 = getTopFullinfo(token,inRequest, tid);
                Logger.info("�����Ա��ӿ� taobao.trade.fullinfo.get " + retStr1);
        
                
            	TradeFullinfoGetResponse response = (TradeFullinfoGetResponse) trans2Obj(retStr1, TradeFullinfoGetResponse.class);
            	
            	String errorCode = response.getErrorCode();
    			Logger.info("wyd rds errorCode" + errorCode);
    			if(StringUtils.isNotBlank(errorCode)){
    				Logger.error(errorCode + response.getSubMsg() );
    				throw new BusinessException(response.getSubMsg());
    			}
    			
    			
    			String url = "http://sjt.yonyoucloud.com/servlet/BaseHttpServlet";
        		Trade trade=response.getTrade();
        		List<Order> orderList = trade.getOrders();
        		
        		List<String> orderids = new ArrayList<String>();				
				orderids.add(trade.getTid().toString());
        		Logger.error("===query before lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
				if(!RuntimeEnv.getInstance().isDevelopMode()) {
					InvocationInfoProxy.getInstance().setUserDataSource("VM");
				} else if(InvocationInfoProxy.getInstance().getUserDataSource() == null){
					InvocationInfoProxy.getInstance().setUserDataSource("VM");
				}
				Logger.error("===query after lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
				List<String> existOrder = NCLocator.getInstance().lookup(ILazadaService.class).queryExistLazadaOrder(orderids);
				
				if(existOrder.size()<=0){
					
					//����ejb��������ܿ� add by weiningc 20190428
					NCLocator.getInstance().lookup(ILazadaService.class).insertlazadaresponse(taobaoBillTransform.convertTaobaoBill(trade,orgId,url), 
							taobaoBillTransform.convertTaobaoBillItem(trade,trade.getOrders()));
				

				}	
        		
        		
                
            }

           
            return returnval;
        } catch (Exception e) {
            Logger.info("getSourceDetail error:" + e.getMessage());
            Logger.error(e.getMessage(), e);
            return null;
        }

        //return result;
    }
    
    
    //ȡ������ϸ��Ϣ
    private String getTopFullinfo(String token,MCloudRequest request, String tid) {
        TradeFullinfoGetRequest req = new TradeFullinfoGetRequest();
        
        request.setRequest(req);
        request.setSession(token);
        request.setAccess("3");
        
        Map<String, Object> maps = new HashMap<String, Object>();
        return getTOPOrderFullinfo(request, maps,tid);
    }

    
    public String getTOPOrderFullinfo(MCloudRequest request, Map<String, Object> map,String tid) {

        try {
            TradeFullinfoGetRequest orderSourceRequest = (TradeFullinfoGetRequest) request.getRequest();
            StringBuilder sbField = new StringBuilder();
            sbField.append("buyerTaxNO,buyer_nick,coupon_fee,price,pic_path,title,type,created,tid,seller_rate,buyer_flag,buyer_rate,status,payment,adjust_fee,post_fee,total_fee,");
            sbField.append("pay_time,end_time,modified,consign_time,buyer_obtain_point_fee,point_fee,real_point_fee,received_payment,commission_fee,");
            sbField.append("buyer_memo,seller_memo,alipay_no,alipay_id,buyer_message,num_iid,num,buyer_alipay_no,receiver_name,receiver_state,receiver_city,");
            sbField.append("receiver_district,receiver_town,receiver_address,receiver_zip,receiver_mobile,receiver_phone,seller_flag,available_confirm_fee,has_post_fee,");
            sbField.append("timeout_action_time,cod_fee,cod_status,shipping_type,trade_memo,is_3D,buyer_email,buyer_area,trade_from,is_lgtype,is_force_wlb,");
            sbField.append("is_brand_sale,buyer_cod_fee,discount_fee,seller_cod_fee,express_agency_fee,invoice_name,service_orders, buyer_memo,buyer_flag,is_part_consign,");
            sbField.append("orders.refund_status,orders.title,orders.price,orders.num,orders.num_iid,orders.sku_id,orders.status,orders.oid,orders.total_fee,orders.payment,orders.discount_fee,orders.adjust_fee,");
            sbField.append("orders.sku_properties_name,orders.item_meal_name,orders.item_meal_id,item_memo,orders.buyer_rate,orders.seller_rate,orders.outer_iid,orders.outer_sku_id,");
            sbField.append("orders.refund_id,orders.seller_type,orders.is_oversold,promotion_details,orders.logistics_company,orders.invoice_no,orders.divide_order_fee,is_sh_ship, orders.store_code,");
            sbField.append("order_tax_fee, orders.sub_order_tax_fee, orders.bind_oid, orders.consign_time, orders.shipping_type,orders.part_mjz_discount,");
            sbField.append("service_orders,service_orders.buyer_nick,service_orders.item_oid,service_orders.num,service_orders.price,service_orders.seller_nick,");
            sbField.append("service_orders.service_id,service_orders.title,service_orders.tmser_spu_code,service_orders.oid,service_orders.payment,service_orders.total_fee");
            orderSourceRequest.setFields(sbField.toString());
            orderSourceRequest.setTid(tid);


            request.setRequest(orderSourceRequest);
            request.setMethod("getTradeFullInfoDecrypt");

            return serviceUtil.execute(request);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return null;
        }
    }
	
    
    @Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
