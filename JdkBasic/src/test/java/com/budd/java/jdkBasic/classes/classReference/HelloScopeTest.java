package com.budd.java.jdkBasic.classes.classReference;

import org.junit.Test;

/**
 * @author budd
 * @desc 作用域入门研究
 * @since 2020/6/27 17:29
 **/
public class HelloScopeTest {

    /**
     * 测试基础类型的作用域
     * 1、不同作用域下，外层作用域不能访问内层作用域的变量，反之可以
     * 2、即使是不同作用域，变量名也不能重复
     */
    @Test
    public void testBasicType(){
        {
            int x = 12;
            // 仅 x 变量可用
            {
                int y = 96;
                // x 和 y 变量皆可用
            }
            // 仅 x 变量可用
            // 变量 y 不在作用域内
        }

        /*------------------------------*/

        {
            int x = 12;
            {
//                int x = 96; // Variable 'x' is already defined in the scope
            }
        }
    }

    /**
     * 测试对象的作用域
     * 创建s的引用，在作用域终点处就结束了，但创建的对象还在堆内存中。
     * 由垃圾收集器根据不可达算法，自动清理
     */
    @Test
    public void testClassType(){
        {
            String s = new String("a string");
        }
        // 作用域终点
    }


}
