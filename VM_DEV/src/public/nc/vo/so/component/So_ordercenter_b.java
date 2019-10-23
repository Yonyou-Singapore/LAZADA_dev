package nc.vo.so.component;

import java.io.Serializable;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2019-6-6
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class So_ordercenter_b extends SuperVO implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3106218076486684541L;
/**
*销售组织表体主键
*/
public String pk_ordercenter_b;
/**
*行号
*/
public String rowno;
/**
*所属集团
*/
public String pk_group;
/**
*组织
*/
public String pk_org;
/**
*组织多版本
*/
public String pk_org_v;
/**
*paid_price
*/
public UFDouble paid_price;
/**
*product_main_image
*/
public String product_main_image;
/**
*tax_amount
*/
public UFDouble tax_amount;
/**
*voucher_platform
*/
public String voucher_platform;
/**
*reason
*/
public String reason;
/**
*product_detail_url
*/
public String product_detail_url;
/**
*purchase_order_id
*/
public String purchase_order_id;
/**
*voucher_seller
*/
public UFDouble voucher_seller;
/**
*shipping_type
*/
public String shipping_type;
/**
*created_at
*/
public String created_at;
/**
*voucher_code
*/
public String voucher_code;
/**
*package_id
*/
public String package_id;
/**
*variation
*/
public String variation;
/**
*wallet_credits
*/
public String wallet_credits;
/**
*updated_at
*/
public UFDate updated_at;
/**
*purchase_order_number
*/
public String purchase_order_number;
/**
*currency
*/
public String currency;
/**
*shipping_provider_type
*/
public String shipping_provider_type;
/**
*sku
*/
public String sku;
/**
*invoice_number
*/
public String invoice_number;
/**
*cancel_return_initiator
*/
public String cancel_return_initiator;
/**
*shop_sku
*/
public String shop_sku;
/**
*is_digital
*/
public String is_digital;
/**
*item_price
*/
public UFDouble item_price;
/**
*shipping_service_cost
*/
public String shipping_service_cost;
/**
*tracking_code_pre
*/
public String tracking_code_pre;
/**
*tracking_code
*/
public String tracking_code;
/**
*shipping_amount
*/
public String shipping_amount;
/**
*reason_detail
*/
public String reason_detail;
/**
*shop_id
*/
public String shop_id;
/**
*return_status
*/
public String return_status;
/**
*name
*/
public String name;
/**
*shipment_provider
*/
public String shipment_provider;
/**
*voucher_amount
*/
public UFDouble voucher_amount;
/**
*digital_delivery_info
*/
public String digital_delivery_info;
/**
*extra_attributes
*/
public String extra_attributes;
/**
*order_id
*/
public String order_id;
/**
*orderstatus
*/
public String orderstatus;
/**
*attrDisplayName14
*/
public UFDouble qty;
/**
*电商订单号
*/
public String order_number;
/**
*上层单据主键
*/
public String pk_ordercenter;
/**
*时间戳
*/
public UFDateTime ts;
    
public String order_item_id;

//发货仓库
public String csendstordocid;
//发货库存组织
public String csendstockorgvid;
//发货库存组织最新版本
public String csendstockorgid;
/**
* 属性 pk_ordercenter_b的Getter方法.属性名：销售组织表体主键
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getPk_ordercenter_b() {
return this.pk_ordercenter_b;
} 

/**
* 属性pk_ordercenter_b的Setter方法.属性名：销售组织表体主键
* 创建日期:2019-6-6
* @param newPk_ordercenter_b java.lang.String
*/
public void setPk_ordercenter_b ( String pk_ordercenter_b) {
this.pk_ordercenter_b=pk_ordercenter_b;
} 
 
/**
* 属性 rowno的Getter方法.属性名：行号
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getRowno() {
return this.rowno;
} 

/**
* 属性rowno的Setter方法.属性名：行号
* 创建日期:2019-6-6
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：所属集团
*  创建日期:2019-6-6
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：所属集团
* 创建日期:2019-6-6
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2019-6-6
* @return nc.vo.org.SalesOrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2019-6-6
* @param newPk_org nc.vo.org.SalesOrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：组织多版本
*  创建日期:2019-6-6
* @return nc.vo.vorg.SalesOrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：组织多版本
* 创建日期:2019-6-6
* @param newPk_org_v nc.vo.vorg.SalesOrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 paid_price的Getter方法.属性名：paid_price
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getPaid_price() {
return this.paid_price;
} 

/**
* 属性paid_price的Setter方法.属性名：paid_price
* 创建日期:2019-6-6
* @param newPaid_price nc.vo.pub.lang.UFDouble
*/
public void setPaid_price ( UFDouble paid_price) {
this.paid_price=paid_price;
} 
 
