//: net/mindview/util/Print.java
// Print methods that can be used without
// qualifiers, using Java SE5 static imports:
package com.budd.java.util;

import java.io.*;

/**
 * @author Blue Eckel JAVA编程思想
 * @since 2006年06月19日 10:38
 **/
public class Print {
    // Print with a newline:
    public static void print(Object obj) {
        System.out.println(obj);
    }

    // Print a newline by itself:
    public static void print() {
        System.out.println();
    }

    // Print with no line break:
    public static void printnb(Object obj) {
        System.out.print(obj);
    }

    // The new Java SE5 printf() (from C):
    public static void
    printf(String format, Object... args) {
        System.out.printf(format, args).println();
    }
} ///:~
