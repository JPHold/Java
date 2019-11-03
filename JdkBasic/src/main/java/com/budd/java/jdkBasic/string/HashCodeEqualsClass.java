package com.budd.java.jdkBasic.string;

public class HashCodeEqualsClass {

    private String color;

    public HashCodeEqualsClass(String color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        return this.color.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj ==null) return false;
        if(!(obj instanceof HashCodeEqualsClass)) return false;
        if(obj==this) return true;
        return this.color.equals(((HashCodeEqualsClass)obj).getColor());

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "HashCodeEqualsClass{" +
                "color='" + color + '\'' +
                '}';
    }
}
