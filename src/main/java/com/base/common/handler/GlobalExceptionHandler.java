package com.base.common.handler;

import com.base.common.exception.BusinessException;
import com.base.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author Single Minded
 * @create 2022-08-14 14:04:13
 * @Description
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public R handleException(Exception e) {
		log.error("{}", e);
		return R.error(e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(BusinessException.class)
	public R handleBusinessException(BusinessException e) {
		log.error("{}", e);
		return R.error(e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(BindException.class)
	public R handleBindException(BindException e) {
		List<ObjectError> allErrors = e.getAllErrors();
		int size = allErrors.size();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < size; i++) {
			stringBuilder.append(allErrors.get(i).getDefaultMessage());
			if (i < (size-1)) {
				stringBuilder.append("；");
			}
		}
		log.error("{}", e);
		return R.error(stringBuilder.toString());
	}
}
