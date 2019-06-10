package nc.ui.so.component.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.component.AggSo_ordercenter;
import nc.itf.so.IComponentMaintain;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceComponentMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggSo_ordercenter>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		IComponentMaintain operator = NCLocator.getInstance().lookup(
				IComponentMaintain.class);
		AggSo_ordercenter[] vos = operator.insert((AggSo_ordercenter[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		IComponentMaintain operator = NCLocator.getInstance().lookup(
				IComponentMaintain.class);
		AggSo_ordercenter[] vos = operator.update((AggSo_ordercenter[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
		// 单据的删除实际上使用的是：ISingleBillService<AggSingleBill>的operateBill
		IComponentMaintain operator = NCLocator.getInstance().lookup(
				IComponentMaintain.class);
		operator.delete((AggSo_ordercenter[]) value);
		return value;
	}
	
	@Override
	public AggSo_ordercenter operateBill(AggSo_ordercenter bill) throws Exception {
		IComponentMaintain operator = NCLocator.getInstance().lookup(
				IComponentMaintain.class);
		operator.delete(new AggSo_ordercenter[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IComponentMaintain query = NCLocator.getInstance().lookup(
				IComponentMaintain.class);
		return query.query(queryScheme);
	}

}
