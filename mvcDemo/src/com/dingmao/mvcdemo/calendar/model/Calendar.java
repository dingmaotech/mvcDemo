package com.dingmao.mvcdemo.calendar.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 *功能：日程管理实体对象
 * <p>
 * 修改历史：对程序的修改历史进行记录
 * </p>
 * yangss 2014年8月10日 创建日程管理实体对象 </p>
 */
@Entity
@Table(name = "T_MVCDEMO_CALENDAR")
public class Calendar implements java.io.Serializable {

	private static final long serialVersionUID = -9054724291967437560L;

	/**
	 * 日程id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;

	/**
	 * 日程类型（公司日程,部门日常，个人日程）.
	 */
	@Column(name = "RCLX", length = 20)
	private String rclx;

	/**
	 * 日程主题.
	 */
	@Column(name = "TITLE", length = 50)
	private String title;

	/**
	 * 开始时间.
	 */
	@Column(name = "STARTTIME")
	private Date startTime;

	/**
	 * 结束时间.
	 */
	@Column(name = "ENDTIME")
	private Date endTime;

	/**
	 * 是否一整天.
	 */
	@Column(name = "ALLDAY", length = 1)
	private String allday;

	/**
	 * 日程状态.
	 */
	@Column(name = "RCZT", length = 1)
	private String rczt;

	/**
	 * 显示颜色.
	 */
	@Column(name = "COLOR", length = 1)
	private String color;

	/**
	 * 申请明细集合.
	 */
	@OneToMany(mappedBy = "calendar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CalendarMx> calendarMx = new ArrayList<CalendarMx>();

	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the rclx
	 */
	public String getRclx() {
		return rclx;
	}

	/**
	 * @param sqlx
	 *            the sqlx to set
	 */
	public void setRclx(String rclx) {
		this.rclx = rclx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAllday() {
		return allday;
	}

	public void setAllday(String allday) {
		this.allday = allday;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRczt() {
		return rczt;
	}

	public void setRczt(String rczt) {
		this.rczt = rczt;
	}

	public List<CalendarMx> getCalendarMx() {
		return calendarMx;
	}

	public void setCalendarMx(List<CalendarMx> calendarMx) {
		this.calendarMx = calendarMx;
	}

}
