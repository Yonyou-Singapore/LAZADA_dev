package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * Created by suzhaowen on 2018/11/1
 */
public class LazadaProductsInfo {

    private String reason; //取消，退回或者其他原因，在sales_order_reason表中定义
    private String digital_delivery_info; //可视化的配送信息
    private String promised_shipping_time; //承诺的运输时间
    private Long order_id; //订单编码
    private String voucher_amount; //发票金额
    private String return_status; //退回状态
    private String shipping_type; //运输类型，直接交运或者仓库取货
    private String shipment_provider; // 第三方物流服务供应商，如LEL
    private String variation ;//变量
    private String created_at; //ISO 8601格式下的创建时间
    private String invoice_number; //发票号
    private String shipping_amount; //运输费
    private String currency; //货币代码
    private String shop_id;//卖方名称
    private String sku;//产品库存单位
    private String voucher_code;//发票号码
    private String wallet_credits;//钱包信贷
    private String updated_at;//ISO 8601 format格式下最新更新时间
    private String is_digital;//是否为电子产品
    private String tracking_code_pre;//未使用
    private String order_item_id;//订单品项代码
    private String package_id;//包装来源代码
    private String tracking_code;//追踪代码
    private Integer shipping_service_cost ; //运输服务成本
    private String extra_attributes;//带有其他属性的JSON编码字符串
    private String paid_price;//支付价格
    private String shipping_provider_type;//包括如下选择：Express（快递）, Standard（标准）, or Economy（经济）
    private String product_detail_url;//产品细节URL链接
    private String shop_sku;//商店库存单位
    private String reason_detail;//详细原因
    private String purchase_order_id;//采购订单号码，当调用SetPackedByMarketPlace时被退回
    private String purchase_order_number;//采购订单数量，当调用SetPackedByMarketPlace时被退回
    private String name;//产品名称
    private String product_main_image;//产品主要照片链接
    private String item_price;//品项价格
    private String tax_amount;//税额
    private String status;//状态
    /**
     *
     * 指出是谁发起或取消的退回订单，可取的值包括
     * cancellation-internal（内部取消）,
     * cancellation-customer（顾客取消）,
     * cancellation-failed Delivery（配送失败取消）,
     * cancellation-seller（卖方取消）,
     * return-customer（顾客退回）,
     * and refund-internal（内部退款)
     */
    private String cancel_return_initiator;
    private String voucher_platform;//由Lazada开的发票
    private String voucher_seller;//由卖方开的发票

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

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
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
        return LazadaDateUtils.iso8601ToNormalNoT(created_at);
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

    public String getUpdated_at() {
        return LazadaDateUtils.iso8601ToNormalNoT(updated_at);
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getIs_digital() {
        return is_digital;
    }

    public void setIs_digital(String is_digital) {
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

    public Integer getShipping_service_cost() {
        return shipping_service_cost;
    }

    public void setShipping_service_cost(Integer shipping_service_cost) {
        this.shipping_service_cost = shipping_service_cost;
    }

    public String getExtra_attributes() {
        return extra_attributes;
    }

    public void setExtra_attributes(String extra_attributes) {
        this.extra_attributes = extra_attributes;
    }

    public String getPaid_price() {
        return paid_price;
    }

    public void setPaid_price(String paid_price) {
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

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCancel_return_initiator() {
        return cancel_return_initiator;
    }

    public void setCancel_return_initiator(String cancel_return_initiator) {
        this.cancel_return_initiator = cancel_return_initiator;
    }

    public String getVoucher_platform() {
        return voucher_platform;
    }

    public void setVoucher_platform(String voucher_platform) {
        this.voucher_platform = voucher_platform;
    }

    public String getVoucher_seller() {
        return voucher_seller;
    }

    public void setVoucher_seller(String voucher_seller) {
        this.voucher_seller = voucher_seller;
    }
}
