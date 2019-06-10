package nc.bs.so.component.ace.bp;

import nc.bs.so.component.plugin.bpplugin.ComponentPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.component.AggSo_ordercenter;

/**
 * 修改保存的BP
 * 
 */
public class AceComponentUpdateBP {

	public AggSo_ordercenter[] update(AggSo_ordercenter[] bills,
			AggSo_ordercenter[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggSo_ordercenter> bp = new UpdateBPTemplate<AggSo_ordercenter>(
				ComponentPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggSo_ordercenter> processer) {
		// TODO 后规则
		IRule<AggSo_ordercenter> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggSo_ordercenter> processer) {
		// TODO 前规则
		IRule<AggSo_ordercenter> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
