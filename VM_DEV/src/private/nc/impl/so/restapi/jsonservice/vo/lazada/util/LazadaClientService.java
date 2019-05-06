package nc.impl.so.restapi.jsonservice.vo.lazada.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lazada.lazop.api.LazopClient;
import com.lazada.lazop.api.LazopRequest;
import com.lazada.lazop.api.LazopResponse;

import nc.bs.logging.Logger;

public class LazadaClientService {
	
	
	
	private static final String key = "107215";
	private static final String serviceSecret = "RF7ooIEg8KJOSjI0v4PXMUDvQgWc62w6";
	//private static final String accessToken = "50000900225xsdTpfASSHsgnlrxuTidYkVEot11c9460dixOfdDaQhLrPaFK7u";

	
	
	/**
	 * 获取订单列表
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String getOrderList(String url, String accessToken, String startTime,String endTime,Boolean isReadytoShip,String updateDate) {
		
		
		try{
			
			LazopClient client = new LazopClient(url, key, serviceSecret);
			LazopRequest request = new LazopRequest();
			request.setApiName("/orders/get");
			request.setHttpMethod("GET");
			
			request.addApiParameter("created_before",endTime);
			request.addApiParameter("created_after",startTime);
			if(updateDate!=null && updateDate.length()>0){
				request.addApiParameter("update_after",updateDate);
			}
			if(isReadytoShip){
				request.addApiParameter("status", "ready_to_ship");
			}
			request.addApiParameter("offset", "0");
			request.addApiParameter("limit", "100");

			LazopResponse response;
			response = client.execute(request, accessToken);
			
			return response.getBody();

		} catch (Exception e){
			Logger.error(e.getMessage(),e);
			return null;
		}

	}
	
	/**
	 * 获取订单明细
	 * @param orderId
	 * @return
	 */
	
   public String getOrderItems(String url,String accessToken, String orderId) {
		
		try {
			
			LazopClient client = new LazopClient(url, key, serviceSecret);
			LazopRequest request = new LazopRequest();
			request.setApiName("/order/items/get");
			request.setHttpMethod("GET");
			request.addApiParameter("order_id",orderId);
			request.setTimestamp(new Date().getTime());
			
			LazopResponse response = client.execute(request, accessToken);
			
			
			return response.getBody();

		} catch (Exception e) {
			
			Logger.error("来赞达平台调用刷新access_token接口异常" + e.getMessage(), e);
			return null;
		}
	}
   
   
   /**
	 * 物品发货
	 * @param orderId
	 * @return
	 */
	
  public String set(String url, String accessToken, String orderId) {
		
		try {
			
			LazopClient client = new LazopClient(url, key, serviceSecret);
			LazopRequest request = new LazopRequest();
			request.setApiName("/order/items/get");
			request.setHttpMethod("GET");
			request.addApiParameter("order_id",orderId);
			request.setTimestamp(new Date().getTime());
			
			LazopResponse response = client.execute(request, accessToken);
			
			
			return response.getBody();

		} catch (Exception e) {
			
			Logger.error("来赞达平台调用刷新access_token接口异常" + e.getMessage(), e);
			return null;
		}
	}
	
	

}
