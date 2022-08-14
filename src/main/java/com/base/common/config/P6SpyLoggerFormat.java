package com.base.common.config;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class P6SpyLoggerFormat implements MessageFormattingStrategy {

	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
		String content = "\n\t" + now + " | " + elapsed + " | " + category + "\n\t" + P6Util.singleLine(sql).replaceAll(" {2,}"," ");
		return getFormatLogString(content, 31, 0);
	}

	/**
	 * @param colour  颜色代号：背景颜色代号(41-46)；前景色代号(31-36)
	 * @param type    样式代号：0无；1加粗；3斜体；4下划线
	 * @param content 要打印的内容
	 */
	private static String getFormatLogString(String content, int colour, int type) {
		boolean hasType = type != 1 && type != 3 && type != 4;
		if (hasType) {
			return String.format("\033[%dm%s\033[0m", colour, content);
		} else {
			return String.format("\033[%d;%dm%s\033[0m", colour, type, content);
		}
	}

	public static void main(String[] args) {
		System.out.println(getFormatLogString("颜色", 31, 0));
		System.out.println(getFormatLogString("颜色", 32, 0));
		System.out.println(getFormatLogString("颜色", 33, 0));
		System.out.println(getFormatLogString("颜色", 34, 0));
		System.out.println(getFormatLogString("颜色", 35, 0));
		System.out.println(getFormatLogString("颜色", 36, 0));

		System.out.println(getFormatLogString("颜色", 41, 0));
		System.out.println(getFormatLogString("颜色", 42, 0));
		System.out.println(getFormatLogString("颜色", 43, 0));
		System.out.println(getFormatLogString("颜色", 44, 0));
		System.out.println(getFormatLogString("颜色", 45, 0));
		System.out.println(getFormatLogString("颜色", 46, 0));
	}
}