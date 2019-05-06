package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import java.io.Serializable;

/**
 * lazada库存更新
 */
public class LazadaUpdateStockRequest implements Serializable {
    private static final long serialVersionUID = 4842924481939464175L;

    private Payload payload;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
