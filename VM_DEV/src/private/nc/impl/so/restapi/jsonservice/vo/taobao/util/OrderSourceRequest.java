package nc.impl.so.restapi.jsonservice.vo.taobao.util;


public class OrderSourceRequest {
	//按照拍下时间下载
		private String fields;
		//查询三个月内交易创建时间开始。格式:yyyy-MM-dd HH:mm:ss
		private String startCreated;
		//查询交易创建时间结束。
		private String endCreated;
		//交易状态
		private String status;
		//买家昵称
		private String buyerNick;
		/**
		 * 交易类型列表，同时查询多种交易类型可用逗号分隔。
		 * 默认同时查询guarantee_trade,auto_delivery,ec,cod,step 这5 种的交易类型的数据；
		 * 查询所有交易类型的数据，需要设置下面全部可选值。
		 */

		private String type;
		/*
		可选值有ershou(二手市场的订单）,service（商城服务子订单）
		mark（双十一大促活动异常订单）作为扩展类型筛选只能做单个ext_type查询，
		不能全部查询所有的ext_type类型
		 */
		private String extType;
		//评价状态
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