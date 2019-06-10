package nc.ui.so.component.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.pub.so.apiservice.ILazadaService;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.component.ace.view.DownloadOrderDialog;
import nc.ui.uif2.NCAction;
import nc.vo.pub.CircularlyAccessibleValueObject;
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
			String[] pk_orgs = dlg.getRefPkorgMethod().getRefPKs();
			String startdate = dlg.getStartCalendar().getRefPK();
			String enddate = dlg.getEndCalendar().getRefPK();
			CircularlyAccessibleValueObject[] bodySelectedVOs = dlg.getRefPlatform().getTableModel().getBodyValueVOs(PlatFormVO.class.getName());
			ILazadaService lookup = NCLocator.getInstance().lookup(ILazadaService.class);
//			lookup.downloadSelectOrderCenter(platform, startdate, enddate);
		}
			
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}
	

}
