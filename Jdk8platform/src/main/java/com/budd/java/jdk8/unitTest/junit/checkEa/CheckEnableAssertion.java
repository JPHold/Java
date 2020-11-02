package com.budd.java.jdk8.unitTest.junit.checkEa;

/**
 * @author budd
 * @desc 检查是否开启断言
 * @since 2020/11/2 21:34
 **/
public class CheckEnableAssertion {
    static {
        boolean assertionsEnabled = false;
        assert assertionsEnabled = true;
        if (!assertionsEnabled) {
            throw new RuntimeException("Assertions disabled");
        }
    }
}
