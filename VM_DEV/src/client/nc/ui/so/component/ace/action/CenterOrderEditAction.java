package nc.ui.so.component.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.so.component.AggSo_ordercenter;
import nc.vo.so.component.PlatformStatusAndPara;

public class CenterOrderEditAction extends EditAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ShowUpableBillForm cardForm;
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.doAction(e);
		//×Ö¶Î¿É±à¼­ÉèÖÃ
		this.setFiledEditable();
	}
	
	private void setFiledEditable() {
		BillCardPanel billcardPanel = this.cardForm.getBillCardPanel();		
		BillItem[] headItems = billcardPanel.getHeadItems();
		BillItem[] bodyItems = billcardPanel.getBodyItems();
		for(BillItem item : headItems) {
			billcardPanel.getHeadItem(item.getKey()).setEnabled(false);
		}
		for(BillItem item : bodyItems) {
			billcardPanel.getBodyItem(item.getKey()).setEnabled(false);
		}
		
		//±íÍ·¿É±à¼­×Ö¶Î
		String[] headeditableitems = new String[]{
				"shipping_address1", "shipping_address2", "shipping_address3","shipping_address4", "shipping_address5",
				"shipping_post_code", "shipping_last_name", "shipping_phone2", "shipping_first_name","shipping_phone",
				"billing_address1", "billing_address2", "billing_address3", "billing_address4", "billing_address5",
				"billing_post_code", "billing_last_name", "billing_phone2", "billing_first_name", "billing_phone"
		};
		//±íÌå¿É±à¼­×Ö¶Î
		String[] bodyeditableitems = new String[] {"csendstordocid", "csendstockorgvid"};
		for(String item : headeditableitems) {
			if(billcardPanel.getHeadItem(item) != null) {
				billcardPanel.getHeadItem(item).setEdit(true);
			}
		}
		for(String item : bodyeditableitems) {
			if(billcardPanel.getBodyItem(item) != null) {
				billcardPanel.getBodyItem(item).setEdit(true);
			}
		}
	}

	@Override
	protected boolean isActionEnable() {
		AggSo_ordercenter selectedData = (AggSo_ordercenter) this.getModel().getSelectedData();
		if(selectedData != null && 
				PlatformStatusAndPara.LAZADA_PENDING.equals(selectedData.getParentVO().getOrder_status())) {
			return true;
		}
		return true;
	}

	public ShowUpableBillForm getCardForm() {
		return cardForm;
	}

	public void setCardForm(ShowUpableBillForm cardForm) {
		this.cardForm = cardForm;
	}
	
	
}
