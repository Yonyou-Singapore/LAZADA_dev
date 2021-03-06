package nc.impl.so.restapi.jsonservice.vo.taobao.util;

import com.taobao.api.ApiRuleException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.internal.util.RequestCheckUtils;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.TradeFullinfoGetResponse;

import java.util.Map;

public class TradeFullinfoGetRequest
  extends BaseTaobaoRequest<TradeFullinfoGetResponse>
{
  private String fields;
  private String tid;
  
  public void setFields(String fields)
  {
    this.fields = fields;
  }
  
  public String getFields()
  {
    return this.fields;
  }
  
  public void setTid(String tid)
  {
    this.tid = tid;
  }
  
  public String getTid()
  {
    return this.tid;
  }
  
  public String getApiMethodName()
  {
    return "taobao.trade.fullinfo.get";
  }
  
  public Map<String, String> getTextParams()
  {
    TaobaoHashMap txtParams = new TaobaoHashMap();
    txtParams.put("fields", this.fields);
    txtParams.put("tid", this.tid);
    if (this.udfParams != null) {
      txtParams.putAll(this.udfParams);
    }
    return txtParams;
  }
  
  public Class<TradeFullinfoGetResponse> getResponseClass()
  {
    return TradeFullinfoGetResponse.class;
  }
  
  public void check()
    throws ApiRuleException
  {
    RequestCheckUtils.checkNotEmpty(this.fields, "fields");
    RequestCheckUtils.checkNotEmpty(this.tid, "tid");
//    RequestCheckUtils.checkMaxValue(this.tid, Long.MAX_VALUE, "tid");
//    RequestCheckUtils.checkMinValue(this.tid, 1L, "tid");
  }
}