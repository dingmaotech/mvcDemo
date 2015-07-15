package com.dingmao.mvcdemo.student.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import com.dingmao.mvcdemo.course.model.Course;
import com.dingmao.platform.enums.SexEnum;
import com.dingmao.platform.poi.excel.annotation.Excel;

@Entity
@Table(name = "T_MVCDEMO_STUDENT")
public class Student implements java.io.Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -8429676695430789213L;

	/**
	 * 学生id
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "stuId", nullable = false, length = 32)
	private String stuId;

	/**
	 * 学生学号
	 */
	@Excel(exportName = "学号", orderNum = "1")
	@Column(name = "stuNo", nullable = false, length = 32)
	private String stuNo;

	/**
	 * 学生姓名
	 */
	@Excel(exportName = "姓名", orderNum = "2")
	@Column(name = "stuName", nullable = true, length = 32)
	private String stuName;

	/**
	 * 学生性别
	 */
	@Excel(exportName = "性别", imExConvert = 1, orderNum = "3")
	@Column(name = "stuSex", nullable = true, length = 2)
	private String stuSex;

	/**
	 * 家庭电话
	 */
	@Excel(exportName = "家庭电话", orderNum = "4")
	@Column(name = "homeTel", nullable = false, length = 20)
	private String homeTel;

	/**
	 * 家庭地址
	 */
	@Excel(exportName = "家庭地址", orderNum = "5", exportFieldWidth = 20, isWrap = true)
	@Column(name = "stuHomeAddr", nullable = true, length = 50)
	private String homeAddr;

	/**
	 * 学生电话
	 */
	@Excel(exportName = "学生电话", exportFieldWidth = 15, orderNum = "6")
	@Column(name = "stuTel", nullable = true, length = 20)
	private String stuTel;

	/**
	 * 学生宿舍地址
	 */
	@Excel(exportName = "宿舍地址", exportFieldWidth = 20, isWrap = true, orderNum = "7")
	@Column(name = "dormitoryAddr", nullable = true, length = 50)
	private String dormitoryAddr;

	/**
	 * 学生出生年月日
	 */
	@Excel(exportName = "出生日期", exportFormat = "yyyy-MM-dd", importFormat = "yyyy-MM-dd", orderNum = "8")
	@Column(name = "BIRTHDAY", nullable = true)
	private Date birthday;

	/** 课程主键 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COURSE_ID")
	private Course course;

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getHomeAddr() {
		return homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getStuTel() {
		return stuTel;
	}

	public void setStuTel(String stuTel) {
		this.stuTel = stuTel;
	}

	public String getDormitoryAddr() {
		return dormitoryAddr;
	}

	public void setDormitoryAddr(String dormitoryAddr) {
		this.dormitoryAddr = dormitoryAddr;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


	public String convertGetStuSex() {
		return this.stuSex.equals("0") ? "男生" : "女生";
	}

	public void convertSetStuSex(String stuSex) {
		this.stuSex = stuSex.equals("男生") ? "0" : "1";
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
