package com.dingmao.platform.dao.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.dingmao.platform.util.reflect.ReflectionUtil;

/**
 * 功能:Hibernate基础CRUD数据操作实现类
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 * 
 * @param <T>
 *            泛型实体
 * @param <PK>
 *            泛型主键类型
 */
public class HibernateDao<T, PK extends Serializable> implements
		DaoPageQuery<T, PK>, CrudDAOInterface<T, PK>, DaoInterface<T, PK> {

	/**
	 * Logger.
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * hibernate sessionFactory.
	 */
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/**
	 * 实体类.
	 */
	protected Class<T> entityClass;

	/**
	 * 获取Hibernate的sessionFactory.
	 * 
	 * @return hibernate的sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 设置sessionFactory的注入.
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 默认的无参构造函数,默认情况下xml配置的hibernate sessionFactory的id=sessionFactory.
	 */
	public HibernateDao() {
		this.entityClass = ReflectionUtil.getSuperClassGenricType(getClass());
	}

	/**
	 * 返回一个Hibernate的Session.
	 * 
	 * @return Session Hibernate的Session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	/**
	 * 保存一个对象.
	 * 
	 * @param entity
	 *            被保存的对象
	 * @return T 保存后生成ID的对象
	 */
	@Override
	public T save(T entity) {
		Assert.notNull(entity, "entity is not null!!!");
		Session session = getSession();
		session.saveOrUpdate(entity);
		logger.debug("save entity: {}", entity);
		return entity;
	}

	/**
	 * 保存一个集合的对象.
	 * 
	 * @param entities
	 *            被保存的集合
	 * @return Collection<T> 返回保存成功后的对象
	 */
	@Override
	public List<T> save(List<T> entities) {
		List<T> list = new ArrayList<T>();
		Assert.notNull(entities, "entities is not null!!!");
		Assert.notEmpty(entities, "entities is not empty!!!");
		for (T t : entities) {
			list.add(save(t));
		}
		logger.debug("save entities: {}", entities);
		return list;
	}

	/**
	 * 根据主键ID删除一个对象.
	 * 
	 * @param id
	 *            被删除对象的主键ID
	 */
	@Override
	public void delete(PK id) {
		Assert.notNull(id, "id is not null!!!");
		delete(get(id));
		logger.debug("delete id: {}", id);
	}
	
	public int deleteById(PK id) {
		Session session = getSession();
		Query query = session.createQuery(" delete  from " + entityClass.getSimpleName() +"  where "+this.getIdName()+"=?");
		query.setParameter(0, id);
		logger.debug("delete all entity {} ", entityClass);
		return query.executeUpdate();
	}

	/**
	 * 直接删除一个实体对象.
	 * 
	 * @param entity
	 *            被删除的对象
	 */
	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "entity is not null!!!");
		Session session = getSession();
		session.delete(entity);
		logger.debug("delete entity {}", entity);
	}

	/**
	 * 根据条件批量删除对象.
	 * 
	 * @param dql
	 *            ql语句
	 * @param values
	 *            删除条件
	 */
	public void delete(String dql, Object... values) {
		Assert.notNull(values, "values is not null!!!");
		Query q = createQuery(dql, values);
		q.executeUpdate();
	}

	/**
	 * 根据条件批量删除对象.
	 * 
	 * @param dql
	 *            ql语句
	 * @param values
	 *            删除条件
	 */
	public void delete(String dql, Map<String, Object> values) {
		Assert.notNull(values, "values is not null!!!");
		Query q = createQuery(dql, values);
		q.executeUpdate();
	}

	/**
	 * 根据一个主键ID的集合删除对象
	 * 
	 * @param ids
	 *            被删除对象ID的集合
	 */
	@Override
	public void delete(Collection<PK> ids) {
		Assert.notNull(ids, "ids is not null!!!");
		Assert.notEmpty(ids, "ids is not empty!!!");
		for (PK id : ids) {
			delete(id);
		}
		logger.debug("delete ids {}", ids);
	}

	/**
	 * 删除所有对象.
	 */
	@Override
	public void deleteAll() {
		Session session = getSession();
		session.createQuery(" delete * from " + entityClass).executeUpdate();
		logger.debug("delete all entity {} ", entityClass);
	}

	/**
	 * 更新一个对象.
	 * 
	 * @param entity
	 *            被更新的对象
	 */
	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 根据条件批量更新对象.
	 * 
	 * @param dql
	 *            ql语句
	 * @param values
	 *            更新条件
	 */
	public void update(String dql, Object... values) {
		Assert.notNull(values, "values is not null!!!");
		Query q = createQuery(dql, values);
		q.executeUpdate();
	}

	/**
	 * 根据条件批量更新对象.
	 * 
	 * @param dql
	 *            ql语句
	 * @param values
	 *            更新条件
	 */
	public void update(String dql, Map<String, Object> values) {
		Assert.notNull(values, "values is not null!!!");
		Query q = createQuery(dql, values);
		q.executeUpdate();
	}

	/**
	 * 根据主键ID查询一个对象.
	 * 
	 * @param id
	 *            主键ID
	 * @return T 查出的对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get(PK id) {
		return (T) getSession().get(entityClass, id);
	}

	/**
	 * 根据传入的条件进行查询,匹配方式为相等.
	 * 
	 * @param propertyName
	 *            查询参数
	 * @param value
	 *            值
	 * @return T 对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName is not null!!!");
		Criterion criterion = Restrictions.eq(propertyName, value);
		List<T> list = createCriteria(criterion).list();
		return list != null && !list.isEmpty() ? list.get(0) : null;
	}

	/**
	 * 根据传入的ql语句查询一条记录.
	 * 
	 * @param dql
	 *            ql语句
	 * @param values
	 *            查询条件
	 * @return T 对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getBy(String dql, Map<String, Object> values) {
		return (T) createQuery(dql, values).setCacheable(true).uniqueResult();
	}

	/**
	 * 根据传入的条件进行查询,匹配方式为相等.
	 * 
	 * @param propertyName
	 *            查询参数
	 * @param value
	 *            值
	 * @return List<T> 对象
	 */
	@SuppressWarnings("unchecked")
	public List<T> findBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName is not null!!!");
		Criterion criterion = Restrictions.eq(propertyName, value);
		List<T> list = createCriteria(criterion).list();
		return list;
	}

	/**
	 * 根据主键ID查询一个对象,使用hibernate作为orm的时候请注意get/load区别.
	 * 
	 * @param id
	 *            主键ID
	 * @return T 查出的对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T load(PK id) {
		Assert.isNull(id, "is not null!!!");
		return (T) getSession().load(entityClass, id);
	}

	/**
	 * 返回所有对象.
	 * 
	 * @return Collection<T> 查询出的所有对象
	 */
	@Override
	public List<T> findAll() {
		return find();
	}

	/**
	 * 根据ql语句进行查询.
	 * 
	 * @param dql
	 *            查询语句
	 * @param values
	 *            条件
	 * @return Collection<T> 对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String dql, Object... values) {
		return createQuery(dql, values).list();
	}

	/**
	 * 根据ql语句进行查询.
	 * 
	 * @param dql
	 *            查询语句
	 * @param values
	 *            条件
	 * @return Collection<T> 对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql) {
		return createQuery(hql).list();
	}

	/**
	 * 根据ql语句和参数进行查询.
	 * 
	 * @param dql
	 *            查询语句
	 * @param values
	 *            条件
	 * @return Collection<T> 对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String dql, Map<String, Object> values) {
		return createQuery(dql, values).list();
	}

	/**
	 * 根据主键ID的集合返回一个对象集合.
	 * 
	 * @param ids
	 *            主键ID的集合
	 * @return Collection<T> 对象的集合
	 */
	@Override
	public List<T> findAll(Collection<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	/**
	 * 根据ID判断对象是否存在.
	 * 
	 * @param id
	 *            主键ID
	 * @return Boolean True/False
	 */
	@Override
	public Boolean isExists(PK id) {
		return get(id) != null ? true : false;
	}

	/**
	 * 判断数据库中Dao的泛型类所对应的表中propertyName字段的value值是否存在.true是存在,false是不存在.
	 * 
	 * @param propertyName
	 *            字段名
	 * @param value
	 *            值
	 * @return Boolean true/false
	 */
	@Override
	public Boolean isExists(String propertyName, Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return createCriteria(criterion).list().isEmpty();
	}

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
	@Override
	public Boolean isPropertyUnique(String propertyName, Object newValue,
			Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Criterion criterion = Restrictions.eq(propertyName, newValue);
		return createCriteria(criterion).uniqueResult() != null ? true : false;
	}

	/**
	 * 返回存储的对象总数.
	 * 
	 * @return Long 总数
	 */
	@Override
	public Long count() {
		String countHql = "select count(*) from " + entityClass.getSimpleName();
		try {
			Long count = findUnique(countHql);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	/**
	 * 返回前num个对象.
	 * 
	 * @param num
	 *            多少条对象的多少
	 * @return Collection<T> 对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> top(Integer num) {
		Query query = getSession().createQuery("from " + entityClass);
		query.setCacheable(true);
		query.setFirstResult(0);
		query.setMaxResults(num);
		return query.list();
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> find(final Criterion... criterion) {
		return createCriteria(criterion).setCacheable(true).list();
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * <p/>
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 */
	protected Criteria createCriteria(final Criterion... criterion) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.setCacheable(true);
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	@SuppressWarnings("unchecked")
	protected <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).setCacheable(true).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	@SuppressWarnings("unchecked")
	protected <X> X findUnique(final String hql,
			final Map<String, Object> values) {
		return (X) createQuery(hql, values).setCacheable(true).uniqueResult();
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * <p/>
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	public long countHqlResult(final String hql, final Object... values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * <p/>
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected Integer countHqlResult(final String hql,
			final Map<String, Object> values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Integer count = ((Number) findUnique(countHql, values)).intValue();
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数.
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	protected Integer countCriteriaResult(final Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;
		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtil.getFieldValue(impl,
					"orderEntries");
			ReflectionUtil.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error(
					"[jcommon] HibernateDao countCriteriaResult() error:{}", e
							.getMessage());
		}
		c.setCacheable(true);
		// 执行Count查询
		Integer totalCount = (Integer) c.setProjection(Projections.rowCount())
				.uniqueResult();
		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);
		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtil.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error(
					"[jcommon] HibernateDao countCriteriaResult() error:{}", e
							.getMessage());
		}

		return totalCount;
	}

	/**
	 * 根据DetachedCriteria去获取本次查询所能获得的对象总数
	 * 
	 * @param {@link DetachedCriteria}
	 */
	protected Long countDetachedCriteriaResult(final DetachedCriteria dc) {
		String count = dc.setProjection(Projections.rowCount())
				.getExecutableCriteria(getSession()).setCacheable(true).list()
				.get(0).toString();
		return Long.parseLong(count);
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * <p/>
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	protected Query createQuery(final String queryString,
			final Object... values) {
		Assert.hasText(queryString, "queryString is not null!!!");
		Query query = getSession().createQuery(queryString);
		query.setCacheable(true);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	protected Query createQuery(final String queryString,
			final Map<String, Object> values) {
		Assert.hasText(queryString, "queryString is not null!!!");
		Query query = getSession().createQuery(queryString);
		query.setCacheable(true);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

/*	*//**
	 * 设置分页参数到Query对象,辅助函数.
	 *//*
	protected Query setPageParameter(final Query q, final Page<T> page) {
		// hibernate的firstResult的序号从0开始
		q.setCacheable(true);
		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize());
		return q;
	}

	*//**
	 * 设置分页参数到Criteria对象,辅助函数.
	 *//*
	protected Criteria setPageParameter(final Criteria c, final Page<T> page) {
		// hibernate的firstResult的序号从0开始
		c.setCacheable(true);
		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());

		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');
			Assert
					.isTrue(
							orderByArray.length == orderArray.length,
							"Page multiple sort parameters in the sort field and sort direction is not equal to the number of");
			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}*/

	/**
	 * 取得对象的主键名.
	 */
	private String getIdName() {
		ClassMetadata meta = sessionFactory.getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	/**
	 * 取得对象属性类型
	 * 
	 * @return
	 */
	public Type getPropertyTypes(String name) {
		ClassMetadata meta = sessionFactory.getClassMetadata(entityClass);

		return meta.getPropertyType(name);
	}

	/**
	 * 取得对象属性名称
	 * 
	 * @return
	 */
	public String[] getPropertyNames() {
		ClassMetadata meta = sessionFactory.getClassMetadata(entityClass);
		return meta.getPropertyNames();
	}

	/**
	 * 获得 Criteria
	 * 
	 * @return
	 */
	public Criteria getCriteria() {
		return getSession().createCriteria(entityClass);
	}
	
	/**
	 * @功能说明:基本的分页查询.
	 * @param 分页对象
	 * @return Page<T>
	 * @throws
	 *//*
	@SuppressWarnings("unchecked")
	@Override
	public Page<T> findPage(Page<T> page) {
		Assert.notNull(page, "page is not null!!!");
		Criteria criteria = getSession().createCriteria(entityClass);
		if (page.isAutoCount()) {
			Integer totalCount = countCriteriaResult(criteria);
			page.setTotalRows(totalCount);
		}
		setPageParameter(criteria, page);
		page.setResult(criteria.setCacheable(true).list());
		return page;
	}

	*//**
	 * @功能说明:带查询条件的分页查询
	 * @param page
	 *            分页对象
	 * @param parameters
	 *            查询条件集合
	 * @return Page<T>
	 * @throws
	 *//*
	@SuppressWarnings( { "rawtypes", "unchecked" })
	@Override
	public Page<T> findPage(Page<T> page, List<Parameter> parameters) {
		DetachedCriteria dc = HibernateCriteria.getDetachedCriteria(parameters,
				entityClass);
		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');
			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					dc.addOrder(Order.asc(orderByArray[i]));
				} else {
					dc.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		if (page.isAutoCount()) {
			if (page.getTotalRows() == -1) {
				Long totalCount = countDetachedCriteriaResult(dc);
				page.setTotalRows(totalCount);
				dc.setProjection(null);
			}
		}
		Criteria criteria = dc.getExecutableCriteria(getSession());
		criteria.setCacheable(true);
		criteria.setFirstResult(page.getFirst());
		criteria.setMaxResults(page.getPageSize());
		List result = criteria.list();
		page.setResult(result);
		return page;
	}

	*//**
	 * @功能说明:按hql语句进行分页查询,暂时无法支持oderBy参数
	 * @param page
	 *            分页对象
	 * @param hql
	 *            hql语句
	 * @return
	 * @throws Exception
	 *//*
	@SuppressWarnings( { "rawtypes", "unchecked" })
	@Override
	public Page<T> findPage(Page<T> page, String hql, Object... values) {
		Assert.notNull(page, "page is not null!!!");
		Query q = createQuery(hql, values);
		if (page.isAutoCount()) {
			Long totalCount = countHqlResult(hql, values);
			page.setTotalRows(totalCount);
		}
		setPageParameter(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}

	*//**
	 * @功能说明:按hql语句进行分页查询,暂时无法支持oderBy参数.
	 * @param page
	 *            分页对象
	 * @param hql
	 *            hql语句
	 * @param values
	 *            查询条件
	 * @return 分页对象
	 * @throws
	 *//*
	@SuppressWarnings( { "rawtypes", "unchecked" })
	@Override
	public Page<T> findPage(Page<T> page, String hql, Map<String, Object> values) {
		Assert.notNull(page, "page is not null!!!");
		Query q = createQuery(hql, values);
		if (page.isAutoCount()) {
			Integer totalCount = countHqlResult(hql, values);
			page.setTotalRows(totalCount);
		}
		setPageParameter(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}*/
}
