package com.dingmao.platform.vo.datatables.wrapper;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.dingmao.platform.vo.datatables.wrapper.data.DataTableRequest;
import com.dingmao.platform.vo.datatables.wrapper.data.DataTableResponse;
import com.dingmao.platform.vo.datatables.wrapper.model.DataTable;

public class DataTableWrapper {

	private DataTable dataTable;
	private DataTableRequest ajaxTableReq;
	private DataTableResponse ajaxTableRes;

	public DataTableWrapper() {
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

	public JSONObject wrap(HttpServletRequest request, IDataHandler<?> handler,
			List list, String urlLocation) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		JSONObject json = null;
		if (dataTable != null) {
			if (handler != null && dataTable.bServerSide != null
					&& dataTable.bServerSide.booleanValue()) {
				String url = urlLocation == null ? urlLocation.toString()
						: urlLocation;
				dataTable.sAjaxSource = url;
			} else if (handler != null
					&& (dataTable.bServerSide == null || !dataTable.bServerSide
							.booleanValue())) {
				dataTable.aaData = handler.getListData(ajaxTableReq, list);
			}
			json = JSONObject.fromObject(dataTable);
		} else {
			ajaxTableReq = parseAjaxRequest(request);
			if (ajaxTableReq != null) {
				ajaxTableRes = new DataTableResponse();
				ajaxTableRes.aaData = handler.getListData(ajaxTableReq, list);
				ajaxTableRes.iTotalRecords = handler.getITotalRecords();
				ajaxTableRes.iTotalDisplayRecords = handler
						.getITotalDisplayRecords();
				ajaxTableRes.sEcho = ajaxTableReq.sEcho;
				json = JSONObject.fromObject(ajaxTableRes);
			}
		}
		return json;
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
