package nc.ui.so.component.ace.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pub.so.apiservice.ILazadaService;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.component.AggSo_ordercenter;
import nc.vo.so.component.So_ordercenter_b;

/**
 * ERP发货
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
		//ready_to_ship参数构建
		AggSo_ordercenter selectedData = (AggSo_ordercenter) this.getModel().getSelectedData();
		if(selectedData == null) {
			return;
		}
		if(!"pending".equals(selectedData.getParentVO().getOrder_status())) {
			ExceptionUtils.wrappBusinessException("The order status should be pending.");
		}
		List<String> order_item_ids = new ArrayList<String>();;
		String shipProvider = "";
		String trackingNo = "";
		So_ordercenter_b [] bvos = selectedData.getChildrenVO();
		for(So_ordercenter_b vo : bvos) {
			order_item_ids.add(vo.getOrder_item_id());
			shipProvider = vo.getShipment_provider();
			trackingNo = vo.getTracking_code();
		}
		String platform = selectedData.getParentVO().getPlatform();
		String country = selectedData.getParentVO().getBilling_country();
		
		lookup.updateLazadaOrderStatus(order_item_ids.toString(), platform, shipProvider, trackingNo, country);
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
