package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * @description:
 * @author: szw
 * @company: yonyou
 * @date: 2018/12/3 14:59
 */

public class LazadaGetOrderListDataResponse {

    private LazadaGetOrderListResponse data;
    private String code;
    private String request_id;

    public LazadaGetOrderListResponse getData() {
        return data;
    }

    public void setData(LazadaGetOrderListResponse data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
}
