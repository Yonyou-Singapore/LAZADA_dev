package nc.impl.so.restapi.jsonservice.vo.lazada.vo;
/**
 * 
 * @author: szw
 *
 */
public class CountryUserInfo {
	
	private String country; //国家ID(sg:新加坡，my:马来西亚，ph:菲律宾，th:泰国，id:印尼， vn:越南)
	private String seller_id;//卖方ID,它在每个国家是不同的
	private String user_id; //用户ID,它在每个国家是不同的
	private String short_code;//由Lazada卖方中心分配给每个卖方ID的短代码，对于整个平台是独有的
	
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getShort_code() {
		return short_code;
	}
	public void setShort_code(String short_code) {
		this.short_code = short_code;
	}

	
	
}
