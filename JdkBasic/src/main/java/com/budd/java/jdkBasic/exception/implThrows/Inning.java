package com.budd.java.jdkBasic.exception.implThrows;

public abstract class Inning {
    public Inning() throws BaseballException {
    }

    public Inning(String s) {
    }

    public void event() throws BaseballException {
        // Doesn't actually have to throw anything
    }

    public abstract void atBat() throws Strike, Foul;

    public void walk() {
    } // Throws no checked exceptions
}