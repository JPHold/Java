package com.budd.java.jdk8.exception;

import org.junit.Test;

import static com.budd.java.util.Print.*;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author budd
 * @desc 异常进阶研究
 * @since 2020/3/19 22:42
 **/
public class ProExceptionTest {
    /**
     * start{异步编程-Promise模式处理异步计算、整合、异常等}
     * 同等对比Js、Go
     * https://time.geekbang.org/column/article/693
     *
     * @author budd
     * @since 2019年3月15日22:37:36
     */
    /**
     * 异步编程-异常处理
     */
    interface CallBackListener {
        void isFailure();

        void isSuccess();
    }

    /**
     * JDK8之前采用callback方法
     */
    @Test
    public void oldExceptionHandle() throws InterruptedException {
        CallBackListener callBackListener = new CallBackListener() {
            @Override
            public void isFailure() {
                print("错误");
            }

            @Override
            public void isSuccess() {
                print("成功");
            }
        };
        new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (new Random().nextBoolean()) {
                callBackListener.isSuccess();
            } else {
                callBackListener.isFailure();
            }
        }).start();

        Thread.sleep(1000);
    }

    /**
     * 异步编程-异常处理
     * JDK8之后采用Promise模式处理异常等
     */
    @Test
    public void exceptionHello() {
        CompletableFuture.supplyAsync(() -> Integer.valueOf("budd"))
                .thenApply(r -> r * 2 * Math.PI)
                .thenApply(s -> "apply>> " + s)
                .exceptionally(ex -> {
                    return "Error: " + ex.getMessage();
                })
                .whenCompleteAsync((arg, ex) -> printf("\n参数:%s,%s异常", arg, ((ex != null) ? ex.getMessage() : "无或屏蔽")));
    }


    /**
     * 组合多个结果
     */
    @Test
    public void combineHello() {
        String result = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return "promise";
        }), (s1, s2) -> s1 + " " + s2).join();
        print("\n----------" + result);
    }
    //end{异步编程-Promise模式处理异步计算、整合、异常等}

}
