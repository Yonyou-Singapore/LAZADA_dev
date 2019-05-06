package nc.pubimpl.so.m30.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ic.pub.util.BillQueryUtils;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubimpl.so.m30.api.check.SaleOrderValidator;
import nc.pubimpl.so.m30.api.fill.SaleOrderSaveDefValue;
import nc.pubitf.bd.accessor.GeneralAccessorFactory;
import nc.pubitf.bd.accessor.IGeneralAccessor;
import nc.pubitf.so.m30.api.ISaleOrderMaintainAPI;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.check.billvalidate.BillVOsCheckRule;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.restapi.RestMessageVO;

import org.apache.commons.lang.StringUtils;

/**
 * @description
 *              ���۶����־û�����ʵ��1
 * @scene
 * 
 * @param ��
 * 
 * 
 * @since 6.5
 * @version 2015-10-20 ����1:52:34
 * @author ����
 */
public class SaleOrderMaintainAPIImpl implements ISaleOrderMaintainAPI {
  @Override
  public SaleOrderVO[] insertBills(SaleOrderVO[] vos) throws BusinessException {
    SaleOrderVO[] fillvos = vos;

    // 1��������У��
    BillVOsCheckRule checker = new BillVOsCheckRule(new SaleOrderValidator());
    checker.check(fillvos);

    // 2��ǿ��Ĭ��ֵ���
    SaleOrderSaveDefValue filldatarule = new SaleOrderSaveDefValue();
    filldatarule.setDefultValue(fillvos);
    
    // 2. Modified on 2018-01-23
    // ����SaleOrderVO���Զ������߼�
//    SaleOrderElkenCalculator cal = new SaleOrderElkenCalculator();
//    cal.calculate(fillvos);
    
    
    // 3����ֵ������
    SaleOrderVO[] combinBillVOs =
        (SaleOrderVO[]) AggVOUtil.combinBillVO(fillvos, vos);

    // 4������
    SaleOrderVO[] retvos =
        (SaleOrderVO[]) PfServiceScmUtil.processBatch(SOConstant.WRITE,
            SOBillType.Order.getCode(), combinBillVOs, null, null);
    return retvos;
  }

  @Override
  public void deleteBillsByID(String[] ids) throws BusinessException {
    BillQuery<SaleOrderVO> query = new BillQuery<>(SaleOrderVO.class);
    SaleOrderVO[] deletevos = query.query(ids);
    PfServiceScmUtil.processBatch(SOConstant.DELETE,
        SOBillType.Order.getCode(), deletevos, null, null);
  }

	@Override
	public synchronized RestMessageVO syncSaleOrders(SaleOrderVO[] vos)
			throws Exception {
		RestMessageVO ret = new RestMessageVO();
		// ���ݺ���ϵͳ���Ѿ����ڵ�����
		List<String> infos = null;
		StringBuilder sb = new StringBuilder();
		SaleOrderVO[] existvos = this.queryBills(vos);
		if (existvos != null && existvos.length > 0) {
			for(SaleOrderVO vo : existvos) {
				sb.append("[" + vo.getParentVO().getVdef2() + "],");
			}
			ExceptionUtils.wrappBusinessException("Lazadaԭ���������۶����ظ������鶩����: " + sb.toString());
		}
		//���ñ���Ҫ�е�Ĭ��ֵ �������ͣ� ˰��ȵ�
		this.setDefaultForLazada(vos);
		
		this.insertBills(vos);
		// Modified by Ethan on 2018-03-23
		// ��ΪSO��һ����һ��ͬ���� ����ֱ�Ӵ���ȥ
		return null;
	}
	
