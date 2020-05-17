package com.jiadao.model;

import org.nutz.dao.entity.annotation.*;

import java.util.List;

//@Table("cust_table")   // 声明了Person对象的数据表
//@TableIndexes({@Index(name="table_name", fields={"name","nameCN"})})
public class CustomTable {

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
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private int status;
    @Many(field = "tableId")
    private List<CustomColumn> columns;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCN() {
        return nameCN;
    }

    public void setNameCN(String nameCN) {
        this.nameCN = nameCN;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<CustomColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<CustomColumn> columns) {
        this.columns = columns;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
