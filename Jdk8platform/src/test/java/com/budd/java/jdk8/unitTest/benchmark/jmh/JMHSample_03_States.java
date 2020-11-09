/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.budd.java.jdk8.unitTest.benchmark.jmh;

import static com.budd.java.util.Print.*;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 * @Date 2020年11月7日 11:20:10
 * http://hg.openjdk.java.net/code-tools/jmh/file/2be2df7dbaf8/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_03_States.java
 */
public class JMHSample_03_States {


    /**
     * 由于JMH大量用于并发基准测试，因此为对象指定某个状态(State)来标识该对象在并发下如何共享
     * 所有基准线程都能初始化和访问状态类，@State的作用与平常写的ThreadLocals一样
     */
    @State(Scope.Thread)
    public static class ThreadState {
        volatile double x = Math.PI;
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile double x = Math.PI;
    }

    /**
     * 在形参引入@State的类即可，轻松构建多线程基准测试
     * ThreadState采取模式是Scope.Thread：每个基准线程各自拥有状态
     * @param state
     */
    @Benchmark
    public void measureUnshared(ThreadState state) {
        print(Thread.currentThread().getName());
        state.x++;
    }

    /**
     * BenchmarkState采取模式是Scope.Benchmark，所有基准线程共享一个状态，用于有状态实例在并发下的共享性能
     * @param state
     */
    @Benchmark
    public void measureShared(BenchmarkState state) {
        print(Thread.currentThread().getName());
        state.x++;
    }

    /**
     * 通过threads指定线程数来开启多线程
     * fork是进程数
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_03_States.class.getSimpleName())
                .threads(4)
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}