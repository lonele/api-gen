package com.jiadao.controller;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.jiadao.model.AjaxResult;
import com.jiadao.model.CustomForm;
import com.jiadao.service.DB;
import com.jiadao.service.FormService;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/form")
public class FormController {

    @Autowired
    DB db;

    @Autowired
    FormService formService;

    @RequestMapping("/")
    String index() {
        return "Hello Form Gen";
    }

    /**
     * 通用新增请求
     *
     * @param table 表
     */
    @PostMapping("/createForm")
    @ResponseBody
    //public AjaxResult createform(@RequestBody CustomForm form)
    public AjaxResult createForm(@RequestBody CustomForm form)
    {
        int ret = 0;
        String msg = "";
        // System.out.println(form.getGenSql());
        // form = db.save(form);
        form = formService.createOrUpdateForm(form);
        if(form.getId() > 0 ){
            ret = 1;
            msg = "ok";
        }
        else{
            msg="error";
        }
        return ret == 1 ? AjaxResult.success(form.getId()) : AjaxResult.error(msg);
    }

    @GetMapping("/getForm")
    @ResponseBody
    public AjaxResult getForm(@RequestParam("formId") String formId)
    {
        int ret = 0;
        String msg = "";
        CustomForm form = db.getOne(CustomForm.class, "id", formId);
        if(form != null){
            ret = 1;
            msg = "ok";
        }
        else{
            msg="error";
        }
        return ret == 1 ? AjaxResult.success(form) : AjaxResult.error(msg);
    }


    @GetMapping("/getFormByName")
    @ResponseBody
    public AjaxResult getFormByName(@RequestParam("path") String path)
    {
        int ret = 0;
        String msg = "";
        // CustomForm form = db.getOne(CustomForm.class, "path", path);
        CustomForm form = formService.getForm(path);
        if(form != null){
            ret = 1;
            msg = "ok";
        }
        else{
            msg="error";
        }
        return ret == 1 ? AjaxResult.success(form) : AjaxResult.error(msg);
    }


    @GetMapping("/deleteForm")
    @ResponseBody
    public AjaxResult deleteForm(@RequestParam("formName") String formName)
    {
        System.out.println("deleteForm formName ==="+formName);
        // int ret = db.deleteByKey("cust_form", "id", formId);
        int ret = formService.deleteForm(formName);
        System.out.println("deleteForm ret ==="+ret);
        return ret == 1 ? AjaxResult.success() : AjaxResult.error();
    }

    @GetMapping("/changeFormStatus")
    @ResponseBody
    public AjaxResult changeFormStatus(@RequestParam("formName") String formName)
    {
        System.out.println("changeFormStatus formName ==="+formName);
        // int ret = db.deleteByKey("cust_form", "id", formId);
        int ret = formService.changeFormStatus(formName);
        System.out.println("changeFormStatus ret ==="+ret);
        return ret == 1 ? AjaxResult.success() : AjaxResult.error();
    }



    Gson gson = new Gson();
    
    @PostMapping("/getFormList")
    @ResponseBody
    public AjaxResult getFormList(
        @RequestParam(value="pageIndex",defaultValue = "1") int pageIndex,
        @RequestParam(value="pageSize",defaultValue = "20") int pageSize,
        @RequestParam("whereConditions") String whereConditionsStr,
        @RequestParam("jsonParam") String jsonStr)
    {
        String sql = "select * from cust_form";
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
        List<Record> formList = db.queryListBySqlMap(pageIndex,pageSize,sql, conditionMap);
        AjaxResult result = AjaxResult.success(formList);
        long totalCount = db.queryCountBySqlMap(sql, conditionMap);
        result.put("recordsTotal", totalCount);
        result.put("recordsFiltered", totalCount);
        return result;
    }
    
}
