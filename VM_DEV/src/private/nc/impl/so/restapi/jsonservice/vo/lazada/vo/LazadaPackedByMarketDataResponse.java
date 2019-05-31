package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * @description:
 * @author: ll
 * @company: yonyou
 * @date: 2019/2/4 16:35
 */

public class LazadaPackedByMarketDataResponse {
    private LazadaPackedByMarketResponse data;
    private String code;
    private String request_id;

    public LazadaPackedByMarketResponse getData() {
        return data;
    }

    public void setData(LazadaPackedByMarketResponse data) {
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
