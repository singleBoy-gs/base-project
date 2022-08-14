package com.base.common.config;

import com.p6spy.engine.spy.appender.FileLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class P6SpyLogger extends FileLogger {

	@Override
	public void logText(String text) {
		super.logText(text);
		log.info(text);
	}
}