package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * create by suzhaowen on 2018/11/1
 */
public class LazadaDeliverOrderItems {

    private Integer order_item_id;//订单品项编码
    private Integer purchase_order_id;//销售中心识别。可选，如果您的业务场景不包括它，请忽略它。
    private String purchase_order_number;//销售中心订单数量。可选，如果您的业务场景不包括它，请忽略它。

    public Integer getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(Integer order_item_id) {
        this.order_item_id = order_item_id;
    }

    public Integer getPurchase_order_id() {
        return purchase_order_id;
    }

    public void setPurchase_order_id(Integer purchase_order_id) {
        this.purchase_order_id = purchase_order_id;
    }

    public String getPurchase_order_number() {
        return purchase_order_number;
    }

    public void setPurchase_order_number(String purchase_order_number) {
        this.purchase_order_number = purchase_order_number;
    }
}
