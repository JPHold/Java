package com.budd.java.jdkBasic.trait;

/**
 * **
 * *******    ******* *
 * ********	  *
 * *      *      *
 * ********      *
 * /**
 * 继承中的加载顺序
 *
 * @author HJP
 * @date 2018年11月4日22:08:02
 * @Description 父类
 */
public class ParentClass {

    /**
     * 静态变量
     */
    public static int a = 1;
    /**
     * 非静态变量
     */
    public int b = 2;

    static {
        System.out.println(String.format("ParentClass-静态代码块,静态变量：%s", a));
    }

    {
        System.out.println(String.format("ParentClass-非静态代码块,非静态变量：%s"
                , b));
    }

    public ParentClass() {
        System.out.println("ParentClass-构造方法");
    }

}
