package com.dingmao.mvcdemo.dict.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.dingmao.mvcdemo.dict.dao.DicCatalogDao;
import com.dingmao.mvcdemo.dict.model.DicCatalog;

/**
 * 功能：获取所有字典的Service,程序启动时自动运行init方法然后向dicMap赋值.
 * <p>
 * 修改历史：对程序的修改历史进行记录
 * </p>
 */
public class DictService {
	private final static Log logger = LogFactory.getLog(DictService.class);
	/** 静态字典数据 **/
	private static Map<String, Map<String, String>> DIC_MAP = new LinkedHashMap<String, Map<String, String>>();

	private DicCatalogDao dicCatalogDao;

	/**
	 * @return dicCatalogDao
	 */
	public DicCatalogDao getDicCatalogDao() {
		return dicCatalogDao;
	}

	/**
	 * @param dicCatalogDao
	 *            the dicCatalogDao to set
	 */
	public void setDicCatalogDao(DicCatalogDao dicCatalogDao) {
		this.dicCatalogDao = dicCatalogDao;
	}

	/**
	 * 根据传入的name返回对应的字典.
	 * 
	 * @param name
	 * @return
	 */
	public static Map<String, String> getDicMap(String name) {
		if (DIC_MAP.containsKey(name.toUpperCase())) {
			return DIC_MAP.get(name.toUpperCase());
		} else {
			logger.debug("字典不存在[" + name + "]字典");
			return null;
		}
	}

	/**
	 * 根据传入的name返回对应的字典包含的对象个数.
	 * 
	 * @param name
	 * @return
	 */
	public static Integer getDicMapSize(String name) {
		if (DIC_MAP.containsKey(name.toUpperCase())) {
			return DictService.DIC_MAP.get(name.toUpperCase()).size();
		} else {
			logger.debug("字典不存在[" + name + "]字典");
			return 0;
		}
	}

	/**
	 * 根据key获取某一字典中所对应的value值.
	 * 
	 * @param dicMapName
	 *            字典名称
	 * @param key
	 *            字典中key值
	 * @return
	 */
	public static String getDicValue(String name, String key) {
		if (DIC_MAP.containsKey(name.toUpperCase())) {
			Map<String, String> map = DictService.DIC_MAP.get(name
					.toUpperCase());
			if (map.containsKey(key)) {
				return map.get(key);
			} else {
				logger.debug("[" + name + "]字典中,不存在 key = [" + key + "]的值");
				return null;
			}

		} else {
			logger.debug("字典不存在[" + name + "]字典");
			return null;
		}

	}

	/**
	 * 功能说明:根据value获取某一字典中所对应的key值.
	 * 
	 * @param dicMapName
	 *            字典名称
	 * @param value
	 *            字典中value值
	 * @return
	 * @exception
	 */
	public static String getDicKey(String name, String value) {
		Map<String, String> map = DictService.DIC_MAP.get(name.toUpperCase());
		for (Entry<String, String> k : map.entrySet()) {
			if (!k.equals("") && k != null) {
				return k.getKey();
			}
		}
		return null;
	}

	/**
	 * 初始化字典
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Integer init() {
		List<DicCatalog> list = dicCatalogDao.findAll();
		List<Object[]> listMap = null;
		Map<String, String> map = null;
		for (DicCatalog dic : list) {
			map = new LinkedHashMap<String, String>();
			Session session = dicCatalogDao.getSessionFactory()
					.getCurrentSession();
			Query query = session.createSQLQuery("select name,value from "
					+ dic.getName());
			listMap = (List<Object[]>) query.list();
			if (listMap != null && !listMap.isEmpty()) {
				map = new LinkedHashMap<String, String>();
				for (Object[] subDic : listMap) {
					map.put(String.valueOf(subDic[1]),
							String.valueOf(subDic[0]));
				}
			}
			DictService.DIC_MAP.put(dic.getCode().toUpperCase(), map);
		}
		return DIC_MAP.size();
	}

	public static Map<String, Map<String, String>> getAllDicMap() {
		return DIC_MAP;
	}

	/**
	 * 功能说明:重新加载字典信息
	 * 
	 * @exception
	 */
	public void reLoad() {
		DictService.DIC_MAP.clear();
		init();
	}

}
