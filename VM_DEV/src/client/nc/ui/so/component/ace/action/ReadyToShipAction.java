package nc.ui.so.component.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.pub.so.apiservice.ILazadaService;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * ERP·¢»õ
 * @author weiningc
 *
 */
public class ReadyToShipAction extends NCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BillManageModel model;

	public ReadyToShipAction() {
		super();
		CenterOrderMenuInit.initializeAction(this, CenterOrderMenuCode.READYTOSHIP);
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		ILazadaService lookup = NCLocator.getInstance().lookup(ILazadaService.class);
//		lookup.updateLazadaOrderStatus(order_item_ids, platform, shipProvider, trackingNo, country);
		ShowStatusBarMsgUtil.showStatusBarMsg("Ready to ship successful.",
				getModel().getContext());
	}
	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}
}
