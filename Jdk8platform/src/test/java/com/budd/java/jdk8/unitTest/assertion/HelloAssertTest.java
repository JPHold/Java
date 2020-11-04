package com.budd.java.jdk8.unitTest.assertion;

import com.budd.java.jdk8.unitTest.assertion.assertStatic.StaticAssertClass;
import com.budd.java.jdk8.unitTest.junit.checkEa.CheckEnableAssertion;
import org.junit.jupiter.api.Test;

/**
 * @author budd
 * @desc 断言入门研究
 * @since 2020/11/2 20:25
 **/
public class HelloAssertTest {

    /**
     * 测试简单断言关键字
     *
     * @Date 2020年11月2日 20:28:24
     */
    @Test
    public void testSimpleAssert() {
        assert false;
    }

    /**
     * 测试简单断言关键字
     *
     * @Date 2020年11月2日 20:29:30
     */
    @Test
    public void testExceptionMsgAssert() {
        assert false : "结果错误";
    }

    /**
     * 测试整个类开启断言功能
     *
     * @Date 2020年11月2日 20:29:30
     */
    @Test
    public void testClassStaticAssertClass() {
        new StaticAssertClass().test();
    }

    /**
     * 测试检查是否开启断言
     *
     * @Date 2020年11月2日 21:36:17
     */
    @Test
    public void testCheckEnableAssertion() {
        new CheckEnableAssertion();
    }
}
