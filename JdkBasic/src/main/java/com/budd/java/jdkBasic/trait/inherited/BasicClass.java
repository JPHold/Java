package com.budd.java.jdkBasic.trait.inherited;

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
 * @Description 基类类
 */
public class BasicClass {

    /**
     * 静态变量
     */
    public static int a = 1;
    /**
     * 非静态变量
     */
    public int b = 2;

    static {
        System.out.println(String.format("基类-静态代码块,静态变量：%s", a));
    }

    {
        System.out.println(String.format("基类-非静态代码块,非静态变量：%s"
                , b));
    }

    public BasicClass() {
        System.out.println("基类-构造方法");
    }

}
