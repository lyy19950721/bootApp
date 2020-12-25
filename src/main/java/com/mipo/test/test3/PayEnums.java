package com.mipo.test.test3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Classname PayEnums
 * @Description TODO
 * @Date 2020/10/21 10:06
 * @Created by li.yy
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum PayEnums {

    PAY_QUERY("query", "订单查询"),
    PAY_SEND("send", "订单创建");

    String type;

    String desc;

//    public  Response buildResponse() {
//        return Response.success(this.getCode(), this.getBizCode(), this.getBizMessage(), null);
//    }

    public static String getPayResultDesc(String type) {
        PayEnums[] values = PayEnums.values();
        for (PayEnums value : values) {
            if (value.getType() == type) {
                return value.getDesc();
            }
        }
        return PAY_QUERY.getDesc();
    }
}
