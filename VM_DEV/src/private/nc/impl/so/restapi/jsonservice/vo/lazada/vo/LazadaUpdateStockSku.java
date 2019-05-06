package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Sku")
public class LazadaUpdateStockSku {
    @XStreamAlias("SellerSku")
    private String SellerSku;
    @XStreamAlias("Quantity")
    private String Quantity;
    @XStreamAlias("Price")
    private String Price;
    @XStreamAlias("SalePrice")
    private String SalePrice;
    @XStreamAlias("SaleStartDate")
    private String SaleStartDate;
    @XStreamAlias("SaleEndDate")
    private String SaleEndDate;

    public String getSellerSku() {
        return SellerSku;
    }

    public void setSellerSku(String sellerSku) {
        SellerSku = sellerSku;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(String salePrice) {
        SalePrice = salePrice;
    }

    public String getSaleStartDate() {
        return SaleStartDate;
    }

    public void setSaleStartDate(String saleStartDate) {
        SaleStartDate = saleStartDate;
    }

    public String getSaleEndDate() {
        return SaleEndDate;
    }

    public void setSaleEndDate(String saleEndDate) {
        SaleEndDate = saleEndDate;
    }

    @Override
    public String toString() {
        return "LazadaUpdateStockSku{" +
                "SellerSku='" + SellerSku + '\'' +
                ", Quantity='" + Quantity + '\'' +
                ", Price='" + Price + '\'' +
                ", SalePrice='" + SalePrice + '\'' +
                ", SaleStartDate='" + SaleStartDate + '\'' +
                ", SaleEndDate='" + SaleEndDate + '\'' +
                '}';
    }
}
