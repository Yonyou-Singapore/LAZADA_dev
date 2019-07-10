package nc.ui.so.component.ace.action;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import nc.bs.framework.common.NCLocator;
import nc.pub.so.apiservice.ILazadaService;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.BusinessException;
import nc.vo.so.component.AggSo_ordercenter;
import nc.vo.so.component.PlatformStatusAndPara;

import org.apache.commons.lang.StringUtils;

public class GenerateSoAction extends NCAction {
	private static final long serialVersionUID = 1L;
	private BillManageModel model;

	public GenerateSoAction() {
		super();
		CenterOrderMenuInit.initializeAction(this, CenterOrderMenuCode.GENERATESO);
	}
	@Override
	public void doAction(ActionEvent e) throws Exception {
		AggSo_ordercenter selectedData = (AggSo_ordercenter) this.getModel().getSelectedData();
		if(selectedData == null) {
			throw new BusinessException("Please choose one source order.");
		}
		//原单状态校验
		this.verifyOrderStatus(selectedData);
		//生成销售订单
		ILazadaService lookup = NCLocator.getInstance().lookup(ILazadaService.class);
		AggSo_ordercenter[] updatedagg = lookup.generateSalesOrderByOrdercenter(selectedData);
		this.model.directlyUpdate(updatedagg);
		ShowStatusBarMsgUtil.showStatusBarMsg("Generate sales order successful.",
				getModel().getContext());
		
	}
	
	private void verifyOrderStatus(AggSo_ordercenter selectedData) throws BusinessException {
		String orderstatus = selectedData.getParentVO().getOrder_status();
		if(StringUtils.isBlank(orderstatus)) {
			throw new BusinessException("Order status can not blank");
		}
		if(!Arrays.asList(PlatformStatusAndPara.GENERATE_ORDERSTATUS).contains(orderstatus)) {
			throw new BusinessException("The source status should in below status," + "\n" +
					"【ready_to_ship,shipped,delivered,WAIT_SELLER_SEND_GOODS,SELLER_CONSIGNED_PART,WAIT_BUYER_CONFIRM_GOODS" +
					",TRADE_BUYER_SIGNED,TRADE_FINISHED,WAIT_PRE_AUTH_CONFIRM】");
		}
		
	}
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
	}
	
}
