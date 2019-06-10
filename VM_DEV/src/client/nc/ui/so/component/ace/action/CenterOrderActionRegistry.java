package nc.ui.so.component.ace.action;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.KeyStroke;

import nc.ui.scmpub.action.SCMActionRegistry;
import nc.ui.uif2.actions.ActionInfo;
import nc.ui.uif2.actions.ActionRegistry;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.scmpub.res.SCMActionCode;

public class CenterOrderActionRegistry {
	private static Map<String, ActionInfo> actionMap =
		      new HashMap<String, ActionInfo>();

		  private static ActionInfo[] infos =
		      new ActionInfo[] {
		        new ActionInfo(CenterOrderMenuCode.DOWNLOADORDER, "menucode",
		            "ordercentermenu_001"/* @res "����ԭ��" */,
		            "ordercentermenu_002"/* @res "����ԭ��(ALT+D)" */, null,
		            KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK)),
		        new ActionInfo(CenterOrderMenuCode.READYTOSHIP, "menucode",
		            "ordercentermenu_003"/* @res "����" */,
		            "ordercentermenu_004"/* @res "����(ALT+F)" */, null,
		            KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK)),
	            new ActionInfo(CenterOrderMenuCode.UPDATETODELIVERED, "menucode",
			            "ordercentermenu_005"/* @res "���µ���״̬" */,
			            "ordercentermenu_006"/* @res "���µ���״̬(ALT+U)" */, null,
			            KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_MASK))
		  };
		  static {
			    for (ActionInfo info : CenterOrderActionRegistry.infos) {
			    	CenterOrderActionRegistry.actionMap.put(info.getCode(), info);
			    }
			  }

		public static ActionInfo getActionInfo(String actionCode) {
			if (StringUtil.isEmptyWithTrim(actionCode)) {
			      return null;
			    }
			    ActionInfo info = ActionRegistry.getActionInfo(actionCode);
			    if (null == info) {
			      info = CenterOrderActionRegistry.actionMap.get(actionCode);
			    }
			    return info;
		}
}
