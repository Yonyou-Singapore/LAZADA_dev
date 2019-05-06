package nc.impl.so.restapi.jsonservice.vo.lazada.vo;


/**
 * @description:
 * @author: szw
 * @company: yonyou
 * @date: 2018/12/3 22:56
 */

public class GetProductsDataResponse {

    private GetProductsResponse data;
    private String code;
    private String request_id;

    public GetProductsResponse getData() {
        return data;
    }

    public void setData(GetProductsResponse data) {
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
