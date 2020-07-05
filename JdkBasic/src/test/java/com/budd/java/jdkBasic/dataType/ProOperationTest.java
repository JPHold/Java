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

}
