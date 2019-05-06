package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.util.List;

public class GetProductsResponse {
	private Integer total_products;
	private List<Products> products;

	public Integer getTotal_products() {
		return total_products;
	}

	public void setTotal_products(Integer total_products) {
		this.total_products = total_products;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}
}
