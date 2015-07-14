package com.dingmao.platform.vo.datatable;

import com.dingmao.platform.enums.SortEnum;

/**
 * 排序参数
 */
public class SortInfo {

	/**
	 * 排序列名
	 */
	private Integer columnId;
	/**
	 * 排序列名 方式 desc or asc
	 */
	private SortEnum sortType = SortEnum.desc;

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public SortEnum getSortType() {
		return sortType;
	}

	public void setSortType(SortEnum sortType) {
		this.sortType = sortType;
	}

}
