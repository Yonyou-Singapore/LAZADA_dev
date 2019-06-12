package nc.vo.so.component;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.component.So_ordercenter")
public class AggSo_ordercenter extends AbstractBill {

	@Override
	public IBillMeta getMetaData() {
		IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(
				AggSo_ordercenterMeta.class);
		return billMeta;
	}

	@Override
	public So_ordercenter getParentVO() {
		return (So_ordercenter) this.getParent();
	}

	public void setParentVO(So_ordercenter parent) {
		super.setParentVO(parent);
	}

	@Override
	public So_ordercenter_b[] getChildrenVO() {
		return (So_ordercenter_b[]) super.getChildrenVO();
	}
	
	public void setChildrenVO(So_ordercenter_b[] childrenvo) {
		super.setChildrenVO(childrenvo);
	}
}