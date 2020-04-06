package com.budd.java.jdkBasic.jvm.gc;

import static com.budd.java.util.Print.*;

public class GcA {

    public static GcA gcA;

    public static GcA getInstance() {
        GcA.gcA = new GcA();
        return gcA;
    }

    public void live() {
        print("我还活着！！！");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        print("调用了finalize方法");
        GcA.getInstance();
    }

}
