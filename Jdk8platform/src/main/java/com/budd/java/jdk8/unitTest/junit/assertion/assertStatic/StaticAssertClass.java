package com.budd.java.jdk8.unitTest.junit.assertion.assertStatic;

import static com.budd.java.util.Print.print;

public class StaticAssertClass {
    static {
        print("开启全局断言功能");
        //所有类
//            ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        //指定某个类
        ClassLoader.getSystemClassLoader().setClassAssertionStatus("com.budd.java.jdk8.unitTest.junit.assertion.assertStatic.StaticAssertClassLoaded", true);
    }

    public void test() {
        new StaticAssertClassLoaded().go();
    }
}