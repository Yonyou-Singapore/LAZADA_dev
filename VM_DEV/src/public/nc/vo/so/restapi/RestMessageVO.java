package nc.vo.so.restapi;

/**
 * Restful返回结果
 * @author weiningc
 */
public class RestMessageVO {
	
	//成功
	public static final String SUCESS = "200";
	
	//修改数据成功
	public static final String SUCCESSUPDATE_CODE = "201";
	
	//删除成功编码
	public static final String SUCCESSDELETE_CODE = "204";
	
	//失败
	public static final String FAILED = "400";
	
	//单据号重复编码
	public static final String FAILED_DUPLICATE = "401";
	
	//json格式错误
	public static final String FAILED_JSON = "406";
	
	//单据号重复信息
	public static final String MESSAGE_DUPLICATE = "is duplicate";
	
	//成功信息
	public static final String MESSAGE_SUCCESS = "Successfully: ";
	
	//失败信息
	public static final String MESSAGE_FAILED = "Failed: ";
	
	/**
	 * 单据号
	 */
	private String billno;

	/**
	 * 返回的单据号
	 */
	private String returncode;

	/**
	 * 信息描述
	 */
	private String description;
	
	public RestMessageVO() {
		this.billno = billno;
		this.returncode = returncode;
		this.description = description;
	}
	public RestMessageVO(String billno, String returncode, String description) {
		this.billno = billno;
		this.returncode = returncode;
		this.description = description;
	}
	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	


}
