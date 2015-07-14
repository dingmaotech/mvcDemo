package com.dingmao.platform.model.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Parameter;

/**
 * 实现分页参数及查询结果封装. 注意所有序号从1开始.
 * 
 * @param <T>
 *            Page中记录的类型.
 */
public class Page<T> {

	// -- 排序方式变量 --//
	// 升序
	public static final String ASC = "asc";
	// 降序
	public static final String DESC = "desc";
	// -- 分页参数 --//
	/**
	 * 页号 *
	 */
	protected int pageNo = 1;
	
	/**
	 * 开始记录号 *
	 */
	private  int  startNo = 1;

	/**
	 * 每页记录数 *
	 */
	protected int pageSize = 10;
	/**
	 * 排序字段 *
	 */
	protected String orderBy = null;
	/**
	 * 排序方式(升序asc，降序desc) *
	 */
	protected String order = null;
	/**
	 * 是否重新计算集合总数 *
	 */
	protected boolean autoCount = true;
	/**
	 * 返回的集合 *
	 */
	protected List<T> result = Collections.emptyList();
	/**
	 * 总数 *
	 */
	protected long totalRows = -1;

	// --查询条件--//
	@SuppressWarnings("unused")
	private T entity;

	private List<Parameter> parameterList = new ArrayList<Parameter>();

	// -- 构造函数 --//
	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public Page(int pageSize,int startNo) {
		this.startNo = startNo;
		this.pageSize = pageSize;
		this.pageNo  = (this.startNo  / this.pageSize) == 0 ? 1
				: (this.startNo /  this.pageSize) + 1;
	}

	// -- 访问查询参数函数 --//

	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}
	

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
		this.startNo =pageNo==1||pageNo < 1?0: (pageNo-1)*this.pageSize-1;
	}

	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * 获得每页的记录数量,默认为1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	/**
	 * 获得排序方向.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param order
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		// 检查order字符串的合法值
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr)
					&& !StringUtils.equals(ASC, orderStr))
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
		}

		this.order = StringUtils.lowerCase(order);
	}

	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils
				.isNotBlank(order));
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数, 默认为true.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	// -- 访问查询结果函数 --//

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * @return the totalRows
	 */
	public long getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows
	 *            the totalRows to set
	 */
	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (totalRows < 0)
			return -1;

		long count = totalRows / pageSize;
		if (totalRows % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean getIsHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (getIsHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean getIsHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (getIsHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}

	/**
	 * 查询条件集合.
	 * 
	 * @return parameterList 查询条件集合
	 */
	public List<Parameter> getParameterList() {
		return parameterList;
	}

	/**
	 * 查询条件集合.
	 * 
	 * @param parameterList
	 *            查询条件集合
	 * @see
	 */
	public void setParameterList(List<Parameter> parameterList) {
		this.parameterList = parameterList;
	}

}
