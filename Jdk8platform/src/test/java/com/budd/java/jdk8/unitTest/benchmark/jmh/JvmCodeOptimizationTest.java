package com.budd.java.jdk8.unitTest.benchmark.jmh;

/**
 * @author budd
 * @desc 测试JVM是否优化掉没有返回结果的方法：查看耗时是否与
 * @since 2020/11/8 16:58
 **/
public class JvmCodeOptimizationTest {

    public static void main(String[] args) {

        long start = System.nanoTime();
        test();
        long end = System.nanoTime();
        System.out.println(end - start);
    }
    private static double x = Math.PI;
    private static double x2 = Math.PI * 2;
    public static void test(){
        Math.log(x);
    }
}
