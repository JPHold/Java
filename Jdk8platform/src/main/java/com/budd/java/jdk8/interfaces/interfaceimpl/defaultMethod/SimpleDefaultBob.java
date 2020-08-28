package com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod;


/**
 * 编译报错，因为多个接口都有一模一样的默认方法，需要重写方法
 * com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod.ErrorBob inherits unrelated defaults for bob()
 * from types com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod.Bob1
 * and com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod.Bob2
 **/
/*class ErrorBob implements Bob1,Bob2{

}*/

/**
 * @author budd
 * @desc 实现多个相同默认方法的类
 * 需要重写方法，并可调用默认实现
 * @since 2020/8/28 10:13
 **/
public class SimpleDefaultBob implements Bob1, Bob2 {
    @Override
    public void bob() {
        Bob1.super.bob();
        Bob2.super.bob();
    }
}
