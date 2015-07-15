package com.dingmao.mvcdemo.course.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dingmao.mvcdemo.course.dao.CourseTreeDao;
import com.dingmao.mvcdemo.course.model.CourseTree;
import com.dingmao.mvcdemo.course.service.CourseTreeService;
import com.dingmao.platform.model.page.Page;
import com.dingmao.platform.service.ServiceException;

/**
 * 功能:课程树管理Service实现类.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Service
public class CourseTreeServiceImpl implements CourseTreeService {

	/**
	 * 注入Dao
	 */
	@Autowired
	private CourseTreeDao courseTreeDao;

	@Override
	@Transactional(readOnly = true)
	public CourseTree getById(Integer id) throws ServiceException {
		return this.courseTreeDao.get(id);
	}

	@Override
	@Transactional
	public CourseTree save(CourseTree entity) throws ServiceException {
		return this.courseTreeDao.save(entity);
	}

	@Override
	@Transactional
	public void update(CourseTree entity) throws ServiceException {
		this.courseTreeDao.update(entity);
	}

	@Override
	@Transactional
	public void delete(Collection<Integer> ids) throws ServiceException {
		this.courseTreeDao.delete(ids);
	}

	@Override
	@Transactional
	public int delete(Integer id) throws ServiceException {
		return this.courseTreeDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseTree> findAll() throws ServiceException {
		return this.courseTreeDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CourseTree> findPage(Page<CourseTree> page)
			throws ServiceException {
		return this.courseTreeDao.findPage(page);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CourseTree> findPage(Page<CourseTree> page,
			List<Parameter> parameters) throws ServiceException {
		return this.courseTreeDao.findPage(page, parameters);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseTree> findChildCourseTreeByPId(Integer pId) {
		return this.courseTreeDao.findChildCourseTreeByPId(pId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseTree> findAllChildCourseTreeByPId(Integer pId) {
		List<CourseTree> treeNodes = new ArrayList<CourseTree>();
		CourseTree node = this.courseTreeDao.get(pId);
		treeNodes.add(node);
		List<CourseTree> childNodes = this.courseTreeDao
				.findChildCourseTreeByPId(pId);
		List<CourseTree> subNodes = null;
		if (childNodes != null && childNodes.size() > 0) {
			for (CourseTree child : childNodes) {
				subNodes = findAllChildCourseTreeByPId(child.getId()); // 递归
				treeNodes.addAll(subNodes);
			}
		}
		return treeNodes;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CourseTree> findPage(Page<CourseTree> page, String hql,
			Object... values) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CourseTree> findPage(Page<CourseTree> page, String hql,
			Map<String, Object> map) {
		return null;
	}

}
