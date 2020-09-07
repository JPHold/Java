package com.budd.java.jdkBasic.classes.innerClass.closure;

public class Caller {
    private Incrementable callbackReference;

    public Caller(Incrementable cbh) {
        callbackReference = cbh;
    }

    public void go() {
        callbackReference.increment();
    }
}
