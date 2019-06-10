package nc.ui.so.component.ace.action;

import javax.swing.Action;

import nc.ui.scmpub.action.SCMActionRegistry;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.ActionInfo;

/**
 * 按钮初始化
 * @author weiningc
 *
 */
public class CenterOrderMenuInit {
	/**
	   * 初始化按钮
	   * 
	   * @param action 要初始化的按钮
	   * @param actionCode 按钮编码
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
