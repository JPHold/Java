package com.budd.java.jdk8.lambda;

/**
 * @author budd
 * @desc 测试没有抽象方法的函数接口
 * @since 2020/4/19 10:30
 **/
@FunctionalInterface
public interface NoAbstractMethodInterface {

    default void defaultExample() {
    }

    void correctExample();

    boolean equals(Object obj);

    String toString();

    //Object clone();//理应不会视为抽象方法

    int hashCode();

    //void finalize();//理应不会视为抽象方法
}
