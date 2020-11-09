package com.budd.java.jdk8.unitTest.benchmark.jmh;

import static com.budd.java.util.Print.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Dead-Code-Elimination(DCE,无效代码消除)
 * 编译器可以推断冗余的代码，并消除。因此JMH的结果可能就不准确(消除的代码产生的耗时并没有纳入统计结果)：观察measureWrong和baseline的统计结果(约等于，说明measureWrong的代码被优化掉了)
 * Benchmark                                                  Mode  Cnt   Score   Error  Units
 * unitTest.benchmark.jmh.JMHSample_08_DeadCode.baseline      avgt        0.250          ns/op
 * unitTest.benchmark.jmh.JMHSample_08_DeadCode.measureRight  avgt       18.041          ns/op
 * unitTest.benchmark.jmh.JMHSample_08_DeadCode.measureWrong  avgt        0.249          ns/op
 *
 * 为了验证JVM优化掉的代码，是否会纳入统计结果，使用普通类JvmCodeOptimizationTest进行测试
 * 发现该代码的耗时会纳入统计结果，因此官方这种结论只用于JMH本身，猜测他的原理是有效代码才纳入统计
 * 注：有效代码的定义是是否被使用，如[1]、[2]、[3]为无效代码；[4]、[5]为有效代码
 *
 * 那如何解决这种问题？
 * JMH提供了解决方案：查看JMHSample_09_Blackholes
 *
 * @Date 2020年11月8日 12:09:00
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_08_DeadCode {

    private double x = Math.PI;

    /**
     * 没有任何计算
     */
    @Benchmark
    public void baseline() {
    }

    /**
     * 没有返回结果，整个计算被消除
     */
    @Benchmark
    public void measureWrong() {
//        double r = Math.log(x);//[1]
        Math.log(x);//[2]
//        JvmCodeOptimizationTest.test();//[3]
//        print(Math.log(x));//[4]
    }

    /**
     * 有返回结果，纳入计算结果
     *
     * @return
     */
    @Benchmark
    public double measureRight() {
        return Math.log(x);//[5]
    }

    /**
     * mvn clean install
     * java -jar target/benchmarks.jar JMHSample_08 -f 1
     *
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_08_DeadCode.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