/**
* 属性 product_main_image的Getter方法.属性名：product_main_image
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getProduct_main_image() {
return this.product_main_image;
} 

/**
* 属性product_main_image的Setter方法.属性名：product_main_image
* 创建日期:2019-6-6
* @param newProduct_main_image java.lang.String
*/
public void setProduct_main_image ( String product_main_image) {
this.product_main_image=product_main_image;
} 
 
/**
* 属性 tax_amount的Getter方法.属性名：tax_amount
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTax_amount() {
return this.tax_amount;
} 

/**
* 属性tax_amount的Setter方法.属性名：tax_amount
* 创建日期:2019-6-6
* @param newTax_amount nc.vo.pub.lang.UFDouble
*/
public void setTax_amount ( UFDouble tax_amount) {
this.tax_amount=tax_amount;
} 
 
/**
* 属性 voucher_platform的Getter方法.属性名：voucher_platform
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getVoucher_platform() {
return this.voucher_platform;
} 

/**
* 属性voucher_platform的Setter方法.属性名：voucher_platform
* 创建日期:2019-6-6
* @param newVoucher_platform nc.vo.pub.lang.UFDouble
*/
public void setVoucher_platform ( String voucher_platform) {
this.voucher_platform=voucher_platform;
} 
 
/**
* 属性 reason的Getter方法.属性名：reason
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getReason() {
return this.reason;
} 

/**
* 属性reason的Setter方法.属性名：reason
* 创建日期:2019-6-6
* @param newReason java.lang.String
*/
public void setReason ( String reason) {
this.reason=reason;
} 
 
/**
* 属性 product_detail_url的Getter方法.属性名：product_detail_url
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getProduct_detail_url() {
return this.product_detail_url;
} 

/**
* 属性product_detail_url的Setter方法.属性名：product_detail_url
* 创建日期:2019-6-6
* @param newProduct_detail_url java.lang.String
*/
public void setProduct_detail_url ( String product_detail_url) {
this.product_detail_url=product_detail_url;
} 
 
/**
* 属性 purchase_order_id的Getter方法.属性名：purchase_order_id
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getPurchase_order_id() {
return this.purchase_order_id;
} 

/**
* 属性purchase_order_id的Setter方法.属性名：purchase_order_id
* 创建日期:2019-6-6
* @param newPurchase_order_id java.lang.String
*/
public void setPurchase_order_id ( String purchase_order_id) {
this.purchase_order_id=purchase_order_id;
} 
 
/**
* 属性 voucher_seller的Getter方法.属性名：voucher_seller
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVoucher_seller() {
return this.voucher_seller;
} 

/**
* 属性voucher_seller的Setter方法.属性名：voucher_seller
* 创建日期:2019-6-6
* @param newVoucher_seller nc.vo.pub.lang.UFDouble
*/
public void setVoucher_seller ( UFDouble voucher_seller) {
this.voucher_seller=voucher_seller;
} 
 
/**
* 属性 shipping_type的Getter方法.属性名：shipping_type
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_type() {
return this.shipping_type;
} 

/**
* 属性shipping_type的Setter方法.属性名：shipping_type
* 创建日期:2019-6-6
* @param newShipping_type java.lang.String
*/
public void setShipping_type ( String shipping_type) {
this.shipping_type=shipping_type;
} 
 
/**
* 属性 created_at的Getter方法.属性名：created_at
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getCreated_at() {
return this.created_at;
} 

/**
* 属性created_at的Setter方法.属性名：created_at
* 创建日期:2019-6-6
* @param newCreated_at java.lang.String
*/
public void setCreated_at ( String created_at) {
this.created_at=created_at;
} 
 
