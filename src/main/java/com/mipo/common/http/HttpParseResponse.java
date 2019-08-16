package com.mipo.common.http;

import com.mipo.common.util.JsonUtil;
import lombok.Data;

@Data
public class HttpParseResponse<T> {

  public static final int SUCCESS = 200;
  public static final int PARSE_ERROR = 999;
  public static final String PARSE_ERROR_TITLE = "PARSE_ERROR";

  private int status = SUCCESS;

  private String message;

  private T data;

  public static <T> HttpParseResponse<T> parseHttpResponse(MyHttpResponse myHttpResponse,
                                                           T target) {

    HttpParseResponse<T> httpParseResponse = new HttpParseResponse<T>();

    int statusCode = myHttpResponse.getStatusCode();
    String message = myHttpResponse.getStatusMessage();

    try {
      httpParseResponse.setData((T) JsonUtil.fromJson(myHttpResponse.getData(), target.getClass()));
      httpParseResponse.setStatus(statusCode);
      httpParseResponse.setMessage(message);

    } catch (Exception e) {
      e.printStackTrace();
      httpParseResponse.setStatus(HttpParseResponse.PARSE_ERROR);
      httpParseResponse.setMessage(HttpParseResponse.PARSE_ERROR_TITLE);
    }

    return httpParseResponse;

  }

}
