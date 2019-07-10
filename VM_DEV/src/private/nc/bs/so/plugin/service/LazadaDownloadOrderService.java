package nc.bs.so.plugin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
import nc.bs.logging.Logger;
import nc.bs.so.component.ace.bp.AceComponentInsertBP;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaBillTransform;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaClientService;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaJsonUtils;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderDetailResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaProductsInfo;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaProductsInfoDataResponse;
import nc.pub.so.apiservice.ILazadaService;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;

/**
 * 
 * @author weiningc
 * 
 */
public class LazadaDownloadOrderService {
	LazadaClientService lazadaClientService = new LazadaClientService();
	LazadaJsonUtils lazadaJsonUtil = new LazadaJsonUtils();
	LazadaBillTransform lazadaBillTransform = new LazadaBillTransform();
	String url;
	String token;
	String orgId;
	List<LazadaGetOrderDetailResponse> itemsList;
	public static final String COBILLTYPE = "CO01";
	public static Map<String, String> saleorgcache = null;
	public static Map<String, String> currencycache = null;
	public static Map<String, String> saleorgvcache = null;

	public LazadaDownloadOrderService(String url, String token, String orgId,
			List<LazadaGetOrderDetailResponse> itemsList) throws Exception {
		this.url = url;
		this.token = token;
		this.orgId = orgId;
		this.itemsList = itemsList;
		this.initSaleorgCache();
	}
	
