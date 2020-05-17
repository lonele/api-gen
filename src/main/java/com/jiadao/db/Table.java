package com.jiadao.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Table<C extends Column> implements Serializable{


	/**
	 * 表名
	 */
	protected String db;

	/**
	 * 表名
	 */
	protected String name;
	/**
	 * 描述
	 */
	protected String comment;
	/**
	 * 字段
	 */
	protected List<C> columns;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<C> getColumns() {
		return columns;
	}

	public void setColumns(List<C> columns) {
		this.columns = columns;
	}

	/**
	 * <pre>
	 * 系统只支持唯一主键的场景
	 * </pre>
	 *
	 * @return
	 */
	public C getPkColumn() {
		if (this.columns == null) {
			return null;
		}
		List<C> list = new ArrayList<>();
		for (C c : this.getColumns()) {
			if (c.isPrimary()) {
				list.add(c);
			}
		}
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * <pre>
	 * 根据name获取字段
	 * </pre>	
	 * @param name
	 * @return
	 */
	public C getColumn(String name) {
		if (this.columns == null) {
			return null;
		}
		for (C c : columns) {
			if (name.equalsIgnoreCase(c.getName())) {
				return c;
			}
		}
		return null;
	}
}
