package com.base.common.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author Single Minded
 * @create 2022-08-27 12:52:14
 * @Description 时间工具类
 * @since 1.0
 */
public class DateUtils extends DateUtil {

	/**
	 * 根据传入的格式返回当前时间
	 * @param format 格式化时间的格式：参见 DatePattern 类
	 * @return
	 */
	public static String now(String format) {
		return format(new Date(), format);
	}

	public static void main(String[] args) {
		System.out.println(now(DatePattern.CHINESE_DATE_PATTERN));
	}
}
