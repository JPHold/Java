package com.budd.java.jdk7.exception.trywithresources;

/**
 * @author budd
 * @desc 实现AutoCloseable的类，构造器抛出错误
 * @since 2020年10月26日 22:41:45
 **/
public class AutoCloseableExceptionImpl extends AutoCloseableImpl {

    public AutoCloseableExceptionImpl() throws AutoCloseableException {
        throw new AutoCloseableException();
    }
}