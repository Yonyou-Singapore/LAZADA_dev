package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * create by ll on 2019/05/23
 */
public class LazadaPackedMarkets {

	
	
//	"order_item_id": "123456",
//    "purchase_order_id": "567890",
//    "purchase_order_number": "ABC-123456",
//    "tracking_number": "TRACK-1126935-4306",
//    "shipment_provider": "5",
//    "package_id": "MPDS-200131783-9800"
//	
	
    
    private String order_item_id; //order item id
    private String purchase_order_id; //OMS order ID
    private String purchase_order_number; //OMS order number
    private String tracking_number; //Shipping package ID
    private String shipment_provider; //shipment provider
    private String package_id; // Package tracking number
  

    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }

    public String getPurchase_order_id() {
        return purchase_order_id;
    }

    public void setPurchase_order_id(String purchase_order_id) {
        this.purchase_order_id = purchase_order_id;
    }

    public String getPurchase_order_number() {
        return purchase_order_number;
    }

    public void setPurchase_order_number(String purchase_order_number) {
        this.purchase_order_number = purchase_order_number;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public String getShipment_provider() {
        return shipment_provider;
    }

    public void setShipment_provider(String shipment_provider) {
        this.shipment_provider = shipment_provider;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

  
}
