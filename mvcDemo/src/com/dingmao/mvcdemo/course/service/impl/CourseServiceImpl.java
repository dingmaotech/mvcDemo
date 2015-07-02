package com.dingmao.mvcdemo.course.service.impl;

import java.util.Collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dingmao.mvcdemo.course.dao.CourseDao;
import com.dingmao.mvcdemo.course.model.Course;
import com.dingmao.mvcdemo.course.service.CourseService;
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
		return courseDao.get(id);
	}

	@Override
	@Transactional
	public Course save(Course entity) throws ServiceException {
		return courseDao.save(entity);
	}

	@Override
	@Transactional
	public void update(Course entity) throws ServiceException {
		courseDao.update(entity);
	}

	@Override
	@Transactional
	public int delete(String id) throws ServiceException {
		return courseDao.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(Collection<String> ids) throws ServiceException {
		courseDao.delete(ids);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Course> findAll() throws ServiceException {
		return courseDao.findAll();
	}

}
