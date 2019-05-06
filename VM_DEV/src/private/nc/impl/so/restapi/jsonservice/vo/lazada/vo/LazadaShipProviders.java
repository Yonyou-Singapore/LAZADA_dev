package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

/**
 * create by suzhaowen on 2018/11/2
 */
public class LazadaShipProviders {

    private Integer is_default; //此运输服务供应商将按照订单处理的标准选择。
    private String tracking_code_example; //跟踪代码举例
    private String enabled_delivery_options; //运输供应商的配送速率，可以是多个值。可取值为economy（经济）, standard（标准）, and express（快递）.
    private String name; //运输商的名字。设置SetStatusToPackedByMarketplace时会使用此字段。
    private Integer cod; //运输供应商通过此运输服务获得现金
    private String tracking_code_validation_regex; // 核对追踪码的常规表达式。例如：/^[a-z0-9]{10}$/i Please check using http://regex101.com/#pcre.
    private String tracking_url; //运输商的货物追踪连接。追踪代码可使用占位符{{{TRACKING_NR}}}。否则，跟踪代码应该被追加到跟踪URL的末尾。
    private Integer api_integration; //若运输商有API，值为1。

    public Integer getIs_default() {
        return is_default;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }

    public String getTracking_code_example() {
        return tracking_code_example;
    }

    public void setTracking_code_example(String tracking_code_example) {
        this.tracking_code_example = tracking_code_example;
    }

    public String getEnabled_delivery_options() {
        return enabled_delivery_options;
    }

    public void setEnabled_delivery_options(String enabled_delivery_options) {
        this.enabled_delivery_options = enabled_delivery_options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getTracking_code_validation_regex() {
        return tracking_code_validation_regex;
    }

    public void setTracking_code_validation_regex(String tracking_code_validation_regex) {
        this.tracking_code_validation_regex = tracking_code_validation_regex;
    }

    public String getTracking_url() {
        return tracking_url;
    }

    public void setTracking_url(String tracking_url) {
        this.tracking_url = tracking_url;
    }

    public Integer getApi_integration() {
        return api_integration;
    }

    public void setApi_integration(Integer api_integration) {
        this.api_integration = api_integration;
    }
}
