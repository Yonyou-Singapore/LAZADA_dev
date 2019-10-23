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
	 * 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗
	 * @param conn
	 * @param jsonstr
	 * @return
	 */
	abstract String requestSystem(URLConnection conn, String jsonstr);
	
	/**
	 * 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗json
	 * @param data
	 * @return
	 */
	abstract String constructRequestJson(List<Map<String, Object>> data);
	
	/**
	 * 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚筳son娑撶皧upervo
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
	
		
		//闁归潧缍婇崳娲即鐎涙ɑ鐓�闁绘鍩栭敓鏂ゆ嫹
		List<Map<String,String>> executeQuery = new ArrayList<Map<String,String>>();

		StringBuffer caseString = new StringBuffer();
		StringBuffer idCondition = new StringBuffer();
		for(LazadaGetOrderDetailResponse billvo : itemsList){
			caseString.append("WHEN '"+billvo.getOrder_id()+"' THEN '"+billvo.getStatuses().get(0)+"' ");
			idCondition.append("'"+billvo.getOrder_id()+"',");
		}
		
		
		String idString = idCondition.substring(0, idCondition.length()-1);
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE DATA_LAZADA_BILL SET ORDER_STATUS = CASE order_id " 
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
			Logger.info("璁㈠崟鐘舵�佸凡鏇存柊");
		} catch (DAOException e) {
			ExceptionUtils.wrapException(e);
		}
		
	}
	
		public void dbProcessForTaobaoStatusUpdate(Trade trade,String updateTimestamp) throws DAOException {
	
		
		//闁归潧缍婇崳娲即鐎涙ɑ鐓�闁绘鍩栭敓鏂ゆ嫹
		List<Map<String,String>> executeQuery = new ArrayList<Map<String,String>>();

		StringBuffer caseString = new StringBuffer();
		StringBuffer idCondition = new StringBuffer();
		
		caseString.append("WHEN '"+trade.getTid()+"' THEN '"+trade.getStatus()+"' ");
		idCondition.append("'"+trade.getTid()+"',");
		
		
		
		String idString = idCondition.substring(0, idCondition.length()-1);
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE DATA_LAZADA_BILL SET ORDER_STATUS = CASE order_id " 
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
			Logger.info("璁㈠崟鐘舵�佸凡鏇存柊");
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
	
	public List<String> queryLazadaOrderLastUpdateTime() {
		List<String> executeQuery = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select MAX(lastUpdateTime) from DATA_LAZADA_BILL where platform = 2");
		BaseDAO dao = new BaseDAO();
		try {
			executeQuery = (List<String>) dao.executeQuery(sql.toString(), new ColumnListProcessor());
		} catch (DAOException e) {
			ExceptionUtils.wrapException(e);
		}
		
		
		return executeQuery;
	}
	
	public List<String> queryTaobaoOrderLastUpdateTime() {
		List<String> executeQuery = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select MAX(created_at) from DATA_LAZADA_BILL where platform = 1");
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
