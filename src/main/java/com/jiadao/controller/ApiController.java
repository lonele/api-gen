package com.jiadao.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.jiadao.anno.CurrentUser;
import com.jiadao.model.AjaxResult;
import com.jiadao.model.CustomApi;
import com.jiadao.service.ApiService;
import com.jiadao.service.DB;
import com.jiadao.util.TemplateUtil;
import com.jiadao.util.User;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.nutz.dao.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
public class ApiController {

    @Autowired
    DB db;

    @Autowired
    ApiService apiService;


    Gson gson = new Gson();
    
    @RequestMapping(value = "/form/{path}",method = RequestMethod.GET)
    @ResponseBody
    String form(@PathVariable("path") String path){
        log.debug("api path",path);
        //CustomApi api = db.getOne(CustomApi.class, "path", path);
        CustomApi api = apiService.getApi(path);
        if(api == null){
            return  "api not exist:"+path;
        }
        if(api.getStatus() == -1){
            return  "api disabled :"+path ;
        }
        if(!"save".equals(api.getType())){
            return  "api type mismatch:"+api.getType();
        }
        return api.getFormHtml();
    }

    @RequestMapping(value = "/save/{path}",method = RequestMethod.POST)
    @ResponseBody
    AjaxResult save(@PathVariable("path") String path,@RequestBody Map<String,String> paramsMap){
        log.debug("api path",path);
        //CustomApi api = db.getOne(CustomApi.class, "path", path);
        CustomApi api = apiService.getApi(path);
        if(api == null){
            return AjaxResult.error("api not exist:"+path) ;
        }
        if(api.getStatus() == -1){
            return AjaxResult.error("api disabled :"+path) ;
        }
        if(!"save".equals(api.getType())){
            return AjaxResult.error("api type mismatch:"+api.getType());
        }
        String tableName = api.getGenSql();
        if(paramsMap == null || paramsMap.size() == 0){
            return AjaxResult.error("params not found:");
        }
        if( paramsMap.get("id")!=null){
            paramsMap.remove("id");
        }

        //数据库中的查询参数
        Map<String,Map<String,String>> whereConditions = gson.fromJson(api.getWhereConditions(), Map.class);
        if(whereConditions != null && whereConditions.size()>0){
            //根据实际传入的字段和定义的字段进行过滤，过滤掉非法字段
            Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
		    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> entry = iterator.next();
                String columnName = entry.getKey();
                Map<String,String> columnMap = whereConditions.get(columnName);
                if(columnMap == null  || StringUtils.isEmpty( String.valueOf( entry.getValue()) )){
                    log.info("param "+columnName+" ignore because null or not defined");
                    // paramsMap.remove(columnName);
                    iterator.remove();
                }
            }
        }

