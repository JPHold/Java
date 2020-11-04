// validating/Inverter3.java
package com.budd.java.jdk8.unitTest.junit.tdd;

import static java.lang.Character.*;

public class Inverter3 implements StringInverter {
    public String invert(String str) {
        if (str.length() > 30)
            throw new RuntimeException("argument too long!");
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            result += isUpperCase(c) ?
                    toLowerCase(c) :
                    toUpperCase(c);
        }
        return result;
    }
}
