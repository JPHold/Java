package com.budd.java.jdkBasic.container.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static com.budd.java.util.Print.*;

/**
 * @author budd
 * @desc 数组入门研究
 * @since 2021/2/2 20:47
 **/
public class HelloArrayTest {

    /**
     * 2021年2月2日 21:25:38
     * 测试默认值
     */
    @Test
    public void testDefaultValue() {
        int[] a = new int[5];
        print(Arrays.toString(a));

        String[] s = new String[5];
        print(Arrays.toString(s));
    }

    /**
     * 2021年2月2日 20:54:21
     * 创建二维数组
     */
    @Test
    public void testCreate2Array() {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        print(Arrays.deepToString(a));
    }

    /**
     * 2021年2月2日 20:55:22
     * 创建三维数组
     */
    @Test
    public void testCreate3Array() {
        int[][][] a = {{{0, 0, 0, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 0, 0, 0}}};
        print(Arrays.deepToString(a));
    }

    /**
     * 2021年2月2日 22:52:28
     * 测试不规则数组
     */
    @Test
    public void testRandomArray() {
        int val = 1;
        Random rand = new Random(47);
        int[][][] a = new int[rand.nextInt(7)][][];//第一维
        for (int i = 0; i < a.length; i++) {
            a[i] = new int[rand.nextInt(5)][];//第二维
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = new int[rand.nextInt(5)];//第三维
                for (int k = 0; k < a[i][j].length; k++) {//往三维数组填充值
                    a[i][j][k] = val++;
                }
            }
        }
        print(Arrays.deepToString(a));
    }
}
