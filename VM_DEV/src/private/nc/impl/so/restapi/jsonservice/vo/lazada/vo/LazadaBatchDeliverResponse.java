package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;

/**
 * create by suzhaowen on2018/11/1
 * 发货相应实体
 */
public class LazadaBatchDeliverResponse implements Serializable {
    private static final long serialVersionUID = -616882954733974464L;

    private LazadaDeliverOrderItems[] order_items;

    public LazadaDeliverOrderItems[] getOrder_items() {
        return order_items;
    }
    public void setOrder_items(LazadaDeliverOrderItems[] order_items) {
        this.order_items = order_items;
    }
}
