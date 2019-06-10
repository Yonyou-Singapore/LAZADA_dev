package nc.bs.so.component.ace.bp;

import nc.bs.so.component.plugin.bpplugin.ComponentPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.component.AggSo_ordercenter;

/**
 * 标准单据新增BP
 */
public class AceComponentInsertBP {

	public AggSo_ordercenter[] insert(AggSo_ordercenter[] bills) {

		InsertBPTemplate<AggSo_ordercenter> bp = new InsertBPTemplate<AggSo_ordercenter>(
				ComponentPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggSo_ordercenter> processor) {
		// TODO 新增后规则
		IRule<AggSo_ordercenter> rule = null;
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggSo_ordercenter> processer) {
		// TODO 新增前规则
		IRule<AggSo_ordercenter> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
