package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * @description:
 * @author: szw
 * @company: yonyou
 * @date: 2018/12/4 16:35
 */

public class LazadaExpressCompanyDataResponse {
    private LazadaExpressCompanyResponse data;
    private String code;
    private String request_id;

    public LazadaExpressCompanyResponse getData() {
        return data;
    }

    public void setData(LazadaExpressCompanyResponse data) {
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
