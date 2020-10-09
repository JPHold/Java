package com.budd.java.jdk8.lambda.recursion.more;

import com.budd.java.jdk8.lambda.recursion.single.IntCall;

public class RecursiveFibonacci {
    IntCall fib;

    public RecursiveFibonacci() {
        fib = n -> n == 0 ? 0 :
                n == 1 ? 1 :
                        fib.call(n - 1) + fib.call(n - 2);
    }

    public int fibonacci(int n) {
        return fib.call(n);
    }
}