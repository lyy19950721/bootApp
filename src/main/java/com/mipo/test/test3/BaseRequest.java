package com.mipo.test.test3;

import lombok.Data;

/**
 * @Classname BaseRequest
 * @Description TODO
 * @Date 2020/10/21 10:02
 * @Created by li.yy
 */
@Data
public abstract class BaseRequest<T extends BaseResponse>  {

    private String type;
    private String mchId;
    private String url;
    // 不同业务具有不同的请求地址
    public abstract String getUrl();
    public abstract String getType();

    public abstract Class<T> getResponseType();
}
