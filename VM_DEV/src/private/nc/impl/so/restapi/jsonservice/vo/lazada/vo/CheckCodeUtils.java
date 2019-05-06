package nc.impl.so.restapi.jsonservice.vo.lazada.vo;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * @description:
 * @author: szw
 * @company: yonyou
 * @date: 2018/12/3 15:03
 */

public class CheckCodeUtils {

    public String getJSONValueByKey(String params, String key) {
//		String params = "{\"access_token\":\"500003016280zbdjxcvSDYjaq3OywCIHxgaEQxBD1975eac93mvQgvRy9L4W2E\",\"country\":\"sg\",\"refresh_token\":\"50001301e28qwEccx6oVUendKgZktSmGa8TPAOqW1ac48a42wPtX1gReaAxUpQ\",\"account_platform\":\"seller_center\",\"refresh_expires_in\":2592000,\"country_user_info\":[{\"country\":\"sg\",\"user_id\":\"100095623\",\"seller_id\":\"100073425\",\"short_code\":\"SG1XO2KP\"}],\"expires_in\":604800,\"account\":\"jimluo@newstead.com.sg\",\"code\":\"0\",\"request_id\":\"0b86d54a15432265595433188\"}";
        if(StringUtils.isNotEmpty(params)){
            JSONObject pa= JSONObject.parseObject(params);
            return pa.getString(key);
        }else {
            return "";
        }
    }

}
