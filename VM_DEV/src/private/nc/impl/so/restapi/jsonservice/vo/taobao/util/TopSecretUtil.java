package nc.impl.so.restapi.jsonservice.vo.taobao.util;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.security.SecurityClient;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shaoyingh
 * @description 娣樺疂瀵嗘枃鎶ユ枃瑙ｅ瘑宸ュ叿
 * @email shaoyingh@yonyou.com
 * @time 2017-8-01
 */

public class TopSecretUtil {
    private static String url;

    static {
        if (url == null) {
        	
        	 
            url = "https://eco.taobao.com/router/rest";
        }
          
    }

    /**
     * 瑙ｅ瘑娣樺疂瀵嗘枃瀛楁(涓�埇鎯呭喌涓嬪氨鐢ㄨ繖涓�
     *
     * @param appkey
     * @param secret
     * @param randomNum
     * @param session
     * @param list      鍔犲瘑鐨勫瓧娈�鏍煎紡{["鍔犲瘑瀛楁鐨勫紑澶�:"鍔犲瘑瀛楁鐨勭粨灏�]}
     *                  濡傦細{["'phone'":"'"]}
     * @param text      鍏ㄩ儴鎶ユ枃(鍚瘑鏂�
     * @return 瑙ｅ瘑鍚庣殑鎶ユ枃
     */
    public static String decrypt(String appkey, String secret, String randomNum, String session, List<String[]> list, String text) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            SecurityClient securityClient = new SecurityClient(new DefaultTaobaoClient(url, appkey, secret), randomNum);
            Map<String, String> map = getMatch(list, text);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String newValue=entry.getKey();
                if (StringUtils.isEmpty(newValue)) {
                    continue;//绌哄�涓嶅鐞�
                }
                if (entry.getValue().contains("mobile")) {
                    newValue = securityClient.decrypt(entry.getKey(), "phone", session);
                }else {
                    newValue = securityClient.decrypt(entry.getKey(), "simple", session);
                }
                result.put(entry.getKey(), newValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.size() > 0) {
            for (Map.Entry<String, String> entry : result.entrySet()) {
                text = text.replace(entry.getKey(), entry.getValue());
            }
        }
        return text;
    }

    /**
     * 姝ｅ垯鍒ゆ柇鏄惁涓烘墜鏈哄彿鐮�
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 瑙ｅ瘑娣樺疂瀵嗘枃瀛楁(杩欎釜鎺ュ彛鐨刴ap闇�閫氳繃getMatch鏉ヨ幏鍙�
     *
     * @param appkey
     * @param secret
     * @param randomNum
     * @param session
     * @param map       闇�瑙ｅ瘑鐨勫唴瀹�
     * @return 瑙ｅ瘑鍚庣殑闆嗗悎{"瀵嗘枃":"鏄庢枃"} 鏍煎紡锛歿"~3lUmBjPEf12DpByic0tuWrqGdmxoX3F/q/TAGq53k5g=~1~":"2088722624414343"}
     */
    public static Map<String, String> decrypt(String appkey, String secret, String randomNum, String session, Map<String, String> map) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            SecurityClient securityClient = new SecurityClient(new DefaultTaobaoClient(url, appkey, secret), randomNum);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().contains("mobile") || entry.getValue().contains("phone")) {
                    String newValue = securityClient.decrypt(entry.getKey(), "phone", session);
                    result.put(entry.getKey(), newValue);
                    System.out.println(entry.getKey() + "----" + newValue);
                } else {
                    String newValue = securityClient.decrypt(entry.getKey(), "simple", session);
                    result.put(entry.getKey(), newValue);
                    System.out.println(entry.getKey() + "----" + newValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 鑾峰彇鍔犲瘑鍐呭
     *
     * @param list 鍔犲瘑鐨勫瓧娈�鏍煎紡{["鍔犲瘑瀛楁鐨勫紑澶�:"鍔犲瘑瀛楁鐨勭粨灏�]}
     *             濡傦細{["'phone'":"'"]}
     * @param text 鍏ㄩ儴鎶ユ枃
     * @return {["瀵嗘枃":"瀵嗘枃鐨刱ey"]}
     */
    public static Map<String, String> getMatch(List<String[]> list, String text) {
        Map<String, String> map = new HashMap<String, String>();
        for (String[] s : list) {
            String regex = s[0] + "([\\s\\S]*?)" + s[1];
            Matcher matcher = Pattern.compile(regex).matcher(text);
            while (matcher.find()) {
                String a = matcher.group();
                if (StringUtils.isNotEmpty(a)) {
                    a = a.substring(s[0].length(), a.lastIndexOf(s[1]));
                    map.put(a, s[0]);
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        String text="<tid>69378843797696527</tid>,3213\"buyer_nick\":\"~0ZRj9hmX2MmpW\\/m12NKksQ==~1~\"21321321<tid>74350926903084645</tid>,\"tid\":\"1111111\",\"tid\":\"2222222\",<tid>74364002162412253</tid>,<tid>75370772523501580</tid>,<tid>75358233013021482</tid>,<tid>69381755850199127</tid>,<tid>75342913752840170</tid>,<tid>74299741875787760</tid>,<tid>69344376704094714</tid>";
        List<String[]> list=new ArrayList<String[]>();
        list.add(new String[]{"<tid>", "</tid>"});
        list.add(new String[]{"\"tid\":\"", "\""});
        list.add(new String[]{"\"buyer_nick\":\"", "\""});
        Map<String, String> map=getMatch(list,text);
        for (String a:map.keySet()) {
            System.out.println(a);
        }
//        System.out.println("url:" + TopSecretUtil.url);
        System.out.println(TopSecretUtil.isMobileNO(""));
        System.out.println(TopSecretUtil.isMobileNO("0377-6888688"));
        System.out.println(TopSecretUtil.isMobileNO("18518801651"));
        System.out.println(TopSecretUtil.isMobileNO("17744591810"));
    }
}
