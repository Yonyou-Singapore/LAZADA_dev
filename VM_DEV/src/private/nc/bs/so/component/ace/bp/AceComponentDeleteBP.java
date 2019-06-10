package nc.bs.so.component.ace.bp;

import nc.bs.so.component.plugin.bpplugin.ComponentPluginPoint;
import nc.vo.so.component.AggSo_ordercenter;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceComponentDeleteBP {

	public void delete(AggSo_ordercenter[] bills) {

		DeleteBPTemplate<AggSo_ordercenter> bp = new DeleteBPTemplate<AggSo_ordercenter>(
				ComponentPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggSo_ordercenter> processer) {
		// TODO ǰ����
		IRule<AggSo_ordercenter> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggSo_ordercenter> processer) {
		// TODO �����

	}
}
