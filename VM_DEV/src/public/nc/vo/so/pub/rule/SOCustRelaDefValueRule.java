package nc.vo.so.pub.rule;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * �ͻ����Ĭ��ֵ����
 * 
 * @since 6.0
 * @version 2012-3-12 ����11:20:51
 * @author ô��
 */
public class SOCustRelaDefValueRule {

  private boolean isdisratechg;

  private IKeyValue keyValue;

  public SOCustRelaDefValueRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public boolean isDiscountRateChg() {
    return this.isdisratechg;
  }

  public void setCustRelaDefValue() {

    String old_origcur =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);
    UFDouble old_discountrate =
        this.keyValue.getHeadUFDoubleValue(SOItemKey.NDISCOUNTRATE);
    // 1.���ݿͻ���������Ĭ��ֵ
    String[] fieldNames =
        new String[] {
        // ר�ܲ��š�ר��ҵ��Ա����Ʊ�ͻ������䷽ʽ
        CustsaleVO.RESPDEPT, CustsaleVO.RESPPERSON,
        CustsaleVO.BILLINGCUST,
        CustsaleVO.SHIPPINGTYPE,
        // Ĭ�Ͻ��ױ��֡�Ĭ���ո���Э�顢�����ۿۡ���������
        CustsaleVO.CURRENCYDEFAULT, CustsaleVO.PAYTERMDEFAULT,
        CustsaleVO.DISCOUNTRATE, CustsaleVO.CHANNEL, CustsaleVO.PK_TRADETERM,
        CustsaleVO.ISSUECUST
    };
    CustsaleVO retVO = this.getCustSaleVO(fieldNames);
    // ����Ĭ��ֵ
    // û��ר��ҵ��Ա����գ���½�û�Ĭ��ҵ��Ա������ID��NCdp205106387
    String cemployeeid = retVO.getRespperson();
    if (!PubAppTool.isNull(cemployeeid)) {
      this.keyValue.setHeadValue(SOItemKey.CEMPLOYEEID, cemployeeid);
    }
    this.keyValue.setHeadValue(SOItemKey.CCHANNELTYPEID, retVO.getChannel());
    this.keyValue.setHeadValue(SOItemKey.CTRADEWORDID, retVO.getPk_tradeterm());
    this.keyValue.setHeadValue(SOItemKey.CTRANSPORTTYPEID,
        retVO.getShippingtype());
    this.keyValue.setHeadValue(SOItemKey.CPAYTERMID, retVO.getPaytermdefault());

    // 2.�ͻ�û��ר�ܲ���
    String deptid = retVO.getRespdept();
    if (!PubAppTool.isNull(deptid)) {
      this.keyValue.setHeadValue(SOItemKey.CDEPTID, deptid);
      String[] pk_depts = new String[] {
          deptid
      };
      Map<String, String> mapvids =
          DeptPubService.getLastVIDSByDeptIDS(pk_depts);
      this.keyValue.setHeadValue(SOItemKey.CDEPTVID, mapvids.get(deptid));
    }
    // 3.�ͻ�û��Ĭ�Ͽ�Ʊ�ͻ���ȡ�ͻ�����
    String invcus = retVO.getBillingcust();
    if (PubAppTool.isNull(invcus)) {
      invcus = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    }
    this.keyValue.setHeadValue(SOItemKey.CINVOICECUSTID, invcus);

    // 4.�ͻ�û��Ĭ�ϱ��֣�ȡ������֯��λ��
    String origcurr = retVO.getCurrencydefault();
    if (PubAppTool.isNull(origcurr)) {
      origcurr = this.getOrgCurr();
    }
    if(this.keyValue.getHeadValue(SOItemKey.CORIGCURRENCYID) == null) {
    	this.keyValue.setHeadValue(SOItemKey.CORIGCURRENCYID, origcurr);
    }
    if (PubAppTool.isNull(old_origcur) || !old_origcur.equals(origcurr)) {
      // ���±������
      BodyUpdateByHead updaterule = new BodyUpdateByHead(this.keyValue);
      updaterule.updateAllBodyValueByHead(SOItemKey.CORIGCURRENCYID,
          PreOrderBVO.CORIGCURRENCYID);
    }

