package com.dingmao.mvcdemo.student.service;

import java.util.List;

import com.dingmao.mvcdemo.student.model.Student;


 
public class StudentDataHandler  {
	List<Student> list;
	int itotalRecords;
	int iTotalDisplay;
	
	public StudentDataHandler(List<Student> list) {
		this.list = list;
		itotalRecords = iTotalDisplay = list.size();
	}

	public List<Student> getList() {
		return list;
	}

	public void setList(List<Student> list) {
		this.list = list;
	}

	public int getItotalRecords() {
		return itotalRecords;
	}

	public int getiTotalDisplay() {
		return iTotalDisplay;
	}


}

