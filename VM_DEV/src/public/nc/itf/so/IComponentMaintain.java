package nc.itf.so;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.component.AggSo_ordercenter;
import nc.vo.pub.BusinessException;

public interface IComponentMaintain {

	public void delete(AggSo_ordercenter[] vos) throws BusinessException;

	public AggSo_ordercenter[] insert(AggSo_ordercenter[] vos) throws BusinessException;

	public AggSo_ordercenter[] update(AggSo_ordercenter[] vos) throws BusinessException;

	public AggSo_ordercenter[] query(IQueryScheme queryScheme)
			throws BusinessException;

}