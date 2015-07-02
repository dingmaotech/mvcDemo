package com.dingmao.platform.poi.excel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dingmao.platform.poi.excel.annotation.Excel;
import com.dingmao.platform.poi.excel.annotation.ExcelTarget;
import com.dingmao.platform.poi.excel.vo.ExcelCollectionVo;
import com.dingmao.platform.poi.excel.vo.ExcelImportVo;
import com.dingmao.platform.poi.excel.vo.ImportParam;
import com.dingmao.platform.util.DateUtils;
import com.dingmao.platform.util.PropertiesUtil;

/**
 * Excel 导入工具
 * 
 */
public final class ExcelImportUtil {

	/**
	 * Excel 导入 字段类型 Integer,Long,Double,Date,String,Boolean,BigDecimal
	 * @param file
	 * @param clazz
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static Collection<?> importExcel(File file, Class<?> clazz,
			ImportParam params) {
		FileInputStream in = null;
		Collection<?> result = null;
		try {
			in = new FileInputStream(file);
			result = importExcelByIs(in, clazz, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * Excel 导入 field 字段类型 Integer,Long,Double,Date,String,Boolean,BigDecimal
	 * @param inputstream
	 * @param clazz
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static  Collection<?> importExcelByIs(InputStream inputstream,
			Class<?> clazz, ImportParam params) throws Exception {
		Collection<T> result = new ArrayList<T>();
		Workbook book = null;
		boolean isXSSFWorkbook = true;
		if (!(inputstream.markSupported())) {
			inputstream = new PushbackInputStream(inputstream, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(inputstream)) {
			book =  new HSSFWorkbook(inputstream);
			isXSSFWorkbook = false;
		}else if (POIXMLDocument.hasOOXMLHeader(inputstream)) {
			book =  new XSSFWorkbook(OPCPackage.open(inputstream));
		}
		Map<String,PictureData> pictures;
		for (int i = 0; i < params.getSheetNum(); i++) {
			if(isXSSFWorkbook){
				pictures = ExcelPublicUtil.getSheetPictrues07(
						(XSSFSheet)book.getSheetAt(i), (XSSFWorkbook)book);
			}else{
				pictures = ExcelPublicUtil.getSheetPictrues03(
						(HSSFSheet)book.getSheetAt(i), (HSSFWorkbook)book);
			}
			result.addAll(importExcel(result, book.getSheetAt(i),
					clazz, params,pictures));
		}
		if(params.isNeedSave()){
			saveThisExcel(params,clazz,isXSSFWorkbook,book);
		}
		return result;
	}
	
	private static void saveThisExcel(ImportParam params,Class<?> clazz,
			boolean isXSSFWorkbook, Workbook book) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//String path = request.getSession().getServletContext().getRealPath("\\")+
	    String path = getSaveExcelUrl(params,clazz);
/*		path = path.replace("WEB-INF/classes/","");
		path = path.replace("file:/","");*/
		File savefile = new File(path);
		if(!savefile.exists()){
			savefile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(path+"/"+DateUtils.getCurrentDateTimeStr()+"_"
				+Math.round(Math.random()*100000)+(isXSSFWorkbook == true?".xlsx":".xls"));
		book.write(fos);
		fos.close();
	}
	
	
	/**
	 * 获取保存的Excel 的真实路径
	 * @param excelImportVo
	 * @param object
	 * @return
	 * @throws Exception 
	 */
	private static String getSaveExcelUrl(ImportParam params,
			Class<?> clazz) throws Exception {
		String url = "";
		if(params.getSaveUrl().equals(PropertiesUtil.getInstance().getPropertiesValue("file.upload.path")+"/excelUpload")){
			url =  clazz.getName().split("\\.")[clazz.getName().split("\\.").length-1];
			return params.getSaveUrl()+"/"+url;
		}
		return params.getSaveUrl();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Collection<? extends T> importExcel(Collection<T> result, Sheet sheet,
			Class<?> clazz, ImportParam params, Map<String, PictureData> pictures)  throws Exception {
		Collection collection = new ArrayList();
		Map<String, ExcelImportVo> excelParams = new HashMap<String, ExcelImportVo>();
		List<ExcelCollectionVo> excelCollection = new ArrayList<ExcelCollectionVo>();
		Field fileds[] = ExcelPublicUtil.getClassFields(clazz);
		ExcelTarget etarget = clazz.getAnnotation(ExcelTarget.class);
		String targetId = null;
		if (etarget != null) {
			targetId = etarget.id();
		}
		getAllExcelField(targetId, fileds, excelParams, excelCollection,
				clazz, null);
		Iterator<Row> rows = sheet.rowIterator();
		for (int j = 0; j < params.getTitleRows(); j++) {
			rows.next();
		}
		Row row = null;
		Iterator<Cell> cellTitle;
		Map<Integer, String> titlemap = new HashMap<Integer, String>();
		for (int j = 0; j < params.getSecondTitleRows(); j++) {
			row = rows.next();
			cellTitle = row.cellIterator();
			int i = row.getFirstCellNum();
			while (cellTitle.hasNext()) {
				Cell cell = cellTitle.next();
				String value = cell.getStringCellValue();
				if(!StringUtils.isEmpty(value)){
					titlemap.put(i, value);
				}
				i = i + 1;
			}
		}
		Object object = null;
		String picId;
		while (rows.hasNext()) {
			row = rows.next();
			// 判断是集合元素还是不是集合元素,如果是就继续加入这个集合,不是就创建新的对象
			if ((row.getCell(params.getKeyIndex())==null||
					StringUtils.isEmpty(getKeyValue(row.getCell(params.getKeyIndex()))))
					&& object != null) {
				for (ExcelCollectionVo param : excelCollection) {
					addListContinue(object, param, row,titlemap, targetId,pictures,params);
				}
			} else {
				object = ExcelPublicUtil.createObject(clazz, targetId);
				for(int i = row.getFirstCellNum() ;i<row.getLastCellNum();i++){
					Cell cell = row.getCell(i);
					String titleString = (String) titlemap.get(i);
					if (excelParams.containsKey(titleString)) {
						if(excelParams.get(titleString).getType()==2){
							picId = row.getRowNum()+"_"+i;
							saveImage(object,picId,excelParams,
									titleString,pictures,params);
						}else{
							judgeTypeAndSetValue(object, cell, excelParams, titleString);
						}
					}
				}
				for (ExcelCollectionVo param : excelCollection) {
					addListContinue(object, param, row,titlemap, targetId,pictures,
							params);
				}
				collection.add(object);
			}
		}
		return collection;
	}
	/**
	 * 获取key的值,针对不同类型获取不同的值
	 *@Author JueYue
	 *@date   2013-11-21
	 *@param cell
	 *@return
	 */
	private static String getKeyValue(Cell cell) {
		Object obj = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:obj = cell.getStringCellValue();break;
			case Cell.CELL_TYPE_BOOLEAN:obj = cell.getBooleanCellValue();break;
			case Cell.CELL_TYPE_NUMERIC:obj = cell.getNumericCellValue();break;
		}
		return obj==null?null:obj.toString();
	}
	/**
	 * 
	 * @param object
	 * @param cell
	 * @param excelParams
	 * @param titleString
	 * @param pictures
	 * @param currentImageIndex
	 * @param params
	 * @throws Exception 
	 */
	private static void saveImage(Object object, String picId,
			Map<String, ExcelImportVo> excelParams, String titleString,
			Map<String, PictureData> pictures, ImportParam params) throws Exception {
		if (pictures == null) {
			return;
		}
		PictureData image = pictures.get(picId);
		byte[] data = image.getData();
		String fileName = "pic"+Math.round(Math.random()*100000000000L);
		fileName+= "."+ExcelPublicUtil.getFileExtendName(data);
		if(excelParams.get(titleString).getSaveType()==1){
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String path = request.getSession().getServletContext().getRealPath("\\") + 
			getSaveUrl(excelParams.get(titleString),object);
			path = path.replace("WEB-INF/classes/","");
			path = path.replace("file:/","");
			File savefile = new File(path);
			if(!savefile.exists()){
				savefile.mkdirs();
			}
			savefile = new File(path+"/"+fileName);
			FileCopyUtils.copy(data, savefile);
			setValues(excelParams.get(titleString),object,getSaveUrl(excelParams.get(titleString),object)+"/"+fileName);
		}else{
			setValues(excelParams.get(titleString),object,data);
		}
	}
	/**
	 * 获取保存的真实路径
	 * @param excelImportVo
	 * @param object
	 * @return
	 * @throws Exception 
	 */
	private static String getSaveUrl(ExcelImportVo excelImportVo,
			Object object) throws Exception {
		String url = "";
		if(excelImportVo.getSaveUrl().equals("upload")){
			if(excelImportVo.getSetMethods()!=null
					&&excelImportVo.getSetMethods().size()>0){
				object = getFieldBySomeMethod(excelImportVo.getSetMethods(), object);
			}
			url =  object.getClass().getName().split("\\.")[object.getClass().getName().split("\\.").length-1];
			return excelImportVo.getSaveUrl()+"/"+url.substring(0, url.lastIndexOf("Entity"));
		}
		return excelImportVo.getSaveUrl();
	}

