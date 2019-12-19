package com.budd.java.jdkBasic.trait.inherited;

import org.junit.Test;

/**
 * 继承特性测试
 *
 * @author budd
 * @since 2019/12/16 22:57
 **/
public class HelloInheritedTest {

    /**
     * 继承中,基类和派生类加载顺序
     */
    @Test
    public void testLoadOrder() {
        //加载顺序
        System.out.println("\n继承中, 派生类和子类等加载顺序");
        new ChildClass();
        System.out.println("---------------------------");
        new ChildClass();
    }

    /**
     * 测试继承中,final方法重写问题
     */
    @Test
    public void testFinalMethod() {
        FinalMethodBasicClass finalMethodBasicClass = new FinalMethodChildClass();
        //fmbc.finalSaeMethod();
    }
}
