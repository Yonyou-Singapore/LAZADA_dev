package nc.impl.so.restapi.jsonservice.vo.taobao.util;

/**
 * Created by Administrator on 2017/2/18.
 */
public class TradesSoldIncrementGetRequest {
    private String buyerNick;
    private String endModified;
    private String extType;
    private String fields;
    private Long pageNo;
    private Long pageSize;
    private String rateStatus;
    private String startModified;
    private String status;
    private String tag;
    private String type;
    private Boolean useHasNext;

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getEndModified() {
        return endModified;
    }

    public void setEndModified(String endModified) {
        this.endModified = endModified;
    }

    public String getExtType() {
        return extType;
    }

    public void setExtType(String extType) {
        this.extType = extType;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getRateStatus() {
        return rateStatus;
    }

    public void setRateStatus(String rateStatus) {
        this.rateStatus = rateStatus;
    }

    public String getStartModified() {
        return startModified;
    }

    public void setStartModified(String startModified) {
        this.startModified = startModified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getUseHasNext() {
        return useHasNext;
    }

    public void setUseHasNext(Boolean useHasNext) {
        this.useHasNext = useHasNext;
    }
}
