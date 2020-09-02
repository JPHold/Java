package com.budd.java.jdk8.interfaces.interfaceimpl.staticMethod;
import static com.budd.java.util.Print.*;
/**
 * @author budd
 * @desc 接口的静态方法
 * @since 2020/4/20 23:02
 **/
public interface StaticMethodInterface {
     static void staticMethod() {
        print("Java8支持在接口中定义静态方法");
    }
}
