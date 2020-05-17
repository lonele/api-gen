package com.jiadao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class GsonTest {

    @Test
    public void test1(){
        String json2 = "[\"apple\", \"pear\", \"banana\"]";
        Gson gson2 = new Gson();
        List<String> fruitList = gson2.fromJson(json2, new TypeToken<List<String>>(){}.getType());
        System.out.println(fruitList.size());
    }

    @Test
    public void test2(){
        String json2 = "[{'key':'name','opt':'=','value':'zhangsan'},{'key':'age','opt':'>','value':'5'}]";
        Gson gson2 = new Gson();
        List<Map<String,Object>> conditionList = gson2.fromJson(json2, new TypeToken<List<Map<String,Object>>>(){}.getType());
        conditionList.forEach(map-> {
            System.out.println("==========");
            map.forEach((k,v)->{
                System.out.println(String.format("k:\t%s,v:\t%s",k,v));
            });
        });
    }


    @Test
    public void testString(){
        StringBuilder sbIndex = new StringBuilder();
        sbIndex.append("sss,sss,sss,");
        sbIndex.delete(sbIndex.length()-1,sbIndex.length());
        System.out.println(sbIndex.toString());
    }
}
