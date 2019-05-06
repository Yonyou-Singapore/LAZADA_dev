package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * Created by Administrator on 2017/7/6.
 */
public class LazadaGoodsInfo {

    private String id;// String 商品ID
    private String name;// String 商品名称
    private String title;// String 商品副标题
    private String short_name;// String 商品短标题
    private Integer stoc;// 商品库存
    private Integer sales_count;// Number 商品销量
    private Integer org_price;// Number 商品原价
    private Integer cur_price;// Number 商品现价
    private String num;// 货号
    private String image;// 商品主图
    private String shelf;// 货位
    private String place_of_dispatch;// String 商品发货地
    private LazadaSkuDescsInfo[] sku_descs;//SkuDescsInfo[] sku属性信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public Integer getStoc() {
        return stoc;
    }

    public void setStoc(Integer stoc) {
        this.stoc = stoc;
    }

    public Integer getSales_count() {
        return sales_count;
    }

    public void setSales_count(Integer sales_count) {
        this.sales_count = sales_count;
    }

    public Integer getOrg_price() {
        return org_price;
    }

    public void setOrg_price(Integer org_price) {
        this.org_price = org_price;
    }

    public Integer getCur_price() {
        return cur_price;
    }

    public void setCur_price(Integer cur_price) {
        this.cur_price = cur_price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getPlace_of_dispatch() {
        return place_of_dispatch;
    }

    public void setPlace_of_dispatch(String place_of_dispatch) {
        this.place_of_dispatch = place_of_dispatch;
    }

    public LazadaSkuDescsInfo[] getSku_descs() {
        return sku_descs;
    }

    public void setSku_descs(LazadaSkuDescsInfo[] sku_descs) {
        this.sku_descs = sku_descs;
    }
}
