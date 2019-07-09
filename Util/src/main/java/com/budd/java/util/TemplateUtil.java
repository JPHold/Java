package com.budd.java.util;

import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;

/**
 * 模板渲染引擎工具类
 * @author budd
 * @since 2019年7月9日22:15:06
 */
@Component
public class TemplateUtil {

    private String basePackagePath;
    private final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_28);

    TemplateUtil(String basePackagePath) {
        this.basePackagePath = basePackagePath;

        //指定加载模板所在的路径
        CONFIGURATION.setClassForTemplateLoading(TemplateUtil.class, basePackagePath);

        CONFIGURATION.setDefaultEncoding("UTF-8");
        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
        //数据格式配置
        CONFIGURATION.setDateFormat("yyyyMMdd");
        CONFIGURATION.setDateTimeFormat("yyyyMMddHHmmss");
    }

    public String showTemplate(String templateName, Object map) {

        StringWriter sw = new StringWriter((int) (templateName.length() * 1.2));

        try {

            Template template = CONFIGURATION.getTemplate(templateName);
            template.process(map, sw);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sw.toString();
    }

    public void clearCache() {
        CONFIGURATION.clearTemplateCache();
    }
}