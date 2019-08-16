package com.mipo.common.export;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class DateSQLFieldTransform implements SQLFieldTransform{

    @Override
    public String transform(Object obj, boolean bool) {
        String dateStr = "";
        if(null != obj){
            dateStr = DateUtil.format((Date) obj, "yyyy-MM-dd HH:mm:ss");
        }
        if(StringUtils.isNotBlank(dateStr)){
            return dateStr;
        }
        return "";
    }
}
