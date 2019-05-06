package nc.pubitf.so.m30.api.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.impl.pubapp.env.BSContext;
import nc.pubitf.so.m30.api.ISaleOrderMaintainAPI;
import nc.pubitf.so.m30.api.ISaleOrderQueryAPI;
import nc.pubitf.so.m30.api.ISaleOrderVO;
import nc.pubitf.so.m30.api.rest.check.SaleOrderMandatoryCheck;
import nc.pubitf.so.pub.api.rest.AbstractSORestResource;
import nc.pubitf.so.utils.rest.validate.RestutilsForSpecialField;
import nc.uap.ws.console.utils.vo.Json;
import nc.ui.querytemplate.operator.EqOperator;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.api.rest.utils.RestUtils;
import nc.vo.scmpub.json.GsonUtils;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.scmpub.util.translate.TranslateUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.restapi.RestMessageVO;
import nc.vo.so.restapi.RestlogUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description 查询销售订单VO资源 <li>
 *              http://127.0.0.1:80/uapws/rest/saleorder/bybillcode?
 *              vbillcode=SO302015111900000152</li>
 * @scene
 * 
 * @param
 * 
 * 
 * @since 6.5
 * @version 2015-11-13 下午3:18:01
 * @author 刘景
 */
@Controller
@Path("saleorder")
public class SaleOrderResource extends AbstractSORestResource {

