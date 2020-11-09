package com.budd.java.jdk8.unitTest.benchmark.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.*;

/**
 * 讲解Level等级的应用
 * @Date 2020年11月7日 22:32:03
 */
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class JMHSample_07_FixtureLevelInvocation {

    /**
     * @Setup(Level.Invocation)可在每次调用前作一些工作
     * @Setup和@TearDown方法，耗时并不会纳入统计结果
     */

    /**
     * 定义了一个全部基准线程共享的状态类，其中创建了一个在整个基准测试生命周期的线程池(只创建一次，共享的)
     */
    @State(Scope.Benchmark)
    public static class NormalState {
        ExecutorService service;

        @Setup(Level.Trial)
        public void up() {
            service = Executors.newCachedThreadPool();
        }

        @TearDown(Level.Trial)
        public void down() {
            service.shutdown();
        }

    }

    /**
     * 继承NormalState，同时继承了状态的特性，新增了每次调用都会触发的休眠方法
     */
    public static class LaggingState extends NormalState {
        public static final int SLEEP_TIME = Integer.getInteger("sleepTime", 100);

        @Setup(Level.Invocation)
        public void lag() throws InterruptedException {
            System.out.println("休眠");
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        }
    }

    /**
     * 基准方法命名为measureHot：并不是说专用于热测试；命名为measureCold，也不是说专用于冷测试
     *
     * @param e
     * @param s
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public double measureHot(NormalState e, final Scratch s) throws ExecutionException, InterruptedException {
        return e.service.submit(new Task(s)).get();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public double measureCold(LaggingState e, final Scratch s) throws ExecutionException, InterruptedException {
        return e.service.submit(new Task(s)).get();
    }

    /**
     * 任务
     */
    @State(Scope.Thread)
    public static class Scratch {
        private double p;

        public double doWork() {
            p = Math.log(p);
            return p;
        }
    }

    public static class Task implements Callable<Double> {
        private Scratch s;

        public Task(Scratch s) {
            this.s = s;
        }

        @Override
        public Double call() {
            return s.doWork();
        }
    }

    /**
     * mvn clean install
     * java -jar target/benchmarks.jar JMHSample_07 -f 1
     *
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        System.out.println(Math.log(1.000D));
        Options opt = new OptionsBuilder()
                .include(JMHSample_07_FixtureLevelInvocation.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
