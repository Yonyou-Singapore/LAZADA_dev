package nc.impl.so.restapi.jsonservice.vo.taobao.util;


import nc.impl.so.restapi.jsonservice.vo.taobao.util.MD5Util;
import com.taobao.api.internal.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2016/8/19.
 */
public class SignUtil {


    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /**
     *
     * @param appKey
     * @param secret
     * @param session
     * @param method
     * @param timestamp
     * @param format
     * @param v
     * @return
     */
    public static String sign(String appKey, String secret, String session, String method, String timestamp, String format, String v) {

        TreeMap treemap = new TreeMap();
        Map<String,String> pmap = new HashMap<String,String>();

        pmap = publicMap(appKey, secret,session,method, timestamp,format,v);

        treemap.putAll(pmap);
        if(StringUtils.isEmpty(secret)){
            secret = "secreKey";
        }
        StringBuilder sb = new StringBuilder(secret);
        Iterator result = treemap.entrySet().iterator();

        while(result.hasNext()) {
            Map.Entry entry = (Map.Entry)result.next();
            String name = (String)entry.getKey();
            String value = (String)entry.getValue();
            if(StringUtils.areNotEmpty(name, value)) {
                sb.append(name).append(value);
            }
        }
        sb.append(secret);

        String result1 =MD5Util.md5(sb.toString());
        return result1;
    }

    /**
     *对入参进行排序加密
     * @param secret
     * @param cloudRequestParam 所有参数
     * @return
     */
    public static String sign(String secret, Map<String,String> cloudRequestParam) {

        TreeMap treemap = new TreeMap();
        treemap.putAll(cloudRequestParam);
        if(StringUtils.isEmpty(secret)){
            secret = "secreKey";
        }
        StringBuilder sb = new StringBuilder(secret);
        Iterator result = treemap.entrySet().iterator();
        while(result.hasNext()) {
            Map.Entry entry = (Map.Entry)result.next();
            String name = (String)entry.getKey();
            String value = (String)entry.getValue();
            if(StringUtils.areNotEmpty(name, value)) {
                sb.append(name).append(value);
            }
        }
        sb.append(secret);

        String result1 =MD5Util.md5(sb.toString());
        return result1;
    }

    /**
     *2.1及以上版本签名
     * @param appKey
     * @param secret
     * @param session
     * @param method
     * @param timestamp
     * @param format
     * @param v
     * @return
     */
    public static String signV21plus(String appKey, String secret, String session, String method, String timestamp, String format, String v,String param_json) {

        TreeMap treemap = new TreeMap();
        Map<String,String> pmap = new HashMap<String,String>();

        pmap = publicMap(appKey, secret,session,method, timestamp,format,v,param_json);

        treemap.putAll(pmap);
        if(StringUtils.isEmpty(secret)){
//            secret = "secreKey";
            secret = "773d19fd286040cc81474f50c2222d0a";
        }
        StringBuilder sb = new StringBuilder(secret);
        Iterator result = treemap.entrySet().iterator();

        while(result.hasNext()) {
            Map.Entry entry = (Map.Entry)result.next();
            String name = (String)entry.getKey();
            String value = (String)entry.getValue();
            if(StringUtils.areNotEmpty(name, value)) {
                sb.append(name).append(value);
            }
        }
        sb.append(secret);

        String result1 =MD5Util.md5(sb.toString());
        return result1;
    }

    /**
     *数据通签名规则
     * @param enting 密钥
     * @param param_json 业务参数
     * @return
     */
    public static String signForSjt(String enting, String param_json) {

        if(StringUtils.isEmpty(enting)){
            enting="";
        }
        StringBuilder sb = new StringBuilder(enting);
        sb.append(param_json).append(enting);

        String result =MD5Util.md5(sb.toString());
        return result;
    }

    /**
     *
     * @param appKey
     * @param secret
     * @param timestamp
     * @param format
     * @param v
     * @return
     */
    public static String signWithoutSession(String appKey, String secret,String timestamp, String format, String v) {

        TreeMap treemap = new TreeMap();
        Map<String,String> pmap = new HashMap<String,String>();

        pmap = publicMapWithoutSession(appKey, secret,timestamp,format,v);

        treemap.putAll(pmap);
        if(StringUtils.isEmpty(secret)){
            secret = "secreKey";
        }
        StringBuilder sb = new StringBuilder(secret);
        Iterator result = treemap.entrySet().iterator();

        while(result.hasNext()) {
            Map.Entry entry = (Map.Entry)result.next();
            String name = (String)entry.getKey();
            String value = (String)entry.getValue();
            if(StringUtils.areNotEmpty(name, value)) {
                sb.append(name).append(value);
            }
        }
        sb.append(secret);
        /*logger.info("sign resource content:"+sb.toString());*/
        String result1 =MD5Util.md5(sb.toString());
        return result1;
    }

