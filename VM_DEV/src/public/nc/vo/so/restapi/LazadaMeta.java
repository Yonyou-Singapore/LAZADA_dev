package nc.vo.so.restapi;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * 
 * @author weiningc
 *
 */
public class LazadaMeta extends AbstractBillMeta {
	public LazadaMeta () {
		this.setParent(LazadaHeadVO.class);
		this.addChildren(LazadaItemVO.class);
	}
}
