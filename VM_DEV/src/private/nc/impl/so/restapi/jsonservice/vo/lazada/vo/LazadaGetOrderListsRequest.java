package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;

public class LazadaGetOrderListsRequest implements Serializable {

	private static final long serialVersionUID = 3639613730679052858L;

	private String created_before; //限制退回订单在指定日期或指定日期之前更新。使用ISO8601日期格式。可选。
	private String created_after; //限制退回订单在指定日期或指定日期之后更新。使用ISO8601日期格式。UpdatedAfter（更新于某日期之后）和CreatedAfter（创建于某日期之后）二者选一必填。
	/**
	 * //当设置时，将返回的订单集限制为松散的订单，只返回符合所提供状态的条目。可取值为：unpaid（未付款）,
	 * unverified（未核实）, pending（待审批）, canceled（取消）,
	 * ready_to_ship（准备运输）, delivered（已配送）, returned（退回）, shipped（已经运达） and failed（失败）.
	 */
	private String status;
	private String update_before; //限制退回订单在指定日期或指定日期之前更新。使用ISO8601日期格式。可选。
	private String sort_direction;  //指定排序类型，可取值为ASC（升序）和DESC（降序）.
	private String offset; //在列表开始时跳过的订单数量。
	private String limit ; //可退回的最大订单数量。支持的最大订单数量为100。
	private String update_after; //限制退回订单在指定日期或指定日期之后更新。使用ISO8601日期格式。UpdatedAfter（更新于某日期之后） 和CreatedAfter（创建于某日期之后）二者选一必填。
	private String sort_by; //允许选择分类方式。可取值为created_at （创建日期）和updated_at（更新日期）.

	public String getCreated_before() {
		return created_before;
	}

	public void setCreated_before(String created_before) {
		this.created_before = created_before;
	}

	public String getCreated_after() {
		return created_after;
	}

	public void setCreated_after(String created_after) {
		this.created_after = created_after;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdate_before() {
		return update_before;
	}

	public void setUpdate_before(String update_before) {
		this.update_before = update_before;
	}

	public String getSort_direction() {
		return sort_direction;
	}

	public void setSort_direction(String sort_direction) {
		this.sort_direction = sort_direction;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getUpdate_after() {
		return update_after;
	}

	public void setUpdate_after(String update_after) {
		this.update_after = update_after;
	}

	public String getSort_by() {
		return sort_by;
	}

	public void setSort_by(String sort_by) {
		this.sort_by = sort_by;
	}
}
