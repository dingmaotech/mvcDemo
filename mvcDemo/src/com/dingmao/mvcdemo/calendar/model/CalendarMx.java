package com.dingmao.mvcdemo.calendar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

/**
 * 功能 ：日程管理明细实体对象
 * <p>
 * 修改历史：对程序的修改历史进行记录
 * </p>
 * <p>
 * yangss 2014年8月10日 创建日程管理明细实体对象
 * </p>
 */
@Entity
@Table(name = "T_MVCDEMO_CALENDARMX")
public class CalendarMx implements java.io.Serializable {

	private static final long serialVersionUID = -4853021416741860266L;
	/**
	 * 日程明细id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;

	/**
	 * 日程实体类.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CALENDAR_ID")
	@ForeignKey(name = "FK_T_RCGL_CALENDAR_ID")
	@JsonIgnore
	private Calendar calendar;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
}
