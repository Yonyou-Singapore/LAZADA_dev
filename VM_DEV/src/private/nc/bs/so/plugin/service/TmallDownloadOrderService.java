package nc.bs.so.plugin.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
import nc.bs.logging.Logger;
import nc.bs.so.component.ace.bp.AceComponentInsertBP;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.DownloadMethod;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaJsonUtils;
import nc.impl.so.restapi.jsonservice.vo.taobao.util.MCloudRequest;
import nc.impl.so.restapi.jsonservice.vo.taobao.util.ServiceUtil;
import nc.impl.so.restapi.jsonservice.vo.taobao.util.TaobaoBillTransform;
import nc.impl.so.restapi.jsonservice.vo.taobao.util.TradeFullinfoGetRequest;
import nc.pub.so.apiservice.ILazadaService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.component.AggSo_ordercenter;
import nc.vo.so.component.PlatFormVO;
import nc.vo.so.component.PlatformEnum;
import nc.vo.so.component.So_ordercenter;
import nc.vo.so.component.So_ordercenter_b;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;
import com.taobao.api.internal.parser.json.JsonConverter;
import com.taobao.api.response.TradeFullinfoGetResponse;

/**
 * 
 * 
 * 
 */
public class TmallDownloadOrderService {
	DownloadMethod downloadmethod = new DownloadMethod();
	
	LazadaJsonUtils lazadaJsonUtil = new LazadaJsonUtils();
	TaobaoBillTransform taobaoBillTransform = new TaobaoBillTransform();
	String token;
	MCloudRequest inRequest;
	List<Trade> list;
	String orgId;
	Date lastModifiedTime;
	private ServiceUtil serviceUtil = new ServiceUtil();
	//数据通请求地址
	private static final String sjturl = "http://sjt.yonyoucloud.com/servlet/BaseHttpServlet";

	public TmallDownloadOrderService(String token, MCloudRequest inRequest,
			List<Trade> list, String orgId, Date lastModifiedTime) {
		this.token = token;
		this.inRequest = inRequest;
		this.list = list;
		this.orgId = orgId;
		this.lastModifiedTime = lastModifiedTime;
		this.initSaleorgCache();

	}

	public static final String COBILLTYPE = "CO01";
	public static Map<String, String> saleorgcache = null;
	public static Map<String, String> currencycache = null;
	public static Map<String, String> saleorgvcache = null;

	private void initSaleorgCache() {
		if (saleorgcache == null || currencycache == null || saleorgvcache == null) {
			saleorgcache = new HashMap<String, String>();
			StringBuffer sb = new StringBuffer();
			sb.append("select code, pk_salesorg from org_salesorg");
			DataAccessUtils util = new DataAccessUtils();
			IRowSet rowset = util.query(sb.toString());
			while (rowset.next()) {
				saleorgcache.put(rowset.getString(0), rowset.getString(1));
			}
			currencycache = new HashMap<String, String>();
			IRowSet rowset2 = util
					.query("select code, pk_currtype from bd_currtype");
			while (rowset2.next()) {
				currencycache.put(rowset2.getString(0), rowset2.getString(1));
			}
			saleorgvcache = new HashMap<String, String>();
	        IRowSet rowset3 = util.query("select code, pk_vid from org_salesorg_v");
	        while(rowset3.next()) {
	        	saleorgvcache.put(rowset3.getString(0), rowset3.getString(1));
	        }
		}

	}

	public Map<String, Object> call() throws Exception {

        List<Long> tids = new ArrayList<Long>();
        for (Trade item : list) {
            tids.add(item.getTid());
        }

        //Map<String,Object> result = new HashMap<String,Object>();
        if (tids.size() > 0) {
            List<Trade> retHashMap = new ArrayList<Trade>();
            return processItems(token,inRequest, list,retHashMap,orgId,lastModifiedTime);
        }

        return null;
    }

