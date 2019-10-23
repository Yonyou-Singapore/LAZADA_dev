package nc.bs.so.plugin.service;

import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
import nc.bs.logging.Logger;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.DownloadMethod;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaBillTransform;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaClientService;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaDateUtils;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.LazadaJsonUtils;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.GenerateAccessTokenResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderDetailResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderListDataResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderListResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderListsRequest;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaProductsInfo;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaProductsInfoDataResponse;
import nc.itf.uap.busibean.ISysInit;
import nc.itf.uap.busibean.ISysInitQry;
import nc.pub.so.apiservice.ILazadaService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;

/**
 * 更新lazada Token
 * @author ll
 * 
 */
public class LazadaRefreshTokenService extends AbstractWorkPlugin {

	// /private static final Logger logger =
	// LoggerFactory.getLogger(LazadaSourceOrderMethod.class);
	DownloadMethod downloadmethod = new DownloadMethod();
	LazadaClientService lazadaClientService = new LazadaClientService();
	LazadaJsonUtils lazadaJsonUtil = new LazadaJsonUtils();
	LazadaBillTransform lazadaBillTransform = new LazadaBillTransform();
	
	public static final String CN01 = "CN01";
	public static final String SG = "SG";
	public static final String CN01_RE = "CN01_RE";
	public static final String SG_RE = "SG_RE";
	
	
	private ILazadaService lazadaService;
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)throws BusinessException {

		GenerateAccessTokenResponse result = null;
		

		try {
			//当前数据源为VM 
			Logger.error("===before init lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
			if(InvocationInfoProxy.getInstance().getUserDataSource() == null) {
				InvocationInfoProxy.getInstance().setUserDataSource("VM");
			}
			Logger.error("===after init lazada datasource===" + InvocationInfoProxy.getInstance().getUserDataSource());
			String pk_group = InvocationInfoProxy.getInstance().getGroupId();
			
			SysInitVO[] sysTokenlist = NCLocator.getInstance().lookup(ISysInitQry.class).querySysInit(pk_group, "SO_LAZADA_TOKEN");//lazada token
			SysInitVO[] sysTokenRefreshToken = NCLocator.getInstance().lookup(ISysInitQry.class)
					.querySysInit(pk_group, "SO_LAZADA_TOKEN_RE");//use by refresh token
			
			ISysInit updateparaservice = NCLocator.getInstance().lookup(ISysInit.class);
			//CN的initcode
			
			for(SysInitVO sysrefreshVO: sysTokenRefreshToken){
				String use_refreshtoken = sysrefreshVO.getValue();
				String initCode = sysrefreshVO.getInitcode();
				result = refreshToken(null,use_refreshtoken,initCode);
				//更新token
				String refresh_token = result.getRefresh_token();
				String access_token = result.getAccess_token();
				if(CN01_RE.equals(initCode) && !StringUtils.isBlank(access_token) && !StringUtils.isBlank(refresh_token)) {
					updateparaservice.updatePara(CN01, sysrefreshVO.getPk_org(), access_token);
					updateparaservice.updatePara(CN01_RE, sysrefreshVO.getPk_org(), refresh_token);
				} else if(SG_RE.equals(initCode) && !StringUtils.isBlank(access_token) && !StringUtils.isBlank(refresh_token)) {
					updateparaservice.updatePara(SG, sysrefreshVO.getPk_org(), access_token);
					updateparaservice.updatePara(SG_RE, sysrefreshVO.getPk_org(), refresh_token);
				}
				
			}
//			result = procOrders();
//			System.out.print(result);

		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		} finally {
			
		}

		// new So().getres();

		return null;
	}

	@Override
	String requestSystem(URLConnection conn, String jsonstr) {

		return "";
	}

	@Override
	String constructRequestJson(List<Map<String, Object>> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	SuperVO[] handleReponse(String response) {
		return null;
	}

	/**
	 * 更新token
	 * 
	 * @param param
	 * @param request
	 * @param shop
	 * @param service
	 * @return
	 */
	private GenerateAccessTokenResponse refreshToken(String token,String refreshToken,String initcode) {
		
		try {
		
			
			String retStr = lazadaClientService.refreshAccessToken(null,refreshToken);

			Logger.info("调用数据通获取原单列表接口【refreshtoken】返回数据" + retStr);
			
			GenerateAccessTokenResponse lazadaTokenResponse = new Gson()
						.fromJson(retStr, GenerateAccessTokenResponse.class);
			
			//更新参数
//			String newToken = lazadaTokenResponse.getAccess_token();
//			String newRefreshToken = lazadaTokenResponse.getRefresh_token();
			
			//TODO:根据orgId(SG,CN01) 保存上面两个参数 
			
			
			return lazadaTokenResponse;

		} catch (Exception e) {

			Logger.error(e);
			ExceptionUtils.wrappBusinessException(e.getMessage());
			return null;
		}
		
	}
	
}
