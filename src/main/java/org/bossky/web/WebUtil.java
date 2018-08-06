package org.bossky.web;

import javax.servlet.http.HttpServletRequest;

import org.bossky.common.util.Misc;

/**
 * web工具类
 * 
 * @author bo
 *
 */
public class WebUtil {
	/**
	 * 判断字符串是否为ip
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean isIp(String ip) {
		if (Misc.isEmpty(ip)) {
			return false;
		}
		String[] ips = ip.split("\\.");
		if (ips.length != 4) {
			return false;
		}
		try {
			for (int i = 0; i < ips.length; i++) {
				int l = Integer.parseInt(ips[i]);
				if (l < 0 || l > 255) {
					return false;
				}
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 获取ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (!isIp(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (!isIp(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (!isIp(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 通过左移位操作（<<）给每一段的数字加权 第一段的权为2的24次方 第二段的权为2的16次方 第三段的权为2的8次方 最后一段的权为1
	 * 
	 * @param ip
	 * @return int
	 */
	public static long ipToLong(String ip) {
		String[] ips = ip.split("\\.");
		return (Long.parseLong(ips[0]) << 24) + (Long.parseLong(ips[1]) << 16) + (Long.parseLong(ips[2]) << 8)
				+ Long.parseLong(ips[3]);
	}

	/**
	 * 将整数值进行右移位操作（>>） 右移24位，右移时高位补0，得到的数字即为第一段IP 右移16位，右移时高位补0，得到的数字即为第二段IP
	 * 右移8位，右移时高位补0，得到的数字即为第三段IP 最后一段的为第四段IP
	 * 
	 * @param i
	 * @return String
	 */
	public static String longToIp(long i) {
		return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + (i & 0xFF);
	}

	public static void main(String[] args) {
		long i = ipToLong("255.255.255.255");
		System.out.println(i);
		System.out.println(longToIp(i));
	}
}