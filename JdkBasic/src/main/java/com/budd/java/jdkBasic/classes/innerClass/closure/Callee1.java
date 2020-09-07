package com.budd.java.jdkBasic.classes.innerClass.closure;

import static com.budd.java.util.Print.*;

// Very simple to just implement the interface:
public class Callee1 implements Incrementable {
    private int i = 0;

    @Override
    public void increment() {
        i++;
        print(i);
    }
}
