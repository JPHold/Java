package com.budd.java.jdk8.lambda;

@FunctionalInterface
public interface MyPredicate<T> {
    public boolean whereCompare(T t);
}
