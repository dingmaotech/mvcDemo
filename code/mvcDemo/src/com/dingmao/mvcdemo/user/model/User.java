package com.dingmao.mvcdemo.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.dingmao.platform.poi.excel.annotation.Excel;

@Entity
@Table(name = "T_MVCDEMO_USER")
public class User {
	/**
	 * ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;
	/**
	 * 姓名
	 */
	@Column(name = "USERNAME", length = 30)
	@Excel(exportName = "用户姓名", exportFieldWidth = 25)
	private String username;

	/**
	 * 密码
	 */
	@Excel(exportName = "用户密码", exportFieldWidth = 25)
	@Column(name = "PASSWORD", length = 50)
	private String password;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
