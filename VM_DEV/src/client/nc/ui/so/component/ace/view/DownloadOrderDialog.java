package nc.ui.so.component.ace.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.bd.ref.IRefConst;
import nc.ui.org.ref.SaleorgDefaultRefModel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pub.bill.IBillItem;
import nc.ui.so.component.ace.billref.JOKCancelDialog;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.component.PlatFormVO;

public class DownloadOrderDialog extends JOKCancelDialog {
	private UIRefPane startdatepanel;// 开始日期
	private UIRefPane enddatepanel;// 结束日期
	private BillScrollPane buplatformpanel;// 平台

	private UIRefPane pk_orgpanel;// 组织
	private nc.ui.pub.bill.BillModel plafformmodel = null;

	public static String[] saBodyColName = new String[] {
			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("menucode",
					"ordercentermenu_007")/* @res "选择" */,
			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("menucode",
					"ordercentermenu_008") /* @res "平台" */,
					"platformcode"
	};
	public static String[] saBodyColKey = new String[] { "choose", "platformname", "platformcode" };

	private static List<PlatFormVO> platforms = null;

	// 初始化电商平台
	static {
		if (platforms == null) {
			platforms = new ArrayList<PlatFormVO>();
			PlatFormVO tmall = new PlatFormVO();
			tmall.setChoose(UFBoolean.FALSE);
			tmall.setPlatformname(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("menucode",
					"ordercentermenu_012") /* @res "天猫" */);
			tmall.setPlatformcode(PlatFormVO.TMALL);
			platforms.add(tmall);
			PlatFormVO lazada = new PlatFormVO();
			lazada.setChoose(UFBoolean.FALSE);
			lazada.setPlatformname(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("menucode",
					"ordercentermenu_013") /* @res "lazada" */);
			lazada.setPlatformcode(PlatFormVO.LAZADA);
			platforms.add(lazada);

		}
	};

	public DownloadOrderDialog(String title) {
		super();
		setSize(450, 300);
		setTitle(title);
	}

	@Override
	protected void initComponent(Container conterPanel) {
		conterPanel.setLayout(new GridBagLayout());
		conterPanel.setSize(500, 450);
		layout(conterPanel);
	}

	public void layout(Container c) {
		UILabel label0 = new UILabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
				.getStrByID("menucode", "ordercentermenu_009")/* @res "组织" */);
		label0.setILabelType(UILabel.STYLE_NOTNULL);

