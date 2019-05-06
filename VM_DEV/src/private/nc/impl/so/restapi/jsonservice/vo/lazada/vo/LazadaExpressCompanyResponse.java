package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * lazada快递公司
 * create by suzhaowen on 2018/11/2
 */
public class LazadaExpressCompanyResponse {

    private LazadaShipProviders[] shipment_providers; //运输服务供应商

    public LazadaShipProviders[] getShipment_providers() {
        return shipment_providers;
    }

    public void setShipment_providers(LazadaShipProviders[] shipment_providers) {
        this.shipment_providers = shipment_providers;
    }
}
