
package com.budd.java.jdk8.unitTest.benchmark.jmh;

import static com.budd.java.util.Print.*;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @Date 2020年11月7日 21:40:35
 */
@State(Scope.Thread)
public class JMHSample_06_FixtureLevel {

    double x;

    /**
     * 三种等级
     * 1、Trial整个基准运行前后(迭代序列)
     * 比如热迭代次数为2，则第二次结束后才会调用安插的方法(@Setup和@TearDown方法)，也就是整个基准测试结束后
     * <p>
     * 2、Iteration每次基准迭代前后(调用序列)
     * 比如热迭代次数为2，则第一次和第二次结束后都会调用安插的方法
     * <p>
     * 3、Invocation每次迭代中，每次基准方法调用前后
     * 比如热迭代次数为1，则每次迭代中，循环调用基准方法，结束后，都会调用安插的方法
     */
    @TearDown(Level.Iteration)
    public void check() {
        assert x > Math.PI : "Nothing changed?";
        print("结果符合预期");
    }

    @Benchmark
    public void measureRight() {
        x++;
    }

    @Benchmark
    public void measureWrong() {
        double x = 0;
        x++;
    }

    /**
     * shouldFailOnError：报错时是否终止基准测试，默认false
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_06_FixtureLevel.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                .jvmArgsAppend("-ea")
                .shouldFailOnError(false)
                .build();

        new Runner(opt).run();
    }

}