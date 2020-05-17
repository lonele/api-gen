package com.jiadao.controller;

import com.jiadao.service.DB;
import com.jiadao.model.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 通用请求处理
 *
 * @author lvenle
 */
// @Controller
public class DBController
{
    private static final Logger log = LoggerFactory.getLogger(DBController.class);

    @Autowired
    private DB db;


    /**
     * 通用新增请求
     *
     * @param tableName 表名称
     * @param jsonData 表单数据，json序列化
     */
    // @PostMapping("db/{tableName}/save")
    // @ResponseBody
    // public AjaxResult save(@PathVariable("tableName") String tableName, String jsonData)
    // {

    //     int ret = db.save(tableName,jsonData);
    //     return ret > 0 ? AjaxResult.success() : AjaxResult.error("save error");
    // }

    /**
     * 通用新增请求
     *
     * @param tableName 表名称
     * @param paramMap 表单数据
     */
    @PostMapping("db/{tableName}/save1")
    @ResponseBody
    public AjaxResult save1(@PathVariable("tableName") String tableName, @RequestParam Map<String,String> paramMap)
    {
        int ret = db.save(tableName,paramMap);
        return ret > 0 ? AjaxResult.success() : AjaxResult.error("save error");
    }

    /**
     * 通用编辑请求
     */
    @PostMapping("db/{tableName}/edit")
    @ResponseBody
    public AjaxResult edit(@PathVariable("tableName") String tableName, String id,String field,String value)
    {
        int ret = db.updateSingleColumnByKey(tableName,field,value,"id",id);
        return ret > 0 ? AjaxResult.success() : AjaxResult.error("update error");
    }


    /**
     * 通用删除请求
     */
    @PostMapping("db/{tableName}/delete")
    @ResponseBody
    public AjaxResult delete(@PathVariable("tableName") String tableName, String id)
    {
        int ret = db.deleteByKey(tableName,"id",id);
        return ret > 0 ? AjaxResult.success() : AjaxResult.error("update error");
    }


    /**
     * 通用分页查询请求
     */
    @PostMapping("db/{tableName}/{id}")
    @ResponseBody
    public Object getOne(@PathVariable("tableName") String tableName,String id)
    {
        return db.getOne(tableName,"id",id);
    }

//    /**
//     * 通用分页查询请求
//     */
//    @PostMapping("db/{tableName}/page")
//    @ResponseBody
//    public TableDataInfo queryListByPage(@PathVariable("tableName") String tableName, int page,int limit,String jsonConditions)
//    {
//        QueryResult result = db.queryListByPage(page,limit,tableName,jsonConditions);
//        return getDataTable(result);
//    }
//
//
//
//    /**
//     * 响应请求分页数据
//     */
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    protected TableDataInfo getDataTable(QueryResult result)
//    {
//        TableDataInfo rspData = new TableDataInfo();
//        rspData.setCode(0);
//        rspData.setData(result.getList());
//        rspData.setCount(result.getPager().getRecordCount());
//
//        return rspData;
//    }


}
