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
	 * 翻译前赋值一些默认值, 如pk_group或某些翻译不过来字段在这里赋值	
	 */
	public static void setDefaultFields(AggregatedValueObject[] aggs) throws MetaDataException {
        //设置集团
        String pk_group = InvocationInfoProxy.getInstance().getGroupId();
		for(AggregatedValueObject agg : aggs) {
			agg.getParentVO().setAttributeValue("pk_group", pk_group);
			//交易类型
			agg.getParentVO().setAttributeValue("vtrantypecode", "30-Cxx-02");
			//扣税类别
			agg.getParentVO().setAttributeValue("ftaxtypeflag", String.valueOf(1));
			//如果pk_org是SG,则ccustomerid 新加坡放 L-00002 其它的 L-00001
			if("SG".equals(agg.getParentVO().getAttributeValue("pk_org"))) {
				agg.getParentVO().setAttributeValue("ccustomerid","L-00002");
				//部门固定值
				agg.getParentVO().setAttributeValue("cdeptid", "ECM");
			} else {
				agg.getParentVO().setAttributeValue("ccustomerid","L-00001");
				//部门固定值
				agg.getParentVO().setAttributeValue("cdeptid", "OPT");
			}
			//单据号默认值为null
			agg.getParentVO().setAttributeValue("vbillcode", null);
			CircularlyAccessibleValueObject[] childrenVO = agg.getChildrenVO();
			for(CircularlyAccessibleValueObject child : childrenVO) {
				//结算财务组织
				agg.getParentVO().setAttributeValue("csettleorgid", agg.getParentVO().getAttributeValue("pk_org"));
			}
			
		}
	}
	
	/**
	 * 某些单据的交易或者单据类型翻译有问题, 暂时查库翻译,
	 * @param billtypeName  因各个模块间的单据类型或交易类型name不一致
	 */
	public static void transBillType(AggregatedValueObject[] aggs, String billtypeName) throws MetaDataException {
		//TO存在单据类型无法翻译的情况, 导致private端保存时各种空指针问题,暂手动翻译代替	
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
	   * 针对两个相同类型，数据不同的VO做合并处理。
	   * 
	   * 把目的VO的数据填充到来源VO中，如果来源VO中字段值为空则填充,否则不填充
	   * 
	   * @param srcvos 来源VO
	   * @param destvos 目的VO
	   * @return 合并后VO
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
