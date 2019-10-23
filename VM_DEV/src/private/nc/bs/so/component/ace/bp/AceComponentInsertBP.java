package nc.bs.so.component.ace.bp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.bs.so.component.plugin.bpplugin.ComponentPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.component.AggSo_ordercenter;

/**
 * ��׼��������BP
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
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggSo_ordercenter> processor) {
		// TODO ���������
		IRule<AggSo_ordercenter> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggSo_ordercenter> processer) {
		// TODO ����ǰ����
		IRule<AggSo_ordercenter> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
