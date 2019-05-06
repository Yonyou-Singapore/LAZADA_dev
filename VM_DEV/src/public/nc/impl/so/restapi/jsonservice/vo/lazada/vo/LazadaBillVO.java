package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFTime;

/**
 * lazada¶©µ¥Ö÷±í
 * @author ll
 *
 */
public class LazadaBillVO extends SuperVO implements Serializable {
	
	
	


	
    private String id;
    private String orgId;
    private java.math.BigDecimal voucher;
    private java.math.BigDecimal shipping_fee;
    private String customer_last_name;
    private String voucher_code;
    private String delivery_info;
    private String gift_message;

    private String branch_number;
    private String tax_code;
    private String extra_attributes;
    private String customer_first_name;
    private String payment_method;
    private String statuses;
    private String remarks;
    private String order_number;
    private String national_registration_number;
    private String promised_shipping_times;

    private Integer items_count;
    private UFDate created_at;
    private String shipping_address1;
    private String shipping_address2;
    private String shipping_address3;
    private String shipping_address4;
    private String shipping_address5;
    private String shipping_post_code;
    private String shipping_last_name;
    private String shipping_country;
    private String shipping_city;
    private String shipping_phone2;
    private String shipping_first_name;
    private String shipping_phone;
    private java.math.BigDecimal price;
    
    private String billing_address1;
    private String billing_address2;
    private String billing_address3;
    private String billing_address4;
    private String billing_address5;
    private String billing_post_code;
    private String billing_last_name;
    private String billing_country;
    private String billing_city;
    private String billing_phone2;
    private String billing_first_name;
    private String billing_phone;
    private String order_id;


    private Boolean gift_option;
    
    //updateed_at
    private UFDate updated_at;
    private String requestUrl;
    private UFDate lastupdatetime;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public BigDecimal getVoucher() {
        return voucher;
    }

    public void setVoucher(BigDecimal voucher) {
        this.voucher = voucher;
    }

    public BigDecimal getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(BigDecimal shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getCustomer_last_name() {
        return customer_last_name;
    }

    public void setCustomer_last_name(String customer_last_name) {
        this.customer_last_name = customer_last_name;
    }

    public String getVoucher_code() {
        return voucher_code;
    }

    public void setVoucher_code(String voucher_code) {
        this.voucher_code = voucher_code;
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

    public String getStatuses() {
        return statuses;
    }

    public void setStatuses(String statuses) {
        this.statuses = statuses;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
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

    public UFDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(UFDate created_at) {
        this.created_at = created_at;
    }

    public String getShipping_address1() {
        return shipping_address1;
    }

    public void setShipping_address1(String shipping_address1) {
        this.shipping_address1 = shipping_address1;
    }

    public String getShipping_address2() {
        return shipping_address2;
    }

    public void setShipping_address2(String shipping_address2) {
        this.shipping_address2 = shipping_address2;
    }

    public String getShipping_address3() {
        return shipping_address3;
    }

    public void setShipping_address3(String shipping_address3) {
        this.shipping_address3 = shipping_address3;
    }

    public String getShipping_address4() {
        return shipping_address4;
    }

    public void setShipping_address4(String shipping_address4) {
        this.shipping_address4 = shipping_address4;
    }

    public String getShipping_address5() {
        return shipping_address5;
    }

    public void setShipping_address5(String shipping_address5) {
        this.shipping_address5 = shipping_address5;
    }

    public String getShipping_post_code() {
        return shipping_post_code;
    }

    public void setShipping_post_code(String shipping_post_code) {
        this.shipping_post_code = shipping_post_code;
    }

    public String getShipping_last_name() {
        return shipping_last_name;
    }

    public void setShipping_last_name(String shipping_last_name) {
        this.shipping_last_name = shipping_last_name;
    }

    public String getShipping_country() {
        return shipping_country;
    }

    public void setShipping_country(String shipping_country) {
        this.shipping_country = shipping_country;
    }

    public String getShipping_city() {
        return shipping_city;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public String getShipping_phone2() {
        return shipping_phone2;
    }

    public void setShipping_phone2(String shipping_phone2) {
        this.shipping_phone2 = shipping_phone2;
    }

    public String getShipping_first_name() {
        return shipping_first_name;
    }

    public void setShipping_first_name(String shipping_first_name) {
        this.shipping_first_name = shipping_first_name;
    }

    public String getShipping_phone() {
        return shipping_phone;
    }

    public void setShipping_phone(String shipping_phone) {
        this.shipping_phone = shipping_phone;
    }

    public String getBilling_address1() {
        return billing_address1;
    }

    public void setBilling_address1(String billing_address1) {
        this.billing_address1 = billing_address1;
    }

    public String getBilling_address2() {
        return billing_address2;
    }

    public void setBilling_address2(String billing_address2) {
        this.billing_address2 = billing_address2;
    }

    public String getBilling_address3() {
        return billing_address3;
    }

    public void setBilling_address3(String billing_address3) {
        this.billing_address3 = billing_address3;
    }

    public String getBilling_address4() {
        return billing_address4;
    }

    public void setBilling_address4(String billing_address4) {
        this.billing_address4 = billing_address4;
    }

    public String getBilling_address5() {
        return billing_address5;
    }

    public void setBilling_address5(String billing_address5) {
        this.billing_address5 = billing_address5;
    }

    public String getBilling_post_code() {
        return billing_post_code;
    }

    public void setBilling_post_code(String billing_post_code) {
        this.billing_post_code = billing_post_code;
    }

    public String getBilling_last_name() {
        return billing_last_name;
    }

    public void setBilling_last_name(String billing_last_name) {
        this.billing_last_name = billing_last_name;
    }

    public String getBilling_country() {
        return billing_country;
    }

    public void setBilling_country(String billing_country) {
        this.billing_country = billing_country;
    }

    public String getBilling_city() {
        return billing_city;
    }

    public void setBilling_city(String billing_city) {
        this.billing_city = billing_city;
    }

    public String getBilling_phone2() {
        return billing_phone2;
    }

    public void setBilling_phone2(String billing_phone2) {
        this.billing_phone2 = billing_phone2;
    }

    public String getBilling_first_name() {
        return billing_first_name;
    }

    public void setBilling_first_name(String billing_first_name) {
        this.billing_first_name = billing_first_name;
    }

    public String getBilling_phone() {
        return billing_phone;
    }

    public void setBilling_phone(String billing_phone) {
        this.billing_phone = billing_phone;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Boolean getGift_option() {
        return gift_option;
    }

    public void setGift_option(Boolean gift_option) {
        this.gift_option = gift_option;
    }
    
    //
	private UFDate ts;
	
	
	public UFDate getTs() {
		return ts;
	}

	public void setTs(UFDate ts) {
		this.ts = ts;
	}
    
    @Override
    public String getTableName() {
    	// TODO Auto-generated method stub
    	return "DATA_LAZADA_BILL";
    }
    
    @Override
    public String getPrimaryKey() {
    	// TODO Auto-generated method stub
    	return id;
    }

	public UFDate getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(UFDate updated_at) {
		this.updated_at = updated_at;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public UFDate getLastUpdateTime() {
		return lastupdatetime;
	}

	public void setLastUpdateTime(UFDate lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	

}

