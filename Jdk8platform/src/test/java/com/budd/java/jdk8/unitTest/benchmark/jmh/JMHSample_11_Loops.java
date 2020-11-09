
package com.budd.java.jdk8.unitTest.benchmark.jmh;

import static com.budd.java.util.Print.*;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Date 2020年11月8日 22:19:39
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_11_Loops {


    /**
     * 可能会在基准测试方法中使用循环，因为有这样的结论：循环是最小化调用测试方法的开销
     * 不要相信这些结论，因为优化器优化循环迭代时，会有奇奇怪怪的事发生
     * 为什么不要相信：
     * 因为JVM会有循环展开的操作，为具有多个功能单元的处理器提供指令级并行。也有利于指令流水线的调度，如下代码会被重新编排
     * for (i = 1; i <= 60; i++)
     *    a[i] = a[i] * b + c;
     *
     * 会展开成如下：
     *  for (i = 1; i <= 60; i+=3)
     * {
     *   a[i] = a[i] * b + c;
     *   a[i+1] = a[i+1] * b + c;
     *   a[i+2] = a[i+2] * b + c;
     * }
     * 由原来的60次缩减到20次循环
     *
     * 测试结果也就不准确了，因此JMH不推荐在基准测试方法中使用循环，可使用@BenchmarkMode(Mode.SingleShotTime)和@Measurement(batchSize = N)达成循环效果
     *
     * 结合https://blog.csdn.net/weixin_42348333/article/details/85857480了解OperationsPerInvocation
     * 一PerInvocation指的是每次基准循环，Operations指的是从基准方法内部的循环次数中取几次作为计算结果
     * 比如内部循环10次，OperationsPerInvocation设置为1，加入BenchmarkMode的Mode为Throughput，那么吞吐为1
     *
     * BenchmarkMode的Mode为AverageTime，观察结果，OperationsPerInvocation(操作次数)越大，因每次耗时=每一次调用的执行时间/操作次数，操作次数越大，每次耗时越小，导致最终计算的平均时间就越小
     * measureWrong_100w设置OperationsPerInvocation：100w大于内部实际循环次数：10w，得出结果却与measureWrong_100000相差很大，因此OperationsPerInvocation设定不要超过内部实际循环次数
     * Benchmark                                                      Mode  Cnt  Score   Error  Units
     * unitTest.benchmark.jmh.JMHSample_11_Loops.measureRight         avgt       2.069          ns/op
     * unitTest.benchmark.jmh.JMHSample_11_Loops.measureWrong_1       avgt       2.014          ns/op
     * unitTest.benchmark.jmh.JMHSample_11_Loops.measureWrong_10      avgt       0.226          ns/op
     * unitTest.benchmark.jmh.JMHSample_11_Loops.measureWrong_100     avgt       0.025          ns/op
     * unitTest.benchmark.jmh.JMHSample_11_Loops.measureWrong_1000    avgt       0.022          ns/op
     * unitTest.benchmark.jmh.JMHSample_11_Loops.measureWrong_10000   avgt       0.018          ns/op
     * unitTest.benchmark.jmh.JMHSample_11_Loops.measureWrong_100000  avgt       0.016          ns/op
     * unitTest.benchmark.jmh.JMHSample_11_Loops.measureWrong_100w    avgt       0.002          ns/op
     * https://github.com/ihaolin/java-benchmark讲解参数
     */


    int x = 1;
    int y = 2;

    @Benchmark
    public int measureRight() {
        return (x + y);
    }

    /**
     * 循环中累加结果，这是Caliper-style的基准测试
     *
     * @param reps
     * @return
     */
    private int reps(int reps) {
//        printf("执行循环：%s", reps);
        int s = 0;
        for (int i = 0; i < reps; i++) {
            s += (x + y);
        }
        return s;
    }

    /**
     * 通过OperationsPerInvocation限定每次基准循环调用多少次基准方法
     * 1次
     *
     * @return
     */
    @Benchmark
    @OperationsPerInvocation(1)
    public int measureWrong_1() {
        return reps(1);
    }

    /**
     * 通过OperationsPerInvocation限定每次基准循环调用多少次基准方法
     * 10次
     *
     * @return
     */
    @Benchmark
    @OperationsPerInvocation(10)
    public int measureWrong_10() {
        return reps(10);
    }

    /**
     * 通过OperationsPerInvocation限定每次基准循环调用多少次基准方法
     * 100次
     *
     * @return
     */
    @Benchmark
    @OperationsPerInvocation(100)
    public int measureWrong_100() {
        return reps(100);
    }

    /**
     * 通过OperationsPerInvocation限定每次基准循环调用多少次基准方法
     * 1k次
     *
     * @return
     */
    @Benchmark
    @OperationsPerInvocation(1_000)
    public int measureWrong_1000() {
        return reps(1_000);
    }

    /**
     * 通过OperationsPerInvocation限定每次基准循环调用多少次基准方法
     * 10k次
     *
     * @return
     */
    @Benchmark
    @OperationsPerInvocation(10_000)
    public int measureWrong_10000() {
        return reps(10_000);
    }

    /**
     * 通过OperationsPerInvocation限定每次基准循环调用多少次基准方法
     * 100k次
     *
     * @return
     */
    @Benchmark
    @OperationsPerInvocation(100_000)
    public int measureWrong_100000() {
        return reps(100_000);
    }

    @Benchmark
    @OperationsPerInvocation(100_0000)
    public int measureWrong_100w() {
        return reps(100_000);
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You might notice the larger the repetitions count, the lower the "perceived"
     * cost of the operation being measured. Up to the point we do each addition with 1/20 ns,
     * well beyond what hardware can actually do.
     *
     * This happens because the loop is heavily unrolled/pipelined, and the operation
     * to be measured is hoisted from the loop. Morale: don't overuse loops, rely on JMH
     * to get the measurement right.
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_11 -f 1
     *    (we requested single fork; there are also other options, see -h)
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_11_Loops.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}