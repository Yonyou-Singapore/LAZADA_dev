package nc.bs.so.component.ace.bp;

import nc.bs.so.component.plugin.bpplugin.ComponentPluginPoint;
import nc.vo.so.component.AggSo_ordercenter;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceComponentDeleteBP {

	public void delete(AggSo_ordercenter[] bills) {

		DeleteBPTemplate<AggSo_ordercenter> bp = new DeleteBPTemplate<AggSo_ordercenter>(
				ComponentPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggSo_ordercenter> processer) {
		// TODO 前规则
		IRule<AggSo_ordercenter> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggSo_ordercenter> processer) {
		// TODO 后规则

	}
}
