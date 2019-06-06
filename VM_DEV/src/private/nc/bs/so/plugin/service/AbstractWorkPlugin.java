package nc.bs.so.plugin.service;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taobao.api.domain.Trade;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillVO;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderDetailResponse;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.login.bs.INCUserQueryService;
import nc.pub.templet.converter.util.helper.ExceptionUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.sm.UserVO;

public abstract class AbstractWorkPlugin implements IBackgroundWorkPlugin {
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	 * @param conn
	 * @param jsonstr
	 * @return
	 */
	abstract String requestSystem(URLConnection conn, String jsonstr);
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹json
	 * @param data
	 * @return
	 */
	abstract String constructRequestJson(List<Map<String, Object>> data);
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹json涓簊upervo
	 * @param response
	 * @return
	 */
	abstract SuperVO[] handleReponse(String response);
	
	/**
	 * 
	 * @param supervos
	 */
	public void dbProcessForInsert(SuperVO[] supervos) {
		if(supervos == null || supervos.length < 1) {
			return;
		}
		try {
			new BaseDAO().insertVOArray(supervos);
		} catch (DAOException e) {
			ExceptionUtils.wrapException(e);
		}
	}
	
	public void dbProcessForLazadaStatusUpdate(List<LazadaGetOrderDetailResponse> itemsList,String updateTimestamp) throws DAOException {
	
		
		//閹靛綊鍣洪弴瀛樻煀閻樿埖锟斤拷
		List<Map<String,String>> executeQuery = new ArrayList<Map<String,String>>();

		StringBuffer caseString = new StringBuffer();
		StringBuffer idCondition = new StringBuffer();
		for(LazadaGetOrderDetailResponse billvo : itemsList){
			caseString.append("WHEN '"+billvo.getOrder_id()+"' THEN '"+billvo.getStatuses().get(0)+"' ");
			idCondition.append("'"+billvo.getOrder_id()+"',");
		}
		
		
		String idString = idCondition.substring(0, idCondition.length()-1);
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE DATA_LAZADA_BILL SET STATUSES = CASE order_id " 
		        +caseString.toString()
		        +"END, "
		        +"lastupdatetime = '"
		        +updateTimestamp
				+"' WHERE order_id IN ("
				+idString);
		sql.append(")");
		BaseDAO dao = new BaseDAO();
		
		try {	
			int i = dao.executeUpdate(sql.toString());
			Logger.info("閺堫剚顐奸崗杈ㄥ灇閸旂喐娲块弬锟�+i+");
		} catch (DAOException e) {
			ExceptionUtils.wrapException(e);
		}
		
	}
	
		public void dbProcessForTaobaoStatusUpdate(Trade trade,String updateTimestamp) throws DAOException {
	
		
		//閹靛綊鍣洪弴瀛樻煀閻樿埖锟斤拷
		List<Map<String,String>> executeQuery = new ArrayList<Map<String,String>>();

		StringBuffer caseString = new StringBuffer();
		StringBuffer idCondition = new StringBuffer();
		
		caseString.append("WHEN '"+trade.getTid()+"' THEN '"+trade.getStatus()+"' ");
		idCondition.append("'"+trade.getTid()+"',");
		
		
		
		String idString = idCondition.substring(0, idCondition.length()-1);
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE DATA_LAZADA_BILL SET STATUSES = CASE order_id " 
		        +caseString.toString()
		        +"END, "
		        +"lastupdatetime = '"
		        +updateTimestamp
				+"' WHERE order_id IN ("
				+idString);
		sql.append(")");
		BaseDAO dao = new BaseDAO();
		
		try {	
			int i = dao.executeUpdate(sql.toString());
			Logger.info("閺堫剚顐奸崗杈ㄥ灇閸旂喐娲块弬锟�+i+");
		} catch (DAOException e) {
			ExceptionUtils.wrapException(e);
		}
		
	}
	
	public List<String> queryExistLazadaOrder(List<String> orderids) {
		List<String> executeQuery = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct order_id from DATA_LAZADA_BILL WHERE order_id =");
		sql.append(orderids.get(0));
		BaseDAO dao = new BaseDAO();
		
		
		try {
			executeQuery = (List<String>) dao.executeQuery(sql.toString(), new ColumnListProcessor());
		} catch (DAOException e) {
			ExceptionUtils.wrapException(e);
		}
		return executeQuery;
	}
	
	public List<String> queryLazadaOrderLastUpdateTime(String orgId) {
		List<String> executeQuery = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select MAX(lastUpdateTime) from DATA_LAZADA_BILL");
		BaseDAO dao = new BaseDAO();
		try {
			executeQuery = (List<String>) dao.executeQuery(sql.toString(), new ColumnListProcessor());
		} catch (DAOException e) {
			ExceptionUtils.wrapException(e);
		}
		return executeQuery;
	}
	
	public List<String> queryTaobaoOrderLastUpdateTime(String orgId) {
		List<String> executeQuery = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select MAX(lastUpdateTime) from DATA_LAZADA_BILL where orgid = 'TAOBAO'");
		BaseDAO dao = new BaseDAO();
		try {
			executeQuery = (List<String>) dao.executeQuery(sql.toString(), new ColumnListProcessor());
		} catch (DAOException e) {
			ExceptionUtils.wrapException(e);
		}
		return executeQuery;
	}
	
	
	
	public List<Map<String,String>> queryExistLazadaOrderByStatus(List<String> statuslist,String orgId) {
		List<Map<String,String>> executeQuery = new ArrayList<Map<String,String>>();
		
		StringBuffer statusCondition = new StringBuffer();
		for(String status: statuslist){
			statusCondition.append("'");
			statusCondition.append(status);
			statusCondition.append("',");
		}
		
		String statusString = statusCondition.substring(0, statusCondition.length()-1);
		StringBuffer sql = new StringBuffer();
		sql.append("select order_id,updated_at,orgid,requestUrl from DATA_LAZADA_BILL WHERE " 
		        +"orgid = '" 
				+ orgId 
				+"' and " 
				+"STATUSES in (");
		sql.append(statusString);
		sql.append(")");
		BaseDAO dao = new BaseDAO();
		try {
			executeQuery = (List<Map<String,String>>) dao.executeQuery(sql.toString(), new MapListProcessor());
		} catch (DAOException e) {
			ExceptionUtils.wrapException(e);
		}
		return executeQuery;
	}
}
