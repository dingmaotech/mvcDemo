package com.dingmao.platform.vo.datatables.wrapper.data;

public class DataTableRequest {

	public String sEcho;
	public String sSearchKeyword;
	public boolean bRegexKeyword;
	public int iDisplayLength;
	public int iDisplayStart;
	public int iColumns;
	public String sSearch[];
	public boolean bSearchable[];
	public boolean bSortable[];
	public boolean bRegex[];
	public int iSortingCols;
	public String sSortDir[];
	public int iSortCol[];
	public String sColumns;

	public DataTableRequest() {
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsSearchKeyword() {
		return sSearchKeyword;
	}

	public void setsSearchKeyword(String sSearchKeyword) {
		this.sSearchKeyword = sSearchKeyword;
	}

	public boolean isbRegexKeyword() {
		return bRegexKeyword;
	}

	public void setbRegexKeyword(boolean bRegexKeyword) {
		this.bRegexKeyword = bRegexKeyword;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public String[] getsSearch() {
		return sSearch;
	}

	public void setsSearch(String[] sSearch) {
		this.sSearch = sSearch;
	}

	public boolean[] getbSearchable() {
		return bSearchable;
	}

	public void setbSearchable(boolean[] bSearchable) {
		this.bSearchable = bSearchable;
	}

	public boolean[] getbSortable() {
		return bSortable;
	}

	public void setbSortable(boolean[] bSortable) {
		this.bSortable = bSortable;
	}

	public boolean[] getbRegex() {
		return bRegex;
	}

	public void setbRegex(boolean[] bRegex) {
		this.bRegex = bRegex;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public String[] getsSortDir() {
		return sSortDir;
	}

	public void setsSortDir(String[] sSortDir) {
		this.sSortDir = sSortDir;
	}

	public int[] getiSortCol() {
		return iSortCol;
	}

	public void setiSortCol(int[] iSortCol) {
		this.iSortCol = iSortCol;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}
	
	
}
