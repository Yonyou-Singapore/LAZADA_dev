package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;

public class GetProductsRequest  implements Serializable {
	private static final long serialVersionUID = -7983971760412017607L;
	/**
	 * 返回具有匹配该参数的状态的产品。可能的值包括：
	 * 所有，实时的，不活跃的，删除的，图像丢失的，
	 * 待审批的，被拒绝的，卖光的
	 */
	private String filter;
	private String update_before;
	private String search;
	private String create_before;
	private String offset;
	private String create_after;
	private String update_after;
	private String limit;
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getUpdate_before() {
		return update_before;
	}
	public void setUpdate_before(String update_before) {
		this.update_before = update_before;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getCreate_before() {
		return create_before;
	}
	public void setCreate_before(String create_before) {
		this.create_before = create_before;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getCreate_after() {
		return create_after;
	}
	public void setCreate_after(String create_after) {
		this.create_after = create_after;
	}
	public String getUpdate_after() {
		return update_after;
	}
	public void setUpdate_after(String update_after) {
		this.update_after = update_after;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getSku_seller_list() {
		return sku_seller_list;
	}
	public void setSku_seller_list(String sku_seller_list) {
		this.sku_seller_list = sku_seller_list;
	}
	private String options;
	private String sku_seller_list;
	
}
