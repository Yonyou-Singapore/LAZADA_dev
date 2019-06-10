package nc.bs.so.component.ace.bp;

import nc.bs.so.component.plugin.bpplugin.ComponentPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.component.AggSo_ordercenter;

/**
 * �޸ı����BP
 * 
 */
public class AceComponentUpdateBP {

	public AggSo_ordercenter[] update(AggSo_ordercenter[] bills,
			AggSo_ordercenter[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggSo_ordercenter> bp = new UpdateBPTemplate<AggSo_ordercenter>(
				ComponentPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggSo_ordercenter> processer) {
		// TODO �����
		IRule<AggSo_ordercenter> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggSo_ordercenter> processer) {
		// TODO ǰ����
		IRule<AggSo_ordercenter> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
