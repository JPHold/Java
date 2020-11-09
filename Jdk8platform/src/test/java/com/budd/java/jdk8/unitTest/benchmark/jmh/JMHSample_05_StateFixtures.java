package com.budd.java.jdk8.unitTest.benchmark.jmh;

import static com.budd.java.util.Print.*;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @Date 2020年11月7日 11:47:50
 * http://hg.openjdk.java.net/code-tools/jmh/file/2be2df7dbaf8/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_05_StateFixtures.java
 */
@State(Scope.Thread)
public class JMHSample_05_StateFixtures {

    double x;

    /**
     * 每个@State类都可以拥有状态管理的方法(fixture methods),使用@Setup标识(与JUnit的@Before注解一样功能，TestNG也有同样功能(未去验证))
     * 这些方法在@State类中才生效，所在线程与@State类在同个基准线程
     */
    /*
     * 准备状态属性
     */
    @Setup
    public void prepare() {
        x = Math.PI;
    }

    /*
     * 每一个@Benchmark的基准测试方法测试完毕后，进行结果检查
     */
    @TearDown
    public void check() {
        printf("结果：%s", x);
        assert x > Math.PI : "Nothing changed?";
    }

    /**
     * 只要执行一次，@TearDown的方法就测试通过
     */
    @Benchmark
    public void measureRight() {
        x++;
    }

    /**
     * 增加同名不同作用域的变量，每次调用都初始化该变量，因此@TearDown的方法测试不通过，报错如下：
     * <failure>
     * <p>
     * java.lang.AssertionError: Nothing changed?
     * at com.budd.java.jdk8.unitTest.benchmark.jmh.JMHSample_05_StateFixtures.check(JMHSample_05_StateFixtures.java:69)
     * at com.budd.java.jdk8.unitTest.benchmark.jmh.jmh_generated.JMHSample_05_StateFixtures_measureWrong_jmhTest.measureWrong_Throughput(JMHSample_05_StateFixtures_measureWrong_jmhTest.java:97)
     * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * at java.lang.reflect.Method.invoke(Method.java:498)
     * at org.openjdk.jmh.runner.BenchmarkHandler$BenchmarkTask.call(BenchmarkHandler.java:453)
     * at org.openjdk.jmh.runner.BenchmarkHandler$BenchmarkTask.call(BenchmarkHandler.java:437)
     * at java.util.concurrent.FutureTask.run(FutureTask.java:266)
     * at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
     * at java.util.concurrent.FutureTask.run(FutureTask.java:266)
     * at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
     * at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
     * at java.lang.Thread.run(Thread.java:748)
     */
    @Benchmark
    public void measureWrong() {
        double x = 0;
        x++;
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can see measureRight() yields the result, and measureWrong() fires
     * the assert at the end of the run.
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -ea -jar target/benchmarks.jar JMHSample_05 -f 1
     *    (we requested single fork; there are also other options, see -h)
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    /**
     * 不依赖集成开发工具来测试，比如丢到服务器测试，每次修改基准类，都需要进行如下
     * mvn clean install
     * java -ea -jar target/benchmarks.jar JMHSample_05_StateFixtures.java -f 1
     * <p>
     * 要注意jvmArgs是覆盖原先的参数，因此导致一个问题：编码问题，引起中文打印到控制台乱码。
     * 因此要使用jvmArgsAppend追加启动参数或者jvmArgs增加"-Dfile.encoding=UTF-8"
     *
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_05_StateFixtures.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(2)
                .forks(1)
//                .jvmArgs("-ea","-Dfile.encoding=UTF-8")
                .jvmArgsAppend("-ea")
                .build();

        new Runner(opt).run();
    }

}