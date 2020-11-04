// validating/tests/DynamicStringInverterTests.java
package com.budd.java.jdk8.unitTest.junit.tdd;

import static com.budd.java.util.Print.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProTddTest {
    // Combine operations to prevent code duplication:
    Stream<DynamicTest> testVersions(String id,
                                     Function<StringInverter, String> test) {
        List<StringInverter> versions = Arrays.asList(
                new Inverter1(), new Inverter2(),
                new Inverter3(), new Inverter4());
        return DynamicTest.stream(
                versions.iterator(),
                inverter -> inverter.getClass().getSimpleName(),
                inverter -> {
                    printf("%s：%s", inverter.getClass().getSimpleName(), id);
                    try {
                        if (test.apply(inverter) != "fail")
                            print("Success");
                    } catch (Exception | Error e) {
                        printf("Exception: %s", e.getMessage());
                    }
                }
        );
    }

    String isEqual(String lval, String rval) {
        if (lval.equals(rval))
            return "success";
        printf("FAIL: %s != %s", lval, rval);
        return "fail";
    }

    @BeforeAll
    static void startMsg() {
        print(">>> Starting DynamicStringInverterTests <<<");
    }

    @AfterAll
    static void endMsg() {
        print(">>> Finished DynamicStringInverterTests <<<");
    }

    /**
     * 使用四个字符串反转实现类，比较长文本反转与源长文本
     *
     * @return
     * @Date 2020年11月4日 20:29:50
     */
    @TestFactory
    Stream<DynamicTest> testLongTextInversion() {
        String in = "Exit, Pursued by a Bear.";
        String out = "eXIT, pURSUED BY A bEAR.";
        return testVersions(
                "Basic inversion (should succeed)",
                inverter -> isEqual(inverter.invert(in), out)
        );
    }

    /**
     * 使用四个字符串反转实现类，比较短文本反转与源短文本
     *
     * @return
     * @Date 2020年11月4日 20:29:56
     */
    @TestFactory
    Stream<DynamicTest> testShortTextInversion() {
        return testVersions(
                "Basic inversion (should fail)",
                inverter -> isEqual(inverter.invert("X"), "X"));
    }

    /**
     * 使用四个字符串反转实现类，比较字符是否在允许字符集合内
     *
     * @return
     * @Date 2020年11月4日 20:45:31
     */
    @TestFactory
    Stream<DynamicTest> testDisallowedCharacters() {
        String disallowed = ";-_()*&^%$#@!~`0123456789";
        return testVersions(
                "Disallowed characters",
                inverter -> {
                    String result = disallowed.chars()
                            .mapToObj(c -> {
                                String cc = Character.toString((char) c);
                                try {
                                    inverter.invert(cc);
                                    return "";
                                } catch (RuntimeException e) {
                                    return cc;
                                }
                            }).collect(Collectors.joining(""));
                    if (result.length() == 0)
                        return "success";
                    printf("Bad characters: %s", result);
                    return "fail";
                }
        );
    }

    /**
     * 使用四个字符串反转实现类，比较字符是否在允许字符集合内
     *
     * @return
     * @Date 2020年11月4日 20:49:37
     */
    @TestFactory
    Stream<DynamicTest> allowedCharacters() {
        String lowcase = "abcdefghijklmnopqrstuvwxyz ,.";
        String upcase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ,.";
        return testVersions(
                "Allowed characters (should succeed)",
                inverter -> {
                    assertEquals(inverter.invert(lowcase), upcase);
                    assertEquals(inverter.invert(upcase), lowcase);
                    return "success";
                }
        );
    }

    /**
     * 使用四个字符串反转实现类，比较长文本是否超过30
     *
     * @return
     * @Date 2020年11月4日 20:51:49
     */
    @TestFactory
    Stream<DynamicTest> lengthNoGreaterThan30() {
        String str = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        assertTrue(str.length() > 30);
        return testVersions(
                "Length must be less than 31 (throws exception)",
                inverter -> inverter.invert(str)
        );
    }

    /**
     * 使用四个字符串反转实现类，比较长文本是否没超过30
     *
     * @return
     * @Date 2020年11月4日 20:52:14
     */
    @TestFactory
    Stream<DynamicTest> lengthLessThan31() {
        String str = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        assertTrue(str.length() < 31);
        return testVersions(
                "Length must be less than 31 (should succeed)",
                inverter -> inverter.invert(str)
        );
    }
}
