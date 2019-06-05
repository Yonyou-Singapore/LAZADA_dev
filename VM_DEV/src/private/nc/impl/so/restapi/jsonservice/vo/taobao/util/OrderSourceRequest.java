package nc.impl.so.restapi.jsonservice.vo.taobao.util;


public class OrderSourceRequest {
//鎸夌収鎷嶄笅鏃堕棿涓嬭浇
	private String fields;
	//鏌ヨ涓変釜鏈堝唴浜ゆ槗鍒涘缓鏃堕棿寮�銆傛牸寮�yyyy-MM-dd HH:mm:ss
	private String startCreated;
	//鏌ヨ浜ゆ槗鍒涘缓鏃堕棿缁撴潫銆�
	private String endCreated;
	//浜ゆ槗鐘舵�
	private String status;
	//涔板鏄电О
	private String buyerNick;
	/**
	 * 浜ゆ槗绫诲瀷鍒楄〃锛屽悓鏃舵煡璇㈠绉嶄氦鏄撶被鍨嬪彲鐢ㄩ�鍙峰垎闅斻�
	 * 榛樿鍚屾椂鏌ヨguarantee_trade,auto_delivery,ec,cod,step 杩� 绉嶇殑浜ゆ槗绫诲瀷鐨勬暟鎹紱
	 * 鏌ヨ鎵�湁浜ゆ槗绫诲瀷鐨勬暟鎹紝闇�璁剧疆涓嬮潰鍏ㄩ儴鍙�鍊笺�
	 */

	private String type;
	/*
	鍙�鍊兼湁ershou(浜屾墜甯傚満鐨勮鍗曪級,service锛堝晢鍩庢湇鍔″瓙璁㈠崟锛�
	mark锛堝弻鍗佷竴澶т績娲诲姩寮傚父璁㈠崟锛変綔涓烘墿灞曠被鍨嬬瓫閫夊彧鑳藉仛鍗曚釜ext_type鏌ヨ锛�
	涓嶈兘鍏ㄩ儴鏌ヨ鎵�湁鐨別xt_type绫诲瀷
	 */
	private String extType;
	//璇勪环鐘舵�
	private String rateStatus;
	
	private Long pageNo;
	
	private Long pageSize;
	
	private Boolean useHasNext;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getStartCreated() {
		return startCreated;
	}

	public void setStartCreated(String startCreated) {
		this.startCreated = startCreated;
	}

	public String getEndCreated() {
		return endCreated;
	}

	public void setEndCreated(String endCreated) {
		this.endCreated = endCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtType() {
		return extType;
	}

	public void setExtType(String extType) {
		this.extType = extType;
	}

	public String getRateStatus() {
		return rateStatus;
	}

	public void setRateStatus(String rateStatus) {
		this.rateStatus = rateStatus;
	}

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Boolean getUseHasNext() {
		return useHasNext;
	}

	public void setUseHasNext(Boolean useHasNext) {
		this.useHasNext = useHasNext;
	}
	
	
} 