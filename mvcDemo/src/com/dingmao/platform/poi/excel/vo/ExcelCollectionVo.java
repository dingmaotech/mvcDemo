package com.dingmao.platform.poi.excel.vo;

import java.util.Map;

public class ExcelCollectionVo {

	/**
	 * 集合对应的名称
	 */
	private String name;
	/**
	 * 实体对象
	 */
	private Class<?> type;
	/**
	 * 这个list下面的参数集合实体对象
	 */
	private Map<String, ExcelImportVo> excelParams;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Map<String, ExcelImportVo> getExcelParams() {
		return excelParams;
	}

	public void setExcelParams(Map<String, ExcelImportVo> excelParams) {
		this.excelParams = excelParams;
	}
}
