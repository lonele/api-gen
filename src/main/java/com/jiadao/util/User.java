package com.jiadao.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import lombok.Data;

@Data
public class User{

    private int id;
    private String name;
    private String dept;
    private String[] depts;
    private String[] rolds;

    public User(String id,String name,String dept,String depts,String roles){
        this.id = NumberUtils.toInt(id);
        this.name = name;
        this.dept = dept;
        if(StringUtils.isNotBlank(depts)){
            this.depts =  depts.split(",");
        }
        if(StringUtils.isNotBlank(roles)){
            this.rolds = roles.split(",");
        }
    }

    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap();
        map.put("uid", this.id);
        map.put("name", this.name);
        map.put("dept", this.dept);
        return map;
    }
}