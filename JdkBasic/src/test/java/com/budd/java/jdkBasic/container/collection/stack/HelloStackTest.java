package com.budd.java.jdkBasic.container.collection.stack;

import static com.budd.java.util.Print.*;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author budd
 * @desc stack的入门研究
 * @since 2020/9/21 21:30
 **/
public class HelloStackTest {


    /**
     * 研究Stack的替代：ArrayDeque
     */
    @Test
    public void testArrayDeque() {
        Deque<String> stack = new ArrayDeque<>();
        for (String s : "My dog has fleas".split(" "))
            stack.push(s);
        while (!stack.isEmpty()) {
            printnb(stack.pop() + " ");
        }
        print();
    }


}
