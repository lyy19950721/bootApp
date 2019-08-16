package com.mipo.common.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    private static ThreadLocal<LogData> LOG_DATA = new ThreadLocal<LogData>(){
        @Override
        protected LogData initialValue() {
            return new LogData();
        }
    };

    @Pointcut("execution(* com.mipo.controller..*(..))")
    public void logMessage() {

    }

    @Before("logMessage()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        LogData logData = LOG_DATA.get();
        logData.setTime(System.currentTimeMillis());
        logData.setUrl(request.getRequestURL().toString());
        logData.setIp(request.getRemoteAddr());
        logData.setMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logData.setArgs(Arrays.toString(joinPoint.getArgs()));

        log.info("[接口路径]：{}, [IP]: {}, [类方法]: {}, [请求参数]: {} " ,
                logData.getUrl(), logData.getIp(), logData.getMethod(), logData.getArgs());
    }

    @AfterReturning(returning = "ret" , pointcut = "logMessage()")
    public void doAfterReturning(Object ret){

        LogData logData = LOG_DATA.get();
        logData.setRet(JSONObject.toJSONString(ret));
        logData.setTime(System.currentTimeMillis() - logData.getTime());

        log.info("[接口路径]：{}, [IP]: {}, [类方法]: {}, [请求参数]: {} , [返回结果]: {}, [耗时]：{}" ,
                logData.getUrl(), logData.getIp(), logData.getMethod(), logData.getArgs(), logData.getRet(), logData.getTime());
    }

    @Data
    static class LogData {

        private String url;

        private String operator;

        private String ip;

        private String method;

        private String args;

        private String ret;

        private Long time;
    }
}
