package com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod;

interface Bob2 {
    default void bob() {
        System.out.println("Bob2::bob");
    }
}