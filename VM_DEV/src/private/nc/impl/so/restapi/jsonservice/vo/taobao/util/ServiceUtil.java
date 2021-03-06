package nc.impl.so.restapi.jsonservice.vo.taobao.util;


import nc.impl.so.restapi.jsonservice.vo.taobao.util.MCloudRequest;
import com.google.gson.Gson;
import com.taobao.api.internal.util.StringUtils;
import com.yonyoucloud.uretail.util.http.HttpsRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ServiceUtil {

	public final static String URL = "http://sjt.yonyoucloud.com/servlet/BaseHttpServlet";// 118.178.170.255
	// public final static String URL_RDS =
	// "http://121.41.172.45/servlet/TopHttpServletForOmsRds";
	public final static String URL_RDS = "http://121.41.172.79/servlet/TopHttpServletForOmsRds";

	Logger logger = LoggerFactory.getLogger(ServiceUtil.class);

	/**
	 * 调用数据通接口
	 * 
	 * @param request
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public String execute(MCloudRequest request) {
		return executeData(request, URL);
	}

	public String executeTopRds(MCloudRequest request) {
		String plat = request.getPlat().toString();
//		if("top".equals(plat)) {
//			request.setPlat("toprds");
//		}
		request.setIsRds("true");
		String reqUrl = URL_RDS;
		return executeData(request, reqUrl);
	}

	public String executeForTest(String paramVal, String url, String appSecret) {
		try {
			return execute(paramVal, url, appSecret);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String execute(String paramVal, String url, String appSecret) throws Exception {
		// 签名校验
		String sign = SignUtil.signForSjt(appSecret, paramVal);

		HttpPost httpRequest = new HttpPost(url);
		// Post运作传送变数必须用NameValuePair[]
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("params", paramVal));
		params.add(new BasicNameValuePair("sign", sign));

		// 发出HTTP request
		httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		// 取得HTTP response
		HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); // 执行调用数据通

		return EntityUtils.toString(httpResponse.getEntity());

	}

	private String executeData(MCloudRequest request, String url) {
		try {
			Gson gson = new Gson();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			request.setTimestamp(df.format(new Date()));
			// String json = JSONObject.toJSONString(request);

			// 获取签名密钥
			// String enting=request.getEnting();
			// 清空密钥信息，不需要传给数据通
			// request.SetEnting(null);
			String paramVal = gson.toJson(request);
			logger.info("调用数据通URL:" + url + " 平台：" + request.getPlat() + " 方法：" + request.getMethod() + " 请求报文:"
					+ paramVal);
			String str = execute(paramVal, url, request.getAppSecret());
			logger.info("调用数据通URL:" + url + " 平台：" + request.getPlat() + " 方法：" + request.getMethod() + " 返回报文" + str);
			return str;
		} catch (Exception e) {
			logger.error("调用数据通URL:" + url + " 平台：" + request.getPlat() + " 方法：" + request.getMethod() + "异常", e);
			logger.error(e.getMessage());
			return "错误："+e.getMessage();
		}
	}

	/* taobao 解密请求 */
	public String PostToTopDecrypt(String cAppKey, String session, String paramJson) {
		String resultStr = "[]";
		try {
			// cAppKey="u8key";
			if (StringUtils.isEmpty(cAppKey))
				cAppKey = "u8key";

			Map<String, String> param = new HashMap<String, String>();
			param.put("data", paramJson);
			param.put("session", session);
			String url = "";
			if (cAppKey.toLowerCase().equals("u8key")) {
				url = "http://sjtparsing.yonyoucloud.com/servlet/U8BatchDecrypt";
			} else {
				url = "http://sjtparsing.yonyoucloud.com/servlet/DstBatchDecrypt";
			}

			HttpClient httpClient = new HttpClient();
			PostMethod postMethod = new PostMethod(url);
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			postMethod.addParameter("session", session);
			postMethod.addParameter("data", paramJson);
			httpClient.executeMethod(postMethod);
			resultStr = postMethod.getResponseBodyAsString();
			// System.out.println(resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("TOP解密接口调用失败");
		}
		return resultStr;
	}

	protected String PostData(Map<String, String> parameters) {
		StringBuilder postData = new StringBuilder();
		Boolean hasParam = false;

		for (String key : parameters.keySet()) {
			if (hasParam) {
				postData.append("&");
			}
			postData.append(key);
			postData.append("=");
			postData.append(parameters.get(key));
			hasParam = true;
		}

		return postData.toString();
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String executePrivate(MCloudRequest request) {
		Gson gson = new Gson();
		String paramVal = gson.toJson(request);
//		HttpPost httpRequest =new HttpPost(InterConst.URL);
		String url = "";// PropertiesUtil.getCommonFileValue("eccloud_inter_test_intf_url");
		HttpPost httpRequest = new HttpPost(url);
		// Post运作传送变数必须用NameValuePair[]
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("params", paramVal));
		// 发出HTTP request
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 取得HTTP response
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);

			String str = EntityUtils.toString(httpResponse.getEntity());
			logger.info("调用数据通URL:" + url + " 平台：" + request.getPlat() + " 方法：" + request.getMethod() + "返回报文" + str);
			return str;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("调用数据通URL:" + url + " 平台：" + request.getPlat() + " 方法：" + request.getMethod() + "异常", e);
			return null;
		}
	}

	/**
	 * 获取token
	 * 
	 * @param url
	 * @param appkey
	 * @param secret
	 * @param refresh_token
	 * @return
	 */
	public String getAuth(String url, String appkey, String secret, String refresh_token) {

		String param = "grant_type=refresh_token&client_id=" + appkey + "&client_secret=" + secret + "&refresh_token="
				+ refresh_token;

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {

			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			// conn.setRequestProperty("user-agent",
			// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送 POST 请求出现异常！", e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		return result;
	}

	public void executeHttps(MCloudRequest request) throws Exception {
		String path = "https://openapi.dsclouds.com/servlet/DstDecrypt";
		path = URL;
		path = "https://118.178.170.255/servlet/BaseHttpServlet";
		path = "https://openapi.dsclouds.com/servlet/BaseHttpServlet";
		Gson gson = new Gson();
		// 获取签名密钥
//		String enting=request.getEnting();
		// 清空密钥信息，不需要传给数据通
//		request.SetEnting(null);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("params", request);
		String content = gson.toJson(params);
		this.post(path, content);
		// return "";

		HttpsRequest req = new HttpsRequest();
		String aa = req.sendPost(path, content, null);
		System.out.println(aa);

	}

	public void post(String path, String content) throws Exception {
		HttpsURLConnection.setDefaultHostnameVerifier(this.new NullHostNameVerifier());
		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		try {
			URL url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			// conn.setConnectTimeout(10000);//连接超时 单位毫秒
			// conn.setReadTimeout(2000);//读取超时 单位毫秒
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
			// 发送请求参数
//            content = "{\"plat\":\"top\",\"session\":\"6101003f3e3117beZZd99c2134184ce4af581d559eeef871081998929\",\"appId\":\"1\",\"method\":\"getTradeFullInfoDecrypt\",\"format\":\"json\",\"access\":\"1\",\"request\":{\"fields\":\"seller_nick,buyer_nick,title,type,created,sid,tid,seller_rate,buyer_rate,status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified,consign_time,buyer_obtain_point_fee,point_fee,real_point_fee,seller_memo,buyer_message,received_payment,commission_fee,pic_path,num_iid,num,price,cod_fee,cod_status,shipping_type,receiver_name,receiver_state,receiver_city,receiver_district,receiver_address,receiver_zip,receiver_mobile,receiver_phone,orders,invoice_type,invoice_name,invoice_kind,seller_flag,buyer_flag,buyerTaxNO,service_orders\",\"tid\":\"237557988029412689\",\"order\":0}}";
			printWriter.write(content);// post的参数 xx=xx&yy=yy
			// flush输出流的缓冲
			printWriter.flush();
			// 开始获取数据
			BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len;
			byte[] arr = new byte[1024];
			while ((len = bis.read(arr)) != -1) {
				bos.write(arr, 0, len);
				bos.flush();
			}
			bos.close();
			String str = new String(bos.toString().getBytes(), "UTF-8");
			System.out.println(str);
			// return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return "";
	}

	static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	} };

	public class NullHostNameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	}
}
