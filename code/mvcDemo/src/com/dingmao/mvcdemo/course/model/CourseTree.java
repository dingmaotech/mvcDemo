package com.dingmao.mvcdemo.course.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.dingmao.platform.model.tree.TreeItem;

@Entity
@Table(name = "T_MVCDEMO_CourseTree")
public class CourseTree extends TreeItem {

	@Column(name = "No", length = 50)
	private String No;
	@Column(name = "Type", length = 50)
	private String Type;
	@Column(name = "bz", length = 50)
	private String bz;
	@Column(name = "eduId", length = 50)
	private String eduId;
	@Column(name = "imgSrc", length = 50)
	private String imgSrc;
	
	@Transient
	private boolean open = false;
	@Transient
	private boolean isParent = false;
	@Transient
	private  List<CourseTree>  children ;

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getEduId() {
		return eduId;
	}

	public void setEduId(String eduId) {
		this.eduId = eduId;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public List<CourseTree> getChildren() {
		return children;
	}

	public void setChildren(List<CourseTree> children) {
		this.children = children;
	}

}
