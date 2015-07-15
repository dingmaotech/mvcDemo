package com.dingmao.platform.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能:时间格式化(工具类)
 */
public class DateUtils {

	// 标识是否初始化
	private static boolean isInit = false;
	private static DateFormat DateFormat = null;
	private static DateFormat DateTimeFormat = null;
	private static DateFormat DateTimeStrFormat = null;

	// 日期格式
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	// 日期时间格式
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 日期时间格式2
	public static final String DATETIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm:ss";
	// 日期时间格式2
	public static final String DATE_FORMAT_CN = "yyyy年MM月dd日";
	// 日期时间字符串格式
	public static final String DATETIME_STR_FORMAT = "yyyyMMddHHmmss";

	//
	public static final int MinutePerDay = 24 * 60;

	/**
	 * @功能说明: 初始化时间格式化对象
	 * @param
	 * @return void
	 * @exception
	 */
	public static void init() {
		if (!isInit) {
			DateFormat = new SimpleDateFormat(DATE_FORMAT);
			DateTimeFormat = new SimpleDateFormat(DATETIME_FORMAT);
			DateTimeStrFormat = new SimpleDateFormat(DATETIME_STR_FORMAT);
			isInit = true;
		}
	}

	/**
	 * @功能说明:取得当前日期时间字符串，格式由 <code>DATETIME_FORMAT</code>指定
	 * @param
	 * @return String 日期时间字符串
	 * @exception
	 */
	public static String getCurrentDateTime() {
		init();
		return DateTimeFormat.format(new Date());
	}

	/**
	 * 功能说明:取得当前日期时间字符串，格式由 <code>DATETIME_STR_FORMAT</code>指定
	 * 
	 * @param
	 * @return String 日期时间字符串
	 * @exception
	 */
	public static String getCurrentDateTimeStr() {
		init();
		return DateTimeStrFormat.format(new Date());
	}

	/**
	 * 功能说明:取得当前日期字符串，格式由 <code>DATE_FORMAT</code>指定
	 * 
	 * @param
	 * @return String 日期字符串
	 * @exception
	 */
	public static String getCurrentDate() {
		init();
		return DateFormat.format(new Date());
	}

	/**
	 * 功能说明:格式化日期时间对象
	 * 
	 * @param date
	 *            日期时间对象
	 * @return String 字符串形式的日期时间
	 * @exception
	 */
	public static String formatDateTime(Date date) {
		init();
		if (date == null) {
			return "";
		}
		return DateTimeFormat.format(date);
	}

	/**
	 * 功能说明:格式化日期对象
	 * 
	 * @param date
	 *            日期对象
	 * @return String 字符串形式的日期
	 * @exception
	 */
	public static String formatDate(Date date) {
		init();
		if (date == null) {
			return "";
		}
		return DateFormat.format(date);
	}

	/**
	 * 功能说明:格式化指定日期对象的下一天
	 * 
	 * @param date
	 *            日期对象
	 * @return String 字符串形式的日期
	 * @exception
	 */
	public static String formatNextDate(Date date) {
		init();
		if (date == null) {
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		date = calendar.getTime();
		return DateFormat.format(date);
	}

	/**
	 * 功能说明:根据时间获取完整时间
	 * 
	 * @param time
	 *            小时
	 * @return String
	 * @exception
	 */
	public static String formatTimeLength(int time) {
		String timeStr = "";
		if (time < 60) {
			timeStr = time + "分钟";
		} else if (time < MinutePerDay) {
			timeStr = time / 60 + "小时";
		} else {
			timeStr = time / MinutePerDay + "天";
		}
		return timeStr;
	}

	/**
	 * 功能说明 根据字符串取得指定的日期时间对象
	 * 
	 * @param date
	 *            字符串
	 * @return 日期时间对象
	 * @exception
	 */
	public static Date getDateTime(String date) throws ParseException {
		init();
		if (date == null || "".equals(date)) {
			return null;
		}
		return DateTimeFormat.parse(date);
	}

	/**
	 * 功能说明:根据字符串取得指定的日期对象
	 * 
	 * @param date
	 *            字符串
	 * @return 日期对象
	 * @exception
	 */
	public static Date getDate(String date) throws ParseException {
		init();
		if (date == null || "".equals(date)) {
			return null;
		}
		return DateFormat.parse(date);
	}

	/**
	 * 功能说明:比较时间是否在当前时间之前
	 * 
	 * @param date
	 *            指定的时间
	 * @return true为在当前时间之前
	 * @exception
	 */
	public static boolean beforeNow(Date date) {
		return new Date().after(date);
	}

	/**
	 * 功能说明:取得当前日期时间字符串，格式由<code>DATE_FORMAT2</code>指定
	 * 
	 * @param date
	 *            指定的时间
	 * @return String 日期时间字符串 add by zcf
	 * @exception
	 */
	public static String parseDateForChinese(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_CN);
		return sdf.format(date);
	}

	/**
	 * 功能说明:取得当前日期时间字符串，格式由<code>DATETIME_FORMAT2</code>指定
	 * 
	 * @param date
	 *            指定的时间
	 * @return String 日期时间字符串 add by zcf
	 * @exception
	 */
	public static String parseDateTimeForChinese(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.format(date);
	}

	/**
	 * 功能说明:获取当前年份
	 * 
	 * @param
	 * @return int 年份
	 * @exception
	 */
	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.YEAR);
	}

}
