package com.jiadao;


import com.jiadao.model.CustomColumn;
import com.jiadao.model.CustomTable;
import com.jiadao.service.DB;
import com.jiadao.service.TableService;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})// 指定启动类
@Slf4j
public class DBTableTest {

    @Autowired
    DB db;

    @Autowired
    TableService tableService;

    String tableName = "qj";


    /**
     * 测试添加数据
     */
    @Test
    public void testCreateTable() {

    }

    @Test
    public void testGetOneWithMany() {
        CustomTable table = db.getOneWithMany(CustomTable.class,23L,"columns");
        System.out.println(table.getName());
        System.out.println(table.getColumns().size());
    }

    @Test
    public void testTableQuery(){
        Dao dao = db.getDao();
        Sql sql = Sqls.create("select t_student.name,t_student.code ,t_score.score,t_score.exam_date \n" +
                "from t_student inner join t_score \n" +
                "on t_student.`code` = t_score.student_code\n");
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(NutMap.class));
        dao.execute(sql);
        List<NutMap> list = sql.getList(NutMap.class);
        System.out.println("lise.size==="+list.size());



    }

    // @Test
    // public void testGetTablesWithColumns(){
    //     List<CustomTable> list = db.getTablesWithColumns(new String[]{"t_student","t_score"});
    //     System.out.println("lise.size==="+list.size());
    //     for(CustomTable table : list){
    //         System.out.println("table name "+table.getName() + " namecn:"+table.getNameCN());
    //         for(CustomColumn column: table.getColumns()){
    //             // System.out.println("table name "+table.getName()+ " column name "+column.getName()+ " column type "+column.getFormType()+" remark"+column.getComment());
    //         }
    //     }
    // }


    @Test
    public void testGetTables(){
        List<String> list = tableService.getAllTables("mis");
        System.out.println("lise.size==="+list.size());
        for(String db : list){
            System.out.println(db);
        }
    }


    @Test
    public void testGetDbs(){
        List<String> list = tableService.getAllDbs();
        System.out.println("lise.size==="+list.size());
        for(String db : list){
            System.out.println(db);
        }
    }



}