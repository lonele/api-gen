package com.jiadao.db;

import org.springframework.jdbc.core.JdbcTemplate;

public class TableOperatorFactory {
	private TableOperatorFactory() {

	}

	/**
	 * <pre>
	 * 构建一个TableOperator
	 * </pre>
	 *
	 * @param type
	 * @param table
	 * @param jdbcTemplate
	 * @return
	 */
	public static TableOperator newOperator(String type, Table<?> table, JdbcTemplate jdbcTemplate) {
		if (DbType.MYSQL.equalsWithKey(type)) {
			return new MysqlTableOperator(table, jdbcTemplate);
		}
		if (DbType.ORACLE.equalsWithKey(type)) {
			// return new OracleTableOperator(table, jdbcTemplate);
		}
		return null;
	}
	
}
