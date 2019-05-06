package nc.impl.so.restapi.jsonservice.vo.lazada.vo;
/**
 * 生成access_token response
 * @author: szw
 *
 */

import java.io.Serializable;
import java.util.List;

public class GenerateAccessTokenResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer expires_in; //access token(获得API接口信任)的截止日期，精确到秒
	private String account_id; //账户名，允许为空。//if(account_platform=seller_center账户平台为卖方中心) account_id=null
	private String country;    //国家ID(sg:新加坡，my:马来西亚，ph:菲律宾，th:泰国，id:印尼， vn:越南)
	private List<CountryUserInfo> country_user_info;
	private String account_platform;  //账户平台
	private String access_token; //访问令牌
	private String account;  //用户商户（登陆的账号）
	private String refresh_expires_in; //更新令牌的到期日期
	private String refresh_token; //更新令牌，当“refresh_expires_in”>0时，用于更新令牌。
	
	
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<CountryUserInfo> getCountry_user_info() {
		return country_user_info;
	}
	public void setCountry_user_info(List<CountryUserInfo> country_user_info) {
		this.country_user_info = country_user_info;
	}
	public String getAccount_platform() {
		return account_platform;
	}
	public void setAccount_platform(String account_platform) {
		this.account_platform = account_platform;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRefresh_expires_in() {
		return refresh_expires_in;
	}
	public void setRefresh_expires_in(String refresh_expires_in) {
		this.refresh_expires_in = refresh_expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
	
}
