package com.budd.java.jdkBasic.typeSafeEnum;

public enum Season {
    SPRING(1,"春天"), SUMMER(2,"夏天"), AUTUMN(3,"秋天"), WINTER(4,"冬天");

    private int code;
    private String desc;

    private Season(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}