	/***
	 * 向List里面继续添加元素
	 * 
	 * @param object
	 * @param param
	 * @param row
	 * @param titlemap 
	 * @param targetId
	 * @param params 
	 * @param currentImageIndex 
	 * @param pictures 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addListContinue(Object object,
			ExcelCollectionVo param, Row row,
			Map<Integer, String> titlemap, String targetId,
			Map<String, PictureData> pictures,ImportParam params) throws Exception {
		Collection collection = (Collection) ExcelPublicUtil.getMethod(
				param.getName(), object.getClass()).invoke(object,  new Object[] {});
		Object entity = ExcelPublicUtil.createObject(param.getType(), targetId);
		String picId;
		boolean isUsed = false;//是否需要加上这个对象
		for(int i =row.getFirstCellNum() ;i<row.getLastCellNum();i++){
			Cell cell = row.getCell(i);
			String titleString = (String) titlemap.get(i);
			if (param.getExcelParams().containsKey(titleString)) {
				if(param.getExcelParams().get(titleString).getType()==2){
					picId = row.getRowNum()+"_"+i;
					saveImage(object,picId,param.getExcelParams(),
							titleString,pictures,params);
				}else{
					judgeTypeAndSetValue(entity, cell, param.getExcelParams(), titleString);
				}
				isUsed = true;
			}
		}
		if(isUsed){
			collection.add(entity);
		}
	}

	/**
	 * 设置值
	 * 
	 * @param object
	 * @param excelParams
	 * @param cell
	 * @param titleString
	 */
	private static void judgeTypeAndSetValue(Object object, Cell cell,
			Map<String, ExcelImportVo> excelParams, String titleString)
			throws Exception {
		ExcelImportVo entity = excelParams.get(titleString);
		Method setMethod = entity.getSetMethods()!=null&&entity.getSetMethods().size() > 0 ? entity
				.getSetMethods().get(entity.getSetMethods().size() - 1)
				: entity.getSetMethod();
		Type[] ts = setMethod.getGenericParameterTypes();
		String xclass = ts[0].toString();
		if (xclass.equals("class java.lang.String")) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			setValues(entity, object, cell.getStringCellValue());
		} else if (xclass.equals("class java.util.Date")) {
			Date cellDate = null;
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				// 日期格式
				cellDate = cell.getDateCellValue();
				setValues(entity, object, cellDate);
			} else {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cellDate = getDateData(entity,cell.getStringCellValue());
				setValues(entity, object, cellDate);
			}
		} else if (xclass.equals("class java.lang.Boolean")) {
			boolean valBool;
			if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
				valBool = cell.getBooleanCellValue();
			} else {
				valBool = cell.getStringCellValue().equalsIgnoreCase("true")
						|| (!cell.getStringCellValue().equals("0"));
			}
			setValues(entity, object, valBool);
		} else if (xclass.equals("class java.lang.Integer")) {
			Integer valInt;
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				valInt = (new Double(cell.getNumericCellValue())).intValue();
			} else {
				valInt = new Integer(cell.getStringCellValue());
			}
			setValues(entity, object, valInt);
		} else if (xclass.equals("class java.lang.Long")) {
			Long valLong;
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				valLong = (new Double(cell.getNumericCellValue())).longValue();
			} else {
				valLong = new Long(cell.getStringCellValue());
			}
			setValues(entity, object, valLong);
		} else if (xclass.equals("class java.math.BigDecimal")) {
			BigDecimal valDecimal;
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				valDecimal = new BigDecimal(cell.getNumericCellValue());
			} else {
				valDecimal = new BigDecimal(cell.getStringCellValue());
			}
			setValues(entity, object, valDecimal);
		} else if (xclass.equals("class java.lang.Double")) {
			Double valDouble;
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				valDouble = new Double(cell.getNumericCellValue());
			} else {
				valDouble = new Double(cell.getStringCellValue());
			}
			setValues(entity, object, valDouble);
		}

	}
	/**
	 * 获取日期类型数据
	 *@Author JueYue
	 *@date   2013年11月26日
	 *@param entity 
	 *@param stringCellValue
	 *@return
	 */
	private static Date getDateData(ExcelImportVo entity, String value) {
		if(StringUtils.isNotEmpty(entity.getImportFormat())&&
				StringUtils.isNotEmpty(value)){
			SimpleDateFormat format = new SimpleDateFormat(entity.getImportFormat());
			try {
				return format.parse(value);
			} catch (ParseException e) {}
		}
		return null;
	}
	/**
	 * 
	 * @param entity
	 * @param object
	 * @param value
	 * @throws Exception
	 */
	private static void setValues(ExcelImportVo entity, Object object,
			Object value) throws Exception {
		if (entity.getSetMethods() != null) {
			setFieldBySomeMethod(entity.getSetMethods(), object, value);
		} else {
			entity.getSetMethod().invoke(object, value);
		}
	}

	/**
	 * 多个get 最后再set
	 * 
	 * @param setMethods
	 * @param object
	 */
	private static void setFieldBySomeMethod(List<Method> setMethods,
			Object object, Object value) throws Exception {
		Object t = getFieldBySomeMethod(setMethods, object);
		setMethods.get(setMethods.size() - 1).invoke(t, value);
	}

	private static Object getFieldBySomeMethod(List<Method> list, Object t)
			throws Exception {
		Method m;
		for (int i = 0; i < list.size() - 1; i++) {
			m = list.get(i);
			t = m.invoke(t, new Object[] {});
		}
		return t;
	}

	/**
	 * 获取需要导出的全部字段
	 * 
	 * @param targetId
	 *            目标ID
	 * @param excelCollection
	 * @param filed
	 * @throws Exception
	 */
	private static void getAllExcelField(String targetId, Field[] fields,
			Map<String, ExcelImportVo> excelParams,
			List<ExcelCollectionVo> excelCollection, Class<?> clazz,
			List<Method> getMethods) throws Exception {
		ExcelImportVo excelEntity = null;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (ExcelPublicUtil.isNotUserExcelUserThis(field, targetId)) {
				continue;
			}
			if (ExcelPublicUtil.isCollection(field.getType())) {
				// 集合对象设置属性
				ExcelCollectionVo collection = new ExcelCollectionVo();
				collection.setName(field.getName());
				Map<String, ExcelImportVo> temp = new HashMap<String, ExcelImportVo>();
				ParameterizedType pt = (ParameterizedType) field
						.getGenericType();
				Class<?> clz = (Class<?>) pt.getActualTypeArguments()[0];
				collection.setType(clz);
				getExcelFieldList(targetId,
						ExcelPublicUtil.getClassFields(clz), clz, temp, null);
				collection.setExcelParams(temp);
				excelCollection.add(collection);
			} else if (ExcelPublicUtil.isJavaClass(field)) {
				addEntityToMap(targetId, field, excelEntity, clazz,getMethods,excelParams);
			} else {
				List<Method> newMethods = new ArrayList<Method>();
				if (getMethods != null) {
					newMethods.addAll(getMethods);
				}
				newMethods.add(ExcelPublicUtil.getMethod(field.getName(),
						clazz));
				getAllExcelField(targetId,
						ExcelPublicUtil.getClassFields(field.getType()),
						excelParams, excelCollection, field.getType(),
						newMethods);
			}
		}
	}

	private static void getExcelFieldList(String targetId, Field[] fields,
			Class<?> clazz, Map<String, ExcelImportVo> temp,
			List<Method> getMethods) throws Exception {
		ExcelImportVo excelEntity = null;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (ExcelPublicUtil.isNotUserExcelUserThis(field, targetId)) {
				continue;
			}
			if (ExcelPublicUtil.isJavaClass(field)) {
				addEntityToMap(targetId, field, excelEntity, clazz,getMethods,temp);
			} else {
				List<Method> newMethods = new ArrayList<Method>();
				if (getMethods != null) {
					newMethods.addAll(getMethods);
				}
				newMethods.add(ExcelPublicUtil.getMethod(field.getName(),
						clazz,field.getType()));
				getExcelFieldList(targetId,
						ExcelPublicUtil.getClassFields(field.getType()),
						field.getType(), temp, newMethods);
			}
		}
	}
	/**
	 * 把这个注解解析放到类型对象中
	 * @param targetId
	 * @param field
	 * @param excelEntity
	 * @param excel
	 * @param clazz
	 * @param getMethods
	 * @param temp
	 * @throws Exception 
	 */
	private static void addEntityToMap(String targetId, Field field,
			ExcelImportVo excelEntity, Class<?> clazz,
			List<Method> getMethods, Map<String, ExcelImportVo> temp) throws Exception {
		Excel excel = field.getAnnotation(Excel.class);
		excelEntity = new ExcelImportVo();
		excelEntity.setType(excel.exportType());
		excelEntity.setSaveUrl(excel.savePath());
		excelEntity.setSaveType(excel.imageType());
		getExcelField(targetId, field, excelEntity, excel, clazz);
		if (getMethods != null) {
			List<Method> newMethods = new ArrayList<Method>();
			newMethods.addAll(getMethods);
			newMethods.add(excelEntity.getSetMethod());
			excelEntity.setSetMethods(newMethods);
		}
		temp.put(excelEntity.getName(), excelEntity);
		
	}

	private static void getExcelField(String targetId, Field field,
			ExcelImportVo excelEntity, Excel excel, Class<?> clazz)
			throws Exception {
		excelEntity.setName(getExcelName(excel.exportName(), targetId));
		String fieldname = field.getName();
		if (excel.importConvertSign() == 1||excel.imExConvert()==1) {
			StringBuffer getConvertMethodName = new StringBuffer("convertSet");
			getConvertMethodName
					.append(fieldname.substring(0, 1).toUpperCase());
			getConvertMethodName.append(fieldname.substring(1));
			Method getConvertMethod = clazz.getMethod(
					getConvertMethodName.toString(), new Class[] {field.getType()});
			excelEntity.setSetMethod(getConvertMethod);
		} else {
			excelEntity.setSetMethod(ExcelPublicUtil.getMethod(fieldname,
					clazz,field.getType()));
		}
		if(StringUtils.isEmpty(excel.importFormat())){
			excelEntity.setImportFormat(excel.imExFormat());
		}else{
			excelEntity.setImportFormat(excel.importFormat());
		}
	}

	/**
	 * 判断在这个单元格显示的名称
	 * 
	 * @param exportName
	 * @param targetId
	 * @return
	 */
	private static String getExcelName(String exportName, String targetId) {
		if (exportName.indexOf("_") < 0) {
			return exportName;
		}
		String[] arr = exportName.split(",");
		for (String str : arr) {
			if (str.indexOf(targetId) != -1) {
				return str.split("_")[0];
			}
		}
		return null;
	}

}
