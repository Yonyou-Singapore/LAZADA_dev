package nc.impl.so.apiservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.data.bill.BillOperator;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillItemVO;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillVO;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.pub.so.apiservice.ILazadaService;
import nc.pubitf.so.m30.api.ISaleOrderMaintainAPI;
import nc.pubitf.so.m30.api.rest.SaleOrderTranslateAdaptor;
import nc.pubitf.so.restapi.jsonservice.IJsonServiceForRestful;
import nc.uap.ws.console.utils.vo.Json;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.restapi.LazadaAggVO;
import nc.vo.so.restapi.RestMessageVO;
import nc.vo.so.restapi.restlog.RestLogVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;

public class LazadaServiceImpl implements ILazadaService {
	@Override
	public void saveLazadaAggVO(LazadaAggVO[] aggvos) throws BusinessException {
		BillOperator<LazadaAggVO> operator = new BillOperator<LazadaAggVO>();
		operator.insert(aggvos);
	}

	@Override
	public Map<String, UFBoolean> isGenerateSo() throws BusinessException {
		// TODO Auto-generated method stub
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
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct order_id from DATA_LAZADA_BILL WHERE order_id =");
		sql.append(orders.get(0));
		IRowSet rowset = new DataAccessUtils().query(sql.toString());
		if(rowset != null && rowset.size() > 0) {
			String[] arr = rowset.toOneDimensionStringArray();
			for(String str : arr) {
				executeQuery.add(str);
			}
		}
		return executeQuery;
	}
}
