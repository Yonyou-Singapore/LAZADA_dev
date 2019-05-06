package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * Created by Administrator on 2017/7/6.
 */
public class LazadaSkuDescsInfo {

    private String sku_num;//属性名
    private String sku_desc;//属性值
    private Integer stock;// 库存
    private String cur_price;// 商品现价
    private String org_price;// 商品原价
    private String shelf;// 库位
    private String seller_no;//Sku商家编号
    private String sku_id;// sku级别的业务标识ID,一般是商品id+_+sku_num,如果sku_num为空则用000000占位

    public String getSku_num() {
        return sku_num;
    }

    public void setSku_num(String sku_num) {
        this.sku_num = sku_num;
    }

    public String getSku_desc() {
        return sku_desc;
    }

    public void setSku_desc(String sku_desc) {
        this.sku_desc = sku_desc;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCur_price() {
        return cur_price;
    }

    public void setCur_price(String cur_price) {
        this.cur_price = cur_price;
    }

    public String getOrg_price() {
        return org_price;
    }

    public void setOrg_price(String org_price) {
        this.org_price = org_price;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getSeller_no() {
        return seller_no;
    }

    public void setSeller_no(String seller_no) {
        this.seller_no = seller_no;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }
}
