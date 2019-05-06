package nc.vo.so.restapi;

/**
 * Restful���ؽ��
 * @author weiningc
 */
public class RestMessageVO {
	
	//�ɹ�
	public static final String SUCESS = "200";
	
	//�޸����ݳɹ�
	public static final String SUCCESSUPDATE_CODE = "201";
	
	//ɾ���ɹ�����
	public static final String SUCCESSDELETE_CODE = "204";
	
	//ʧ��
	public static final String FAILED = "400";
	
	//���ݺ��ظ�����
	public static final String FAILED_DUPLICATE = "401";
	
	//json��ʽ����
	public static final String FAILED_JSON = "406";
	
	//���ݺ��ظ���Ϣ
	public static final String MESSAGE_DUPLICATE = "is duplicate";
	
	//�ɹ���Ϣ
	public static final String MESSAGE_SUCCESS = "Successfully: ";
	
	//ʧ����Ϣ
	public static final String MESSAGE_FAILED = "Failed: ";
	
	/**
	 * ���ݺ�
	 */
	private String billno;

	/**
	 * ���صĵ��ݺ�
	 */
	private String returncode;

	/**
	 * ��Ϣ����
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
