package nc.ui.so.component.ace.billref;

import java.awt.DefaultKeyboardFocusManager;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;

import nc.ui.pub.beans.UIDialog;

/**
 * <p>Title: Smart Client</p>
 * <p>Description: �Ի�����࣬����Smart Client��Ŀ��ضԻ���Ҫ�̳��ڸ���</p>
 * <p>Copyright 2007 ufgov, Inc.</p>
 * <p>Company: ufgov</p>
 * <p>����ʱ�䣺2007-8-3</p>
 * @author ����ΰ(manlge)
 * @version 1.0
 */
public class JBaseDialog extends UIDialog {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1703031225368495904L;

public JBaseDialog() {
    super(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager()
      .getActiveWindow());
  }

  public JBaseDialog(Frame owner) {
    super(owner);
  }


  public JBaseDialog(Frame owner, String title) {
    super(owner, title);
  }




  public JBaseDialog(Dialog owner) {
    super(owner);
  }



  public JBaseDialog(Dialog owner, String title) {
    super(owner, title);
  }



  public JBaseDialog(Window owner) {
    super(owner);
  }


  public JBaseDialog(Window owner, String title) {
    super(owner, title);
  }


  public void moveToScreenCenter() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    setLocation((screenSize.width - frameSize.width) / 2,
      (screenSize.height - frameSize.height) / 2);
  }

  @Override
  public void setVisible(boolean b) {
    super.setVisible(b); //���ø��෽��
  }
}
