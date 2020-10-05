package com.budd.java.jdk8.lambda.methodreference;

import static com.budd.java.util.Print.print;
import static com.budd.java.util.Print.printf;

public class MethodReferences {
    public static void hello(String name) { // [3]
        System.out.println("Hello, " + name);
    }

    public static class Description {
        String about;

        public Description(String desc) {
            about = desc;
        }

        public void help(String msg) { // [4]
            printf("%s %s", about, msg);
        }
    }

    public static class Helper {
        public static void assist(String msg) { // [5]
            print(msg);
        }
    }
}
