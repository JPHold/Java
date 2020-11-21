package com.budd.java.jdk8.unitTest.benchmark;

import com.budd.java.jdk8.unitTest.benchmark.micro.MicroBenchMarkTimer;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.SplittableRandom;

import static com.budd.java.util.Print.print;
import static com.budd.java.util.Print.printf;

/**
 * @author budd
 * @desc 基准测试的入门研究
 * @since 2020/11/5 20:33
 **/
public class HelloBenchMarkTest {

    /**
     * 微基准测试
     * 很多开发人员(包括我在内)，使用在调用前后记录时间，然后两个时间相减得出耗时
     *
     * @Date 2020年11月5日 20:34:44
     */
    @Test
    public void testMicroBenchMark() {
        long cost = MicroBenchMarkTimer.duration(() -> print("任务执行完毕"));
        printf("花费：%s (ns)", cost);
    }


    /**
     * 错误的微基准测试-1
     *
     * @Date 2020年11月5日 20:43:58
     */
    @Test
    public void testBadMicroBenchMark1() {
        long[] la = new long[250_000_000];
        printf("setAll耗时：%s", MicroBenchMarkTimer.duration(() -> Arrays.setAll(la, n -> n)));
        printf("parallelSetAll耗时：%s", MicroBenchMarkTimer.duration(() -> Arrays.parallelSetAll(la, n -> n)));
    }

    /**
     * 错误的微基准测试-2
     * 并行计算，使用同个资源，不同线程竞争，导致耗时增加
     * 使用并行计算的SplittableRandom类，耗时减少
     * @Date 2020年11月5日 20:56:32
     */
    @Test
    public void testBadMicroBenchMark2() {
        long[] la = new long[5_000_000];

        print("使用Random");
        Random random = new Random();
        printf("setAll耗时：%s", MicroBenchMarkTimer.duration(() -> Arrays.setAll(la, n -> random.nextLong())));
        printf("parallelSetAll耗时：%s", MicroBenchMarkTimer.duration(() -> Arrays.parallelSetAll(la, n -> random.nextLong())));

        printf("%n使用并行计算Random");
        SplittableRandom splittableRandom = new SplittableRandom();
        printf("setAll耗时：%s", MicroBenchMarkTimer.duration(() -> Arrays.setAll(la, n -> splittableRandom.nextLong())));
        printf("parallelSetAll耗时：%s", MicroBenchMarkTimer.duration(() -> Arrays.parallelSetAll(la, n -> splittableRandom.nextLong())));
    }


}
