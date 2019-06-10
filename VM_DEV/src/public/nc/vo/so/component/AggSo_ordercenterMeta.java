package nc.vo.so.component;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSo_ordercenterMeta extends AbstractBillMeta{
	
	public AggSo_ordercenterMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.so.component.So_ordercenter.class);
		this.addChildren(nc.vo.so.component.So_ordercenter_b.class);
	}
}