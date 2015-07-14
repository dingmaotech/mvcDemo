package com.dingmao.platform.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 功能:网络通信相关的工具类
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
public class NetWorkUtils {
	/**
	 * 获取本机IP地址.
	 * 
	 * @return 当前本机IP
	 */
	public static String getServerIp() {
		String ip = "";
		try {
			Enumeration<?> netInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces
						.nextElement();
				if (!"eth0".equals(ni.getName())) {
					continue;
				} else {
					Enumeration<?> netInterfaces2 = ni.getInetAddresses();
					while (netInterfaces2.hasMoreElements()) {
						InetAddress ia = (InetAddress) netInterfaces2
								.nextElement();
						ip = ia.getHostAddress();
					}
					break;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return ip;
	}
}
