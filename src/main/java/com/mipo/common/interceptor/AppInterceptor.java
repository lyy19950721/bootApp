package com.mipo.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.mipo.common.constant.RedisConstant;
import com.mipo.common.constant.TokenBO;
import com.mipo.common.util.ContextUtil;
import com.mipo.common.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class AppInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With, token, languageType");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");

        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }
        ContextUtil.getCurrentContext().init(request, handler);
        //方便调试,暂时写死
        String key = String.format(RedisConstant.SMART_FINANCE_MANAGER_TOKEN, "111@qq.com");
        String tokenKey = RedisUtil.get(key, String.class);
        if(StringUtils.isNotBlank(tokenKey)){
            TokenBO tokenBO = RedisUtil.get(tokenKey, TokenBO.class);
            ContextUtil.getCurrentContext().setPartnerId(tokenBO.getPartnerId());
            ContextUtil.getCurrentContext().setTokenBOStr(JSONObject.toJSONString(tokenBO));
            ContextUtil.getCurrentContext().setLanguageType("EN");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
