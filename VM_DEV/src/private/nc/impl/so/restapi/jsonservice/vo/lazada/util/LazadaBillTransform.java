package nc.impl.so.restapi.jsonservice.vo.lazada.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillItemVO;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillVO;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderDetailResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaProductsInfo;
import nc.pub.templet.converter.util.helper.ExceptionUtils;
import nc.vo.pub.lang.UFDate;



/**
 * lazada 鍘熷崟VO
 * @author ll
 *
 */


public class LazadaBillTransform {
	
	
	
	   //杞崲鍘熷崟order
		public LazadaBillVO[] convertLazadaBill(LazadaGetOrderDetailResponse order,String orgId,String requestUrl,List<LazadaProductsInfo> lazadaProductsInfoResponse){

			List<LazadaBillVO> billList = new ArrayList<LazadaBillVO>();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			UFDate created_at = null;
			UFDate updated_at = null;;
			try {
				created_at = new UFDate(format.parse(order.getCreated_at()));
				updated_at = new UFDate(format.parse(order.getUpdated_at()));
			} catch (ParseException e) {
				ExceptionUtils.wrapException("瑙ｆ瀽鏃ユ湡閿欒" + e.getMessage(), e);
			}
		
			LazadaBillVO billvo = new LazadaBillVO();
			
			billvo.setId(order.getOrder_id().toString());
			billvo.setOrder_id(order.getOrder_id().toString());
			
			String price = order.getPrice();
			price=price.replace(",","");
			billvo.setPrice(new BigDecimal(price));
			
			String voucher = order.getPrice();
			voucher=voucher.replace(",","");
			billvo.setVoucher(new BigDecimal(voucher));
			billvo.setShipping_fee(new BigDecimal(order.getShipping_fee()));
			billvo.setCustomer_first_name(order.getCustomer_first_name());
			billvo.setCustomer_last_name(order.getCustomer_last_name());
			billvo.setVoucher_code(order.getVoucher_code());
			billvo.setDelivery_info(order.getDelivery_info());
			billvo.setGift_message(order.getGift_message());
			billvo.setBranch_number(order.getBranch_number());
			billvo.setTax_code(order.getTax_code());
			billvo.setExtra_attributes(order.getExtra_attributes());
			billvo.setPayment_method(order.getPayment_method());
			billvo.setStatuses(order.getStatuses().get(0));
			
			billvo.setRemarks(order.getRemarks());
			billvo.setOrder_number(order.getOrder_number().toString());
			billvo.setVoucher(new BigDecimal(order.getVoucher()));
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
			billvo.setGift_option(order.getGift_option());
			
			
			
			billvo.setUpdated_at(updated_at);
			
			//璁剧疆鏈鏇存柊鏃堕棿
			Date lastUpdateTime = new Date();
			billvo.setLastUpdateTime(new UFDate(lastUpdateTime));
			
			
			//
			billvo.setOrgId(orgId);
			billvo.setRequestUrl(requestUrl);
			
			billvo.setPlatform("LAZADA");
			billvo.setShopname(lazadaProductsInfoResponse.get(0).getShop_id());
			
			billList.add(billvo);
			
			
			
			
			LazadaBillVO[] arr = (LazadaBillVO[])billList.toArray(new LazadaBillVO[billList.size()]);
			return arr;
				
		}
	
	
	//杞崲鍘熷崟orderitem
	public LazadaBillItemVO[] convertLazadaBillItem(List<LazadaProductsInfo> orderItemList){
		List<LazadaBillItemVO> billItemList = new ArrayList<LazadaBillItemVO>();
		
		if(orderItemList.size()<=0) return null;
		
		for(LazadaProductsInfo product : orderItemList){
			LazadaBillItemVO billitem = new LazadaBillItemVO();
			
			
			billitem.setItemid(product.getOrder_item_id());
			
			if(product.getWallet_credits()!=null){
				billitem.setWallet_credits(new BigDecimal(product.getWallet_credits()));
			}
		
			billitem.setOrder_id(product.getOrder_id().toString());
			billitem.setOrder_item_id(product.getOrder_item_id());
			billitem.setCurrency(product.getCurrency());
			if(product.getItem_price()!=null){
				billitem.setItem_price(new BigDecimal(product.getItem_price()));
			}
			
			billitem.setTracking_code(product.getTracking_code());
			billitem.setShipment_provider(product.getShipment_provider());
			billitem.setName(product.getName());
			if(product.getVoucher_amount()!=null){
				billitem.setVoucher_amount(new BigDecimal(product.getVoucher_amount()));
			}
			
			billitem.setOrderStatus(product.getStatus());
			billitem.setTax_amount(new BigDecimal(product.getTax_amount()));
			billitem.setPaid_price(new BigDecimal(product.getPaid_price()));
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
				billitem.setVoucher_seller(new BigDecimal(voucher_seller));
			}
		
			billitem.setCancel_return_initiator(product.getCancel_return_initiator());
			billitem.setReturn_status(product.getReturn_status());
			billitem.setProduct_detail_url(product.getProduct_detail_url());
			billitem.setProduct_main_image(product.getProduct_main_image());
			//鏁伴噺 榛樿涓� add by weiningc 
			billitem.setQty(new java.math.BigDecimal(1.00));
			
			
			billItemList.add(billitem);
		}
		
		LazadaBillItemVO[] arr = (LazadaBillItemVO[])billItemList.toArray(new LazadaBillItemVO[billItemList.size()]);
		return arr;
			
	}

}
