package nc.impl.so.apiservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.ic.pub.util.BillQueryUtils;
import nc.bs.logging.Logger;
import nc.bs.so.plugin.service.LazadaGetOrderService;
import nc.bs.so.plugin.service.LazadaGetSelectOrderService;
import nc.bs.so.plugin.service.LazadaReadyToShopService;
import nc.bs.so.plugin.service.TaobaoGetOrderService;
import nc.bs.so.plugin.service.TaobaoGetSelectOrderService;
import nc.cmp.tools.UFDoubleUtils;
import nc.impl.pubapp.pattern.data.bill.BillOperator;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillItemVO;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillVO;
import nc.pub.so.apiservice.ILazadaService;
import nc.pubitf.so.m30.api.ISaleOrderMaintainAPI;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.component.AggSo_ordercenter;
import nc.vo.so.component.PlatFormVO;
import nc.vo.so.component.PlatformStatusAndPara;
import nc.vo.so.component.So_ordercenter_b;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.restapi.LazadaAggVO;


public class LazadaServiceImpl implements ILazadaService {
	@Override
	public void saveLazadaAggVO(LazadaAggVO[] aggvos) throws BusinessException {
		BillOperator<LazadaAggVO> operator = new BillOperator<LazadaAggVO>();
		operator.insert(aggvos);
	}

	@Override
	public Map<String, UFBoolean> isGenerateSo() throws BusinessException {
		return null;
	}

	

