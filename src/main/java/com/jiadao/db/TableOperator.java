package com.jiadao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.Map.Entry;

public abstract class TableOperator {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
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
	public TableOperator(Table<? extends Column> table, JdbcTemplate jdbcTemplate) {
		super();
		this.table = table;
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * <pre>
	 * 返回的数据库类型
	 * 枚举：DbType
	 * </pre>
	 *
	 * @return
	 */
	public abstract String type();

	/**
	 * <pre>
	 * 创建表
	 * </pre>
	 */
	public void createTable() {
	}

	/**
	 * <pre>
	 * 删除表
	 * </pre>
	 */
	public void dropTable() {
		if (!isTableCreated()) {
			return;
		}
		String sql = "drop table " + table.getName() + "";
		jdbcTemplate.execute(sql);
	}

	/**
	 * <pre>
	 * 表是否被生成过
	 * 或者说，表是否已存在数据库
	 * </pre>
	 *
	 * @return
	 */
	public boolean isTableCreated() {
		return false;
	}

	/**
	 * <pre>
	 * 增加字段
	 * </pre>
	 *
	 * @param column
	 *            字段
	 */
	public void addColumn(Column column) {

	}

	/**
	 * <pre>
	 * 更新字段
	 * </pre>
	 *
	 * @param column
	 *            字段
	 */
	public void updateColumn(Column column) {

	}

	/**
	 * <pre>
	 * 删除字段
	 * </pre>
	 *
	 * @param column
	 */
	public void dropColumn(String columnName) {

	}


}
