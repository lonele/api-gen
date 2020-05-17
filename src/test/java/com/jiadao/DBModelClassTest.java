package com.jiadao;


import com.jiadao.model.Person;
import com.jiadao.service.DB;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})// 指定启动类
@Slf4j
public class DBModelClassTest {

    @Autowired
    DB db;

    Class<Person> mocelClass = Person.class;

    /**
     * 测试添加数据
     */
    @Test
    public void testAdd() {
        // log.info("test add");
//        String person1 = "{'name':'lisi','age':'15','createTime':'2019-10-12 12:12:12'}";
//        Person p1 = (Person)db.save("person",person1);
//        log.info(""+p1.getId());

        String person1 = "{'name':'wangwu','age':'25','createTime':'2019-10-12 12:12:12'}";
        Person p1 = db.save(mocelClass,person1);
        // log.info(""+p1.getId());
        TestCase.assertTrue(p1.getId()>0);
    }



    /**
     * 测试查询条件
     */
    @Test
    public void testQuery() {
        String con = "[{'key':'name','opt':'=','value':'zhangsan'},{'key':'age','opt':'>','value':'5'}]";
        con = "[{'key':'name','opt':'=','value':'zhangsan'}]";
        con = "[{'key':'age','opt':'>','value':'15'}]";
        con = "[{'key':'createTime','opt':'<','value':'2019-12-12 12:12:12'}]";
        List<Person> list = db.getList(mocelClass,con);
        System.out.println("count:"+list.size());
        list.forEach(person -> {
            log.info("person:"+person.getName());
        });

    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        String jsonData = "{'name':'zhangsi','id':2}";
        int ret = db.update(mocelClass,jsonData);
        System.out.println("ret:"+ret);

    }

    /**
     * 测试查询条件
     */
    @Test
    public void testDelete() {
        String[] ids={"1","3"};
        int ret = db.deleteByKeys(mocelClass,"id",ids);
        System.out.println("ret:"+ret);
    }

    @Before
    public void testBefore() {
        System.out.println("before");
    }

    @After
    public void testAfter() {
        System.out.println("after");
    }
}