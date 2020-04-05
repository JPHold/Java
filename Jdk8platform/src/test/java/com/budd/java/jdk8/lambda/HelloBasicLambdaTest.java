package com.budd.java.jdk8.lambda;

import org.junit.Test;

import static com.budd.java.util.Print.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * @author budd
 * @desc Lambda表达式入门研究
 * @since 2020/3/20 13:25
 **/
public class HelloBasicLambdaTest {

    /**
     * start:{函数式接口}
     */
    //消费型接口
    @Test
    public void consumerTest() {
        consumer(10000.0, (x) -> printf("今天，我买了一台价%s元的联想电脑!!!", x));
    }

    public void consumer(double money, Consumer<Double> c) {
        c.accept(money);
    }

    //供给型接口
    @Test
    public void supplierTest() {
        supplier(5, () -> (int) (Math.random() * 100));
    }

    public void supplier(int num, Supplier<Integer> s) {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            intList.add(s.get());
        }
        print(intList);
    }

    //函数型接口
    @Test
    public void functionTest() {
        function("\t\t\t 糖衣语法-Lambda  ", (x) -> x.trim());
    }

    public void function(String str, Function<String, String> f) {
        print(f.apply(str));
    }

    //断言型接口
    @Test
    public void predicateTest() {
        predicate(Arrays.asList("HELLO", "H", "E", "LL", "O"), (x) -> x.length() >= 2);
    }

    public void predicate(List<String> list, Predicate<String> p) {
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (p.test(s))
                strList.add(s);
        }
        print(strList);
    }
    /**
     * end:{函数式接口}
     */

    /**
     * start:{创建对象}
     */
    /**
     * @author HJP
     * @date 2018年1月14日 %上午11:05:05
     * @Description 构造器引用
     */
    @Test
    public void constructorRef() {
        Supplier<Employee> sp1 = () -> new Employee();
        printf("未使用构造器引用-无参:%s\n", sp1.get());
        Supplier<Employee> sp2 = Employee::new;
        printf("使用构造器引用-无参数:%s\n", sp2.get());

        print("---------------分割线--------------");

        Function<String, Employee> f1 = (x) -> new Employee(x);
        printf("未使用构造器引用-一个参数:%s\n", f1.apply("张三"));
        Function<String, Employee> f2 = Employee::new;
        printf("使用构造器引用-一个参数：%s\n", f2.apply("张三"));

        print("---------------分割线--------------");

        BiFunction<String, Integer, Employee> bf1 = (x, y) -> new Employee(x, y);
        printf("未使用构造器引用-两个参数:%s\n", bf1.apply("张三", 30));
        BiFunction<String, Integer, Employee> bf2 = Employee::new;
        printf("使用构造器引用-两个参数:%s\n", bf2.apply("张三", 30));
    }

    /**
     * @author HJP
     * @date 2018年1月14日 %上午11:04:55
     * @Description 数组创建引用
     */
    @Test
    public void arrayTypeRef() {
        Function<Integer, String[]> f1 = (x) -> new String[x];
        printf("未使用数组创建引用:%s\n", f1.apply(10).length);

        print("---------------分割线--------------");

        Function<Integer, String[]> f2 = String[]::new;
        printf("使用数组创建引用:%s\n", f2.apply(10).length);
    }
    //end:{创建对象}

    /**
     * @author HJP
     * @date 2018年1月13日 下午6:11:18
     * @Description 方法引用Hello
     */
    /**
     * @author HJP
     * @date 2018年1月13日 %下午6:21:13
     * @Description 实例调用普通方法 实例::实例方法
     */
    @Test
    public void instanceMethod() {
        print("------------实例调用普通方法--------------");

        Consumer<String> c1 = (x) -> System.out.println(x);
        c1.accept("未使用方法引用-(x) -> System.out.println(x)");
        Consumer<String> c2 = System.out::println;
        c2.accept("使用方法引用-System.out::println");

        print("------------分割线--------------");

        Employee e1 = new Employee("张三", 15000, 29);
        Supplier<String> s1 = () -> e1.getName();
        printf("未使用方法引用-() -> e1.getName() : %s\n", s1.get());
        Supplier<String> s2 = e1::getName;
        printf("使用方法引用-e1::getName : %s\n", s2.get());
    }

    /**
     * @author HJP
     * @date 2018年1月13日 %下午6:26:55
     * @Description 类调用静态方法 类::静态方法
     */
    @Test
    public void classMethod() {
        print("------------类调用静态方法--------------");

        Comparator<Integer> c1 = (x, y) -> Integer.compare(x, y);
        printf("未使用方法引用-(x, y) -> Integer.compare(x, y): %s\n", c1.compare(1, 2));

        print("------------分割线--------------");

        Comparator<Integer> c2 = Integer::compare;
        printf("使用方法引用-Integer::compare: %s\n", c2.compare(1, 1));
    }

    /**
     * @author HJP
     * @date 2018年1月14日 %上午9:00:54
     * @Description 类调用实例方法  类::实例方法
     */
    @Test
    public void ClassInstanceMethod() {
        print("------------类调用实例方法--------------");

        BiPredicate<String, String> bp1 = (x, y) -> x.equals(y);
        printf("未使用方法引用-(x, y) -> x.equals(y)：%s\n", bp1.test("1", "2"));

        print("------------分割线--------------");

        BiPredicate<String, String> bp2 = String::equals;
        printf("使用方法引用-String::equals：%s\n", bp2.test("1", "2"));

        BiPredicate<Employee, Employee> p1 = Employee::isEquals;
        printf("使用方法引用-Employee::isEquals(自己扩展)：%s\n", p1.test(new Employee(), new Employee()));
    }

}
