package nc.ui.so.component.ace.billref;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * <p>Title: Smart Client</p>
 * <p>Description: 带“确定”、“取消”按钮的简单对话框，主要用来继承</p>
 * <p>Copyright: Copyright 2007 ufgov, Inc.</p>
 * <p>Company: ufgov</p>
 * <p>创建时间: 2007-8-17</p>
 * @author 刘永伟(manlge)
 * @version 1.0
 */
public abstract class JOKCancelDialog extends JBaseDialog {
  private static final long serialVersionUID = -2109976048432306784L;

  /**
   * 确定按钮
   */
  protected JButton okButton = new JButton("OK(O)");

  /**
   * 取消按钮
   */
  protected JButton cancelButton = new JButton("Cancel");

  /**
   * 底部面板
   */
  protected JPanel buttonPanel = new JPanel();

  /**
   * 客户区面板
   */
  protected JPanel clientPanel = new JPanel(new BorderLayout());

  /**
   * 组件是否已初始化
   */
  private boolean componentInited = false;

  /**
   * 对话框返回值
   */
  private boolean retValue = false;

  public JOKCancelDialog() {
    super();
    init();
  }

  public JOKCancelDialog(Frame owner) {
    super(owner);
    init();
  }



  public JOKCancelDialog(Frame owner, String title) {
    super(owner, title);
    init();
  }

 

  public JOKCancelDialog(Dialog owner) {
    super(owner);
  }


  public JOKCancelDialog(Dialog owner, String title) {
    super(owner, title);
  }


  public JOKCancelDialog(Window owner) {
    super(owner);
    init();
  }



  public JOKCancelDialog(Window owner, String title) {
    super(owner, title);
    init();
  }

 


  /**
   * 执行初始化
   */
  protected void init() {

    //模式对话框
    setModal(true);

    //不能改变大小
    setResizable(false);

    Container c = getContentPane();
    //设置布局管理器
    c.setLayout(new BorderLayout());

    //放到底部
    c.add(buttonPanel, BorderLayout.SOUTH);

    //占满整个客户区
    c.add(clientPanel, BorderLayout.CENTER);

    //添加按钮到buttonPanel
    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 12));
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);
//    Color col=new Color(153, 186, 243);
//    buttonPanel.setBackground(col);

    //为关闭按钮添加关闭代码

    Action cancelAction = new AbstractAction("取消") {

      private static final long serialVersionUID = 3899490957061678029L;

      @Override
      public void actionPerformed(ActionEvent e) {
        cancel();
      }
    };
    cancelButton.setAction(cancelAction);

    //添加按下ESC执行取消快捷键
    cancelButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
      KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true), "actionCancel");
    cancelButton.getActionMap().put("actionCancel", cancelAction);

    Action okAction = new AbstractAction("OK") {

      private static final long serialVersionUID = -3312607898831589769L;

      @Override
      public void actionPerformed(ActionEvent e) {
        doOK();
      }
    };

    okButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "actionOK");
    okButton.getActionMap().put("actionOK", okAction);
    okButton.setAction(okAction);
    okButton.setText("OK(O)");
    okButton.setMnemonic('O');
    cancelButton.setText("Cancel(C)");
    cancelButton.setMnemonic('C');
  }

  /**
   * 初始化组件，方法由子类实现，用于添加相应的初始化代码
   * @param c Container 容器，用于摆放用户创建的组件
   */
  protected abstract void initComponent(Container c);

  /**
   * 执行
   * @return boolean
   */
  public boolean execute() {
    if (!componentInited) {
      componentInited = true;
      initComponent(clientPanel);
      initialized();
    }
    setLocationRelativeTo(null);
    setVisible(true);
    return retValue;
  }

  /**
   * 已经初始化，由子类覆盖可做些处理，如选定
   */
  protected void initialized() {
  }

  public void cancel() {
    retValue = false;
    this.setVisible(false);
  }

  protected void doOK() {
    retValue = true;
    this.setVisible(false);
  }

  public void setOkButtonText(String text) {
    okButton.setText(text);
  }

  public void setCancelButtonText(String text) {
    cancelButton.setText(text);
  }
}
