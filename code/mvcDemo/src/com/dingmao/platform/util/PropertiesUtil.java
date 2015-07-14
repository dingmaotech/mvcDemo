package com.dingmao.platform.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 功能:Properties操作工具类.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
public class PropertiesUtil {
	private static Log logger = LogFactory.getLog(PropertiesUtil.class);

	private String SYSTEM = "application.properties";

	private static PropertiesUtil util = null;

	public static PropertiesUtil getInstance() {
		if (util == null) {
			util = new PropertiesUtil();
		}
		return util;
	}

	/**
	 * 通过file获取属性文件
	 * 
	 * @param file
	 * @return
	 */
	@SuppressWarnings("resource")
	public static Properties getProperties(String fileName) {
		Properties prop = null;
		InputStream is = null;
		try {
			if (prop == null) {
				try {
					is = new FileInputStream(fileName);// 创建输入流
				} catch (Exception e) {
					if (fileName.startsWith("/")) {
						is = PropertiesUtil.class.getResourceAsStream(fileName);
					} else {
						is = PropertiesUtil.class
								.getResourceAsStream((new StringBuilder("/"))
										.append(fileName).toString());
					}
				}
			}
			prop = new Properties();
			if (is != null) {
				prop.load(is);
			}
		} catch (FileNotFoundException e) {
			logger.debug("FileNotFoundException ", e);
		} catch (IOException e) {
			logger.debug("Read" + fileName + "IOException ", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return prop;
	}

	public String getPropertyValue(String fileName, String strKey) {
		Properties p = getProperties(fileName);
		return p.getProperty(strKey);
	}

	public String getPropertiesValue(String strKey) {
		return getPropertyValue(SYSTEM, strKey);
	}

	public void setSystemConfigValue(String strKey, String strValue) {
		Properties p = getProperties(SYSTEM);
		p.setProperty(strKey, strValue);
	}

	public static void saveProperties(Properties prop, String file) {
		if (prop == null) {
			return;
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file, false);
			prop.store(out, "modify properties file");
		} catch (FileNotFoundException e) {
			logger.debug("FileNotFoundException ", e);
		} catch (IOException e) {
			logger.debug("Save" + file + "IOException ", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void addProperties(Map<String, String> map, String file) {
		Properties prop = getProperties(file);
		if (map != null && map.size() > 0) {
			Set<String> set = map.keySet();
			for (String e : set) {
				prop.put(e, map.get(e));
			}
		}
		saveProperties(prop, file);
	}

	public static void updateProperties(String key, String value, String file) {
		if (key == null) {
			return;
		}
		Properties prop = getProperties(file);
		prop.put(key, value);
		saveProperties(prop, file);
		prop.put(key, value);
		saveProperties(prop, file);
	}

}
