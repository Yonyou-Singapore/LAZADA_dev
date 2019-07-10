package nc.pub.so.apiservice;

import java.util.List;
import java.util.Map;

import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillItemVO;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.so.component.AggSo_ordercenter;
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
	
	void updateTmallStatus(LazadaBillVO[] headvos,
			LazadaBillItemVO[] itemvos);
	
	
	/**
	 * ejb 查询
	 * @param orders
	 * @return
	 */
	List<String> queryExistLazadaOrder(List<String> orders);
	
	/**
	 * 下载原单
	 * @throws BusinessException 
	 */
	public void downloadOrderCenter() throws BusinessException;
	
	/**
	 * 下载taobao原单
	 * @throws BusinessException 
	 */
	public void downloadTaobaoOrderCenter() throws BusinessException;
	
	
	/**
	 * 下载选定的原单
	 * @throws BusinessException 
	 * String[] platform 平台
	 * String[] orgs 公司
	 * UFDate startdate create after
	 * UFDate enddate create before
	 */
	public void downloadSelectOrderCenter(String[] platform,String[] orgs, UFDate startdate, UFDate enddate) throws BusinessException;
	
	/**
	 * 更新lazada订单状态到readytoship
	 * @throws BusinessException 
	 * @param String order_item_ids lazada原单物品ID，格式为"[2132243,132343]"
	 * @param String shipProvider lazada运输供应商
	 * @param String trackingNo  快递追踪号
	 * @param String country 国家
	 */
	public String updateLazadaOrderStatus(String order_item_ids, String platform,String shipProvider,String trackingNo,String country) throws BusinessException;
	
	/**
	 * 生成销售订单
	 * @param agg
	 * @return 
	 * @throws BusinessException
	 */
	AggSo_ordercenter[] generateSalesOrderByOrdercenter(AggSo_ordercenter agg) throws BusinessException;

	AggSo_ordercenter updateLazadaOrderStatus(AggSo_ordercenter selectedData);
}
