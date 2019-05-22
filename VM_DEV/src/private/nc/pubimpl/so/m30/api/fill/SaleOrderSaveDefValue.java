package nc.pubimpl.so.m30.api.fill;

import nc.bs.pubapp.AppBsContext;
import nc.pubimpl.so.m30.api.fill.SaleOrderNPriceMnyCal;
import nc.pubimpl.so.pub.api.fill.BatchCodeBillRule;
import nc.pubimpl.so.pub.api.fill.BusitypeFillRule;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.fill.pricemny.INumPriceMnyCalculator;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.m30.rule.PayTermRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.MaterialFullRule;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgFillRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.so.pub.util.ArrayUtil;

/**
 * @description
 * ���۶������ǿ��Ĭ��ֵ����1
 * @scene
 * ���۶�������
 * @param
 * ��
 *
 * @since 6.5
 * @version 2015-10-20 ����2:13:21
 * @author ����
 */
public class SaleOrderSaveDefValue {

  /**
   * ���۶���Ĭ��ֵǿ�����
   * 
   * @param vos
   */
  public void setDefultValue(SaleOrderVO[] vos) {

    // ����������֯vid����Ա������
    SaleOrgFillRule<SaleOrderVO> orgfill = new SaleOrgFillRule<>(vos);
    orgfill.setOrgEmplyDept();

    // ��佻�����ͱ��롢ҵ������
    BusitypeFillRule<SaleOrderVO> busitypefill = new BusitypeFillRule<>(vos);
    busitypefill.setBusitype();

    // �������old
    MaterialFullRule<SaleOrderVO> materialfull = new MaterialFullRule<>(vos);
    materialfull.setMaterialOid();

    for (SaleOrderVO salebillvo : vos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      // ���Ĭ��ֵ
      setDefultInfo(keyValue);
    }

    //���δ���
    BatchCodeBillRule<SaleOrderVO> batchcode = new BatchCodeBillRule<>();
    batchcode.fillValue(vos);

    // ���㵥λ�������ʡ����������ۡ����
    INumPriceMnyCalculator cal = new SaleOrderNPriceMnyCal<SaleOrderVO>(vos);
    cal.calculate();
  }

  private void setDefultInfo(IKeyValue keyValue) {
    // ���ǿ�Ƶ���Ĭ��ֵ
    this.setForceDefValue(keyValue);

    // ���������֯��������֯��������֯
    BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);

    SaleOrgRelationRule orgrelrule = new SaleOrgRelationRule(keyValue);
    int[] sendstockrows =
        bodycouuitl.getValueNullRows(SaleOrderBVO.CSENDSTOCKORGVID);
    orgrelrule.setSendStockOrg(sendstockrows);

    int[] trafficrows =
        bodycouuitl.getValueNullRows(SaleOrderBVO.CTRAFFICORGVID);
    orgrelrule.setTrafficOrg(trafficrows);

    int[] finacerows = bodycouuitl.getValueNullRows(SaleOrderBVO.CSETTLEORGVID);
    orgrelrule.setFinanceOrg(finacerows);

