package com.budd.java.util;

import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class BaseTest<T> {

    private Class beanClass;
    private AnnotationConfigApplicationContext ctx;

    private T bean;

    protected abstract Class<T> assignBeanClass();

    @Before
    public void init() {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        bean =  ctx.getBean(assignBeanClass());
    }

    public T getBean(){
        return bean;
    }

}
