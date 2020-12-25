package com.mipo.test.test3;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname PayClient
 * @Description TODO
 * @Date 2020/10/21 10:15
 * @Created by li.yy
 */
@Service
public class PayClient {

    private final Lock lock = new ReentrantLock();

    public  <T extends BaseResponse> T execute(BaseRequest<T> request, Converter converter) {
//        String requestStr = converter.bean2String(request);
//        String response = "假设这是三方系统返回内容";
//        T responseBean = converter.string2Bean(response, request.getResponseType());
        // 1、签名（如果需要）
        // 2、RequestBean 转 JSON
        // 3、发送请求
        // 4、验签（如果需要）
        // 5、JSON 转 ResponBean


        T responseBean = null;
        lock.lock();
        try {
            if(lock.tryLock()) {

            }
            String response = "假设是第三方返回的body体";
            responseBean = JSON.parseObject(response, request.getResponseType());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return responseBean;
    }

}
