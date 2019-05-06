package nc.vo.so.restapi;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * ���ڲ�ѯԭ��
 * @author weiningc
 *
 */
public class LazadaViewVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pk_org;
	private String dbilldate;
	private String vtrantypecode;
	private String corigcurrencyid;
	private String country;
	private String orderid;
	private String fullname;
	private String shippingphone;
	private String fulladdress;
	private String shippingtype;
	private String shippingstatus;
	
	private String sku;
	private UFDouble nqtunitnum; //����
	private UFDouble norigtaxmny;//��˰�ϼ�
	private UFDouble norigmny;//��˰���   = ��˰�ϼ�
	private UFDouble nqtorigprice;//��˰����  
	private UFDouble nqtorigtaxprice; //��˰���� = ��˰����  
	private UFDouble norigdiscount; //�ۿ۶�
	private UFBoolean blargessflag; //N
	private String order_item_id;
	private String shipment_provider;
	private String tracking_code;
	
	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getDbilldate() {
		return dbilldate;
	}

	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}

	public String getVtrantypecode() {
		return vtrantypecode;
	}

	public void setVtrantypecode(String vtrantypecode) {
		this.vtrantypecode = vtrantypecode;
	}

	public String getCorigcurrencyid() {
		return corigcurrencyid;
	}

	public void setCorigcurrencyid(String corigcurrencyid) {
		this.corigcurrencyid = corigcurrencyid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getShippingphone() {
		return shippingphone;
	}

	public void setShippingphone(String shippingphone) {
		this.shippingphone = shippingphone;
	}

	public String getFulladdress() {
		return fulladdress;
	}

	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}

	public String getShippingtype() {
		return shippingtype;
	}

	public void setShippingtype(String shippingtype) {
		this.shippingtype = shippingtype;
	}

	public String getShippingstatus() {
		return shippingstatus;
	}

	public void setShippingstatus(String shippingstatus) {
		this.shippingstatus = shippingstatus;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public UFDouble getNqtunitnum() {
		return nqtunitnum;
	}

	public void setNqtunitnum(UFDouble nqtunitnum) {
		this.nqtunitnum = nqtunitnum;
	}

	public UFDouble getNorigtaxmny() {
		return norigtaxmny;
	}

	public void setNorigtaxmny(UFDouble norigtaxmny) {
		this.norigtaxmny = norigtaxmny;
	}

	public UFDouble getNorigmny() {
		return norigmny;
	}

	public void setNorigmny(UFDouble norigmny) {
		this.norigmny = norigmny;
	}

	public UFDouble getNqtorigprice() {
		return nqtorigprice;
	}

	public void setNqtorigprice(UFDouble nqtorigprice) {
		this.nqtorigprice = nqtorigprice;
	}

	public UFDouble getNqtorigtaxprice() {
		return nqtorigtaxprice;
	}

	public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
		this.nqtorigtaxprice = nqtorigtaxprice;
	}

	public String getOrder_item_id() {
		return order_item_id;
	}

	public void setOrder_item_id(String order_item_id) {
		this.order_item_id = order_item_id;
	}

	public String getShipment_provider() {
		return shipment_provider;
	}

	public void setShipment_provider(String shipment_provider) {
		this.shipment_provider = shipment_provider;
	}

	public String getTracking_code() {
		return tracking_code;
	}

	public void setTracking_code(String tracking_code) {
		this.tracking_code = tracking_code;
	}
	

	public UFDouble getNorigdiscount() {
		return norigdiscount;
	}

	public void setNorigdiscount(UFDouble norigdiscount) {
		this.norigdiscount = norigdiscount;
	}

	public UFBoolean getBlargessflag() {
		return blargessflag;
	}

	public void setBlargessflag(UFBoolean blargessflag) {
		this.blargessflag = blargessflag;
	}

	@Override
	public String getTableName() {
		return "(select * from ( select /*��������*/" +
				" h.orgid pk_org, /*pk_org*/" +
				" h.created_at dbilldate," + 
       " '30-Cxx-02' vtrantypecode, /*vtrantypecode*/" + 
       " b.currency corigcurrencyid,/*currecy*/" + 
       " h.billing_country country," + 
       " decode(h.statuses, 'returned', h.order_id || '-R', h.order_id) orderid," + 
       " h.shipping_last_name || ' ' || h.shipping_first_name fullname," + 
       " h.shipping_phone shippingphone," + 
       " h.shipping_address1 || h.shipping_address2 || h.shipping_address4 fulladdress," + 
       " b.shipping_type shippingtype," + 
       " h.statuses shippingstatus," + 
       " /*�ֱ�����*/" + 
      " b.sku, " + 
       " b.qty nqtunitnum," + 
       " b.item_price norigtaxmny," + 
       " b.item_price norigmny," + 
      "  b.item_price - b.voucher_seller nqtorigprice," + 
       " b.item_price - b.voucher_seller nqtorigtaxprice," + 
      " b.order_item_id order_item_id," + 
       " b.shipment_provider shipment_provider," + 
      " b.tracking_code tracking_code," + 
       " b.voucher_seller norigdiscount/*�ۿ۶�*/," +
      " h.gift_option blargessflag" +
  " from DATA_LAZADA_BILLITEM b" + 
 " inner join DATA_LAZADA_BILL h" + 
    " on h.order_id = b.order_id" + 
   " ) sbb" +
   " WHERE 1 = 1 and not exists (select soh.vdef2 from so_saleorder soh where soh.vdef2 = sbb.orderid and soh.dr=0))sb";
	}
	
	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return super.getPrimaryKey();
	}
}
