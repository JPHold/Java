package com.budd.java.jdk8.lambda;

import static com.budd.java.util.Print.*;

import java.util.function.Consumer;

/**
 * @author budd
 * @desc 继承体系中-lambda表达式-派生类
 * @since 2020/4/19 20:12
 **/
public class ChildClass extends ParentClass {

    public void invokeThisMethod(String word) {
        printf("Hello, world!%s\n", word);
    }

    public void oldInvokeSuperMethod() {
        super.hello("old:super.");
        ChildClass.super.hello("old:ChildClass.super.");
        ChildClass.this.invokeThisMethod("old:ChildClass.this.");
    }

    public void lambdaInvokeSuperMethod() {
        Consumer<String> consumer1 = super::hello;
        consumer1.accept("lambda:supper::");

        Consumer<String> consumer2 = ChildClass.super::hello;
        consumer2.accept("lambda:ChildClass.supper::");

        Consumer<String> consumer3 = ChildClass.this::invokeThisMethod;
        consumer3.accept("lambda:ChildClass.this::");
    }
}
