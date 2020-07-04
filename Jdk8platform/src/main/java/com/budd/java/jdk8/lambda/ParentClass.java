package com.budd.java.jdk8.lambda;

import static com.budd.java.util.Print.*;

/**
 * @author budd
 * @desc 继承体系中-lambda表达式-基类
 * @since 2020/4/19 20:12
 **/
public class ParentClass {
    public void hello(String word) {
        printf("Hello, world!%s\n", word);
    }
}
