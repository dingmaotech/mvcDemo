package com.dingmao.platform.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 分页信息 第一页从1开始
 */
public class Page<T> implements Iterable<T> {

	protected int pageSize;

	protected int pageNumber;

	protected int totalCount = 0;

	private String sortColumns;

	protected List<T> result;

	public Page(DataGrid p, int totalCount) {
		this(p.getPageNumber(), p.getPageSize(), totalCount, p.getSortColumns());
	}

	public Page(int pageNumber, int pageSize, int totalCount, String sortColumns) {
		this(pageNumber, pageSize, totalCount, new ArrayList<T>(0), sortColumns);
	}

	public Page(int pageNumber, int pageSize, int totalCount, List<T> result,
			String sortColumns) {
		if (pageSize <= 0)
			throw new IllegalArgumentException(
					"[pageSize] must great than zero");
		this.pageSize = pageSize;
		this.pageNumber = computePageNumber(pageNumber, pageSize, totalCount);
		this.totalCount = totalCount;
		this.sortColumns = sortColumns;
		setResult(result);
	}

	public int computePageNumber(int pageNumber, int pageSize, int totalElements) {
		if (pageNumber <= 1) {
			return 1;
		}
		if (Integer.MAX_VALUE == pageNumber
				|| pageNumber > computeLastPageNumber(totalElements, pageSize)) { // last
																					// page
			return computeLastPageNumber(totalElements, pageSize);
		}
		return pageNumber;
	}

	public int computeLastPageNumber(int totalElements, int pageSize) {
		int result = totalElements % pageSize == 0 ? totalElements / pageSize
				: totalElements / pageSize + 1;
		if (result <= 1)
			result = 1;
		return result;
	}

	public void setResult(List<T> elements) {
		if (elements == null)
			throw new IllegalArgumentException("'result' must be not null");
		this.result = elements;
	}

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	/**
	 * 当前页包含的数据
	 * 
	 * @return 当前页数据源
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 是否是首页（第一页），第一页页码为1
	 * 
	 * @return 首页标识
	 */
	public boolean isFirstPage() {
		return getThisPageNumber() == 1;
	}

	/**
	 * 是否是最后一页
	 * 
	 * @return 末页标识
	 */
	public boolean isLastPage() {
		return getThisPageNumber() >= getLastPageNumber();
	}

	/**
	 * 是否有下一页
	 * 
	 * @return 下一页标识
	 */
	public boolean isHasNextPage() {
		return getLastPageNumber() > getThisPageNumber();
	}

	/**
	 * 是否有上一页
	 * 
	 * @return 上一页标识
	 */
	public boolean isHasPreviousPage() {
		return getThisPageNumber() > 1;
	}

	/**
	 * 获取最后一页页码，也就是总页数
	 * 
	 * @return 最后一页页码
	 */
	public int getLastPageNumber() {
		return this.computeLastPageNumber(totalCount, pageSize);
	}

	/**
	 * 总的数据条目数量，0表示没有数据
	 * 
	 * @return 总数量
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 获取当前页的首条数据的行编码
	 * 
	 * @return 当前页的首条数据的行编码
	 */
	public int getThisPageFirstElementNumber() {
		return (getThisPageNumber() - 1) * getPageSize() + 1;
	}

	/**
	 * 获取当前页的末条数据的行编码
	 * 
	 * @return 当前页的末条数据的行编码
	 */
	public int getThisPageLastElementNumber() {
		int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
		return getTotalCount() < fullPage ? getTotalCount() : fullPage;
	}

	/**
	 * 获取下一页编码
	 * 
	 * @return 下一页编码
	 */
	public int getNextPageNumber() {
		return getThisPageNumber() + 1;
	}

	/**
	 * 获取上一页编码
	 * 
	 * @return 上一页编码
	 */
	public int getPreviousPageNumber() {
		return getThisPageNumber() - 1;
	}

	/**
	 * 每一页显示的条目数
	 * 
	 * @return 每一页显示的条目数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 当前页的页码
	 * 
	 * @return 当前页的页码
	 */
	public int getThisPageNumber() {
		return pageNumber;
	}

	/**
	 * 得到用于多页跳转的页码
	 * 
	 * @return
	 */
	public List<Integer> getLinkPageNumbers() {
		return this.generateLinkPageNumbers(getThisPageNumber(),
				getLastPageNumber(), 10);
	}

	public static List<Integer> generateLinkPageNumbers(int currentPageNumber,
			int lastPageNumber, int count) {
		int avg = count / 2;

		int startPageNumber = currentPageNumber - avg;
		if (startPageNumber <= 0) {
			startPageNumber = 1;
		}

		int endPageNumber = startPageNumber + count - 1;
		if (endPageNumber > lastPageNumber) {
			endPageNumber = lastPageNumber;
		}

		if (endPageNumber - startPageNumber < count) {
			startPageNumber = endPageNumber - count;
			if (startPageNumber <= 0) {
				startPageNumber = 1;
			}
		}

		List<Integer> result = new java.util.ArrayList<Integer>();
		for (int i = startPageNumber; i <= endPageNumber; i++) {
			result.add(new Integer(i));
		}
		return result;
	}

	/**
	 * 得到数据库的第一条记录号
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return getFirstResult(pageNumber, pageSize);
	}

	/**
	 * 得到数据库的第一条记录号
	 * 
	 * @return
	 */
	public int getFirstResult(int pageNumber, int pageSize) {
		if (pageSize <= 0)
			throw new IllegalArgumentException(
					"[pageSize] must great than zero");
		return (pageNumber - 1) * pageSize;
	}

	@SuppressWarnings("unchecked")
	public Iterator<T> iterator() {
		return result == null ? Collections.EMPTY_LIST.iterator() : result
				.iterator();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageSize=");
		builder.append(pageSize);
		builder.append(", pageNumber=");
		builder.append(pageNumber);
		builder.append(", totalCount=");
		builder.append(totalCount);
		builder.append(", result=");
		// builder.append(ArrayUtils.toString(result));
		builder.append(result);
		builder.append("]");
		return builder.toString();
	}

}
