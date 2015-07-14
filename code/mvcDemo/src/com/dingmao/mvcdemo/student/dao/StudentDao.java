package com.dingmao.mvcdemo.student.dao;

import org.springframework.stereotype.Repository;

import com.dingmao.mvcdemo.student.model.Student;
import com.dingmao.platform.dao.base.HibernateDao;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class StudentDao extends HibernateDao<Student, String> {

}
