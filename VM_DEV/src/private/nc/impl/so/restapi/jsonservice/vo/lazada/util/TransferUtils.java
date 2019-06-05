package nc.impl.so.restapi.jsonservice.vo.lazada.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

/**
 * 
 * @author jiahch
 * 
 */

public class TransferUtils {

	private Gson gson = null;

	public TransferUtils() {
		gson = new Gson();
	}

	/**
	 * 灏咼SON瀛楃涓茶浆鍖栦负瀵硅薄
	 * 
	 * @param str
	 * @param clazz
	 * @return
	 */
	public Object transferStrToObject(String str, Class<?> clazz) {
		Object obj = null;
		if (null == str)
			return null;
		if (null != str) {
			try {
				obj = gson.fromJson(str, clazz);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("json瀛楃涓茶浆鍖栧け", e);
			}
		}
		return obj;
	}

	public String toJson(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * 灏嗗璞¤浆鍖栦负JSON瀛楃涓�
	 * 
	 * @param obj
	 * @return
	 */
	public String transferObjToJson(Object obj) {
		String str = null;
		return str;
	}

	/**
	 * 灏唈son 瀛楃涓茶浆鍖栦负list瀵硅薄
	 * 
	 * @param str
	 * @param @param type 杩斿洖绫诲瀷(濡�java.lang.reflect.Type type = new
	 *        com.google.gson.reflect.TypeToken<List<User>>() { }.getType();)
	 * @return
	 */
	public List<?> transferJsonToList(String str, Type type) {
		if (str == null)
			return null;
		List listObj = null;
		try {
			listObj = gson.fromJson(str, type);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("杞寲涓簂ist鍑洪敊", e);
		}
		return listObj;
	}

	/**
	 * 灏唋ist 杞寲涓簀son 瀛楃涓�
	 * 
	 * @param list
	 * @return
	 */
	public String transferListToJson(List list) {
		String str = null;
		try {
			str = gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("list杞琷son瀛楃涓插嚭閿", e);
		}
		return str;
	}

	/**
	 * 灏嗚緭鍏ユ祦杞寲涓哄璞�
	 * 
	 * @param is
	 * @return
	 */
	public Object transferStreamToObj(InputStream is) {
		Object obj = null;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(is);
			obj = in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("杈撳叆娴佽浆鍖栦负瀵硅薄鍑洪敊", e);
		}
		return obj;
	}

	/**
	 * 鍏抽棴娴�
	 */
	public void closeStream(InputStream is) {
		if (null != is) {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 鎵撳紑杩炴帴娴�
	 * 
	 * @param path
	 */
	public URLConnection openConnection(String path) {
		URL url = null;
		URLConnection conn = null;
		try {
			url = new URL(path);
			conn = url.openConnection();
			if (conn instanceof HttpURLConnection) {
				((HttpURLConnection) conn).setChunkedStreamingMode(2048);
			}
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("content-type",
					"application/x-java-serialized-object,charset=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("鎵撳紑杩炴帴澶辫触");
		}
		return conn;
	}

	/**
	 * 寰楀埌鐗╂枡璁块棶鏈嶅姟鍣ㄨ矾寰�
	 * 
	 * @return
	 */
	public static String getMaterialPath() {
		String path = "http://20.2.4.14:80/service/VerificationServlet";
		return path;
	}

	public static void transferDataToDomain(Map<String, String> map,
			URLConnection conn) {
		ObjectOutputStream oos = null;
		TransferUtils utils = new TransferUtils();
		// String path = utils.getDomainPath(servletName);
		// URLConnection conn = utils.openConnection(path);
		String material = utils.transferMapToJson(map);
		try {
			oos = new ObjectOutputStream(conn.getOutputStream());
			oos.writeObject(material);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("浼犺緭NC棰嗗煙浜у搧鍑洪敊", e);
		}
	}
	
	public static void transferMapToDomain(Map<String, Object> map,
			URLConnection conn) {
		ObjectOutputStream oos = null;
		TransferUtils utils = new TransferUtils();
		// String path = utils.getDomainPath(servletName);
		// URLConnection conn = utils.openConnection(path);
		String material = utils.transferMapToJson(map);
		try {
			oos = new ObjectOutputStream(conn.getOutputStream());
			oos.writeObject(material);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 灏唌ap 杞寲涓簀son 瀛楃涓�
	 * 
	 * @param list
	 * @return
	 */
	public String transferMapToJson(Map map) {
		String str = null;
		try {
			str = gson.toJson(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("map杞琷son瀛楃涓插嚭閿", e);
		}
		return str;
	}

	/**
	 * 灏佽浼犺緭鏁版嵁绫�鏃犺繑鍥炲�
	 * 
	 * @param servletName
	 * @param list
	 */
	public static void transferDataToDomain(List list, URLConnection conn) {
		ObjectOutputStream oos = null;
		TransferUtils utils = new TransferUtils();
		// String path = utils.getDomainPath(servletName);
		// URLConnection conn = utils.openConnection(path);
		HashMap<String, String> data = new HashMap<String, String>();
		String material = utils.transferListToJson(list);
		data.put("data", material.toString());
		try {
			oos = new ObjectOutputStream(conn.getOutputStream());
			oos.writeObject(data);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("浼犺緭NC棰嗗煙浜у搧鍑洪敊", e);
		}
	}

	/**
	 * 杩斿洖鏈嶅姟鍣ㄦ暟鎹�
	 * 
	 * @param conn
	 * @return
	 */
	public Object callBackData(URLConnection conn) {
		Object obj = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(conn.getInputStream());
			obj = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("鏈嶅姟鍣ㄨ繑鍥炴暟鎹嚭鐜伴敊璇", e);
		} finally {
			if (null != ois) {
				try {
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	/**
	 * 鎸夊瓧鑺傝鍙栬緭鍏ユ祦
	 * 
	 * @param is
	 * @return
	 */
	public String readStreamByBytes(InputStream inputStream) {
		StringBuffer postResult = new StringBuffer();
		byte[] bytes = new byte[1024];
		int count;
		try {
			count = inputStream.read(bytes);
			while (count != -1) {
				postResult.append(new String(bytes, 0, count));
				count = inputStream.read(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("璇诲彇杈撳叆娴佸嚭閿", e);
		}
		return postResult.toString();
	}

	/**
	 * 灏唈son涓茶浆鎹负Map瀵硅薄
	 * 
	 * @param json
	 * @return
	 */
	public Map parseJson2Map(String json) {
		Map mapObj = gson.fromJson(json, HashMap.class);
		return mapObj;
	}

	public static void transferDataToDomain(TreeMap<String, Object> map,
			URLConnection conn) {
		ObjectOutputStream oos = null;
		TransferUtils utils = new TransferUtils();
		// String path = utils.getDomainPath(servletName);
		// URLConnection conn = utils.openConnection(path);
		String material = utils.transferMapToJson(map);
		try {
			oos = new ObjectOutputStream(conn.getOutputStream());
			oos.writeObject(material);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("浼犺緭NC棰嗗煙浜у搧鍑洪敊", e);
		}
	}
}