	/**
     * 保存更新交易订单详细数据
     */
    private Map<String, Object> processItems(String token,MCloudRequest inRequest,List<Trade> insList,List<Trade> retHashMap,String orgId,Date lastModifiedTime) {
    	
        try {
        	
        	
        	List<String> tidlists = new ArrayList<String>();	
        	for(Trade trade: insList){
        		tidlists.add(trade.getTid().toString());
        	}
			
    		Logger.error("===query before Taobao datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
			if(!RuntimeEnv.getInstance().isDevelopMode()) {
				InvocationInfoProxy.getInstance().setUserDataSource("VM");
			} else if(InvocationInfoProxy.getInstance().getUserDataSource() == null){
				InvocationInfoProxy.getInstance().setUserDataSource("VM");
			}
			Logger.error("===query after taobao datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
			List<String> existOrder = NCLocator.getInstance().lookup(ILazadaService.class).queryExistLazadaOrder(tidlists);
			

        	Map<String, Object> returnval=new HashMap<String, Object>();
            List<String> list = new ArrayList<String>();
            //批量保存
    		List<AggSo_ordercenter> aggs = new ArrayList<AggSo_ordercenter>();
            for (int i = 0; i < insList.size(); i++) {
            	
            	//取单据Tid
                String tid = String.valueOf(insList.get(i).getTid());
            	if(!existOrder.contains(tid)){
            		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	
                    String retStr1 = getTopFullinfo(token,inRequest, tid);
                    Logger.info("调用淘宝接口 taobao.trade.fullinfo.get " + retStr1);
            
                    
                	TradeFullinfoGetResponse response = (TradeFullinfoGetResponse) trans2Obj(retStr1, TradeFullinfoGetResponse.class);
                	
                	String errorCode = response.getErrorCode();
        			Logger.info("wyd rds errorCode" + errorCode);
        			if(StringUtils.isNotBlank(errorCode)){
        				Logger.error(errorCode + response.getSubMsg() );
        				throw new BusinessException(response.getSubMsg());
        			}
        			
            		Trade trade=response.getTrade();	
//    				NCLocator.getInstance().lookup(ILazadaService.class).insertlazadaresponse(taobaoBillTransform.convertTaobaoBill(trade,orgId,sjturl), 
//    							taobaoBillTransform.convertTaobaoBillItem(trade,trade.getOrders()));
            		AggSo_ordercenter agg = this.convertOrdercenterVOandSave(trade, orgId, sjturl, trade.getOrders());
    				aggs.add(agg);
            	}

            }
            if(aggs.size()>0){
            	AceComponentInsertBP bp = new AceComponentInsertBP();
          		bp.insert(aggs.toArray(new AggSo_ordercenter[0]));
            }
        
            return returnval;
        } catch (Exception e) {
        	ExceptionUtils.wrappBusinessException(e.getMessage());
            Logger.info("getSourceDetail error:" + e.getMessage());
            Logger.error(e.getMessage(), e);
            return null;
        }

        //return result;
    }
    
    
    private AggSo_ordercenter convertOrdercenterVOandSave(Trade trade,
			String orgId, String url, List<Order> orders) {
    	AggSo_ordercenter agg = new AggSo_ordercenter();
		So_ordercenter hvo = this.convertOrdercenterHVO(trade,orgId, url,
				orders);
		So_ordercenter_b[] bvos = this.converOrderCenterBVO(trade, orders);
		if(bvos == null || bvos.length == 0) {
			bvos = this.converOrderCenterBVO(trade, orders);
		}
		agg.setParentVO(hvo);
		agg.setChildrenVO(bvos);
		return agg;
//		AceComponentInsertBP bp = new AceComponentInsertBP();
//		bp.insert(new AggSo_ordercenter[] {agg});

	}

	private So_ordercenter_b[] converOrderCenterBVO(Trade trade,List<Order> orderItemList) {
		List<So_ordercenter_b> billItemList = new ArrayList<So_ordercenter_b>();
		
		if(orderItemList.size()<=0) return null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		for(Order order : orderItemList){
			So_ordercenter_b billitem = new So_ordercenter_b();
			
			
			billitem.setOrder_id(order.getOid().toString());
			
//			if(product.getWallet_credits()!=null){
//				billitem.setWallet_credits(new BigDecimal(product.getWallet_credits()));
//			}
		
			billitem.setOrder_id(trade.getTid().toString());
			billitem.setOrder_item_id(order.getOid().toString());
			//billitem.setCurrency(product.getCurrency());
			if(order.getPrice()!=null){
				billitem.setItem_price(new UFDouble(order.getPrice()));
			}
			
			billitem.setTracking_code(order.getInvoiceNo());
			billitem.setShipment_provider(order.getLogisticsCompany());
			billitem.setName(order.getTitle());
			if(order.getPartMjzDiscount()!=null){
				billitem.setVoucher_amount(new UFDouble(order.getPartMjzDiscount()));
			}
			
			billitem.setOrderstatus(order.getStatus());
			
			if(order.getSubOrderTaxFee()!=null){
				billitem.setTax_amount(new UFDouble(order.getSubOrderTaxFee()));
			}
			
			if(order.getPayment()!=null){
				billitem.setPaid_price(new UFDouble(order.getPayment()));
			}
			
			
			Date created_at = trade.getCreated();
			billitem.setCreated_at(format.format(created_at));
			billitem.setSku(order.getOuterSkuId());//修改为商家编码order.getOuterSkuId()
			billitem.setPurchase_order_id(trade.getTid().toString());
			//billitem.setPackage_id(product.getPackage_id());
			billitem.setShipping_type(order.getShippingType());
			
			//交易标题，以店铺名作为此标题的值。注:taobao.trades.get接口返回的Trade中的title是商品名称
			billitem.setShop_id(trade.getTitle());
			
			if(order.getNumIid()!=null){
				billitem.setShop_sku(order.getOuterIid());//修改为商家编码order.getOuterIid()
			}
			billitem.setInvoice_number(order.getInvoiceNo());
			//billitem.setIs_digital(product.getIs_digital());
			
//			if(product.getVoucher_seller()!=null){
//				String voucher_seller = product.getVoucher_seller();
//				voucher_seller=voucher_seller.replace(",","");
//				billitem.setVoucher_seller(new BigDecimal(voucher_seller));
//			}
		
			//billitem.setCancel_return_initiator(product.getCancel_return_initiator());
			billitem.setReturn_status(order.getRefundStatus());
			//billitem.setProduct_detail_url(product.getProduct_detail_url());
			billitem.setProduct_main_image(order.getPicPath());
			//数量 add by weiningc 
			billitem.setQty(new UFDouble(order.getNum()));
			billitem.setStatus(VOStatus.NEW);
			//发货库存组织及版本字段默认值处理  add by weiningc 20190705 start
			billitem.setCsendstockorgid(this.queryOrgPK(orgId));
			billitem.setCsendstockorgvid(saleorgvcache.get(orgId));
			//end
			billItemList.add(billitem);
		}
		
		So_ordercenter_b[] arr = (So_ordercenter_b[])billItemList.toArray(new So_ordercenter_b[billItemList.size()]);
		return arr;
			
	}

	private So_ordercenter convertOrdercenterHVO(Trade trade, String orgId2,
			String url, List<Order> orders) {
		So_ordercenter billvo = new So_ordercenter();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UFDate created_at = null;
		UFDate updated_at = null;;
		try {
			created_at = new UFDate(trade.getCreated());
			updated_at = new UFDate(trade.getModified());
		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException("time format incorrect:" + e.getMessage());
		}
		
		billvo.setOrder_id(trade.getTid().toString());
		billvo.setOrder_number(trade.getTid().toString());
		
	
		
		if(trade.getPrice()!=null){
			String price = trade.getPrice();
			price=price.replace(",","");
			billvo.setPrice(new UFDouble(price));
		}
		
		
		if(trade.getDiscountFee()!=null){
			String voucher = trade.getDiscountFee();
			voucher=voucher.replace(",","");
			billvo.setVoucher(new UFDouble(voucher));
		}
		
		if(trade.getPostFee()!=null){
			billvo.setShipping_fee(new UFDouble(trade.getPostFee()));
		}
		billvo.setCustomer_first_name(trade.getReceiverName());
		billvo.setCustomer_last_name(trade.getReceiverName());
		
		if(trade.getPromotionDetails()!=null && trade.getPromotionDetails().size()>0){
			billvo.setVoucher_code(trade.getPromotionDetails().get(0).getPromotionId());
		}
		//billvo.setDelivery_info("");
		//billvo.setGift_message(trade.getisg);
		//billvo.setBranch_number(order.getBranch_number());
		//billvo.setTax_code(order.getTax_code());
		//billvo.setExtra_attributes(order.getExtra_attributes());
		//billvo.setPayment_method(order.getPayment_method());
		billvo.setOrder_status(trade.getStatus());
		
		billvo.setRemarks(trade.getBuyerMessage());
		//billvo.setOrder_number(trade.getNumIid());
		//billvo.setPromised_shipping_times(order.getPromised_shipping_times());
		if(trade.getNum()!=null){
			billvo.setItems_count(Integer.parseInt(trade.getNum().toString()));
		}
		billvo.setCreated_at(created_at);
	
		billvo.setShipping_address1(trade.getReceiverAddress());
		billvo.setShipping_address2(trade.getReceiverDistrict());
		billvo.setShipping_address3(trade.getReceiverState());
		billvo.setShipping_address4(trade.getReceiverCity());
		//billvo.setShipping_address5();
		
		billvo.setShipping_post_code(trade.getReceiverZip());
		billvo.setShipping_last_name(trade.getReceiverName());
		billvo.setShipping_country(trade.getReceiverCountry());
		billvo.setShipping_city(trade.getReceiverCity());
		billvo.setShipping_phone2(trade.getReceiverPhone());
		billvo.setShipping_first_name(trade.getReceiverName());
		billvo.setShipping_phone(trade.getReceiverMobile());
		
		billvo.setBilling_address1(trade.getReceiverAddress());
		billvo.setBilling_address2(trade.getReceiverDistrict());
		billvo.setBilling_address3(trade.getReceiverState());
		billvo.setBilling_address4(trade.getReceiverCity());
		//billvo.setBilling_address5(order.getAddress_billing().getAddress5());
		
		billvo.setBilling_post_code(trade.getReceiverZip());
		billvo.setBilling_last_name(trade.getReceiverName());
		billvo.setBilling_country(trade.getReceiverCountry());
		billvo.setBilling_city(trade.getReceiverCity());
		billvo.setBilling_phone2(trade.getReceiverPhone());
		billvo.setBilling_first_name(trade.getReceiverName());
		billvo.setBilling_phone(trade.getReceiverMobile());
		
		//billvo.setGift_option(order.getGift_option());
		
		
		
		billvo.setUpdated_at(updated_at);
		
		billvo.setLastupdatetime(new UFDate());
		
		//单据日期
		billvo.setPk_org(this.queryOrgPK(orgId));
		billvo.setPk_org_v(saleorgvcache.get(orgId));
		billvo.setPk_group(AppContext.getInstance().getPkGroup());
		billvo.setRequesturl(url);
		billvo.setBilldate(new UFDate(trade.getCreated()));
		billvo.setBilltype(COBILLTYPE);
		billvo.setTranstype(COBILLTYPE);
		billvo.setTranstypepk(COBILLTYPE);
		billvo.setPk_currtype(currencycache.get("CNY"));
		billvo.setPlatform(PlatformEnum.TMALL.value().toString());
		billvo.setStatus(VOStatus.NEW);
		
		return billvo;
	}

	//取订单详细信息
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

	private String queryOrgPK(String orgcode) {
		return saleorgcache.get(orgcode);
	}
	//JSON convert
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
            throw new Exception("返回数据为空" + cloudPlatformData);
        }
        for (int i = 0; i < 2; i++) {
            if (cloudPlatformData.indexOf("}") == -1) {
                Logger.error("返回数据格式中的 } 有问题" + cloudPlatformData);
                throw new Exception("返回数据格式中的 } 有问题" + cloudPlatformData);
            }
            cloudPlatformData = cloudPlatformData.substring(
                    cloudPlatformData.indexOf("{") + 1,
                    cloudPlatformData.lastIndexOf("}"));
        }
        cloudPlatformData = "{" + cloudPlatformData + "}";
        return cloudPlatformData;
    }
}