	@Override
	public void insertlazadaresponse(LazadaBillVO[] headvos,
			LazadaBillItemVO[] itemvos) {
		// TODO Auto-generated method stub
		
		try {
			new BaseDAO().insertVOArray(headvos);
			new BaseDAO().insertVOArray(itemvos);
		} catch (DAOException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
	}
	
	
	@Override
	public void updateTmallStatus(LazadaBillVO[] headvos,
			LazadaBillItemVO[] itemvos) {
		// TODO Auto-generated method stub
		String[] fieldNames = {"statuses"};
		String[] fieldNames2 = {"Invoice_number"};
		
		try {
			new BaseDAO().updateVOArray(headvos, fieldNames);
			new BaseDAO().updateVOArray(itemvos, fieldNames2);
		} catch (DAOException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
	}
	


	@Override
	public List<String> queryExistLazadaOrder(List<String> orders) {
		List<String> executeQuery = new ArrayList<String>();
//		StringBuffer sql = new StringBuffer();
//		sql.append("select distinct order_id from DATA_LAZADA_BILL WHERE order_id =");
//		sql.append(orderids.get(0));
//		BaseDAO dao = new BaseDAO();
//		try {
//			executeQuery = (List<String>) dao.executeQuery(sql.toString(), new ColumnListProcessor());
//		} catch (DAOException e) {
//			ExceptionUtils.wrapException(e);
//		}
//		return executeQuery;
		SqlBuilder sql = new SqlBuilder();
		sql.append("select distinct order_id from DATA_LAZADA_BILL WHERE order_id", orders.toArray(new String[0]));
//		sql.append(orders.get(0));
		IRowSet rowset = new DataAccessUtils().query(sql.toString());
		if(rowset != null && rowset.size() > 0) {
			String[] arr = rowset.toOneDimensionStringArray();
			for(String str : arr) {
				executeQuery.add(str);
			}
		}
		return executeQuery;
	}

	@Override
	public void downloadOrderCenter() throws BusinessException {
		LazadaGetOrderService lazadaservice = new LazadaGetOrderService();
		lazadaservice.executeTask(null);
	}
	
	@Override
	public void downloadTaobaoOrderCenter() throws BusinessException {
		TaobaoGetOrderService taobaoservice = new TaobaoGetOrderService();
		taobaoservice.executeTask(null);
	}
	
	
	/**
	 * 手动拉单
	 * @param String[] platforms 平台列表 
	 * @param String[] orgs 集团列表 
	 * @param UFDate 日期区间开始 
	 * @param UFDate 日期区间结束
	 * 
	 */
	
	@Override
	public void downloadSelectOrderCenter(String[] platforms,String[] orgs, UFDate startdate, UFDate enddate) throws BusinessException {
		List<String> platformList = Arrays.asList(platforms);
		
		if(platformList.size()>0){
			for(String platformName:platformList){
				switch(platformName){
				case PlatFormVO.LAZADA:
					LazadaGetSelectOrderService lazadaservice = new LazadaGetSelectOrderService();
					lazadaservice.execute(orgs, startdate,enddate);
					break;
				case PlatFormVO.TMALL:
					TaobaoGetSelectOrderService taobaoservice = new TaobaoGetSelectOrderService();
					taobaoservice.execute(orgs, startdate, enddate);
					break;
				default:
					break;
				}
			}
		}
		
	}
	
	
	/**
	 * 发货接口
	 * @param String order_item_ids lazada原单物品ID，格式为"[2132243,132343]"
	 * @param String shipProvider lazada运输供应商
	 * @param String trackingNo  快递追踪号
	 * @param String country 国家
	 * 
	 */
	
	@Override
	public String updateLazadaOrderStatus(String order_item_ids, String platform,String shipProvider,String trackingNo,String country) throws BusinessException {
		LazadaReadyToShopService lazadaservice = new LazadaReadyToShopService();
		//BgWorkingContext bgwc = new BgWorkingContext();
		//lazadaservice.executeTask(null);
		lazadaservice.execute(order_item_ids,platform, shipProvider,trackingNo,country);
		return "";
	}

	@Override
	public void generateSalesOrderByOrdercenter(AggSo_ordercenter agg)
			throws BusinessException {
		//组装成SO
		SaleOrderVO[] vos = this.constructSalesOrder(agg);
		//调用接口 生成销售订单
	    ISaleOrderMaintainAPI lookup = NCLocator.getInstance().lookup(ISaleOrderMaintainAPI.class);
	    try {
	    	lookup.syncSaleOrders(vos);
		} catch (Exception e) {
			Logger.error("===generate salesorder error===" + e.getMessage());
			ExceptionUtils.wrappException(e);
		}
		
	}
	
	/**
	 * 
	 * @param agg
	 * @return
	 * @throws BusinessException 
	 */
	private SaleOrderVO[] constructSalesOrder(AggSo_ordercenter agg) throws BusinessException {
		String order_number = agg.getParentVO().getOrder_number();
		if(this.checkIsGenerateSO(order_number)) {
			throw new BusinessException("The source order generated sales order already.");
		}
		//主表
		SaleOrderHVO hvo = this.constructSalesOrderHVO(agg);
		SaleOrderBVO[] bvos = this.constructSalesOrderBVOs(agg);
		SaleOrderVO ordervo = new SaleOrderVO();
		ordervo.setParentVO(hvo);
		ordervo.setChildrenVO(bvos);
		
		return new SaleOrderVO[] {ordervo};
	}
	
	private SaleOrderBVO[] constructSalesOrderBVOs(AggSo_ordercenter agg) {
		//字表
		List<SaleOrderBVO> bvos = new ArrayList<SaleOrderBVO>();
		So_ordercenter_b[] childrenVO = agg.getChildrenVO();
		for(So_ordercenter_b child : childrenVO) {
			SaleOrderBVO bvo = new SaleOrderBVO();
			//sku
			bvo.setCmaterialvid(child.getSku());
			
			//价税合计 norigtaxmny  主含税单价  - 折扣额
			UFDouble norigtaxmny = UFDoubleUtils.sub(child.getItem_price(), child.getVoucher_seller());
			UFBoolean isGift = this.verfyIsGift(norigtaxmny);
			bvo.setNorigtaxmny(norigtaxmny);
			bvo.setNqtorigtaxprice(child.getItem_price());
			
			bvo.setBlargessflag(isGift);
			//数量
			bvo.setNqtunitnum(child.getQty());
			bvo.setNastnum(child.getQty());//需要处理
			bvo.setNnum(child.getQty());
			
			//主表id
			bvo.setCfirstid(child.getOrder_id()); //用于判断是否属于主表
			bvos.add(bvo);
		}
		return bvos.toArray(new SaleOrderBVO[0]);
	}

	private SaleOrderHVO constructSalesOrderHVO(AggSo_ordercenter agg) {
		SaleOrderHVO hvo = new SaleOrderHVO();
		//pk_org  主表
		hvo.setPk_org(agg.getParentVO().getPk_org());
		hvo.setPk_group(agg.getParentVO().getPk_group());
		hvo.setPk_org_v(agg.getParentVO().getPk_org_v());
		//dbilldate
		hvo.setDbilldate(agg.getParentVO().getBilldate());
		//Vtrantypecode
		hvo.setVtrantypecode("30-Cxx-02");
		//币种
		hvo.setCorigcurrencyid(agg.getParentVO().getPk_currtype());
		if(PlatformStatusAndPara.SG_PKORG.equals(agg.getParentVO().getPk_org())) {
			hvo.setCdeptid(PlatformStatusAndPara.SG_DEPTID);
			hvo.setCcustomerid(PlatformStatusAndPara.SG_CUSTOMERID);
		} else {
			hvo.setCdeptid(PlatformStatusAndPara.CN_DEPTID);
			hvo.setCcustomerid(PlatformStatusAndPara.CN_CUSTOMERID);
			
		}
		hvo.setVbillcode(null);
		//国家(交易平台) vdef1 如果是SG 设置为004 
		if(PlatformStatusAndPara.SG_PKORG.equals(agg.getParentVO().getPk_org())) {
			hvo.setVdef1(PlatformStatusAndPara.SG_PLATFORMSTR);//LAZADA SG
		} else {
			hvo.setVdef1(PlatformStatusAndPara.CN_PLATFORMSTR + agg.getParentVO().getBilling_country());
		}
		//平台订单号 
		hvo.setVdef2(agg.getParentVO().getOrder_number());
		//联系人
		hvo.setVdef3(agg.getParentVO().getShipping_last_name() + " " + agg.getParentVO().getShipping_first_name());
		//联系电话
		hvo.setVdef4(agg.getParentVO().getShipping_phone());
		//地址
		hvo.setVdef5(agg.getParentVO().getShipping_address1() + agg.getParentVO().getShipping_address2() + agg.getParentVO().getShipping_address4());
		//运输方式
		hvo.setVdef7(agg.getChildrenVO()[0].getShipping_type());
		//订单状态
		hvo.setVdef20(agg.getParentVO().getOrder_status());
		//字表
		return hvo;
	}

	private boolean checkIsGenerateSO(String order_number) {
		// 订单号
	    SqlBuilder whereSql = new SqlBuilder();
	    whereSql.append(" from so_saleorder where vdef2 ",
	    		order_number);
	    whereSql.append("and dr = '0' and vdef19='Y'");
	    BillQueryUtils<SaleOrderVO> util =
	        new BillQueryUtils<SaleOrderVO>(new SaleOrderVO());
	    SaleOrderVO[] saleorderVO = util.queryBills(whereSql.toString());
	    if(saleorderVO != null && saleorderVO.length > 0) {
	    	return true;
	    }
	    return false;
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
