package nc.pubitf.so.m30.api;

import java.util.List;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.restapi.RestMessageVO;

/**
 * @description 
 *              <ul>
 *              <li>�����������۶���
 *              <li>�������۶�������ɾ�����۶���
 *              <li>�������۶�����Դ����IDɾ������
 *              </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName ���۶����־û�����
 * 
 * @since 6.5
 * @version 2015-10-20 ����1:49:10
 * @author ����
 */
@Component("���۶���")
@OpenAPI(value = OpenLevel.OPENED)
public interface ISaleOrderMaintainAPI {

  /**
   * <B>�����������۶���</B> <li>�����Դ���Ĳ�����������У���Ĭ��ֵ���</li>
   * 
   * <li>����Ϊ��У��</li> ���۶������������ֶ��У�</br>
   * ��ͷ��������֯��pk_org��������
   * ��cdeptid�����������ͣ�ctrantypeid�����ͻ���ccustomerid�������֣�corigcurrencyid��</br>
   * ���壺���ϣ�cmaterialvid������������nnum����������nastnum������ͬʱΪ�գ���˰���ۣ�nqtorigtaxprice��������˰����
   * ��norigtaxprice�� ����˰�ϼƣ�norigtaxmny������ͬʱΪ�գ�</br>
   * 
   * <li>Ĭ��ֵ��䣨��ֵ�����ǣ�</li> ��ͷ������״̬�������ۿۡ���Ա���������ͱ��롢ҵ�����̡��տ�Э�顢��Ʊ�ͻ�����������</br>
   * ���壺����λ����λ�������ʡ����������֯��������֯��������֯��Ӧ����֯���������ġ���λ�ҡ��۱����ʡ��ջ��ͻ������ҡ��������͡�˰��˰�롢�ֿ⡢���š�
   * ȫ�ֻ��ʡ��ƻ��������ڡ�Ҫ�󵽻����ڡ� �����ۿۡ���Ʒ�ۿۡ���״̬����ͷ�ϼ��ֶΡ�
   * 
   * <li>��������¼������������ۡ����</li> 1.�������������������ס�����������۽���㷨������������</br>
   * 2.����м�˰�ϼƣ������ü�˰�ϼƴ������۽���㷨�������õ��۴������۽���㷨��</br>
   * 3.����к�˰���ۣ������ú�˰���۴������۽���㷨������������˰���۴������۽���㷨��</br>
   * 
   * 
   * @param vos ���۶���VO����
   * @return ���������۶���VO����
   * @throws BusinessException �쳣
   */
  SaleOrderVO[] insertBills(SaleOrderVO[] vos) throws BusinessException;

  /**
   * <B>�������۶�������ɾ�����۶���</B>
   * 
   * @param ids ���۶�����������
   * @return ���������۶���VO����
   * @throws BusinessException �쳣
   */
  void deleteBillsByID(String[] ids) throws BusinessException;

  /**
   * ͬ�����۶���
   * 
   * @param vos
   * @return ���ص���ͬ���ɹ��Ķ���
   * @throws BusinessException
   * @throws Exception 
   */
  public RestMessageVO syncSaleOrders(SaleOrderVO[] vos) throws BusinessException, Exception;

}
