package nc.vo.so.restapi;

import java.util.Vector;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.restapi.jsonservice.IJsonServiceForRestful;
import nc.vo.pub.lang.UFDate;
import nc.vo.so.restapi.restlog.RestLogVO;

/**
 * 
 * @author weiningc
 *
 */
public class RestlogUtils {
	public static void porcessRestLogForSave(String moduleCode, String methodName, String ClassName, String requestJson, String responseJson) {
		Vector<RestLogVO> logvos = new Vector<RestLogVO>();
		RestLogVO logvo = new RestLogVO();
		logvo.setClassname(ClassName);
		logvo.setMethodname(methodName);
		logvo.setModulecode(moduleCode);
		logvo.setJson(requestJson);
		logvo.setReturnmsg(responseJson);
		logvo.setTs(new UFDate());
		logvo.setUsercode(InvocationInfoProxy.getInstance().getUserCode());
		logvo.setIP(InvocationInfoProxy.getInstance().getClientHost());
		logvo.setSuccessflag("May be");
		logvos.add(logvo);
		//save
		NCLocator.getInstance().lookup(IJsonServiceForRestful.class).insertJsonInfoAndOther(logvos.toArray(new RestLogVO[0]));
	}

	public static void porcessRestLogForSave(String moduleCode, String methodName, String ClassName, String str) {
		if(str == null || "".equals(str)) {
			return;
		}
		Vector<RestLogVO> logvos = new Vector<RestLogVO>();
		RestLogVO logvo = new RestLogVO();
		logvo.setClassname(ClassName);
		logvo.setMethodname(methodName);
		logvo.setModulecode(moduleCode);
		logvo.setJson(str);
		logvo.setTs(new UFDate());
		logvo.setUsercode(InvocationInfoProxy.getInstance().getUserCode());
		logvo.setIP(InvocationInfoProxy.getInstance().getClientHost());
		logvo.setSuccessflag("SEND JSON");
		logvos.add(logvo);
		//±£´æ
		NCLocator.getInstance().lookup(IJsonServiceForRestful.class).insertJsonInfoAndOther(logvos.toArray(new RestLogVO[0]));
	}
	

	public static void porcessRestLogForSave(RestLogVO logvo) {
		if(logvo == null || logvo.getJson() == null) {
			return;
		}
		logvo.setTs(new UFDate());
		logvo.setUsercode(InvocationInfoProxy.getInstance().getUserCode());
		logvo.setIP(InvocationInfoProxy.getInstance().getClientHost());
		logvo.setSuccessflag("SEND JSON");
		//±£´æ
		NCLocator.getInstance().lookup(IJsonServiceForRestful.class).insertJsonInfoAndOther(new RestLogVO[]{logvo});
	}
}
