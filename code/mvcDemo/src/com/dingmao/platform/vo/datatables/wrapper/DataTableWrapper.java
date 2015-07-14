package com.dingmao.platform.vo.datatables.wrapper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dingmao.platform.service.BaseService;
import com.dingmao.platform.vo.datatables.wrapper.data.DataTableRequest;
import com.dingmao.platform.vo.datatables.wrapper.data.DataTableResponse;
import com.dingmao.platform.vo.datatables.wrapper.model.DataTable;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DataTableWrapper<T, PK extends Serializable> {

	public static final String HANDLER_PARAM = "hdl";
	private DataTable dataTable;
	private DataTableRequest ajaxTableReq;
	private DataTableResponse ajaxTableRes;
	private Class handler;
	private String urlLocation;
	public ObjectMapper objMapper;

	public DataTableWrapper() {
		objMapper = new ObjectMapper();
	}

	public void setUrlLocation(String urlLocation) {
		this.urlLocation = urlLocation;
	}

	public DataTableRequest getAjaxTableReq() {
		return ajaxTableReq;
	}

	public void setAjaxTableRes(DataTableResponse ajaxTableRes) {
		this.ajaxTableRes = ajaxTableRes;
	}

	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}

	public void setDataHandler(Class handler) {
		this.handler = handler;
	}

	public void wrap(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		if (req == null || res == null)
			throw new IllegalArgumentException(
					"Parmeters request and response must not be null.");
		String handlerClazzName;
		if (handler != null)
			handlerClazzName = handler.getName();
		else
			handlerClazzName = req.getParameter("hdl");
		if (dataTable != null && req.getParameter("hdl") == null) {
			if (handler != null && dataTable.bServerSide != null
					&& dataTable.bServerSide.booleanValue()) {
				String url = urlLocation == null ? req.getRequestURL()
						.toString() : urlLocation;
				dataTable.sAjaxSource = (new StringBuilder(String.valueOf(url)))
						.append(url.contains("?") ? "&" : "?").append("hdl")
						.append("=").append(handlerClazzName).toString();
			} else if (handler != null
					&& (dataTable.bServerSide == null || !dataTable.bServerSide
							.booleanValue())) {
				IDataHandler dataHandler = (IDataHandler) handler.newInstance();
				dataTable.aaData = dataHandler.getListData(ajaxTableReq, req);
			}
			writeJson(res.getOutputStream(), dataTable);
		} else {
			ajaxTableReq = parseAjaxRequest(req);
			if (ajaxTableReq != null) {
				Class clazz = Class.forName(handlerClazzName);
				if (clazz != null) {
					IDataHandler tableHandler = (IDataHandler) clazz
							.newInstance();
					ajaxTableRes = new DataTableResponse();
					ajaxTableRes.aaData = tableHandler.getListData(
							ajaxTableReq, req);
					ajaxTableRes.iTotalRecords = tableHandler
							.getITotalRecords();
					ajaxTableRes.iTotalDisplayRecords = tableHandler
							.getITotalDisplayRecords();
					ajaxTableRes.sEcho = ajaxTableReq.sEcho;
					writeJson(res.getOutputStream(), ajaxTableRes);
				}
			}
		}
	}

	public void writeJson(OutputStream out, Object obj) {
		ObjectMapper objMapper = getObjectMapper();
		try {
			objMapper.writeValue(out, obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String toString() {
		String dataTableStr = "";
		if (dataTable == null
				&& (dataTable.bServerSide == null || !dataTable.bServerSide
						.booleanValue()) && handler != null) {
			IDataHandler dataHandler;
			try {
				dataHandler = (IDataHandler) handler.newInstance();
				dataTable.aaData = dataHandler.getListData(ajaxTableReq, null);
				dataTableStr = getObjectMapper().writeValueAsString(dataTable);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return dataTableStr;
	}

	private ObjectMapper getObjectMapper() {
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
		objMapper.setDateFormat(dateFormat);
		objMapper.setSerializationInclusion(Include.NON_NULL);
		objMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return objMapper;
	}

	public DataTableRequest parseAjaxRequest(HttpServletRequest request) {
		DataTableRequest ajaxRequest = new DataTableRequest();
		if (request.getParameter("sEcho") != null
				&& request.getParameter("sEcho") != "") {
			ajaxRequest.sEcho = request.getParameter("sEcho");
			ajaxRequest.sSearchKeyword = request.getParameter("sSearch");
			ajaxRequest.bRegexKeyword = Boolean.parseBoolean(request
					.getParameter("bRegex"));
			ajaxRequest.sColumns = request.getParameter("sColumns");
			ajaxRequest.iDisplayStart = Integer.parseInt(request
					.getParameter("iDisplayStart"));
			ajaxRequest.iDisplayLength = Integer.parseInt(request
					.getParameter("iDisplayLength"));
			ajaxRequest.iColumns = Integer.parseInt(request
					.getParameter("iColumns"));
			ajaxRequest.sSearch = new String[ajaxRequest.iColumns];
			ajaxRequest.bSearchable = new boolean[ajaxRequest.iColumns];
			ajaxRequest.bSortable = new boolean[ajaxRequest.iColumns];
			ajaxRequest.bRegex = new boolean[ajaxRequest.iColumns];
			for (int i = 0; i < ajaxRequest.iColumns; i++) {
				ajaxRequest.sSearch[i] = request
						.getParameter((new StringBuilder("sSearch_")).append(i)
								.toString());
				ajaxRequest.bSearchable[i] = Boolean.parseBoolean(request
						.getParameter((new StringBuilder("bSearchable_"))
								.append(i).toString()));
				ajaxRequest.bSortable[i] = Boolean.parseBoolean(request
						.getParameter((new StringBuilder("bSortable_")).append(
								i).toString()));
				ajaxRequest.bRegex[i] = Boolean.parseBoolean(request
						.getParameter((new StringBuilder("bRegex_")).append(i)
								.toString()));
			}

			if (request.getParameter("iSortingCols") != null)
				ajaxRequest.iSortingCols = Integer.parseInt(request
						.getParameter("iSortingCols"));
			ajaxRequest.sSortDir = new String[ajaxRequest.iSortingCols];
			ajaxRequest.iSortCol = new int[ajaxRequest.iSortingCols];
			for (int i = 0; i < ajaxRequest.iSortingCols; i++) {
				ajaxRequest.sSortDir[i] = request
						.getParameter((new StringBuilder("sSortDir_"))
								.append(i).toString());
				ajaxRequest.iSortCol[i] = Integer.parseInt(request
						.getParameter((new StringBuilder("iSortCol_"))
								.append(i).toString()));
			}

		}
		return ajaxRequest;
	}
}
