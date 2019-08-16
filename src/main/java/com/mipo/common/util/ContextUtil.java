package com.mipo.common.util;


import lombok.Data;

import javax.servlet.http.HttpServletRequest;


@Data
public class ContextUtil {

  protected static final ThreadLocal<ContextUtil> THREAD_LOCAL = new ThreadLocal<ContextUtil>() {
    @Override
    protected ContextUtil initialValue() {
      return new ContextUtil();
    }
  };

  private Object handler;

  protected HttpServletRequest httpServletRequest;

  private String requestURI;

  private String urlParamStr;

  private String languageType;

  private Long partnerId;

  private String tokenBOStr;

  protected ContextUtil() {

  }


  /**
   * 获取当前Context
   */
  public static ContextUtil getCurrentContext() {
    return THREAD_LOCAL.get();
  }


  /**
   * 在Interceptor中使用，初始化参数
   */
  public void init(HttpServletRequest request, Object handler) {

    this.httpServletRequest = request;
    this.handler = handler;
    this.requestURI = request.getRequestURI();
    this.urlParamStr = request.getQueryString();
  }

}
