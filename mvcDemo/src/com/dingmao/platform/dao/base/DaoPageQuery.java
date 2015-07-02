package com.dingmao.platform.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dingmao.platform.model.Page;


public interface DaoPageQuery<T, PK extends Serializable> extends
		CrudDAOInterface<T, PK> {
/*
	*//**
	 * 基本的分页查询.
	 * 
	 * @param page
	 *            分页对象
	 * @return 分页对象
	 *//*
	Page<T> findPage(Page<T> page);

	*//**
	 * 带查询条件的分页查询.
	 * 
	 * @param page
	 *            分页对象
	 * @param parameters
	 *            查询条件集合
	 * @return 分页对象
	 *//*
	Page<T> findPage(Page<T> page, List<Parameter> parameters);

	*//**
	 * 按ql语句进行分页查询.
	 * 
	 * @param page
	 *            分页对象
	 * @param ql
	 *            ql语句
	 * @param values
	 *            查询条件
	 * @return 分页对象
	 *//*
	Page<T> findPage(Page<T> page, String ql, Object... values);

	*//**
	 * 按ql语句进行分页查询.
	 * 
	 * @param page
	 *            分页对象
	 * @param ql
	 *            ql语句
	 * @param values
	 *            查询条件
	 * @return 分页对象
	 *//*
	Page<T> findPage(Page<T> page, String ql, Map<String, Object> values);*/
}