        int id = db.save(tableName,paramsMap);
        return id > 0 ? AjaxResult.success(id) : AjaxResult.error("save error");
    }


    /**
     * 删除数据，支持物理删除和逻辑删除
     * 逻辑删除,数据库必须包含delete_flag字段，将修改delete_flag字段的值修改为-1，
     * @param path
     * @param id
     * @param deleteFlag
     * @return
     */
    @RequestMapping(value = "/delete/{path}",method = RequestMethod.GET)
    @ResponseBody
    AjaxResult delete(@PathVariable("path") String path,
                    @RequestParam(value="id") String id,
                    @RequestParam(value="deleteFlag",defaultValue="true") boolean deleteFlag){
        log.debug("api path",path);
        //CustomApi api = db.getOne(CustomApi.class, "path", path);
        CustomApi api = apiService.getApi(path);
        if(api == null){
            return AjaxResult.error("api not exist:"+path) ;
        }
        if(api.getStatus() == -1){
            return AjaxResult.error("api disabled :"+path) ;
        }
        if(!"delete".equals(api.getType())){
            return AjaxResult.error("api type mismatch:"+api.getType());
        }
        if(StringUtils.isEmpty(id)){
            return AjaxResult.error("id must be contains in params "+api.getType());
        }
        String tableName = api.getGenSql();
        int ret = -1;
        // Map<String,String> jsonData = gson.fromJson(jsonStr, Map.class);
        if(deleteFlag){
            //物理删除
            ret = db.deleteByKey(tableName,"id",id);
        }
        else{
            //逻辑删除,修改delete_flag字段的值为-1
            ret = db.updateSingleColumnByKey(tableName, "delete_flag", "-1", "id", id);
        }
        return ret > 0 ? AjaxResult.success("ok") : AjaxResult.error("affect rows:"+ret);
    }

    @RequestMapping(value = "/edit/{path}",method = RequestMethod.POST)
    @ResponseBody
    AjaxResult edit(@PathVariable("path") String path,
                    //@PathVariable("id") String id,
                    @RequestBody Map<String,String> paramsMap){
        log.debug("api path"+path);
        //CustomApi api = db.getOne(CustomApi.class, "path", path);
        CustomApi api = apiService.getApi(path);
        if(api == null){
            return AjaxResult.error("api not exist:"+path) ;
        }
        if(api.getStatus() == -1){
            return AjaxResult.error("api disabled :"+path) ;
        }
        if(!"edit".equals(api.getType())){
            return AjaxResult.error("api type mismatch:"+api.getType());
        }
        String tableName = api.getGenSql();
        String id = paramsMap.get("id");
        if(paramsMap.size()==0 ||  StringUtils.isEmpty(id)){
            return AjaxResult.error("need param id") ;
        }
        //数据库中的查询参数
        Map<String,Map<String,String>> whereConditions = gson.fromJson(api.getWhereConditions(), Map.class);
        if(whereConditions != null && whereConditions.size()>0){
            //根据实际传入的字段和定义的字段进行过滤，过滤掉非法字段
            Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
		    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> entry = iterator.next();
                String columnName = entry.getKey();
                Map<String,String> columnMap = whereConditions.get(columnName);
                if(columnMap == null  || StringUtils.isEmpty( String.valueOf( entry.getValue()) )){
                    log.info("param "+columnName+" ignore because null or not defined");
                    // paramsMap.remove(columnName);
                    iterator.remove();
                }
            }
        }
        
        int ret = db.updateByKey(tableName, paramsMap, "id", id);
        return ret > 0 ? AjaxResult.success("ok") : AjaxResult.error("affect rows:"+ret);
    }



    @RequestMapping(value = "/query/{path}",method = RequestMethod.POST)
    @ResponseBody
    AjaxResult query(@PathVariable("path") String path,
                    //@RequestParam(value="pageIndex",defaultValue = "1") int pageIndex,
                    //@RequestParam(value="pageSize",defaultValue = "20") int pageSize,
                    //是否查询总条数
                    //@RequestParam(value="total",defaultValue = "true") boolean total,
                    //@RequestParam("jsonParam") String jsonStr
                    @RequestBody(required = false) Map<String,String> conditionMap,
                    @CurrentUser User user
                    ){
        // User user = UserUtil.get();
        log.info("headers uid from threadlocal:" + user.getId());
        log.info("headers name from threadlocal:" + user.getName());
        
        log.info("headers roles from threadlocal:" + Arrays.toString(user.getRolds()));
        log.debug("api path "+path);
        if(conditionMap==null){
            conditionMap = new HashMap<>();
        }
        int pageIndex = NumberUtils.toInt(conditionMap.get("pageIndex"), 1);
        int pageSize = NumberUtils.toInt(conditionMap.get("pageSize"), 20);
        int total = NumberUtils.toInt(conditionMap.get("total"), 1);
        log.info("api pageIndex "+pageIndex);
        log.info("api pageSize "+pageSize);
        log.debug("api params "+conditionMap);
        //CustomApi api = db.getOne(CustomApi.class, "path", path);
        CustomApi api = apiService.getApi(path);
        if(api == null){
            return AjaxResult.error("api not exist:"+path) ;
        }
        if(api.getStatus() == -1){
            return AjaxResult.error("api disabled :"+path) ;
        }
        if(!"query".equals(api.getType())){
            return AjaxResult.error("api type mismatch:"+api.getType());
        }
        String sql = api.getGenSql();
        log.debug("api sql "+sql);
        if(sql.indexOf("where") != -1){
            sql = sql.substring(0, sql.indexOf("where"));
        }
        log.debug("api sql "+sql);
        log.info(api.getWhereConditions());
        //数据库中的查询参数
        Map<String,Map<String,String>> whereConditions = gson.fromJson(api.getWhereConditions(), Map.class);
        
        //实际传入的查询参数
        // Map<String,String> conditionMap = gson.fromJson(jsonStr, Map.class);
        
        boolean needReplace = false;
        sql += " where 1=1 ";
        //处理传入的参数
        if(whereConditions!=null && whereConditions.size()>0){
            for(Map.Entry<String,Map<String,String>> entry : whereConditions.entrySet()){
                String columnName = entry.getKey();
                //获取请求参数
                String columnValue = conditionMap.get(columnName)!=null? String.valueOf(conditionMap.get(columnName)) :"";
                
                if( StringUtils.isBlank(columnValue)){
                    log.info("param {} ignore because not passed",columnName);
                    continue;
                }
                Map<String,String> columnMap = entry.getValue();//获取默认值
                
                String defaultValue = columnMap.get("default_value");
                if(StringUtils.isNotBlank(defaultValue)){
                    //如果有默认值，使用默认值替代
                    columnValue = defaultValue;
                    if(defaultValue.startsWith("${") && defaultValue.endsWith("}")){
                        needReplace = true;
                        log.info("==needReplace===");
                    }
                    else{
                        log.info("=====");
                    }
                }
                String opt = columnMap.get("opt");
                // if(!StringUtils.isEmpty( String.valueOf( entry.getValue()) )){
                //     sql += " and "+ columnMap.get("column") + " "+ opt+ " @"+columnName;
                // }
                log.info("columnName: {}  columnValue:  {}",columnName,columnValue);
                if(!StringUtils.isEmpty(columnValue )){
                    //普通参数
                    if(StringUtils.isBlank(defaultValue)){
                        sql += " and "+ columnMap.get("column") + " "+ opt+ " @"+columnName;
                    }
                    //默认参数
                    else{
                        sql += " and "+ columnMap.get("column") + " "+ opt+ " "+defaultValue;
                        //默认非占位符参数存入参数表
                        if(!defaultValue.startsWith("${")){
                            conditionMap.put(columnName, columnValue);
                        }
                    }
                    
                    if("like".equals(opt)){
                        conditionMap.put(columnName, "%"+columnValue+"%");
                    }
                    else if("left_like".equals(opt)){
                        conditionMap.put(columnName, "%"+columnValue);
                    }
                    else if("right_like".equals(opt)){
                        conditionMap.put(columnName, columnValue+"%");
                    }
                }
                else{
                    log.info("param: {} ignore because param value is emtpy",columnName);
                }
            }
        }
        log.info("api sql add conditions : "+sql);
        if(needReplace && user != null){
            //使用当前用户的用户id或者部门id替换占位符
            sql = TemplateUtil.simpleTemplate(sql, user.toMap());

            log.info("api after replace : "+sql);
        }
        if( StringUtils.isNotBlank(api.getAddSql())){
            //添加orderby  groupby等
            sql += api.getAddSql();
        }
        
        //long totalCount = 0 ;
        
        List<Record> data = db.queryListBySqlMap(pageIndex,total!=0 ? pageSize:0,sql,conditionMap);
        
        //处理字典或者日期格式
        // gson.from
        // List<Map<String,Object>> resultColumnList =  gson.fromJson(api.getResultColumns(), List.class);
        // for(Map columnMap : resultColumnList){
        //     System.out.println(columnMap.get("column_format"));
        //     //#日期 @字典  
            
        // }
        
        AjaxResult result = AjaxResult.success(data);
        //如果需要查询总条数
        if(total != 0){
            long totalCount = db.queryCountBySqlMap(sql, conditionMap);
            result.put("recordsTotal", totalCount);
            result.put("recordsFiltered", totalCount);
        }
        return result;

    }


    @RequestMapping(value = "/stat/{path}",method = RequestMethod.POST)
    @ResponseBody
    AjaxResult stat(@PathVariable("path") String path,
                    //@RequestParam(value="pageNum",defaultValue = "1") int pageIndex,
                    //@RequestParam(value="pageSize",defaultValue = "20") int pageSize,
                    //是否查询总条数
                    //@RequestParam(value="total",defaultValue = "true") boolean total,
                    //@RequestParam("jsonParam") String jsonStr
                    @RequestBody(required = false) Map<String,String> conditionMap
                    ){
        if(conditionMap==null){
            conditionMap = new HashMap<>();
        }
        int pageIndex = NumberUtils.toInt(conditionMap.get("pageIndex"), 1);
        int pageSize = NumberUtils.toInt(conditionMap.get("pageSize"), 20);
        int total = NumberUtils.toInt(conditionMap.get("total"), 1);
        log.debug("api path "+path);
        log.debug("api pageIndex "+pageIndex);
        log.debug("api pageSize "+pageSize);
        log.debug("api params "+conditionMap);
        // CustomApi api = db.getOne(CustomApi.class, "path", path);
        CustomApi api = apiService.getApi(path);
        if(api == null){
            return AjaxResult.error("api not exist:"+path) ;
        }
        if(api.getStatus() == -1){
            return AjaxResult.error("api disabled :"+path) ;
        }
        if(!"stat".equals(api.getType())){
            return AjaxResult.error("api type mismatch:"+api.getType());
        }
        String sql = api.getGenSql();
        log.debug("api sql "+sql);
        if(sql.indexOf("where") != -1){
            sql = sql.substring(0, sql.indexOf("where"));
        }
        //数据库中的查询参数
        Map<String,Map<String,String>> whereConditions = gson.fromJson(api.getWhereConditions(), Map.class);
        
        //实际传入的查询参数
        // Map<String,String> conditionMap = gson.fromJson(jsonStr, Map.class);
        if(conditionMap!=null && conditionMap.size()>0){
            sql += " where 1=1 ";
            for(Map.Entry<String,String> entry : conditionMap.entrySet()){
                String columnName = entry.getKey();
                String columnValue =  String.valueOf( entry.getValue());
                Map<String,String> columnMap = whereConditions.get(columnName);
                if(columnMap == null){
                    log.info("param "+columnName+" ignore because not defined");
                    continue;
                }
                String opt = columnMap.get("opt");
                
                if(!StringUtils.isEmpty(columnValue )){
                    sql += " and "+ columnMap.get("column") + " "+ opt+ " @"+columnName;
                    if("like".equals(opt)){
                        conditionMap.put(columnName, "%"+columnValue+"%");
                    }
                }
                else{
                    log.info("param "+entry.getKey()+" ignore because param value is emtpy");
                }
            }
        }
        log.info("api sql add conditions : "+sql);
        if( StringUtils.isNotBlank(api.getAddSql())){
            //添加orderby  groupby等
            sql += api.getAddSql();
        }
        
        //long totalCount = 0 ;
        
        List<Record> data = db.queryListBySqlMap(pageIndex,total!=0 ? pageSize:0,sql,conditionMap);
        AjaxResult result = AjaxResult.success(data);
        //如果需要查询总条数
        if(total!=0){
            long totalCount = db.queryCountBySqlMap(sql, conditionMap);
            result.put("totalCount", totalCount);
            result.put("recordsFiltered", totalCount);
        }
        return result;

    }

    @RequestMapping(value = "/fetch/{path}",method = RequestMethod.GET)
    @ResponseBody
    AjaxResult fetch(@PathVariable("path") String path,
                    
                    @RequestParam("id") String id){

        log.debug("api path"+path);
        log.debug("api param id :"+id);
        //CustomApi api = db.getOne(CustomApi.class, "path", path);
        CustomApi api = apiService.getApi(path);
        if(api == null){
            return AjaxResult.error("api not exist:"+path) ;
        }
        if(api.getStatus() == -1){
            return AjaxResult.error("api disabled :"+path) ;
        }
        if(!"fetch".equals(api.getType())){
            return AjaxResult.error("api type mismatch:"+api.getType());
        }
        if(StringUtils.isEmpty(id)){
            return AjaxResult.error("id must be contains in params "+api.getType());
        }
        String sql = api.getGenSql();
        log.info("api sql",sql);
        // if(sql.indexOf("where") != -1){
        //     sql = sql.substring(0, sql.indexOf("where"));
        // }
        // sql += " where id = @id ";
        Map<String,String> conditionMap = new HashMap<>();
        conditionMap.put("id", id);
        
        List<Record> data = db.queryListBySqlMap(1,1,sql,conditionMap);
        if(data!=null && data.size() ==1){
            AjaxResult result = AjaxResult.success(data.get(0));
            return result;
        }
        
        return AjaxResult.error("not found");

    }

}
