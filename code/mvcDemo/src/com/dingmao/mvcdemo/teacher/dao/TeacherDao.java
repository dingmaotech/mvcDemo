package com.dingmao.mvcdemo.teacher.dao;

import org.springframework.stereotype.Repository;

import com.dingmao.mvcdemo.teacher.model.Teacher;
import com.dingmao.platform.dao.base.HibernateDao;

/**
 * 功能:教师管理Dao实现类.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Repository
public class TeacherDao extends HibernateDao<Teacher, String> {

}