/**
* 属性 voucher_code的Getter方法.属性名：voucher_code
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getVoucher_code() {
return this.voucher_code;
} 

/**
* 属性voucher_code的Setter方法.属性名：voucher_code
* 创建日期:2019-6-6
* @param newVoucher_code java.lang.String
*/
public void setVoucher_code ( String voucher_code) {
this.voucher_code=voucher_code;
} 
 
/**
* 属性 package_id的Getter方法.属性名：package_id
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getPackage_id() {
return this.package_id;
} 

/**
* 属性package_id的Setter方法.属性名：package_id
* 创建日期:2019-6-6
* @param newPackage_id java.lang.String
*/
public void setPackage_id ( String package_id) {
this.package_id=package_id;
} 
 
/**
* 属性 variation的Getter方法.属性名：variation
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getVariation() {
return this.variation;
} 

/**
* 属性variation的Setter方法.属性名：variation
* 创建日期:2019-6-6
* @param newVariation java.lang.String
*/
public void setVariation ( String variation) {
this.variation=variation;
} 
 
/**
* 属性 wallet_credits的Getter方法.属性名：wallet_credits
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getWallet_credits() {
return this.wallet_credits;
} 

/**
* 属性wallet_credits的Setter方法.属性名：wallet_credits
* 创建日期:2019-6-6
* @param newWallet_credits nc.vo.pub.lang.UFDouble
*/
public void setWallet_credits ( String wallet_credits) {
this.wallet_credits=wallet_credits;
} 
 
/**
* 属性 updated_at的Getter方法.属性名：updated_at
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getUpdated_at() {
return this.updated_at;
} 

/**
* 属性updated_at的Setter方法.属性名：updated_at
* 创建日期:2019-6-6
* @param newUpdated_at nc.vo.pub.lang.UFDate
*/
public void setUpdated_at ( UFDate updated_at) {
this.updated_at=updated_at;
} 
 
/**
* 属性 purchase_order_number的Getter方法.属性名：purchase_order_number
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getPurchase_order_number() {
return this.purchase_order_number;
} 

/**
* 属性purchase_order_number的Setter方法.属性名：purchase_order_number
* 创建日期:2019-6-6
* @param newPurchase_order_number java.lang.String
*/
public void setPurchase_order_number ( String purchase_order_number) {
this.purchase_order_number=purchase_order_number;
} 
 
/**
* 属性 currency的Getter方法.属性名：currency
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getCurrency() {
return this.currency;
} 

/**
* 属性currency的Setter方法.属性名：currency
* 创建日期:2019-6-6
* @param newCurrency java.lang.String
*/
public void setCurrency ( String currency) {
this.currency=currency;
} 
 
/**
* 属性 shipping_provider_type的Getter方法.属性名：shipping_provider_type
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_provider_type() {
return this.shipping_provider_type;
} 

/**
* 属性shipping_provider_type的Setter方法.属性名：shipping_provider_type
* 创建日期:2019-6-6
* @param newShipping_provider_type java.lang.String
*/
public void setShipping_provider_type ( String shipping_provider_type) {
this.shipping_provider_type=shipping_provider_type;
} 
 
/**
* 属性 sku的Getter方法.属性名：sku
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getSku() {
return this.sku;
} 

/**
* 属性sku的Setter方法.属性名：sku
* 创建日期:2019-6-6
* @param newSku java.lang.String
*/
public void setSku ( String sku) {
this.sku=sku;
} 
 
/**
* 属性 invoice_number的Getter方法.属性名：invoice_number
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getInvoice_number() {
return this.invoice_number;
} 

/**
* 属性invoice_number的Setter方法.属性名：invoice_number
* 创建日期:2019-6-6
* @param newInvoice_number java.lang.String
*/
public void setInvoice_number ( String invoice_number) {
this.invoice_number=invoice_number;
} 
 
/**
* 属性 cancel_return_initiator的Getter方法.属性名：cancel_return_initiator
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getCancel_return_initiator() {
return this.cancel_return_initiator;
} 

/**
* 属性cancel_return_initiator的Setter方法.属性名：cancel_return_initiator
* 创建日期:2019-6-6
* @param newCancel_return_initiator java.lang.String
*/
public void setCancel_return_initiator ( String cancel_return_initiator) {
this.cancel_return_initiator=cancel_return_initiator;
} 
 
