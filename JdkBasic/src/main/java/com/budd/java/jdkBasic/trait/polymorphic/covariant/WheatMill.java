package com.budd.java.jdkBasic.trait.polymorphic.covariant;

public class WheatMill extends Mill {
    @Override
    public Wheat process() {
        return new Wheat();
    }
}