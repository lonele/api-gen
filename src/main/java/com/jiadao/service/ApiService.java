package com.jiadao.service;

import com.jiadao.model.CustomApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiService {

    @Autowired
    DB db;



    @CachePut(value = "db_cust_api", key = "#api.path")
    public CustomApi createOrUpdateApi(CustomApi api){
        log.info("createOrUpdateApi:"+api.getPath());
        return db.save(api);
    }


    @Cacheable(value = "db_cust_api" ,key = "#apiPath")
    public CustomApi getApi(String apiPath){
        log.info("createOrUpdateApi:"+apiPath);
        CustomApi api = db.getOne(CustomApi.class, "path", apiPath);
        return api;
    }

    @CacheEvict(value="db_cust_api",key="#apiPath")
    public int changeApiStatus(String apiPath){
        log.info("createOrUpdateApi:"+apiPath);
        CustomApi api = getApi(apiPath);
        int status = api.getStatus()==0 ? -1 : 0 ;

        int ret = db.updateSingleColumnByKey("cust_api", "status", status, "path", apiPath);
        return ret;
    }


    @CacheEvict(value="db_cust_api",key="#apiPath")
    public int deleteApi(String apiPath){
        log.info("createOrUpdateApi:"+apiPath);
        int ret = db.deleteByKey("cust_api", "path", apiPath);
        return ret;
    }


}
