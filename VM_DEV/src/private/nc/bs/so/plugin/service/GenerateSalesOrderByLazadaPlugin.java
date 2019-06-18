package nc.bs.so.plugin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.ic.pub.util.BillQueryUtils;
import nc.bs.logging.Logger;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.cmp.tools.UFDoubleUtils;
import nc.itf.uap.busibean.ISysInitQry;
import nc.login.bs.INCUserQueryService;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.so.m30.api.ISaleOrderMaintainAPI;
import nc.vo.fipub.utils.SqlBuilder;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.UserVO;
import nc.vo.so.component.PlatformStatusAndPara;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.restapi.LazadaViewVO;

public class GenerateSalesOrderByLazadaPlugin implements IBackgroundWorkPlugin {
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		PreAlertObject retObj = new PreAlertObject();
	    retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
	    //定时任务设置usercode未L001
	    this.setInvocationInfo();
	    //查询原单数据
	    SaleOrderVO[] vos = this.querySalesOrderData(bgwc);
	    if(vos == null || vos.length < 1) {
	    	ExceptionUtils.wrappBusinessException("===generate salesorder error=== NO so!!!" );
	    }
	    //生成销售订单
	    ISaleOrderMaintainAPI lookup = NCLocator.getInstance().lookup(ISaleOrderMaintainAPI.class);
	    try {
	    	lookup.syncSaleOrders(vos);
		} catch (Exception e) {
			Logger.error("===generate salesorder error===" + e.getMessage());
			ExceptionUtils.wrappException(e);
		}
	    
