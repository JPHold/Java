package com.budd.java.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("ctxConfig")
public class ApplicationContextConfig {
    @Bean
    public ResourcesUtil resourcesUtil() {
        return new ResourcesUtil();
    }

    @Bean
    public TemplateUtil templateUtil() {
        return new TemplateUtil("/template");
    }


}
