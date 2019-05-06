package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Request")
public class Payload {
    @XStreamAlias("skus")
    private LazdaUpdateStockSkus skus;

    public LazdaUpdateStockSkus getSkus() {
        return skus;
    }

    public void setSkus(LazdaUpdateStockSkus skus) {
        this.skus = skus;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "skus=" + skus +
                '}';
    }

//    public static void main(String[] args) {
//        LazadaUpdateStockSku lazadaUpdateStockSku1 = new LazadaUpdateStockSku();
//        lazadaUpdateStockSku1.setPrice("1.22");
//        lazadaUpdateStockSku1.setQuantity("2");
//        lazadaUpdateStockSku1.setSellerSku("33333");
//        LazadaUpdateStockSku lazadaUpdateStockSku2 = new LazadaUpdateStockSku();
//        lazadaUpdateStockSku2.setPrice("1.23332");
//        lazadaUpdateStockSku2.setQuantity("2444");
//        lazadaUpdateStockSku2.setSellerSku("335555333");
//        List<LazadaUpdateStockSku> skuList = new ArrayList<LazadaUpdateStockSku>();
//        skuList.add(lazadaUpdateStockSku1);
//        skuList.add(lazadaUpdateStockSku2);
//        LazdaUpdateStockSkus lazdaUpdateStockSkus = new LazdaUpdateStockSkus();
//        lazdaUpdateStockSkus.setSkus(skuList);
//
//        Payload lazadaUpdateStockRequest = new Payload();
//        lazadaUpdateStockRequest.setSkus(lazdaUpdateStockSkus);
////        System.out.println(lazadaUpdateStockRequest.toString());
//        XStream stream = new XStream();
//        //通知XStream对象读取并识别RendezvousMessage中的注解
//        stream.processAnnotations(lazadaUpdateStockRequest.getClass()); // 识别obj类中的注解
//        System.out.println(stream.toXML(lazadaUpdateStockRequest));
//    }
}
