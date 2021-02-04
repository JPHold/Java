package com.budd.java.jdk8.container.array;

import org.junit.Test;

import java.util.Arrays;

import static com.budd.java.util.Print.print;

/**
 * @author budd
 * @desc 数组入门研究
 * @since 2021/2/4 21:16
 **/
public class HelloArrayTest {

    /**
     * 2021年2月4日 21:08:06
     * 测试Arrays工具集
     */
    int val = 0;

    @Test
    public void testArraysUtil() {
        /*
         * setAll
         */
        Integer[] a = new Integer[3];
        Arrays.setAll(a, index -> val++);
        print(Arrays.toString(a));
    }

}
