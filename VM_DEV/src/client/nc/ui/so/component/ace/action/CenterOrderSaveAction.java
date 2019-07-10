package nc.ui.so.component.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.trade.businessaction.IPFACTION;
import nc.ui.uif2.actions.ActionInitializer;

public class CenterOrderSaveAction extends ScriptPFlowAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BillManageModel model;
	
	public CenterOrderSaveAction() {
		ActionInitializer.initializeAction(this, IActionCode.SAVE);
	    this.setActionName(IPFACTION.SAVE);
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.doAction(e);
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	
	
}
