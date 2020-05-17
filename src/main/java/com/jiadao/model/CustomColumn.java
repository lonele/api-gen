package com.jiadao.model;


import org.nutz.dao.entity.annotation.*;

// @Table("cust_column")   // 声明了Person对象的数据表
// @TableIndexes({@Index(name="column_name", fields={"tableId","name","nameCN"})})
public class CustomColumn {

    @Column("table_id")      // 表示该对象属性可以映射到数据库里作为一个字段
    private int tableId; // @Id与属性名称id没有对应关系.

    @Column()      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 50)
    private String name;
    @Column("name_cn")      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 50)
    private String nameCN;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 50)
    private String comment;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    @ColDefine(width = 10)
    private String type;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private int length;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private String dict;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private boolean pk;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private String index;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private boolean nullable;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private boolean insertable;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private boolean editable;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private boolean listable;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private boolean queryable;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private String queryType;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private String formType;
    @Column      // 表示该对象属性可以映射到数据库里作为一个字段
    private int sort;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
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

    public boolean isPk() {
        return pk;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }


	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isInsertable() {
        return insertable;
    }

    public void setInsertable(boolean insertable) {
        this.insertable = insertable;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isListable() {
        return listable;
    }

    public void setListable(boolean listable) {
        this.listable = listable;
    }

    public boolean isQueryable() {
        return queryable;
    }

    public void setQueryable(boolean queryable) {
        this.queryable = queryable;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }


    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
