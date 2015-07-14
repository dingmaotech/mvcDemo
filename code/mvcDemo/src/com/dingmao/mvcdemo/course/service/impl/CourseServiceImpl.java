package com.dingmao.mvcdemo.course.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dingmao.mvcdemo.course.dao.CourseDao;
import com.dingmao.mvcdemo.course.model.Course;
import com.dingmao.mvcdemo.course.service.CourseService;
import com.dingmao.platform.model.page.Page;
import com.dingmao.platform.service.ServiceException;

/**
 * 功能:课程管理Service实现类.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Service
public class CourseServiceImpl implements CourseService {
	/**
	 * 注入Dao
	 */
	@Autowired
	private CourseDao courseDao;

	@Override
	@Transactional(readOnly = true)
	public Course getById(String id) throws ServiceException {
		return this.courseDao.get(id);
	}

	@Override
	@Transactional
	public Course save(Course entity) throws ServiceException {
		return this.courseDao.save(entity);
	}

	@Override
	@Transactional
	public void update(Course entity) throws ServiceException {
		this.courseDao.update(entity);
	}

	@Override
	@Transactional
	public int delete(String id) throws ServiceException {
		return this.courseDao.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(Collection<String> ids) throws ServiceException {
		this.courseDao.delete(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findAll() throws ServiceException {
		return this.courseDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Course> findPage(Page<Course> page) throws ServiceException {
		return this.courseDao.findPage(page);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Course> findPage(Page<Course> page, List<Parameter> parameters)
			throws ServiceException {
		return this.courseDao.findPage(page, parameters);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Course> findPage(Page<Course> page, String hql,
			Object... values) {
		return this.courseDao.findPage(page, hql, values);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Course> findPage(Page<Course> page, String hql,
			Map<String, Object> map) {
		return this.courseDao.findPage(page, hql, map);
	}

}
