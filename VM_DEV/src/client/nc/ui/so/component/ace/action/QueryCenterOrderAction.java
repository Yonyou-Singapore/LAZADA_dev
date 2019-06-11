package nc.ui.so.component.ace.action;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.DefaultQueryConditionDLG;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.uif2.NCAction;
import nc.vo.querytemplate.TemplateInfo;

public class QueryCenterOrderAction extends DefaultQueryAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public QueryConditionDLGDelegator getQryDLGDelegator() {

		if (iQueryDlg != null) {
			DefaultQueryConditionDLG qcd = (DefaultQueryConditionDLG) iQueryDlg
					.createQCDByIQCD(iQueryDlg);
			qcd.getQryCondEditor().getQueryContext()
					.setReloadQuickAreaValue(isReloadQuickAreaValue());
			this.qryCondDLGDelegator = this.createQryDLGDelegator(qcd);
			qryCondDLGDelegator.registerNeedPermissionOrgFieldCode("pk_org");
			qryCondDLGDelegator.setPowerEnable(true);
			return qryCondDLGDelegator;

		} else {
			if (this.qryCondDLGDelegator == null) {
				TemplateInfo tempinfo = new TemplateInfo();
				tempinfo.setPk_Org(this.getModel().getContext().getPk_group());
				tempinfo.setFunNode(this.getFunNode());
				tempinfo.setUserid(this.getModel().getContext().getPk_loginUser());
				tempinfo.setNodekey(this.getNodeKey());
				tempinfo.setSealedDataShow(true);
				// 返回供应链的查询对话框
				this.qryCondDLGDelegator = this.createQryDLGDelegator(tempinfo);
				this.qryCondDLGDelegator.getQce().getQueryContext()
						.setReloadQuickAreaValue(isReloadQuickAreaValue());
			}
			qryCondDLGDelegator.registerNeedPermissionOrgFieldCode("pk_org");
			qryCondDLGDelegator.setPowerEnable(true);
			return this.qryCondDLGDelegator;
		}
	
	}
	
	@Override
	protected boolean isActionEnable() {
		// TODO Auto-generated method stub
		return true;
	}
}
