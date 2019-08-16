package com.mipo.common.constant;

public enum UserStatusEnum {

    DEFAULT("默认", 0),
    NORMAL("正常", 1),
    DEL("删除", 10);

    UserStatusEnum(String desc, Integer value) {
        this.desc = desc;
        this.value = value;
    }

    private String desc;
    private Integer value;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
