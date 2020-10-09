package com.budd.java.jdk8.lambda;

import com.budd.java.util.Print;
import org.junit.Test;

import static com.budd.java.util.Print.*;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author budd
 * @desc Lambda表达式进阶研究
 * @since 2020/10/9 10:40
 **/
public class HelloProLambdaTest {

    /**
     * @Author budd
     * @Description 函数组合
     * @Date 2020/10/9 10:51
     **/

    Function<String, String> f1 = (str) -> {
        printf("第二个执行(A替换成_):%s", str);
        return str.replace("A", "_");
    }, f2 = (str) -> {
        printf("第一个执行(从第三个位置截取):%s", str);
        return str.substring(3);
    }, f3 = (str) -> {
        printf("第三个执行(转小写):%s", str);
        return str.toLowerCase();
    };

    /**
     * @return void
     * @Author budd
     * @Description 运算操作组合
     * Function接口：compose(后一个函数结果作为前一个函数的入参)和andThen(前一个函数结果作为后一个函数的入参)
     * @Date 2020/10/9 10:52
     * @Param []
     **/
    @Test
    public void testOperationComposition() {
        Function<String, String> basicCompositionFunction = f1.compose(f2).andThen(f3);
        String result = basicCompositionFunction.apply("GO AFTER ALL AMBULANCES");
        printf("结果:%s", result);
        printf("1" + "bar".contains("bar"));
    }

    Predicate<String>
            p1 = (str) -> str.contains("bar"), p2 = (str) -> str.length() < 5, p3 = (str) -> str.contains("foo");

    /**
     * @return void
     * @Author budd
     * @Description 条件组合
     * 两组条件运算：形成( xxx and xxx ) or xxx
     * 1、p1.negate().and(p2)
     * 不包含"bar"且长度不超过5，无满足的结果
     * 2、or(p3)
     * 包含"foo"，满足的结果有：foobar、foobaz
     * @Date 2020/10/9 11:03
     * @Param []
     **/
    @Test
    public void testConditionComposition() {
        Predicate<String> predicate = p1.negate().and(p2).or(p3);

        Stream.of("bar", "foobar", "foobaz", "fongopuckey")
                .filter(predicate)
                .forEach(Print::print);
    }
}
