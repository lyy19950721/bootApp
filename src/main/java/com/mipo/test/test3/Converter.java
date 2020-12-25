package com.mipo.test.test3;

/**
 * @Classname Converter
 * @Description TODO
 * @Date 2020/10/21 11:49
 * @Created by li.yy
 */
public interface Converter {

    <A extends BaseRequest<?>> String bean2String(A request);

    <B extends BaseResponse> B string2Bean(String responStr, Class<B> clazz);

}
