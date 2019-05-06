package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * lazada 公共错误Response
 */
public class LazadaErrorResponse {
    private String type;
    private String code;
    private String message;
    private LazadaErrorDetail[] detail;
    private String request_id;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LazadaErrorDetail[] getDetail() {
        return detail;
    }

    public void setDetail(LazadaErrorDetail[] detail) {
        this.detail = detail;
    }
}
