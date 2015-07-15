package com.dingmao.platform.vo.datatables.wrapper;

import java.util.List;

import com.dingmao.platform.vo.datatables.wrapper.data.DataTableRequest;

public interface IDataHandler<T> {

	public  List<T> getListData(DataTableRequest ajaxrequest,List<T> list);
	
	public abstract int getITotalRecords();

	public abstract int getITotalDisplayRecords();
}
