package nc.bs.so.plugin.service;

import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.pub.so.apiservice.ILazadaService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.so.restapi.LazadaAggVO;
import nc.vo.so.restapi.LazadaHeadVO;
import nc.vo.so.restapi.LazadaItemVO;

/**
 * 销售订单发货后上传至OMS的定时服务
 * @author weiningc
 *
 */
public class SyncDeliveryPlugin extends AbstractWorkPlugin {

	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
	    PreAlertObject retObj = new PreAlertObject();
	    retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);

//	    String result = new SyncDeliveryDataHandler().pushDeliverySo();
	    LazadaAggVO agg = new LazadaAggVO();
	    LazadaHeadVO head = new LazadaHeadVO();
	    LazadaItemVO item = new LazadaItemVO();
	    head.setBilling_country("CN");
	    item.setCreated_at("2019");
	    agg.setParentVO(head);
	    agg.setChildrenVO(new LazadaItemVO[] {item});
	    ILazadaService lookup = NCLocator.getInstance().lookup(ILazadaService.class);
	    lookup.saveLazadaAggVO(new LazadaAggVO[]{agg});
	    return retObj;
	  }

	@Override
	String requestSystem(URLConnection conn, String jsonstr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	String constructRequestJson(List<Map<String, Object>> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	SuperVO[] handleReponse(String response) {
		// TODO Auto-generated method stub
		return null;
	}

}
