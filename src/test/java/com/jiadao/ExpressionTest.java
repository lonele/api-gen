package com.jiadao;

import java.util.HashMap;
import java.util.Map;

import com.jiadao.util.TemplateUtil;

import org.junit.Test;

public class ExpressionTest {

    @Test
    public void test(){
        String greetingExp = "Hello,${uid},${name},${sex}";                                 
        Map map = new HashMap();
        map.put("uid", "111");
        map.put("name", "张三");
        map.put("money", String.format("%.2f", 10.155));
        map.put("point", 10);

        String result = TemplateUtil.processTemplate(greetingExp, map);
        // String result = TemplateUtil.simpleTemplate(greetingExp, map,"#{null}");
        System.out.println(result);
        
    }
}
