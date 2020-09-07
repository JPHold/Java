package com.budd.java.jdkBasic.classes.innerClass.closure;

import static com.budd.java.util.Print.*;

public class MyIncrement {
    public void increment() {
        print("Other operation");
    }

    public static void f(MyIncrement mi) {
        mi.increment();
    }
}