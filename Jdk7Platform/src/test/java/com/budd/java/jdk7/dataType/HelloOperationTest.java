package com.budd.java.jdk7.dataType;

import org.junit.Test;

import static com.budd.java.util.Print.printf;

/**
 * @author budd
 * @desc 运算符入门研究
 * @since 2020/4/13 22:47
 **/
public class HelloOperationTest {

    /**
     * @author budd
     * @desc 使用“_”作为大数字的视觉分隔符，java7及以上才支持
     * @since 2020年4月13日 22:50:10
     */
    @Test
    public void testBigNumberVisionSplit() {
        int bigNumberVisionSplit = 1_0000_0000;//一亿
    }

    /**
     * Java 7 引入了二进制的字面值常量，由前导 0b 或 0B 表示，它可以初始化所有的整数类型。
     * 测试二进制字面量
     * 2020年7月4日 16:09:32
     */
    @Test
    public void testBinaryLiteral() {

        byte byteTypeBinary = (byte) 0b00110101;
        printf("byte类型的二进制：%s,对应值", Integer.toBinaryString(byteTypeBinary), byteTypeBinary);

        short shortTypeBinary = (short) 0B0010111110101111;
        printf("short类型的二进制：%s,对应值", Integer.toBinaryString(shortTypeBinary), shortTypeBinary);

        int intTypeBinary = 0b00101111101011111010111110101111;
        printf("int类型的二进制：%s,对应值", Integer.toBinaryString(intTypeBinary), intTypeBinary);

        long longTypeBinary = 0b00101111101011111010111110101111;
        printf("long类型的二进制：%s,对应值", Long.toBinaryString(longTypeBinary), longTypeBinary);

    }
}
