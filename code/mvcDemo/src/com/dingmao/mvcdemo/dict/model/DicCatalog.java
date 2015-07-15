package com.dingmao.mvcdemo.dict.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 功能：字典目录.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Entity
@Table(name = "T_MVCDEMO_DICCATALOG")
public class DicCatalog {

	/**
	 * 主键PK.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", length = 32)
	private java.lang.String id;
	/**
	 * 实力增加类别名称,
	 */
	@Column(name = "TABLE_NAME", length = 50)
	private java.lang.String name;
	/**
	 * 实力增加类代码.
	 */
	@Column(name = "CODE", length = 10)
	private java.lang.String code;
	/**
	 * 实力增加类别值.
	 */
	@Column(name = "VALUE", length = 50)
	private java.lang.String value;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

}
