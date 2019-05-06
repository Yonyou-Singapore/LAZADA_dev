package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * Created by Administrator on 2017/7/6.
 */
public class LazadaPaginationInfo {

    private Integer total_count;// 总数量
    private Integer total_page;// 总页数
    private Integer current_page;// 当前页
    private Integer per_page;// 每页数量
//    private Zhe800RelInfo rel;// 详细地址

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Integer getTotal_page() {
        return total_page;
    }

    public void setTotal_page(Integer total_page) {
        this.total_page = total_page;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

//    public Zhe800RelInfo getRel() {
//        return rel;
//    }
//
//    public void setRel(Zhe800RelInfo rel) {
//        this.rel = rel;
//    }
}