/**
* 属性 shop_sku的Getter方法.属性名：shop_sku
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShop_sku() {
return this.shop_sku;
} 

/**
* 属性shop_sku的Setter方法.属性名：shop_sku
* 创建日期:2019-6-6
* @param newShop_sku java.lang.String
*/
public void setShop_sku ( String shop_sku) {
this.shop_sku=shop_sku;
} 
 
/**
* 属性 is_digital的Getter方法.属性名：is_digital
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getIs_digital() {
return this.is_digital;
} 

/**
* 属性is_digital的Setter方法.属性名：is_digital
* 创建日期:2019-6-6
* @param newIs_digital java.lang.String
*/
public void setIs_digital ( String is_digital) {
this.is_digital=is_digital;
} 
 
/**
* 属性 item_price的Getter方法.属性名：item_price
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getItem_price() {
return this.item_price;
} 

/**
* 属性item_price的Setter方法.属性名：item_price
* 创建日期:2019-6-6
* @param newItem_price nc.vo.pub.lang.UFDouble
*/
public void setItem_price ( UFDouble item_price) {
this.item_price=item_price;
} 
 
/**
* 属性 shipping_service_cost的Getter方法.属性名：shipping_service_cost
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_service_cost() {
return this.shipping_service_cost;
} 

/**
* 属性shipping_service_cost的Setter方法.属性名：shipping_service_cost
* 创建日期:2019-6-6
* @param newShipping_service_cost java.lang.String
*/
public void setShipping_service_cost ( String shipping_service_cost) {
this.shipping_service_cost=shipping_service_cost;
} 
 
/**
* 属性 tracking_code_pre的Getter方法.属性名：tracking_code_pre
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getTracking_code_pre() {
return this.tracking_code_pre;
} 

/**
* 属性tracking_code_pre的Setter方法.属性名：tracking_code_pre
* 创建日期:2019-6-6
* @param newTracking_code_pre java.lang.String
*/
public void setTracking_code_pre ( String tracking_code_pre) {
this.tracking_code_pre=tracking_code_pre;
} 
 
/**
* 属性 tracking_code的Getter方法.属性名：tracking_code
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getTracking_code() {
return this.tracking_code;
} 

/**
* 属性tracking_code的Setter方法.属性名：tracking_code
* 创建日期:2019-6-6
* @param newTracking_code java.lang.String
*/
public void setTracking_code ( String tracking_code) {
this.tracking_code=tracking_code;
} 
 
/**
* 属性 shipping_amount的Getter方法.属性名：shipping_amount
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getShipping_amount() {
return this.shipping_amount;
} 

/**
* 属性shipping_amount的Setter方法.属性名：shipping_amount
* 创建日期:2019-6-6
* @param newShipping_amount nc.vo.pub.lang.UFDouble
*/
public void setShipping_amount ( String shipping_amount) {
this.shipping_amount=shipping_amount;
} 
 
/**
* 属性 reason_detail的Getter方法.属性名：reason_detail
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getReason_detail() {
return this.reason_detail;
} 

/**
* 属性reason_detail的Setter方法.属性名：reason_detail
* 创建日期:2019-6-6
* @param newReason_detail java.lang.String
*/
public void setReason_detail ( String reason_detail) {
this.reason_detail=reason_detail;
} 
 
/**
* 属性 shop_id的Getter方法.属性名：shop_id
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShop_id() {
return this.shop_id;
} 

/**
* 属性shop_id的Setter方法.属性名：shop_id
* 创建日期:2019-6-6
* @param newShop_id java.lang.String
*/
public void setShop_id ( String shop_id) {
this.shop_id=shop_id;
} 
 
/**
* 属性 return_status的Getter方法.属性名：return_status
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getReturn_status() {
return this.return_status;
} 

/**
* 属性return_status的Setter方法.属性名：return_status
* 创建日期:2019-6-6
* @param newReturn_status java.lang.String
*/
public void setReturn_status ( String return_status) {
this.return_status=return_status;
} 
 
