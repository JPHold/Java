package com.budd.java.jdk8.unitTest.benchmark.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 支持多个Fork，JVM是复杂的，不固定是特性。因此不同进程的运行结果，该如何计算，forking在几个JVM中聚合了结果
 * @Fork与@State(Scope.Thread)搭配使用，@Fork指定从2开始，@State(Scope.Thread)才有效(可观察baseline，三次结果都接近，说明三次循环调用都在同进程同线程)
 *
 * 观察结果
 * # Benchmark: com.budd.java.jdk8.unitTest.benchmark.jmh.JMHSample_13_RunToRun.baseline
 *
 * # Run progress: 0.00% complete, ETA 00:03:00
 * # Fork: 1 of 1
 * Iteration   1: 419.549 ms/op
 * Iteration   2: 419.531 ms/op
 * Iteration   3: 419.433 ms/op
 *
 *
 * Result "com.budd.java.jdk8.unitTest.benchmark.jmh.JMHSample_13_RunToRun.baseline":
 *   419.504 ±(99.9%) 1.137 ms/op [Average]
 *   (min, avg, max) = (419.433, 419.504, 419.549), stdev = 0.062
 *   CI (99.9%): [418.368, 420.641] (assumes normal distribution)
 *
 *---------------------------------------
 *
 * # Benchmark: com.budd.java.jdk8.unitTest.benchmark.jmh.JMHSample_13_RunToRun.fork_2
 *
 * # Run progress: 66.67% complete, ETA 00:01:02
 * # Fork: 1 of 2
 * Iteration   1: 422.554 ms/op
 * Iteration   2: 422.452 ms/op
 * Iteration   3: 422.525 ms/op
 *
 * # Run progress: 83.33% complete, ETA 00:00:31
 * # Fork: 2 of 2
 * Iteration   1: 44.575 ms/op
 * Iteration   2: 44.531 ms/op
 * Iteration   3: 44.554 ms/op
 *
 *---------------------------------------
 *
 * # Benchmark: com.budd.java.jdk8.unitTest.benchmark.jmh.JMHSample_13_RunToRun.fork_3
 *
 * # Run progress: 16.67% complete, ETA 00:02:34
 * # Fork: 1 of 3
 * Iteration   1: 42.582 ms/op
 * Iteration   2: 42.619 ms/op
 * Iteration   3: 42.619 ms/op
 *
 * # Run progress: 33.33% complete, ETA 00:02:03
 * # Fork: 2 of 3
 * Iteration   1: 249.449 ms/op
 * Iteration   2: 249.528 ms/op
 * Iteration   3: 249.494 ms/op
 *
 * # Run progress: 50.00% complete, ETA 00:01:32
 * # Fork: 3 of 3
 * Iteration   1: 493.506 ms/op
 * Iteration   2: 493.559 ms/op
 * Iteration   3: 493.536 ms/op
 *
 *
 * @Date 2020年11月9日 21:36:22
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JMHSample_13_RunToRun {

    @State(Scope.Thread)
    public static class SleepyState {
        public long sleepTime;

        @Setup
        public void setup() {
            sleepTime = (long) (Math.random() * 1000);
        }
    }

    /*
     * Now, we will run this different number of times.
     */

    @Benchmark
    @Fork(1)
    public void baseline(SleepyState s) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(s.sleepTime);
    }

    @Benchmark
    @Fork(2)
    public void fork_5(SleepyState s) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(s.sleepTime);
    }

    @Benchmark
    @Fork(3)
    public void fork_3(SleepyState s) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(s.sleepTime);
    }

    /**
     * mvn clean install
     * java -jar target/benchmarks.jar JMHSample_13 -wi 0 -i 3
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_13_RunToRun.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }

}