package com.budd.java.jdkBasic.trait;

import org.junit.Test;

/**
 * 继承特性测试
 *
 * @author budd
 * @since 2019/12/16 22:57
 **/
public class HelloInheritedTest {

    /**
     * 继承中,父子加载顺序
     */
    @Test
    public void testLoadOrder() {
        //加载顺序
        System.out.println("\n继承中,父子等加载顺序");
        new ChildClass();
        System.out.println("---------------------------");
        new ChildClass();
    }

}
