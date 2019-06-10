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
 * <p>Description: ����ȷ��������ȡ������ť�ļ򵥶Ի�����Ҫ�����̳�</p>
 * <p>Copyright: Copyright 2007 ufgov, Inc.</p>
 * <p>Company: ufgov</p>
 * <p>����ʱ��: 2007-8-17</p>
 * @author ����ΰ(manlge)
 * @version 1.0
 */
public abstract class JOKCancelDialog extends JBaseDialog {
  private static final long serialVersionUID = -2109976048432306784L;

  /**
   * ȷ����ť
   */
  protected JButton okButton = new JButton("OK(O)");

  /**
   * ȡ����ť
   */
  protected JButton cancelButton = new JButton("Cancel");

  /**
   * �ײ����
   */
  protected JPanel buttonPanel = new JPanel();

  /**
   * �ͻ������
   */
  protected JPanel clientPanel = new JPanel(new BorderLayout());

  /**
   * ����Ƿ��ѳ�ʼ��
   */
  private boolean componentInited = false;

  /**
   * �Ի��򷵻�ֵ
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
   * ִ�г�ʼ��
   */
  protected void init() {

    //ģʽ�Ի���
    setModal(true);

    //���ܸı��С
    setResizable(false);

    Container c = getContentPane();
    //���ò��ֹ�����
    c.setLayout(new BorderLayout());

    //�ŵ��ײ�
    c.add(buttonPanel, BorderLayout.SOUTH);

    //ռ�������ͻ���
    c.add(clientPanel, BorderLayout.CENTER);

    //��Ӱ�ť��buttonPanel
    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 12));
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);
//    Color col=new Color(153, 186, 243);
//    buttonPanel.setBackground(col);

    //Ϊ�رհ�ť��ӹرմ���

    Action cancelAction = new AbstractAction("ȡ��") {

      private static final long serialVersionUID = 3899490957061678029L;

      @Override
      public void actionPerformed(ActionEvent e) {
        cancel();
      }
    };
    cancelButton.setAction(cancelAction);

    //��Ӱ���ESCִ��ȡ����ݼ�
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
   * ��ʼ�����������������ʵ�֣����������Ӧ�ĳ�ʼ������
   * @param c Container ���������ڰڷ��û����������
   */
  protected abstract void initComponent(Container c);

  /**
   * ִ��
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
   * �Ѿ���ʼ���������า�ǿ���Щ������ѡ��
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