    private void setDefaultForLazada(SaleOrderVO[] vos) {
    	Map<String, String> querySkuPK = this.querySkuPK(vos);
    	//sku ƥ��
    	this.setSkuPk(vos, querySkuPK);
    	//��֯ƥ��
    	this.setPk_org(vos);
    	//�Զ�����
    	this.setDef(vos);
    	//����
    	Map<String, String> idCOdeMap = new HashMap<String, String>();
    	Set<String> defcodes = new HashSet<String>();
    	for(SaleOrderVO vo : vos) {
    		defcodes.add(vo.getParentVO().getCorigcurrencyid());
    	}
    	SqlBuilder sb = new SqlBuilder();
    	sb.append("select code, pk_currtype from bd_currtype where");
    	sb.append(" code", defcodes.toArray(new String[0]));
    	DataAccessUtils util = new DataAccessUtils();
    	IRowSet rowset = util.query(sb.toString());
    	while (rowset.next()) {
    		idCOdeMap.put(rowset.getString(0), rowset.getString(1));
    	}
    	for(SaleOrderVO vo : vos) {
    		vo.getParentVO().setCorigcurrencyid(idCOdeMap.get(vo.getParentVO().getCorigcurrencyid()));
    	}
		
	}

    private void setDef(SaleOrderVO[] vos) { 
    	StringBuffer err = new StringBuffer();
    	Map<String, String> idCOdeMap = new HashMap<String, String>();
    	Set<String> defcodes = new HashSet<String>();
    	//���䷽ʽ(�Զ�����) 1001G61000000000RCL3
    	IGeneralAccessor accessor = GeneralAccessorFactory
				.getAccessor("1001G61000000000RCL3");
    	
    	for(SaleOrderVO vo : vos) {
    		defcodes.add(vo.getParentVO().getVdef1());
    	}
    	SqlBuilder sb = new SqlBuilder();
    	sb.append("select code, pk_defdoc from bd_defdoc where");
    	sb.append(" code", defcodes.toArray(new String[0]));
    	DataAccessUtils util = new DataAccessUtils();
    	IRowSet rowset = util.query(sb.toString());
    	while (rowset.next()) {
    		idCOdeMap.put(rowset.getString(0), rowset.getString(1));
    	}
    	for(SaleOrderVO vo : vos) {
    		String defpk = idCOdeMap.get(vo.getParentVO().getVdef1());
    		if(StringUtils.isBlank(defpk)) {
    			err.append("order [" + vo.getParentVO().getVdef2() + "], code: [" + vo.getParentVO().getVdef1() + "], ");
    		}
    		//����ƽ̨
    		vo.getParentVO().setVdef1(idCOdeMap.get(vo.getParentVO().getVdef1()));
    		//���䷽ʽ
    		String shippingtype = vo.getParentVO().getVdef7();
    		if(!StringUtils.isBlank(shippingtype)) {
    			shippingtype = shippingtype.toUpperCase();
    		}
    		IBDData docdata = accessor.getDocByNameWithMainLang(vo.getParentVO().getPk_group(), shippingtype);
    		if(docdata != null) {
    			vo.getParentVO().setVdef7(docdata.getPk());
    		}
    		
    	}
    	if(err.length() > 0) {
//    		ExceptionUtils.wrappBusinessException("����ƽ̨��ƥ��: " + err.toString());
    	}
		
    	
	}

	private void setPk_org(SaleOrderVO[] vos) {
    	Map<String, String> idCOdeMap = new HashMap<String, String>();
    	Set<String> orgcodes = new HashSet<String>();
    	for(SaleOrderVO vo : vos) {
    		orgcodes.add(vo.getParentVO().getPk_org());
    	}
    	SqlBuilder sb = new SqlBuilder();
    	sb.append("select code, pk_org from org_orgs where");
    	sb.append(" code", orgcodes.toArray(new String[0]));
    	DataAccessUtils util = new DataAccessUtils();
    	IRowSet rowset = util.query(sb.toString());
    	while (rowset.next()) {
    		idCOdeMap.put(rowset.getString(0), rowset.getString(1));
    	}
    	for(SaleOrderVO vo : vos) {
    		vo.getParentVO().setPk_org(idCOdeMap.get(vo.getParentVO().getPk_org()));
    	}
	}

