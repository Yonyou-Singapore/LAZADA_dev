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
 
public class So_ordercenter extends SuperVO {
	
/**
*������֯����
*/
public String pk_ordercenter;
/**
*����
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
*���ݺ�
*/
public String billno;
/**
*��������
*/
public String billname;
/**
*��������
*/
public UFDate billdate;
/**
*�Ƶ���
*/
public String billmaker;
/**
*������
*/
public String creator;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*�޸���
*/
public String modifier;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*������
*/
public String approver;
/**
*����״̬
*/
public Integer approvestatus;
/**
*��������
*/
public String approvenote;
/**
*����ʱ��
*/
public UFDateTime approvedate;
/**
*�������ͱ���
*/
public String transtype;
/**
*��������
*/
public String billtype;
/**
*��������pk
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
*����״̬
*/
public String order_status;
/**
*��ע
*/
public String remarks;
/**
*���̶�����
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
*����
*/
public String pk_currtype;
/**
*ʱ���
*/
public UFDateTime ts;

/**
 * ����ƽ̨
 */
public String platform;

/**
 * �Ƿ��Ѿ����ɶ���
 */
public UFBoolean isgenerated;
    
/**
* ���� pk_ordercenter��Getter����.��������������֯����
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getPk_ordercenter() {
return this.pk_ordercenter;
} 

/**
* ����pk_ordercenter��Setter����.��������������֯����
* ��������:2019-6-6
* @param newPk_ordercenter java.lang.String
*/
public void setPk_ordercenter ( String pk_ordercenter) {
this.pk_ordercenter=pk_ordercenter;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2019-6-6
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
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
* ���� billno��Getter����.�����������ݺ�
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBillno() {
return this.billno;
} 

/**
* ����billno��Setter����.�����������ݺ�
* ��������:2019-6-6
* @param newBillno java.lang.String
*/
public void setBillno ( String billno) {
this.billno=billno;
} 
 
/**
* ���� billname��Getter����.����������������
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBillname() {
return this.billname;
} 

/**
* ����billname��Setter����.����������������
* ��������:2019-6-6
* @param newBillname java.lang.String
*/
public void setBillname ( String billname) {
this.billname=billname;
} 
 
/**
* ���� billdate��Getter����.����������������
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getBilldate() {
return this.billdate;
} 

/**
* ����billdate��Setter����.����������������
* ��������:2019-6-6
* @param newBilldate nc.vo.pub.lang.UFDate
*/
public void setBilldate ( UFDate billdate) {
this.billdate=billdate;
} 
 
/**
* ���� billmaker��Getter����.���������Ƶ���
*  ��������:2019-6-6
* @return nc.vo.sm.UserVO
*/
public String getBillmaker() {
return this.billmaker;
} 

/**
* ����billmaker��Setter����.���������Ƶ���
* ��������:2019-6-6
* @param newBillmaker nc.vo.sm.UserVO
*/
public void setBillmaker ( String billmaker) {
this.billmaker=billmaker;
} 
 
/**
* ���� creator��Getter����.��������������
*  ��������:2019-6-6
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* ����creator��Setter����.��������������
* ��������:2019-6-6
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* ���� creationtime��Getter����.������������ʱ��
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* ����creationtime��Setter����.������������ʱ��
* ��������:2019-6-6
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* ���� modifier��Getter����.���������޸���
*  ��������:2019-6-6
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* ����modifier��Setter����.���������޸���
* ��������:2019-6-6
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* ���� modifiedtime��Getter����.���������޸�ʱ��
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* ����modifiedtime��Setter����.���������޸�ʱ��
* ��������:2019-6-6
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* ���� approver��Getter����.��������������
*  ��������:2019-6-6
* @return nc.vo.sm.UserVO
*/
public String getApprover() {
return this.approver;
} 

/**
* ����approver��Setter����.��������������
* ��������:2019-6-6
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( String approver) {
this.approver=approver;
} 
 
/**
* ���� approvestatus��Getter����.������������״̬
*  ��������:2019-6-6
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getApprovestatus() {
return this.approvestatus;
} 

/**
* ����approvestatus��Setter����.������������״̬
* ��������:2019-6-6
* @param newApprovestatus nc.vo.pub.pf.BillStatusEnum
*/
public void setApprovestatus ( Integer approvestatus) {
this.approvestatus=approvestatus;
} 
 
/**
* ���� approvenote��Getter����.����������������
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getApprovenote() {
return this.approvenote;
} 

/**
* ����approvenote��Setter����.����������������
* ��������:2019-6-6
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
} 
 
/**
* ���� approvedate��Getter����.������������ʱ��
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getApprovedate() {
return this.approvedate;
} 

/**
* ����approvedate��Setter����.������������ʱ��
* ��������:2019-6-6
* @param newApprovedate nc.vo.pub.lang.UFDateTime
*/
public void setApprovedate ( UFDateTime approvedate) {
this.approvedate=approvedate;
} 
 
/**
* ���� transtype��Getter����.���������������ͱ���
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getTranstype() {
return this.transtype;
} 

/**
* ����transtype��Setter����.���������������ͱ���
* ��������:2019-6-6
* @param newTranstype java.lang.String
*/
public void setTranstype ( String transtype) {
this.transtype=transtype;
} 
 
/**
* ���� billtype��Getter����.����������������
*  ��������:2019-6-6
* @return nc.vo.pub.billtype.BilltypeVO
*/
public String getBilltype() {
return this.billtype;
} 

/**
* ����billtype��Setter����.����������������
* ��������:2019-6-6
* @param newBilltype nc.vo.pub.billtype.BilltypeVO
*/
public void setBilltype ( String billtype) {
this.billtype=billtype;
} 
 
/**
* ���� transtypepk��Getter����.����������������pk
*  ��������:2019-6-6
* @return nc.vo.pub.billtype.BilltypeVO
*/
public String getTranstypepk() {
return this.transtypepk;
} 

/**
* ����transtypepk��Setter����.����������������pk
* ��������:2019-6-6
* @param newTranstypepk nc.vo.pub.billtype.BilltypeVO
*/
public void setTranstypepk ( String transtypepk) {
this.transtypepk=transtypepk;
} 
 
/**
* ���� voucher��Getter����.��������voucher
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVoucher() {
return this.voucher;
} 

/**
* ����voucher��Setter����.��������voucher
* ��������:2019-6-6
* @param newVoucher nc.vo.pub.lang.UFDouble
*/
public void setVoucher ( UFDouble voucher) {
this.voucher=voucher;
} 
 
/**
* ���� shipping_fee��Getter����.��������shipping_fee
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getShipping_fee() {
return this.shipping_fee;
} 

/**
* ����shipping_fee��Setter����.��������shipping_fee
* ��������:2019-6-6
* @param newShipping_fee nc.vo.pub.lang.UFDouble
*/
public void setShipping_fee ( UFDouble shipping_fee) {
this.shipping_fee=shipping_fee;
} 
 
/**
* ���� customer_last_name��Getter����.��������customer_last_name
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getCustomer_last_name() {
return this.customer_last_name;
} 

/**
* ����customer_last_name��Setter����.��������customer_last_name
* ��������:2019-6-6
* @param newCustomer_last_name java.lang.String
*/
public void setCustomer_last_name ( String customer_last_name) {
this.customer_last_name=customer_last_name;
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
* ���� delivery_info��Getter����.��������delivery_info
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getDelivery_info() {
return this.delivery_info;
} 

/**
* ����delivery_info��Setter����.��������delivery_info
* ��������:2019-6-6
* @param newDelivery_info java.lang.String
*/
public void setDelivery_info ( String delivery_info) {
this.delivery_info=delivery_info;
} 
 
/**
* ���� gift_message��Getter����.��������gift_message
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getGift_message() {
return this.gift_message;
} 

/**
* ����gift_message��Setter����.��������gift_message
* ��������:2019-6-6
* @param newGift_message java.lang.String
*/
public void setGift_message ( String gift_message) {
this.gift_message=gift_message;
} 
 
/**
* ���� branch_number��Getter����.��������branch_number
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBranch_number() {
return this.branch_number;
} 

/**
* ����branch_number��Setter����.��������branch_number
* ��������:2019-6-6
* @param newBranch_number java.lang.String
*/
public void setBranch_number ( String branch_number) {
this.branch_number=branch_number;
} 
 
/**
* ���� tax_code��Getter����.��������tax_code
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getTax_code() {
return this.tax_code;
} 

/**
* ����tax_code��Setter����.��������tax_code
* ��������:2019-6-6
* @param newTax_code java.lang.String
*/
public void setTax_code ( String tax_code) {
this.tax_code=tax_code;
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
* ���� customer_first_name��Getter����.��������customer_first_name
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getCustomer_first_name() {
return this.customer_first_name;
} 

/**
* ����customer_first_name��Setter����.��������customer_first_name
* ��������:2019-6-6
* @param newCustomer_first_name java.lang.String
*/
public void setCustomer_first_name ( String customer_first_name) {
this.customer_first_name=customer_first_name;
} 
 
/**
* ���� payment_method��Getter����.��������payment_method
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getPayment_method() {
return this.payment_method;
} 

/**
* ����payment_method��Setter����.��������payment_method
* ��������:2019-6-6
* @param newPayment_method java.lang.String
*/
public void setPayment_method ( String payment_method) {
this.payment_method=payment_method;
} 
 
/**
* ���� order_status��Getter����.������������״̬
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getOrder_status() {
return this.order_status;
} 

/**
* ����order_status��Setter����.������������״̬
* ��������:2019-6-6
* @param newOrder_status java.lang.String
*/
public void setOrder_status ( String order_status) {
this.order_status=order_status;
} 
 
/**
* ���� remarks��Getter����.����������ע
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getRemarks() {
return this.remarks;
} 

/**
* ����remarks��Setter����.����������ע
* ��������:2019-6-6
* @param newRemarks java.lang.String
*/
public void setRemarks ( String remarks) {
this.remarks=remarks;
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
* ���� national_registration_number��Getter����.��������national_registration_number
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getNational_registration_number() {
return this.national_registration_number;
} 

/**
* ����national_registration_number��Setter����.��������national_registration_number
* ��������:2019-6-6
* @param newNational_registration_number java.lang.String
*/
public void setNational_registration_number ( String national_registration_number) {
this.national_registration_number=national_registration_number;
} 
 
/**
* ���� promised_shipping_times��Getter����.��������promised_shipping_times
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getPromised_shipping_times() {
return this.promised_shipping_times;
} 

/**
* ����promised_shipping_times��Setter����.��������promised_shipping_times
* ��������:2019-6-6
* @param newPromised_shipping_times java.lang.String
*/
public void setPromised_shipping_times ( String promised_shipping_times) {
this.promised_shipping_times=promised_shipping_times;
} 
 
/**
* ���� items_count��Getter����.��������items_count
*  ��������:2019-6-6
* @return java.lang.Integer
*/
public Integer getItems_count() {
return this.items_count;
} 

/**
* ����items_count��Setter����.��������items_count
* ��������:2019-6-6
* @param newItems_count java.lang.Integer
*/
public void setItems_count ( Integer items_count) {
this.items_count=items_count;
} 
 
/**
* ���� created_at��Getter����.��������created_at
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getCreated_at() {
return this.created_at;
} 

/**
* ����created_at��Setter����.��������created_at
* ��������:2019-6-6
* @param newCreated_at nc.vo.pub.lang.UFDate
*/
public void setCreated_at ( UFDate created_at) {
this.created_at=created_at;
} 
 
/**
* ���� shipping_address1��Getter����.��������shipping_address1
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_address1() {
return this.shipping_address1;
} 

/**
* ����shipping_address1��Setter����.��������shipping_address1
* ��������:2019-6-6
* @param newShipping_address1 java.lang.String
*/
public void setShipping_address1 ( String shipping_address1) {
this.shipping_address1=shipping_address1;
} 
 
/**
* ���� shipping_address2��Getter����.��������shipping_address2
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_address2() {
return this.shipping_address2;
} 

/**
* ����shipping_address2��Setter����.��������shipping_address2
* ��������:2019-6-6
* @param newShipping_address2 java.lang.String
*/
public void setShipping_address2 ( String shipping_address2) {
this.shipping_address2=shipping_address2;
} 
 
/**
* ���� shipping_address3��Getter����.��������shipping_address3
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_address3() {
return this.shipping_address3;
} 

/**
* ����shipping_address3��Setter����.��������shipping_address3
* ��������:2019-6-6
* @param newShipping_address3 java.lang.String
*/
public void setShipping_address3 ( String shipping_address3) {
this.shipping_address3=shipping_address3;
} 
 
/**
* ���� shipping_address4��Getter����.��������shipping_address4
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_address4() {
return this.shipping_address4;
} 

/**
* ����shipping_address4��Setter����.��������shipping_address4
* ��������:2019-6-6
* @param newShipping_address4 java.lang.String
*/
public void setShipping_address4 ( String shipping_address4) {
this.shipping_address4=shipping_address4;
} 
 
/**
* ���� shipping_address5��Getter����.��������shipping_address5
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_address5() {
return this.shipping_address5;
} 

/**
* ����shipping_address5��Setter����.��������shipping_address5
* ��������:2019-6-6
* @param newShipping_address5 java.lang.String
*/
public void setShipping_address5 ( String shipping_address5) {
this.shipping_address5=shipping_address5;
} 
 
/**
* ���� shipping_post_code��Getter����.��������shipping_post_code
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_post_code() {
return this.shipping_post_code;
} 

/**
* ����shipping_post_code��Setter����.��������shipping_post_code
* ��������:2019-6-6
* @param newShipping_post_code java.lang.String
*/
public void setShipping_post_code ( String shipping_post_code) {
this.shipping_post_code=shipping_post_code;
} 
 
/**
* ���� shipping_last_name��Getter����.��������shipping_last_name
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_last_name() {
return this.shipping_last_name;
} 

/**
* ����shipping_last_name��Setter����.��������shipping_last_name
* ��������:2019-6-6
* @param newShipping_last_name java.lang.String
*/
public void setShipping_last_name ( String shipping_last_name) {
this.shipping_last_name=shipping_last_name;
} 
 
/**
* ���� shipping_country��Getter����.��������shipping_country
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_country() {
return this.shipping_country;
} 

/**
* ����shipping_country��Setter����.��������shipping_country
* ��������:2019-6-6
* @param newShipping_country java.lang.String
*/
public void setShipping_country ( String shipping_country) {
this.shipping_country=shipping_country;
} 
 
/**
* ���� shipping_city��Getter����.��������shipping_city
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_city() {
return this.shipping_city;
} 

/**
* ����shipping_city��Setter����.��������shipping_city
* ��������:2019-6-6
* @param newShipping_city java.lang.String
*/
public void setShipping_city ( String shipping_city) {
this.shipping_city=shipping_city;
} 
 
/**
* ���� shipping_phone2��Getter����.��������shipping_phone2
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_phone2() {
return this.shipping_phone2;
} 

/**
* ����shipping_phone2��Setter����.��������shipping_phone2
* ��������:2019-6-6
* @param newShipping_phone2 java.lang.String
*/
public void setShipping_phone2 ( String shipping_phone2) {
this.shipping_phone2=shipping_phone2;
} 
 
/**
* ���� shipping_first_name��Getter����.��������shipping_first_name
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_first_name() {
return this.shipping_first_name;
} 

/**
* ����shipping_first_name��Setter����.��������shipping_first_name
* ��������:2019-6-6
* @param newShipping_first_name java.lang.String
*/
public void setShipping_first_name ( String shipping_first_name) {
this.shipping_first_name=shipping_first_name;
} 
 
/**
* ���� shipping_phone��Getter����.��������shipping_phone
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getShipping_phone() {
return this.shipping_phone;
} 

/**
* ����shipping_phone��Setter����.��������shipping_phone
* ��������:2019-6-6
* @param newShipping_phone java.lang.String
*/
public void setShipping_phone ( String shipping_phone) {
this.shipping_phone=shipping_phone;
} 
 
/**
* ���� price��Getter����.��������price
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getPrice() {
return this.price;
} 

/**
* ����price��Setter����.��������price
* ��������:2019-6-6
* @param newPrice nc.vo.pub.lang.UFDouble
*/
public void setPrice ( UFDouble price) {
this.price=price;
} 
 
/**
* ���� billing_address1��Getter����.��������billing_address1
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_address1() {
return this.billing_address1;
} 

/**
* ����billing_address1��Setter����.��������billing_address1
* ��������:2019-6-6
* @param newBilling_address1 java.lang.String
*/
public void setBilling_address1 ( String billing_address1) {
this.billing_address1=billing_address1;
} 
 
/**
* ���� billing_address2��Getter����.��������billing_address2
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_address2() {
return this.billing_address2;
} 

/**
* ����billing_address2��Setter����.��������billing_address2
* ��������:2019-6-6
* @param newBilling_address2 java.lang.String
*/
public void setBilling_address2 ( String billing_address2) {
this.billing_address2=billing_address2;
} 
 
/**
* ���� billing_address3��Getter����.��������billing_address3
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_address3() {
return this.billing_address3;
} 

/**
* ����billing_address3��Setter����.��������billing_address3
* ��������:2019-6-6
* @param newBilling_address3 java.lang.String
*/
public void setBilling_address3 ( String billing_address3) {
this.billing_address3=billing_address3;
} 
 
/**
* ���� billing_address4��Getter����.��������billing_address4
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_address4() {
return this.billing_address4;
} 

/**
* ����billing_address4��Setter����.��������billing_address4
* ��������:2019-6-6
* @param newBilling_address4 java.lang.String
*/
public void setBilling_address4 ( String billing_address4) {
this.billing_address4=billing_address4;
} 
 
/**
* ���� billing_address5��Getter����.��������billing_address5
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_address5() {
return this.billing_address5;
} 

/**
* ����billing_address5��Setter����.��������billing_address5
* ��������:2019-6-6
* @param newBilling_address5 java.lang.String
*/
public void setBilling_address5 ( String billing_address5) {
this.billing_address5=billing_address5;
} 
 
/**
* ���� billing_post_code��Getter����.��������billing_post_code
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_post_code() {
return this.billing_post_code;
} 

/**
* ����billing_post_code��Setter����.��������billing_post_code
* ��������:2019-6-6
* @param newBilling_post_code java.lang.String
*/
public void setBilling_post_code ( String billing_post_code) {
this.billing_post_code=billing_post_code;
} 
 
/**
* ���� billing_last_name��Getter����.��������billing_last_name
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_last_name() {
return this.billing_last_name;
} 

/**
* ����billing_last_name��Setter����.��������billing_last_name
* ��������:2019-6-6
* @param newBilling_last_name java.lang.String
*/
public void setBilling_last_name ( String billing_last_name) {
this.billing_last_name=billing_last_name;
} 
 
/**
* ���� billing_country��Getter����.��������billing_country
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_country() {
return this.billing_country;
} 

/**
* ����billing_country��Setter����.��������billing_country
* ��������:2019-6-6
* @param newBilling_country java.lang.String
*/
public void setBilling_country ( String billing_country) {
this.billing_country=billing_country;
} 
 
/**
* ���� billing_city��Getter����.��������billing_city
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_city() {
return this.billing_city;
} 

/**
* ����billing_city��Setter����.��������billing_city
* ��������:2019-6-6
* @param newBilling_city java.lang.String
*/
public void setBilling_city ( String billing_city) {
this.billing_city=billing_city;
} 
 
/**
* ���� billing_phone2��Getter����.��������billing_phone2
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_phone2() {
return this.billing_phone2;
} 

/**
* ����billing_phone2��Setter����.��������billing_phone2
* ��������:2019-6-6
* @param newBilling_phone2 java.lang.String
*/
public void setBilling_phone2 ( String billing_phone2) {
this.billing_phone2=billing_phone2;
} 
 
/**
* ���� billing_first_name��Getter����.��������billing_first_name
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_first_name() {
return this.billing_first_name;
} 

/**
* ����billing_first_name��Setter����.��������billing_first_name
* ��������:2019-6-6
* @param newBilling_first_name java.lang.String
*/
public void setBilling_first_name ( String billing_first_name) {
this.billing_first_name=billing_first_name;
} 
 
/**
* ���� billing_phone��Getter����.��������billing_phone
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getBilling_phone() {
return this.billing_phone;
} 

/**
* ����billing_phone��Setter����.��������billing_phone
* ��������:2019-6-6
* @param newBilling_phone java.lang.String
*/
public void setBilling_phone ( String billing_phone) {
this.billing_phone=billing_phone;
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
* ���� gift_option��Getter����.��������gift_option
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getGift_option() {
return this.gift_option;
} 

/**
* ����gift_option��Setter����.��������gift_option
* ��������:2019-6-6
* @param newGift_option nc.vo.pub.lang.UFBoolean
*/
public void setGift_option ( UFBoolean gift_option) {
this.gift_option=gift_option;
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
* ���� requesturl��Getter����.��������requesturl
*  ��������:2019-6-6
* @return java.lang.String
*/
public String getRequesturl() {
return this.requesturl;
} 

/**
* ����requesturl��Setter����.��������requesturl
* ��������:2019-6-6
* @param newRequesturl java.lang.String
*/
public void setRequesturl ( String requesturl) {
this.requesturl=requesturl;
} 
 
/**
* ���� lastupdatetime��Getter����.��������lastupdatetime
*  ��������:2019-6-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getLastupdatetime() {
return this.lastupdatetime;
} 

/**
* ����lastupdatetime��Setter����.��������lastupdatetime
* ��������:2019-6-6
* @param newLastupdatetime nc.vo.pub.lang.UFDate
*/
public void setLastupdatetime ( UFDate lastupdatetime) {
this.lastupdatetime=lastupdatetime;
} 
 
/**
* ���� pk_currtype��Getter����.������������
*  ��������:2019-6-6
* @return nc.vo.bd.currtype.CurrtypeVO
*/
public String getPk_currtype() {
return this.pk_currtype;
} 

/**
* ����pk_currtype��Setter����.������������
* ��������:2019-6-6
* @param newPk_currtype nc.vo.bd.currtype.CurrtypeVO
*/
public void setPk_currtype ( String pk_currtype) {
this.pk_currtype=pk_currtype;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2019-6-6
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


public UFBoolean getIsgenerated() {
	return isgenerated;
}

public void setIsgenerated(UFBoolean isgenerated) {
	this.isgenerated = isgenerated;
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
    return VOMetaFactory.getInstance().getVOMeta("so.so_ordercenter");
    }
   }
    