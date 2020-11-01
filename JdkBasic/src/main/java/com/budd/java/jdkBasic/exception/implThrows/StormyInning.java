package com.budd.java.jdkBasic.exception.implThrows;

public class StormyInning extends Inning implements Storm {
    //【1】派生类构造器可以throws新异常，但必须包含抽象中预定义的异常
    public StormyInning()
            throws RainedOut, BaseballException {
        super();
    }

    //【1】如果没有初始化基类构造器，默认是调用基类的无参构造器。因此要声明异常
    /*public StormyInning(String s){
    }*/

    //【1】
    public StormyInning(String s) {
        super(s);
    }

    //【2】抽象方法中无预定义的异常，派生类不能抛出
//    void walk() throws PopFoul {} //编译报错

    //【3】接口定义的方法由继承的抽象类静默实现，抛出异常要一致
//    public void event() throws RainedOut {}//编译报错

    //【4】即使抽象方法中预定义的异常，派生类可不抛出
    @Override
    public void event() {
    }

    //【5】没有静默实现，当然没报错
    @Override
    public void rainHard() throws RainedOut {
    }

    //【6】抽象方法中预定义的异常。派生类可抛出该异常的派生类
    @Override
    public void atBat() throws PopFoul {
    }

}