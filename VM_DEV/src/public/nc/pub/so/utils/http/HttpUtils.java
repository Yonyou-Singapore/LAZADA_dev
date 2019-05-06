package nc.pub.so.utils.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

import nc.bs.logging.Logger;
import nc.pub.templet.converter.util.helper.ExceptionUtils;
import nc.uap.ws.console.utils.vo.Json;
import nc.vo.so.restapi.RestlogUtils;

import com.alibaba.fastjson.JSON;

/**
 * 提供http或者https连接公共类 jsonstr是请求的json， conn是URLConnection对象，设置好请求属性,用法见getConn
 * 
 * @author weiningc
 *
 */
public class HttpUtils {
	
	private static final String DEFAULTCHARSET = "UTF-8";
	
	/**
	 * 
	 * @param conn
	 * @param jsonstr
	 * @return
	 */
	public static String  post(URLConnection conn, String jsonstr) {
		return call(conn, jsonstr);
	}
	
	/**
	 * 
	 * @param conn
	 * @param json
	 */
	private static String call(URLConnection conn, String json) {
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		StringBuffer response = null;
		try {
			wr = new OutputStreamWriter(conn.getOutputStream(), DEFAULTCHARSET);
			wr.write(json);
			wr.flush();
			rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line = null;
			response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
			if (response.length() == 0) {
				Logger.init("DST");
				Logger.error("外部JSON数据格式电商通客户端未收到返回结果！");
				throw new Exception("外部JSON数据格式零售端操作未收到返回结果！");
			}
		} catch (Exception e) {
			RestlogUtils.porcessRestLogForSave("dst", "callOMS", "nc.bs.dst.http.HttpForSyncOms", json, response.toString());
			Logger.error(e);
			ExceptionUtils.wrapException(e.getMessage(), e);
		}
		Logger.error("==DST==外部JSON数据格式DST零售端操作返回结果:" + response.toString());
		//日志保存
		RestlogUtils.porcessRestLogForSave("dst", "callOMS", "nc.bs.dst.http.HttpForSyncOms", json, response.toString());
		//处理返回json
		return response.toString();
		
	}

	public static HttpURLConnection getConn(String secret, String token, String url) {
		url = url + "access_token=" + token;
		HttpURLConnection conn = null;
		
		try {
			URL realURL = new URL(url);
			conn = (HttpURLConnection) realURL
					.openConnection();
			Logger.info("成功获取Http连接URL：" + url);
			Logger.info("开始设置Http地址链接信息");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("charset", "UTF-8");
//			conn.setRequestProperty("X-URETAIL-AUTH",
//					md5sign);
//			//test
//			Map<String, Object> map = JSON.parseObject(Json.object2json(postdata),
//					LinkedHashMap.class);
//			conn.setRequestProperty("X-URETAIL-AUTH",
//					buildMD5Sign(map, secret, "UTF-8"));
			//end
			conn.setRequestMethod("POST");
			Logger.info("完成Http连接属性设置");
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
