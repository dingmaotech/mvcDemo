// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Page.java

package com.dingmao.platform.model;

import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Page {

	public static final String ASC = "asc";
	public static final String DESC = "desc";
	protected int pageNo;
	protected int pageSize;
	protected String orderBy;
	protected String order;
	protected boolean autoCount;
	protected List result;
	protected long totalRows;
	private Object entity;

	public Page() {
		pageNo = 1;
		pageSize = 1;
		orderBy = null;
		order = null;
		autoCount = true;
		result = Collections.emptyList();
		totalRows = -1L;
	}

	public Page(int pageSize) {
		pageNo = 1;
		this.pageSize = 1;
		orderBy = null;
		order = null;
		autoCount = true;
		result = Collections.emptyList();
		totalRows = -1L;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1)
			this.pageNo = 1;
	}

	public Page pageNo(int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if (pageSize < 1)
			this.pageSize = 1;
	}

	public Page pageSize(int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	public int getFirst() {
		return (pageNo - 1) * pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Page orderBy(String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		String orders[] = StringUtils.split(StringUtils.lowerCase(order), ',');
		String as[];
		int j = (as = orders).length;
		for (int i = 0; i < j; i++) {
			String orderStr = as[i];
			if (!StringUtils.equals("desc", orderStr)
					&& !StringUtils.equals("asc", orderStr))
				throw new IllegalArgumentException(
						(new StringBuilder("������")).append(orderStr)
								.append("���ǺϷ�ֵ").toString());
		}

		this.order = StringUtils.lowerCase(order);
	}

	public Page order(String theOrder) {
		setOrder(theOrder);
		return this;
	}

	public boolean isOrderBySetted() {
		return StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order);
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page autoCount(boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public long getTotalPages() {
		if (totalRows < 0L)
			return -1L;
		long count = totalRows / (long) pageSize;
		if (totalRows % (long) pageSize > 0L)
			count++;
		return count;
	}

	public boolean getIsHasNext() {
		return (long) (pageNo + 1) <= getTotalPages();
	}

	public int getNextPage() {
		if (getIsHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	public boolean getIsHasPre() {
		return pageNo - 1 >= 1;
	}

	public int getPrePage() {
		if (getIsHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}
}
