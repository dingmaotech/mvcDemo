package com.dingmao.platform.poi.excel.util;

import java.util.Comparator;

import com.dingmao.platform.poi.excel.vo.ExcelExportVo;

/**
 * 按照升序排序
 *
 */
public class SortExcelFieldUtil  implements Comparator<ExcelExportVo> {
	
	public int compare(ExcelExportVo prev,ExcelExportVo next) {
		return prev.getOrderNum() - next.getOrderNum();
	}

}
