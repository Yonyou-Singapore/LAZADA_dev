package nc.impl.so.restapi.jsonservice.vo.taobao.util;

public class MCloudRequest {
	/**
	 * 所属平台
	 */
	private String plat;
	
	private String appKey="u8key";
	
	private String appSecret="u8secret";
	
	private String session;

	private String appId;
	private String clientId;//聚美商家id
	
	private String isRds = "false";

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * 数据通接口方法名
	 */
	private String method;
	/**
	 * 参数格式 （json 或 xml , 默认json）
	 */
	private String format = "json";
	/**
	 * 访问标识 （服务商接口调用：1； 商家自研接口调用：2）
	 */
	private String access;
	/**
	 * 请求应用参数
	 */
	private Object request;

	private String timestamp;
	
	public MCloudRequest() {
	}
	
	public MCloudRequest(String plat) {
		this.plat = plat;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getIsRds() {
		return isRds;
	}

	public void setIsRds(String isRds) {
		this.isRds = isRds;
	}
}
