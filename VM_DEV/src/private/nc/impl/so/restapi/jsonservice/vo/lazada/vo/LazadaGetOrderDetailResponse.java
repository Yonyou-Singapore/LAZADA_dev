package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.util.List;

/**
 * Created by suzhaowen on 2018/10/31
 */
public class LazadaGetOrderDetailResponse {

    private String delivery_info; //配送订单信息
    private String gift_message; //根据客户特别注明的礼品信息
    private String branch_number; //(只适用于泰国)当客户下订单时，由客户提供的客户税务部门代码
    private String tax_code; //(只适用于泰国和越南)当客户下订单时，由客户提供的客户增值税代码
    private String extra_attributes; //在调用getMarketPlaceOrders时，分配给销售中心的其他参数
    private String shipping_fee; //运输费
    private String customer_first_name; //客户的姓
    private String payment_method; //支付方式
    private List<String> statuses; //订单中品项的某一状态
    private String remarks; //可读的备注
    private Long order_number; //订单数量
    private Long order_id; //销售中心分配的订单编码
    private String voucher; //发票金额
    private String national_registration_number; //某些国家规定必填的注册码
    private String promised_shipping_times; //承诺起运时间
    private Integer items_count; //订单中的品项数量
    private String created_at; //下订单的时间和日期
    private String price; //订单总金额
    private LazadaAddressBilling address_billing; //包含其他节点的节点，组成收货地的地址，包括：收货人姓，收货人名字，电话1，电话2，地址1，地址2，城市，邮编，国家
    private LazadaAddressShipping address_shipping; //包含其他节点的节点，组成收货地的地址，包括：收货人姓，收货人名字，电话1，电话2，地址1，地址2，城市，邮编，国家
    private String customer_last_name; //顾客的姓
    private Boolean gift_option; //如果该品项为礼品，值为1；反之，为0。
    private String voucher_code; //发票编码
    private String updated_at; //订单最新更新时间
    //订单商品行
    private List<LazadaProductsInfo> products;

    public List<LazadaProductsInfo> getProducts() {
        return products;
    }

    public void setProducts(List<LazadaProductsInfo> products) {
        this.products = products;
    }

    public String getDelivery_info() {
        return delivery_info;
    }

    public void setDelivery_info(String delivery_info) {
        this.delivery_info = delivery_info;
    }

    public String getGift_message() {
        return gift_message;
    }

    public void setGift_message(String gift_message) {
        this.gift_message = gift_message;
    }

    public String getBranch_number() {
        return branch_number;
    }

    public void setBranch_number(String branch_number) {
        this.branch_number = branch_number;
    }

    public String getTax_code() {
        return tax_code;
    }

    public void setTax_code(String tax_code) {
        this.tax_code = tax_code;
    }

    public String getExtra_attributes() {
        return extra_attributes;
    }

    public void setExtra_attributes(String extra_attributes) {
        this.extra_attributes = extra_attributes;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getCustomer_first_name() {
        return customer_first_name;
    }

    public void setCustomer_first_name(String customer_first_name) {
        this.customer_first_name = customer_first_name;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Long order_number) {
        this.order_number = order_number;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getNational_registration_number() {
        return national_registration_number;
    }

    public void setNational_registration_number(String national_registration_number) {
        this.national_registration_number = national_registration_number;
    }

    public String getPromised_shipping_times() {
        return promised_shipping_times;
    }

    public void setPromised_shipping_times(String promised_shipping_times) {
        this.promised_shipping_times = promised_shipping_times;
    }

    public Integer getItems_count() {
        return items_count;
    }

    public void setItems_count(Integer items_count) {
        this.items_count = items_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LazadaAddressBilling getAddress_billing() {
        return address_billing;
    }

    public void setAddress_billing(LazadaAddressBilling address_billing) {
        this.address_billing = address_billing;
    }

    public LazadaAddressShipping getAddress_shipping() {
        return address_shipping;
    }

    public void setAddress_shipping(LazadaAddressShipping address_shipping) {
        this.address_shipping = address_shipping;
    }

    public String getCustomer_last_name() {
        return customer_last_name;
    }

    public void setCustomer_last_name(String customer_last_name) {
        this.customer_last_name = customer_last_name;
    }

    public Boolean getGift_option() {
        return gift_option;
    }

    public void setGift_option(Boolean gift_option) {
        this.gift_option = gift_option;
    }

    public String getVoucher_code() {
        return voucher_code;
    }

    public void setVoucher_code(String voucher_code) {
        this.voucher_code = voucher_code;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
