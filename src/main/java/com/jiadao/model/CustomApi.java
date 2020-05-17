package com.jiadao.model;

import lombok.Data;

import org.nutz.dao.entity.annotation.*;

@Data
@Table("cust_api")   // 声明数据表cust_api
@TableIndexes({@Index(name="path", fields={"path"})})
public class CustomApi {

    @Id       // 表示该字段为一个自增长的Id,注意,是数据库表中自增!!
    private int id;

    @Column()      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 50)
    private String name;
    @Column() 
    @Name
    @ColDefine(width = 50)
    private String path;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 50)
    private String comment;
    @Column("form_html")      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 1000)
    private String formHtml;
    @ColDefine(width = 2000)
    @Column("gen_sql")
    private String genSql;
    @ColDefine(width = 255)
    @Column("add_sql")
    private String addSql;
    @ColDefine(width = 1000)
    @Column("select_table_info")
    private String selectTableInfo;
    @ColDefine(width = 1000)
    @Column("table_joins")
    private String tableJoins;
    @ColDefine(width = 1000)
    @Column("where_conditions")
    private String whereConditions;
    @ColDefine(width = 4000)
    @Column("result_columns")
    private String resultColumns;
    @ColDefine(width = 2000)
    @Column("result_headers")
    private String resultHeaders;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 10)
    private String type;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 10)
    private int status;


}