    // ��������ȡֵ����ֱ�˷�ֱ��ҵ������ȡֵ
    SOProfitCenterValueRule profitRule = new SOProfitCenterValueRule(keyValue);
    profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
        SaleOrderBVO.CSPROFITCENTERID, sendstockrows);

    // ��֯��λ��
    SOCurrencyRule currule = new SOCurrencyRule(keyValue);
    currule.setCurrency(finacerows);

    // ���ͻ����Ĭ��ֵ
    SOCustRelaDefValueRule custrefrule = new SOCustRelaDefValueRule(keyValue);
    custrefrule.setCustRelaDefValue();

    // �۱�����
    SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
    exrule.calcBodyExchangeRates(finacerows);

    // �����տ�Э����Ϣ
    PayTermRule payTermRule = new PayTermRule(keyValue);
    payTermRule.setPayTermInfo();

    // �ջ��ͻ�
    int[] custisnullrows =
        bodycouuitl.getValueNullRows(SaleOrderBVO.CRECEIVECUSTID);
    custrefrule.setRelaReceiveCust(custisnullrows);

    // �ջ���ַ
    int[] eiveaddrnullrows =
        bodycouuitl.getValueNullRows(SaleOrderBVO.CRECEIVEADDRID);
    ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue);
    defaddrule.setCustDefaultAddress(eiveaddrnullrows);

    // ����
    int[] needchangerows = ArrayUtil.combinArrays(sendstockrows, finacerows);
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(needchangerows);
    // ��������
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(needchangerows);

    int[] rows = bodycouuitl.getMarNotNullRows();
    String ctrantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    // ֱ�˲�
    if (!PubAppTool.isNull(ctrantypeid)) {
      DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
      dirstorerule.setDirectStore(rows);
    }
    //˰��
	SOTaxInfoRule taxinforule = new SOTaxInfoRule(keyValue);
	taxinforule.setTaxInfoByBodyPos(needchangerows);

    // ���š�ȫ�ֻ��ʼ���
    SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
    globalraterule.calcGlobalExchangeRate(rows);
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    groupraterule.calcGroupExchangeRate(rows);

  }

  /**
   * ����ת����ǿ�Ƶ�����Ĭ��ֵ
   */
  private void setForceDefValue(IKeyValue keyValue) {

    // ����״̬
    keyValue.setHeadValue(SaleOrderHVO.FSTATUSFLAG,
        BillStatus.FREE.getIntegerValue());
    // �����ۿ�
    UFDouble discountrate =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NDISCOUNTRATE);
    if (null == discountrate) {
      discountrate = SOConstant.ONEHUNDRED;
      keyValue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE, discountrate);
    }

    // ��Ʊ�ͻ����
    String invoicecust =
        keyValue.getHeadStringValue(SaleOrderHVO.CINVOICECUSTID);
    if (PubAppTool.isNull(invoicecust)) {
      SOCustRelaDefValueRule custrelarule =
          new SOCustRelaDefValueRule(keyValue);
      custrelarule.setCustRelaInvoiceCust();
    }

    // ��������
    UFDate busdate = AppBsContext.getInstance().getBusiDate();
    if(busdate == null) {
    	keyValue.setHeadValue(SaleOrderHVO.DBILLDATE, busdate);
    }

    String pk_group = AppBsContext.getInstance().getPkGroup();
    keyValue.setHeadValue(SaleOrderHVO.PK_GROUP, pk_group);

    UFDate enddate = busdate.asLocalEnd();
    int bodycount = keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      keyValue.setBodyValue(i, SaleOrderBVO.DBILLDATE, busdate);
      keyValue.setBodyValue(i, SaleOrderBVO.PK_GROUP, pk_group);
      // �ƻ���������
      UFDate senddate = keyValue.getBodyUFDateValue(i, SaleOrderBVO.DSENDDATE);
      if (null == senddate || senddate.before(busdate)) {
        keyValue.setBodyValue(i, SaleOrderBVO.DSENDDATE, enddate);
      }

      // Ҫ�󵽻�����
      UFDate receivedate =
          keyValue.getBodyUFDateValue(i, SaleOrderBVO.DRECEIVEDATE);
      if (null == receivedate || receivedate.before(busdate)) {
        keyValue.setBodyValue(i, SaleOrderBVO.DRECEIVEDATE, enddate);
      }

      // �����ۿ�
      UFDouble disrate =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NDISCOUNTRATE);
      if (null == disrate) {
        keyValue.setBodyValue(i, SaleOrderHVO.NDISCOUNTRATE, discountrate);
      }
      // ��Ʒ�ۿ�
      UFDouble itemdisrate =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NITEMDISCOUNTRATE);
      if (null == itemdisrate) {
        keyValue.setBodyValue(i, SaleOrderBVO.NITEMDISCOUNTRATE,
            SOConstant.ONEHUNDRED);
      }
      // ��״̬
      keyValue.setBodyValue(i, SaleOrderBVO.FROWSTATUS,
          BillStatus.FREE.getIntegerValue());
    }
  }

}
