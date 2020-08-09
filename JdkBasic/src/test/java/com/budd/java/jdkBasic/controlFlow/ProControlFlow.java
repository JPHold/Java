package com.budd.java.jdkBasic.controlFlow;

import static com.budd.java.util.Print.*;

import org.junit.Test;

/**
 * @author budd
 * @desc 进阶控制流研究
 * @since 2020/8/9 14:52
 **/
public class ProControlFlow {

    /**
     * @return void
     * @Author budd
     * @Description 标签跳转
     * 结论：
     * 1、break outer跳转到最外层，不会继续执行循环，不会执行i++
     * 2、continue outer跳转到最外层，会继续执行循环，不会执行i++
     * @Date 2020/8/9 15:12
     * @Param []
     **/
    @Test
    public void testLabelGoto() {
        int i = 0;
        outer:
        // 此处不允许存在执行语句
        for (; true; ) { // 无限循环
            inner:
            // 此处不允许存在执行语句
            for (; i < 10; i++) {
                printf("i = %s", i);
                if (i == 2) {
                    printf("continue");
                    continue;
                }
                if (i == 3) {
                    printf("break");
                    i++; // 因跳出内层循环，循环的末尾操作：i++不会执行，因此一直会循环i=3。所以需要自增1
                    break;
                }
                if (i == 7) {
                    printf("continue outer");
                    i++;  // 因跳到最外层循环，相当于break内层循环，循环的末尾操作：i++不会执行，因此一直会循环i=7。所以需要自增1
                    continue outer;
                }
                if (i == 8) {
                    printf("break outer");
                    break outer;
                }
                for (int k = 0; k < 5; k++) {
                    if (k == 3) {
                        printf("k:continue inner");
                        continue inner;
                    }
                }
            }
        }
    }

}
