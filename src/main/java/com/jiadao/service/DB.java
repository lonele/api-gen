package com.jiadao.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DB {

    @Autowired
    DataSource dataSource;

    // @Value("${system.modelPackage}")
    // String modelPackage;




    Dao dao;

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @PostConstruct
    public void init() {
        // System.out.println("modelPackage==" + modelPackage);
        dao = new NutDao(dataSource);

        // dao.create(Person.class, false); // false的含义是,如果表已经存在,就不要删除重建了.
        // map.put("person",Person.class);
        // 批量建表,扫描某个package下的bean,为带@Table注解的类建表
        // Daos.createTablesInPackage(dao, modelPackage, false);
    }

    public Dao getDao() {
        return dao;
    }

    public boolean dropTable(String tableName){
       return  dao.drop(tableName);
    }

    // jsonCondition =
    // [{'key':"name",'opt':"=",'value':"zhangsan"},{'key':"age",'opt':">",'value':"5"}]

    public Cnd buildQueryCondition(String jsonCondition) {
        List<java.util.Map<String, String>> conditionList = gson.fromJson(jsonCondition,
                new TypeToken<List<java.util.Map<String, Object>>>() {
                }.getType());
        Cnd cnd = Cnd.where("1", "=", "1");
        if (conditionList == null || conditionList.size() == 0) {
            return cnd;
        }
        conditionList.forEach(map -> {
            String opt = map.get("opt");
            String key = map.get("key");
            String value = map.get("value");
            if (StringUtils.isEmpty(opt) || StringUtils.isEmpty(value)) {
                return;
            }
            cnd.and(key, opt, value);
        });

        // cri.getOrderBy().asc("name").desc("id");
        return cnd;

    }

    /**
     * 返回插入数据的id
     * @param tableName
     * @param paramMap
     * @return
     */
    public int save(String tableName, Map<String, String> paramMap) {
        if (paramMap.size() > 0) {
            Chain chain = Chain.from(paramMap);
            dao.insert(tableName, chain);
            Sql sql = Sqls.fetchLong("select last_insert_id()");
            dao.execute(sql);
            //TO-DO,高并发下，获取id可能会有问题，可以在放在一个原子操作内完成
            long id = sql.getLong();
            // Trans.exec(new Atom(){
            //     public void run() {
                    
            //     }
            // });
            //dao.insert(tableName, chain);
            return (int)id;
        }
        return 0;
    }

    public <T> T save(T obj) {
        // dao.insert(obj);
        dao.insertOrUpdate(obj);
        return obj;
    }

    public int saveList(List obj) {
        dao.fastInsert(obj);
        return 1;
    }

    //
    // public <T> T save(T obj){
    // dao.insert(obj);
    // return obj;
    // }

    // public int save(String tableName, String jsonData) {
    //     Map map = gson.fromJson(jsonData, Map.class);
    //     if (map.size() > 0) {
    //         Chain chain = Chain.from(map);
    //         dao.insert(tableName, chain);
    //         return 1;
    //     }
    //     return 0;
    // }

    public <T> T save(Class<T> modelClass, String jsonData) {
        Object obj = gson.fromJson(jsonData, modelClass);
        return (T) dao.insertOrUpdate(obj);
    }

    public int update(Class modelClass, String jsonData) {
        Object obj = gson.fromJson(jsonData, modelClass);
        return dao.updateIgnoreNull(obj);
    }

    public int updateSingleColumnByKey(String tableName, String key, Object value, String keyColumn, String keyValue) {
        Chain chain = Chain.make(key, value);
        return dao.update(tableName, chain, Cnd.where(keyColumn, "=", keyValue));
    }

    public int updateByKey(String tableName, String jsonData, String keyColumn, String keyValue) {
        Map map = gson.fromJson(jsonData, Map.class);
        if (map.size() > 0) {
            Chain chain = Chain.from(map);
            return dao.update(tableName, chain, Cnd.where(keyColumn, "=", keyValue));
        }
        return 0;
    }


    public int updateByKey(String tableName, Map mapData, String keyColumn, String keyValue) {
        // Map map = gson.fromJson(jsonData, Map.class);
        if (mapData.size() > 0) {
            Chain chain = Chain.from(mapData);
            return dao.update(tableName, chain, Cnd.where(keyColumn, "=", keyValue));
        }
        return 0;
    }

    // 删除单个对象
    public int deleteByKey(Class modelClass, String id) {
        return dao.delete(modelClass, id);
    }

    // 根据删除多个对象
    public int deleteByKey(String tableName, String keyColumn, String keyValue) {
        return dao.clear(tableName, Cnd.where(keyColumn, "=", keyValue));
    }

    public int deleteByKeys(Class modelClass, String keyColumn, String[] ids) {
        String sids = String.join(",", ids);
        return dao.clear(modelClass, Cnd.where(keyColumn, "in", sids));
    }

    public int deleteByKeys(String tableName, String keyColumn, String[] ids) {
        String sids = String.join(",", ids);
        return dao.clear(tableName, Cnd.where(keyColumn, "in", sids));
    }

    public Record getOne(String tableName, String keyColumn, String keyValue) {
        return dao.fetch(tableName, Cnd.where(keyColumn, "=", keyValue));
    }

    public long getCount(String tableName){
        return dao.count(tableName);
    }

    //@Cacheable注解会先查询是否已经有缓存，有会使用缓存，没有则会执行方法并缓存。

    public <T> T getOne(Class<T> modelClass, String keyColumn, String keyValue) {
        return dao.fetch(modelClass, Cnd.where(keyColumn, "=", keyValue));
    }

    public <T> T getOneWithMany(Class<T> modelClass, long idValue, String manyField) {
        return dao.fetchLinks(dao.fetch(modelClass, idValue), manyField);
    }

    public <T> T getOneWithMany(Class<T> modelClass, String nameValue, String manyField) {
        return dao.fetchLinks(dao.fetch(modelClass, nameValue), manyField);
    }

    public List<Record> getList(String tableName, String conditions) {
        Cnd condition = buildQueryCondition(conditions);
        List<Record> list = dao.query(tableName, condition, null);
        return list;
    }

    public List getList(Class modelClass, String conditions) {
        Cnd condition = buildQueryCondition(conditions);
        List list = dao.query(modelClass, condition, null);
        return list;
    }

    // conditions =
    // [{'key':"name",'opt':"=",'value':"zhangsan"},{'key':"age",'opt':">",'value':"5"}]
    public QueryResult queryListByPage(int pageNumber, int pageSize, Class modelClass, String conditions) {
        Cnd condition = buildQueryCondition(conditions);
        Pager pager = dao.createPager(pageNumber, pageSize);
        List list = dao.query(modelClass, condition, pager);
        pager.setRecordCount(dao.count(modelClass));
        return new QueryResult(list, pager);
    }

    public QueryResult queryListByPage(int pageNumber, int pageSize, String tableName, String conditions) {
        Cnd condition = buildQueryCondition(conditions);
        // System.out.println("queryListByPage 1 "+conditions);
        // System.out.println("queryListByPage 2 "+condition.toString());
        Pager pager = dao.createPager(pageNumber, pageSize);
        List list = dao.query(tableName, condition, pager);
        pager.setRecordCount(dao.count(tableName));
        return new QueryResult(list, pager);
    }

    public List<Record> queryListBySql(int pageNumber, int pageSize, String sqlStr, String jsonConditions) {
        // Sql sql = Sqls.create("select t_student.name,t_student.code
        // ,t_score.score,t_score.exam_date \n" +
        // "from t_student inner join t_score \n" +
        // "on t_student.`code` = t_score.student_code\n");
        // sqlStr += " $condition";
        // sqlStr += orderSql;
        sqlStr += " limit @limit ,@offset ";
        Sql sql = Sqls.create(sqlStr);
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Record.class));
        Cnd condition = buildQueryCondition(jsonConditions);
        sql.setCondition(condition);
        sql.params().set("limit", (pageNumber - 1) * pageSize);
        sql.params().set("offset", pageSize);
        dao.execute(sql);
        List<Record> list = sql.getList(Record.class);
        return list;
    }


    public List<Record>  queryListBySqlMap( int pageNumber, int pageSize,String sqlStr,Map<String,String> conditionMap){
        if(pageSize>0){
            sqlStr += " limit @limit ,@offset ";
        }
        
        Sql sql = Sqls.create(sqlStr);
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Record.class));
        if(conditionMap != null){
            for(Map.Entry<String,String> entity : conditionMap.entrySet()){
                if(!StringUtils.isEmpty(entity.getValue())){
                    sql.params().set(entity.getKey(),entity.getValue());
                }
                else{
                    log.info("param "+entity.getKey()+" ignore because value is emtpy");
                }
            }
        }
        
        if(pageSize >0){
            sql.params().set("limit",(pageNumber-1)*pageSize);
            sql.params().set("offset",pageSize);
        }
        dao.execute(sql);
        List<Record> list = sql.getList(Record.class);
        return list;
    }

    public long  queryCountBySqlMap(String sqlStr,Map<String,String> conditionMap){
        // sqlStr += " limit @limit ,@offset ";
        // int fromIndex = sqlStr.indexOf("from");
        // String sqlCount = "select count(1) "+ sqlStr.substring(fromIndex);
        // int fromIndex = sqlStr.indexOf("from");
        String sqlCount = "select count(1) from ( "+ sqlStr +" ) as table_c";
        log.info("sqlCount=="+sqlCount);
        // System.out.println("sqlCount=="+sqlCount);
        Sql sql = Sqls.fetchLong(sqlCount);
        // sql.setCallback(Sqls.callback.entities());
        // sql.setEntity(dao.getEntity(Record.class));
        if( conditionMap != null){
            for(Map.Entry<String,String> entity : conditionMap.entrySet()){
                if(!StringUtils.isEmpty(entity.getValue())){
                    sql.params().set(entity.getKey(),entity.getValue());
                }
                else{
                    log.info("param "+entity.getKey()+" ignore because value is emtpy");
                }
            }
        }
        
        dao.execute(sql);
        long count = sql.getLong();
        return count;
    }

}