	    return retObj;
	}

	private void setInvocationInfo() {
		if(InvocationInfoProxy.getInstance().getUserDataSource() == null) {
			InvocationInfoProxy.getInstance().setUserDataSource("VM");
		}
		try {
			UserVO userVO = NCLocator.getInstance().lookup(INCUserQueryService.class)
			          .findUserVO(InvocationInfoProxy.getInstance().getUserDataSource(), "L001");
			if(userVO != null) {
				InvocationInfoProxy.getInstance().setUserId(userVO.getPrimaryKey());
				InvocationInfoProxy.getInstance().setUserCode("L001");
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
	}

	private SaleOrderVO[] querySalesOrderData(BgWorkingContext bgwc) {
		List<LazadaViewVO> lazadaviewvos = new ArrayList<LazadaViewVO>();
		BaseDAO dao = new BaseDAO();
		try {
			//组织可多选
			String pk_group = InvocationInfoProxy.getInstance().getGroupId();
			String[] pk_orgs = bgwc.getPk_orgs();
//			List<String> orgcode = this.getOrgcode(pk_orgs);
			SqlBuilder sb = new SqlBuilder();
			sb.append(" 1=1");
			if(pk_orgs != null && pk_orgs.length > 0) {
				sb.append(" and sb.pk_org", pk_orgs);
			}
			//增加日期过滤
			UFDate startdate = null;
			UFDate enddate = null;
			SysInitVO sysvostart = NCLocator.getInstance().lookup(ISysInitQry.class).queryByParaCode(pk_group, "SOLAZADA01");//开始时间
			SysInitVO sysvoend = NCLocator.getInstance().lookup(ISysInitQry.class).queryByParaCode(pk_group, "SOLAZADA02");//结束时间
			if(sysvostart != null && sysvoend != null && sysvostart.getValue() != null && sysvoend.getValue() != null) {
				startdate = new UFDate(sysvostart.getValue());
				enddate = new UFDate(sysvoend.getValue());
				sb.append(" and sb.dbilldate between '");
				sb.append(startdate.asLocalBegin() + "' and '");
				sb.append(enddate.asLocalEnd() + "'");
			} else if(sysvostart != null && sysvoend != null && sysvostart.getValue() != null && sysvoend.getValue() == null) {
				startdate = new UFDate(sysvostart.getValue());
				sb.append(" and sb.dbilldate > '");
				sb.append(startdate.toStdString() + "'");
			} else {
				throw new BusinessException("请设置lazada查询起始时间！销售订单集团级参数");
			}
			//增加状态过滤
			sb.append(" and sb.shippingstatus", PlatformStatusAndPara.GENERATE_ORDERSTATUS);
			Logger.error("===Lazada===" + sb.toString());
			lazadaviewvos = (List<LazadaViewVO>) dao.retrieveByClause(LazadaViewVO.class, sb.toString());
			return this.contructSaleOrderVO(lazadaviewvos);
		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return null;
	}
	
	private List<String> getOrgcode(String[] pk_orgs) {
		List<String> orgcodes = new ArrayList<String>();
		SqlBuilder sb = new SqlBuilder();
		sb.append("pk_org", pk_orgs);
		try {
			Collection<OrgVO> c = MDPersistenceService.lookupPersistenceQueryService().
					queryBillOfVOByCond(OrgVO.class, sb.toString(), false);
			OrgVO[] orgcode = (OrgVO[]) c.toArray(new OrgVO[0]);
			if(c == null || c.size() == 0) {
				return null;
			}
			for(OrgVO org : orgcode) {
				orgcodes.add(org.getCode());
			}
			return orgcodes;
		} catch (MetaDataException e) {
			ExceptionUtils.wrappBusinessException("===lazada query org error!!!===" + e.getMessage());
		}
		return null;
	}

	private SaleOrderVO[] contructSaleOrderVO(List<LazadaViewVO> lazadaviewvos) {
		if(lazadaviewvos == null || lazadaviewvos.size() < 1) {
			return null;
		} 
		List<String> orderids = new ArrayList<String>();
		List<SaleOrderVO> saleordervos = new ArrayList<SaleOrderVO>();
		//表头list
		List<SaleOrderHVO> hvos = new ArrayList<SaleOrderHVO>();
		//字表
		List<SaleOrderBVO> bvos = new ArrayList<SaleOrderBVO>();
		
		for(LazadaViewVO viewvo : lazadaviewvos) {
			SaleOrderHVO hvo = null;
			if(!orderids.contains(viewvo.getOrderid())) {
				hvo = new SaleOrderHVO();
				//pk_org  主表
				hvo.setPk_org(viewvo.getPk_org());
				//dbilldate
				hvo.setDbilldate(new UFDate(viewvo.getDbilldate()));
				//Vtrantypecode
				hvo.setVtrantypecode("30-Cxx-02");
				//币种
				hvo.setCorigcurrencyid(viewvo.getCorigcurrencyid());
				hvo.setPk_group(InvocationInfoProxy.getInstance().getGroupId());
				if(PlatformStatusAndPara.SG_PKORG.equals(viewvo.getPk_org())) {
					hvo.setCdeptid(PlatformStatusAndPara.SG_DEPTID);
					hvo.setCcustomerid(PlatformStatusAndPara.SG_CUSTOMERID);
				} else {
					hvo.setCdeptid(PlatformStatusAndPara.CN_DEPTID);
					hvo.setCcustomerid(PlatformStatusAndPara.CN_CUSTOMERID);
					
				}
				hvo.setVbillcode(null);
				//国家(交易平台) vdef1 如果是SG 设置为004 
				if(PlatformStatusAndPara.SG_PKORG.equals(viewvo.getPk_org())) {
					hvo.setVdef1(PlatformStatusAndPara.SG_PLATFORMSTR);//LAZADA SG
				} else {
					hvo.setVdef1(PlatformStatusAndPara.CN_PLATFORMSTR + viewvo.getCountry());
				}
				//平台订单号 
				hvo.setVdef2(viewvo.getOrderid());
				//联系人
				hvo.setVdef3(viewvo.getFullname());
				//联系电话
				hvo.setVdef4(viewvo.getShippingphone());
				//地址
				hvo.setVdef5(viewvo.getFulladdress());
				//运输方式
				hvo.setVdef7(viewvo.getShippingtype());
				//订单状态
				hvo.setVdef20(viewvo.getShippingstatus());
				hvos.add(hvo);
			}
			orderids.add(viewvo.getOrderid());
			
			
			SaleOrderBVO bvo = new SaleOrderBVO();
			//sku
			bvo.setCmaterialvid(viewvo.getSku());
			
			//价税合计 norigtaxmny  主含税单价  - 折扣额
			UFDouble norigtaxmny = UFDoubleUtils.sub(viewvo.getNorigtaxmny(), viewvo.getNorigdiscount());
			UFBoolean isGift = this.verfyIsGift(norigtaxmny);
			bvo.setNorigtaxmny(norigtaxmny);
			bvo.setNqtorigtaxprice(viewvo.getNorigtaxmny());
			
			bvo.setBlargessflag(isGift);
			//数量
			bvo.setNqtunitnum(viewvo.getNqtunitnum());
			bvo.setNastnum(viewvo.getNqtunitnum());//需要处理
			bvo.setNnum(viewvo.getNqtunitnum());
			
			//主表id
			bvo.setCfirstid(viewvo.getOrderid()); //用于判断是否属于主表
			bvos.add(bvo);
		}
		
		//组装 
		saleordervos = this.constructSaleOrderVO(hvos, bvos, saleordervos);
		
		//若时退货(Returned) 金额为负数  处理
		saleordervos = this.processReturn(saleordervos);
		
		return saleordervos.toArray(new SaleOrderVO[0]);
	}
	

	private List<SaleOrderVO> processReturn(List<SaleOrderVO> saleordervos) {
		Iterator<SaleOrderVO> iterator = saleordervos.iterator();
		while(iterator.hasNext()) {
			SaleOrderVO next = iterator.next();
			UFBoolean returnflag = PlatformStatusAndPara.LAZADA_RETURNED.equals(next.getParentVO().getVdef20().toLowerCase()) ? UFBoolean.TRUE : UFBoolean.FALSE;
			//假设没有几笔退货的单据
			if(returnflag.booleanValue() && this.isAlreadyGenerateSO(next)) {
				iterator.remove();
				continue;
			}
			if(returnflag.booleanValue()) {
				next.getParentVO().setVdef19(UFBoolean.TRUE.toString()); 
			} else {
				next.getParentVO().setVdef19(UFBoolean.FALSE.toString());
			}
			SaleOrderBVO[] childrenVO = next.getChildrenVO();
			for(SaleOrderBVO child : childrenVO) {
				if(returnflag.booleanValue()) {
					//价税合计
					child.setNorigtaxmny(UFDoubleUtils.multiply(new UFDouble(-1), child.getNorigtaxmny()));  
					//数量
					child.setNqtunitnum(UFDoubleUtils.multiply(new UFDouble(-1), child.getNqtunitnum()));
					child.setNastnum(UFDoubleUtils.multiply(new UFDouble(-1),child.getNastnum()));//需要处理
					child.setNnum(UFDoubleUtils.multiply(new UFDouble(-1),child.getNnum()));
				}
				
			}
		}
		return saleordervos;
	}

	private boolean isAlreadyGenerateSO(SaleOrderVO saleorder) {
		String orderid = saleorder.getParentVO().getVdef2();
		// 订单号
	    SqlBuilder whereSql = new SqlBuilder();
	    whereSql.append(" from so_saleorder where vdef2 ",
	    		orderid);
	    whereSql.append("and dr = '0' and vdef19='Y'");
	    BillQueryUtils<SaleOrderVO> util =
	        new BillQueryUtils<SaleOrderVO>(new SaleOrderVO());
	    SaleOrderVO[] saleorderVO = util.queryBills(whereSql.toString());
	    if(saleorderVO != null && saleorderVO.length > 0) {
	    	return true;
	    }
	    return false;
	}

	private List<SaleOrderVO> constructSaleOrderVO(List<SaleOrderHVO> hvos,
			List<SaleOrderBVO> bvos, List<SaleOrderVO> saleordervos) {
		for(SaleOrderHVO hvo : hvos) {
			SaleOrderVO ordervo = new SaleOrderVO();
			ordervo.setParentVO(hvo);
			String orderid = hvo.getVdef2();
			List<SaleOrderBVO> bvoss = new ArrayList<SaleOrderBVO>();
			for(SaleOrderBVO bo : bvos) {
				if(orderid.equals(bo.getCfirstid())) {
					bvoss.add(bo);
				}
			}
			ordervo.setChildrenVO(bvoss.toArray(new SaleOrderBVO[0]));
			saleordervos.add(ordervo);
		}
		return saleordervos;
	}

	/**
	 * 是否赠品
	 * @param sub
	 * @return
	 */
	private UFBoolean verfyIsGift(UFDouble sub) {
		if(sub.doubleValue() == 0.0 || UFDouble.ZERO_DBL.equals(sub)) {
			return UFBoolean.TRUE;
		}
		return UFBoolean.FALSE;
	}

}