	private void initSaleorgCache() {
		if(saleorgcache == null || currencycache == null || saleorgvcache == null) {
			saleorgcache = new HashMap<String, String>();
			StringBuffer sb = new StringBuffer();
			sb.append("select code, pk_salesorg from org_salesorg");
			DataAccessUtils util = new DataAccessUtils();
	        IRowSet rowset = util.query(sb.toString());
	        while(rowset.next()) {
	        	saleorgcache.put(rowset.getString(0), rowset.getString(1));
	        }
	        currencycache = new HashMap<String, String>();
	        IRowSet rowset2 = util.query("select code, pk_currtype from bd_currtype");
	        while(rowset2.next()) {
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

		Map<String, Object> newmap = new HashMap<String, Object>();
		
		//批量保存
		List<AggSo_ordercenter> aggs = new ArrayList<AggSo_ordercenter>();
		
		//查询数据库中是否已存在原单

		List<String> orderids = new ArrayList<String>();				
		
		for(LazadaGetOrderDetailResponse orderitem:itemsList ){
			orderids.add(orderitem.getOrder_id().toString());
		}
		
		Logger.error("===query before lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
		if(!RuntimeEnv.getInstance().isDevelopMode()) {
			InvocationInfoProxy.getInstance().setUserDataSource("VM");
		} else if(InvocationInfoProxy.getInstance().getUserDataSource() == null){
			InvocationInfoProxy.getInstance().setUserDataSource("VM");
		}
		Logger.error("===query after lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
		List<String> existOrder = NCLocator.getInstance().lookup(ILazadaService.class).queryExistLazadaOrder(orderids);
		
		List<LazadaGetOrderDetailResponse> lazadaGetOrderDetaillist = new ArrayList<LazadaGetOrderDetailResponse>();

		for (LazadaGetOrderDetailResponse item : itemsList) {		

			if(!existOrder.contains(item.getOrder_id().toString())){
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
				//
//				NCLocator.getInstance().lookup(ILazadaService.class).insertlazadaresponse(lazadaBillTransform.convertLazadaBill(item,orgId,url,lazadaProductsInfoResponse), 
//						lazadaBillTransform.convertLazadaBillItem(lazadaProductsInfoResponse));
				AggSo_ordercenter agg = this.convertOrdercenterVOandSave(item, orgId, url, lazadaProductsInfoResponse);
				aggs.add(agg);
				lazadaGetOrderDetaillist.add(item);
				
				Logger.info("原单下载，调用lazada接口 检索单个订单信息 返回数据" +itemsByOrderId);
				
			}
		}
		AceComponentInsertBP bp = new AceComponentInsertBP();
		if(aggs != null && aggs.size() > 0) {
			bp.insert(aggs.toArray(new AggSo_ordercenter[0]));
		}
//		JSONArray toJSON = lazadaJsonUtil.convertJSON(lazadaGetOrderDetaillist);		
//		newmap.put("result", toJSON);
		
		return newmap;
	}
	
	/**
	 * @param header
	 * @param orgId
	 * @param url
	 * @param lazadaProductsInfoResponse
	 */
	private AggSo_ordercenter convertOrdercenterVOandSave(
			LazadaGetOrderDetailResponse header, String orgId, String url,
			List<LazadaProductsInfo> lazadaProductsInfoResponse) {
		AggSo_ordercenter agg = new AggSo_ordercenter();
		So_ordercenter hvo = this.convertOrdercenterHVO(header,orgId, url,
				lazadaProductsInfoResponse);
		So_ordercenter_b[] bvos = this.converOrderCenterBVO(lazadaProductsInfoResponse);
		agg.setParentVO(hvo);
		agg.setChildrenVO(bvos);
		return agg;
//		AceComponentInsertBP bp = new AceComponentInsertBP();
//		bp.insert(new AggSo_ordercenter[] {agg});
		
	}

	private So_ordercenter_b[] converOrderCenterBVO(
			List<LazadaProductsInfo> orderItemList) {
		List<So_ordercenter_b> billItemList = new ArrayList<So_ordercenter_b>();
		
		if(orderItemList.size()<=0) return null;
		
		for(LazadaProductsInfo product : orderItemList){
			So_ordercenter_b billitem = new So_ordercenter_b();
			
			
			billitem.setOrder_number(product.getOrder_id().toString());
			billitem.setOrder_item_id(product.getOrder_item_id());
			
			if(product.getWallet_credits()!=null){
				billitem.setWallet_credits(product.getWallet_credits());
			}
		
			billitem.setOrder_id(product.getOrder_id().toString());
			billitem.setCurrency(product.getCurrency());
			if(product.getItem_price()!=null){
				billitem.setItem_price(new UFDouble(product.getItem_price()));
			}
			
			billitem.setTracking_code(product.getTracking_code());
			billitem.setShipment_provider(product.getShipment_provider());
			billitem.setName(product.getName());
			if(product.getVoucher_amount()!=null){
				billitem.setVoucher_amount(new UFDouble(product.getVoucher_amount()));
			}
			
			billitem.setOrderstatus(product.getStatus());
			billitem.setTax_amount(new UFDouble(product.getTax_amount()));
			billitem.setPaid_price(new UFDouble(product.getPaid_price()));
			billitem.setCreated_at(product.getCreated_at());
			billitem.setSku(product.getSku());
			billitem.setPurchase_order_id(product.getPurchase_order_number());
			billitem.setPackage_id(product.getPackage_id());
			billitem.setShipping_type(product.getShipping_type());
			billitem.setShop_id(product.getShop_id());
			billitem.setShop_sku(product.getShop_sku());
			billitem.setInvoice_number(product.getInvoice_number());
			billitem.setIs_digital(product.getIs_digital());
			
			if(product.getVoucher_seller()!=null){
				String voucher_seller = product.getVoucher_seller();
				voucher_seller=voucher_seller.replace(",","");
				billitem.setVoucher_seller(new UFDouble(voucher_seller));
			}
		
			billitem.setCancel_return_initiator(product.getCancel_return_initiator());
			billitem.setReturn_status(product.getReturn_status());
			billitem.setProduct_detail_url(product.getProduct_detail_url());
			billitem.setProduct_main_image(product.getProduct_main_image());
			//qty add by weiningc 
			billitem.setQty(UFDouble.ONE_DBL);
			
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

	private So_ordercenter convertOrdercenterHVO(
			LazadaGetOrderDetailResponse order, String orgId,
			String url, List<LazadaProductsInfo> lazadaProductsInfoResponse) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UFDate created_at = null;
		UFDate updated_at = null;;
		try {
			created_at = new UFDate(format.parse(order.getCreated_at()));
			updated_at = new UFDate(format.parse(order.getUpdated_at()));
		} catch (ParseException e) {
			ExceptionUtils.wrappBusinessException("解析日期错误");
		}
	
		So_ordercenter billvo = new So_ordercenter();
		
		billvo.setOrder_number(order.getOrder_id().toString());
		billvo.setOrder_id(order.getOrder_id().toString());
		
		String price = order.getPrice();
		price=price.replace(",","");
		billvo.setPrice(new UFDouble(price));
		
		String voucher = order.getPrice();
		voucher=voucher.replace(",","");
		billvo.setVoucher(new UFDouble(voucher));
		billvo.setShipping_fee(new UFDouble(order.getShipping_fee()));
		billvo.setCustomer_first_name(order.getCustomer_first_name());
		billvo.setCustomer_last_name(order.getCustomer_last_name());
		billvo.setVoucher_code(order.getVoucher_code());
		billvo.setDelivery_info(order.getDelivery_info());
		billvo.setGift_message(order.getGift_message());
		billvo.setBranch_number(order.getBranch_number());
		billvo.setTax_code(order.getTax_code());
		billvo.setExtra_attributes(order.getExtra_attributes());
		billvo.setPayment_method(order.getPayment_method());
		billvo.setOrder_status(order.getStatuses().get(0));
		
		billvo.setRemarks(order.getRemarks());
		billvo.setOrder_number(order.getOrder_number().toString());
		billvo.setVoucher(new UFDouble(order.getVoucher()));
		billvo.setPromised_shipping_times(order.getPromised_shipping_times());
		billvo.setItems_count(order.getItems_count());
		billvo.setCreated_at(created_at);
	
		billvo.setShipping_address1(order.getAddress_shipping().getAddress1());
		billvo.setShipping_address2(order.getAddress_shipping().getAddress2());
		billvo.setShipping_address3(order.getAddress_shipping().getAddress3());
		billvo.setShipping_address4(order.getAddress_shipping().getAddress4());
		billvo.setShipping_address5(order.getAddress_shipping().getAddress5());
		
		billvo.setShipping_post_code(order.getAddress_shipping().getPost_code());
		billvo.setShipping_last_name(order.getAddress_shipping().getLast_name());
		billvo.setShipping_country(order.getAddress_shipping().getCountry());
		billvo.setShipping_city(order.getAddress_shipping().getCity());
		billvo.setShipping_phone2(order.getAddress_shipping().getPhone2());
		billvo.setShipping_first_name(order.getAddress_shipping().getFirst_name());
		billvo.setShipping_phone(order.getAddress_shipping().getPhone());
		
		billvo.setBilling_address1(order.getAddress_billing().getAddress1());
		billvo.setBilling_address2(order.getAddress_billing().getAddress2());
		billvo.setBilling_address3(order.getAddress_billing().getAddress3());
		billvo.setBilling_address4(order.getAddress_billing().getAddress4());
		billvo.setBilling_address5(order.getAddress_billing().getAddress5());
		
		billvo.setBilling_post_code(order.getAddress_billing().getPost_code());
		billvo.setBilling_last_name(order.getAddress_billing().getLast_name());
		billvo.setBilling_country(order.getAddress_billing().getCountry());
		billvo.setBilling_city(order.getAddress_billing().getCity());
		billvo.setBilling_phone2(order.getAddress_billing().getPhone2());
		billvo.setBilling_first_name(order.getAddress_billing().getFirst_name());
		billvo.setBilling_phone(order.getAddress_billing().getPhone());
		billvo.setGift_option(UFBoolean.FALSE);
		
		
		
		billvo.setUpdated_at(updated_at);
		
		UFDate lastUpdateTime = new UFDate();
		billvo.setLastupdatetime(lastUpdateTime);
		
		billvo.setPk_org(this.queryOrgPK(orgId));
		billvo.setPk_org_v(saleorgvcache.get(orgId));
		billvo.setPk_group(AppContext.getInstance().getPkGroup());
		billvo.setRequesturl(url);
		billvo.setStatus(VOStatus.NEW);
		//单据日期
		billvo.setBilldate(new UFDate(order.getCreated_at()));
		billvo.setBilltype(COBILLTYPE);
		billvo.setTranstype(COBILLTYPE);
		billvo.setTranstypepk(COBILLTYPE);
		billvo.setPk_currtype(currencycache.get(lazadaProductsInfoResponse.get(0).getCurrency()));
		billvo.setPlatform(PlatformEnum.LAZADA.value().toString());
//		billvo.setShopname(lazadaProductsInfoResponse.get(0).getShop_id());
		
		return billvo;
	}

	private String queryOrgPK(String orgcode) {
		return saleorgcache.get(orgcode);
	}
}
