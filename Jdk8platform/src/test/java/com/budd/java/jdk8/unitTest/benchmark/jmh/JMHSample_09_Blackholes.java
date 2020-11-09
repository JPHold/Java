package com.budd.java.jdk8.unitTest.benchmark.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 返回多个结果，基准方法该如何编写，有两种方式
 * 查看方式1和方式2
 * 使用结果相加并返回、使用Blackhole消费结果
 * 如果只返回一个结果，采用隐式返回，可读性更好(但没有纳入统计结果，所以不会采用这种方式)
 *
 * @Date 2020年11月8日 16:25:37
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class JMHSample_09_Blackholes {

    double x1 = Math.PI;
    double x2 = Math.PI * 2;

    /**
     * 基线测试：Math.log
     *
     * @return
     */
    @Benchmark
    public double baseline() {
        return Math.log(x1);
    }

    /**
     * [1]是无效、会被优化的代码，该耗时不会纳入统计结果(观察measureRight_2和measureWrong的分数即可得知(后者比前者少了一半耗时))
     * [2]计算结果会被纳入统计结果
     *
     * @return
     */
    @Benchmark
    public double measureWrong() {
        Math.log(x1);//[1]
        return Math.log(x2);//[2]
    }

    /**
     * 方式1
     * 两个结果相加，当计算是相对地重量级，合并结果对最终统计结果影响不大
     *
     * @return
     */
    @Benchmark
    public double measureRight_1() {
        return Math.log(x1) + Math.log(x2);
    }

    /**
     * 方式二
     * 使用显式的黑洞对象去消费结果
     *
     * @param bh
     */
    @Benchmark
    public void measureRight_2(Blackhole bh) {
        bh.consume(Math.log(x1));
        bh.consume(Math.log(x2));
    }

    /**
     * mvn clean install
     * java -jar target/benchmarks.jar JMHSample_09 -f 1
     *
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_09_Blackholes.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                .build();

        new Runner(opt).run();
    }


}