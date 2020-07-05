package com.budd.java.jdk7.dataType;

import org.junit.Test;

import static com.budd.java.util.Print.*;

/**
 * @author budd
 * @desc 运算符入门研究
 * @since 2020/4/13 22:47
 **/
public class HelloOperationTest {

    /**
     * @author budd
     * @desc 使用下划线“_”作为大数字的视觉分隔符，java7及以上才支持
     * @since 2020年4月13日 22:50:10
     */
    @Test
    public void testUnderlineSplit() {
        int i = 1_0000_0000;//一亿
        printf("int类型：%s", i);

        double d = 341_435_936.445_667;
        printf("double类型：%s", d);

        int binary = 0b0010_1111_1010_1111_1010_1111_1010_1111;
        printf("二进制类型：%s", Integer.toBinaryString(binary));
        //%n是忽略平台差异的换行符(只针对System.out.printf() 或 System.out.format()有效，System.out.println()是输出%n)
        System.out.printf("十六进制输出：%x%n", binary);

        long hex = 0x7f_e9_b7_aa;
        printf("十六进制输出：%x%n", hex);

        /**
         * 以下是违反使用规则的用法
         */
        //辅助字面量：F、D 和 L的前面不能出现。报错：Illegal underscore
        /*float errorFloat = 123_F;
        double errorDouble = 123_D;
        long errorLong = 123_L;
        */

        //二进制前导 b 和 十六进制 x 前后不能出现。报错：Illegal underscore
        /*int errorBinary = 0B_0011;
        int errorHex = 0X_7B;*/
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
