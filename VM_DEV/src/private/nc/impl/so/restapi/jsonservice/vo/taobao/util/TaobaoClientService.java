package nc.impl.so.restapi.jsonservice.vo.taobao.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lazada.lazop.api.LazopClient;
import com.lazada.lazop.api.LazopRequest;
import com.lazada.lazop.api.LazopResponse;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.domain.Trade;
import com.taobao.api.request.TradeFullinfoGetRequest;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.TradesSoldIncrementGetRequest;

import nc.vo.pub.BusinessException;

import com.taobao.api.response.TradeFullinfoGetResponse;
import com.taobao.api.response.TradesSoldGetResponse;
import com.taobao.api.response.TradesSoldIncrementGetResponse;

import nc.bs.logging.Logger;
import nc.impl.so.restapi.jsonservice.vo.lazada.util.TransferUtils;

public class TaobaoClientService {
	
	
	
	private static final String key = "21581307";
	private static final String serviceSecret = "e777f24880625ddcd82d70f733240526";
	private static final String sessionkey = "62004194f3d511f601d965d56c0ZZ8e4ZZ6a062882f79122200700613862";
	private static final String url = "http://gw.api.taobao.com/router/rest";
	
	
	public String getTradesSold(String appkey, String appSecret,String session, String obj, String format)throws BusinessException {

		TradesSoldGetRequest request = new TradesSoldGetRequest();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		try{
			request = gson.fromJson(obj,new TypeToken<TradesSoldGetRequest>(){}.getType());
		}catch(Exception e){
			e.printStackTrace();
//			通过接口回传云平台写入数据库，不在平台接口及实现类处做error处理
			//(session, e.getMessage());
			
			Logger.error("Taobaogetsaleorder" + e.getMessage(), e);
			
			return "";
		}
		
		// 封装返回数据
		TradesSoldGetResponse response = new TradesSoldGetResponse();
//		用户本地校验
//		this.checkMemoryValid(response, session);
		
//		if(null != response.getErrorCode()
//				&& !"".equals(response.getErrorCode()))
//			return response;
		
		// 调用接口
		TaobaoClient client = new DefaultTaobaoClient(url, key,serviceSecret, format);
		try {
			response = client.execute(request, sessionkey);
		} catch (Exception e) {
			e.printStackTrace();
//			通过接口回传云平台写入数据库，不在平台接口及实现类处做error处理
			//(session, e.getMessage());
			
			Logger.error("Taobaogetsaleorder" + e.getMessage(), e);
		}
		
		if(null == response){
			throw new BusinessException("调用查询卖家已卖出的交易数据接口没有接受到返回信息!");
		}

		String body=response.getBody();
		if(body!=null){
			//需要匹配的字段
			List<String[]> list = new ArrayList<String[]>();
			if("xml".equals(format)){
				list.add(new String[]{"<tid>", "</tid>"});
			}else{
				list.add(new String[]{"\"tid\":\"", "\""});
			}
			taskExecutor(request.getApiMethodName(),list,body,"查询卖家已卖出的交易数据（根据创建时间）",appkey);
		}
		return (null == response.getBody() ? "" : response.getBody());
	}
	
	
	
	public String getTaobaoTradeFullinfo(Long tid){
		
		    TaobaoClient client = new DefaultTaobaoClient(url, key,serviceSecret);
			// 封装返回数据
			TradeFullinfoGetResponse incResponse = new TradeFullinfoGetResponse();
			
			TradeFullinfoGetRequest  temp = new TradeFullinfoGetRequest();
		
					
			temp.setFields("buyer_message,promotion_details,seller_nick,buyer_nick,title,type,created,sid,tid,seller_rate,buyer_rate,seller_memo," +
					"status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified," +
					"consign_time,buyer_obtain_point_fee,point_fee,real_point_fee,received_payment,commission_fee," +
					"pic_path,num_iid,num_iid,num,price,cod_fee,cod_status,shipping_type,receiver_name,receiver_state," +
					"receiver_city,receiver_district,receiver_address,receiver_zip,receiver_mobile,receiver_phone," +
					"orders.title,orders.pic_path,orders.price,orders.num,orders.iid,orders.num_iid,orders.sku_id," +
					"orders.refund_status,orders.status,orders.oid,orders.total_fee,orders.payment,orders.discount_fee," +
					"orders.adjust_fee,orders.sku_properties_name,orders.item_meal_name,orders.buyer_rate,orders.seller_rate," +
					"orders.outer_iid,orders.outer_sku_id,orders.refund_id,orders.seller_type,trade_from,orders.order_from");
			temp.setTid(tid);
			try {
				incResponse = client.execute(temp, sessionkey);
			} catch (Exception e) {
				e.printStackTrace();
//						Logger.error("调用获取单笔交易的详细信息接口出错:"+incResponse.getErrorCode(), e);
				
				return "";
			}
	
		
		return (null == incResponse.getBody() ? "" : incResponse.getBody());
		
	}
	
	
	/**
	 * 淘宝御城河调用
	 * @param url  调用的地址
	 * @param list tradeIds的匹配字段
	 * @param html 报文
	 * @param operation 描述
     */
	public void taskExecutor(String url,List<String[]> list,String html, String operation,String appId) {
		try{
			String appName="用友淘宝应用";
			List<String> tradeIds = new ArrayList<String>();
			Map<String ,String> result= TopSecretUtil.getMatch(list, html);
			for (String tradeId :result.keySet()){
				tradeIds.add(tradeId);
			}
			if("23205977".equals(appId)){
				appName="用友电商通";
			}else if("21581307".equals(appId)){
				appName="用友ERP";
			}
			new TaobaoSafeOrderThread("系统", "121.41.172.45", "0000000000", url, tradeIds, operation, new Date(),appId,appName).start();
		}catch (Exception e){
			Logger.error("淘宝御城河调用失败",e);
		}
	}


	

}
