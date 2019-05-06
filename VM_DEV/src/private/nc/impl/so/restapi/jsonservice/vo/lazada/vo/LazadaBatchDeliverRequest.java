package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;

/**
 * create by suzhaowen on 2018/11/1
 * 发货请求实体
 */
public class LazadaBatchDeliverRequest implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = 1663010313875780364L;
    /**
     * 下面运输类型之一：
     *  Dropship ： 卖方负责运输
     *  Pickup ： 零售商店从卖方地点取货（越库）
     *  send_to_warehouse ： 卖方负责运输到指定仓库（越库）
     */
    private String delivery_type;
    private String order_item_ids; //准备运输的被标记的订单品项。在方括号中用逗号分隔
    private String shipping_provider; //在GetShipmentProviders上的有效的运输服务供应商。“dropship“运输类型是必填的
    private String tracking_number; //包裹追踪号码。在drop-shipping（制造商直接出货）条件下必填。

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getOrder_item_ids() {
        return order_item_ids;
    }

    public void setOrder_item_ids(String order_item_ids) {
        this.order_item_ids = order_item_ids;
    }

    public String getShipping_provider() {
        return shipping_provider;
    }

    public void setShipping_provider(String shipping_provider) {
        this.shipping_provider = shipping_provider;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }
}
