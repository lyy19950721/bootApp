package com.mipo.common.export;

public class BankValiditySQLFieldTransform implements SQLFieldTransform{

    @Override
    public String transform(Object obj, boolean bool) {
        String str = "";
        if(null == obj){
            return str;
        }
        Integer value = (Integer) obj;
        if(value == 0){
            str = bool ? "未验证" : "Retail store";
        }else if(value == 1){
            str = bool ? "有效" : "Bank card payment";
        }else if(value == 2){
            str = bool ? "无效" : "Bill VA";
        }
        return str;
    }
}
