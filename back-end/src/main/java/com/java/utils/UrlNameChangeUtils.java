package com.java.utils;

/**
 * for：url中的缩写转换工具
 * */

public class UrlNameChangeUtils {

	/**
	 * 将银行缩写转化为中文名称
	 * */
	public static String bankNameChange(String bank) {
		switch (bank) {
		case "1":
			bank="工商银行";
			break;
		case "2":
			bank="农业银行";
			break;
		case "3":
			bank="建设银行";
			break;
		case "4":
			bank="中国银行";
			break;
		default:
			bank=null;
		}
		return bank;
	}
}
