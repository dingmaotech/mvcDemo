package com.dingmao.mvcdemo.calendar.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dingmao.mvcdemo.calendar.dao.CalendarDao;
import com.dingmao.mvcdemo.calendar.model.Calendar;
import com.dingmao.mvcdemo.calendar.model.CalendarMx;
import com.dingmao.mvcdemo.calendar.service.CalendarService;
import com.dingmao.platform.model.page.Page;
import com.dingmao.platform.service.ServiceException;

/**
 * 功能:日程管理Service实现类.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Service
public class CalendarServiceImpl implements CalendarService {
	/**
	 * 注入Dao
	 */
	@Autowired
	private CalendarDao calendarDao;

	/**
	 * 根据Id查询年度申请实体
	 */
	@Override
	@Transactional(readOnly = true)
	public Calendar getById(String id) throws ServiceException {
		return this.calendarDao.get(id);
	}

	/**
	 * 保存实体
	 */
	@Transactional
	@Override
	public Calendar save(Calendar entity) throws ServiceException {
		for (CalendarMx m : entity.getCalendarMx()) {
			m.setCalendar(entity);
		}
		return this.calendarDao.save(entity);
	}

	/**
	 * 更新实体
	 */
	@Override
	@Transactional
	public void update(Calendar entity) throws ServiceException {
		for (CalendarMx m : entity.getCalendarMx()) {
			m.setCalendar(entity);
		}
		this.calendarDao.update(entity);
	}

	@Override
	@Transactional
	public int delete(String id) throws ServiceException {
		return calendarDao.deleteById(id);
	}

	/**
	 * 根据ids删除实体
	 */
	@Override
	@Transactional
	public void delete(Collection<String> ids) throws ServiceException {
		this.calendarDao.delete(ids);
	}

	/**
	 * 查询全部的实体
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Calendar> findAll() throws ServiceException {
		return this.calendarDao.findAll();
	}

	/**
	 * 分页查询实体
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Calendar> findPage(Page<Calendar> page) throws ServiceException {
		return this.calendarDao.findPage(page, page.getParameterList());
	}

	/**
	 * 分页查询实体
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Calendar> findPage(Page<Calendar> page,
			List<Parameter> parameters) throws ServiceException {
		return this.calendarDao.findPage(page, parameters);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Calendar> findPage(Page<Calendar> page, String hql,
			Object... values) {
		return this.calendarDao.findPage(page, hql, values);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Calendar> findPage(Page<Calendar> page, String hql,
			Map<String, Object> map) {
		return this.calendarDao.findPage(page, hql, map);
	}

}
