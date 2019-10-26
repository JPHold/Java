package com.budd.java.jdkBasic.typeSafeEnum;

public class HelloEnumTest {

    private void getSeason(SeasonEnum season) {
        switch (season) {
            case SPRING:
            case SUMMER:
            case AUTUMN:
            case WINTER:
                System.out.println(season.name() + "," + season.getCode() + "," + season.getDesc());
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {

        /**
         * 在枚举出现之前是定义多个静态变量,表示一组描述
         * 但代码的要求是安全性、易用性、可读性。
         *  安全性：上面的方法getSeason(int i),除了1到4还可以传入其他值,这显然破坏了该方法本身的含义
         *  可读性：如果打印出传入的描述值,比如1,无法得知描述什么,如果改为String,因为switch用equals比对,影响性能
         */
        OldEnumImpl oldEnumImpl = new OldEnumImpl();
        oldEnumImpl.getSeason(3);
        oldEnumImpl.getSeason("3");

        /**
         * 安全性、易用性、可读性都符合
         */
        new HelloEnumTest().getSeason(SeasonEnum.WINTER);

    }
}
