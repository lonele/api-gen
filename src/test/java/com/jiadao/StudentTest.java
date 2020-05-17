package com.jiadao;


import com.jiadao.model.CustomTable;
import com.jiadao.service.DB;
// import com.jiadao.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})// 指定启动类
@Slf4j
public class StudentTest {

    @Autowired
    // StudentService studentService;


    @Test
    public void testQueryScore(){

        // List<Record> list = studentService.queryScore();
        // System.out.println(list.size());



    }


}