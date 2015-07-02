package com.dingmao.mvcdemo.course.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import com.dingmao.mvcdemo.student.model.Student;
import com.dingmao.mvcdemo.teacher.model.Teacher;
import com.dingmao.platform.poi.excel.annotation.Excel;
import com.dingmao.platform.poi.excel.annotation.ExcelCollection;
import com.dingmao.platform.poi.excel.annotation.ExcelEntity;


@Entity
@Table(name = "t_mvcdemo_course")
public class Course implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 0x632d5a7260dc9f15L;
	
	/**
	 * 主键ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	private java.lang.String id;
	
	/**
	 * 课程名称
	 */
	@Excel(exportName="课程名称",orderNum="1",needMerge=true)
	@Column(name ="NAME",nullable=true,length=25)
	private java.lang.String name;
	
	/**
	 * 老师主键
	 */
	@ExcelEntity(exportName = "")
	@ManyToOne(cascade=CascadeType.REMOVE)
	private Teacher teacher;
	
	@ExcelCollection(exportName="选课学生",orderNum="4")
	@OneToMany(mappedBy="course",cascade=CascadeType.REMOVE)
	private List<Student> students;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List getStudents() {
		return students;
	}

	public void setStudents(List students) {
		this.students = students;
	}
}
