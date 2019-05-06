package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * Created by Administrator on 2017/7/6.
 */
public class LazadaInvoiceInfo {

    private String type;
    private String content;
    private String receiver;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
