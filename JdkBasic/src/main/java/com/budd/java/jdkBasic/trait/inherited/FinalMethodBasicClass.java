package com.budd.java.jdkBasic.trait.inherited;

public class FinalMethodBasicClass {

    private void privateSameMethod() {
        System.out.println("基类的私有同个方法");
    }

    public final void finalSameMethod() {
        System.out.println("基类的不变同个方法");
    }

}
