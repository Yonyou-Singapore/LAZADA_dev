package nc.vo.so.component;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2019-6-6
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class So_ordercenter_b extends SuperVO {
	
/**
*������֯��������
*/
public String pk_ordercenter_b;
/**
*�к�
*/
public String rowno;
/**
*��������
*/
public String pk_group;
/**
*��֯
*/
public String pk_org;
/**
*��֯��汾
*/
public String pk_org_v;
/**
*paid_price
*/
public String paid_price;
/**
*product_main_image
*/
public String product_main_image;
/**
*tax_amount
*/
public String tax_amount;
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
public String voucher_seller;
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
public String item_price;
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
public String voucher_amount;
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
public String qty;
/**
*���̶�����
*/
public String order_number;
/**
*�ϲ㵥������
*/
public String pk_ordercenter;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_ordercenter_b��Getter����.��������������֯��������
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getPk_ordercenter_b() {
return this.pk_ordercenter_b;
} 

/**
* ����pk_ordercenter_b��Setter����.��������������֯��������
* ��������:2019-6-6
* @param newPk_ordercenter_b java.lang.String
*/
public void setPk_ordercenter_b ( String pk_ordercenter_b) {
this.pk_ordercenter_b=pk_ordercenter_b;
} 
 
/**
* ���� rowno��Getter����.���������к�
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getRowno() {
return this.rowno;
} 

/**
* ����rowno��Setter����.���������к�
* ��������:2019-6-6
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
} 
 
/**
* ���� pk_group��Getter����.����������������
*  ��������:2019-6-6
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.����������������
* ��������:2019-6-6
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2019-6-6
* @return nc.vo.org.SalesOrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2019-6-6
* @param newPk_org nc.vo.org.SalesOrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.����������֯��汾
*  ��������:2019-6-6
* @return nc.vo.vorg.SalesOrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.����������֯��汾
* ��������:2019-6-6
* @param newPk_org_v nc.vo.vorg.SalesOrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� paid_price��Getter����.��������paid_price
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getPaid_price() {
return this.paid_price;
} 

/**
* ����paid_price��Setter����.��������paid_price
* ��������:2019-6-6
* @param newPaid_price nc.vo.pub.lang.UFDouble
*/
public void setPaid_price ( String paid_price) {
this.paid_price=paid_price;
} 
 
/**
* ���� product_main_image��Getter����.��������product_main_image
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getProduct_main_image() {
return this.product_main_image;
} 

/**
* ����product_main_image��Setter����.��������product_main_image
* ��������:2019-6-6
* @param newProduct_main_image java.lang.String
*/
public void setProduct_main_image ( String product_main_image) {
this.product_main_image=product_main_image;
} 
 
/**
* ���� tax_amount��Getter����.��������tax_amount
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getTax_amount() {
return this.tax_amount;
} 

/**
* ����tax_amount��Setter����.��������tax_amount
* ��������:2019-6-6
* @param newTax_amount nc.vo.pub.lang.UFDouble
*/
public void setTax_amount ( String tax_amount) {
this.tax_amount=tax_amount;
} 
 
/**
* ���� voucher_platform��Getter����.��������voucher_platform
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getVoucher_platform() {
return this.voucher_platform;
} 

/**
* ����voucher_platform��Setter����.��������voucher_platform
* ��������:2019-6-6
* @param newVoucher_platform nc.vo.pub.lang.UFDouble
*/
public void setVoucher_platform ( String voucher_platform) {
this.voucher_platform=voucher_platform;
} 
 
/**
* ���� reason��Getter����.��������reason
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getReason() {
return this.reason;
} 

/**
* ����reason��Setter����.��������reason
* ��������:2019-6-6
* @param newReason java.lang.String
*/
public void setReason ( String reason) {
this.reason=reason;
} 
 
/**
* ���� product_detail_url��Getter����.��������product_detail_url
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getProduct_detail_url() {
return this.product_detail_url;
} 

/**
* ����product_detail_url��Setter����.��������product_detail_url
* ��������:2019-6-6
* @param newProduct_detail_url java.lang.String
*/
public void setProduct_detail_url ( String product_detail_url) {
this.product_detail_url=product_detail_url;
} 
 
/**
* ���� purchase_order_id��Getter����.��������purchase_order_id
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getPurchase_order_id() {
return this.purchase_order_id;
} 

/**
* ����purchase_order_id��Setter����.��������purchase_order_id
* ��������:2019-6-6
* @param newPurchase_order_id java.lang.String
*/
public void setPurchase_order_id ( String purchase_order_id) {
this.purchase_order_id=purchase_order_id;
} 
 
/**
* ���� voucher_seller��Getter����.��������voucher_seller
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getVoucher_seller() {
return this.voucher_seller;
} 

/**
* ����voucher_seller��Setter����.��������voucher_seller
* ��������:2019-6-6
* @param newVoucher_seller nc.vo.pub.lang.UFDouble
*/
public void setVoucher_seller ( String voucher_seller) {
this.voucher_seller=voucher_seller;
} 
 
/**
* ���� shipping_type��Getter����.��������shipping_type
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_type() {
return this.shipping_type;
} 

/**
* ����shipping_type��Setter����.��������shipping_type
* ��������:2019-6-6
* @param newShipping_type java.lang.String
*/
public void setShipping_type ( String shipping_type) {
this.shipping_type=shipping_type;
} 
 
/**
* ���� created_at��Getter����.��������created_at
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getCreated_at() {
return this.created_at;
} 

/**
* ����created_at��Setter����.��������created_at
* ��������:2019-6-6
* @param newCreated_at java.lang.String
*/
public void setCreated_at ( String created_at) {
this.created_at=created_at;
} 
 
/**
* ���� voucher_code��Getter����.��������voucher_code
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getVoucher_code() {
return this.voucher_code;
} 

/**
* ����voucher_code��Setter����.��������voucher_code
* ��������:2019-6-6
* @param newVoucher_code java.lang.String
*/
public void setVoucher_code ( String voucher_code) {
this.voucher_code=voucher_code;
} 
 
/**
* ���� package_id��Getter����.��������package_id
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getPackage_id() {
return this.package_id;
} 

/**
* ����package_id��Setter����.��������package_id
* ��������:2019-6-6
* @param newPackage_id java.lang.String
*/
public void setPackage_id ( String package_id) {
this.package_id=package_id;
} 
 
/**
* ���� variation��Getter����.��������variation
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getVariation() {
return this.variation;
} 

/**
* ����variation��Setter����.��������variation
* ��������:2019-6-6
* @param newVariation java.lang.String
*/
public void setVariation ( String variation) {
this.variation=variation;
} 
 
/**
* ���� wallet_credits��Getter����.��������wallet_credits
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getWallet_credits() {
return this.wallet_credits;
} 

/**
* ����wallet_credits��Setter����.��������wallet_credits
* ��������:2019-6-6
* @param newWallet_credits nc.vo.pub.lang.UFDouble
*/
public void setWallet_credits ( String wallet_credits) {
this.wallet_credits=wallet_credits;
} 
 
/**
* ���� updated_at��Getter����.��������updated_at
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getUpdated_at() {
return this.updated_at;
} 

/**
* ����updated_at��Setter����.��������updated_at
* ��������:2019-6-6
* @param newUpdated_at nc.vo.pub.lang.UFDate
*/
public void setUpdated_at ( UFDate updated_at) {
this.updated_at=updated_at;
} 
 
/**
* ���� purchase_order_number��Getter����.��������purchase_order_number
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getPurchase_order_number() {
return this.purchase_order_number;
} 

/**
* ����purchase_order_number��Setter����.��������purchase_order_number
* ��������:2019-6-6
* @param newPurchase_order_number java.lang.String
*/
public void setPurchase_order_number ( String purchase_order_number) {
this.purchase_order_number=purchase_order_number;
} 
 
/**
* ���� currency��Getter����.��������currency
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getCurrency() {
return this.currency;
} 

/**
* ����currency��Setter����.��������currency
* ��������:2019-6-6
* @param newCurrency java.lang.String
*/
public void setCurrency ( String currency) {
this.currency=currency;
} 
 
/**
* ���� shipping_provider_type��Getter����.��������shipping_provider_type
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_provider_type() {
return this.shipping_provider_type;
} 

/**
* ����shipping_provider_type��Setter����.��������shipping_provider_type
* ��������:2019-6-6
* @param newShipping_provider_type java.lang.String
*/
public void setShipping_provider_type ( String shipping_provider_type) {
this.shipping_provider_type=shipping_provider_type;
} 
 
/**
* ���� sku��Getter����.��������sku
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getSku() {
return this.sku;
} 

/**
* ����sku��Setter����.��������sku
* ��������:2019-6-6
* @param newSku java.lang.String
*/
public void setSku ( String sku) {
this.sku=sku;
} 
 
/**
* ���� invoice_number��Getter����.��������invoice_number
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getInvoice_number() {
return this.invoice_number;
} 

/**
* ����invoice_number��Setter����.��������invoice_number
* ��������:2019-6-6
* @param newInvoice_number java.lang.String
*/
public void setInvoice_number ( String invoice_number) {
this.invoice_number=invoice_number;
} 
 
/**
* ���� cancel_return_initiator��Getter����.��������cancel_return_initiator
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getCancel_return_initiator() {
return this.cancel_return_initiator;
} 

/**
* ����cancel_return_initiator��Setter����.��������cancel_return_initiator
* ��������:2019-6-6
* @param newCancel_return_initiator java.lang.String
*/
public void setCancel_return_initiator ( String cancel_return_initiator) {
this.cancel_return_initiator=cancel_return_initiator;
} 
 
/**
* ���� shop_sku��Getter����.��������shop_sku
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShop_sku() {
return this.shop_sku;
} 

/**
* ����shop_sku��Setter����.��������shop_sku
* ��������:2019-6-6
* @param newShop_sku java.lang.String
*/
public void setShop_sku ( String shop_sku) {
this.shop_sku=shop_sku;
} 
 
/**
* ���� is_digital��Getter����.��������is_digital
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getIs_digital() {
return this.is_digital;
} 

/**
* ����is_digital��Setter����.��������is_digital
* ��������:2019-6-6
* @param newIs_digital java.lang.String
*/
public void setIs_digital ( String is_digital) {
this.is_digital=is_digital;
} 
 
/**
* ���� item_price��Getter����.��������item_price
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getItem_price() {
return this.item_price;
} 

/**
* ����item_price��Setter����.��������item_price
* ��������:2019-6-6
* @param newItem_price nc.vo.pub.lang.UFDouble
*/
public void setItem_price ( String item_price) {
this.item_price=item_price;
} 
 
/**
* ���� shipping_service_cost��Getter����.��������shipping_service_cost
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_service_cost() {
return this.shipping_service_cost;
} 

/**
* ����shipping_service_cost��Setter����.��������shipping_service_cost
* ��������:2019-6-6
* @param newShipping_service_cost java.lang.String
*/
public void setShipping_service_cost ( String shipping_service_cost) {
this.shipping_service_cost=shipping_service_cost;
} 
 
/**
* ���� tracking_code_pre��Getter����.��������tracking_code_pre
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getTracking_code_pre() {
return this.tracking_code_pre;
} 

/**
* ����tracking_code_pre��Setter����.��������tracking_code_pre
* ��������:2019-6-6
* @param newTracking_code_pre java.lang.String
*/
public void setTracking_code_pre ( String tracking_code_pre) {
this.tracking_code_pre=tracking_code_pre;
} 
 
/**
* ���� tracking_code��Getter����.��������tracking_code
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getTracking_code() {
return this.tracking_code;
} 

/**
* ����tracking_code��Setter����.��������tracking_code
* ��������:2019-6-6
* @param newTracking_code java.lang.String
*/
public void setTracking_code ( String tracking_code) {
this.tracking_code=tracking_code;
} 
 
/**
* ���� shipping_amount��Getter����.��������shipping_amount
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getShipping_amount() {
return this.shipping_amount;
} 

/**
* ����shipping_amount��Setter����.��������shipping_amount
* ��������:2019-6-6
* @param newShipping_amount nc.vo.pub.lang.UFDouble
*/
public void setShipping_amount ( String shipping_amount) {
this.shipping_amount=shipping_amount;
} 
 
/**
* ���� reason_detail��Getter����.��������reason_detail
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getReason_detail() {
return this.reason_detail;
} 

/**
* ����reason_detail��Setter����.��������reason_detail
* ��������:2019-6-6
* @param newReason_detail java.lang.String
*/
public void setReason_detail ( String reason_detail) {
this.reason_detail=reason_detail;
} 
 
/**
* ���� shop_id��Getter����.��������shop_id
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShop_id() {
return this.shop_id;
} 

/**
* ����shop_id��Setter����.��������shop_id
* ��������:2019-6-6
* @param newShop_id java.lang.String
*/
public void setShop_id ( String shop_id) {
this.shop_id=shop_id;
} 
 
/**
* ���� return_status��Getter����.��������return_status
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getReturn_status() {
return this.return_status;
} 

/**
* ����return_status��Setter����.��������return_status
* ��������:2019-6-6
* @param newReturn_status java.lang.String
*/
public void setReturn_status ( String return_status) {
this.return_status=return_status;
} 
 
/**
* ���� name��Getter����.��������name
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* ����name��Setter����.��������name
* ��������:2019-6-6
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* ���� shipment_provider��Getter����.��������shipment_provider
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipment_provider() {
return this.shipment_provider;
} 

/**
* ����shipment_provider��Setter����.��������shipment_provider
* ��������:2019-6-6
* @param newShipment_provider java.lang.String
*/
public void setShipment_provider ( String shipment_provider) {
this.shipment_provider=shipment_provider;
} 
 
/**
* ���� voucher_amount��Getter����.��������voucher_amount
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getVoucher_amount() {
return this.voucher_amount;
} 

/**
* ����voucher_amount��Setter����.��������voucher_amount
* ��������:2019-6-6
* @param newVoucher_amount nc.vo.pub.lang.UFDouble
*/
public void setVoucher_amount ( String voucher_amount) {
this.voucher_amount=voucher_amount;
} 
 
/**
* ���� digital_delivery_info��Getter����.��������digital_delivery_info
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getDigital_delivery_info() {
return this.digital_delivery_info;
} 

/**
* ����digital_delivery_info��Setter����.��������digital_delivery_info
* ��������:2019-6-6
* @param newDigital_delivery_info java.lang.String
*/
public void setDigital_delivery_info ( String digital_delivery_info) {
this.digital_delivery_info=digital_delivery_info;
} 
 
/**
* ���� extra_attributes��Getter����.��������extra_attributes
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getExtra_attributes() {
return this.extra_attributes;
} 

/**
* ����extra_attributes��Setter����.��������extra_attributes
* ��������:2019-6-6
* @param newExtra_attributes java.lang.String
*/
public void setExtra_attributes ( String extra_attributes) {
this.extra_attributes=extra_attributes;
} 
 
/**
* ���� order_id��Getter����.��������order_id
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getOrder_id() {
return this.order_id;
} 

/**
* ����order_id��Setter����.��������order_id
* ��������:2019-6-6
* @param newOrder_id java.lang.String
*/
public void setOrder_id ( String order_id) {
this.order_id=order_id;
} 
 
/**
* ���� orderstatus��Getter����.��������orderstatus
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getOrderstatus() {
return this.orderstatus;
} 

/**
* ����orderstatus��Setter����.��������orderstatus
* ��������:2019-6-6
* @param newOrderstatus java.lang.String
*/
public void setOrderstatus ( String orderstatus) {
this.orderstatus=orderstatus;
} 
 
/**
* ���� qty��Getter����.��������attrDisplayName14
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getQty() {
return this.qty;
} 

/**
* ����qty��Setter����.��������attrDisplayName14
* ��������:2019-6-6
* @param newQty nc.vo.pub.lang.UFDouble
*/
public void setQty ( String qty) {
this.qty=qty;
} 
 
/**
* ���� order_number��Getter����.�����������̶�����
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getOrder_number() {
return this.order_number;
} 

/**
* ����order_number��Setter����.�����������̶�����
* ��������:2019-6-6
* @param newOrder_number java.lang.String
*/
public void setOrder_number ( String order_number) {
this.order_number=order_number;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2019-6-6
* @return String
*/
public String getPk_ordercenter(){
return this.pk_ordercenter;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2019-6-6
* @param newPk_ordercenter String
*/
public void setPk_ordercenter(String pk_ordercenter){
this.pk_ordercenter=pk_ordercenter;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2019-6-6
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("so.so_ordercenter_b");
    }
   }
    