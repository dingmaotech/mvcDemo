package com.dingmao.platform.util.json;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 功能:JSON格式帮助类
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
public class JsonUtils {

	/**
	 * 功能说明:把JSON格式数据转换为Bean
	 * 
	 * @param <T>
	 *            泛型类型
	 * @param json
	 *            JSON字符串
	 * @param beanClass
	 *            转换模型类
	 * @return Bean对象
	 * @exception
	 */
	public static <T> T jsonToBean(String json, Class<T> beanClass) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		return beanClass.cast(JSONObject.toBean(jsonObject, beanClass));

	}

	/**
	 * 功能说明:实体对象转JSON格式数据
	 * 
	 * @param bean
	 *            实体对象
	 * @param jsonConfig
	 *            JSON格式配置信息
	 * @return JSON字符串
	 * @exception
	 */
	public static String beanToJson(Object bean, JsonConfig jsonConfig) {
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		return JSONObject.fromObject(bean, jsonConfig).toString();
	}

	public static String beanToJson(Object bean, String[] excludes) {

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		return JSONObject.fromObject(bean, jsonConfig).toString();
	}

	public static String beanToJsonArray(Object bean, String[] excludes) {

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());

		return JSONArray.fromObject(bean, jsonConfig).toString();
	}

	@SuppressWarnings("rawtypes")
	public static String listToJson(List list, JsonConfig jsonConfig) {
		return JSONArray.fromObject(list, jsonConfig).toString();
	}

	/**
	 * 功能说明:list序列化JSON
	 * 
	 * @param list
	 *            集合
	 * @param excludes
	 *            需要过滤的字段
	 * @return JSON字符串
	 * @exception
	 */
	@SuppressWarnings("rawtypes")
	public static String listToJson(List list, String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		return JSONArray.fromObject(list, jsonConfig).toString();
	}

	@SuppressWarnings( { "deprecation", "rawtypes" })
	public static List jsonToList(String json, Class beanClass) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		return JSONArray.toList(jsonArray, beanClass);
	}
}
