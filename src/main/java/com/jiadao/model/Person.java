package com.jiadao.model;

import org.nutz.dao.entity.annotation.*;

import java.util.Date;

@Table("t_person")   // 声明了Person对象的数据表
@TableIndexes({@Index(name="age", fields={"age"})})
public class Person { // 不会强制要求继承某个类

    @Id       // 表示该字段为一个自增长的Id,注意,是数据库表中自增!!
    private int id; // @Id与属性名称id没有对应关系.

    @Name    // 表示该字段可以用来标识此对象，或者是字符型主键，或者是唯一性约束
    private String name;


    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine()
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column()      // 表示该对象属性可以映射到数据库里作为一个字段
    private Date createTime;
}