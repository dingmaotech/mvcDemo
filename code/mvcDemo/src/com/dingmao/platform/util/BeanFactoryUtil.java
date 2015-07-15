package com.dingmao.platform.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryUtil implements ApplicationContextAware {

	private static Log log = LogFactory.getLog(BeanFactoryUtil.class);

	private static volatile ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		if (applicationContext != null) {
			throw new IllegalStateException(
					"BeanFactoryUtil already holded 'applicationContext' !");
		}
		applicationContext = context;
		log.info("holded applicationContext,displayName:"
				+ applicationContext.getDisplayName());
	}

	public static ApplicationContext getApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException(
					"BeanFactoryUtil not holded 'applicationContext' !");
		}
		return applicationContext;
	}

	/**
	 * 仅用于单元测试时不启动web服务器,手工加载spring配置文件 TODO 404中貌似不是这样处理
	 * 
	 * @param xmlPath
	 *            配置文件路径,例如 spring/*.xml
	 */
	public static void initApplicationContext(String xmlPath) {
		if (applicationContext == null) {
			log.info("test unit begin init 'applicationContext'.");
			synchronized (BeanFactoryUtil.class) {
				if (applicationContext == null) {
					// 当spring开始加载后,通过ApplicationContextAware将context注入进来
					applicationContext = new ClassPathXmlApplicationContext(
							"classpath:/" + xmlPath);
				}
			}
		}
	}

	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	public static boolean containsBean(String beanName) {
		return getApplicationContext().containsBean(beanName);
	}

}
