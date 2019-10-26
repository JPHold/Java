package com.budd.java.jdkBasic.typeSafeEnum;

/**
 * 枚举的使用规范
 *
 * @author budd
 * @since 2019/10/26 12:06
 **/
public class ProEnumTest {
    public static void main(String[] args) {

        //枚举的正确姿势
        //1、使用接口组织枚举
        //2、实现接口定义的接口方法
        Food.Coffee.BLACK.desc();

        /**
         * 枚举的特点(http://www.hollischuang.com/archives/197)
         *  通过jad xxxEnun生成的xxxEnum.jad,用文本打开即可：
         *  1、无法被继承
         *  2、线程安全
         *   当一个Java类第一次被真正使用到的时候,静态资源被初始化、Java类的加载和初始化过程都是线程安全的
         *   当类实现序列化时,通过readObject()返回一个新对象,造成单例失效,虽然可以通过readResolve()避免,但麻烦
         *   所以JVM针对枚举的序列化,禁止掉readObject()、readResolve()等方法。
         *   同时枚举的序列化是将name()返回。
         *   反序列化是将values()返回,这个values方法是将存放枚举变量的数组返回,这些枚举变量和装他们的数组都是静态和常量的,在编译类时就已维护好了
         */
        /**
         * 枚举的使用场景
         *  1、单例(Effective Java作者推荐)
         */
    }

}
