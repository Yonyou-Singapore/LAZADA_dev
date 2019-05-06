package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;

public class GetProductsSkus implements Serializable {
	private static final long serialVersionUID = -857506997315691984L;

	private String Status;
	private String SkuId;
	private String quantity;
	private String product_weight;
	private String[] Images;
	private String SellerSku;
	private String ShopSku;
	private String Url;

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getSkuId() {
		return SkuId;
	}

	public void setSkuId(String skuId) {
		SkuId = skuId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProduct_weight() {
		return product_weight;
	}

	public void setProduct_weight(String product_weight) {
		this.product_weight = product_weight;
	}

	public String[] getImages() {
		return Images;
	}

	public void setImages(String[] images) {
		Images = images;
	}

	public String getSellerSku() {
		return SellerSku;
	}

	public void setSellerSku(String sellerSku) {
		SellerSku = sellerSku;
	}

	public String getShopSku() {
		return ShopSku;
	}

	public void setShopSku(String shopSku) {
		ShopSku = shopSku;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
