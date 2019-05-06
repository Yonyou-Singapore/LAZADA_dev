package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;

public class Attributes implements Serializable {

	private static final long serialVersionUID = -4132456052998592129L;

	private String description;
	private String name;
	private String brand;
	private String short_description;
	private String warranty_type;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getWarranty_type() {
		return warranty_type;
	}

	public void setWarranty_type(String warranty_type) {
		this.warranty_type = warranty_type;
	}
}
