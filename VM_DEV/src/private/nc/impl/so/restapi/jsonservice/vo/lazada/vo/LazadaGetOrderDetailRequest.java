package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;

public class LazadaGetOrderDetailRequest implements Serializable {

	private static final long serialVersionUID = -3986923688026334030L;
	//销售中心分配的订单编码
	private String order_id;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	

}
