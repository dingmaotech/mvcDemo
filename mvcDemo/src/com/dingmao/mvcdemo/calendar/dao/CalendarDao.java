package com.dingmao.mvcdemo.calendar.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dingmao.mvcdemo.calendar.model.Calendar;
import com.dingmao.platform.dao.base.HibernateDao;

/**
 * 功能:日程管理Dao实现类.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Repository
public class CalendarDao extends HibernateDao<Calendar, String> {

	public int deleteById(String id) {
		String hql = " delete from  Calendar c where c.id = ? ";
		Query query =  this.getSession().createQuery(hql);
		query.setParameter(0, id);
		return query.executeUpdate();
	}

}