	@GET
	@Produces("application/json")
	@Path("bybillcode")
	public JSONString getSaleorderByBillcode(
			@QueryParam("orgcode") String orgcode,
			@QueryParam("vbillcode") String vbillcode) {
		if (StringUtils.isEmpty(vbillcode)) {
			return RestUtils.emptyJSONString;
		}
		RestUtils.initInvocationInfo();

		ISaleOrderQueryAPI query = NCLocator.getInstance().lookup(
				ISaleOrderQueryAPI.class);
		QuerySchemeBuilder builder = QuerySchemeBuilder
				.buildByFullClassName(SaleOrderHVO.class.getName());
		builder.append(ISaleOrderVO.VBILLCODE, EqOperator.getInstance(),
				new String[] { vbillcode });

		if (!StringUtils.isEmpty(orgcode)) {
			String pkGroup = AppContext.getInstance().getPkGroup();
			OrgVO orgVO = new OrgVO();
			Map<String, String> codeToId = TranslateUtils.trancelateCodeToID(
					orgVO, new String[] { orgcode }, pkGroup);

			if (codeToId != null && codeToId.get(orgcode) != null) {
				builder.append(ISaleOrderVO.PK_ORG, EqOperator.getInstance(),
						new String[] { codeToId.get(orgcode) });
			}
		}

		SaleOrderVO[] vos = null;
		try {
			vos = query.queryVOByScheme(builder.create());
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		if (ArrayUtils.isEmpty(vos)) {
			return RestUtils.toJSONString(new SaleOrderVO());
		}

		return RestUtils.toJSONString(vos[0]);
	}

	@POST
	@Path("insertWithArray")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString insertArriveBills(JSONString str)
			throws BusinessException {
		SaleOrderVO[] bills = GsonUtils.buildNCGson4Rest().fromJson(
				str.toString(), SaleOrderVO[].class);

		if (ArrayUtils.isEmpty(bills)) {
			return RestUtils.emptyJSONString;
		}
		RestUtils.initInvocationInfo();
		RestutilsForSpecialField.setDefaultFields(bills);
		// 翻译VO
		bills = new SaleOrderTranslateAdaptor().doTranslate(bills);

		// 执行插入
		SaleOrderVO[] saleorderVOs = NCLocator.getInstance()
				.lookup(ISaleOrderMaintainAPI.class).insertBills(bills);
		List<String> vbillCodes = new ArrayList<>();
		for (SaleOrderVO saleorderVO : saleorderVOs) {
			vbillCodes.add(saleorderVO.getParentVO().getVbillcode());
		}
		return RestUtils.toJSONString(vbillCodes.toArray(new String[0]));
	}

	/**
	 * 同步导入订单
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("syncSaleOrders")
	@Consumes("application/json")
	@Produces("application/json")
	@ResponseBody
	public JSONString syncSaleOrders(JSONString str) throws Exception {
		List<RestMessageVO> message = new ArrayList<RestMessageVO>();
		RestMessageVO result1 = null;
		SaleOrderVO[] bills = GsonUtils.buildNCGson4Rest().fromJson(
				str.toString().trim(), SaleOrderVO[].class);

		if (ArrayUtils.isEmpty(bills)) {
			return RestUtils.emptyJSONString;
		}
		RestUtils.initInvocationInfo();
		RestutilsForSpecialField.setDefaultFields(bills);

		// Modified on 2018-01-23
		// 前台必输项校验
		SaleOrderMandatoryCheck check = new SaleOrderMandatoryCheck();

		// 翻译后的vo
		SaleOrderVO[] translatedBills = null;
		// 每一个单据都调用一次同步
		for (SaleOrderVO bill : bills) {
			try {
				// 翻译VO one by one translate ...... 20180530 start
				translatedBills = new SaleOrderTranslateAdaptor()
						.doTranslate(new SaleOrderVO[] { bill });
				RestMessageVO result = new RestMessageVO();
				result.setBillno(translatedBills[0].getParentVO()
						.getVbillcode());
				SaleOrderVO[] input = new SaleOrderVO[] { translatedBills[0] };
				check.validate(input);
				result1 = NCLocator.getInstance()
						.lookup(ISaleOrderMaintainAPI.class)
						.syncSaleOrders(input);
				result1.setBillno(input[0].getParentVO().getVdef2());
				message.add(result1);
			} catch (Exception e) {
				RestMessageVO vo = new RestMessageVO();
				vo.setBillno(bill.getParentVO().getVdef2());
				vo.setReturncode("400");
				vo.setDescription(e.getMessage());
				this.resetEndNum();
				message.add(vo);
				Logger.error("====SO INTERFACE===" + e.getMessage(), e);
//				ExceptionUtils.wrappException(e);
			}
		}
		//保存返回json
		RestlogUtils.porcessRestLogForSave("so", "syncSaleOrders", "SaleOrderResource", Json.object2json(message).toString(), str.toString());
		return RestUtils.toJSONString(message);
	}

	/**
	 * 由于接口restful catch了异常，需要返回格式信息，但是走了begin，没到end之前抛了错， 所以不回滚
	 * 所以这里特殊处理 modified by weiningc 20180406 start
	 */
	public void resetEndNum() {

		Object engrossnum = BSContext.getInstance().getSession("engrossnum");
		int flag = 0;
		if (engrossnum == null) {
			return;
		}
		int engrossnumvalue = Integer.parseInt(engrossnum.toString());
		if (engrossnumvalue == 1) {
			// String message =
			// nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4032001_0","04032001-0139")/*@res
			// "存在嵌套调用,请检查"*/;
			// ExceptionUtils.wrappBusinessException(message);
			BSContext.getInstance().setSession("engrossnum", flag);// 重置为0
		}

	}
	
	/**
	 * 同步导入订单
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("syncLazadaSaleOrders")
	@Consumes("application/json")
	@Produces("application/json")
	@ResponseBody
	public JSONString syncLazadaSaleOrders(String str) throws Exception {
		List<RestMessageVO> message = new ArrayList<RestMessageVO>();
		SaleOrderVO[] bills = this.buildSaleOrderVO(str);

		if (ArrayUtils.isEmpty(bills)) {
			return RestUtils.emptyJSONString;
		}
		RestUtils.initInvocationInfo();
		RestutilsForSpecialField.setDefaultFields(bills);

		// Modified on 2018-01-23
		// 前台必输项校验
		SaleOrderMandatoryCheck check = new SaleOrderMandatoryCheck();

		// 翻译后的vo
		SaleOrderVO[] translatedBills = null;
		// 每一个单据都调用一次同步
		for (SaleOrderVO bill : bills) {
			try {
				// 翻译VO one by one translate ...... 20180530 start
				translatedBills = new SaleOrderTranslateAdaptor()
						.doTranslate(new SaleOrderVO[] { bill });
				RestMessageVO result = new RestMessageVO();
				result.setBillno(translatedBills[0].getParentVO()
						.getVbillcode());
				SaleOrderVO[] input = new SaleOrderVO[] { translatedBills[0] };
				check.validate(input);
				RestMessageVO result1 = NCLocator.getInstance()
						.lookup(ISaleOrderMaintainAPI.class)
						.syncSaleOrders(input);
				result1.setBillno(input[0].getParentVO().getVdef2());
				message.add(result1);
			} catch (Exception e) {
				RestMessageVO vo = new RestMessageVO();
				vo.setBillno(bill.getParentVO().getVdef2());
				vo.setReturncode("400");
				vo.setDescription(e.getMessage());
				this.resetEndNum();
				message.add(vo);
				Logger.error("====SO INTERFACE===" + e.getMessage(), e);
//				ExceptionUtils.wrappException(e);
			}
		}
		//保存返回json
		RestlogUtils.porcessRestLogForSave("so", "syncSaleOrders", "SaleOrderResource", str.toString(), Json.object2json(message).toString());
		return RestUtils.toJSONString(message);
	}

	private SaleOrderVO[] buildSaleOrderVO(String request) {
		List<SaleOrderVO> sos = new ArrayList<SaleOrderVO>();
		//解析json字符串
		
		JSONArray jsonarr = JSONArray.fromObject(request);
		for(int i=0; i<jsonarr.size(); i++) {
			SaleOrderVO so = new SaleOrderVO();
			SaleOrderHVO hvo = new SaleOrderHVO();
			JSONObject dataobj = jsonarr.getJSONObject(i);
			String header = dataobj.get("so.so_saleorder").toString();
			JSONObject headerjson = JSONObject.fromObject(header);
			hvo.setPk_org(headerjson.getString("pk_org"));
//			hvo.setPk_group(headerjson.getString("pk_group"));
			hvo.setCcustomerid(headerjson.getString("ccustomerid"));
			hvo.setCdeptid(headerjson.getString("cdeptid"));
			hvo.setCorigcurrencyid(headerjson.getString("corigcurrencyid"));
			hvo.setDbilldate(new UFDate(headerjson.getString("dbilldate")));
			hvo.setVbillcode(headerjson.getString("vbillcode"));
			hvo.setVdef1(headerjson.getString("vdef1"));
//			hvo.setVdef2(headerjson.getString("vdef2"));
			hvo.setVdef2("aaa"+i);
			hvo.setVdef3(headerjson.getString("vdef3"));
			hvo.setVdef4(headerjson.getString("vdef4"));
			hvo.setVdef5(headerjson.getString("vdef5"));
//			hvo.setVdef6(headerjson.getString("vdef6"));
			hvo.setVdef7(headerjson.getString("vdef7"));
			hvo.setVdef20(headerjson.getString("vdef20"));
			hvo.setStatus(VOStatus.NEW);
			so.setParentVO(hvo);
			
			List<SaleOrderBVO> bvos = new ArrayList<SaleOrderBVO>();
			String body = dataobj.get("so.so_saleorder_b").toString();
			JSONArray bodys = JSONArray.fromObject(body);
			for(int j=0; j<bodys.size(); j++) {
				SaleOrderBVO bvo = new SaleOrderBVO();
				JSONObject bodyjson = bodys.getJSONObject(j);
				bvo.setBlargessflag(new UFBoolean((String) bodyjson.get("blargessflag")));
				bvo.setCmaterialvid(bodyjson.getString("cmaterialvid"));
				bvo.setCsendstockorgid(bodyjson.getString("csendstockorgid"));
				bvo.setCsendstordocid(bodyjson.getString("csendstordocid"));
				bvo.setCtaxcodeid(bodyjson.getString("ctaxcodeid"));
				bvo.setNorigdiscount(new UFDouble(bodyjson.getString("norigdiscount")));
				bvo.setNorigmny(new UFDouble(bodyjson.getString("norigmny")));
				bvo.setNqtorigprice(new UFDouble(bodyjson.getString("nqtorigprice")));
				bvo.setNqtorigtaxprice(new UFDouble(bodyjson.getString("nqtorigtaxprice")));
				bvo.setNqtunitnum(new UFDouble(bodyjson.getString("nqtunitnum")));
				bvo.setNtax(new UFDouble(bodyjson.getString("ntax")));
				bvo.setNtaxrate(new UFDouble(bodyjson.getString("ntaxrate")));
				bvo.setNorigtaxmny(new UFDouble(bodyjson.getString("norigtaxmny")));
				bvo.setVbdef4(bodyjson.getString("vbdef4"));
				bvo.setVbdef5(bodyjson.getString("vbdef5"));
				bvo.setVbdef6(bodyjson.getString("vbdef6"));
				bvo.setStatus(VOStatus.NEW);
				bvos.add(bvo);
			}
			so.setChildrenVO(bvos.toArray(new SaleOrderBVO[0]));
			sos.add(so);
			
		}
		return sos.toArray(new SaleOrderVO[0]);
	}
}
