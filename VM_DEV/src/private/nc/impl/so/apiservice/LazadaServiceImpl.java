package nc.impl.so.apiservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.so.plugin.service.LazadaGetOrderService;
import nc.bs.so.plugin.service.LazadaGetSelectOrderService;
import nc.bs.so.plugin.service.LazadaReadyToShopService;
import nc.bs.so.plugin.service.TaobaoGetOrderService;
import nc.bs.so.plugin.service.TaobaoGetSelectOrderService;
import nc.impl.pubapp.pattern.data.bill.BillOperator;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillItemVO;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillVO;
import nc.pub.so.apiservice.ILazadaService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.component.PlatFormVO;
import nc.vo.so.restapi.LazadaAggVO;
import edu.emory.mathcs.backport.java.util.Arrays;

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
}
