// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ImportParam.java

package com.dingmao.platform.poi.excel.vo;

import com.dingmao.platform.util.PropertiesUtil;

public class ImportParam {

	private int titleRows;
	private int secondTitleRows;
	private int startRows;
	private int keyIndex;
	private int sheetNum;
	private boolean needSave;
	private String saveUrl;

	public ImportParam() {
		titleRows = 0;
		secondTitleRows = 1;
		startRows = 0;
		keyIndex = 0;
		sheetNum = 1;
		needSave = false;
		saveUrl = (new StringBuilder(String.valueOf(PropertiesUtil
				.getInstance().getPropertiesValue("file.upload.path"))))
				.append("/excelUpload").toString();
	}

	public int getTitleRows() {
		return titleRows;
	}

	public void setTitleRows(int titleRows) {
		this.titleRows = titleRows;
	}

	public int getSecondTitleRows() {
		return secondTitleRows;
	}

	public void setSecondTitleRows(int secondTitleRows) {
		this.secondTitleRows = secondTitleRows;
	}

	public int getStartRows() {
		return startRows;
	}

	public void setStartRows(int startRows) {
		this.startRows = startRows;
	}

	public int getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	public int getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(int keyIndex) {
		this.keyIndex = keyIndex;
	}

	public boolean isNeedSave() {
		return needSave;
	}

	public void setNeedSave(boolean needSave) {
		this.needSave = needSave;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}
}
