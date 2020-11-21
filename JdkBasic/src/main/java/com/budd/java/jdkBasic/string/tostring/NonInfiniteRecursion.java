package com.budd.java.jdkBasic.string.tostring;

public class NonInfiniteRecursion {
    @Override
    public String toString() {
        return " InfiniteRecursion address: " + super.toString() + "\n";
    }
}