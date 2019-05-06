package nc.pubitf.so.m30.api.rest.check;

import java.util.HashSet;
import java.util.Set;

import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class SaleOrderMandatoryCheck {
	private static String[] HEADER_FIELDS = new String[] {
			SaleOrderHVO.PK_GROUP, SaleOrderHVO.PK_ORG,
			SaleOrderHVO.VTRANTYPECODE, SaleOrderHVO.VDEF2,
			SaleOrderHVO.VDEF1, SaleOrderHVO.VDEF3,
			SaleOrderHVO.VDEF4, SaleOrderHVO.VDEF5,
			SaleOrderHVO.VDEF7, SaleOrderHVO.DBILLDATE, 
			SaleOrderHVO.CCUSTOMERID,SaleOrderHVO.CDEPTID, 
			SaleOrderHVO.CORIGCURRENCYID };
	
	private static String[] BODY_FIELDS = new String[] { 
			SaleOrderBVO.CMATERIALVID
			};
	
	public SaleOrderMandatoryCheck() {
		super();
	}
	
	public void validate(SaleOrderVO[] vos) throws Exception {
		Set<String> headNullFields = new HashSet<String>();
		Set<String> bodyNullFields = new HashSet<String>();
		for (SaleOrderVO vo : vos) {
			SaleOrderHVO headvo = vo.getParentVO();
			//20180914 add by weiningc 新需:若transtype属于OTC， 则不需要校验
			String transtype = headvo.getVtrantypecode();
					
			for (String headString : HEADER_FIELDS) {
				if (headvo.getAttributeValue(headString) == null) {
					headNullFields.add(headString);
				}
			}
			SaleOrderBVO[] bodyVOs = vo.getChildrenVO();
			for (SaleOrderBVO bodyVO : bodyVOs) {
				// Modified on 2018-01-25
				// 解决传值时表体pk_org丢失的问题
				bodyVO.setPk_org(headvo.getPk_org());
				for (String bodyString : BODY_FIELDS) {
					if ((bodyVO.getAttributeValue(bodyString) == null || "".equals(bodyVO.getAttributeValue(bodyString))) && !bodyNullFields.contains(bodyString)) {
						bodyNullFields.add(bodyString);
					}
				}
			}
		}
		
		// Check whether any fields missing
		if (!headNullFields.isEmpty() || !bodyNullFields.isEmpty()) {
			// Make up error message
			StringBuilder sb = new StringBuilder();
			sb.append("Missing header information: ");
			if (headNullFields.isEmpty()) {
				sb.append("[null],");
			}
			for (String head : headNullFields) {
				sb.append("[" + head + "],");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append(" Missing body information: ");
			if (bodyNullFields.isEmpty()) {
				sb.append("[null],");
			}
			for (String body : bodyNullFields) {
				sb.append("[" + body + "],");
			}
			sb.deleteCharAt(sb.length()-1);
			throw new Exception(sb.toString());
		}
	}
}
