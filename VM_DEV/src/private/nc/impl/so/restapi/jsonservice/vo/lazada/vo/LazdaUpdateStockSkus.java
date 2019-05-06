package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("Skus")
public class LazdaUpdateStockSkus {

    @XStreamImplicit(itemFieldName="Sku")
    private List<LazadaUpdateStockSku> Sku;

    public List<LazadaUpdateStockSku> getSku() {
        return Sku;
    }

    public void setSkus(List<LazadaUpdateStockSku> skus) {
        this.Sku = skus;
    }

}
