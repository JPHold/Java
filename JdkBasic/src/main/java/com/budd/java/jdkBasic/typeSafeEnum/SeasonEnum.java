package com.budd.java.jdkBasic.typeSafeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SeasonEnum {
    SPRING(1, "春天"), SUMMER(2, "夏天"), AUTUMN(3, "秋天"), WINTER(4, "冬天");

    private int code;
    private String desc;

}


