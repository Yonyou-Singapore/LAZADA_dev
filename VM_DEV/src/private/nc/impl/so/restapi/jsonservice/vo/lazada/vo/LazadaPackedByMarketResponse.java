package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * lazada快�?公司
 * create by suzhaowen on 2018/11/2
 */
public class LazadaPackedByMarketResponse {

    private LazadaPackedMarkets[] packed_Market_infos; //运输服务供应�?

    public LazadaPackedMarkets[] getPacked_Market_infos() {
        return packed_Market_infos;
    }

    public void setPacked_Market_infos(LazadaPackedMarkets[] LazadaPackedMarkets) {
        this.packed_Market_infos = packed_Market_infos;
    }
}
