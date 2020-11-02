// validating/tests/CountedListTest.java
// Simple use of JUnit to test CountedList.
package com.budd.java.jdk8.unitTest.junit;

import org.junit.jupiter.api.*;

import static com.budd.java.util.Print.print;
/**
 * 单元测试(JUint5.7.0)入门研究
 * @author budd
 * @since 2020年11月2日 19:59:22
 */
public class HelloJunitTest {

    @BeforeAll
    static void beforeAll() {
        print(">>> BeforeAll");
    }

    @AfterAll
    static void afterAll() {
        print(">>> afterAll");
    }

    @BeforeEach
    public void beforeEach() {
        print(" beforeEach");
    }

    @AfterEach
    public void afterEach() {
        print(" afterEach");
    }

    @Test
    public void test1() {
        print(" test1");
    }

    @Test
    public void test2() {
        print(" test2");
    }

}