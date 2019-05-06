package nc.vo.so.restapi.restlog;

import java.io.Serializable;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;

/**
 * ×öÎªrestLog
 * @author weiningc
 *
 */
public class RestLogVO extends SuperVO implements Serializable {
	
	//json  content
	private String json;
	
	//module code
	private String modulecode;
	//
	private String classname;
	//
	private String methodname;
	//
	private UFDate ts;
	
	private String usercode;
	//
	private String IP;
	//bill NO.
	private String billcode;
	
	//NIBS OR EKWEB
	private String srcsystem;
	
	//is success
	private String successflag;
	
	//PK
	private String pk_jsonlog;
	
	//result
	private String returncode;
	
	//result
	private String returnmsg;
	
	private String url;

	public RestLogVO() {
		super();
	}

	public String getJson() {
		return json;
	}

	public void setJson(String jsonstring) {
		this.json = jsonstring;
	}

	public String getModulecode() {
		return modulecode;
	}

	public void setModulecode(String modulecode) {
		this.modulecode = modulecode;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getMethodname() {
		return methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	public UFDate getTs() {
		return ts;
	}

	public void setTs(UFDate ts) {
		this.ts = ts;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public String getSrcsystem() {
		return srcsystem;
	}

	public void setSrcsystem(String srcsystem) {
		this.srcsystem = srcsystem;
	}

	public String getPk_jsonlog() {
		return pk_jsonlog;
	}

	public void setPk_jsonlog(String pk_jsonlog) {
		this.pk_jsonlog = pk_jsonlog;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getSuccessflag() {
		return successflag;
	}

	public void setSuccessflag(String successflag) {
		this.successflag = successflag;
	}
	

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getReturnmsg() {
		return returnmsg;
	}

	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}
	
	@Override
	public String getTableName() {
		return "bd_restlog";
	}
	
	public String getPrimaryKey(){
		return pk_jsonlog;
	}
	
	public String getPKFieldName(){
		return "pk_jsonlog";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
