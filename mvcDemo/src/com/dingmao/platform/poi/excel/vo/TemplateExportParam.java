package com.dingmao.platform.poi.excel.vo;

/**
 * 模板导出参数设置
 * 
 * @author  
 * @date 2015-06-17
 * @version 1.0
 */
public class TemplateExportParam {

	public TemplateExportParam() {

	}

	public TemplateExportParam(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	public TemplateExportParam(String templateUrl,
			int sheetNum) {
		this.templateUrl = templateUrl;
		this.sheetNum = sheetNum;
	}
	
	public TemplateExportParam(String templateUrl,
			String sheetName) {
		this.templateUrl = templateUrl;
		this.sheetName = sheetName;
	}
	
	public TemplateExportParam(String templateUrl, String sheetName,
			int sheetNum) {
		this.templateUrl = templateUrl;
		this.sheetName = sheetName;
		this.sheetNum = sheetNum;
	}

	/**
	 * 模板的路径
	 */
	private String templateUrl;

	/**
	 * 需要导出的第几个 sheetNum,默认是第0个
	 */
	private int sheetNum = 0;
	/**
	 * 这只sheetName 不填就使用原来的
	 */
	private String sheetName;

	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	public int getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

}
