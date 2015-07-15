package com.dingmao.mvcdemo.course.dao;

import org.springframework.stereotype.Repository;

import com.dingmao.mvcdemo.course.model.Course;
import com.dingmao.platform.dao.base.HibernateDao;

/**
 * 功能:课程管理Dao实现类.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Repository
public class CourseDao extends HibernateDao<Course, String> {

}
