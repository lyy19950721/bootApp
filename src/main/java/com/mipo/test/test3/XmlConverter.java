package com.mipo.test.test3;

/**
 * @Classname XmlConverter
 * @Description TODO
 * @Date 2020/10/21 14:03
 * @Created by li.yy
 */
public class XmlConverter implements Converter {

    @Override
    public <A extends BaseRequest<?>> String bean2String(A request) {
        // 这里省略 XmlUtils 具体实现...
        return null;
    }

    @Override
    public <B extends BaseResponse> B string2Bean(String responStr, Class<B> clazz) {
        return null;
    }
}
