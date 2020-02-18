package com.budd.java.jdkBasic.classes.innerClass;

import org.junit.Test;

/**
 * 内部类入门研究
 *
 * @author budd
 * @since 2020年2月18日 22:52:31
 **/
public class HelloInnerClassTest {
    /**
     * @return
     * @author HJP
     * @date 2018年1月6日 %上午11:56:08
     * @Description JDK1.8之前，局部类操作局部变量， 需要显示添加final
     */
    @Test
    public void testLocalVarFinal() {
        //Variable 'i' is accessed from within inner class, needs to be declared final
        /*int i = 0;
        Runnable rn = new Runnable() {
            public void run() {
                System.out.println(i);
            }
        };*/
    }
}
