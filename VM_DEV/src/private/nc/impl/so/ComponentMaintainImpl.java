package nc.impl.so;

import nc.impl.pub.ace.AceComponentPubServiceImpl;
import nc.vo.so.component.AggSo_ordercenter;
import nc.itf.so.IComponentMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class ComponentMaintainImpl extends AceComponentPubServiceImpl implements
		IComponentMaintain {

	@Override
	public void delete(AggSo_ordercenter[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggSo_ordercenter[] insert(AggSo_ordercenter[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggSo_ordercenter[] update(AggSo_ordercenter[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggSo_ordercenter[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
