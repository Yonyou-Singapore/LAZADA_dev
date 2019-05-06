package nc.impl.so.restapi.jsonservice.vo.lazada.vo;
/**
 * 获取access_token request
 * @author: szw
 *
 */

import java.io.Serializable;

public class GenerateAccessTokenRequest implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	private String code;  //oauth代码，从app回调URL
	private String uuid; //特殊标识，拒绝回复
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
