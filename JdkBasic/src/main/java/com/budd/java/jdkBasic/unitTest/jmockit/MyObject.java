package com.budd.java.jdkBasic.unitTest.jmockit;

import java.util.Random;

public class MyObject {
    public MyObject() {
    }

    public MyObject(String name) {
        this.name = name;
    }

    private String name;

    public String hello(String name) {
        return "Hello " + name + new Random().nextInt();
    }

    public String hello1(String name) {
        return "Hello1 " + name;
    }

    public static String hello2(String name) {
        return "Hello2 " + name;
    }

    private String hello3(String name) {
        return "Hello3 " + name;
    }

    private static String hello4(String name) {
        return "Hello4 " + name;
    }

    public String hello5(String name) {
        return "Hello5 " + name;
    }

    public static String hello6(String name) {
        return "Hello6 " + name;
    }

    public String getName() {
        return name;
    }
}