package com.jiadao.model;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

@Data
// @Table("cust_form")   // 声明了Form对象的数据表
public class CustomForm {

    @Id       // 表示该字段为一个自增长的Id,注意,是数据库表中自增!!
    private int id;

    @Column()      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 50)
    @Name
    private String name;
    @Column("name_cn")      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 50)
    private String nameCN;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 50)
    private String comment;    
    @Column("table_name")      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 50)
    private String tableName;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private int status;
    @Column
    private String columns;
    @Column
    private String html;

}
