package com.dingmao.platform.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * 功能:基础业务操作服务接口
 * 
 * @author 
 * @createTime 2015-5-13下午05:37:38
 * @version V1.0

 * @param <T>
 * @param <PK>
 */
public interface BaseService<T, PK extends Serializable> {

	/**
	 * 功能说明:依据主键获取数据
	 * 
	 * @param id
	 *            主键ID
	 * @return T
	 * @throws Exception
	 */
	T getById(PK id) throws ServiceException;

	/**
	 * 功能说明:插入/保存数据
	 * 
	 * @param entity
	 *            需要保存的对象
	 * @return void
	 * @throws Exception
	 */
	T save(T entity) throws ServiceException;

	/**
	 * 功能说明:更新/编辑数据
	 * 
	 * @param entity
	 *            需要更新的对象
	 * @return void
	 * @throws Exception
	 */
	void update(T entity) throws ServiceException;

	/**
	 * 功能说明:删除/移除数据
	 * 
	 * @param Collection
	 *            <PK> ids 主键IDS
	 * @return void
	 * @throws Exception
	 */
	void delete(Collection<PK> ids) throws ServiceException;
	
	/**
	 * 功能说明:删除/移除数据
	 * 
	 * @param PK id 主键ID
	 * @return void
	 * @throws Exception
	 */
	int delete(PK id) throws ServiceException;

	/**
	 * 功能说明:查询所有数据
	 * 
	 * @param
	 * @return List<T>
	 * @throws Exception
	 */
	List<T> findAll() throws ServiceException;

	/**
	 * 功能说明:分页查询数据
	 * 
	 * @param 当前分页信息
	 * @param request
	 *            请求对象
	 * @return Page<T> 分页对象
	 * @throws Exception
	 */
	//Page<T> findPage(Page<T> page) throws ServiceException;
}
