package com.jiadao.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jiadao.db.MysqlTableUtil;
import com.jiadao.db.Table;
import com.jiadao.model.CustomColumn;
import com.jiadao.model.CustomTable;

import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class TableService {


    @Autowired
    DataSource dataSource;

    @Autowired
    DB db;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Value("${system.dbName}")
    String defaultdbName;

    Dao dao;

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public boolean createMysqlTable(Table table) throws Exception {
        // CustomTable 
        MysqlTableUtil tableOperator = new MysqlTableUtil(table, jdbcTemplate);
        boolean isCreate = tableOperator.isTableCreated();
        // System.out.println("isCreate===="+isCreate);
        log.debug("table "+table.getName()+" is_create "+isCreate);
        if (!isCreate) {
            tableOperator.createTable();
        } else {
            
        }
        return true;
    }

    public String getDefaultDbName(){
        return defaultdbName;
    }


    public List<String> getAllTables() {
        return getAllTables(defaultdbName);
    }

    public List<String> getAllTables(String dbName) {
        //String schema = "nutsdemo";
        try (Connection conn = dataSource.getConnection()){
            List<String> tables = new ArrayList<>();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tableRet = metaData.getTables(dbName, null,"%",new String[]{"TABLE"}); 
            while(tableRet.next()){
                String tableName = tableRet.getString("TABLE_NAME");
                String tableNameCn = tableRet.getString("REMARKS");
                // System.out.println("tableName==="+tableName);
                tables.add(tableName);
            } 
            tableRet.close();
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Map<String,String>> getTableListMap() {
        try (Connection conn = dataSource.getConnection()){
            List<Map<String,String>> tables = new ArrayList<>();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tableRet = metaData.getTables(defaultdbName, null,"%",new String[]{"TABLE"}); 
            while(tableRet.next()){
                String tableName = tableRet.getString("TABLE_NAME");
                String tableNameCn = tableRet.getString("REMARKS");
                String db = tableRet.getString("TABLE_CAT");
                // System.out.println("tableName==="+tableName);
                Map<String,String> tableMap = new HashMap<>();
                tableMap.put("name", tableName);
                tableMap.put("nameCN", tableNameCn);
                tableMap.put("db", db);
                tables.add(tableMap);
            } 
            tableRet.close();
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getAllDbs() {
        try (Connection conn = dataSource.getConnection()){
            List<String> dbs = new ArrayList<>();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tableRet = metaData.getCatalogs();
            while(tableRet.next()){
                String dbName = tableRet.getString("TABLE_CAT");
                dbs.add(dbName);
            } 
            tableRet.close();
            return dbs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CustomTable> getTablesWithColumns(String[] tableNames) {
        //String schema = "nutsdemo";
        List<CustomTable> tableList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()){
            DatabaseMetaData metaData = conn.getMetaData();
            for(String tableName : tableNames){
                CustomTable table = getTableWithColumns(tableName);
                tableList.add((table));
            }
            return tableList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public CustomTable getTableWithColumns(String tableName) {
        //String schema = "nutsdemo";
        List<CustomTable> tableList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()){
            DatabaseMetaData metaData = conn.getMetaData();
            CustomTable table = new CustomTable();
                tableList.add(table);
                table.setName(tableName);
                ResultSet tableRet = metaData.getTables(defaultdbName, null,tableName,new String[]{"TABLE"}); 
                
                while(tableRet.next()){
                    // String table_name = tableRet.getString("TABLE_NAME");
                    // System.out.println("table_name==="+table_name);
                    String tableNameCN = tableRet.getString("REMARKS");
                    // System.out.println("table_name_cn==="+table_name_cn);
                    table.setNameCN(tableNameCN);
                } 
                tableRet.close();
                ResultSet rs = metaData.getIndexInfo(defaultdbName, null, tableName, false, true);  
                Map<String,String> indexMap = new HashMap<>();
                while (rs.next()){  
                    //是否唯一索引
                    boolean nonUnique = rs.getBoolean("NON_UNIQUE");// 索引值是否可以不唯一,TYPE为 tableIndexStatistic时索引值为 false;
                    //索引名，PRIMARY表示主键索引
                    String indexName = rs.getString("INDEX_NAME");//索引的名称 ;TYPE为 tableIndexStatistic 时索引名称为 null;
                    
                    String columnName = rs.getString("COLUMN_NAME");//列名;TYPE为 tableIndexStatistic时列名称为 null;
                    indexMap.put(columnName, nonUnique+"@"+indexName);
                }
                rs.close();

                List<CustomColumn> columns = new ArrayList<>();
                ResultSet colRet = metaData.getColumns(defaultdbName, null, tableName, "%");
                while(colRet.next()){
                    CustomColumn column = new CustomColumn();
                    String columnName =colRet.getString("COLUMN_NAME");
                    column.setName(columnName);
                    column.setType(colRet.getString("TYPE_NAME").toLowerCase());
                    column.setLength(colRet.getInt("COLUMN_SIZE"));
                    column.setNameCN(colRet.getString("REMARKS")); 
                    String indexInfo = indexMap.get(columnName);
                    if(indexInfo != null){
                        String[] indexInfoArr = indexInfo.split("@");
                        if(indexInfoArr[1].equals("PRIMARY")){
                            column.setPk(true);
                        }
                        else if(indexInfoArr[0].equals("true")){
                            column.setIndex("normal");
                        }
                        else{
                            column.setIndex("unique");
                        }
                    }
                    columns.add(column);
                }
                table.setColumns(columns);
                colRet.close();
                return table;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @param tableName 表名
     * @return 1 表中有记录，不删除 0 删除成功 -1 删除失败
     */
    public int dropTable(String tableName){
        long count = db.getCount(tableName);
        if(count > 0){
            return 1;
        }
        else{
            return db.dropTable(tableName)? 0: -1;
        }
    }

}
