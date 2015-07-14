package com.dingmao.platform.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Calendar;
import java.util.ArrayList;

/**
 * 此类中封装一些常用的数值类型方法。 所有方法都是静态方法，不需要生成此类的实例， 为避免生成此类的实例，构造方法被申明为private类型的。
 * 
 */
public class NumberUtils {

	/**
	 * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
	 */
	private NumberUtils() {
	}

	private static Random random = new Random();

	/**
	 * 生成随机数,种子为Calendar.DATE时间 (基数+占位符+9位时间)的一个字符串,根据占位符得到不同长度的随机数,最小10位
	 * 
	 * @author lx_mxin
	 * @param base基数
	 * @param placeholder占位符
	 * @return 基数+占位符+9位时间的长度
	 */
	public static long getRandomNum(int base, int placeholder) {
		int nextInt = random.nextInt(1000);
		Calendar cal = Calendar.getInstance();
		int date = cal.get(Calendar.DATE);
		base += placeholder;
		long second = date / 1000;
		String seed = second + nextInt + "";
		seed = seed.substring(1, seed.length());
		seed = placeholder + base + seed;
		return Long.parseLong(seed);
	}

	/**
	 * 精确的除法运算,返回一个商.
	 * 
	 * @author lx_mxin
	 * @param dividend除数
	 * @param divisor被除数
	 * @param scale保留小数点后几位
	 * @return 商
	 */
	public static double div(double dividend, double divisor, int scale) {
		if (scale < 0 || divisor == 0)
			return 0.00;
		BigDecimal b1 = new BigDecimal(dividend);
		BigDecimal b2 = new BigDecimal(divisor);
		return (b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP)).doubleValue();
	}

	/**
	 * 精确的除法运算,返回一个商的百分比. 有除不尽可能，可选择保留小数点位数
	 * 
	 * @author lx_mxin and cpxy
	 * @param dividend除数
	 * @param divisor被除数
	 * @param scale保留小数点后几位
	 * @return 百分比
	 */
	public static String percent(double dividend, double divisor, int scale) {
		String number = null;
		if (divisor == 0)
			return "0%";
		String testStr = String.valueOf(div(dividend, divisor, 2) * 100);
		String minDotNumber = testStr.substring(parseDot(testStr),
				testStr.length());// 获得小数点后数值
		testStr = testStr.substring(0, parseDot(testStr)); // 获得整数数值
		if (scale == 0)
			return testStr + "%";
		minDotNumber = minDotNumber.substring(0, scale + 1);
		number = testStr + minDotNumber + "%";
		return number;

	}

	/**
	 * 生成指定位数随机数字,并返回字符形式,过大会导致机器停止响应
	 * 
	 * @author cpxy
	 * @param values指定位数
	 * @return String 随机的一组字符型数值
	 */
	public static String toNumberValue(int values) {
		String tempValues = "1";

		if (values < 0 || values > 10)
			values = 0;
		for (int i = 1; i < values; i++) {
			tempValues = tempValues + "0";
		}
		int tempInt = Integer.parseInt(tempValues);
		tempValues = null;
		ArrayList<String> list = new ArrayList<String>();

		for (int j = 0; j < tempInt; j++) {
			list.add(String.valueOf(j)); // 加载指定位数数值
		}
		String rs = "";
		int tmp = -1;
		if (list.size() > 1) {
			while (list.size() > 0) {
				tmp = random.nextInt(list.size());
				rs += list.get(tmp) + ",";
				list.remove(tmp);
			}
		} else {
			rs = String.valueOf(random.nextInt(9));
		}

		return rs;
	}

	/**
	 * 根据当前豪秒生成一组随机数，扰乱数越大生成随机数越大
	 * 
	 * @author cpxy
	 * @param idByte扰乱数
	 * @return String 返回随机数数值
	 */
	public synchronized static String toMillisId(int idByte) {
		StringBuffer result = new StringBuffer();
		result = result.append(System.currentTimeMillis());
		for (int i = 1; i < idByte; i++) {
			result = result.append(random.nextInt(9));
		}
		return result.toString();
	}

	/**
	 * 根据数值产生一个指定数值到其倍数之间的随机数
	 * 
	 * @author cpxy
	 * @param vals指定启始数值
	 * @return double 返回随机数数值
	 */
	public static double toRanValueToDouble(int vals) {
		double val = (vals + 1) * Math.random();
		val = Math.floor(val) + vals;
		return val;
	}

	/**
	 * 返回指定数值的阶乘
	 * 
	 * @author cpxy and lx_mxin
	 * @param numerical指定启始数值
	 * @param square指定数值的倍数
	 * @return int 返回阶乘
	 */
	public static int doFactorial(int numerical, int multiple) {
		if (numerical == 1)
			return 1;
		if (multiple == 1)
			return numerical;
		int original = numerical;
		for (int i = 1; i < multiple; i++) {
			numerical = numerical * original;
		}
		return numerical;
	}

	/**
	 * 返回两个字符串数字的和
	 * 
	 * @author cpxy and lx_mxin
	 * @param val1第一个数
	 * @param val2第二个数
	 * @return int 和
	 */
	public static int addStr(String val1, String val2) {
		if (checkNumber(val1) == false || checkNumber(val2) == false)
			return -1;
		BigDecimal b1 = new BigDecimal(val1);
		BigDecimal b2 = new BigDecimal(val2);
		return b1.add(b2).intValue();
	}

	/**
	 * 将给定数值反转输出
	 * 
	 * @author cpxy
	 * @param values指定启始数值
	 * @return 返回反转后数值
	 */
	public static int doNumberRev(String values) {

		if (values.length() == 1)
			return Integer.parseInt(values);
		String result = values.substring(values.length() - 1, values.length());
		result += doNumberRev(values.substring(0, values.length() - 1));
		return Integer.parseInt(result);
	}

	/**
	 * 将给定字符转化为整型
	 * 
	 * @author cpxy
	 * @param values指定启始数值
	 * @return 返回字符类型
	 */
	public static int toNumber(String values) {
		if (checkNumber(values) == false)
			return -1;

		return Integer.parseInt(values);
	}

	/**
	 * 获得字符串中第一个小数点所在位置(由0开始,想看往后的自己递归)
	 * 
	 * @author cpxy
	 * @param values指定数字
	 * @return 返回小数点位置
	 */
	public static int parseDot(String values) {
		int dot = 0;
		for (int i = 0; i < values.length(); i++) {
			char ctemp = values.charAt(i);
			if (ctemp == ".".charAt(0)) {
				dot = i;
				break;
			}
		}
		return dot;
	}

	/**
	 * 返回数组中数值的平均值
	 * 
	 * @author cpxy
	 * @param values指定数组
	 * @return 返回平均值
	 */
	public static int toTotal(ArrayList<?> values) {
		int total = 0;
		if (values.size() > 0) {
			for (int i = 0; i < values.size(); i++) {
				total += Integer.parseInt(values.get(i).toString());
			}
		} else
			return 0;

		return total / values.size();
	}

	/**
	 * 检查指定数值是否为数字形式
	 * 
	 * @author cpxy
	 * @param str指定数字
	 * @return boolean 返回布尔类型
	 */
	public static boolean checkNumber(String str) {
		char[] cnum = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		int plength = str.length();
		for (int i = 0; i < plength; i++) {
			char ctemp = str.charAt(i);
			boolean btemp = false;
			for (int j = 0; j < cnum.length; j++) {
				if (ctemp == cnum[j]) {
					btemp = true;
					break;
				}
			}
			if (!btemp)
				return false;
		}
		return true;
	}

	/**
	 * 计算传入数值的大小(B,KB,MB)
	 * 
	 * @author cpxy
	 * @param size整型数值大小
	 * @return 值+(B,KB,MB)
	 */
	public static String toMKByte(int size) {
		if (size > (1024 * 1024)) {
			return ((float) size / (1024 * 1024) + "").substring(0, 4) + "MB";
		} else if (size > 1024) {
			return ((float) size / 1024 + "").substring(0, 4) + "KB";
		} else
			return size + "B";
	}
}
