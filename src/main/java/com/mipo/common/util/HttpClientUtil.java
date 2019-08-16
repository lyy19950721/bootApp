package com.mipo.common.util;

import com.alibaba.fastjson.JSONObject;
import com.mipo.common.http.MyHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Slf4j
public class HttpClientUtil {

  public static final String Encoding_UTF_8 = "utf-8";
  public static final String User_Agent = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
  public static final String Content_type_json = "application/json";
  public static final String Content_type_json_utf8 = "application/json;charset=UTF-8";
  public static final String Content_type_form = "application/x-www-form-urlencoded";
  public static final String Content_type_multipart = "multipart/form-data";
  public static final String Content_type_xml = "text/xml";
  public static final String HTTP_PROTOCOL_URL_PREFIX = "http://";


  public static MyHttpResponse httpPost(String url, String paramsJson) {
    return httpPost(url, paramsJson, null);
  }

  public static MyHttpResponse httpPost(String url, String paramsJson, Map<String, String> headers){
    //解决中文乱码问题
    StringEntity entity = new StringEntity(paramsJson, Encoding_UTF_8);
    return httpPost(url, entity, headers, false);

  }

  public static MyHttpResponse httpPost(String url, String paramsJson,
      Map<String, String> headers, boolean sync){
    //解决中文乱码问题
    StringEntity entity = new StringEntity(paramsJson, Encoding_UTF_8);
    return httpPost(url, entity, headers, sync);

  }

  public static MyHttpResponse httpPostEncript(String url, JSONObject paramsJson){
    //解决中文乱码问题
    StringEntity entity = new StringEntity(paramsJson.toJSONString(), Encoding_UTF_8);
    if(!url.startsWith("http")){
      url = HTTP_PROTOCOL_URL_PREFIX+url;
    }
    return httpPost(url, entity, null, false);

  }

