package com.budd.java.jdkBasic.trait;

/**
 * **
 * *******    ******* *
 * ********	  *
 * *      *      *
 * ********      *
 * /** 继承中的加载顺序
 *
 * @author HJP
 * @date 2018年11月4日22:08:11
 * @Description 子类
 */
public class ChildClass extends ParentClass {

    /**
     * 静态变量
     */
    public static int a = 3;
    /**
     * 非静态变量
     */
    public int b = 4;

    static {
        System.out.println(String.format("ChildClass-静态代码块,静态变量：%s", a));
    }

    {
        System.out.println(String.format("ChildClass-非静态代码块,非静态变量：%s"
                , b));
    }

    public ChildClass() {
        System.out.println("ChildClass-构造方法");
    }

}
