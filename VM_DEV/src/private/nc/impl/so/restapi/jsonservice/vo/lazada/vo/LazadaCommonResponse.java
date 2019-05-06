package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.util.List;

/**
 *  lazada 公共响应参数
 */
public class LazadaCommonResponse {

    private String type; //错误类型。可选值包括ISP(服务提供者错误)，ISV(研发者错误) ，SYSTEM（平台系统错误）
    private String code; //错误代码。例如：ServiceUnavailable，服务不可用
    private String message; //描述错误细节
    private String request_id; //特殊标识请求
    private List<LazadaErrorDetail> detail; //如果调用API是为了批量处理，若调用失败（部分或全部请求失败），响应会给出错误细节

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

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<LazadaErrorDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<LazadaErrorDetail> detail) {
        this.detail = detail;
    }
}

