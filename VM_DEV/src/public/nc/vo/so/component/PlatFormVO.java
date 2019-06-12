package nc.vo.so.component;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;

public class PlatFormVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String platformname;
	private UFBoolean choose;
	private String platformcode;
	
	public static final String PLATFORM = "platform";
	public static final String TMALL = "tmall";
	public static final String LAZADA = "lazada";
	
	public UFBoolean getChoose() {
		return choose;
	}
	public void setChoose(UFBoolean choose) {
		this.choose = choose;
	}
	public String getPlatformname() {
		return platformname;
	}
	public void setPlatformname(String platformname) {
		this.platformname = platformname;
	}
	public String getPlatformcode() {
		return platformcode;
	}
	public void setPlatformcode(String platformcode) {
		this.platformcode = platformcode;
	}
	
}
