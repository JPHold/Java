package com.budd.java.jdkBasic.classes.innerClass;

public class InvokeAnonymousInterface {
    // 无参构造-继承类
    public AnonymousInterface f0(final String str) {
        return new AnonymousInterface() {
            @Override
            public void f0() {
                System.out.println("无参构造器-方法调用");
            }
        };
    }

    // 无参构造-继承类
    public AnonymousInterface f1(final String str) {
        new AnonymousInterface() {
            @Override
            public void f0() {
                System.out.println("无参构造器-方法调用" + str);
            }
        };
        return new AnonymousInterface() {
            @Override
            public void f0() {
                System.out.println("无参构造器-方法调用" + str);
            }
        };
    }
}
