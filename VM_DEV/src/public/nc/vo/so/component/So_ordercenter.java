package nc.vo.so.component;

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
 
public class So_ordercenter extends SuperVO {
	
/**
*销售组织主键
*/
public String pk_ordercenter;
/**
*集团
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
*单据号
*/
public String billno;
/**
*单据名称
*/
public String billname;
/**
*单据日期
*/
public UFDate billdate;
/**
*制单人
*/
public String billmaker;
/**
*创建人
*/
public String creator;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*修改人
*/
public String modifier;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*审批人
*/
public String approver;
/**
*审批状态
*/
public Integer approvestatus;
/**
*审批批语
*/
public String approvenote;
/**
*审批时间
*/
public UFDateTime approvedate;
/**
*交易类型编码
*/
public String transtype;
/**
*单据类型
*/
public String billtype;
/**
*交易类型pk
*/
public String transtypepk;
/**
*voucher
*/
public UFDouble voucher;
/**
*shipping_fee
*/
public UFDouble shipping_fee;
/**
*customer_last_name
*/
public String customer_last_name;
/**
*voucher_code
*/
public String voucher_code;
/**
*delivery_info
*/
public String delivery_info;
/**
*gift_message
*/
public String gift_message;
/**
*branch_number
*/
public String branch_number;
/**
*tax_code
*/
public String tax_code;
/**
*extra_attributes
*/
public String extra_attributes;
/**
*customer_first_name
*/
public String customer_first_name;
/**
*payment_method
*/
public String payment_method;
/**
*订单状态
*/
public String order_status;
/**
*备注
*/
public String remarks;
/**
*电商订单号
*/
public String order_number;
/**
*national_registration_number
*/
public String national_registration_number;
/**
*promised_shipping_times
*/
public String promised_shipping_times;
/**
*items_count
*/
public Integer items_count;
/**
*created_at
*/
public UFDate created_at;
/**
*shipping_address1
*/
public String shipping_address1;
/**
*shipping_address2
*/
public String shipping_address2;
/**
*shipping_address3
*/
public String shipping_address3;
/**
*shipping_address4
*/
public String shipping_address4;
/**
*shipping_address5
*/
public String shipping_address5;
/**
*shipping_post_code
*/
public String shipping_post_code;
/**
*shipping_last_name
*/
public String shipping_last_name;
/**
*shipping_country
*/
public String shipping_country;
/**
*shipping_city
*/
public String shipping_city;
/**
*shipping_phone2
*/
public String shipping_phone2;
/**
*shipping_first_name
*/
public String shipping_first_name;
/**
*shipping_phone
*/
public String shipping_phone;
/**
*price
*/
public UFDouble price;
/**
*billing_address1
*/
public String billing_address1;
/**
*billing_address2
*/
public String billing_address2;
/**
*billing_address3
*/
public String billing_address3;
/**
*billing_address4
*/
public String billing_address4;
/**
*billing_address5
*/
public String billing_address5;
/**
*billing_post_code
*/
public String billing_post_code;
/**
*billing_last_name
*/
public String billing_last_name;
/**
*billing_country
*/
public String billing_country;
/**
*billing_city
*/
public String billing_city;
/**
*billing_phone2
*/
public String billing_phone2;
/**
*billing_first_name
*/
public String billing_first_name;
/**
*billing_phone
*/
public String billing_phone;
/**
*order_id
*/
public String order_id;
/**
*gift_option
*/
public UFBoolean gift_option;
/**
*updated_at
*/
public UFDate updated_at;
/**
*requesturl
*/
public String requesturl;
/**
*lastupdatetime
*/
public UFDate lastupdatetime;
/**
*币种
*/
public String pk_currtype;
/**
*时间戳
*/
public UFDateTime ts;

/**
 * 电商平台
 */
public String platform;
    
    
/**
* 属性 pk_ordercenter的Getter方法.属性名：销售组织主键
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getPk_ordercenter() {
return this.pk_ordercenter;
} 

/**
* 属性pk_ordercenter的Setter方法.属性名：销售组织主键
* 创建日期:2019-6-6
* @param newPk_ordercenter java.lang.String
*/
public void setPk_ordercenter ( String pk_ordercenter) {
this.pk_ordercenter=pk_ordercenter;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2019-6-6
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
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
* 属性 billno的Getter方法.属性名：单据号
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBillno() {
return this.billno;
} 

/**
* 属性billno的Setter方法.属性名：单据号
* 创建日期:2019-6-6
* @param newBillno java.lang.String
*/
public void setBillno ( String billno) {
this.billno=billno;
} 
 
/**
* 属性 billname的Getter方法.属性名：单据名称
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBillname() {
return this.billname;
} 

/**
* 属性billname的Setter方法.属性名：单据名称
* 创建日期:2019-6-6
* @param newBillname java.lang.String
*/
public void setBillname ( String billname) {
this.billname=billname;
} 
 
/**
* 属性 billdate的Getter方法.属性名：单据日期
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getBilldate() {
return this.billdate;
} 

/**
* 属性billdate的Setter方法.属性名：单据日期
* 创建日期:2019-6-6
* @param newBilldate nc.vo.pub.lang.UFDate
*/
public void setBilldate ( UFDate billdate) {
this.billdate=billdate;
} 
 
/**
* 属性 billmaker的Getter方法.属性名：制单人
*  创建日期:2019-6-6
* @return nc.vo.sm.UserVO
*/
public String getBillmaker() {
return this.billmaker;
} 

/**
* 属性billmaker的Setter方法.属性名：制单人
* 创建日期:2019-6-6
* @param newBillmaker nc.vo.sm.UserVO
*/
public void setBillmaker ( String billmaker) {
this.billmaker=billmaker;
} 
 
/**
* 属性 creator的Getter方法.属性名：创建人
*  创建日期:2019-6-6
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：创建人
* 创建日期:2019-6-6
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：创建时间
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：创建时间
* 创建日期:2019-6-6
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：修改人
*  创建日期:2019-6-6
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：修改人
* 创建日期:2019-6-6
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：修改时间
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：修改时间
* 创建日期:2019-6-6
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 approver的Getter方法.属性名：审批人
*  创建日期:2019-6-6
* @return nc.vo.sm.UserVO
*/
public String getApprover() {
return this.approver;
} 

/**
* 属性approver的Setter方法.属性名：审批人
* 创建日期:2019-6-6
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( String approver) {
this.approver=approver;
} 
 
/**
* 属性 approvestatus的Getter方法.属性名：审批状态
*  创建日期:2019-6-6
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getApprovestatus() {
return this.approvestatus;
} 

/**
* 属性approvestatus的Setter方法.属性名：审批状态
* 创建日期:2019-6-6
* @param newApprovestatus nc.vo.pub.pf.BillStatusEnum
*/
public void setApprovestatus ( Integer approvestatus) {
this.approvestatus=approvestatus;
} 
 
/**
* 属性 approvenote的Getter方法.属性名：审批批语
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getApprovenote() {
return this.approvenote;
} 

/**
* 属性approvenote的Setter方法.属性名：审批批语
* 创建日期:2019-6-6
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
} 
 
/**
* 属性 approvedate的Getter方法.属性名：审批时间
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getApprovedate() {
return this.approvedate;
} 

/**
* 属性approvedate的Setter方法.属性名：审批时间
* 创建日期:2019-6-6
* @param newApprovedate nc.vo.pub.lang.UFDateTime
*/
public void setApprovedate ( UFDateTime approvedate) {
this.approvedate=approvedate;
} 
 
/**
* 属性 transtype的Getter方法.属性名：交易类型编码
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getTranstype() {
return this.transtype;
} 

/**
* 属性transtype的Setter方法.属性名：交易类型编码
* 创建日期:2019-6-6
* @param newTranstype java.lang.String
*/
public void setTranstype ( String transtype) {
this.transtype=transtype;
} 
 
/**
* 属性 billtype的Getter方法.属性名：单据类型
*  创建日期:2019-6-6
* @return nc.vo.pub.billtype.BilltypeVO
*/
public String getBilltype() {
return this.billtype;
} 

/**
* 属性billtype的Setter方法.属性名：单据类型
* 创建日期:2019-6-6
* @param newBilltype nc.vo.pub.billtype.BilltypeVO
*/
public void setBilltype ( String billtype) {
this.billtype=billtype;
} 
 
/**
* 属性 transtypepk的Getter方法.属性名：交易类型pk
*  创建日期:2019-6-6
* @return nc.vo.pub.billtype.BilltypeVO
*/
public String getTranstypepk() {
return this.transtypepk;
} 

/**
* 属性transtypepk的Setter方法.属性名：交易类型pk
* 创建日期:2019-6-6
* @param newTranstypepk nc.vo.pub.billtype.BilltypeVO
*/
public void setTranstypepk ( String transtypepk) {
this.transtypepk=transtypepk;
} 
 
/**
* 属性 voucher的Getter方法.属性名：voucher
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVoucher() {
return this.voucher;
} 

/**
* 属性voucher的Setter方法.属性名：voucher
* 创建日期:2019-6-6
* @param newVoucher nc.vo.pub.lang.UFDouble
*/
public void setVoucher ( UFDouble voucher) {
this.voucher=voucher;
} 
 
/**
* 属性 shipping_fee的Getter方法.属性名：shipping_fee
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getShipping_fee() {
return this.shipping_fee;
} 

/**
* 属性shipping_fee的Setter方法.属性名：shipping_fee
* 创建日期:2019-6-6
* @param newShipping_fee nc.vo.pub.lang.UFDouble
*/
public void setShipping_fee ( UFDouble shipping_fee) {
this.shipping_fee=shipping_fee;
} 
 
/**
* 属性 customer_last_name的Getter方法.属性名：customer_last_name
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getCustomer_last_name() {
return this.customer_last_name;
} 

/**
* 属性customer_last_name的Setter方法.属性名：customer_last_name
* 创建日期:2019-6-6
* @param newCustomer_last_name java.lang.String
*/
public void setCustomer_last_name ( String customer_last_name) {
this.customer_last_name=customer_last_name;
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
* 属性 delivery_info的Getter方法.属性名：delivery_info
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getDelivery_info() {
return this.delivery_info;
} 

/**
* 属性delivery_info的Setter方法.属性名：delivery_info
* 创建日期:2019-6-6
* @param newDelivery_info java.lang.String
*/
public void setDelivery_info ( String delivery_info) {
this.delivery_info=delivery_info;
} 
 
/**
* 属性 gift_message的Getter方法.属性名：gift_message
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getGift_message() {
return this.gift_message;
} 

/**
* 属性gift_message的Setter方法.属性名：gift_message
* 创建日期:2019-6-6
* @param newGift_message java.lang.String
*/
public void setGift_message ( String gift_message) {
this.gift_message=gift_message;
} 
 
/**
* 属性 branch_number的Getter方法.属性名：branch_number
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBranch_number() {
return this.branch_number;
} 

/**
* 属性branch_number的Setter方法.属性名：branch_number
* 创建日期:2019-6-6
* @param newBranch_number java.lang.String
*/
public void setBranch_number ( String branch_number) {
this.branch_number=branch_number;
} 
 
/**
* 属性 tax_code的Getter方法.属性名：tax_code
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getTax_code() {
return this.tax_code;
} 

/**
* 属性tax_code的Setter方法.属性名：tax_code
* 创建日期:2019-6-6
* @param newTax_code java.lang.String
*/
public void setTax_code ( String tax_code) {
this.tax_code=tax_code;
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
* 属性 customer_first_name的Getter方法.属性名：customer_first_name
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getCustomer_first_name() {
return this.customer_first_name;
} 

/**
* 属性customer_first_name的Setter方法.属性名：customer_first_name
* 创建日期:2019-6-6
* @param newCustomer_first_name java.lang.String
*/
public void setCustomer_first_name ( String customer_first_name) {
this.customer_first_name=customer_first_name;
} 
 
/**
* 属性 payment_method的Getter方法.属性名：payment_method
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getPayment_method() {
return this.payment_method;
} 

/**
* 属性payment_method的Setter方法.属性名：payment_method
* 创建日期:2019-6-6
* @param newPayment_method java.lang.String
*/
public void setPayment_method ( String payment_method) {
this.payment_method=payment_method;
} 
 
/**
* 属性 order_status的Getter方法.属性名：订单状态
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getOrder_status() {
return this.order_status;
} 

/**
* 属性order_status的Setter方法.属性名：订单状态
* 创建日期:2019-6-6
* @param newOrder_status java.lang.String
*/
public void setOrder_status ( String order_status) {
this.order_status=order_status;
} 
 
/**
* 属性 remarks的Getter方法.属性名：备注
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getRemarks() {
return this.remarks;
} 

/**
* 属性remarks的Setter方法.属性名：备注
* 创建日期:2019-6-6
* @param newRemarks java.lang.String
*/
public void setRemarks ( String remarks) {
this.remarks=remarks;
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
* 属性 national_registration_number的Getter方法.属性名：national_registration_number
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getNational_registration_number() {
return this.national_registration_number;
} 

/**
* 属性national_registration_number的Setter方法.属性名：national_registration_number
* 创建日期:2019-6-6
* @param newNational_registration_number java.lang.String
*/
public void setNational_registration_number ( String national_registration_number) {
this.national_registration_number=national_registration_number;
} 
 
/**
* 属性 promised_shipping_times的Getter方法.属性名：promised_shipping_times
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getPromised_shipping_times() {
return this.promised_shipping_times;
} 

/**
* 属性promised_shipping_times的Setter方法.属性名：promised_shipping_times
* 创建日期:2019-6-6
* @param newPromised_shipping_times java.lang.String
*/
public void setPromised_shipping_times ( String promised_shipping_times) {
this.promised_shipping_times=promised_shipping_times;
} 
 
/**
* 属性 items_count的Getter方法.属性名：items_count
*  创建日期:2019-6-6
* @return java.lang.Integer
*/
public Integer getItems_count() {
return this.items_count;
} 

/**
* 属性items_count的Setter方法.属性名：items_count
* 创建日期:2019-6-6
* @param newItems_count java.lang.Integer
*/
public void setItems_count ( Integer items_count) {
this.items_count=items_count;
} 
 
/**
* 属性 created_at的Getter方法.属性名：created_at
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getCreated_at() {
return this.created_at;
} 

/**
* 属性created_at的Setter方法.属性名：created_at
* 创建日期:2019-6-6
* @param newCreated_at nc.vo.pub.lang.UFDate
*/
public void setCreated_at ( UFDate created_at) {
this.created_at=created_at;
} 
 
/**
* 属性 shipping_address1的Getter方法.属性名：shipping_address1
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_address1() {
return this.shipping_address1;
} 

/**
* 属性shipping_address1的Setter方法.属性名：shipping_address1
* 创建日期:2019-6-6
* @param newShipping_address1 java.lang.String
*/
public void setShipping_address1 ( String shipping_address1) {
this.shipping_address1=shipping_address1;
} 
 
/**
* 属性 shipping_address2的Getter方法.属性名：shipping_address2
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_address2() {
return this.shipping_address2;
} 

/**
* 属性shipping_address2的Setter方法.属性名：shipping_address2
* 创建日期:2019-6-6
* @param newShipping_address2 java.lang.String
*/
public void setShipping_address2 ( String shipping_address2) {
this.shipping_address2=shipping_address2;
} 
 
/**
* 属性 shipping_address3的Getter方法.属性名：shipping_address3
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_address3() {
return this.shipping_address3;
} 

/**
* 属性shipping_address3的Setter方法.属性名：shipping_address3
* 创建日期:2019-6-6
* @param newShipping_address3 java.lang.String
*/
public void setShipping_address3 ( String shipping_address3) {
this.shipping_address3=shipping_address3;
} 
 
/**
* 属性 shipping_address4的Getter方法.属性名：shipping_address4
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_address4() {
return this.shipping_address4;
} 

/**
* 属性shipping_address4的Setter方法.属性名：shipping_address4
* 创建日期:2019-6-6
* @param newShipping_address4 java.lang.String
*/
public void setShipping_address4 ( String shipping_address4) {
this.shipping_address4=shipping_address4;
} 
 
/**
* 属性 shipping_address5的Getter方法.属性名：shipping_address5
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_address5() {
return this.shipping_address5;
} 

/**
* 属性shipping_address5的Setter方法.属性名：shipping_address5
* 创建日期:2019-6-6
* @param newShipping_address5 java.lang.String
*/
public void setShipping_address5 ( String shipping_address5) {
this.shipping_address5=shipping_address5;
} 
 
/**
* 属性 shipping_post_code的Getter方法.属性名：shipping_post_code
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_post_code() {
return this.shipping_post_code;
} 

/**
* 属性shipping_post_code的Setter方法.属性名：shipping_post_code
* 创建日期:2019-6-6
* @param newShipping_post_code java.lang.String
*/
public void setShipping_post_code ( String shipping_post_code) {
this.shipping_post_code=shipping_post_code;
} 
 
/**
* 属性 shipping_last_name的Getter方法.属性名：shipping_last_name
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_last_name() {
return this.shipping_last_name;
} 

/**
* 属性shipping_last_name的Setter方法.属性名：shipping_last_name
* 创建日期:2019-6-6
* @param newShipping_last_name java.lang.String
*/
public void setShipping_last_name ( String shipping_last_name) {
this.shipping_last_name=shipping_last_name;
} 
 
/**
* 属性 shipping_country的Getter方法.属性名：shipping_country
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_country() {
return this.shipping_country;
} 

/**
* 属性shipping_country的Setter方法.属性名：shipping_country
* 创建日期:2019-6-6
* @param newShipping_country java.lang.String
*/
public void setShipping_country ( String shipping_country) {
this.shipping_country=shipping_country;
} 
 
/**
* 属性 shipping_city的Getter方法.属性名：shipping_city
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_city() {
return this.shipping_city;
} 

/**
* 属性shipping_city的Setter方法.属性名：shipping_city
* 创建日期:2019-6-6
* @param newShipping_city java.lang.String
*/
public void setShipping_city ( String shipping_city) {
this.shipping_city=shipping_city;
} 
 
/**
* 属性 shipping_phone2的Getter方法.属性名：shipping_phone2
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_phone2() {
return this.shipping_phone2;
} 

/**
* 属性shipping_phone2的Setter方法.属性名：shipping_phone2
* 创建日期:2019-6-6
* @param newShipping_phone2 java.lang.String
*/
public void setShipping_phone2 ( String shipping_phone2) {
this.shipping_phone2=shipping_phone2;
} 
 
/**
* 属性 shipping_first_name的Getter方法.属性名：shipping_first_name
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_first_name() {
return this.shipping_first_name;
} 

/**
* 属性shipping_first_name的Setter方法.属性名：shipping_first_name
* 创建日期:2019-6-6
* @param newShipping_first_name java.lang.String
*/
public void setShipping_first_name ( String shipping_first_name) {
this.shipping_first_name=shipping_first_name;
} 
 
/**
* 属性 shipping_phone的Getter方法.属性名：shipping_phone
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getShipping_phone() {
return this.shipping_phone;
} 

/**
* 属性shipping_phone的Setter方法.属性名：shipping_phone
* 创建日期:2019-6-6
* @param newShipping_phone java.lang.String
*/
public void setShipping_phone ( String shipping_phone) {
this.shipping_phone=shipping_phone;
} 
 
/**
* 属性 price的Getter方法.属性名：price
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getPrice() {
return this.price;
} 

/**
* 属性price的Setter方法.属性名：price
* 创建日期:2019-6-6
* @param newPrice nc.vo.pub.lang.UFDouble
*/
public void setPrice ( UFDouble price) {
this.price=price;
} 
 
/**
* 属性 billing_address1的Getter方法.属性名：billing_address1
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_address1() {
return this.billing_address1;
} 

/**
* 属性billing_address1的Setter方法.属性名：billing_address1
* 创建日期:2019-6-6
* @param newBilling_address1 java.lang.String
*/
public void setBilling_address1 ( String billing_address1) {
this.billing_address1=billing_address1;
} 
 
/**
* 属性 billing_address2的Getter方法.属性名：billing_address2
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_address2() {
return this.billing_address2;
} 

/**
* 属性billing_address2的Setter方法.属性名：billing_address2
* 创建日期:2019-6-6
* @param newBilling_address2 java.lang.String
*/
public void setBilling_address2 ( String billing_address2) {
this.billing_address2=billing_address2;
} 
 
/**
* 属性 billing_address3的Getter方法.属性名：billing_address3
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_address3() {
return this.billing_address3;
} 

/**
* 属性billing_address3的Setter方法.属性名：billing_address3
* 创建日期:2019-6-6
* @param newBilling_address3 java.lang.String
*/
public void setBilling_address3 ( String billing_address3) {
this.billing_address3=billing_address3;
} 
 
/**
* 属性 billing_address4的Getter方法.属性名：billing_address4
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_address4() {
return this.billing_address4;
} 

/**
* 属性billing_address4的Setter方法.属性名：billing_address4
* 创建日期:2019-6-6
* @param newBilling_address4 java.lang.String
*/
public void setBilling_address4 ( String billing_address4) {
this.billing_address4=billing_address4;
} 
 
/**
* 属性 billing_address5的Getter方法.属性名：billing_address5
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_address5() {
return this.billing_address5;
} 

/**
* 属性billing_address5的Setter方法.属性名：billing_address5
* 创建日期:2019-6-6
* @param newBilling_address5 java.lang.String
*/
public void setBilling_address5 ( String billing_address5) {
this.billing_address5=billing_address5;
} 
 
/**
* 属性 billing_post_code的Getter方法.属性名：billing_post_code
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_post_code() {
return this.billing_post_code;
} 

/**
* 属性billing_post_code的Setter方法.属性名：billing_post_code
* 创建日期:2019-6-6
* @param newBilling_post_code java.lang.String
*/
public void setBilling_post_code ( String billing_post_code) {
this.billing_post_code=billing_post_code;
} 
 
/**
* 属性 billing_last_name的Getter方法.属性名：billing_last_name
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_last_name() {
return this.billing_last_name;
} 

/**
* 属性billing_last_name的Setter方法.属性名：billing_last_name
* 创建日期:2019-6-6
* @param newBilling_last_name java.lang.String
*/
public void setBilling_last_name ( String billing_last_name) {
this.billing_last_name=billing_last_name;
} 
 
/**
* 属性 billing_country的Getter方法.属性名：billing_country
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_country() {
return this.billing_country;
} 

/**
* 属性billing_country的Setter方法.属性名：billing_country
* 创建日期:2019-6-6
* @param newBilling_country java.lang.String
*/
public void setBilling_country ( String billing_country) {
this.billing_country=billing_country;
} 
 
/**
* 属性 billing_city的Getter方法.属性名：billing_city
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_city() {
return this.billing_city;
} 

/**
* 属性billing_city的Setter方法.属性名：billing_city
* 创建日期:2019-6-6
* @param newBilling_city java.lang.String
*/
public void setBilling_city ( String billing_city) {
this.billing_city=billing_city;
} 
 
/**
* 属性 billing_phone2的Getter方法.属性名：billing_phone2
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_phone2() {
return this.billing_phone2;
} 

/**
* 属性billing_phone2的Setter方法.属性名：billing_phone2
* 创建日期:2019-6-6
* @param newBilling_phone2 java.lang.String
*/
public void setBilling_phone2 ( String billing_phone2) {
this.billing_phone2=billing_phone2;
} 
 
/**
* 属性 billing_first_name的Getter方法.属性名：billing_first_name
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_first_name() {
return this.billing_first_name;
} 

/**
* 属性billing_first_name的Setter方法.属性名：billing_first_name
* 创建日期:2019-6-6
* @param newBilling_first_name java.lang.String
*/
public void setBilling_first_name ( String billing_first_name) {
this.billing_first_name=billing_first_name;
} 
 
/**
* 属性 billing_phone的Getter方法.属性名：billing_phone
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getBilling_phone() {
return this.billing_phone;
} 

/**
* 属性billing_phone的Setter方法.属性名：billing_phone
* 创建日期:2019-6-6
* @param newBilling_phone java.lang.String
*/
public void setBilling_phone ( String billing_phone) {
this.billing_phone=billing_phone;
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
* 属性 gift_option的Getter方法.属性名：gift_option
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getGift_option() {
return this.gift_option;
} 

/**
* 属性gift_option的Setter方法.属性名：gift_option
* 创建日期:2019-6-6
* @param newGift_option nc.vo.pub.lang.UFBoolean
*/
public void setGift_option ( UFBoolean gift_option) {
this.gift_option=gift_option;
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
* 属性 requesturl的Getter方法.属性名：requesturl
*  创建日期:2019-6-6
* @return java.lang.String
*/
public String getRequesturl() {
return this.requesturl;
} 

/**
* 属性requesturl的Setter方法.属性名：requesturl
* 创建日期:2019-6-6
* @param newRequesturl java.lang.String
*/
public void setRequesturl ( String requesturl) {
this.requesturl=requesturl;
} 
 
/**
* 属性 lastupdatetime的Getter方法.属性名：lastupdatetime
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getLastupdatetime() {
return this.lastupdatetime;
} 

/**
* 属性lastupdatetime的Setter方法.属性名：lastupdatetime
* 创建日期:2019-6-6
* @param newLastupdatetime nc.vo.pub.lang.UFDate
*/
public void setLastupdatetime ( UFDate lastupdatetime) {
this.lastupdatetime=lastupdatetime;
} 
 
/**
* 属性 pk_currtype的Getter方法.属性名：币种
*  创建日期:2019-6-6
* @return nc.vo.bd.currtype.CurrtypeVO
*/
public String getPk_currtype() {
return this.pk_currtype;
} 

/**
* 属性pk_currtype的Setter方法.属性名：币种
* 创建日期:2019-6-6
* @param newPk_currtype nc.vo.bd.currtype.CurrtypeVO
*/
public void setPk_currtype ( String pk_currtype) {
this.pk_currtype=pk_currtype;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2019-6-6
* @return nc.vo.pub.lang.UFDateTime
*/

public UFDateTime getTs() {
return this.ts;
}
public String getPlatform() {
	return platform;
}

public void setPlatform(String platform) {
	this.platform = platform;
}

/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2019-6-6
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("so.so_ordercenter");
    }
   }
    