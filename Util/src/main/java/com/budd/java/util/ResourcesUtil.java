package com.budd.java.util;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.*;

/**
 * 资源文件读取工具类
 * @author budd
 * @since 2019年7月8日21:50:00
 */
@Component
public class ResourcesUtil implements Serializable {

    private final long serialVersionUID = -7657898714983901418L;

    /**
     * 系统语言环境，默认为中文zh
     */
    private String language = "zh";

    /**
     * 系统国家环境，默认为中国CN
     */
    public String country = "CN";

    ResourcesUtil() {
        this("zh", "CN");
    }

    ResourcesUtil(String language, String country) {
        this.language = language;
        this.country = country;
    }

    /**
     * 通过单个key从资源文件读取内容
     *
     * @param baseName 资源文件名
     * @param key      屬性key
     * @return 属性值
     */
    public String getValue(String baseName, String key) {
        String value = getProperties(baseName, key);
        return value;
    }

    /**
     * 从资源文件中获取所有属性值
     *
     * @param baseName 资源文件名
     * @return 所有key-属性值
     */
    public Map<String,String> getKeyValueMap(String baseName) {
        Locale locale = getLocale();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);

        Map<String,String> valueMap= Maps.newHashMap();

        //获取资源文件的所有key
        Set<String> keySet = resourceBundle.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = getValue(baseName, key);
            valueMap.put(key,value);
        }
        return valueMap;
    }

    /**
     * 通过key从资源文件读取内容，并格式化
     *
     * @param baseName 资源文件名
     * @param key      屬性key
     * @param params   格式化参数
     * @return 格式化后的内容
     */
    public String getValue(String baseName, String key, Object[] params) {
        String pattern = getValue(baseName, key);
        String value = MessageFormat.format(pattern, params);
        return value;
    }

    /**
     * 获取本地化配置
     *
     * @return
     */
    private Locale getLocale() {
        Locale locale = new Locale(language, country);
        return locale;
    }

    /**
     * 根据语言、国家、资源文件名和key名字获取资源文件值
     *
     * @param baseName 资源文件名
     * @param key      屬性key
     * @return 属性值
     */
    private String getProperties(String baseName, String key) {
        String retValue = null;
        try {
            Locale locale = getLocale();
            ResourceBundle rb = ResourceBundle.getBundle(baseName, locale);
            retValue = (String) rb.getObject(key);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO 日志打印
        }
        return retValue;
    }
}
