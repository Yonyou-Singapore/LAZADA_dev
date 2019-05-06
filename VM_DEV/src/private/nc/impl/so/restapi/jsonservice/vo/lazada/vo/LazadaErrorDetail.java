package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * 如果调用API是为了批量处理，若调用失败（部分或全部请求失败），响应会给出错误细节
 */
public class LazadaErrorDetail {

    private String field;
    private String seller_sku;
    private String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSeller_sku() {
        return seller_sku;
    }

    public void setSeller_sku(String seller_sku) {
        this.seller_sku = seller_sku;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
