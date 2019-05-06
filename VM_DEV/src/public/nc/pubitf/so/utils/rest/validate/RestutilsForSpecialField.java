package nc.pubitf.so.utils.rest.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.fipub.utils.SqlBuilder;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class RestutilsForSpecialField {
	
	/**
	 * ����ǰ��ֵһЩĬ��ֵ, ��pk_group��ĳЩ���벻�����ֶ������︳ֵ	
	 */
	public static void setDefaultFields(AggregatedValueObject[] aggs) throws MetaDataException {
        //���ü���
        String pk_group = InvocationInfoProxy.getInstance().getGroupId();
		for(AggregatedValueObject agg : aggs) {
			agg.getParentVO().setAttributeValue("pk_group", pk_group);
			//��������
			agg.getParentVO().setAttributeValue("vtrantypecode", "30-Cxx-02");
			//��˰���
			agg.getParentVO().setAttributeValue("ftaxtypeflag", String.valueOf(1));
			//���pk_org��SG,��ccustomerid �¼��·� L-00002 ������ L-00001
			if("SG".equals(agg.getParentVO().getAttributeValue("pk_org"))) {
				agg.getParentVO().setAttributeValue("ccustomerid","L-00002");
				//���Ź̶�ֵ
				agg.getParentVO().setAttributeValue("cdeptid", "ECM");
			} else {
				agg.getParentVO().setAttributeValue("ccustomerid","L-00001");
				//���Ź̶�ֵ
				agg.getParentVO().setAttributeValue("cdeptid", "OPT");
			}
			//���ݺ�Ĭ��ֵΪnull
			agg.getParentVO().setAttributeValue("vbillcode", null);
			CircularlyAccessibleValueObject[] childrenVO = agg.getChildrenVO();
			for(CircularlyAccessibleValueObject child : childrenVO) {
				//���������֯
				agg.getParentVO().setAttributeValue("csettleorgid", agg.getParentVO().getAttributeValue("pk_org"));
			}
			
		}
	}
	
	/**
	 * ĳЩ���ݵĽ��׻��ߵ������ͷ���������, ��ʱ��ⷭ��,
	 * @param billtypeName  �����ģ���ĵ������ͻ�������name��һ��
	 */
	public static void transBillType(AggregatedValueObject[] aggs, String billtypeName) throws MetaDataException {
		//TO���ڵ��������޷���������, ����private�˱���ʱ���ֿ�ָ������,���ֶ��������	
		String billtype = (String) aggs[0].getParentVO().getAttributeValue(billtypeName);
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();
		if(billtype != null && !"".equals(billtype)) {
			StringBuffer wherecond = new StringBuffer();
			wherecond.append("pk_billtypecode = ").append("'").append(billtype).append("'")
				.append(" and pk_group =").append("'").append(pk_group).append("'  order by ts desc");
			NCObject[] object = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(BilltypeVO.class, wherecond.toString(), false);
			if(object != null && object.length > 0) {
				billtype = object[0].getAttributeValue("pk_billtypeid").toString();
			}
			for(AggregatedValueObject agg : aggs) {
				agg.getParentVO().setAttributeValue(billtypeName, billtype);
			}
		}
	}
	/**
	   * ���������ͬ���ͣ����ݲ�ͬ��VO���ϲ�����
	   * 
	   * ��Ŀ��VO��������䵽��ԴVO�У������ԴVO���ֶ�ֵΪ�������,�������
	   * 
	   * @param srcvos ��ԴVO
	   * @param destvos Ŀ��VO
	   * @return �ϲ���VO
	   */
	  public static AggregatedValueObject[] combinBillVO(
	      AggregatedValueObject[] srcvos, AggregatedValueObject[] destvos) {
	    int j = 0;
	    for (AggregatedValueObject srcvo : srcvos) {
	      String[] headnames = srcvo.getParentVO().getAttributeNames();
	      CircularlyAccessibleValueObject srcheadvo = srcvo.getParentVO();
	      CircularlyAccessibleValueObject destheadvo = destvos[j].getParentVO();
	      for (String headname : headnames) {
	        if (srcheadvo.getAttributeValue(headname) == null) {
	          srcheadvo.setAttributeValue(headname,
	              destheadvo.getAttributeValue(headname));
	        }
	      }
	      CircularlyAccessibleValueObject[] srcbodyvos = srcvo.getChildrenVO();
	      CircularlyAccessibleValueObject[] destbodyvos =
	          destvos[j].getChildrenVO();
	      if (srcbodyvos == null || srcbodyvos.length == 0) {
	        return srcvos;
	      }
	      int i = 0;
	      for (CircularlyAccessibleValueObject srcbodyvo : srcbodyvos) {
	        String[] srcbodynames = srcbodyvo.getAttributeNames();
	        for (String srcbodyname : srcbodynames) {
	          if (srcbodyvo.getAttributeValue(srcbodyname) == null) {
	            srcbodyvo.setAttributeValue(srcbodyname,
	                destbodyvos[i].getAttributeValue(srcbodyname));
	          }
	        }
	        i++;
	      }
	      j++;
	    }
	    return srcvos;
	  }
	  
}
