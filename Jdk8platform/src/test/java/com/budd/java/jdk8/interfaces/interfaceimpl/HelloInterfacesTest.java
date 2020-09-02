package com.budd.java.jdk8.interfaces.interfaceimpl;

import com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod.ExtendMoreImlBob;
import com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod.SimpleDefaultBob;
import com.budd.java.jdk8.interfaces.interfaceimpl.staticMethod.StaticMethodInterface;
import org.junit.Test;

/**
 * @author budd
 * @desc 接口入门研究
 * @since 2020/4/20 23:02
 **/
public class HelloInterfacesTest {

    /**
     * @return void
     * @Author budd
     * @Description 静态方法
     * @Date 2020/8/28 10:47
     * @Param []
     **/
    @Test
    public void testStaticMethod() {
        StaticMethodInterface.staticMethod();
    }

    /**
     * @return void
     * @Author budd
     * @Description 测试实现多个相同默认方法
     * @Date 2020/8/28 11:10
     * @Param []
     **/
    @Test
    public void testSimpleDefaultMethod() {
        new SimpleDefaultBob();
    }

    /**
     * @Author budd
     * @Description 继承方法+实现多个默认方法
     * @Date 2020/8/28 11:11
     * @Param []
     * @return void
     **/
    @Test
    public void testExtendDefaultMethod() {
        new ExtendMoreImlBob();
    }
}
