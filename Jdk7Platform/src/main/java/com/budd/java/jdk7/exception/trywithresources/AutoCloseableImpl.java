package com.budd.java.jdk7.exception.trywithresources;

import static com.budd.java.util.Print.*;

public class AutoCloseableImpl implements AutoCloseable {
    String name = getClass().getSimpleName();

    public AutoCloseableImpl() {
        printf("Creating %s", name);
    }

    public void close() {
        printf("Closing %s", name);
    }
}