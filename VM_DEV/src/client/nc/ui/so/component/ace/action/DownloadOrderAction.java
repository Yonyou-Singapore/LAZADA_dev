package nc.ui.so.component.ace.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pub.so.apiservice.ILazadaService;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.component.ace.view.DownloadOrderDialog;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.so.component.PlatFormVO;

public class DownloadOrderAction extends NCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BillManageModel model;

	public DownloadOrderAction() {
		super();
		CenterOrderMenuInit.initializeAction(this, CenterOrderMenuCode.DOWNLOADORDER);
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		DownloadOrderDialog dlg = new DownloadOrderDialog("Download Order");
		dlg.execute();
		if(dlg.getResult() == UIDialog.ID_OK){
			String[] codes = dlg.getRefPkorgMethod().getRefCodes();
			UFDate startdate = dlg.getStartCalendar().getDateBegin();
			UFDate enddate = dlg.getEndCalendar().getDateEnd();
			PlatFormVO[] bodySelectedVOs = (PlatFormVO[]) dlg.getRefPlatform().getTableModel().getBodyValueVOs(PlatFormVO.class.getName());
			List<String> platvos = new ArrayList<String>();
			for(PlatFormVO vo : bodySelectedVOs) {
				if(vo.getChoose().booleanValue()) {
					platvos.add(vo.getPlatformcode());
				}
			}
			
			ILazadaService lookup = NCLocator.getInstance().lookup(ILazadaService.class);
			lookup.downloadSelectOrderCenter(platvos.toArray(new String[0]), codes, startdate, enddate);
			ShowStatusBarMsgUtil.showStatusBarMsg("Download order successful.",
					getModel().getContext());
		}
			
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}
	

}
