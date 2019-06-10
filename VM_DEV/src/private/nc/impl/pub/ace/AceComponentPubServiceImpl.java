package nc.impl.pub.ace;

import nc.bs.so.component.ace.bp.AceComponentInsertBP;
import nc.bs.so.component.ace.bp.AceComponentUpdateBP;
import nc.bs.so.component.ace.bp.AceComponentDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.component.AggSo_ordercenter;

public abstract class AceComponentPubServiceImpl {
	// 新增
	public AggSo_ordercenter[] pubinsertBills(AggSo_ordercenter[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggSo_ordercenter> transferTool = new BillTransferTool<AggSo_ordercenter>(
					vos);
			AggSo_ordercenter[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceComponentInsertBP action = new AceComponentInsertBP();
			AggSo_ordercenter[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggSo_ordercenter[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggSo_ordercenter> transferTool = new BillTransferTool<AggSo_ordercenter>(
					vos);
			AggSo_ordercenter[] fullBills = transferTool.getClientFullInfoBill();
			AceComponentDeleteBP deleteBP = new AceComponentDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggSo_ordercenter[] pubupdateBills(AggSo_ordercenter[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggSo_ordercenter> transTool = new BillTransferTool<AggSo_ordercenter>(
					vos);
			// 补全前台VO
			AggSo_ordercenter[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggSo_ordercenter[] originBills = transTool.getOriginBills();
			// 调用BP
			AceComponentUpdateBP bp = new AceComponentUpdateBP();
			AggSo_ordercenter[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSo_ordercenter[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSo_ordercenter[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSo_ordercenter> query = new BillLazyQuery<AggSo_ordercenter>(
					AggSo_ordercenter.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

}