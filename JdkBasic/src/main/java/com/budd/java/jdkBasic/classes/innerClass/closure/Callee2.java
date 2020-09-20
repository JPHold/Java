package com.budd.java.jdkBasic.classes.innerClass.closure;

import static com.budd.java.util.Print.*;

// If your class must implement increment() in
// some other way, you must use an inner class:
public class Callee2 extends MyIncrement {
    private int i = 0;

    @Override
    public void increment() {
        super.increment();
        i++;
        print(i);
    }

    private class Closure implements Incrementable {
        @Override
        public void increment() {
            // Specify outer-class method, otherwise
            // you'll get an infinite recursion:
            Callee2.this.increment();
        }
    }

    public Incrementable getCallbackReference() {
        return new Closure();
    }
}