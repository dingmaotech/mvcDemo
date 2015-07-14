package com.dingmao.platform.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

/**
 * 功能:基本的增删改查接口
 * 
 * @param <T>
 *            实体对象
 * @param <PK>
 *            主键类型
 */
public interface CrudDAOInterface<T, PK extends Serializable> extends
		DaoInterface<T, PK> {

	SessionFactory sessionFactory = null;

	/**
	 * @功能说明:插入/保存一个对象
	 * @param entity
	 *            被保存的对象
	 * @return T 保存后生成ID的对象
	 * @throws Exception
	 */
	T save(T entity);

	/**
	 * @功能说明:插入/保存一个集合的对象 <p>
	 *                    数据量大于100以上需要考虑性能问题，不建议使用该方法
	 *                    </p>
	 * @param 被保存的集合
	 * @return List<T> Collection<T> 返回保存成功后的对象
	 * @throws Exception
	 */
	List<T> save(List<T> entities);

	/**
	 * 根据主键ID删除一个对象.
	 * 
	 * @param id
	 *            被删除对象的主键ID
	 */
	void delete(final PK id);

	/**
	 * 直接删除一个实体对象.
	 * 
	 * @param entity
	 *            被删除的对象
	 */
	void delete(final T entity);

	/**
	 * 根据条件批量删除对象.
	 * 
	 * @param dql
	 *            ql语句
	 * @param values
	 *            删除条件
	 */
	void delete(String dql, Object... values);

	/**
	 * 根据条件批量删除对象.
	 * 
	 * @param dql
	 *            ql语句
	 * @param values
	 *            删除条件
	 */
	void delete(String dql, Map<String, Object> values);

	/**
	 * 根据一个主键ID的集合删除对象
	 * 
	 * @param ids
	 *            被删除对象ID的集合
	 */
	void delete(Collection<PK> ids);

	/**
	 * 删除所有对象.
	 */
	void deleteAll();

	/**
	 * 更新一个对象.
	 * 
	 * @param entity
	 *            被更新的对象
	 */
	void update(T entity);

	/**
	 * 根据条件批量更新对象.
	 * 
	 * @param dql
	 *            ql语句
	 * @param values
	 *            更新条件
	 */
	void update(String dql, Object... values);

	/**
	 * 根据条件批量更新对象.
	 * 
	 * @param dql
	 *            ql语句
	 * @param values
	 *            更新条件
	 */
	void update(String dql, Map<String, Object> values);

	/**
	 * 根据主键ID查询一个对象,使用hibernate作为orm的时候请注意get/load区别.
	 * 
	 * @param id
	 *            主键ID
	 * @return T 查出的对象
	 */
	T get(final PK id);

	/**
	 * 根据传入的条件进行查询.可以是一条ql语句.
	 * 
	 * @param propertyName
	 *            查询参数
	 * @param value
	 *            值
	 * @return T 对象
	 */
	T getBy(final String propertyName, final Object value);

	/**
	 * 根据传入的ql语句查询一条记录.
	 * 
	 * @param dql
	 *            ql语句
	 * @param values
	 *            查询条件
	 * @return T 对象
	 */
	T getBy(final String dql, final Map<String, Object> values);

	/**
	 * 根据传入的条件进行查询,匹配方式为相等.
	 * 
	 * @param propertyName
	 *            查询参数
	 * @param value
	 *            值
	 * @return List<T> 对象
	 */
	public List<T> findBy(String propertyName, Object value);

	/**
	 * 根据主键ID查询一个对象,使用hibernate作为orm的时候请注意get/load区别.
	 * 
	 * @param id
	 *            主键ID
	 * @return T 查出的对象
	 */
	T load(final PK id);

	/**
	 * 返回所有对象.
	 * 
	 * @return Collection<T> 查询出的所有对象
	 */
	List<T> findAll();

	/**
	 * 根据ql语句进行查询.
	 * 
	 * @param dql
	 *            查询语句
	 * @param values
	 *            条件
	 * @return Collection<T> 对象集合
	 */
	List<T> find(String dql, Object... values);

	/**
	 * 根据ql语句和参数进行查询.
	 * 
	 * @param dql
	 *            查询语句
	 * @param values
	 *            条件
	 * @return Collection<T> 对象集合
	 */
	List<T> find(String dql, Map<String, Object> values);

	/**
	 * 根据主键ID的集合返回一个对象集合.
	 * 
	 * @param ids
	 *            主键ID的集合
	 * @return Collection<T> 对象的集合
	 */
	List<T> findAll(final Collection<PK> ids);

	/**
	 * 根据ID判断对象是否存在.
	 * 
	 * @param id
	 *            主键ID
	 * @return Boolean True/False
	 */
	Boolean isExists(final PK id);

	/**
	 * 判断数据库中Dao的泛型类所对应的表中propertyName字段的value值是否存在.
	 * 
	 * @param propertyName
	 *            字段名
	 * @param value
	 *            值
	 * @return Boolean true/false
	 */
	Boolean isExists(final String propertyName, final Object value);

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * <p/>
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 * 
	 * @param propertyName
	 *            字段名
	 * @param newValue
	 *            新的值
	 * @param oldValue
	 *            原值
	 * @return Boolean true/false
	 */
	Boolean isPropertyUnique(final String propertyName, final Object newValue,
			final Object oldValue);

	/**
	 * 返回存储的对象总数.
	 * 
	 * @return Long 总数
	 */
	Long count();

	/**
	 * 返回前num个对象.
	 * 
	 * @param num
	 *            多少条对象的多少
	 * @return Collection<T> 对象集合
	 */
	List<T> top(Integer num);

	/**
	 * 根据hql语句查询
	 * 
	 * @param dql
	 * @return
	 */
	List<T> find(String hql);
}
