package com.mipo.common.result;

import lombok.Data;

@Data
public class ResponseResult {

    private String code = "200";

    private String msg = "SUCCESS";

    private Object data;
}
