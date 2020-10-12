package com.budd.java.jdk8.lambda;

import com.budd.java.util.Print;
import org.junit.Test;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.budd.java.util.Print.print;
import static com.budd.java.util.Print.printf;

/**
 * @author budd
 * @desc 流式Lambda入门研究
 * @since 2020/10/10 22:54
 **/
public class HelloStreamLambdaTest {

    /**
     * 使用陈述式编程和Lambda编程完成功能开发，比对可读性、易懂性
     * @Date 2020年10月10日 23:08:10
     */

    /**
     * 使用Lambda编程
     */
    @Test
    public void testConciseCode() {
        new Random(47)
                .ints(5, 20)//[1]
                .distinct()//[2]
                .limit(7)//[3]
                .sorted()//[4]
                .forEach(Print::print);//[5]

        testDeclarativeCode();
    }

    /**
     * 使用陈述式编程
     */
    @Test
    public void testDeclarativeCode() {
        Random rand = new Random(47);
        SortedSet<Integer> rints = new TreeSet<>();//对应[1]、[2]、[4]
        while (rints.size() < 7) {//对应[3]
            int r = rand.nextInt(20);//对应[1]
            if (r < 5) continue;//对应[1]
            rints.add(r);
        }
        print(rints);//对应[5]
    }

    /**
     * 创建流
     * @Date 2020年10月11日 11:20:17
     */

    /**
     * 使用Stream生成
     *
     * @Date 2020年10月11日 11:20:17
     */
    @Test
    public void testStreamCreate() {
        print("使用Stream.of()创建流");
        Stream.of("Hello ", "Stream.of() ", "create stream")
                .forEach(Print::print);

        printf("%n使用Stream.generate()创建流");
        ArrayDeque<String> generateDeque = new ArrayDeque<>(Arrays.asList("Hello ", "Stream.generate() ", "create stream"));
        Stream.generate(() -> generateDeque.poll())
                .limit(3)
                .forEach(Print::print);
    }


    /**
     * 随机流
     *
     * @Date 2020年10月11日 11:21:37
     */
    public <T> void show(Stream<T> stream) {
        stream.limit(3)
                .forEach(Print::print);
        print("++++++++");
    }

    @Test
    public void testRandomStream() {
        Random random = new Random(47);
        //无限制，以[当前类型最大值(负数),当前类型最大值(正数)]为范围
        show(random.ints().boxed());

        //控制值的上下限范围
        show(random.ints(10, 20).boxed());

        //控制流大小
        show(random.ints(3).boxed());

        //控制流大小和上下限范围
        show(random.ints(3, 10, 20).boxed());
    }

    /**
     * IntStream
     *
     * @Date 2020年10月11日 12:00:58
     */
    @Test
    public void testIntStream() {
        print("求和");
        int sum = IntStream.range(0, 10).sum();
        print(sum);

        //替代传统的for循环
        printf("%n指定次数的遍历");
        IntStream.range(0, 10)
                .forEach((i) -> printf("第%s次循环", i));
    }

    /**
     * iterate反复将结果作为入参
     * 第一个参数是种子
     * 第二个参数是操作方法
     * <p>
     * 第一次执行，将初始种子：0传入到i
     * 之后执行，将操作方法的结果存储起来，作为下次操作方法的入参
     *
     * @Date 2020年10月12日 13:25:22
     */
    int x = 1;

    Stream<Integer> numbers() {
        return Stream.iterate(0, i -> {
            int result = x + i;
            x = i;
            return result;
        });
    }

    @Test
    public void testIterate() {
        numbers().skip(0).limit(10).forEach(Print::print);
    }

    /**
     * 建造者模式builder()
     * 将数据加入:
     * accept(ele)和add(ele)都能加入
     * <p>
     * build()之后，不能再添加元素了，不然报错：
     * java.lang.IllegalStateException
     *
     * @Date 2020年10月12日 13:44:09
     */
    @Test
    public void testBuilder() {
        Stream.Builder<Object> builder = Stream.builder();
        Stream.of("Hello ", "Stream ", "builder")
                .forEach((x) -> {
                    builder.accept(x);
//                    builder.add(x);
                });

        builder.build().forEach(Print::print);
        builder.add("end");
    }

    /**
     * 使用Arrays的静态方法stream()创建流
     *
     * @Date 2020年10月12日 20:45:28
     */
    @Test
    public void testArrays() {
        Arrays.stream(new String[]{"1", "2", "3"})
                .forEach(Print::print);

        //从[x,y)截取
        Arrays.stream(new String[]{"1", "2", "3", "4", "5", "6"}, 3, 6)
                .forEach(Print::print);

    }

    /**
     * 正则表达式和splitAsStream()将字符串转化成流
     *
     * @Date 2020年10月12日 21:14:14
     */
    @Test
    public void testPatternSplitAsStream() {
        String lineStr = "Hello Pattern SplitAsStream.";

        Pattern.compile("[ .,?]+")
                .splitAsStream(lineStr)
                .forEach(Print::print);
    }

}
