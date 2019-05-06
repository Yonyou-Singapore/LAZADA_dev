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
	 * 将JSON字符串转化为对象
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
				throw new RuntimeException("json字符串转化失败", e);
			}
		}
		return obj;
	}

	public String toJson(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * 将对象转化为JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public String transferObjToJson(Object obj) {
		String str = null;
		return str;
	}

	/**
	 * 将json 字符串转化为list对象
	 * 
	 * @param str
	 * @param @param type 返回类型(如:java.lang.reflect.Type type = new
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
			throw new RuntimeException("转化为list出错", e);
		}
		return listObj;
	}

	/**
	 * 将list 转化为json 字符串
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
			throw new RuntimeException("list转json字符串出错", e);
		}
		return str;
	}

	/**
	 * 将输入流转化为对象
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
			throw new RuntimeException("输入流转化为对象出错", e);
		}
		return obj;
	}

	/**
	 * 关闭流
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
	 * 打开连接流
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
			throw new RuntimeException("打开连接失败");
		}
		return conn;
	}

	/**
	 * 得到物料访问服务器路径
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
			throw new RuntimeException("传输NC领域产品出错", e);
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
	 * 将map 转化为json 字符串
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
			throw new RuntimeException("map转json字符串出错", e);
		}
		return str;
	}

	/**
	 * 封装传输数据类 无返回值
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
			throw new RuntimeException("传输NC领域产品出错", e);
		}
	}

	/**
	 * 返回服务器数据
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
			throw new RuntimeException("服务器返回数据出现错误", e);
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
	 * 按字节读取输入流
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
			throw new RuntimeException("读取输入流出错", e);
		}
		return postResult.toString();
	}

	/**
	 * 将json串转换为Map对象
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
			throw new RuntimeException("传输NC领域产品出错", e);
		}
	}
}
