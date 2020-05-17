package com.jiadao.service;

import com.jiadao.model.CustomForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FormService {

    @Autowired
    DB db;



    @CachePut(value = "db_cust_form", key = "#form.name")
    public CustomForm createOrUpdateForm(CustomForm form){
        log.info("createOrUpdateForm:"+form.getName());
        return db.save(form);
    }


    @Cacheable(value = "db_cust_form" ,key = "#formName")
    public CustomForm getForm(String formName){
        log.info("getForm:"+formName);
        CustomForm form = db.getOne(CustomForm.class, "name", formName);
        return form;
    }

    @CacheEvict(value="db_cust_form",key="#formName")
    public int changeFormStatus(String formName){
        log.info("changeFormStatus:"+formName);
        CustomForm form = getForm(formName);
        int status = form.getStatus()==0 ? -1 : 0 ;

        int ret = db.updateSingleColumnByKey("cust_form", "status", status, "name", formName);
        return ret;
    }


    @CacheEvict(value="db_cust_form",key="#formName")
    public int deleteForm(String formName){
        log.info("deleteForm:"+formName);
        int ret = db.deleteByKey("cust_form", "name", formName);
        return ret;
    }


}