    /**
     * 生成URL参数
     * @param
     * @param sign 签名数据
     * @param timestamp
     * @return
     */
    public static String paramUrl(String appKey, String secret, String session, String method,String timestamp, String format, String v,String sign){
        Map<String,String> pmap = new HashMap<String,String>();
        pmap = publicMap(appKey, secret,session,method, timestamp,format,v);
        pmap.put("sign",sign); //签名
        TreeMap treemap = new TreeMap();
        treemap.putAll(pmap);
        StringBuilder sb = new StringBuilder("?");
        Iterator result = pmap.entrySet().iterator();

        while(result.hasNext()) {
            Map.Entry entry = (Map.Entry)result.next();
            String name = (String)entry.getKey();
            String value = (String)entry.getValue();
            if(StringUtils.areNotEmpty(name, value)) {
                sb.append(name).append("=").append(value).append("&");
            }
        }
        return sb.deleteCharAt(sb.lastIndexOf("&")).toString();
    }

    public static String paramUrlWithoutSession(String appKey, String secret, String timestamp, String format, String v,String sign){
        Map<String,String> pmap = new HashMap<String,String>();
        pmap = publicMapWithoutSession(appKey, secret, timestamp,format,v);
        pmap.put("sign",sign); //签名
        TreeMap treemap = new TreeMap();
        treemap.putAll(pmap);
        StringBuilder sb = new StringBuilder("?");
        Iterator result = pmap.entrySet().iterator();

        while(result.hasNext()) {
            Map.Entry entry = (Map.Entry)result.next();
            String name = (String)entry.getKey();
            String value = (String)entry.getValue();
            if(StringUtils.areNotEmpty(name, value)) {
                sb.append(name).append("=").append(value).append("&");
            }
        }
        return sb.deleteCharAt(sb.lastIndexOf("&")).toString();
    }

    private static Map<String,String> publicMap(String appKey, String secret, String session, String method,String timestamp, String format, String v){
        Map<String,String> pmap = new HashMap<String,String>();
        pmap.put("appKey",appKey);
        pmap.put("session",session);
        pmap.put("method",method);
        pmap.put("timestamp", timestamp);
        pmap.put("format", format);
        pmap.put("v", v);
        return pmap;
    }

    /**
     * 组装map集合
     * @param appKey
     * @param secret
     * @param session
     * @param method
     * @param timestamp
     * @param format
     * @param v
     * @param param_json
     * @return
     */
    private static Map<String,String> publicMap(String appKey, String secret, String session, String method,String timestamp, String format, String v,String param_json){
        Map<String,String> pmap = new HashMap<String,String>();
        pmap.put("appKey",appKey);
        pmap.put("session",session);
        pmap.put("method",method);
        pmap.put("timestamp", timestamp);
        pmap.put("format", format);
        pmap.put("v", v);
        pmap.put("param_json", param_json);
        return pmap;
    }

    private static Map<String,String> publicMapWithoutSession(String appKey, String secret, String timestamp, String format, String v){
        Map<String,String> pmap = new HashMap<String,String>();
        pmap.put("appKey",appKey);
        pmap.put("timestamp", timestamp);
        pmap.put("format", format);
        pmap.put("v", v);
        return pmap;
    }


    /**
     * 生成数字签名，及URL
     * @param appKey
     * @param secret
     * @param session
     * @param method
     * @param timestamp
     * @param format
     * @param v
     * @return
     */
    public static String paramUrlAndSign(String appKey, String secret, String session, String method,String timestamp,String format, String v) {
        String sign = sign(appKey, secret,session,method,  timestamp,format,v);
        String newTimestamp = null;
        try {
            newTimestamp = URLEncoder.encode(timestamp, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return paramUrl( appKey,  secret,  session,  method, newTimestamp, format,  v,sign);
    }

    /**
     * 生成数字签名，及URL
     * @param appKey
     * @param secret
     * @param timestamp
     * @param format
     * @param v
     * @return
     */
    public static String paramUrlAndSignWithoutSession(String appKey, String secret,String timestamp,String format, String v) {
        String sign = signWithoutSession(appKey, secret,timestamp,format,v);
        String newTimestamp = null;
        try {
            newTimestamp = URLEncoder.encode(timestamp, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return paramUrlWithoutSession( appKey, secret, newTimestamp, format, v,sign);
    }



}
