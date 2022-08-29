package com.base.common.utils;

import cn.hutool.core.util.StrUtil;

import java.util.Optional;

/**
 * @author Single Minded
 * @create 2022-08-27 10:30:32
 * @Description 字符串工具类
 * @since 1.0
 */
public class StringUtils extends StrUtil {

	/**
	 * 字符串常量：等号 {@code "="}
	 */
	public static final String EQUAL = "=";

	/**
	 * 字符串常量：与 {@code "&"}
	 */
	public static final String AMP = "&";

	/**
	 * 将Object对象转换成字符串
	 * @param data 要转换的数据对象
	 * @return
	 */
	public static String parse(Object data) {
		if (data == null) {
			return EMPTY;
		}
		return String.valueOf(data);
	}

	public static String trim(Object data) {
		String result = Optional.ofNullable(parse(data))
				.orElse(EMPTY)
				.trim();
		return result;
	}

	/**
	 * 剔除所有空格
	 * @param data 源数据
	 * @return
	 */
	public static String eliminate(Object data) {
		return eliminate(data, EMPTY);
	}

	/**
	 * 剔除所有空格并将指定的字符替换成空串
	 * @param data 源数据
	 * @param regex 被替换的字符串或正则表达式
	 * @return
	 */
	public static String eliminate(Object data, String regex) {
		return eliminate(data, regex, EMPTY);
	}

	/**
	 * 剔除所有空格并替换指定的字符，默认替换成空串
	 * @param data 源数据
	 * @param regex 被替换的字符串或正则表达式
	 * @param replacement 要替换的字符串
	 * @return
	 */
	public static String eliminate(Object data, String regex, String replacement) {
		if (replacement == null) {
			replacement = EMPTY;
		}
		String result = Optional.ofNullable(parse(data))
								.orElse(EMPTY)
								.replaceAll(regex, replacement)
								.replaceAll(SPACE, EMPTY);
		return result;
	}


	public static void main(String[] args) {
		String data = " 1 a 2 b 3 c 4 d 5 e 6 f 7 g 8 h 9 ";
		String result = trim(data);
		System.out.println(result);
	}
}
