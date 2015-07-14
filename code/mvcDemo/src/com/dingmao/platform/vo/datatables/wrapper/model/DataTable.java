package com.dingmao.platform.vo.datatables.wrapper.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.dingmao.platform.vo.datatables.wrapper.annotation.AoColumn;


public class DataTable{
	
	private  Class  clazz;

	public Boolean bDestroy;
	public Boolean bRetrieve;
	/**
	 * @deprecated Field bScrollAutoCss is deprecated
	 */
	public Boolean bScrollAutoCss;
	public Boolean bScrollCollapse;
	public Boolean bSortCellsTop;
	public Integer iCookieDuration;
	public Integer iDeferLoading;
	public Integer iDisplayLength;
	public Integer iDisplayStart;
	public Integer iScrollLoadGap;
	public Integer iTabIndex;
	public String sAjaxDataProp;
	public String sAjaxSource;
	public String sCookiePrefix;
	public String sDom;
	public String sPaginationType;
	public String sScrollXInner;
	public String sServerMethod;
	public Boolean bAutoWidth;
	public Boolean bDeferRender;
	public Boolean bFilter;
	public Boolean bInfo;
	public Boolean bJQueryUI;
	public Boolean bLengthChange;
	public Boolean bPaginate;
	public Boolean bProcessing;
	/**
	 * @deprecated Field bScrollInfinite is deprecated
	 */
	public Boolean bScrollInfinite;
	public Boolean bServerSide;
	public Boolean bSort;
	public Boolean bSortClasses;
	public Boolean bStateSave;
	public String sScrollX;
	public String sScrollY;
	public FnCallBack fnCookieCallback;
	public FnCallBack fnCreatedRow;
	public FnCallBack fnDrawCallback;
	public FnCallBack fnFooterCallback;
	public FnCallBack fnFormatNumber;
	public FnCallBack fnHeaderCallback;
	public FnCallBack fnInfoCallback;
	public FnCallBack fnInitComplete;
	public FnCallBack fnPreDrawCallback;
	public FnCallBack fnRowCallback;
	public FnCallBack fnServerData;
	public FnCallBack fnServerParams;
	public FnCallBack fnStateLoad;
	public FnCallBack fnStateLoadParams;
	public FnCallBack fnStateLoaded;
	public FnCallBack fnStateSave;
	public FnCallBack fnStateSaveParams;
	public OSearch oSearch;
	public OLanguage oLanguage;
	public List aoColumnDefs;
	private List aoColumns;
	public List aaData;
	public String aaSorting[][];

	public DataTable() {
	}
	
	public DataTable(Class  clazz) {
		this.clazz = clazz;
	}

	public List getAoColumns() {
		return aoColumns != null ? aoColumns :parseAoColumns();
	}

	public void setAoColumns(List aoColumns) {
		this.aoColumns = aoColumns;
	}
	
	
	protected  List<AoColumnVo> parseAoColumns() {
		List<AoColumnVo> columns = new ArrayList<AoColumnVo>();
		Field[]  fields = getClassFields(clazz);
		if (fields != null && fields.length > 0) {
			getAllAoColumnField(fields,columns, clazz);
		}
		return columns;
	}

	private Field[] getClassFields(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields;
		do {
			fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				list.add(fields[i]);
			}
			clazz = clazz.getSuperclass();
		} while (clazz != Object.class && clazz != null);
		return list.toArray(fields);
	}
	
	private  void getAllAoColumnField( Field[] fields,
			List<AoColumnVo> columns, Class<?> clazz) {
		// 遍历整个filed
		AoColumnVo aoColumn;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			// 先判断是不是collection,在判断是不是java自带对象,之后就是我们自己的对象了
			if (isNotUserAoColumnUserThis(field)) {
				continue;
			}
			 if (isJavaClass(field)) {
				 AoColumn anoColumn = field.getAnnotation(AoColumn.class);
					if (anoColumn != null){
						aoColumn = new AoColumnVo();
						aoColumn.aDataSort = toIntegerArr(anoColumn.aDataSort());
						aoColumn.asSorting = toStringArr(anoColumn.asSorting());
						aoColumn.bSearchable = toBoolean(anoColumn.bSearchable());
						aoColumn.bSortable = toBoolean(anoColumn.bSortable());
						aoColumn.bVisible = toBoolean(anoColumn.bVisible());
						aoColumn.bUseRendered = toBoolean(anoColumn.bUseRendered());
						String fnCreatedCell = toString(anoColumn.fnCreatedCell());
						if (fnCreatedCell != null)
							aoColumn.fnCreatedCell = new FnCallBack(fnCreatedCell);
						aoColumn.fnRender = toString(anoColumn.fnRender());
						aoColumn.iDataSort = toInteger(anoColumn.iDataSort());
						aoColumn.mData = getMData(anoColumn.mData(), field.getName());
						aoColumn.mRender = toString(anoColumn.mRender());
						aoColumn.sCellType = toString(anoColumn.sCellType());
						aoColumn.sClass = toString(anoColumn.sClass());
						aoColumn.sContentPadding = toString(anoColumn.sContentPadding());
						aoColumn.sDefaultContent = toString(anoColumn.sDefaultContent());
						aoColumn.sName = toString(anoColumn.sName());
						aoColumn.sSortDataType = toString(anoColumn.sSortDataType());
						aoColumn.sTitle = toString(anoColumn.sTitle());
						aoColumn.sType = toString(anoColumn.sType());
						aoColumn.sWidth = toString(anoColumn.sWidth());
						columns.add(aoColumn);
					}
			}
		}
	}
	
	/**
	 * 是不是java基础类
	 * 
	 * @param field
	 * @return
	 */
	private   boolean isJavaClass(Field field) {
		Class<?> fieldType = field.getType();
		boolean isBaseClass = false;
		if (fieldType.isArray()) {
			isBaseClass = false;
		} else if (fieldType.isPrimitive() || fieldType.getPackage() == null
				|| fieldType.getPackage().getName().equals("java.lang")
				|| fieldType.getPackage().getName().equals("java.math")
				|| fieldType.getPackage().getName().equals("java.util")) {
			isBaseClass = true;
		}
		return isBaseClass;
	}

	private  boolean isNotUserAoColumnUserThis(Field field) {
		boolean boo = true;
		if (field.getAnnotation(AoColumn.class) != null) {
			boo = true;
		} else {
			boo = false;
		}
		return boo;
	}
	


	private Boolean toBoolean(boolean input) {
		if (input)
			return null;
		else
			return Boolean.valueOf(input);
	}

	private Integer[] toIntegerArr(int in[]) {
		if (in != null && in.length > 0) {
			Integer newArray[] = new Integer[in.length];
			int i = 0;
			int ai[];
			int k = (ai = in).length;
			for (int j = 0; j < k; j++) {
				int value = ai[j];
				newArray[i++] = Integer.valueOf(value);
			}

			return newArray;
		} else {
			return null;
		}
	}

	private Integer toInteger(int in) {
		if (in == 0)
			return null;
		else
			return Integer.valueOf(in);
	}

	private String toString(String in) {
		if (in != null && in.equals(""))
			return null;
		else
			return in;
	}

	private String getMData(String annotated, String fieldName) {
		if (annotated != null && annotated.equals(""))
			return fieldName;
		else
			return annotated;
	}

	private String[] toStringArr(String annotatedArr[]) {
		if (annotatedArr != null && annotatedArr.length > 0)
			return annotatedArr;
		else
			return null;
	}
}
