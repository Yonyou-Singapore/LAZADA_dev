package nc.impl.so.restapi.jsonservice.vo.taobao.util;

public class MCloudRequest {
	/**
	 * 鎵�睘骞冲彴
	 */
	private String plat;
	
	private String appKey="u8key";
	
	private String appSecret="u8secret";
	
	private String session;

	private String appId;
	private String clientId;//鑱氱編鍟嗗id
	
	private String isRds = "false";

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * 鏁版嵁閫氭帴鍙ｆ柟娉曞悕
	 */
	private String method;
	/**
	 * 鍙傛暟鏍煎紡 锛坖son 鎴�xml , 榛樿json锛�
	 */
	private String format = "json";
	/**
	 * 璁块棶鏍囪瘑 锛堟湇鍔″晢鎺ュ彛璋冪敤锛�锛�鍟嗗鑷爺鎺ュ彛璋冪敤锛�锛�
	 */
	private String access;
	/**
	 * 璇锋眰搴旂敤鍙傛暟
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
