package com.budd.java.util.resources;

import com.budd.java.util.BaseTest;
import com.budd.java.util.ResourcesUtil;
import org.junit.Test;

import java.util.Map;

/**
 * 资源文件读取工具类测试
 * @author budd
 * @since 2019年7月8日21:50:00
 */
public class ResourcesUtilTest extends BaseTest<ResourcesUtil> {

    @Override
    protected Class<ResourcesUtil> assignBeanClass() {
        return ResourcesUtil.class;
    }

    /**
     * 通过单个key从资源文件读取内容
     */
    @Test
    public void getValue() {
        String value = getBean().getValue("hello", "key1");
        System.out.println(value);
    }

    /**
     * 从资源文件中获取所有属性值
     */
    @Test
    public void getKeyValueMap() {
        Map<String, String> valueMap = getBean().getKeyValueMap("hello");
        System.out.println(valueMap);
    }

    /**
     * 通过key从资源文件读取内容，并格式化
     */
    @Test
    public void getParamValue() {
        String value = getBean().getValue("hello", "key2", new Object[]{"value2"});
        System.out.println(value);
    }

    /**
     * format格式
     */
    @Test
    public void format(){
        //java.text.Format
        //  |-- java.text.MessageFormat
        //  |--java.text.ChoiceFormat
        //  |--java.text.NumberFormat
        //  |--java.text.DecimalFormat
        //  |--java.text.DateFormat
        //  |--java.text.SimpleDateFormat
    }
}