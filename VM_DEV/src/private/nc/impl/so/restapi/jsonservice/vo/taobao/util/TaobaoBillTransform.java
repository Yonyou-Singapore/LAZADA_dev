package nc.impl.so.restapi.jsonservice.vo.taobao.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;

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


public class TaobaoBillTransform {
	
	
	
	   //杞崲鍘熷崟order
		public LazadaBillVO[] convertTaobaoBill(Trade trade,String orgId,String requestUrl){

			List<LazadaBillVO> billList = new ArrayList<LazadaBillVO>();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			UFDate created_at = null;
			UFDate updated_at = null;;
			try {
				created_at = new UFDate(trade.getCreated());
				updated_at = new UFDate(trade.getModified());
			} catch (Exception e) {
				ExceptionUtils.wrapException("瑙ｆ瀽鏃ユ湡閿欒" + e.getMessage(), e);
			}
		
			LazadaBillVO billvo = new LazadaBillVO();
			
			billvo.setId(trade.getTid().toString());
			billvo.setOrder_id(trade.getTid().toString());
			
		
			
			if(trade.getPrice()!=null){
				String price = trade.getPrice();
				price=price.replace(",","");
				billvo.setPrice(new BigDecimal(price));
			}
			
			
			if(trade.getDiscountFee()!=null){
				String voucher = trade.getDiscountFee();
				voucher=voucher.replace(",","");
				billvo.setVoucher(new BigDecimal(voucher));
			}
			
			if(trade.getPostFee()!=null){
				billvo.setShipping_fee(new BigDecimal(trade.getPostFee()));
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
			billvo.setStatuses(trade.getStatus());
			
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
			
			//璁剧疆鏈鏇存柊鏃堕棿
			Date lastUpdateTime = new Date();
			billvo.setLastUpdateTime(new UFDate(lastUpdateTime));
			
			
			//璁剧疆缁勭粐鍙傛暟 bill
			billvo.setOrgId(orgId);
			billvo.setRequestUrl(requestUrl);
			billList.add(billvo);
			
			
			
			
			LazadaBillVO[] arr = (LazadaBillVO[])billList.toArray(new LazadaBillVO[billList.size()]);
			return arr;
				
		}
	
	
	//杞崲鍘熷崟orderitem
	public LazadaBillItemVO[] convertTaobaoBillItem(Trade trade,List<Order> orderItemList){
		List<LazadaBillItemVO> billItemList = new ArrayList<LazadaBillItemVO>();
		
		if(orderItemList.size()<=0) return null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		for(Order order : orderItemList){
			LazadaBillItemVO billitem = new LazadaBillItemVO();
			
			
			billitem.setItemid(order.getOid().toString());
			
//			if(product.getWallet_credits()!=null){
//				billitem.setWallet_credits(new BigDecimal(product.getWallet_credits()));
//			}
		
			billitem.setOrder_id(trade.getTid().toString());
			billitem.setOrder_item_id(order.getOid().toString());
			//billitem.setCurrency(product.getCurrency());
			if(order.getPrice()!=null){
				billitem.setItem_price(new BigDecimal(order.getPrice()));
			}
			
			billitem.setTracking_code(order.getInvoiceNo());
			billitem.setShipment_provider(order.getLogisticsCompany());
			billitem.setName(order.getTitle());
			if(order.getPartMjzDiscount()!=null){
				billitem.setVoucher_amount(new BigDecimal(order.getPartMjzDiscount()));
			}
			
			billitem.setOrderStatus(order.getStatus());
			
			if(order.getSubOrderTaxFee()!=null){
				billitem.setTax_amount(new BigDecimal(order.getSubOrderTaxFee()));
			}
			
			if(order.getPayment()!=null){
				billitem.setPaid_price(new BigDecimal(order.getPayment()));
			}
			
			
			Date created_at = trade.getCreated();
			billitem.setCreated_at(format.format(created_at));
			billitem.setSku(order.getSkuId());
			billitem.setPurchase_order_id(trade.getTid().toString());
			//billitem.setPackage_id(product.getPackage_id());
			billitem.setShipping_type(order.getShippingType());
			billitem.setShop_id(order.getEtShopName());
			
			if(order.getNumIid()!=null){
				billitem.setShop_sku(order.getNumIid().toString());
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
			//鏁伴噺 榛樿涓� add by weiningc 
			billitem.setQty(new java.math.BigDecimal(1.00));
			billItemList.add(billitem);
		}
		
		LazadaBillItemVO[] arr = (LazadaBillItemVO[])billItemList.toArray(new LazadaBillItemVO[billItemList.size()]);
		return arr;
			
	}

}
