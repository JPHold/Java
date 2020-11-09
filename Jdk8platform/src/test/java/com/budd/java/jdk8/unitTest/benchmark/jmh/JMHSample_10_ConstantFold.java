package com.budd.java.jdk8.unitTest.benchmark.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_10_ConstantFold {

    /**
     * 消除无效代码，另一个特征是不断折叠
     * 如果JVM意识到代码多次到用，计算结果都是一样的，那么会被优化掉：使用常量参与计算，这样的代码会在编译期间就完成了计算。
     * 因此执行这样的基准测试，其实基准循环测试并没有计算(计算工作在JMH之外(编译期间)就完成了)
     *
     * 假如想在JMH内才计算，有以下方式
     * 1、从@State类读取非final属性并使用
     */

    /**
     * IDE会提示最好将x变量声明为局部变量(别采取该建议，因为实际测试是不起作用的)
     */
    private double x = Math.PI;

    private final double wrongX = Math.PI;

    /**
     * 直接返回
     *
     * @return
     */
    @Benchmark
    public double baseline() {
        return Math.PI;
    }

    /**
     * 错误的，代码是可预测的，计算是可折叠的
     *
     * @return
     */
    @Benchmark
    public double measureWrong_1() {
        return Math.log(Math.PI);
    }

    /**
     * 错误的，代码是可预测的，计算是可折叠的
     *
     * @return
     */
    @Benchmark
    public double measureWrong_2() {
        return Math.log(wrongX);
    }

    @Benchmark
    public void measureWrong_3(Blackhole blackhole) {
        blackhole.consume(Math.log(Math.PI));
    }

    /**
     * 正确的，代码是不可预测的
     *
     * @return
     */
    @Benchmark
    public double measureRight() {
        return Math.log(x);
    }

    /**
     * mvn clean install
     * java -jar target/benchmarks.jar JMHSample_10 -i 5 -f 1
     *
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_10_ConstantFold.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}