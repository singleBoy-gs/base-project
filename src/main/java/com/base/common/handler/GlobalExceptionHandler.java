package com.base.common.handler;

import com.base.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

/**
 * @author Single Minded
 * @create 2022-08-14 14:04:13
 * @Description
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public R handleBindException(Exception e) {
		log.error("Exception.class");
		//e.printStackTrace();
		return R.error();
	}

	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	public R handleBindException(ValidationException e) {
		log.error("ValidationException.class");
		log.error("{}", e);
		return R.error(e.getMessage());
	}
}
