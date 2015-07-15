package com.dingmao.mvcdemo.student.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dingmao.mvcdemo.student.dao.StudentDao;
import com.dingmao.mvcdemo.student.model.Student;
import com.dingmao.mvcdemo.student.service.StudentService;
import com.dingmao.platform.model.page.Page;
import com.dingmao.platform.service.ServiceException;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	@Transactional(readOnly = true)
	public Student getById(String id) throws ServiceException {
		return this.studentDao.get(id);
	}

	@Override
	@Transactional
	public Student save(Student entity) throws ServiceException {
		return this.studentDao.save(entity);
	}

	@Override
	@Transactional
	public void update(Student entity) throws ServiceException {
		this.studentDao.update(entity);
	}

	@Override
	@Transactional
	public int delete(String id) throws ServiceException {
		return this.studentDao.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(Collection<String> ids) throws ServiceException {
		this.studentDao.delete(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Student> findAll() throws ServiceException {
		return this.studentDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Student> findPage(Page<Student> page) throws ServiceException {
		return this.studentDao.findPage(page);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Student> findPage(Page<Student> page, List<Parameter> parameters)
			throws ServiceException {
		return this.studentDao.findPage(page, parameters);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Student> findPage(Page<Student> page, String hql,
			Object... values) {
		return this.studentDao.findPage(page, hql, values);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Student> findPage(Page<Student> page, String hql,
			Map<String, Object> map) {
		return this.studentDao.findPage(page, hql, map);
	}

}
