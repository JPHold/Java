package com.budd.java.jdk8.lambda;

import org.junit.Test;

import static com.budd.java.util.Print.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author budd
 * @desc List-Lambda表达式入门研究
 * @since 2020/4/1 20:57
 **/
public class HelloListLambdaTest {

    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 5555, 22),
            new Employee("李四", 6666, 23),
            new Employee("王五", 7777, 24),
            new Employee("黄六", 8888, 25),
            new Employee("田七", 9999, 26)
    );

    /**
     * start:{不采用stream()}
     */
    /**
     * HJP 2018年1月1日22:06:18
     * 使用FunctionalInterface完成如下：
     * 查找工资大于7000
     */
    @Test
    public void testLambdaFilter() {
        print("----工资大于7000");
        List<Employee> list2 = filterEmployees(employees, (e) -> e.getSalary() > 7000);
        list2.forEach(System.out::println);
    }

    // HJP 2018年1月1日22:10:16
    public List<Employee> filterEmployees(List<Employee> employees, MyPredicate<Employee> mp) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if (mp.whereCompare(employee)) {
                list.add(employee);
            }
        }
        return list;
    }
    /**
     * end:{不采用stream()}
     */

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * start:{采用stream()}
     */
    /**
     * HJP 2018年1月1日22:50:28
     * 采用filter完成如下
     * 查找工资大于7000
     */
    @Test
    public void testStreamFilter() {
        employees.stream()
                .filter((e) -> e.getSalary() > 7000)
                .forEach(System.out::println);
    }

    /**
     * HJP 2018年1月1日22:54:13
     * 采用limit完成如下
     * 限制条数
     */
    @Test
    public void testStreamLimit() {
        employees.stream()
                .limit(2)
                .forEach(System.out::println);
    }

    /**
     * HJP 2018年1月1日22:57:46
     * 采用map完成如下
     * 输出指定内容(指定属性)
     */
    @Test
    public void testStreamMap() {
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    /**
     * end:{采用stream()}
     */
}
