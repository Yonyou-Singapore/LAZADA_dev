package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.util.List;

/**
 * Created by suzhaowen on 2018/11/1
 */
public class LazadaGetOrderListResponse {

    private Integer count;// 订单数量
    private List<LazadaGetOrderDetailResponse> orders;// 订单列表

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<LazadaGetOrderDetailResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<LazadaGetOrderDetailResponse> orders) {
        this.orders = orders;
    }
}
