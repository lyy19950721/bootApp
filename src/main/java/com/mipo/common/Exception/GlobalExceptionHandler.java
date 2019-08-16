package com.mipo.common.Exception;

import com.mipo.common.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value=Exception.class)
    public ResponseResult exceptionHandler(HttpServletRequest request, Exception e) {

        String url = request.getRequestURL().toString();
        log.error("[请求接口]:{}出错, 错误信息如下{}", url, e.getMessage());
        e.printStackTrace();

        ResponseResult responseResult = new ResponseResult();

        if(e instanceof BindException) {
            BindException ex = (BindException)e;
            FieldError fieldError = ex.getFieldErrors().get(0);
            String field = fieldError.getField();
            String code = fieldError.getDefaultMessage();

            setResponseResult(responseResult, field, code);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            String code = "ERROR_REQUEST";
            setResponseResult(responseResult, null, code);
        } else if(e instanceof ServiceException){
            ServiceException ex = (ServiceException) e;
            String code = ex.getCode();

            setResponseResult(responseResult, null, code);
        } else if(e instanceof MethodArgumentNotValidException){

            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = ex.getBindingResult();
            FieldError error = bindingResult.getFieldErrors().get(0);
            String field = error.getField();
            String code = error.getDefaultMessage();

            setResponseResult(responseResult, field, code);
        } else {
            String code = "INNER_ERROR";
            setResponseResult(responseResult, null, code);
        }

        return responseResult;
    }

    private void setResponseResult(ResponseResult responseResult, String field, String code) {
        String message = ErrorProperties.getProperty(code);
        responseResult.setCode(code);
        if(null != field){
            message = "{" + field + "}" + message;
        }
        responseResult.setMsg(message);
    }
}