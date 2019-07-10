package nc.pubimpl.so.m30.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ic.pub.util.BillQueryUtils;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.bd.material.custmaterial.ICustMaterialQueryService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.uap.busibean.ISysInitQry;
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
import nc.vo.pub.para.SysInitVO;
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
import nc.vo.uapbd.custmaterial.CustMaterialVO;
import nc.vo.uapbd.custom.CustomVO;

import org.apache.commons.lang.StringUtils;

/**
 * @description
 *              销售订单持久化服务实现1
 * @scene
 * 
 * @param 无
 * 
 * 
 * @since 6.5
 * @version 2015-10-20 下午1:52:34
 * @author 刘景
 */
public class SaleOrderMaintainAPIImpl implements ISaleOrderMaintainAPI {
  @Override
  public SaleOrderVO[] insertBills(SaleOrderVO[] vos) throws BusinessException {
    SaleOrderVO[] fillvos = vos;

    // 1、基本空校验
    BillVOsCheckRule checker = new BillVOsCheckRule(new SaleOrderValidator());
    checker.check(fillvos);

    // 2、强制默认值填充
    SaleOrderSaveDefValue filldatarule = new SaleOrderSaveDefValue();
    filldatarule.setDefultValue(fillvos);
    
    // 2. Modified on 2018-01-23
    // 更改SaleOrderVO的自动计算逻辑
//    SaleOrderElkenCalculator cal = new SaleOrderElkenCalculator();
//    cal.calculate(fillvos);
    
    
    // 3、有值不覆盖
    SaleOrderVO[] combinBillVOs =
        (SaleOrderVO[]) AggVOUtil.combinBillVO(fillvos, vos);

    // 4、保存
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
		// 单据号在系统中已经存在的数据
		List<String> infos = null;
		StringBuilder sb = new StringBuilder();
		SaleOrderVO[] existvos = this.queryBills(vos);
		if (existvos != null && existvos.length > 0) {
			for(SaleOrderVO vo : existvos) {
				sb.append("[" + vo.getParentVO().getVdef2() + "],");
			}
			ExceptionUtils.wrappBusinessException("Lazada原单生成销售订单重复，请检查订单号: " + sb.toString());
		}
		//设置必须要有的默认值 购销类型， 税码等等
		this.setDefaultForLazada(vos); 
		
		this.insertBills(vos);
		// Modified by Ethan on 2018-03-23
		// 因为SO是一个接一个同步的 所以直接传上去
		//根据参数确定是否自动审批
		ISysInitQry lookup = NCLocator.getInstance().lookup(ISysInitQry.class);
		SysInitVO para = lookup.queryByParaCode(vos[0].getParentVO().getPk_org(), "SO99");
		if(para != null && "Y".equals(para.getValue())) {
			PfServiceScmUtil.processBatch(SOConstant.APPROVE,
		            SOBillType.Order.getCode(), vos, null, null);
		}
		return null;
	}
	
    private void setDefaultForLazada(SaleOrderVO[] vos) {
    	Map<String, String> queryHelpSkuPK = this.queryHelpSkuPK(vos);
    	Map<String, String> queryCodeSkuPK = this.querySkuCodePK(vos);
    	//使用客户物料码-集团 匹配sku	   add by weiningc 20190701 start 
    	Map<String, String> custmaterials = this.transferSkuByCustomerSku(vos);
    	//end
    	//sku 匹配
    	this.setSkuPk(vos, custmaterials);
    	//组织匹配
//    	this.setPk_org(vos);
    	//自定义项
    	this.setDef(vos);
    	//币种
//    	Map<String, String> idCOdeMap = new HashMap<String, String>();
//    	Set<String> defcodes = new HashSet<String>();
//    	for(SaleOrderVO vo : vos) {
//    		defcodes.add(vo.getParentVO().getCorigcurrencyid());
//    	}
//    	SqlBuilder sb = new SqlBuilder();
//    	sb.append("select code, pk_currtype from bd_currtype where");
//    	sb.append(" code", defcodes.toArray(new String[0]));
//    	DataAccessUtils util = new DataAccessUtils();
//    	IRowSet rowset = util.query(sb.toString());
//    	while (rowset.next()) {
//    		idCOdeMap.put(rowset.getString(0), rowset.getString(1));
//    	}
//    	for(SaleOrderVO vo : vos) {
//    		vo.getParentVO().setCorigcurrencyid(idCOdeMap.get(vo.getParentVO().getCorigcurrencyid()));
//    	}
		
	}

