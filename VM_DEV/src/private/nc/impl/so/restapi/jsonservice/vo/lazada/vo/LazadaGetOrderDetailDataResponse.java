package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * @description:
 * @author: szw
 * @Company: yonyou
 * @date: 2018/12/3 14:56
 */

public class LazadaGetOrderDetailDataResponse {

    private LazadaGetOrderDetailResponse data;
    private String code;
    private String request_id;

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

    public LazadaGetOrderDetailResponse getData() {
        return data;
    }

    public void setData(LazadaGetOrderDetailResponse data) {
        this.data = data;
    }
}
