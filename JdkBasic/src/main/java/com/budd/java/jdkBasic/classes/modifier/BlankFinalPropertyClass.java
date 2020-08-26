package com.budd.java.jdkBasic.classes.modifier;

import static com.budd.java.util.Print.*;

/**
 * @author budd
 * @desc 空白常量属性类
 * @since 2020/8/25 16:24
 **/
public class BlankFinalPropertyClass {
    private final int i = 0;
    private final int j;
    private final int k;
    private final String s;
    private final String t;

    public BlankFinalPropertyClass(int k) {
        j = i;
        s = "str";
        this.k = k;
        printf("i=%s,j=%s,k=%s,s=%s,t=%s", i, j, k, s, t);
    }

    {
        t = "str";
    }

}
