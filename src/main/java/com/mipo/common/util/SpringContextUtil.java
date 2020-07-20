package com.mipo.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        SpringContextUtil.context = context;
    }

    public static ApplicationContext getContext(){
        return context;
    }

    /**
     * 获取httpResponse对象
     * @return
     */
    public static HttpServletResponse getHttpServletReponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取对象
     *
     * @param name
     * @return Object
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return context.getBean(name);
    }
}