    // 5.�����ۿ����ȼ���1)�ͻ�����Ĭ�� 2)ԭ��ֵ 3)Ĭ��100
    UFDouble discountrate = retVO.getDiscountrate();
    if (null == discountrate) {
      if (null != old_discountrate) {
        discountrate = old_discountrate;
      }
      else {
        discountrate = new UFDouble(100);
      }
    }
    this.keyValue.setHeadValue(SOItemKey.NDISCOUNTRATE, discountrate);
    if (null == old_discountrate
        || discountrate.compareTo(old_discountrate) != 0) {
      // ���±��������ۿ�
      BodyUpdateByHead updaterule = new BodyUpdateByHead(this.keyValue);
      updaterule.updateAllBodyValueByHead(PreOrderBVO.NDISCOUNTRATE,
          SOItemKey.NDISCOUNTRATE);
      this.isdisratechg = true;
    }
    // 6.���ԭ��ɢ���ֶ�ֵ
    this.keyValue.setHeadValue(SOItemKey.CFREECUSTID, null);

    // 7.���ñ����ջ��ͻ�
    BodyValueRowRule countutil = new BodyValueRowRule(this.keyValue);
    int[] rows = countutil.getMarNotNullRows();
    String rececust = retVO.getIssuecust();
    if (PubAppTool.isNull(rececust)) {
      rececust = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    }
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SOItemKey.CRECEIVECUSTID, rececust);
    }

    // 7.2 V635��������ͷ�ջ��ͻ�
    this.keyValue.setHeadValue(SOItemKey.CRECEIVECUSTID, rececust);

  }

  /**
   * ���ÿͻ�Ĭ�Ͻ��ױ���
   */
  public void setCustRelaCurrency() {
    // ���ݿͻ���������Ĭ��ֵ
    String[] fieldNames = new String[] {
        // Ĭ�Ͻ��ױ���
        CustsaleVO.CURRENCYDEFAULT
    };
    CustsaleVO retVO = this.getCustSaleVO(fieldNames);
    // �ͻ�û��Ĭ�ϱ��֣�ȡ������֯��λ��
    String origcurr = retVO.getCurrencydefault();
    if (PubAppTool.isNull(origcurr)) {
      origcurr = this.getOrgCurr();
    }
    this.keyValue.setHeadValue(SOItemKey.CORIGCURRENCYID, origcurr);
  }

  /**
   * ���ÿͻ�Ĭ���ջ��ͻ�
   */
  public void setRelaReceiveCust(int[] rows) {
    // ���ݿͻ���������Ĭ��ֵ
    String[] fieldNames = new String[] {
        // �ջ��ͻ�
        CustsaleVO.ISSUECUST
    };
    CustsaleVO retVO = this.getCustSaleVO(fieldNames);

    String recust = this.keyValue.getHeadStringValue(SOItemKey.CRECEIVECUSTID);
    if (PubAppTool.isNull(recust)) {
      recust = retVO.getIssuecust();
    }

    if (PubAppTool.isNull(recust)) {
      recust = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    }
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SOItemKey.CRECEIVECUSTID, recust);
    }
  }

  public void setCustRelaInvoiceCust() {

    // ���ݿͻ���������Ĭ��ֵ
    String[] fieldNames = new String[] {
        // ��Ʊ�ͻ�
        CustsaleVO.BILLINGCUST
    };
    CustsaleVO retVO = this.getCustSaleVO(fieldNames);
    // �ͻ�û��Ĭ�Ͽ�Ʊ�ͻ���ȡ�ͻ�����
    String invcus = retVO.getBillingcust();
    if (PubAppTool.isNull(invcus)) {
      invcus = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    }
    this.keyValue.setHeadValue(SOItemKey.CINVOICECUSTID, invcus);
  }

  private CustsaleVO getCustSaleVO(String[] fieldNames) {

    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String customer = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    if (PubAppTool.isNull(customer)) {
      return new CustsaleVO();
    }
    Map<String, CustsaleVO> mret =
        CustomerPubService.getCustSaleVOByPks(new String[] {
            customer
        }, pk_org, fieldNames);

    if (null == mret || mret.size() == 0) {
      return new CustsaleVO();
    }
    return mret.get(customer);
  }

  private String getOrgCurr() {
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    Map<String, String> orgCurrMap = null;

    orgCurrMap = OrgUnitPubService.queryOrgCurrByPk(new String[] {
        pk_org
    });

    if (null != orgCurrMap) {
      return orgCurrMap.get(pk_org);
    }
    return null;
  }
}
