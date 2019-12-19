package com.budd.java.jdkBasic.trait.inherited;

/**
 * @author HJP
 * 2017年12月31日15:26:14
 * 基类：方法1调用方法2。
 * 派生类：重载方法2
 */
public class RelationMethodBasicClass {

    public void method1() {
        System.out.println("开始调用method2");
        method2();
    }

    public void method2() {
        System.out.println("基类调用");
    }
}
