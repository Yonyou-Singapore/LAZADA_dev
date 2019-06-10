package nc.pub.so.apiservice;

import java.util.List;
import java.util.Map;

import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillItemVO;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
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
	 * Lazada �����Ƿ�������NC���۶���
	 * @return
	 * @throws BusinessException
	 */
	Map<String, UFBoolean> isGenerateSo() throws BusinessException;
	
	
	/**
	 * luolanc ר��
	 */
	void insertlazadaresponse(LazadaBillVO[] headvos, LazadaBillItemVO[] itemvos);
	
	
	/**
	 * ejb ��ѯ
	 * @param orders
	 * @return
	 */
	List<String> queryExistLazadaOrder(List<String> orders);
	
	/**
	 * ����ԭ��
	 * @throws BusinessException 
	 */
	public void downloadOrderCenter() throws BusinessException;
	
	/**
	 * ����taobaoԭ��
	 * @throws BusinessException 
	 */
	public void downloadTaobaoOrderCenter() throws BusinessException;
	
	
	/**
	 * ����ѡ����ԭ��
	 * @throws BusinessException 
	 * String[] platform ƽ̨
	 * String[] orgs ��˾
	 * UFDate startdate create after
	 * UFDate enddate create before
	 */
	public void downloadSelectOrderCenter(String[] platform,String[] orgs, UFDate startdate, UFDate enddate) throws BusinessException;
	
	/**
	 * ����lazada����״̬��readytoship
	 * @throws BusinessException 
	 * @param String order_item_ids lazadaԭ����ƷID����ʽΪ"[2132243,132343]"
	 * @param String shipProvider lazada���乩Ӧ��
	 * @param String trackingNo  ���׷�ٺ�
	 * @param String country ����
	 */
	public String updateLazadaOrderStatus(String order_item_ids, String platform,String shipProvider,String trackingNo,String country) throws BusinessException;
	
}
