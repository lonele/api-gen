package com.jiadao.model;


import org.nutz.dao.entity.annotation.*;

@Table("t_blog")   // 声明了Person对象的数据表
@TableIndexes({@Index(name="title", fields={"title"})})
public class Blog {

    @Id       // 表示该字段为一个自增长的Id,注意,是数据库表中自增!!
    private int id;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private String title;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
