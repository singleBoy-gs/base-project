package com.base.common.exception;

import com.base.common.constant.enums.ReturnEnums;
import lombok.Getter;

/**
 * @author Single Minded
 * @create 2022-08-07 13:32:11
 * @Description
 * @since 1.0
 */
@Getter
public class BusinessException extends RuntimeException{

	private int code;

	public BusinessException(ReturnEnums returnEnums) {
		super(returnEnums.getMsg());
		this.code = returnEnums.getCode();
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}
