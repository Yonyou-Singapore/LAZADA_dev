package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;

/**
 * 刷新授权request
 * @author: szw
 *
 */
public class RefreshAccessTokenRequest implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -3515263306918146358L;
	
	private String refresh_token;

	public String getRefresh_token() {
		return refresh_token;
	}
	
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
}
