package com.dingmao.platform.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 功能:弹出提示信息
 *<p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
public class MessageDto implements Serializable {
	/**
	 * @Fields serialVersionUID:序列化字段
	 */
	@JsonIgnore
	private static final long serialVersionUID = 0x7b863e0d05dd37dbL;
	
	private boolean success;// 处理是否成功
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public MessageDto(boolean success, String message) {
		this.success = false;
		this.success = success;
		this.message = message;
	}
}
