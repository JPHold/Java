package com.budd.java.jdkBasic.string;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

/**
 * 字符串入门研究
 *
 * @author budd
 * @since 2019/11/3 17:00
 **/
public class HelloStringTest {


    public class TipNullString {
        private String s;

        TipNullString(@NotNull String test) {
            s = test;
        }
    }

    @Test
    public void testTipNull() {
        String nullStr = null;
        String s1 = new String(nullStr);
        TipNullString testString = new TipNullString(nullStr);
        String intern = nullStr.intern();
    }

}
