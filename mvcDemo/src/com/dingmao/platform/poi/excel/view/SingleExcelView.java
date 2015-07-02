package com.dingmao.platform.poi.excel.view;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.dingmao.platform.poi.excel.constants.POIConstant;
import com.dingmao.platform.poi.excel.util.ExcelExportUtil;
import com.dingmao.platform.poi.excel.vo.ExcelTitleVo;

/**
 * Excel 生成解析器,减少用户操作
 */
public class SingleExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook hssfWorkbook, HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception {
        String codedFileName = "临时文件.xls";
        if (model.containsKey(POIConstant.FILE_NAME)) {
            codedFileName = (String) model.get(POIConstant.FILE_NAME)+".xls";
        }
        httpServletResponse.setHeader(
                "content-disposition",
                "attachment;filename=" + new String(codedFileName.getBytes(), "iso8859-1"));
        if (model.containsKey(POIConstant.MAP_LIST)) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) model.get(POIConstant.MAP_LIST);
            for (Map<String, Object> map : list) {
                ExcelExportUtil.createSheetInUserModel2File(hssfWorkbook,
                        (ExcelTitleVo) map.get(POIConstant.EXCEL_TITLE),
                        (Class<?>) map.get(POIConstant.CLASS),
                        (Collection<?>) map.get(POIConstant.DATA_LIST));
            }
        } else {
            ExcelExportUtil.createSheetInUserModel2File(hssfWorkbook,
                    (ExcelTitleVo) model.get(POIConstant.EXCEL_TITLE), (Class<?>) model.get(POIConstant.CLASS),
                    (Collection<?>) model.get(POIConstant.DATA_LIST));
        }
    }
}
