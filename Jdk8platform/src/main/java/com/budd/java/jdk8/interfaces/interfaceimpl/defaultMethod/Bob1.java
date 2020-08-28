package com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod;

interface Bob1 {
    default void bob() {
        System.out.println("Bob1::bob");
    }
}