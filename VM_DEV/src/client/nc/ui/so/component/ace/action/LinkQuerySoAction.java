package nc.ui.so.component.ace.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.am.common.util.ArrayUtils;
import nc.vo.am.common.util.LinkQueryData;
import nc.vo.am.common.util.MultiLinkQueryData;
import nc.vo.am.common.util.StringUtils;
import nc.vo.am.constant.CommonKeyConst;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.component.AggSo_ordercenter;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * �������۶���
 * @author weiningc
 *
 */
@SuppressWarnings("restriction")
public class LinkQuerySoAction extends NCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BillManageModel model;
	public static final String SOBILLTYPE = "30";
	public static final String SOFUNNODE = "40060301";

	public LinkQuerySoAction() {
		super();
		CenterOrderMenuInit.initializeAction(this, CenterOrderMenuCode.UPDATETODELIVERED);
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// ѡ�������У��
		String msg = checkSelected();
		if (msg != null && msg.length() > 0) {
			ShowStatusBarMsgUtil.showErrorMsg("Failed", msg,
					getModel().getContext());
			return;
		}
		// ������Ҫ���������
		ILinkQueryData queryData = getLinkQueryData();
		if (queryData == null) {
			ShowStatusBarMsgUtil.showErrorMsg("Failed", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ampub_0", "04501000-0340")/*
					 * @
					 * res
					 * "δ�鵽�������������ݣ���ȷ��Ҫ�������Ϣ."
					 */, getModel().getContext());
			return;
		}
		showFuncNodeClient(queryData);
		
	}
	private String checkSelected() {
		AggSo_ordercenter selectedData = (AggSo_ordercenter) this.getModel().getSelectedData();
		int selectedRow = 0;
		if(selectedData != null && selectedData.getParentVO() != null) {
			selectedRow = 1;
		} else {
			return "������ѡ��һ������.";
		}
		if(selectedRow > 1) {
			return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ampub_0", "04501000-0344")/*
					 * @
					 * res
					 * "��֧��ѡ�������������"
					 */;
		}
		return null;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}
	
	/**
	 * ��ȡ�������ݣ������Ҫ���⴦����д�÷�������
	 * 
	 * @return
	 */
	protected ILinkQueryData getLinkQueryData() {
		// ��ȡ���������
		String[] billids = getQueryAboutDataKeys();
		ILinkQueryData queryData = null;
		if (ArrayUtils.isEmpty(billids)) {
			return null;
		} else if (billids.length == 1) {
			LinkQueryData linkqueryData = new LinkQueryData();
			linkqueryData.setBillID(billids[0]);
			linkqueryData.setBillType(SOBILLTYPE);
			linkqueryData.setPkOrg(getModel().getContext().getPk_org());
			queryData = linkqueryData;
		} else {
			MultiLinkQueryData multiQueryData = new MultiLinkQueryData();
			multiQueryData.setBillIds(billids);
			multiQueryData.setBillType(SOBILLTYPE);
			multiQueryData.setPkOrg(getModel().getContext().getPk_org());
			queryData = multiQueryData;
		}
		return queryData;
	}
	
	/** ������ʾ */
	protected void showFuncNodeClient(ILinkQueryData queryData) throws BusinessException {
		// ����
		// SFClientUtil.openLinkedQueryDialog(funnode,
		// getModel().getContext().getEntranceUI(), queryData);
		// ����ע��VO����Ϣ
		FuncRegisterVO frVO = WorkbenchEnvironment.getInstance().getFuncRegisterVO(SOFUNNODE);
		if (frVO != null) {
			// �������鵥�ݵ���Ϣ
			FuncletInitData initData = new FuncletInitData();
			initData.setInitType(ILinkType.LINK_TYPE_QUERY);
			initData.setInitData(queryData);

			// �򿪽ڵ�
			FuncletWindowLauncher.openFuncNodeFrame(getModel().getContext().getEntranceUI(), frVO, initData, null,
					false, new Dimension(CommonKeyConst.LINK_SCREEN_WIDTH, CommonKeyConst.LINK_SCREEN_HEIGHT));
		} else {
			ShowStatusBarMsgUtil.showErrorMsg(
					nc.ui.ml.NCLangRes.getInstance().getStrByID("sysframev5", "UPPsysframev5-000062")/*
																									 * @
																									 * res
																									 * "����"
																									 */,

					nc.ui.ml.NCLangRes.getInstance().getStrByID("sysframev5", "UPPsysframev5-000095")/*
																									 * @
																									 * res
																									 * "û�д򿪴˽ڵ��Ȩ�� . �ڵ��="
																									 */
							+ SOFUNNODE, getModel().getContext());
		}
	}
	
	/**
	 * Ŀ�ĵ����������������Ŀ�ĵ���Ϊ���ʱ������Ҫ��д�ķ���������Ŀ�ĵ�����������
	 * 
	 * @return
	 */
	protected String[] getQueryAboutDataKeys() {
		String pk = getQueryAboutDataKey();
		if (StringUtils.isEmpty(pk)) {
			return null;
		}
		String[] billids = new String[] { pk };
		return billids;
	}
	
	/**
	 * ������Ҫ���������
	 * 
	 * @return
	 */
	protected String getQueryAboutDataKey() {
		AggSo_ordercenter selectedData = (AggSo_ordercenter) this.getModel().getSelectedData();
		//���̶�����
		String order_number = selectedData.getParentVO().getOrder_number();
		//��֯
		String pk_org = selectedData.getParentVO().getPk_org();
		//����������ж��,ȡ���µ�
		SqlBuilder sb = new SqlBuilder();
		sb.append(" dr=0 and vdef2", order_number);
		sb.append(" and rownum=1");
		
		try {
			NCObject[] obj = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(SaleOrderHVO.class, sb.toString(), false);
			if(obj != null && obj.length > 0) {
				return (String) obj[0].getAttributeValue(SaleOrderHVO.CSALEORDERID);
			}
		} catch (MetaDataException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		//test
		return null;
		
	}
}
