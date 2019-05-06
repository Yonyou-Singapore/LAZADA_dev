package nc.pub.so.apiservice;

import java.util.List;
import java.util.Map;

import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillItemVO;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.restapi.LazadaAggVO;

/**
 * 
 * @author weiningc
 *
 */
public interface ILazadaService {
	/**
	 * 
	 * @param aggvos
	 * @throws BusinessException
	 */
	void saveLazadaAggVO(LazadaAggVO[] aggvos) throws BusinessException;
	
	/**
	 * Lazada 订单是否已生成NC销售订单
	 * @return
	 * @throws BusinessException
	 */
	Map<String, UFBoolean> isGenerateSo() throws BusinessException;
	
	
	/**
	 * luolanc 专用
	 */
	void insertlazadaresponse(LazadaBillVO[] headvos, LazadaBillItemVO[] itemvos);
	
	/**
	 * ejb 查询
	 * @param orders
	 * @return
	 */
	List<String> queryExistLazadaOrder(List<String> orders);
}