		UILabel label1 = new UILabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
				.getStrByID("menucode", "ordercentermenu_010")/* @res "开始日期" */);
		label1.setILabelType(UILabel.STYLE_NOTNULL);

		UILabel label2 = new UILabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
				.getStrByID("menucode", "ordercentermenu_011")/* @res "结束日期" */);
		label2.setILabelType(UILabel.STYLE_NOTNULL);

		UILabel label3 = new UILabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
				.getStrByID("menucode", "ordercentermenu_008")/* @res "平台" */);
		label3.setILabelType(UILabel.STYLE_NOTNULL);

		c.add(label0, getGridBagConstraints(0, 20, 0, 0));
		c.add(getRefPkorgMethod(), getGridBagConstraints(0, 0, 1, 0));

		c.add(label1, getGridBagConstraints(20, 20, 0, 1));
		c.add(getStartCalendar(), getGridBagConstraints(20, 20, 1, 1));

		c.add(label2, getGridBagConstraints(40, 40, 0, 2));
		c.add(getEndCalendar(), getGridBagConstraints(40, 40, 1, 2));

		c.add(label3, getGridBagConstraints(20, 20, 0, 3));
		c.add(getRefPlatform(), getGridBagConstraints(100, 100, 1, 3));
	}

	public GridBagConstraints getGridBagConstraints(int ipadx, int ipady,
			int gridx, int gridy) {
		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.HORIZONTAL;
		g.ipadx = ipadx;
		g.ipady = ipady;
		g.gridx = gridx;
		g.gridy = gridy;
		return g;
	}

	public UIRefPane getRefPkorgMethod() {
		if (pk_orgpanel == null) {
			pk_orgpanel = new UIRefPane(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("menucode",
							"ordercentermenu_009")/* @res "组织" */);
			pk_orgpanel.setSize(280, 22);
			SaleorgDefaultRefModel orgmodel = new SaleorgDefaultRefModel();
			String userId = InvocationInfoProxy.getInstance().getUserId();
			StringBuffer sb = new StringBuffer();
			sb.append(" pk_salesorg in (select urg.pk_org from (select distinct ur.cuserid, subjectorg.pk_org");
            sb.append(" from sm_user_role ur, sm_subject_org subjectorg");
            sb.append(" where ur.pk_role = subjectorg.subjectid) urg where 1 = 1");
            sb.append(" and urg.cuserid in ('" + userId + "'))");
			orgmodel.setWherePart(sb.toString());
			pk_orgpanel.setRefModel(orgmodel);
			pk_orgpanel.setMultiSelectedEnabled(false);
		}
		return pk_orgpanel;
	}

	public UIRefPane getStartCalendar() {
		if (startdatepanel == null) {
			startdatepanel = new UIRefPane(IRefConst.REFNODENAME_CALENDAR);
			startdatepanel.setPK(WorkbenchEnvironment.getInstance()
					.getBusiDate().toString());
			startdatepanel.setValue(WorkbenchEnvironment.getInstance()
					.getBusiDate().toString());
			startdatepanel.setPreferredSize(new Dimension(110, 50));
			startdatepanel.getUITextField().setShowMustInputHint(true);
		}
		return startdatepanel;
	}

	public UIRefPane getEndCalendar() {
		if (enddatepanel == null) {
			enddatepanel = new UIRefPane(IRefConst.REFNODENAME_CALENDAR);
			enddatepanel.setPK(WorkbenchEnvironment.getInstance().getBusiDate()
					.toString());
			enddatepanel.setValue(WorkbenchEnvironment.getInstance()
					.getBusiDate().toString());
			enddatepanel.setPreferredSize(new Dimension(110, 50));
			enddatepanel.getUITextField().setShowMustInputHint(true);
		}
		return enddatepanel;
	}

	public BillScrollPane getRefPlatform() {
		if (buplatformpanel == null) {
			buplatformpanel = new BillScrollPane();
			buplatformpanel
					.setName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
							.getStrByID("menucode", "ordercentermenu_008")/*
																		 * @res
																		 * "平台"
																		 */);
			buplatformpanel.setSize(280, 322);
			BillItem[] biaBody = new BillItem[saBodyColName.length];
			for (int i = 0; i < saBodyColName.length; i++) {
				biaBody[i] = new BillItem();
				biaBody[i].setName(saBodyColName[i]);
				biaBody[i].setKey(saBodyColKey[i]);
				biaBody[i].setWidth(100);
				biaBody[i].setEnabled(true);
				biaBody[i].setEdit(false);
				biaBody[i].setDataType(IBillItem.STRING);
			}
			biaBody[0].setEdit(true);
			biaBody[0].setDataType(IBillItem.BOOLEAN);
			biaBody[2].setShow(false);

			this.getBillModelPlatForm().setBodyItems(biaBody);
			this.getBillModelPlatForm().setBodyDataVO(platforms.toArray(new PlatFormVO[0]));
			this.buplatformpanel.setTableModel(this.getBillModelPlatForm());
			// this.buplatformpanel.setBounds(60, 60, 280, 222);
		}
		return buplatformpanel;
	}

	/* 警告：此方法将重新生成。 */
	private nc.ui.pub.bill.BillModel getBillModelPlatForm() {
		if (this.plafformmodel == null) {
			try {
				this.plafformmodel = new nc.ui.pub.bill.BillModel();
			} catch (java.lang.Throwable ivjExc) {
			}
		}
		return this.plafformmodel;
	}

	@Override
	public void cancel() {
		super.cancel();
		setResult(UIDialog.ID_CANCEL);
	}

	@Override
	protected void doOK() {
		String pk_org = this.getRefPkorgMethod().getRefPK();
		if (pk_org == null || pk_org.length() == 0) {
			MessageDialog.showWarningDlg(null, "warning",
					"Organization can't be null");
			return;
		}
		String startdate = this.getStartCalendar().getRefPK();
		String enddate = this.getEndCalendar().getRefPK();
		if(StringUtils.isBlank(startdate) || StringUtils.isBlank(enddate)) {
			MessageDialog.showWarningDlg(null, "warning",
					"Start Date and End date can't be blank.");
			return;
		}
		PlatFormVO[] platformvos = (PlatFormVO[]) this.getBillModelPlatForm().getBodyValueVOs(PlatFormVO.class.getName());
		if(platformvos != null && platformvos.length > 0) {
			int count = 0;
			for(PlatFormVO vo : platformvos) {
				if(vo.getChoose().booleanValue()) {
					count ++;
				}
			}
			if(count == 0) {
				MessageDialog.showWarningDlg(null, "warning",
						"Please choose platform.");
				return;
			}
			super.doOK();
			setResult(UIDialog.ID_OK);
		} else {
			MessageDialog.showWarningDlg(null, "warning",
					"Please choose platform.");
			return;
		}

	}

}
