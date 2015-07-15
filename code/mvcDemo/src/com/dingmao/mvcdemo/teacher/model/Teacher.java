package com.dingmao.mvcdemo.teacher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import com.dingmao.platform.enums.SexEnum;
import com.dingmao.platform.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: 课程老师
 * @author
 * @date 2013-08-31 22:52:17
 * @version V1.0
 * 
 */
@Entity
@Table(name = "T_MVCDEMO_TEACHER", schema = "")
public class Teacher implements java.io.Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -8092770268079427275L;

	/** 老师id */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 32)
	private String id;

	/** 老师姓名 */
	@Excel(exportName = "老师姓名", orderNum = "2", needMerge = true)
	@Column(name = "NAME", nullable = true, length = 12)
	private String name;
	
	/**
	 * 性别
	 */
	@Excel(exportName = "性别", imExConvert = 1, orderNum = "3")
	@Column(name = "sex", nullable = true, length = 2)
	private String sex;

	//@Excel(exportName = "老师照片", orderNum = "3", exportType = 2, exportFieldHeight = 15, exportFieldWidth = 20)
	@Column(name = "PIC_URL", nullable = true, length = 12)
	private String pic;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String convertGetSex() {
		return SexEnum.getName(this.sex);
	}

	public void convertSetSex(String sex) {
		this.sex =SexEnum.getCode(this.sex) ;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}
