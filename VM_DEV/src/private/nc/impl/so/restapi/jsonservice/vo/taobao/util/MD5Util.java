package nc.impl.so.restapi.jsonservice.vo.taobao.util;

import java.security.MessageDigest;

/**
 * @description 1MD5鍔犲瘑绫�
 * @author zhangwd
 * @date 2012-5-24
 * 
 */
public class MD5Util {

	/**
	 * MD5绠楁硶
	 */
	private static final String ALGORITHM_MD5 = "MD5";

	/**
	 * 鏂扮殑md5绛惧悕锛岄灏炬斁secret銆�
	 * 
	 * @param data
	 *            浼犵粰鏈嶅姟鍣ㄧ殑鍙傛暟
	 *
	 */
	public static String md5(String data) {
		String result = null;

		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
			MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
//			result = md.digest(data.toString().getBytes("UTF-8")).toString()
//					.toUpperCase();
			byte[] resultb = md.digest(data.toString().getBytes("UTF-8"));
			result = byte2hex(resultb);
		} catch (Exception e) {
			throw new RuntimeException("sign error !");
		}
		return result;
	}

	public static void main(String[] args) {
//2app_key2customerId2formatxmlmethod2sign_methodmd5timestamp20v212
		String data= "entingappKey123456780233FA31AD94AA59CFA65305session123timestamp2016-11-16 13:23:30param={\"shopName\":\"\",\"clientId\":\"456\"}enting";
		//45C72EFAF8D51F0EFCBD2B047F49BA5F
		String result = md5(data);
		System.out.println(result);
	}

	/**
	 * 浜岃繘鍒惰浆瀛楃涓�
	 */
	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	public static boolean checkSign(String result, String sign) {
		if (result.equals(sign))
			return true;
		else
			return false;

	}
}
