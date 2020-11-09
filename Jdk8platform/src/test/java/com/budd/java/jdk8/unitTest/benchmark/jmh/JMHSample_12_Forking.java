package com.budd.java.jdk8.unitTest.benchmark.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 *  JMH为基准测试新开独立进程来进行测试
 *  JVM的profile-guided optimizations(PGO)对JMH影响很大(profile文件讲解：https://www.ibm.com/support/knowledgecenter/en/SSGMCP_5.3.0/com.ibm.cics.ts.java.doc/topics/dfhpj_jvmprofile_what.html)
 *  因为不同的测试可将各自的概要文件混合在一起，导致结果混乱
 *
 * 观察结果，measure_1_c、measure_2_c2、measure_3_c1_again三者都是同样代码，但结果迥异，快、慢、慢。这是为什么捏：官方说是c1和c2的JVM配置文件混合在一起了
 * Benchmark                                                        Mode  Cnt   Score   Error  Units
 * unitTest.benchmark.jmh.JMHSample_12_Forking.measure_1_c1         avgt        1.992          ns/op
 * unitTest.benchmark.jmh.JMHSample_12_Forking.measure_2_c2         avgt       11.797          ns/op
 * unitTest.benchmark.jmh.JMHSample_12_Forking.measure_3_c1_again   avgt       11.551          ns/op
 * unitTest.benchmark.jmh.JMHSample_12_Forking.measure_4_forked_c1  avgt        3.415          ns/op
 * unitTest.benchmark.jmh.JMHSample_12_Forking.measure_5_forked_c2  avgt        3.349          ns/op
 *
 * @Fork为每个基准测试方法独立开新进程来测试
 * @Date 2020年11月9日 21:08:58
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_12_Forking {

    public interface Counter {
        int inc();
    }

    public static class Counter1 implements Counter {
        private int x;

        @Override
        public int inc() {
            return x++;
        }
    }

    public static class Counter2 implements Counter {
        private int x;

        @Override
        public int inc() {
            return x++;
        }
    }

    /**
     * 还是不推荐内部循环
     * @param c
     * @return
     */
    public int measure(Counter c) {
        int s = 0;
        for (int i = 0; i < 10; i++) {
            s += c.inc();
        }
        return s;
    }

    /*
     * These are two counters.
     */
    Counter c1 = new Counter1();
    Counter c2 = new Counter2();

    /**
     * @return
     * @Fork(0)与JVM同进程
     */
    @Benchmark
    @Fork(0)
    public int measure_1_c1() {
        return measure(c1);
    }

    @Benchmark
    @Fork(0)
    public int measure_2_c2() {
        return measure(c2);
    }

    @Benchmark
    @Fork(0)
    public int measure_3_c1_again() {
        return measure(c1);
    }

    /**
     * @return
     * @Fork(1)与JVM不同进程 启动命令-f：全局配置所有基准测试方法都与JVM不同进程
     * @Fork是默认配置，其实可不配置
     */
    @Benchmark
    @Fork(1)
    public int measure_4_forked_c1() {
        return measure(c1);
    }

    @Benchmark
    @Fork(1)
    public int measure_5_forked_c2() {
        return measure(c2);
    }


    /**
     * mvn clean install
     * java -jar target/benchmarks.jar JMHSample_12
     *
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_12_Forking.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(1)
                .build();

        new Runner(opt).run();
    }

}