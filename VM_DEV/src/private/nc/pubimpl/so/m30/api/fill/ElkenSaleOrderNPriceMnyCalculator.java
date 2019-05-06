package nc.pubimpl.so.m30.api.fill;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.cmp.tools.UFDoubleUtils;
import nc.itf.cmp.pub.Currency;
import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.fill.pricemny.AbstractNPMnyCalculator;
import nc.vo.scmpub.fill.pricemny.ICalculator;
import nc.vo.scmpub.fill.pricemny.IFindPrice;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class ElkenSaleOrderNPriceMnyCalculator<E> extends
	AbstractNPMnyCalculator<SaleOrderVO>{

	private SaleOrderVO[] elkenVOs;
	
	public ElkenSaleOrderNPriceMnyCalculator(SaleOrderVO[] e) {
		super(e);
		elkenVOs = e;
	}

	@Override
	public void calculate() {
	    // 1.设置单位
	    super.setUnit();
	    
	    // 3.计算换算率
	    super.calChangeRate();
	    
	    // 4. 强行计算中
	    try {
			this.forceCalculate();
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
	}

	@Override
	public void findTaxInfo(SaleOrderVO[] vos) {
	    for (SaleOrderVO salebillvo : vos) {
	    	IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
	    	BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
	    	int[] sendstockrows =
	    			bodycouuitl.getValueNullRows(SaleOrderBVO.CSENDSTOCKORGVID);
	    	int[] finacerows =
	    			bodycouuitl.getValueNullRows(SaleOrderBVO.CSETTLEORGVID);
	    	int[] needchangerows = ArrayUtil.combinArrays(sendstockrows, finacerows);
	    	SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
	    	taxInfo.setTaxInfoByBodyPos(needchangerows);
	    }
	}

	@Override
	public IFindPrice getFindPrice() {
		return new SaleOrderFindPrice(); 
	}

	@Override
	public int getUnitType() {
		return IMaterialPubService_C.MATERIAL_CONVERT_SALE; 
	}

	@Override
	public ICalculator getCalculator() {
		return new SaleOrderCalculator();
	}
	  
	@Override
	public boolean isTaxPrior() {
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();
		UFBoolean istaxprior = SOSysParaInitUtil.getSO23(pk_group);
		if (istaxprior.booleanValue()) {
		    return true;
		}
		return false;
	}
	
	public void setElkenVO(SaleOrderVO[] newVO) {
		this.elkenVOs = newVO;
	}
	
	public SaleOrderVO[] getElkenVO() {
		return this.elkenVOs;
	}
	
	private void forceCalculate() throws BusinessException {
		SaleOrderVO[] vos = this.getElkenVO();
		for (SaleOrderVO vo : vos) {
			SaleOrderBVO[] bvos = vo.getChildrenVO();
			
			//金额计算参数 add by weiningc start
			String pk_corp = vo.getParentVO().getPk_org();
			String pk_currtype = vo.getParentVO().getCorigcurrencyid();
			String oppcurrtype = CurrencyRateUtilHelper.getInstance().getLocalCurrtypeByOrgID(pk_corp);
			UFDate billdate = vo.getParentVO().getDbilldate();
			//end
			
			for (SaleOrderBVO bvo : bvos) {
				// 设置数量
				bvo.setAttributeValue(SaleOrderBVO.NASTNUM, 
						bvo.getAttributeValue(SaleOrderBVO.NQTUNITNUM));
				bvo.setAttributeValue(SaleOrderBVO.NNUM, 
						bvo.getAttributeValue(SaleOrderBVO.NQTUNITNUM));
				
				// 获取已知条件
				UFDouble qtOrigPrice = (UFDouble) bvo
						.getAttributeValue(SaleOrderBVO.NQTORIGPRICE);
				UFDouble qtOrigTaxPrice = (UFDouble) bvo
						.getAttributeValue(SaleOrderBVO.NQTORIGTAXPRICE);
				UFDouble exchangeRate = (UFDouble) bvo
						.getAttributeValue(SaleOrderBVO.NEXCHANGERATE);
				UFDouble groupExRate = (UFDouble) bvo.getAttributeValue(SaleOrderBVO.NGROUPEXCHGRATE) == null ? UFDouble.ONE_DBL : (UFDouble) bvo.getAttributeValue(SaleOrderBVO.NGROUPEXCHGRATE);
				UFDouble origMny = (UFDouble) bvo.getAttributeValue(SaleOrderBVO.NORIGMNY);
				UFDouble origTaxMny = (UFDouble) bvo.getAttributeValue(SaleOrderBVO.NORIGTAXMNY);
				
				if(exchangeRate == null) {
					ExceptionUtils.wrappBusinessException("exchangeRate is null, please check.");
				}
				if(exchangeRate.doubleValue() == 0.0) { 
					exchangeRate = Currency.getRate(pk_corp, pk_currtype, oppcurrtype, billdate);
				}
				//金额可能乘以可能除  nqtorignetprice 
				UFDouble netprice = (UFDouble) bvo.getAttributeValue(SaleOrderBVO.NORIGTAXNETPRICE);
				UFDouble calnetprice = Currency.getAmountByOpp(pk_corp, pk_currtype, oppcurrtype, netprice, exchangeRate, billdate);
				UFDouble calqtOrigPrice = Currency.getAmountByOpp(pk_corp, pk_currtype, oppcurrtype, qtOrigPrice, exchangeRate, billdate);
				UFDouble calqtOrigTaxPrice = Currency.getAmountByOpp(pk_corp, pk_currtype, oppcurrtype, qtOrigTaxPrice, exchangeRate, billdate);
				UFDouble calorigTaxMny = Currency.getAmountByOpp(pk_corp, pk_currtype, oppcurrtype, origTaxMny, exchangeRate, billdate);
				// 无税单价们
				bvo.setAttributeValue(SaleOrderBVO.NNETPRICE, calnetprice);
				bvo.setAttributeValue(SaleOrderBVO.NORIGNETPRICE, netprice);
				bvo.setAttributeValue(SaleOrderBVO.NORIGPRICE, qtOrigPrice);
				bvo.setAttributeValue(SaleOrderBVO.NPRICE, calqtOrigPrice);
				bvo.setAttributeValue(SaleOrderBVO.NQTNETPRICE, calnetprice);
				bvo.setAttributeValue(SaleOrderBVO.NQTORIGNETPRICE, netprice);
				bvo.setAttributeValue(SaleOrderBVO.NQTPRICE, calqtOrigPrice);
				
				// 含税单价们
				bvo.setAttributeValue(SaleOrderBVO.NORIGTAXNETPRICE, netprice);
				bvo.setAttributeValue(SaleOrderBVO.NORIGTAXPRICE, qtOrigTaxPrice);
				bvo.setAttributeValue(SaleOrderBVO.NQTORIGTAXNETPRC, netprice);
				bvo.setAttributeValue(SaleOrderBVO.NQTTAXNETPRICE, calnetprice);
				bvo.setAttributeValue(SaleOrderBVO.NQTTAXPRICE, calqtOrigTaxPrice);
				bvo.setAttributeValue(SaleOrderBVO.NTAXNETPRICE, calnetprice);
				bvo.setAttributeValue(SaleOrderBVO.NTAXPRICE, calqtOrigTaxPrice);
				
				// 金额们
				bvo.setAttributeValue(SaleOrderBVO.NTAXMNY, calorigTaxMny);
				bvo.setAttributeValue(SaleOrderBVO.NBFORIGSUBMNY, origTaxMny);
				bvo.setAttributeValue(SaleOrderBVO.NCALTAXMNY, origTaxMny);
				bvo.setAttributeValue(SaleOrderBVO.NGROUPMNY, calorigTaxMny);
				bvo.setAttributeValue(SaleOrderBVO.NGROUPTAXMNY, calorigTaxMny);
				bvo.setAttributeValue(SaleOrderBVO.NMNY, calorigTaxMny);	
				//税额
//				bvo.setNtax(UFDoubleUtils.multiply(bvo.get, second))
			}
		}
	}
}