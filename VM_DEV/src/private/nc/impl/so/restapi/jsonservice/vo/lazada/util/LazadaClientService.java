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
			
			if(updateDate==null || updateDate.length()<=0){
				request.addApiParameter("created_before",endTime);
				request.addApiParameter("created_after",startTime);
			}
			
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
 
	  	/**
		 * ��ȡ������Ӧ��
		 * @param orderId
		 * @return
		 */
	
        public String GetShipmentProviders(String url, String accessToken) {
		
		try {
			
			
			LazopClient client = new LazopClient(url, key, serviceSecret);
			LazopRequest request = new LazopRequest();
			request.setApiName("/shipment/providers/get");
			request.setHttpMethod("GET");
			LazopResponse response = client.execute(request, accessToken);
			System.out.println(response.getBody());
			
			return response.getBody();

		} catch (Exception e) {
			
			Logger.error("来赞达平台调用刷新access_token接口异常" + e.getMessage(), e);
			return null;
		}
	}

  
     /**
	 * �������
	 * @param orderId
	 * @return
	 */
	
	public String SetStatusToPackedByMarketplace(String url, String accessToken, String orderItemIds,String shipProvider) {
		
		try {	
			
			LazopClient client = new LazopClient(url, key, serviceSecret);
			LazopRequest request = new LazopRequest();
			request.setApiName("/order/pack");
			request.addApiParameter("shipping_provider", shipProvider);
			request.addApiParameter("delivery_type", "dropship");
			request.addApiParameter("order_item_ids", orderItemIds);
			LazopResponse response = client.execute(request, accessToken);
			System.out.println(response.getBody());
			Thread.sleep(10);
				
			return response.getBody();

		} catch (Exception e) {
			
			Logger.error("SetStatusToPackedByMarketplaceʧ��" + e.getMessage(), e);
			return null;
		}
	}
  
  
  
  /**
	 * ������д����
	 * @param orderId
	 * @return
	 */
	
 public String SetStatusToReadyToShip(String url, String accessToken, String orderItemIds,String shipProvider,String trackingNo) {
		
		try {
			
			LazopClient client = new LazopClient(url, key, serviceSecret);
			LazopRequest request = new LazopRequest();
			request.setApiName("/order/rts");
			request.addApiParameter("delivery_type", "dropship");
			request.addApiParameter("order_item_ids", orderItemIds);
			request.addApiParameter("shipment_provider", shipProvider);
			request.addApiParameter("tracking_number", trackingNo);
			LazopResponse response = client.execute(request, accessToken);			
			
			return response.getBody();

		} catch (Exception e) {		
			Logger.error("来赞达平台调用刷新access_token接口异常" + e.getMessage(), e);
			return null;
		}
	}
 
 
     /**
	 * ˢ����Ȩ refresh_access_token
	 * @param
	 * @throws Exception
	 */
	public static void refreshAccessToken() {
		
		LazopClient lazopClient = new LazopClient("https://auth.lazada.com/rest","107215","RF7ooIEg8KJOSjI0v4PXMUDvQgWc62w6");
		LazopRequest lazaopRequest = new LazopRequest();
		lazaopRequest.setTimestamp(new Date().getTime());
		lazaopRequest.setHttpMethod("POST");
//		lazaopRequest.setApiName("/GetOrder");
		lazaopRequest.setApiName("/auth/token/refresh");  
	
	    //{"access_token":"500003016280zbdjxcvSDYjaq3OywCIHxgaEQxBD1975eac93mvQgvRy9L4W2E","country":"sg","refresh_token":"50001301e28qwEccx6oVUendKgZktSmGa8TPAOqW1ac48a42wPtX1gReaAxUpQ","account_platform":"seller_center","refresh_expires_in":2592000,"country_user_info":[{"country":"sg","user_id":"100095623","seller_id":"100073425","short_code":"SG1XO2KP"}],"expires_in":604800,"account":"jimluo@newstead.com.sg","code":"0","request_id":"0b86d54a15432265595433188"}

		lazaopRequest.addApiParameter("refresh_token", "50001301e28qwEccx6oVUendKgZktSmGa8TPAOqW1ac48a42wPtX1gReaAxUpQ");
		
//		lazaopRequest.addApiParameter("uuid", "38284839234"); 
		LazopResponse lazopResponse = null;
		try {
			lazopResponse = lazopClient.execute(lazaopRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(lazopResponse.getBody());
		
	}
	
	

}
