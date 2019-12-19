package com.budd.java.jdkBasic.trait.inherited;

public class StaticChildClass extends StaticBasicClass {

    public static void getStaticGet() {
        System.out.println("派生类：getStaticGet");
    }

    public void getNormalGet() {
        System.out.println("派生类:getNormalGet");
    }

}