    private Map<String, String> transferSkuByCustomerSku(SaleOrderVO[] vos) {
		Set<String> ordersku = new HashSet<String>();
		//key 客户物料码code, value: 物料的PK
		Map<String, String> custommap = new HashMap<String, String>();
		for(AggregatedValueObject agg : vos) {
			CircularlyAccessibleValueObject[] childrenVO = agg.getChildrenVO();
			for(CircularlyAccessibleValueObject child : childrenVO) {
				ordersku.add((String)child.getAttributeValue("cmaterialvid"));
			}
		}
		try {
			SqlBuilder sb = new SqlBuilder();
			sb.append("code", ordersku.toArray(new String[0]));
			sb.append(" and dr=0");
			CustMaterialVO[] custommaterialvos = NCLocator.getInstance().lookup(ICustMaterialQueryService.class)
				.queryVOsByCondition(sb.toString());
			if(custommaterialvos != null) {
				for(CustMaterialVO vo : custommaterialvos) {
					custommap.put(vo.getCode(), vo.getMaterialid());
				}
			}
			
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return custommap;
		
		
	}

	private void setDef(SaleOrderVO[] vos) { 
    	StringBuffer err = new StringBuffer();
    	Map<String, String> idCOdeMap = new HashMap<String, String>();
    	Set<String> defcodes = new HashSet<String>();
    	//运输方式(自定义项) 1001G61000000000RCL3
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
    		//交易平台
    		vo.getParentVO().setVdef1(idCOdeMap.get(vo.getParentVO().getVdef1()));
    		//运输方式
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
//    		ExceptionUtils.wrappBusinessException("交易平台不匹配: " + err.toString());
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

	private void setSkuPk(SaleOrderVO[] vos, Map<String, String> custmaterials) {
		StringBuffer error = new StringBuffer();
		for(SaleOrderVO vo : vos) { 
			
			SaleOrderBVO[] childrenVO = vo.getChildrenVO();
			for(SaleOrderBVO child : childrenVO) {
				String skuPK = custmaterials.get(child.getCmaterialvid());
				if(StringUtils.isBlank(skuPK)) {
					error.append("order:" + vo.getParentVO().getVdef2() +" ,sku [" + child.getCmaterialvid() + "], Country:[" + vo.getParentVO().getVdef1() + "]");
				}
				child.setCmaterialvid(skuPK);
				child.setCmaterialid(skuPK);
				//赠品默认值处理
				if(child.getBlargessflag() == null) {
					child.setBlargessflag(UFBoolean.FALSE);
				}
			}
		}
		if(error.length() > 0) {
			ExceptionUtils.wrappBusinessException("客户物料码不匹配, : " + error.toString()); 
		}
		
	}

/**
   * 审批
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
   * 根据订单号查询订单
   * 
   * @param vos
   * @return
   * @throws BusinessException
   */
  private SaleOrderVO[] queryBills(SaleOrderVO[] vos) throws BusinessException {
    // 订单号
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
  
  private Map<String, String> queryHelpSkuPK(SaleOrderVO[] aggs) {
		Map<String, String> helpcodesAndsku_map = new HashMap<String, String>();
		Set<String> helpcodes = new HashSet<String>();
		for(AggregatedValueObject agg : aggs) {
			CircularlyAccessibleValueObject[] childrenVO = agg.getChildrenVO();
			for(CircularlyAccessibleValueObject child : childrenVO) {
				helpcodes.add((String)child.getAttributeValue("cmaterialvid"));
			}
		}
		SqlBuilder sb = new SqlBuilder();
		sb.append(" materialmnecode", helpcodes.toArray(new String[0]));
		try {
			NCObject[] ncobjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(MaterialVO.class, 
					sb.toString(), 
					new String[]{"materialmnecode", "code"}, 
					true);
			if(ncobjects != null && ncobjects.length > 0) {
				for(NCObject obj : ncobjects) {
					//助记码
					if(!StringUtils.isEmpty((String)obj.getAttributeValue("materialmnecode"))) {
						helpcodesAndsku_map.put((String)obj.getAttributeValue("materialmnecode"), (String)obj.getAttributeValue("pk_material"));
					}
				}
			}
			
		} catch (MetaDataException e) {
			ExceptionUtils.wrappException(e);
		}
		return helpcodesAndsku_map;
	}
  
  private Map<String, String> querySkuCodePK(SaleOrderVO[] aggs) {
		Map<String, String> helpcodesAndsku_map = new HashMap<String, String>();
		Set<String> helpcodes = new HashSet<String>();
		for(AggregatedValueObject agg : aggs) {
			CircularlyAccessibleValueObject[] childrenVO = agg.getChildrenVO();
			for(CircularlyAccessibleValueObject child : childrenVO) {
				helpcodes.add((String)child.getAttributeValue("cmaterialvid"));
			}
		}
		SqlBuilder sb = new SqlBuilder();
		sb.append(" code", helpcodes.toArray(new String[0]));
		try {
			NCObject[] ncobjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(MaterialVO.class, 
					sb.toString(), 
					new String[]{"materialmnecode", "code"}, 
					true);
			if(ncobjects != null && ncobjects.length > 0) {
				for(NCObject obj : ncobjects) {
					//物料编码
					helpcodesAndsku_map.put((String)obj.getAttributeValue("code"), (String)obj.getAttributeValue("pk_material"));
				}
			}
			
		} catch (MetaDataException e) {
			ExceptionUtils.wrappException(e);
		}
		return helpcodesAndsku_map;
	}

}
