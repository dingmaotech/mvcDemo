package com.dingmao.platform.vo.datatables.wrapper.model;

public class AoColumnVo {

	public Integer[] aDataSort;
	public String asSorting[];
	public Boolean bSearchable;
	public Boolean bSortable;
	/**
	 * @deprecated Field bUseRendered is deprecated
	 */
	public Boolean bUseRendered;
	public Boolean bVisible;
	public FnCallBack fnCreatedCell;
	/**
	 * @deprecated Field fnRender is deprecated
	 */
	public String fnRender;
	public Integer iDataSort;
	public String mData;
	public String mRender;
	public String sCellType;
	public String sClass;
	public String sContentPadding;
	public String sDefaultContent;
	public String sName;
	public String sSortDataType;
	public String sTitle;
	public String sType;
	public String sWidth;

	public AoColumnVo() {
	}

	public AoColumnVo(String sTitle, String mData) {
		this.sTitle = sTitle;
		this.mData = mData;
	}

	public Integer [] getaDataSort() {
		return aDataSort;
	}

	public void setaDataSort(Integer[] aDataSort) {
		this.aDataSort = aDataSort;
	}

	public String[] getAsSorting() {
		return asSorting;
	}

	public void setAsSorting(String[] asSorting) {
		this.asSorting = asSorting;
	}

	public Boolean getbSearchable() {
		return bSearchable;
	}

	public void setbSearchable(Boolean bSearchable) {
		this.bSearchable = bSearchable;
	}

	public Boolean getbSortable() {
		return bSortable;
	}

	public void setbSortable(Boolean bSortable) {
		this.bSortable = bSortable;
	}

	public Boolean getbUseRendered() {
		return bUseRendered;
	}

	public void setbUseRendered(Boolean bUseRendered) {
		this.bUseRendered = bUseRendered;
	}

	public Boolean getbVisible() {
		return bVisible;
	}

	public void setbVisible(Boolean bVisible) {
		this.bVisible = bVisible;
	}

	public FnCallBack getFnCreatedCell() {
		return fnCreatedCell;
	}

	public void setFnCreatedCell(FnCallBack fnCreatedCell) {
		this.fnCreatedCell = fnCreatedCell;
	}

	public String getFnRender() {
		return fnRender;
	}

	public void setFnRender(String fnRender) {
		this.fnRender = fnRender;
	}

	public Integer getiDataSort() {
		return iDataSort;
	}

	public void setiDataSort(Integer iDataSort) {
		this.iDataSort = iDataSort;
	}

	public String getmData() {
		return mData;
	}

	public void setmData(String mData) {
		this.mData = mData;
	}

	public String getmRender() {
		return mRender;
	}

	public void setmRender(String mRender) {
		this.mRender = mRender;
	}

	public String getsCellType() {
		return sCellType;
	}

	public void setsCellType(String sCellType) {
		this.sCellType = sCellType;
	}

	public String getsClass() {
		return sClass;
	}

	public void setsClass(String sClass) {
		this.sClass = sClass;
	}

	public String getsContentPadding() {
		return sContentPadding;
	}

	public void setsContentPadding(String sContentPadding) {
		this.sContentPadding = sContentPadding;
	}

	public String getsDefaultContent() {
		return sDefaultContent;
	}

	public void setsDefaultContent(String sDefaultContent) {
		this.sDefaultContent = sDefaultContent;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsSortDataType() {
		return sSortDataType;
	}

	public void setsSortDataType(String sSortDataType) {
		this.sSortDataType = sSortDataType;
	}

	public String getsTitle() {
		return sTitle;
	}

	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public String getsWidth() {
		return sWidth;
	}

	public void setsWidth(String sWidth) {
		this.sWidth = sWidth;
	}
	
	
}
