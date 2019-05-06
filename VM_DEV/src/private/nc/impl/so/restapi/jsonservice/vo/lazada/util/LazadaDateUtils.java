package nc.impl.so.restapi.jsonservice.vo.lazada.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: Lazada平台工具日期格式化工具
 * @author: ll
 * @company: yonyou
 * 
 */

public class LazadaDateUtils {

    public static final String format_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String getFormat_yyyyMMddHHmmss_iso8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String getFormat_yyyyMMddHHmmss_iso8601_noT = "yyyy-MM-dd HH:mm:ss Z";
    public static final String getFormat_yyyyMMdd = "yyyy-MM-dd";

    /**
     * 正常时间格式转为iso8601格式
     * @param normalString
     * @return
     */
    public static String normalToIso8601(String normalString){
        if (StringUtils.isEmpty(normalString)) {
            return "";
        }
        SimpleDateFormat normalFormat = new SimpleDateFormat(format_yyyyMMddHHmmss);
        SimpleDateFormat iso8601Format = new SimpleDateFormat(getFormat_yyyyMMddHHmmss_iso8601);
        String isoformat = "";
        try {
           
            isoformat = isoformat+iso8601Format.format(Long.valueOf(normalString));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isoformat;
    }

    /**
     * iso8601格式转为正常格式
     * @param iso8601String
     * @return
     */
    public static String iso8601ToNormal(String iso8601String) {
        if (StringUtils.isEmpty(iso8601String)) {
            return "";
        }
        SimpleDateFormat iso8601Format = new SimpleDateFormat(getFormat_yyyyMMddHHmmss_iso8601);
        SimpleDateFormat normalFormat = new SimpleDateFormat(format_yyyyMMddHHmmss);
        String normalString = "";
        try {
            Date date = iso8601Format.parse(iso8601String);
            normalString = normalFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return normalString;
    }
    /**
     * iso8601格式转为正常格式 ，没有T标识
     * @param iso8601StringNoT
     * @return
     */
    public static String iso8601ToNormalNoT(String iso8601StringNoT) {
        if (StringUtils.isEmpty(iso8601StringNoT)) {
            return "";
        }
        SimpleDateFormat iso8601Format = new SimpleDateFormat(getFormat_yyyyMMddHHmmss_iso8601_noT);
        SimpleDateFormat normalFormat = new SimpleDateFormat(format_yyyyMMddHHmmss);
        String normalString = "";
        try {
            Date date = iso8601Format.parse(iso8601StringNoT);
            normalString = normalFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return normalString;
    }
}
