package com.mipo.test.test3;

/**
 * @Classname BaseResponse
 * @Description TODO
 * @Date 2020/10/21 10:13
 * @Created by li.yy
 */
public abstract class BaseResponse<T> {

    private Integer code;
    private T data;
    public abstract String getType();
}
