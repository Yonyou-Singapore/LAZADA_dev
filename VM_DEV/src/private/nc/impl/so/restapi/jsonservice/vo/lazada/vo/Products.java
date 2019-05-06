package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;
import java.util.List;

public class Products implements Serializable {
	private static final long serialVersionUID = 499556810526751433L;

	private Integer primary_category;
	private Attributes attributes;
	private List<GetProductsSkus> skus;
	private Integer item_id;

	public Integer getPrimary_category() {
		return primary_category;
	}

	public void setPrimary_category(Integer primary_category) {
		this.primary_category = primary_category;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public List<GetProductsSkus> getSkus() {
		return skus;
	}

	public void setSkus(List<GetProductsSkus> skus) {
		this.skus = skus;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
}
