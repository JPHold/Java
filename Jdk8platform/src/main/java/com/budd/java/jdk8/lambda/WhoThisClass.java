package com.budd.java.jdk8.lambda;

/**
 * @author budd
 * @desc 调用this.toString，试验是当前类的还是Lambda的
 * @since 2020/4/19 23:01
 **/
public class WhoThisClass {
    public void doWork() {
        Runnable runner = () -> {
            System.out.println(this.toString());
        };
        runner.run();
    }

    @Override
    public String toString() {
        return "WhoThisClass{}";
    }
}
