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
	// ����
	public AggSo_ordercenter[] pubinsertBills(AggSo_ordercenter[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggSo_ordercenter> transferTool = new BillTransferTool<AggSo_ordercenter>(
					vos);
			AggSo_ordercenter[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceComponentInsertBP action = new AceComponentInsertBP();
			AggSo_ordercenter[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggSo_ordercenter[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggSo_ordercenter> transferTool = new BillTransferTool<AggSo_ordercenter>(
					vos);
			AggSo_ordercenter[] fullBills = transferTool.getClientFullInfoBill();
			AceComponentDeleteBP deleteBP = new AceComponentDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggSo_ordercenter[] pubupdateBills(AggSo_ordercenter[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggSo_ordercenter> transTool = new BillTransferTool<AggSo_ordercenter>(
					vos);
			// ��ȫǰ̨VO
			AggSo_ordercenter[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggSo_ordercenter[] originBills = transTool.getOriginBills();
			// ����BP
			AceComponentUpdateBP bp = new AceComponentUpdateBP();
			AggSo_ordercenter[] retBills = bp.update(fullBills, originBills);
			// ���췵������
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
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

}