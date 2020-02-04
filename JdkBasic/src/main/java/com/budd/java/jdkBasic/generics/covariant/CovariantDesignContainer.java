package com.budd.java.jdkBasic.generics.covariant;

/**
 * 泛型设计容器
 *
 * @author budd
 * @since 2018/3/4 22:57:54
 **/
public class CovariantDesignContainer<T> {

    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public void setValue2(Object value) {
        this.value = (T) value;
    }

    public T getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return this.value.equals(obj);
    }

}