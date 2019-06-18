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
 * 联查销售订单
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
		// 选择的数据校验
		String msg = checkSelected();
		if (msg != null && msg.length() > 0) {
			ShowStatusBarMsgUtil.showErrorMsg("Failed", msg,
					getModel().getContext());
			return;
		}
		// 设置需要联查的数据
		ILinkQueryData queryData = getLinkQueryData();
		if (queryData == null) {
			ShowStatusBarMsgUtil.showErrorMsg("Failed", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ampub_0", "04501000-0340")/*
					 * @
					 * res
					 * "未查到符合条件的数据，请确认要联查的信息."
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
			return "请至少选择一行数据.";
		}
		if(selectedRow > 1) {
			return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ampub_0", "04501000-0344")/*
					 * @
					 * res
					 * "不支持选择多条表体联查"
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
	 * 获取联查数据，如果需要特殊处理，重写该方法即可
	 * 
	 * @return
	 */
	protected ILinkQueryData getLinkQueryData() {
		// 获取联查的主键
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
	
	/** 联查显示 */
	protected void showFuncNodeClient(ILinkQueryData queryData) throws BusinessException {
		// 联查
		// SFClientUtil.openLinkedQueryDialog(funnode,
		// getModel().getContext().getEntranceUI(), queryData);
		// 功能注册VO的信息
		FuncRegisterVO frVO = WorkbenchEnvironment.getInstance().getFuncRegisterVO(SOFUNNODE);
		if (frVO != null) {
			// 设置联查单据的信息
			FuncletInitData initData = new FuncletInitData();
			initData.setInitType(ILinkType.LINK_TYPE_QUERY);
			initData.setInitData(queryData);

			// 打开节点
			FuncletWindowLauncher.openFuncNodeFrame(getModel().getContext().getEntranceUI(), frVO, initData, null,
					false, new Dimension(CommonKeyConst.LINK_SCREEN_WIDTH, CommonKeyConst.LINK_SCREEN_HEIGHT));
		} else {
			ShowStatusBarMsgUtil.showErrorMsg(
					nc.ui.ml.NCLangRes.getInstance().getStrByID("sysframev5", "UPPsysframev5-000062")/*
																									 * @
																									 * res
																									 * "错误"
																									 */,

					nc.ui.ml.NCLangRes.getInstance().getStrByID("sysframev5", "UPPsysframev5-000095")/*
																									 * @
																									 * res
																									 * "没有打开此节点的权限 . 节点号="
																									 */
							+ SOFUNNODE, getModel().getContext());
		}
	}
	
	/**
	 * 目的单据主键，如果联查目的单据为多个时，则需要重写改方法，返回目的单据所有主键
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
	 * 返回需要联查的主键
	 * 
	 * @return
	 */
	protected String getQueryAboutDataKey() {
		AggSo_ordercenter selectedData = (AggSo_ordercenter) this.getModel().getSelectedData();
		//电商订单号
		String order_number = selectedData.getParentVO().getOrder_number();
		//组织
		String pk_org = selectedData.getParentVO().getPk_org();
		//如果查出结果有多个,取最新的
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
