package nc.vo.so.restapi;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class LazadaItemVO extends SuperVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reason;
	private String digital_delivery_info; 
    private String promised_shipping_time; 
    private String order_id; 
    private String voucher_amount; 
    private String return_status;
    private String shipping_type; 
    private String shipment_provider; 
    private String variation;
    private String created_at; 
    private String invoice_number; 
    private String shipping_amount;
    private String currency; 
    private String shop_id; 
    private String sku;
    private String voucher_code; 
    private String wallet_credits; 
    private UFDate updated_at;
    private UFDouble is_digital; 
    private String tracking_code_pre;
    private String order_item_id; 
    private String package_id;
    private String tracking_code; 
    private UFDouble shipping_service_cost; 
    private String extra_attributes;
    private UFDouble paid_price; 
    private String shipping_provider_type; 
    private String product_detail_url; 
    private String shop_sku; 
    private String reason_detail; 
    private String purchase_order_id; 
    private String purchase_order_number; 
    private String name; 
    private String product_main_image;
    private UFDouble item_price; 
    private UFDouble tax_amount;
    private String order_status; 
    private String cancel_return_initiator; 
    private UFDouble voucher_platform; 
    private UFDouble voucher_seller; 
	
    public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDigital_delivery_info() {
		return digital_delivery_info;
	}

	public void setDigital_delivery_info(String digital_delivery_info) {
		this.digital_delivery_info = digital_delivery_info;
	}

	public String getPromised_shipping_time() {
		return promised_shipping_time;
	}

	public void setPromised_shipping_time(String promised_shipping_time) {
		this.promised_shipping_time = promised_shipping_time;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getVoucher_amount() {
		return voucher_amount;
	}

	public void setVoucher_amount(String voucher_amount) {
		this.voucher_amount = voucher_amount;
	}

	public String getReturn_status() {
		return return_status;
	}

	public void setReturn_status(String return_status) {
		this.return_status = return_status;
	}

	public String getShipping_type() {
		return shipping_type;
	}

	public void setShipping_type(String shipping_type) {
		this.shipping_type = shipping_type;
	}

	public String getShipment_provider() {
		return shipment_provider;
	}

	public void setShipment_provider(String shipment_provider) {
		this.shipment_provider = shipment_provider;
	}

	public String getVariation() {
		return variation;
	}

	public void setVariation(String variation) {
		this.variation = variation;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public String getShipping_amount() {
		return shipping_amount;
	}

	public void setShipping_amount(String shipping_amount) {
		this.shipping_amount = shipping_amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getVoucher_code() {
		return voucher_code;
	}

	public void setVoucher_code(String voucher_code) {
		this.voucher_code = voucher_code;
	}

	public String getWallet_credits() {
		return wallet_credits;
	}

	public void setWallet_credits(String wallet_credits) {
		this.wallet_credits = wallet_credits;
	}

	public UFDate getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(UFDate updated_at) {
		this.updated_at = updated_at;
	}

	public UFDouble getIs_digital() {
		return is_digital;
	}

	public void setIs_digital(UFDouble is_digital) {
		this.is_digital = is_digital;
	}

	public String getTracking_code_pre() {
		return tracking_code_pre;
	}

	public void setTracking_code_pre(String tracking_code_pre) {
		this.tracking_code_pre = tracking_code_pre;
	}

	public String getOrder_item_id() {
		return order_item_id;
	}

	public void setOrder_item_id(String order_item_id) {
		this.order_item_id = order_item_id;
	}

	public String getPackage_id() {
		return package_id;
	}

	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}

	public String getTracking_code() {
		return tracking_code;
	}

	public void setTracking_code(String tracking_code) {
		this.tracking_code = tracking_code;
	}

	public UFDouble getShipping_service_cost() {
		return shipping_service_cost;
	}

	public void setShipping_service_cost(UFDouble shipping_service_cost) {
		this.shipping_service_cost = shipping_service_cost;
	}

	public String getExtra_attributes() {
		return extra_attributes;
	}

	public void setExtra_attributes(String extra_attributes) {
		this.extra_attributes = extra_attributes;
	}

	public UFDouble getPaid_price() {
		return paid_price;
	}

	public void setPaid_price(UFDouble paid_price) {
		this.paid_price = paid_price;
	}

	public String getShipping_provider_type() {
		return shipping_provider_type;
	}

	public void setShipping_provider_type(String shipping_provider_type) {
		this.shipping_provider_type = shipping_provider_type;
	}

	public String getProduct_detail_url() {
		return product_detail_url;
	}

	public void setProduct_detail_url(String product_detail_url) {
		this.product_detail_url = product_detail_url;
	}

	public String getShop_sku() {
		return shop_sku;
	}

	public void setShop_sku(String shop_sku) {
		this.shop_sku = shop_sku;
	}

	public String getReason_detail() {
		return reason_detail;
	}

	public void setReason_detail(String reason_detail) {
		this.reason_detail = reason_detail;
	}

	public String getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(String purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	public String getPurchase_order_number() {
		return purchase_order_number;
	}

	public void setPurchase_order_number(String purchase_order_number) {
		this.purchase_order_number = purchase_order_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduct_main_image() {
		return product_main_image;
	}

	public void setProduct_main_image(String product_main_image) {
		this.product_main_image = product_main_image;
	}

	public UFDouble getItem_price() {
		return item_price;
	}

	public void setItem_price(UFDouble item_price) {
		this.item_price = item_price;
	}

	public UFDouble getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(UFDouble tax_amount) {
		this.tax_amount = tax_amount;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getCancel_return_initiator() {
		return cancel_return_initiator;
	}

	public void setCancel_return_initiator(String cancel_return_initiator) {
		this.cancel_return_initiator = cancel_return_initiator;
	}

	public UFDouble getVoucher_platform() {
		return voucher_platform;
	}

	public void setVoucher_platform(UFDouble voucher_platform) {
		this.voucher_platform = voucher_platform;
	}

	public UFDouble getVoucher_seller() {
		return voucher_seller;
	}

	public void setVoucher_seller(UFDouble voucher_seller) {
		this.voucher_seller = voucher_seller;
	}

	@Override
    public String getTableName() {
    	// TODO Auto-generated method stub
    	return super.getTableName();
    }
    
    @Override
    public String getPrimaryKey() {
    	// TODO Auto-generated method stub
    	return super.getPrimaryKey();
    }
    
}
