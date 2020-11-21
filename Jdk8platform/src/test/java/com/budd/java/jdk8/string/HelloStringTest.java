package com.budd.java.jdk8.string;

import static com.budd.java.util.Print.*;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author budd
 * @desc 字符串的入门研究
 * @since 2020/11/21 17:29
 **/
public class HelloStringTest {

    /**
     * 测试java的唯一重载运算符"+"
     *
     * @date 2020年11月21日 17:30:08
     */
    @Test
    public void testPlusOverloadOperators() {
        String result = IntStream.range(0, 5)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
        print(result);
    }
}
