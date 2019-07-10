package nc.impl.so.restapi.jsonservice.vo.lazada.vo;


/**
 * @description:
 * @author: szw
 * @company: yonyou
 * @date: 2018/12/3 22:35
 */

public class LazadaBatchDeliverDataResponse {
    private LazadaBatchDeliverResponse data;
    private Integer code;
    private Integer request_id;

    public LazadaBatchDeliverResponse getData() {
        return data;
    }

    public void setData(LazadaBatchDeliverResponse data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }
}
