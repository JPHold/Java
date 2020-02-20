package com.budd.java.jdkBasic.classes.innerClass;

public class InvokeAnonymousClass {
    // 一参构造-继承类，无重写构造器
    public AnonymousClass f0(final String str) {
        return new AnonymousClass(str) {
            @Override
            public void f0() {
                System.out.println("一参子类构造器-方法调用");
            }
        };
    }

    // 两参构造-继承类，重写构造器
    public AnonymousClass f1(final String str) {
        return new AnonymousClass(str) {
            {
                System.out.println("一参子类构造器" + str);
            }

            @Override
            public void f0() {
                System.out.println("方法调用");
            }
        };
    }

    // 两参构造-继承类，重写构造器
    public AnonymousClass f2(final String str1, final String str2) {
        return new AnonymousClass(str1) {
            @Override
            public void f0() {
                System.out.println("一参子类构造器-方法调用" + str2);
            }
        };
    }

    // 两参构造-继承类，重写构造器
    public AnonymousClass f3(final String str1) {

        final String str2 = null;
        return new AnonymousClass(str1) {

            @Override
            public void f0() {
                System.out.println("一参子类构造器-方法调用" + str2);
            }
        };
    }
}