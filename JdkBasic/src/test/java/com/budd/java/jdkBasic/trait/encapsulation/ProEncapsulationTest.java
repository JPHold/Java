package com.budd.java.jdkBasic.trait.encapsulation;

import static com.budd.java.util.Print.printf;

/**
 * @author budd
 * @desc 封装-进阶研究
 * @since 2020/8/23 11:38
 **/
public class ProEncapsulationTest {

    /**
     * 实现条件编译
     */
    private static final boolean SWITCH_SIGN = false;

    public void conditionCompile() {
        if (SWITCH_SIGN) {
            printf("条件编译分支");
        }
    }

    private boolean switchSign = false;

    public void unConditionCompile() {
        if (switchSign) {
            printf("条件编译分支");
        }
    }
}
