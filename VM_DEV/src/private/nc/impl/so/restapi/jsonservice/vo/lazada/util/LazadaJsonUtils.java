package nc.impl.so.restapi.jsonservice.vo.lazada.util;

import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaGetOrderDetailResponse;
import nc.impl.so.restapi.jsonservice.vo.lazada.vo.LazadaProductsInfo;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description: Lazada平台JSON转换工具
 * @author: ll
 * @company: yonyou
 * @date: 
 */

public class LazadaJsonUtils {

    
    /**
     * 将List<LazadaGetOrderDetailResponse>转换为NC需要的JSON格式
     * @param normalString
     * @return
     */
	public JSONArray convertJSON(List<LazadaGetOrderDetailResponse> orderlist){
		JSONArray jsonlist = new JSONArray();
		
		
		
		for(LazadaGetOrderDetailResponse order : orderlist){
			
			JSONObject head = new JSONObject();
			
				JSONObject itemobj = new JSONObject();
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				
				itemobj.put("pk_org", "SG");
				itemobj.put("vtrantypecode", "SG-CN-01");
				itemobj.put("vbillcode", "");
				
				try {
					
					Date date = format.parse(order.getCreated_at());
					itemobj.put("dbilldate", format.format(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				itemobj.put("ccustomerid", "");
				itemobj.put("cdeptid", "");
				itemobj.put("corigcurrencyid", order.getProducts().get(0).getCurrency());
				itemobj.put("cinvoiceno", order.getProducts().get(0).getInvoice_number());
				itemobj.put("vdef1", order.getAddress_billing().getCountry());
				itemobj.put("vdef2", order.getOrder_id().toString());
				itemobj.put("vdef3", order.getAddress_shipping().getLast_name()+" "+order.getAddress_shipping().getFirst_name());
				itemobj.put("vdef4", order.getAddress_shipping().getPhone());
				itemobj.put("vdef5", order.getAddress_shipping().getAddress1()+order.getAddress_shipping().getAddress2()+order.getAddress_shipping().getAddress4());
				itemobj.put("vdef7", order.getProducts().get(0).getShipping_type());
				itemobj.put("vdef20", order.getStatuses().get(0));
				
			head.put("so.so_saleorder", itemobj);
			head.put("so.so_saleorder_b", convertSubJSON(order.getProducts()));
			
			jsonlist.add(head);
				
		}
		
		return jsonlist;
	}

	public JSONArray convertSubJSON(List<LazadaProductsInfo> productlist){
		JSONArray jsonlist = new JSONArray();
		for(LazadaProductsInfo product : productlist){
			if(jsonlist.size()<1){
			
				JSONObject obj = new JSONObject();
				
				obj.put("crowno", "");
				obj.put("cmaterialvid",product.getSku());
				obj.put("nqtunitnum",productlist.size());
				obj.put("norigdiscount", product.getVoucher_amount());
				obj.put("nqtorigprice", Double.toString((Double.parseDouble(product.getPaid_price())-Double.parseDouble(product.getTax_amount()))));
				obj.put("nqtorigtaxprice", product.getPaid_price());
				obj.put("ctaxcodeid", "");
				obj.put("ntaxrate", "");
				obj.put("ntax", product.getTax_amount());
				obj.put("norigmny", Double.toString((Double.parseDouble(product.getPaid_price())-Double.parseDouble(product.getTax_amount()))));
				obj.put("norigtaxmny", product.getPaid_price());
				obj.put("csendstordocid", "");
				obj.put("blargessflag", "");
	            obj.put("csendstockorgid", "");
	            obj.put("vdef4",product.getOrder_item_id());
	            obj.put("vdef5",product.getShipment_provider());
	            obj.put("vdef6",product.getTracking_code());
	            
	            jsonlist.add(obj);
				
			}
		}
		
		return jsonlist;
	}
}