	private void setSkuPk(SaleOrderVO[] vos, Map<String, String> querySkuPK) {
		StringBuffer error = new StringBuffer();
		for(SaleOrderVO vo : vos) { 
	//		//���ݺ�Ĭ��
	//		BillCodeInfo info =
	//		        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Order.getCode(), SaleOrderHVO.VBILLCODE,
	//		            SaleOrderHVO.PK_GROUP, SaleOrderHVO.PK_ORG, SaleOrderHVO.VTRANTYPECODE);
	//	    BillCodeUtils util = new BillCodeUtils(info);
	//	    util.createBillCode(vos); 
			
			SaleOrderBVO[] childrenVO = vo.getChildrenVO();
			for(SaleOrderBVO child : childrenVO) {
				if(querySkuPK.get(child.getCmaterialvid()) == null) {
					error.append("order:" + vo.getParentVO().getVdef2() +" ,sku [" + child.getCmaterialvid() + "], Country:[" + vo.getParentVO().getVdef1() + "]");
				}
				child.setCmaterialvid(querySkuPK.get(child.getCmaterialvid()));
				child.setCmaterialid(querySkuPK.get(child.getCmaterialvid()));
				//��ƷĬ��ֵ����
				if(child.getBlargessflag() == null) {
					child.setBlargessflag(UFBoolean.FALSE);
				}
			}
		}
		if(error.length() > 0) {
			ExceptionUtils.wrappBusinessException("���ϲ�ƥ��: " + error.toString()); 
		}
		
	}

/**
   * ����
   * 
   * @param vos
   * @return
   * @throws BusinessException
   */
  private SaleOrderVO[] approveBills(SaleOrderVO[] vos)
      throws BusinessException {
    SaleOrderVO[] retvos =
        (SaleOrderVO[]) PfServiceScmUtil.processBatch(SOConstant.APPROVE,
            SOBillType.Order.getCode(), vos, null, null);
    return retvos;
  }

  /**
   * ���ݶ����Ų�ѯ����
   * 
   * @param vos
   * @return
   * @throws BusinessException
   */
  private SaleOrderVO[] queryBills(SaleOrderVO[] vos) throws BusinessException {
    // ������
    List<String> billcodeList = new ArrayList<String>();
    for (SaleOrderVO saleorderVO : vos) {
      billcodeList.add(saleorderVO.getParentVO().getVdef2());
    }
    SqlBuilder whereSql = new SqlBuilder();
    whereSql.append(" from so_saleorder where vdef2 ",
        billcodeList.toArray(new String[billcodeList.size()]));
    whereSql.append("and dr = '0'");
    BillQueryUtils<SaleOrderVO> util =
        new BillQueryUtils<SaleOrderVO>(new SaleOrderVO());
    return util.queryBills(whereSql.toString());
  }
  
  private Map<String, String> querySkuPK(SaleOrderVO[] aggs) {
		Map<String, String> helpcodesAndsku_map = new HashMap<String, String>();
		Map<String, String> skucodeAndPK_map = new HashMap<String, String>();
		Set<String> helpcodes = new HashSet<String>();
		for(AggregatedValueObject agg : aggs) {
			CircularlyAccessibleValueObject[] childrenVO = agg.getChildrenVO();
			for(CircularlyAccessibleValueObject child : childrenVO) {
				helpcodes.add((String)child.getAttributeValue("cmaterialvid"));
			}
		}
		SqlBuilder sb = new SqlBuilder();
		sb.append(" materialmnecode", helpcodes.toArray(new String[0]));
		sb.append(" or code", helpcodes.toArray(new String[0]));
		try {
			NCObject[] ncobjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(MaterialVO.class, 
					sb.toString(), 
					new String[]{"materialmnecode", "code"}, 
					true);
			if(ncobjects != null && ncobjects.length > 0) {
				for(NCObject obj : ncobjects) {
					//������
					helpcodesAndsku_map.put((String)obj.getAttributeValue("materialmnecode"), (String)obj.getAttributeValue("pk_material"));
					//���ϱ���
					helpcodesAndsku_map.put((String)obj.getAttributeValue("code"), (String)obj.getAttributeValue("pk_material"));
				}
			}
			
		} catch (MetaDataException e) {
			ExceptionUtils.wrappException(e);
		}
		return helpcodesAndsku_map;
	}

}
