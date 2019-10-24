package com.budd.java.jdkBasic.typeSafeEnum;

public class UseSeason {

    private void getSeason(Season season) {
        switch (season) {
            case SPRING:
                System.out.println(season.name() + "," + season.getCode() + "," + season.getDesc());
                break;
            case SUMMER:
                System.out.println(season.name() + "," + season.getCode() + "," + season.getDesc());
                break;
            case AUTUMN:
                System.out.println(season.name() + "," + season.getCode() + "," + season.getDesc());
                break;
            case WINTER:
                System.out.println(season.name() + "," + season.getCode() + "," + season.getDesc());
                break;
            default:
                break;
        }
    }

    private void getSeason(int i) {
        switch (i) {
            case 1:
                System.out.println("春天");
                break;
            case 2:
                System.out.println("夏天");
                break;
            case 3:
                System.out.println("秋天");
                break;
            case 4:
                System.out.println("冬天");
                break;
            default:
                System.out.println("未选择");
                break;
        }
    }

    private void getSeason(String i) {
        switch (i) {
            case "1":
                System.out.println("春天");
                break;
            case "2":
                System.out.println("夏天");
                break;
            case "3":
                System.out.println("秋天");
                break;
            case "4":
                System.out.println("冬天");
                break;
            default:
                System.out.println("未选择");
                break;
        }
    }

    public static void main(String[] args) {
        new UseSeason().getSeason(Season.WINTER);
        /**
         * 在枚举之前是定义多个静态变量,表示一组描述
         * 但代码的要求是安全性、易用性、可读性。
         *  安全性：上面的方法getSeason(int i)/getSeason(String i),除了1到4还可以传入其他值,这显然破坏了该方法本身的含义
         *  可读性：如果打印出传入的描述值,比如1,无法得知描述什么,如果改为String,因为switch用equals比对,影响性能
         */

        //枚举的正确姿势
        //1、自定义方法(包括静态方法)
        //2、重写方法
        Food.Coffee.BLACK.desc();//3、实现接口 4、使用接口组织枚举

        /**
         * 枚举的特点(http://www.hollischuang.com/archives/197)
         *  通过jad xxxEnun生成的xxxEnum.jad,用文本打开即可：
         *  1、无法被继承
         *  2、线程安全
         *   当一个Java类第一次被真正使用到的时候静态资源被初始化、Java类的加载和初始化过程都是线程安全的
         *   当类实现序列化时,通过readObject()返回一个新对象,造成单例失效,虽然可以通过readResolve()避免,但麻烦
         *   所以JVM针对枚举的序列化,禁止掉readObject()、readResolve()等方法。
         *   同时枚举的序列化是将name()返回,反序列化是将values(),
         *      这个values方法是将存放枚举变量的数组返回,这些枚举变量和装他们的数组都是静态和常量的,在编译类时就已维护好了
         */
        /**
         * 枚举的使用场景
         *  1、单例(Effective Java作者推荐)
         */
    }

    private interface Food {
        enum Coffee implements Food {
            BLACK, LATTE;

            @Override
            public void desc() {
                System.out.println(name());
            }
        }

        enum Fruit implements Food {
            SNOW, BANANA;

            @Override
            public void desc() {
                System.out.println(name());
            }
        }

        void desc();

    }

}
