package com.dingmao.mvcdemo.user.model;

import java.util.ArrayList;
import java.util.List;

import com.dingmao.platform.vo.datatables.wrapper.IDataHandler;
import com.dingmao.platform.vo.datatables.wrapper.data.DataTableRequest;


public class UserDataHandler implements IDataHandler<User> {
	private List<User> list;
	private int itotalRecords;
	private int iTotalDisplay;
	
	public UserDataHandler() {
	}
	@Override
	public List<User> getListData(DataTableRequest tableReq,List<User> users) {
		iTotalDisplay  = itotalRecords = users.size();
		list = filterStudents(tableReq,users);
		return list;
	}

	@Override
	public int getITotalRecords() {
		return itotalRecords;
	}

	@Override
	public int getITotalDisplayRecords() {
		return iTotalDisplay;
	}
	
	public  List<User> filterStudents(DataTableRequest tableReq,List<User> list) {
		List<User> tmpStudents = new ArrayList<User>();
		if (tableReq != null) {
			
			for (int i = tableReq.iDisplayStart; i < list.size()
					&& i < tableReq.iDisplayStart + tableReq.iDisplayLength; i++) {
				tmpStudents.add(list.get(i));
			}
			return tmpStudents;
		} else {
			return list;
		}
	}
	
}

