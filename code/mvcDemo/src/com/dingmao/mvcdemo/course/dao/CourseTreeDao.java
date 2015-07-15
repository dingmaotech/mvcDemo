package com.dingmao.mvcdemo.course.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dingmao.mvcdemo.course.model.CourseTree;
import com.dingmao.platform.dao.base.HibernateDao;

/**
 * 功能:课程树管理Dao实现类.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Repository
public class CourseTreeDao extends HibernateDao<CourseTree, Integer> {

	public List<CourseTree> findChildCourseTreeByPId(Integer pId) {
		String hql = " from  CourseTree tree WHERE tree.pId = ? ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, pId);
		List<CourseTree> list = query.list();
		return list;
	}

}
