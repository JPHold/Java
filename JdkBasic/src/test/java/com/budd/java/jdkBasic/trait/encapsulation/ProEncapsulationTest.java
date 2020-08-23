package com.budd.java.jdkBasic.trait.encapsulation;

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
            System.out.println("条件编译分支%n");
        }
    }

    private boolean switchSign = false;

    public void unConditionCompile() {
        if (switchSign) {
            System.out.println("条件编译分支%n");
        }
    }
}
