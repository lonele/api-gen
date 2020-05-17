package com.jiadao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jiadao.db.Column;
import com.jiadao.db.Table;
import com.jiadao.model.AjaxResult;
import com.jiadao.model.CustomTable;
import com.jiadao.service.TableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用请求处理
 *
 * @author lvenle
 */
@Controller
@RequestMapping("/table")
public class TableController
{

    @Autowired
    private TableService tableService;

    /**
     * 通用新增请求
     *
     * @param table 表
     */
    @PostMapping("/createTable")
    @ResponseBody
    public AjaxResult createTable(@RequestBody CustomTable table)
    {

        int ret = 0;
        String msg = "";
        // System.out.println(table);
        if(table.getColumns().size()>=2){
            List<Column> innerColumns = new ArrayList<>();
            table.getColumns().forEach(column->{
                Column col = new Column();
                col.setName(column.getName());
                col.setComment(column.getNameCN());
                col.setLength(column.getLength());
                col.setType(column.getType());
                col.setRequired(!column.isNullable());
                col.setPrimary(column.isPk());
                col.setIndex(column.getIndex());
                innerColumns.add(col);
            });

            Table innerTable = new Table<Column>();
            innerTable.setComment(table.getNameCN());
            innerTable.setName(table.getName());
            innerTable.setColumns(innerColumns);
            innerTable.setDb(tableService.getDefaultDbName());

            try {
                tableService.createMysqlTable(innerTable);
                
                ret = 1;
            } catch (Exception e) {
                e.printStackTrace();
                msg = e.getMessage();
            }
        }
        else{
            msg="column number must not less than 2";
        }

        return ret == 1 ? AjaxResult.success(table.getId()) : AjaxResult.error(msg);
    }

    @PostMapping("/getTableList")
    @ResponseBody
    public AjaxResult getTableList()
    {
        List<Map<String,String>> tables = tableService.getTableListMap();
        return AjaxResult.success(tables);
    }

    

    @GetMapping("getTableNames")
    @ResponseBody
    public List<String> getTableNames()
    {
        return tableService.getAllTables();
    }


    @PostMapping("/dropTable")
    @ResponseBody
    public AjaxResult dropTable( @RequestParam("tableName") String tableName)
    {
        int ret = tableService.dropTable(tableName);
        return ret == 0 ?AjaxResult.success():AjaxResult.error(ret+"");
    }


    @PostMapping("/getTable")
    @ResponseBody
    public AjaxResult getTableByName(@RequestParam("tableName") String tableName)
    {
        CustomTable table = tableService.getTableWithColumns(tableName);
        return table !=null ? AjaxResult.success(table) : AjaxResult.error("table not found");
    }



    @PostMapping("/getTableByNames")
    @ResponseBody
    public AjaxResult getTableByNames( @RequestParam("tableNames") String[] tableNames)
    {
        
        List<CustomTable> tables = tableService.getTablesWithColumns(tableNames);
        return tables.size()>0 ? AjaxResult.success(tables) : AjaxResult.error("table not found");
    }
    

}
