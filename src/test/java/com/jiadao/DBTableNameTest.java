package com.jiadao;


import com.jiadao.service.DB;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})// 指定启动类
@Slf4j
public class DBTableNameTest {

    @Autowired
    DB db;

    String tableName = "people";

    /**
     * 测试添加数据
     */
    @Test
    public void testAdd() {
        // log.info("test add");

        // String person1 = "{'name':'wangsu','age':'25','create_time':'2019-10-12 12:12:12'}";
        // int ret = db.save(tableName,person1);
        // // log.info(""+ret);
        // TestCase.assertTrue(ret > 0);
    }



    /**
     * 测试查询条件
     */
    @Test
    public void testQuery() {
        String con = "[{'key':'name','opt':'=','value':'zhangsan'},{'key':'age','opt':'>','value':'5'}]";
        con = "[{'key':'name','opt':'=','value':'zhangsan'}]";
        con = "[{'key':'age','opt':'>','value':'15'}]";
        con = "[{'key':'create_time','opt':'<','value':'2019-12-12 12:12:12'}]";
        List<Record> list = db.getList(tableName,con);
        System.out.println("count:"+list.size());
        list.forEach(person -> {
           log.info("person:"+person.get("name"));
        });

    }


    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        String[] ids={"1","3"};
        String jsonData = "{'name':'zhangsi'}";
        int ret = db.updateByKey(tableName,jsonData,"id","1");
        System.out.println("ret:"+ret);

    }

    /**
     * 测试批量
     */
    @Test
    public void testDelete() {
        String[] ids={"1","3"};
        int ret = db.deleteByKeys(tableName,"id",ids);
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