/**
* 属性 name的Getter方法.属性名：name
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* 属性name的Setter方法.属性名：name
* 创建日期:2019-6-6
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* 属性 shipment_provider的Getter方法.属性名：shipment_provider
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipment_provider() {
return this.shipment_provider;
} 

/**
* 属性shipment_provider的Setter方法.属性名：shipment_provider
* 创建日期:2019-6-6
* @param newShipment_provider java.lang.String
*/
public void setShipment_provider ( String shipment_provider) {
this.shipment_provider=shipment_provider;
} 
 
/**
* 属性 voucher_amount的Getter方法.属性名：voucher_amount
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVoucher_amount() {
return this.voucher_amount;
} 

/**
* 属性voucher_amount的Setter方法.属性名：voucher_amount
* 创建日期:2019-6-6
* @param newVoucher_amount nc.vo.pub.lang.UFDouble
*/
public void setVoucher_amount ( UFDouble voucher_amount) {
this.voucher_amount=voucher_amount;
} 
 
/**
* 属性 digital_delivery_info的Getter方法.属性名：digital_delivery_info
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getDigital_delivery_info() {
return this.digital_delivery_info;
} 

/**
* 属性digital_delivery_info的Setter方法.属性名：digital_delivery_info
* 创建日期:2019-6-6
* @param newDigital_delivery_info java.lang.String
*/
public void setDigital_delivery_info ( String digital_delivery_info) {
this.digital_delivery_info=digital_delivery_info;
} 
 
/**
* 属性 extra_attributes的Getter方法.属性名：extra_attributes
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getExtra_attributes() {
return this.extra_attributes;
} 

/**
* 属性extra_attributes的Setter方法.属性名：extra_attributes
* 创建日期:2019-6-6
* @param newExtra_attributes java.lang.String
*/
public void setExtra_attributes ( String extra_attributes) {
this.extra_attributes=extra_attributes;
} 
 
/**
* 属性 order_id的Getter方法.属性名：order_id
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getOrder_id() {
return this.order_id;
} 

/**
* 属性order_id的Setter方法.属性名：order_id
* 创建日期:2019-6-6
* @param newOrder_id java.lang.String
*/
public void setOrder_id ( String order_id) {
this.order_id=order_id;
} 
 
/**
* 属性 orderstatus的Getter方法.属性名：orderstatus
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getOrderstatus() {
return this.orderstatus;
} 

/**
* 属性orderstatus的Setter方法.属性名：orderstatus
* 创建日期:2019-6-6
* @param newOrderstatus java.lang.String
*/
public void setOrderstatus ( String orderstatus) {
this.orderstatus=orderstatus;
} 
 
/**
* 属性 qty的Getter方法.属性名：attrDisplayName14
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getQty() {
return this.qty;
} 

/**
* 属性qty的Setter方法.属性名：attrDisplayName14
* 创建日期:2019-6-6
* @param newQty nc.vo.pub.lang.UFDouble
*/
public void setQty ( UFDouble qty) {
this.qty=qty;
} 
 
/**
* 属性 order_number的Getter方法.属性名：电商订单号
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getOrder_number() {
return this.order_number;
} 

/**
* 属性order_number的Setter方法.属性名：电商订单号
* 创建日期:2019-6-6
* @param newOrder_number java.lang.String
*/
public void setOrder_number ( String order_number) {
this.order_number=order_number;
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2019-6-6
* @return String
*/
public String getPk_ordercenter(){
return this.pk_ordercenter;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2019-6-6
* @param newPk_ordercenter String
*/
public void setPk_ordercenter(String pk_ordercenter){
this.pk_ordercenter=pk_ordercenter;
} 


public String getCsendstordocid() {
	return csendstordocid;
}

public void setCsendstordocid(String csendstordocid) {
	this.csendstordocid = csendstordocid;
}

public String getCsendstockorgvid() {
	return csendstockorgvid;
}

public void setCsendstockorgvid(String csendstockorgvid) {
	this.csendstockorgvid = csendstockorgvid;
}

public String getCsendstockorgid() {
	return csendstockorgid;
}

public void setCsendstockorgid(String csendstockorgid) {
	this.csendstockorgid = csendstockorgid;
}

/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2019-6-6
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;


} 
     
    public String getOrder_item_id() {
	return order_item_id;
}

public void setOrder_item_id(String order_item_id) {
	this.order_item_id = order_item_id;
}

	@Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("so.so_ordercenter_b");
    }
   }
    