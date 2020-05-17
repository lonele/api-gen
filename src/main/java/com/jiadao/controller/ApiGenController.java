package com.jiadao.controller;

import com.google.gson.Gson;
import com.jiadao.model.AjaxResult;
import com.jiadao.model.CustomApi;
import com.jiadao.service.ApiService;
import com.jiadao.service.DB;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api-gen")
public class ApiGenController {

    @Autowired
    DB db;

    @Autowired
    ApiService apiService;

    @RequestMapping("/")
    String index() {
        return "Hello Api Gen";
    }

    /**
     * 通用新增请求
     *
     * @param table 表
     */
    @PostMapping("/createApi")
    @ResponseBody
    //public AjaxResult createApi(@RequestBody CustomApi api)
    public AjaxResult createApi(@RequestBody CustomApi api)
    {
        int ret = 0;
        String msg = "";
        // System.out.println(api.getGenSql());
        // api = db.save(api);
        api = apiService.createOrUpdateApi(api);
        if(api.getId() > 0 ){
            ret = 1;
            msg = "ok";
        }
        else{
            msg="error";
        }
        return ret == 1 ? AjaxResult.success(api.getId()) : AjaxResult.error(msg);
    }

    @GetMapping("/getApi")
    @ResponseBody
    public AjaxResult getApi(@RequestParam("apiId") String apiId)
    {
        int ret = 0;
        String msg = "";
        CustomApi api = db.getOne(CustomApi.class, "id", apiId);
        if(api != null){
            ret = 1;
            msg = "ok";
        }
        else{
            msg="error";
        }
        return ret == 1 ? AjaxResult.success(api) : AjaxResult.error(msg);
    }


    @GetMapping("/getApiByPath")
    @ResponseBody
    public AjaxResult getApiByPath(@RequestParam("path") String path)
    {
        int ret = 0;
        String msg = "";
        // CustomApi api = db.getOne(CustomApi.class, "path", path);
        CustomApi api = apiService.getApi(path);
        if(api != null){
            ret = 1;
            msg = "ok";
        }
        else{
            msg="error";
        }
        return ret == 1 ? AjaxResult.success(api) : AjaxResult.error(msg);
    }

    // @GetMapping("/getApiByPath")
    // @ResponseBody
    // public AjaxResult getApiByPath(@RequestParam("path") String path)
    // {
    //     int ret = 0;
    //     String msg = "";
    //     CustomApi api = db.getOne(CustomApi.class, "path", path);
    //     if(api != null){
    //         ret = 1;
    //         msg = "ok";
    //     }
    //     else{
    //         msg="error";
    //     }
    //     return ret == 1 ? AjaxResult.success(api) : AjaxResult.error(msg);
    // }

    @GetMapping("/deleteApi")
    @ResponseBody
    public AjaxResult deleteApi(@RequestParam("apiPath") String apiPath)
    {
        System.out.println("deleteApi apiPath ==="+apiPath);
        // int ret = db.deleteByKey("cust_api", "id", apiId);
        int ret = apiService.deleteApi(apiPath);
        System.out.println("deleteApi ret ==="+ret);
        return ret == 1 ? AjaxResult.success() : AjaxResult.error();
    }

    @GetMapping("/changeApiStatus")
    @ResponseBody
    public AjaxResult changeApiStatus(@RequestParam("apiPath") String apiPath)
    {
        System.out.println("changeApiStatus apiPath ==="+apiPath);
        // int ret = db.deleteByKey("cust_api", "id", apiId);
        int ret = apiService.changeApiStatus(apiPath);
        System.out.println("changeApiStatus ret ==="+ret);
        return ret == 1 ? AjaxResult.success() : AjaxResult.error();
    }

    private static final String api_where_condition_json="{'name':{'column':'name','name':'api名称','opt':'like'},"+
                                        "'path':{'column':'path','name':'api地址','opt':'like'}}";

    @PostMapping("/getApiList")
    @ResponseBody
    public AjaxResult getApiList(
        @RequestParam(value="pageIndex",defaultValue = "1") int pageIndex,
        @RequestParam(value="pageSize",defaultValue = "20") int pageSize,
        @RequestParam("whereConditions") String whereConditionsStr,
        @RequestParam("jsonParam") String jsonStr)
    {
        String sql = "select id,name,path,comment,type,gen_sql,add_sql,status,where_conditions from cust_api";
        //数据库中的查询参数
        Map<String,Map<String,String>> whereConditions =  gson.fromJson(whereConditionsStr, Map.class);
        
        //实际传入的查询参数
        Map<String,String> conditionMap = gson.fromJson(jsonStr, Map.class);
        if(conditionMap.size()>0){
            sql += " where 1=1 ";
            for(Map.Entry<String,String> entry : conditionMap.entrySet()){
                String columnName = entry.getKey();
                Map<String,String> columnMap = whereConditions.get(columnName);
                if(columnMap == null){
                    log.info("param "+columnName+" ignore because not defined");
                    continue;
                }
                if(!StringUtils.isEmpty( String.valueOf( entry.getValue()) )){
                    sql += " and "+ columnMap.get("column") + " "+ columnMap.get("opt")+ " @"+columnName;
                }
                else{
                    log.info("param "+entry.getKey()+" ignore because param value is emtpy");
                }
            }
        }
        sql += " order by id desc";
        List<Record> apiList = db.queryListBySqlMap(pageIndex,pageSize,sql, conditionMap);
        AjaxResult result = AjaxResult.success(apiList);
        long totalCount = db.queryCountBySqlMap(sql, conditionMap);
        result.put("recordsTotal", totalCount);
        result.put("recordsFiltered", totalCount);
        return result;
    }

