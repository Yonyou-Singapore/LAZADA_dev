package nc.vo.so.restapi;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.restapi.LazadaHeadVO")
public class LazadaAggVO extends AbstractBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public IBillMeta getMetaData() {
		IBillMeta billMeta =
		        BillMetaFactory.getInstance().getBillMeta(LazadaMeta.class);
		return billMeta;
	}

}