  public static MyHttpResponse httpPost(String url,
      Map<String, Object> params, Map<String, String> headers) {
      //装填参数
      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      if(!CollectionUtils.isEmpty(params)){
        for (Entry<String, Object> entry : params.entrySet()) {
          nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
      }
      UrlEncodedFormEntity urlEncodedFormEntity = null;
      try {
        urlEncodedFormEntity = new UrlEncodedFormEntity(nvps, Encoding_UTF_8);
        urlEncodedFormEntity.setContentType(Content_type_json);
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        log.error("[HttpClientUtil]请求{},参数异常:{}", url, e.getMessage());
      }
    return httpPost(url, urlEncodedFormEntity, headers, false);
  }

  public static MyHttpResponse httpPost(String url, StringEntity paramEntity,
      Map<String, String> headers, boolean sync) {
    //post请求返回结果
    String dataResponse = "";
    MyHttpResponse myHttpResponse = new MyHttpResponse();

    HttpPost httpPost = new HttpPost(url);
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse httpResponse = null;
    try {
      if (null != paramEntity) {
        httpPost.setEntity(paramEntity);
      }

      if (CollectionUtils.isEmpty(headers)) {
        httpPost.addHeader("Accept", Content_type_json);
        httpPost.addHeader("Content-Type", Content_type_json_utf8);
      } else {
        for (Entry<String, String> entry : headers.entrySet()) {
          httpPost.addHeader(entry.getKey(), entry.getValue());
        }
      }
      int time = 16000;
      if(sync){
        time = 6000;
      }
      RequestConfig requestConfig = RequestConfig.custom()
          .setConnectionRequestTimeout(time)
          .setSocketTimeout(time)
          .setConnectTimeout(time)
          .build();
      httpPost.setConfig(requestConfig);
      httpResponse = httpClient.execute(httpPost);

      /**请求发送成功，并得到响应**/
      int statusCode = httpResponse.getStatusLine().getStatusCode();
      myHttpResponse.setStatusCode(statusCode);
      HttpEntity httpEntity = httpResponse.getEntity();
      if (statusCode == MyHttpResponse.STATUS_CODE_SUCCESS) {
        url = URLDecoder.decode(url, Encoding_UTF_8);
        /**读取服务器返回过来的json字符串数据**/
        dataResponse = EntityUtils.toString(httpEntity);
        myHttpResponse.setData(dataResponse);
        myHttpResponse.setStatusMessage(httpResponse.getStatusLine().toString());
        EntityUtils.consume(httpEntity);

      } else {
        dataResponse = EntityUtils.toString(httpEntity);
        log.error("[HttpClientUtil]请求{},失败:{},httpResponse:{},dataResponse:{}", url, httpResponse.getStatusLine().toString(),httpResponse,dataResponse);
        myHttpResponse.setStatusMessage(httpResponse.getStatusLine().toString());
        // 非200状态码可能包含的数据体
        myHttpResponse.setData(dataResponse);
        EntityUtils.consume(httpEntity);

      }
    } catch (Exception e) {
      /**
       * 处理异常
       * */
      setHttpStatusAdnMessage(myHttpResponse,url,e);

    }finally {
      try {
        if (httpResponse != null) {
          httpResponse.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      httpPost.releaseConnection();
      try {
        httpClient.close();
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }
    return myHttpResponse;
  }

  /***
   * 处理exception对应的code
   * @param httpResponse 待返回的 httpResponse实体
   * @param url 访问的链接
   * @param e 异常
   */
  private static void setHttpStatusAdnMessage(MyHttpResponse httpResponse,String url,Exception e){
    /***
     *
     *处理可能的三种超时情况和IO异常
     */
    if(e instanceof ConnectionPoolTimeoutException){
      log.error("[HttpClientUtil]从连接池获取连接超时,url:{},信息:",url,e);
      httpResponse.setStatusCode(MyHttpResponse.STATUS_CODE_UNKOWN);
      httpResponse.setStatusMessage(e.getMessage());
    }else if(e instanceof ConnectException){
      log.error("[HttpClientUtil]客户端和服务器建立连接超时,url:{},信息:",url,e);
      httpResponse.setStatusCode(MyHttpResponse.STATUS_CODE_UNKOWN);
      httpResponse.setStatusMessage(e.getMessage());
    }else if(e instanceof SocketTimeoutException){
      log.error("[HttpClientUtil]客户端和服务器连接成功,读取数据超时,url:{},信息:",url,e);
      httpResponse.setStatusCode(MyHttpResponse.STATUS_CODE_DON_NOT_CONNECT_AGAIN);
      httpResponse.setStatusMessage(e.getMessage());
    }else if (e instanceof IOException){
      // EntityUtils.consume(httpEntity)出错
      log.error("[HttpClientUtil]客户端和服务器连接成功,处理流数据出错,url:{},信息:",url,e);
      httpResponse.setStatusCode(MyHttpResponse.STATUS_CODE_DON_NOT_CONNECT_AGAIN);
      httpResponse.setStatusMessage(e.getMessage());
    }else{
      log.error("[HttpClientUtil]请求{},失败:{}", url, e);
      httpResponse.setStatusCode(MyHttpResponse.STATUS_CODE_DON_NOT_CONNECT_AGAIN);
      httpResponse.setStatusMessage(e.getMessage());
    }
  }

  public static MyHttpResponse httpPost(String url, String paramsJson,
                                        Map<String, String> headers, int time) {
    //post请求返回结果
    String dataResponse = "";
    MyHttpResponse myHttpResponse = new MyHttpResponse();

    HttpPost httpPost = new HttpPost(url);
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse httpResponse = null;
    try {

      if (StringUtils.isNotBlank(paramsJson)) {
        //解决中文乱码问题
        StringEntity entity = new StringEntity(paramsJson, Encoding_UTF_8);
        httpPost.setEntity(entity);
      }

      if (CollectionUtils.isEmpty(headers)) {
        httpPost.addHeader("Accept", Content_type_json);
        httpPost.addHeader("Content-Type", Content_type_json_utf8);
      } else {
        for (Entry<String, String> entry : headers.entrySet()) {
          httpPost.addHeader(entry.getKey(), entry.getValue());
        }
      }

      RequestConfig requestConfig = RequestConfig.custom()
              .setConnectionRequestTimeout(time)
              .setSocketTimeout(time)
              .setConnectTimeout(time)
              .build();
      httpPost.setConfig(requestConfig);

      httpResponse = httpClient.execute(httpPost);


      /**请求发送成功，并得到响应**/
      int statusCode = httpResponse.getStatusLine().getStatusCode();
      myHttpResponse.setStatusCode(statusCode);
      HttpEntity httpEntity = httpResponse.getEntity();
      if (statusCode == MyHttpResponse.STATUS_CODE_SUCCESS) {
        url = URLDecoder.decode(url, Encoding_UTF_8);
        /**读取服务器返回过来的json字符串数据**/
        dataResponse = EntityUtils.toString(httpEntity);
        myHttpResponse.setData(dataResponse);
        EntityUtils.consume(httpEntity);

      } else {
        dataResponse = EntityUtils.toString(httpEntity);
        log.error("[HttpClientUtil]请求{},失败:{},httpResponse:{},dataResponse:{}", url, httpResponse.getStatusLine().toString(),httpResponse,dataResponse);
        myHttpResponse.setStatusMessage(httpResponse.getStatusLine().toString());
        myHttpResponse.setData(dataResponse);
        EntityUtils.consume(httpEntity);

      }
    } catch (Exception e) {
      log.error("[HttpClientUtil]请求{},失败:{}", url, e);
      setHttpStatusAdnMessage(myHttpResponse,url,e);
    }finally {
      try {
        if (httpResponse != null) {
          httpResponse.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      httpPost.releaseConnection();
      try {
        httpClient.close();
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }
    return myHttpResponse;
  }

  public static MyHttpResponse httpGet(String url,Map<String, String> headers, int time) {
    //post请求返回结果
    String dataResponse = "";
    MyHttpResponse myHttpResponse = new MyHttpResponse();

    HttpGet httpGet = new HttpGet(url);
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse httpResponse = null;
    try {

      if (CollectionUtils.isEmpty(headers)) {
        httpGet.addHeader("Accept", Content_type_json);
        httpGet.addHeader("Content-Type", Content_type_json_utf8);
      } else {
        for (Entry<String, String> entry : headers.entrySet()) {
          httpGet.addHeader(entry.getKey(), entry.getValue());
        }
      }

      RequestConfig requestConfig = RequestConfig.custom()
              .setConnectionRequestTimeout(time)
              .setSocketTimeout(time)
              .setConnectTimeout(time)
              .build();
      httpGet.setConfig(requestConfig);

      httpResponse = httpClient.execute(httpGet);


      /**请求发送成功，并得到响应**/
      int statusCode = httpResponse.getStatusLine().getStatusCode();
      myHttpResponse.setStatusCode(statusCode);
      HttpEntity httpEntity = httpResponse.getEntity();
      if (statusCode == MyHttpResponse.STATUS_CODE_SUCCESS) {
        url = URLDecoder.decode(url, Encoding_UTF_8);
        /**读取服务器返回过来的json字符串数据**/
        dataResponse = EntityUtils.toString(httpEntity);
        myHttpResponse.setData(dataResponse);
        EntityUtils.consume(httpEntity);
      } else {
        dataResponse = EntityUtils.toString(httpEntity);
        log.error("[HttpClientUtil>httpGet]请求{},失败:{},httpResponse:{},dataResponse:{}", url, httpResponse.getStatusLine().toString(),httpResponse,dataResponse);
        myHttpResponse.setStatusMessage(httpResponse.getStatusLine().toString());
        EntityUtils.consume(httpEntity);

      }
    } catch (Exception e) {
      log.error("[HttpClientUtil>httpGet]请求{},失败:{}", url, e);
      setHttpStatusAdnMessage(myHttpResponse,url,e);
    }finally {
      try {
        if (httpResponse != null) {
          httpResponse.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      httpGet.releaseConnection();
      try {
        httpClient.close();
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }
    return myHttpResponse;
  }

  public static SSLContext createIgnoreVerifySSL()
      throws NoSuchAlgorithmException, KeyManagementException {
    SSLContext sc = SSLContext.getInstance("SSLv3");

    // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
    X509TrustManager trustManager = new X509TrustManager() {
      @Override
      public void checkClientTrusted(
          java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
          String paramString) throws CertificateException {
      }

      @Override
      public void checkServerTrusted(
          java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
          String paramString) throws CertificateException {
      }

      @Override
      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
      }
    };

    sc.init(null, new TrustManager[]{trustManager}, null);
    return sc;
  }

  /**
   * 获取Ip地址
   */
  public static String getIpAdrress(HttpServletRequest request) {
    String Xip = request.getHeader("X-Real-IP");
    String XFor = request.getHeader("X-Forwarded-For");

    if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
      //多次反向代理后会有多个ip值，第一个ip才是真实ip
      int index = XFor.indexOf(",");
      if (index != -1) {
        return XFor.substring(0, index);
      } else {
        return XFor;
      }
    }
    XFor = Xip;
    if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
      return XFor;
    }
    if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
      XFor = request.getHeader("Proxy-Client-IP");
    }
    if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
      XFor = request.getHeader("WL-Proxy-Client-IP");
    }
    if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
      XFor = request.getHeader("HTTP_CLIENT_IP");
    }
    if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
      XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
      XFor = request.getRemoteAddr();
    }

    return XFor.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : XFor;
  }
}
