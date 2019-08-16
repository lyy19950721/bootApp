package com.mipo.common.http;

import lombok.Data;


@Data
public class MyHttpResponse {

  public static final int STATUS_CODE_SUCCESS = 200;
  /****
   *
   * 网络超时，包括连接池获取连接超时，连接服务端超时
   *
   */
  public static final int STATUS_CODE_UNKOWN = 666;

  /***
   *
   * 连接成功，读取数据时间超过了httpclient设置的时间,属于我们服务器主动断开
   */
  public static final int STATUS_CODE_DON_NOT_CONNECT_AGAIN = 777;


  public static final int STATUS_CODE_COMMON = 9999;
  public static final String STATUS_FAIL_COMMON = "httpServletRequest error";

  private int statusCode;
  private String statusMessage;
  private String data;
}