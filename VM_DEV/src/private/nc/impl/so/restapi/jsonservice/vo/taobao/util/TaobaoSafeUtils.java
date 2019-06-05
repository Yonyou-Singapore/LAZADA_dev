package nc.impl.so.restapi.jsonservice.vo.taobao.util;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * 浣�    鑰咃細寮犻懌
 * 
 * 璇�    鏄庯細娣樺疂鐢熸�瀹夊叏
 * 
 * 鍒涘缓鏃堕棿锛�015-12-30
 */

public class TaobaoSafeUtils {
	
	private static Logger logger = Logger.getLogger(TaobaoSafeUtils.class);
	static String appKey = "68756628";// 鏃ュ織鎺ュ叆绯荤粺鍒嗛厤缁欑敤鎴穉ppKey,骞朵笉鏄紑鏀惧钩鍙�top)鍒嗛厤鐨刟ppKey
	static String appSecret = "3t722gXA2JQSZkvzNDHo";// 鏃ュ織鎺ュ叆绯荤粺鍒嗛厤缁欑敤鎴穉ppsecret,骞朵笉鏄紑鏀惧钩鍙�top)鍒嗛厤鐨刟ppsecret

	/**
	 * 璁㈠崟璁块棶鏃ュ織
	 * http://gw.ose.aliyun.com/event/order
	 * 璁㈠崟璁块棶鏃ュ織鍙互璁╂垜浠竻妤氱殑浜嗚В璋併�鍦ㄤ粈涔堟椂闂淬�閫氳繃浠�箞搴旂敤銆佹嬁鍒颁簡浠�箞淇℃伅锛屽浜庢垜浠帰娴嬫暟鎹硠婕忛潪甯告湁甯姪銆�
	 * @param username 璐﹀彿 user.getUserAccount()
	 * @param userIp 鐢ㄦ埛ip getRemoteHost()
	 * @param ati [ @CookieValue(value="_ati",defaultValue="")String ati ] 瀵笲/S鏋舵瀯鐨勫簲鐢紝鎺ュ叆瀛旀槑閿佸悗锛屼細鍦ㄥ煙鍚嶄笅鐢熸垚涓�釜cookie _ati銆傚湪鏈嶅姟鍣ㄧ锛屽彲浠嶩TTP璇锋眰涓幏鍙栧悕绉颁负_ati鐨刢ookie鐨勫�
	 * @param url 瀹㈡埛绔姹傜殑URL
	 * @param tradeIds 璁㈠崟鍙峰垪琛紝鐢ㄨ嫳鏂囬�鍙峰垎闅�
	 * @param operation 瀵硅鍗曠殑鎿嶄綔锛屾瘮濡傛墦鍗拌鍗�
	 * @param date 璋冪敤鏃堕棿 鍙傛暟鍊间负null鏃朵唬琛ㄥ綋鍓嶆椂闂�***寮傛璋冪敤鏃跺繀浼�
	 */
	public static void order(String username, String userIp, String ati, String url, List<String> tradeIds, String operation, Date date,String appId,String appName) {
		try {
			//璁㈠崟id鍒嗙粍锛屾渶澶�00涓负涓�粍
			List<String> idsGroups = new ArrayList<String>();
			StringBuilder ids = new StringBuilder();
			boolean split = false;
			for (int i = 0; i < tradeIds.size(); i++) {
				if(split){ids.append(",");}
				ids.append(tradeIds.get(i));
				split = true;
				if(i % 100 == 99 || i == tradeIds.size()-1){
					idsGroups.add(ids.toString());
					ids = new StringBuilder();
					split = false;
				}
			}
			//鍒嗙粍璋冪敤鎺ュ彛
			for(int i = 0; i < idsGroups.size(); i++){
				Map<String, String> paramMap = new TreeMap<String, String>();//鍔犲瘑瀛楃涓查渶鎺掑簭锛屾墍浠ヤ娇鐢╰reemap
				paramMap.put("userId", username);//甯愬彿浣撶郴涓殑鐢ㄦ埛ID鎴栬�鐢ㄦ埛鍚�
				paramMap.put("userIp", userIp);//IP
				paramMap.put("ati", ati);//cookie淇℃伅
				paramMap.put("topAppKey", appId);//TOP appkey
				paramMap.put("appName", appName);
				paramMap.put("url", url);//瀹㈡埛绔姹傜殑URL
				paramMap.put("tradeIds", idsGroups.get(i));//璁㈠崟鍙峰垪琛紝鐢ㄨ嫳鏂囬�鍙峰垎闅旓紝姣忔鏈�100鏉¤褰曘�濡傛灉瓒呰繃100鏉★紝鎷嗗垎鎴愬鏉¤姹�
				paramMap.put("operation", operation);//瀵硅鍗曠殑鎿嶄綔锛屾瘮濡傛墦鍗拌鍗�
				paramMap.put("time", String.valueOf(null != date ? (date.getTime()) : (new Date()).getTime()));
				paramMap.put("appKey", appKey);
				
				StringBuffer query = new StringBuffer();
				String sign = getSignature(appSecret, paramMap);//鍔犲瘑瀛楃涓�
				for (Entry<String, String> en : paramMap.entrySet()) {
					query.append(en.getKey());
					query.append("=");
					query.append(URLEncoder.encode(en.getValue(), "UTF-8"));// 閲嶇偣;涓�畾瑕佸湪鐢熶骇绛惧悕鍚庢墽琛寀rl缂栫爜
					query.append("&");
				}
				query.append("sign=");
				query.append(sign);
				String postResult;
				postResult = doPost("http://gw.ose.aliyun.com/event/order", query.toString());
//				System.out.println("寰″煄姹犺繑鍥炴暟鎹細"+postResult);
				JSONObject jo = JSONObject.fromObject(postResult);
				if(!"success".equals(String.valueOf(jo.get("result")))){
					logger.error("调用淘宝订单访问日志接口返回错误信息："+String.valueOf(jo.get("errMsg"))+" tradeId值："+idsGroups.get(i));
					throw new RuntimeException("调用淘宝订单访问日志接口返回错误信息："+String.valueOf(jo.get("errMsg"))+" tradeId值："+idsGroups.get(i));
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("调用淘宝订单访问日志接口出错：sign签名字符串声称失败",e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("调用淘宝订单访问日志接口出错：调用淘宝远程接口失败",e);
		}
	}
	
	/**
	 * post璇锋眰
	 * @param path 鍦板潃
	 * @param query 鍙傛暟
	 * @return
	 * @throws IOException
	 */
	private static String doPost(String path, String query) throws IOException {

		URLConnection connection = null;
		OutputStreamWriter out = null;
		try {
			URL url = new URL(path);
			connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setConnectTimeout(5000);//5绉掕秴鏃�
			out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			out.write(query); // 鍚戦〉闈紶閫掓暟鎹�post鐨勫叧閿墍鍦紒
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}

		// 涓�棪鍙戦�鎴愬姛锛岀敤浠ヤ笅鏂规硶灏卞彲浠ュ緱鍒版湇鍔″櫒鐨勫洖搴旓細
		StringBuffer respStr = new StringBuffer();
		try {
			String sCurrentLine = null;
			InputStream l_urlStream = connection.getInputStream();
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				respStr.append(sCurrentLine + "\r\n");
			}
			l_urlStream.close();
		} catch (Exception e) {

		}
		return respStr.toString();

	}

	/**
	 * 鑾峰彇鍔犲瘑绛惧悕瀛楃涓�
	 * @param appSecret 
	 * @param paramMap
	 * @return
	 */
	private static String getSignature(String appSecret, Map<String, String> paramMap) {
		try {
			if (paramMap == null) {
				return "";
			}
			StringBuilder combineString = new StringBuilder();
			combineString.append(appSecret);
			Set<Entry<String, String>> entrySet = paramMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				combineString.append(entry.getKey() + entry.getValue());
			}
			combineString.append(appSecret);

			byte[] bytesOfMessage = combineString.toString().getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			String signature = bytesToHexString(thedigest);
			return signature;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 16
	 * @param src
	 * @return
	 */
	private static String bytesToHexString(byte[] src) {
		try {
			StringBuilder stringBuilder = new StringBuilder("");
			if (src == null || src.length <= 0) {
				return null;
			}
			for (int i = 0; i < src.length; i++) {
				int v = src[i] & 0xFF;
				String hv = Integer.toHexString(v);
				if (hv.length() < 2) {
					stringBuilder.append(0);
				}
				stringBuilder.append(hv);
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			return null;
		}
	}


}