    Gson gson = new Gson();



    @RequestMapping(value = "/queryList",method = RequestMethod.POST)
    @ResponseBody
    List<Record> queryList(@RequestParam("genSql") String genSql,
                            @RequestParam("addSql") String addSql,
                            @RequestParam("whereConditions") String whereConditionsStr,
                            @RequestParam("jsonParam") String jsonStr){
        System.out.println(jsonStr);
        System.out.println(genSql);
        System.out.println(addSql);
        if(genSql.indexOf("where") != -1){
            genSql = genSql.substring(0, genSql.indexOf("where"));
        }
        //String sql = genSql;
        //传入可以做查询条件的参数
        Map<String,Map<String,String>> whereConditions = gson.fromJson(whereConditionsStr, Map.class);
        
        //实际传入的查询参数
        Map<String,String> conditionMap = gson.fromJson(jsonStr, Map.class);
        if(conditionMap.size()>0){
            genSql += " where 1=1 ";
            for(Map.Entry<String,String> entry : conditionMap.entrySet()){
                String columnName = entry.getKey();
                Map<String,String> columnMap = whereConditions.get(columnName);
                if(columnMap == null){
                    log.info("param "+columnName+" ignore because not defined");
                    continue;
                }
                //根据设定的参数和实际传入的参数，拼接sql语句
                if(!StringUtils.isEmpty( String.valueOf( entry.getValue()) )){
                    genSql += " and "+ columnMap.get("column") + " "+ columnMap.get("opt")+ " @"+columnName;
                }
                else{
                    log.info("param "+entry.getKey()+" ignore because param value is emtpy");
                }
            }
            log.info("sql with where:"+genSql);
        }
        //拼接orderby 等结果
        genSql += addSql;
        log.info("sql with addSql:"+genSql);
        return db.queryListBySqlMap(1,20,genSql,conditionMap);

    }

    @RequestMapping(value = "/fetch",method = RequestMethod.POST)
    @ResponseBody
    AjaxResult fetch(@RequestParam("genSql") String sql,
                    
                    @RequestParam("id") String id){

        
        log.info("api sql",sql);
        Map<String,String> conditionMap = new HashMap<>();
        conditionMap.put("id", id);
    
        List<Record> data = db.queryListBySqlMap(1,1,sql,conditionMap);
        if(data!=null && data.size() ==1){
            AjaxResult result = AjaxResult.success(data.get(0));
            return result;
        }
        
        return AjaxResult.error("not found");

    }


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    AjaxResult save(@RequestParam("tableName") String tableName,@RequestParam("jsonParam") String jsonStr){
        
        Map<String,String> paramsMap = gson.fromJson(jsonStr, Map.class);
        if(paramsMap == null || paramsMap.size() == 0){
            return AjaxResult.error("params not found:");
        }
        if( paramsMap.get("id")!=null){
            paramsMap.remove("id");
        }
        int ret = db.save(tableName,paramsMap);
        return ret > 0 ? AjaxResult.success() : AjaxResult.error("save error");
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    AjaxResult edit(@RequestParam("tableName") String tableName,@RequestParam("jsonParam") String jsonStr){
        
        Map<String,String> paramsMap = gson.fromJson(jsonStr, Map.class);
        if(paramsMap == null || paramsMap.size() == 0){
            return AjaxResult.error("params not found:");
        }
        String id = paramsMap.get("id");
        if(paramsMap.size()==0 ||  StringUtils.isEmpty(id)){
            return AjaxResult.error("need param id") ;
        }
        int ret = db.updateByKey(tableName,paramsMap,"id",id);
        return ret > 0 ? AjaxResult.success() : AjaxResult.error("save error");
    }

    @RequestMapping(value = "/loadById",method = RequestMethod.POST)
    @ResponseBody
    AjaxResult loadById(@RequestParam("tableName") String tableName,@RequestParam("id") String id){
        
        Record data = db.getOne(tableName, "id", id);
        return data !=null ? AjaxResult.success(data) : AjaxResult.error("save error");
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    AjaxResult deleteById(@RequestParam("tableName") String tableName,
                        @RequestParam("id") String id,
                        @RequestParam(value="deleteFlag",defaultValue="true") boolean deleteFlag){
        int ret = -1;
        if(deleteFlag){
            //物理删除
            ret = db.deleteByKey(tableName,"id",id);
        }
        else{
            //逻辑删除,修改delete_flag字段的值为-1
            ret = db.updateSingleColumnByKey(tableName, "delete_flag", "-1", "id", id);
        }
        return ret > 0 ? AjaxResult.success() : AjaxResult.error("affect rows:"+ret);
    }

    
}
