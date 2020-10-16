package com.budd.java.jdk8.lambda;

import com.budd.java.util.Print;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
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

    /**
     * 创建流
     * end
     */

    /*------------------------------------------------------------*/

    /**
     * 中间操作
     * @Date 2020年10月12日 22:45:41
     */

    /**
     * 跟踪调试
     * 跟调试一样，可以反复拿着某个数据进行测试
     * 其实跟Deque队列的peek一个意思：拿到数据但可以不弹出，可以多次使用
     *
     * @Date 2020年10月12日 22:46:07
     */
    @Test
    public void testPeek() {
        Stream.of("one", "two", "three", "four")
                .map(String::toLowerCase)
                .peek(Print::print)
                .map(String::toUpperCase)
                .peek(Print::print)
                .collect(Collectors.toList());
    }

    /**
     * 排序指定比较器
     * 默认是自然排序(从小到大)。Comparator.reverseOrder()是倒序排序
     *
     * @Date 2020年10月12日 23:01:51
     */
    @Test
    public void testComparator() {
        Stream.of("2", "3", "1")
                .sorted(Comparator.reverseOrder())
                .forEach(Print::print);
    }

    /**
     * 从2开始到(long) Math.sqrt(n)的范围内,记作i。n取余i都不等于0，则为质数
     * <p>
     * 可以看到校验质数，除数并不用到校验的数值，只需到(long) Math.sqrt(n)，这个颠覆我的认知，印象中老师没教过(或者是我没听课,嘻嘻嘻)
     * <p>
     * 只要noneMatch存在true，则会退出rangeClosed
     *
     * @Date 2020年10月13日 21:06:13
     */
    public Boolean isPrime(long n) {
        printf("%n当前设置的校验数和上限：%s, %s", n, (long) Math.sqrt(n));
        return LongStream.rangeClosed(2, (long) Math.sqrt(n))
                .noneMatch(i -> {
                    print("当前除数：" + i);
                    return n % i == 0;
                });
    }

    public LongStream filterNumbers() {
        return LongStream.iterate(2, i -> i + 1)
                .filter(this::isPrime);
    }

    @Test
    public void testFilter() {
        filterNumbers().limit(10)
                .forEach(x -> printf("质数：" + x));

    }

    /**
     * 测试map()，修改数据，甚至修改数据类型、转变流
     *
     * @Date
     */

    class MapTran {
        private String x;

        public MapTran(String x) {
            this.x = x;
        }

        @Override
        public String toString() {
            return String.format("MapTran:%s", x);
        }
    }

    @Test
    public void testMap() {

        printf("修改数据");
        Stream.of("1", "2", "3")
                .map(x -> Integer.parseInt(x) - 1)
                .forEach(Print::print);

        printf("%n基本类型转成类");
        Stream.of("1", "2", "3")
                .map(MapTran::new)
                .forEach(Print::print);

        printf("%n转变流");
        Stream.of("1", "2", "3")
                .mapToDouble(Double::parseDouble)
                .forEach(Print::print);
    }

    /**
     * flatMap() 与map()一样功能，只是转变出来的流，会扁平化，输出流中元素，而不是流
     *
     * @Date 2020年10月14日 21:00:19
     */
    @Test
    public void testFlatMap() {
        print("使用map");
        Stream.of("1", "2", "3")
                .map(i -> Stream.of("map1", "map2", "map3"))
                .forEach(Print::print);

        printf("%n使用flatMap");
        Stream.of("1", "2", "3")
                .flatMap(i -> Stream.of("map1", "map2", "map3"))
                .forEach(Print::print);

        printf("%n使用flatMapToInt");
        Function<Integer, Random> randomFunction = Random::new;
        Random random = randomFunction.apply(47);
        Stream.of(1, 2, 3)
                .flatMapToInt(i -> IntStream.concat(random.ints(0, 100).limit(i), IntStream.of(-1)))
                .forEach(Print::print);

    }

    /**
     * Optional:解决空指针问题，不会报错，封装友好提示
     *
     * @Date 2020年10月14日 21:21:34
     */
    /**
     * 基本例子
     *
     * @Date 2020年10月14日 21:23:08
     */
    @Test
    public void testSingleOptional() {
        //模拟空流
        Optional<Object> anyOptional = Stream.empty()
                .findAny();
        if (anyOptional.isPresent()) {
            Object o = anyOptional.get();
        } else {
            print("findAny为null");
        }
    }

    /**
     * Optional的空判断函数
     *
     * @Date 2020年10月14日 21:55:38
     */
    void executeOptionalMethod(String testName, Consumer<Optional<String>> cos) {
        printf("%n当前进行%s", testName);
        cos.accept(Stream.of("元素值").findFirst());
        cos.accept(Stream.<String>empty().findFirst());
    }

    @Test
    public void testOptionalMethod() {
        //如果存在则输出元素
        executeOptionalMethod("ifPresent(Consumer)", (x) -> x.ifPresent(Print::print));
        //如果不存在则直接返回值
        executeOptionalMethod("orElse(otherObject)", (x) -> print(x.orElse("orElse")));
        //如果不存在则通过函数，制作出一个返回值
        executeOptionalMethod("orElseGet(Supplier)", (x) -> print(x.orElseGet(() -> {
            String result = "orElseGet";
            result = result.toUpperCase();
            return result;
        })));

        executeOptionalMethod("orElseThrow(Supplier)", (x) -> x.orElseThrow(NullPointerException::new));
    }

    /**
     * 创建Optional
     *
     * @Date 2020年10月14日 22:17:48
     */
    @Test
    public void testCreateOptional() {

        Consumer<Optional> nullConsumer = (x) -> print(x.orElse("元素为空"));

        print("Optional.empty()");
        Optional<Object> emptyOptional = Optional.empty();
        nullConsumer.accept(emptyOptional);

        printf("%nOptional.empty()");
        Optional<String> ofOptional = Optional.of("我有值");
//        Optional<String> ofOptional = Optional.of(null);//执行报错：java.lang.NullPointerException

        nullConsumer.accept(ofOptional);

        printf("%nOptional.ofNullable()");
        String ofNullableStr = "我有值";
        Optional<String> ofNoNullableOptional = Optional.ofNullable(ofNullableStr);
        nullConsumer.accept(ofNoNullableOptional);

        ofNullableStr = null;
        Optional<String> ofNullableOptional = Optional.ofNullable(ofNullableStr);
        nullConsumer.accept(ofNullableOptional);
    }

    /**
     * Optional的操作函数
     *
     * @Date 2020/10/16 11:07
     **/
    public void filterOptional(String operationName, Predicate<String> predicate) {
        printf("当前Optional的操作函数：%s, 结果：%s", operationName, Stream.of("1", "2", "3")
                .findAny()
                .filter(predicate));
    }

    public void mapOptional(String operationName, Function<String, String> function) {
        printf("当前Optional的操作函数：%s, 结果：%s", operationName, Stream.of("hello", "optional", "map")
                .findAny()
                .map(function));
    }

    public void flatMapOptional(String operationName, Function<String, Optional<String>> function) {
        printf("当前Optional的操作函数：%s, 结果：%s", operationName, Stream.of("hello", "optional", "map")
                .findAny()
                .flatMap(function));
    }

    @Test
    public void testOptionalOperation() {
        //filter：
        // 数据为空则直接返回Optional.empty
        // 数据不为空则应用Predicate，如果验证结果为false则返回Optional.empty
        print("filter");
        filterOptional("filter(始终true)", (x) -> true);
        filterOptional("filter(始终false)", (x) -> false);

        //map:
        // 数据为空则直接返回Optional.empty
        // 数据不为空则应用Function，转变数据，自动使用Optional封装结果
        printf("%nmap");
        mapOptional("map", String::toUpperCase);

        //flatMap:
        // 数据为空则直接返回Optional.empty
        // 数据不为空则应用Function，转变数据，跟map有一旦不同：需要对结果进行Optional封装，Optional不会自动帮我们封装
        printf("%nflatMap");
        flatMapOptional("flatMap", (x) -> Optional.ofNullable(x.toUpperCase()));
    }

    /**
     * end
     * Optional:解决空指针问题，不会报错，封装友好提示
     *
     * @Date 2020/10/16 12:00
     *
     */

    /**
     * 输出流
     *
     * @Date 2020/10/16 15:00
     **/
    /**
     * @return void
     * @Author budd
     * @Description 测试toArray()
     * @Date 2020/10/16 15:11
     * @Param []
     **/
    @Test
    public void outputToArray() {
        int[] repeatedValues = new Random(47).ints(0, 100).limit(3).toArray();

        Arrays.stream(repeatedValues)
                .forEach(Print::print);
    }

    /**
     * @return void
     * @Author budd
     * @Description 测试循环
     * @Date 2020/10/16 15:20
     * @Param []
     **/
    @Test
    public void outputForEach() {
        int[] repeatedValues = new Random(47).ints(0, 100).limit(100).toArray();

        int resultSize = 14;
        print("forEach");
        Arrays.stream(repeatedValues)
                .limit(resultSize)
                .forEach(x -> System.out.printf(" %s", x));

        printf("%n%nforEachOrdered");
        Arrays.stream(repeatedValues)
                .limit(resultSize)
                .forEachOrdered(x -> System.out.printf(" %s", x));

        printf("%n%nparallel+ forEach");
        Arrays.stream(repeatedValues)
                .limit(resultSize)
                .parallel()
                .forEach(x -> System.out.printf(" %s", x));
        print();
        final Set<String> threadRecords = Sets.newHashSet();
        Arrays.stream(repeatedValues)
                .limit(resultSize)
                .parallel()
                .forEach(x -> {
                            System.out.printf(" %s[%s]", x, Thread.currentThread().getName());
                            threadRecords.add(Thread.currentThread().getName());
                        }
                );
        //并行的线程数：8--[ForkJoinPool.commonPool-worker-7, ForkJoinPool.commonPool-worker-1
        // , ForkJoinPool.commonPool-worker-2, main, ForkJoinPool.commonPool-worker-5
        // , ForkJoinPool.commonPool-worker-6, ForkJoinPool.commonPool-worker-3, ForkJoinPool.commonPool-worker-4]
        //公司电脑是4核，8线程
        printf("%n并行的线程数：%s--%s", threadRecords.size(), threadRecords);

        //观察到结果：
        // peek打印发现，并发执行
        // forEachOrdered打印发现，单线程执行
        printf("%nparallel+ forEachOrdered");
        Arrays.stream(repeatedValues)
                .limit(resultSize)
                .peek((x) -> System.out.printf("  %s[%s]", x, Thread.currentThread().getName()))
                .parallel()
                .forEachOrdered(x -> System.out.printf(" %n%s[%s]", x, Thread.currentThread().getName()));
    }
}
