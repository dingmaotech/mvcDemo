// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ApplicationContextUtil.java

package com.dingmao.platform.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	public ApplicationContextUtil() {
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}
}
