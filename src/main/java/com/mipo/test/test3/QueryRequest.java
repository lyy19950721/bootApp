package com.mipo.test.test3;

import lombok.Data;

/**
 * @Classname QueryRequest
 * @Description TODO
 * @Date 2020/10/21 10:03
 * @Created by li.yy
 */
@Data
public class QueryRequest extends BaseRequest<QueryResponse> {

    private String outTradeNo;

    @Override
    public String getType() {
        return PayEnums.PAY_QUERY.getType();
    }

    @Override
    public String getUrl() {
        return "https://127.0.0.1:1234/openapi/query";
    }

    @Override
    public Class<QueryResponse> getResponseType() {
        return QueryResponse.class;
    }
}
