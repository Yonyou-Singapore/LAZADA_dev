package nc.vo.so.component;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;

public class PlatFormVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String platform;
	private UFBoolean choose;
	
	public static final String PLATFORM = "platform";
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public UFBoolean getChoose() {
		return choose;
	}
	public void setChoose(UFBoolean choose) {
		this.choose = choose;
	}
	
}
