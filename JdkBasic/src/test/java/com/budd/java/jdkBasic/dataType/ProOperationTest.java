package com.budd.java.jdkBasic.dataType;

import org.junit.Test;

import static com.budd.java.util.Print.*;

/**
 * @author budd
 * @desc 运算符进阶研究
 * @since 2020/6/29 22:33
 **/
public class ProOperationTest {

    /**
     * 位运算
     * 2020年7月5日 19:49:00
     */
    @Test
    public void bitOperation() {

        int i1 = 1;//0001
        int i2 = 0;//0000

        printf("%n---------------基础位运算");

        //与运算
        int andBit = i1 & i1;//0001 & 0001 = 0001
        int andBit1 = i1 & i2;//0001 & 0000 = 0000
        printf("与运算，两个为1结果：%s，一个为0结果：%s", andBit, andBit1);

        //或运算
        int orBit = i1 | i2;//0001 & 0000 = 0001
        printf("或运算，一个为0结果：%s", orBit);

        //取反运算
        int faceBit = ~i1;
        printf("取反运算，结果：%s", faceBit);

        //异或运算
        int xorBit = i1 ^ i1;
        int xorBit1 = i1 ^ i2;
        printf("异或运算，两个为1结果：%s，一个为0结果：%s", xorBit, xorBit1);

        //也支持组合使用
        i1 &= 0;
        printf("组合\"=\"使用：%s", i1);

        /**
         * 布尔值的运算
         */
        printf("%n---------------布尔值的位运算");

        boolean blAndBit = true & false;
        printf("布尔类型的与运算：%s", blAndBit);

        printf("---------------");

        boolean blOrBit = true | false;
        printf("布尔类型的或运算：%s", blOrBit);

        printf("---------------");

        boolean blXorBit = true ^ false;
        printf("布尔类型的异或运算：%s", blXorBit);

    }

    /**
     * 移位运算
     */
    @Test
    public void moveBitOperation() {

        //负数的二进制是其正数的补码
        //Int站4字节，32位
        printf("Int移位");
        int i = -1;
        int i1 = i >>> 10;
        printf("无符号右移前：%s%n无符号右移后：%s%n", Integer.toBinaryString(i), Integer.toBinaryString(i1));

        /*-------------------*/

        //Int站8字节，64位
        printf("Long移位");
        long l = -1;
        long l1 = l >>> 10;
        printf("无符号右移前：%s%n无符号右移后：%s%n", Long.toBinaryString(l), Long.toBinaryString(l1));

        /*-------------------*/

        /**
         * 对于char、byte、short的移位，先转为int，再移位
         *
         * 其中byte和short的移位
         * 这有两种情况：
         * 1、>>> =
         *   运算结果重新赋值给原变量，会被截断，导致最终结果为-1
         *
         * 2、= >>>
         *   运算结果不是赋值给原变量，而是赋值给int，这不会被截断，结果正确
         */
        printf("Short移位");
        short s = -1;
        printf("(使用源类型(byte)接收)无符号右移前：%s", Integer.toBinaryString(s));
        s >>>= 10;
        printf("(使用源类型(byte)接收)无符号右移后：%s", Integer.toBinaryString(s));

        s = -1;
        int s1 = s >>> 10;
        printf("(使用int接收)无符号右移后：%s", Integer.toBinaryString(s1));

        /*-------------------*/

        printf("%nByte移位");
        byte b = -1;
        printf("(使用源类型(byte)接收)无符号右移前：%s", Integer.toBinaryString(b));
        b >>>= 10;
        printf("(使用源类型(byte)接收)无符号右移后：%s", Integer.toBinaryString(b));

        b = -1;
        int b1 = b >>> 10;
        printf("(使用int接收)无符号右移后：%s%n", Integer.toBinaryString(b1));
    }

}
