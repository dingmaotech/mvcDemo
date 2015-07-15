package com.dingmao.mvcdemo.teacher.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dingmao.mvcdemo.teacher.dao.TeacherDao;
import com.dingmao.mvcdemo.teacher.model.Teacher;
import com.dingmao.mvcdemo.teacher.service.TeacherService;
import com.dingmao.platform.model.page.Page;
import com.dingmao.platform.service.ServiceException;

/**
 * 功能:教师管理Service实现类.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Service
public class TeacherServiceImpl implements TeacherService {
	/**
	 * 注入Dao
	 */
	@Autowired
	private TeacherDao teacherDao;

	@Override
	@Transactional(readOnly = true)
	public Teacher getById(String id) throws ServiceException {
		return teacherDao.get(id);
	}

	@Override
	@Transactional
	public Teacher save(Teacher entity) throws ServiceException {
		return teacherDao.save(entity);
	}

	@Override
	@Transactional
	public void update(Teacher entity) throws ServiceException {
		teacherDao.update(entity);
	}

	@Override
	@Transactional
	public int delete(String id) throws ServiceException {
		return teacherDao.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(Collection<String> ids) throws ServiceException {
		teacherDao.delete(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Teacher> findAll() throws ServiceException {
		return teacherDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Teacher> findPage(Page<Teacher> page) throws ServiceException {
		return teacherDao.findPage(page);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Teacher> findPage(Page<Teacher> page, List<Parameter> parameters)
			throws ServiceException {
		return teacherDao.findPage(page, parameters);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Teacher> findPage(Page<Teacher> page, String hql,
			Object... values) {
		return teacherDao.findPage(page, hql, values);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Teacher> findPage(Page<Teacher> page, String hql,
			Map<String, Object> map) {
		return teacherDao.findPage(page, hql, map);
	}

}
