package com.budd.java.jdk8.unitTest.assertion;

import static com.budd.java.util.Print.*;
import static com.google.common.base.Preconditions.*;

import com.google.common.base.VerifyException;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static com.budd.java.util.Print.print;
import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Preconditions.checkPositionIndexes;
import static com.google.common.base.Verify.verify;
import static com.google.common.base.Verify.verifyNotNull;

/**
 * @author budd
 * @desc Guava的断言入门研究
 * @since 2020/11/4 16:48
 **/
public class HelloGuavaAssertTest {

    /**
     * 测试guava的Verify
     * 替代java的assert
     *
     * @Date 2020年11月2日 21:36:17
     */
    @Test
    public void testGuavaVerify() {

        print("verify(expression)");
        verify(2 + 2 == 4);
        try {
            verify(1 + 2 == 4);
        } catch (VerifyException e) {
            System.out.println(e);
        }

        printf("%nverify(expression, errorMessageTemplate)");
        try {
            verify(1 + 2 == 4, "运算结果错误");
        } catch (VerifyException e) {
            System.out.println(e.getMessage());
        }

        printf("%nverify(expression, errorMessageTemplate, param)");
        try {
            verify(1 + 2 == 4, "运算结果错误: %s", "不等于4");
        } catch (VerifyException e) {
            System.out.println(e.getMessage());
        }

        printf("%nverifyNotNull(reference)");
        String s = "";
        s = verifyNotNull(s);
        s = null;
        try {
            verifyNotNull(s);
        } catch (VerifyException e) {
            print(e.getMessage());
        }

        printf("%nverifyNotNull(reference, errorMessageTemplate, ...errorMessageArgs)");
        try {
            verifyNotNull(
                    s, "值为null: %s", "arg s");
        } catch (VerifyException e) {
            print(e.getMessage());
        }
    }

    /**
     * 测试Guava包的前置条件
     *
     * @Date 2020年11月3日 23:05:08
     */
    static void test(Consumer<String> c, String s) {
        try {
            printnb(s);
            c.accept(s);
            print(",Success");
        } catch (Exception e) {
            String type = e.getClass().getSimpleName();
            String msg = e.getMessage();
            printf(",%s:%s", type, (msg == null ? "" : msg));
        }
    }

    @Test
    public void testGuavaPreCondition() {
        print("checkNotNull");
        test(s -> s = checkNotNull(s), "X");
        test(s -> s = checkNotNull(s), null);
        test(s -> s = checkNotNull(s, "s was null"), null);

        print();
        print("checkArgument");
        test(s -> checkArgument(s == "Fozzie"), "Fozzie");
        test(s -> checkArgument(s == "Fozzie"), "X");
        test(s -> checkArgument(s == "Fozzie"), null);

        print();
        print("checkState");
        test(s -> checkState(s.length() > 6), "Mortimer");
        test(s -> checkState(s.length() > 6), "Mort");
        test(s -> checkState(s.length() > 6), null);

        print();
        print("checkElementIndex");
        test(s ->
                checkElementIndex(6, s.length()), "Robert");
        test(s ->
                checkElementIndex(6, s.length()), "Bob");
        test(s ->
                checkElementIndex(6, s.length()), null);

        print();
        print("checkPositionIndex");
        test(s ->
                checkPositionIndex(6, s.length()), "Robert");
        test(s ->
                checkPositionIndex(6, s.length()), "Bob");
        test(s ->
                checkPositionIndex(6, s.length()), null);
        test(s -> checkPositionIndexes(
                0, 6, s.length()), "Hieronymus");
        test(s -> checkPositionIndexes(
                0, 10, s.length()), "Hieronymus");
        test(s -> checkPositionIndexes(
                0, 11, s.length()), "Hieronymus");
        test(s -> checkPositionIndexes(
                -1, 6, s.length()), "Hieronymus");
        test(s -> checkPositionIndexes(
                7, 6, s.length()), "Hieronymus");
        test(s -> checkPositionIndexes(
                0, 6, s.length()), null);
    }
}
