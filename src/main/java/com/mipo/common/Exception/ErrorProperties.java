package com.mipo.common.Exception;

import com.mipo.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Slf4j
public class ErrorProperties {

    private static Properties props;

    static {
        loadProps();
    }

    synchronized static private void loadProps(){
        log.info("加载error.properties文件[开始]......");
        props = new Properties();
        InputStream in = null;
        try {
            in = ErrorProperties.class.getClassLoader().getResourceAsStream("error.properties");
            props.load(in);
        } catch (Exception e) {
            log.error("加载error.properties出错......");
            e.printStackTrace();
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("加载error.properties文件[完成].......");
    }

    public static String getProperty(String key) {
        if(null == props) {
            loadProps();
        }

        String value = props.getProperty(key);

//        String[] split = value.split(",");
//        if(ContextUtil.getCurrentContext().getLanguageType().equalsIgnoreCase("EN")){
//            return split[0];
//        }
//        return split[1];

        if(StringUtils.isEmpty(value)) {
            value = "未知error";
        }
        return value;
    }
}
