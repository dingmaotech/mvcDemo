package com.dingmao.platform.util;

import java.util.List;

import net.sf.json.JSONObject;

import com.dingmao.platform.util.reflect.ReflectionUtil;
import com.dingmao.platform.vo.datatable.DataTableVo;

/**
 * 
 * @author Administrator
 * 
 */
public class DataTableUtil {
	/**
	 * 循环LIST对象拼接datatable格式的JSON数据
	 * 
	 * @param fields
	 * @param total
	 * @param list
	 */
	private static String datatable(String field, int total, List list)
			throws Exception {
		String[] fields = field.split(",");
		Object[] values = new Object[fields.length];
		StringBuffer jsonTemp = new StringBuffer();
		jsonTemp.append("{\"iTotalDisplayRecords\":" + total
				+ ",\"iTotalRecords\":" + total + ",\"aaData\":[");
		for (int j = 0; j < list.size(); j++) {
			jsonTemp.append("{");
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].toString();
				values[i] = fieldNametoValues(fieldName, list.get(j));
				jsonTemp.append("\"" + fieldName + "\"" + ":\"" + values[i]
						+ "\"");
				if (i != fields.length - 1) {
					jsonTemp.append(",");
				}
			}
			if (j != list.size() - 1) {
				jsonTemp.append("},");
			} else {
				jsonTemp.append("}");
			}
		}
		jsonTemp.append("]}");
		return jsonTemp.toString();
	}

	/**
	 * 
	 * 获取对象内对应字段的值
	 * 
	 * @param fields
	 */
	public static Object fieldNametoValues(String FiledName, Object o) {
		Object value = "";
		String fieldName = "";
		String childFieldName = null;
		if (FiledName.indexOf("_") == -1) {
			if (FiledName.indexOf(".") == -1) {
				fieldName = FiledName;
			} else {
				fieldName = FiledName.substring(0, FiledName.indexOf("."));// 外键字段引用名
				childFieldName = FiledName
						.substring(FiledName.indexOf(".") + 1);// 外键字段名
			}
		} else {
			fieldName = FiledName.substring(0, FiledName.indexOf("_"));// 外键字段引用名
			childFieldName = FiledName.substring(FiledName.indexOf("_") + 1);// 外键字段名
		}
		value = ReflectionUtil.getFieldValue(o, fieldName) == null ? ""
				: ReflectionUtil.getFieldValue(o, fieldName);
		if (value != ""
				&& value != null
				&& (fieldName.indexOf("_") != -1 || fieldName.indexOf(".") != -1)) {
			value = fieldNametoValues(childFieldName, value);
		}
		if (value != "" && value != null) {
			value = value.toString().replaceAll("\r\n", "");
		}
		return value;
	}

	/**
	 * 返回列表JSONObject对象
	 * 
	 * @param field
	 * @param dataGrid
	 * @return
	 */
	@SuppressWarnings("unused")
	public static JSONObject getJson(DataTableVo dataTable, String field) {
		JSONObject jObject = null;
		try {
			jObject = JSONObject
					.fromObject(datatable(field,
							dataTable.getiTotalDisplayRecords(),
							dataTable.getAaData()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jObject;

	}
}
