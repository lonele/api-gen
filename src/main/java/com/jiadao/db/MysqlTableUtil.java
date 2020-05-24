package com.jiadao.db;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MysqlTableUtil{


	/**
	 * 表对象
	 */
	protected Table<? extends Column> table;
	/**
	 * jdbc
	 */
	protected JdbcTemplate jdbcTemplate;


	/**
	 * @param table
	 * @param jdbcTemplate
	 */
	public MysqlTableUtil(Table<? extends Column> table, JdbcTemplate jdbcTemplate) {
		
		this.table = table;
		this.jdbcTemplate = jdbcTemplate;
	}

	// @Override
	public String type() {
		return DbType.MYSQL.getKey();
	}

	// @Override
	public void createTable() {
		if (isTableCreated()) {
			log.info("表[" + table.getName() + "(" + table.getComment() + ")]已存在数据库中，无需再次生成");
			throw new RuntimeException("表[" + table.getName() + "(" + table.getComment() + ")]已存在数据库中，无需再次生成");
		}

		// 建表语句
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE " + table.getName() + " (" + "\n");
		StringBuilder sbIndex = new StringBuilder();
		for (Column column : table.getColumns()) {
			sql.append(columnToSql(column) + ",\n");
			//排除主键和没有设置索引的列
			if(! column.isPrimary() && StringUtils.isNotEmpty(column.getIndex()) ){
				//特殊处理唯一索引
				if("unique".equalsIgnoreCase(column.getIndex())){
					sbIndex.append(" unique  ");
				}
				else if("normal".equalsIgnoreCase(column.getIndex())){
					sbIndex.append(" INDEX  idx_"+column.getName()+"  ("+column.getName()+"),");

				}
			}
		}
		sql.append("PRIMARY KEY (" + table.getPkColumn().getName() + ")" );
		if(sbIndex.length()>0){
			sbIndex.delete(sbIndex.length()-1,sbIndex.length());
			sql.append(","+sbIndex.toString());
		}
		sql.append("\n)");

		//默认设置字符集
		sql.append(" ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin ");
		if (StringUtils.isNotEmpty(table.getComment())) {
			sql.append(" COMMENT='" + table.getComment() + "'");
		}
		// 建表结束
		sql.append(";");
        System.out.println("createTable sql :"+sql.toString());
		log.info("createTable",sql.toString());
		jdbcTemplate.execute(sql.toString());
	}

	// @Override
	public boolean isTableCreated() {
		String sql = "select count(1) from information_schema.TABLES t where TABLE_SCHEMA=? and  table_name =?";
		System.out.println("isTableCreated=="+sql);
		return jdbcTemplate.queryForObject(sql, Integer.class, table.getDb() ,table.getName()) > 0 ? true : false;
	}

	public void dropTable() {
		if (!isTableCreated()) {
			return;
		}
		String sql = "drop table " + table.getName() + "";
		jdbcTemplate.execute(sql);
	}

	// @Override
	public void addColumn(Column column) {
		StringBuilder sql = new StringBuilder();
		sql.append("ALTER TABLE " + table.getName() + "");
		sql.append(" ADD COLUMN " + columnToSql(column) + ";");
		jdbcTemplate.execute(sql.toString());
	}

	// @Override
	public void updateColumn(Column column) {
		StringBuilder sql = new StringBuilder();
		sql.append("ALTER TABLE " + table.getName() + "");
		sql.append(" MODIFY COLUMN " + columnToSql(column) + ";");
		jdbcTemplate.execute(sql.toString());
	}

	// @Override
	public void dropColumn(String columnName) {
		StringBuilder sql = new StringBuilder();
		sql.append("ALTER TABLE " + table.getName() + "");
		sql.append(" DROP COLUMN " + columnName + ";");
		jdbcTemplate.execute(sql.toString());
	}

	/**
	 * <pre>
	 * 把column解析成Sql
	 * </pre>
	 *
	 * @param column
	 * @return
	 */
	private String columnToSql(Column column) {
		StringBuilder sb = new StringBuilder();
		sb.append("" + column.getName() + "");
		if (ColumnType.CLOB.equalsWithKey(column.getType())) {
			sb.append(" text");
		} else if (ColumnType.DATE.equalsWithKey(column.getType())) {
			sb.append(" datetime");
		} else if (ColumnType.INT.equalsWithKey(column.getType())) {
			sb.append(" int(" + column.getLength() +")");
		} else if (ColumnType.NUMBER.equalsWithKey(column.getType())) {
			sb.append(" decimal(" + column.getLength() + "," + column.getDecimal() + ")");
		} else if (ColumnType.VARCHAR.equalsWithKey(column.getType())) {
			sb.append(" varchar(" + column.getLength() + ")");
		}

		if (column.isRequired() ) {
			sb.append(" NOT NULL ");
		} else {
			sb.append(" NULL");
		}
		if (ColumnType.INT.equalsWithKey(column.getType()) && column.isPrimary()){
			sb.append(" auto_increment ");
		}

		if (StringUtils.isNotEmpty(column.getDefaultValue())) {
			//sb.append(" DEFAULT " + column.getDefaultValue() + "");
		}
		sb.append(" COMMENT '" + column.getComment() + "'");
		return sb.toString();
	}

}
