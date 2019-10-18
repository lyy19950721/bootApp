package com.mipo.common.enums;

public enum Admin {

    login("",1),
    loginOut("", 2);
    private String desc;
    private int type;

    Admin(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static Admin getValue(int type) {
        for (Admin mccEnum : Admin.values()) {
            if (mccEnum.getType() == type) {
                return mccEnum;
            }
        }
        return null;
    }
}
