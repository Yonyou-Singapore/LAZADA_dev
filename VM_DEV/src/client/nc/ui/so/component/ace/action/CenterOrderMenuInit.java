package nc.ui.so.component.ace.action;

import javax.swing.Action;

import nc.ui.scmpub.action.SCMActionRegistry;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.ActionInfo;

/**
 * ��ť��ʼ��
 * @author weiningc
 *
 */
public class CenterOrderMenuInit {
	/**
	   * ��ʼ����ť
	   * 
	   * @param action Ҫ��ʼ���İ�ť
	   * @param actionCode ��ť����
	   */
	  public static void initializeAction(NCAction action, String actionCode) {
	    ActionInfo info = CenterOrderActionRegistry.getActionInfo(actionCode);
	    action.setBtnName(info.getName());
	    action.setCode(info.getCode());
	    action.putValue(Action.ACCELERATOR_KEY, info.getKeyStroke());
	    action.putValue(Action.SHORT_DESCRIPTION, info.getShort_description());
	    action.putValue(Action.SMALL_ICON, info.getIcon());
	  }
}
