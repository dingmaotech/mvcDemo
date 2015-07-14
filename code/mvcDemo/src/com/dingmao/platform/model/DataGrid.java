package com.dingmao.platform.model;

import java.util.List;

import com.dingmao.platform.enums.SortEnum;

public class DataGrid {

	// 当前页 页号码,页码从1开始
	private int pageNumber = 1;
	// 每页显示记录数 分页大小;为0或者null时不分页
	private int pageSize = 10;
	// 排序字段名 排序的多个列,如: username desc
	private String sortColumns;
	// 按什么排序(asc,desc)
	private SortEnum order = SortEnum.asc;
	// 字段
	private String field;
	// 结果集
	private List<?> results;
	// 总记录数
	private int total;
	// 合计列
	private String footer;

	public DataGrid() {
		pageNumber = 1;
		pageSize = 10;
		order = SortEnum.asc;
	}

	public DataGrid(int pageNumber, int pageSize) {
		this(pageNumber, pageSize, null);
	}

	public DataGrid(int pageNumber, int pageSize, String sortColumns) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		setSortColumns(sortColumns);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getField() {
		return field;
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

	public void setField(String field) {
		this.field = field;
	}

	public SortEnum getOrder() {
		return order;
	}

	public void setOrder(SortEnum order) {
		this.order = order;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortColumns() {
		return sortColumns;
	}

	/**
	 * 排序的列,可以同时多列,使用逗号分隔,如 username desc,age asc
	 * 
	 * @return
	 */
	public void setSortColumns(String sortColumns) {
		checkSortColumnsSqlInjection(sortColumns);
		if (sortColumns != null && sortColumns.length() > 50) {
			throw new IllegalArgumentException(
					"sortColumns.length() <= 50 must be true");
		}
		this.sortColumns = sortColumns;
	}

	/*	*//**
	 * 将sortColumns进行解析以便返回SortInfo以便使用
	 * 
	 * @return
	 */
	/*
	 * public List<SortInfo> getSortInfos() { return
	 * Collections.unmodifiableList(SortInfo.parseSortColumns(sortColumns)); }
	 */

	private void checkSortColumnsSqlInjection(String sortColumns) {
		if (sortColumns == null)
			return;
		if (sortColumns.indexOf("'") >= 0 || sortColumns.indexOf("\\") >= 0) {
			throw new IllegalArgumentException("sortColumns:" + sortColumns
					+ " has SQL Injection risk");
		}
	}

}
