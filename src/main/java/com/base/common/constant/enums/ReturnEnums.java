package com.base.common.constant.enums;

import lombok.Getter;

/**
 * @author Single Minded
 * @create 2022-08-07 13:33:50
 * @Description 公共错误码和描述枚举类
 * @since 1.0
 */
@Getter
public enum ReturnEnums {
	/**
	 * 默认成功
	 */
	DEFAULT_SUCCESS(200, "成功"),
	/**
	 * 默认失败
	 */
	DEFAULT_ERROR(500, "失败"),

	HTTP_URL_NULL_ERROR(1001, "URL不能为空"),
	HTTP_RESULT_NULL_ERROR(1002, "接口返回结果为空"),
	HTTP_NAME_NULL_ERROR(1003, "文件名不能为空"),
	HTTP_KEY_NULL_ERROR(1004, "KEY不能为空"),


	BUSINESS_ERROR(9999, "业务错误"),
	;
	// 编码
	private int code;
	// 描述
	private String msg;

	ReturnEnums(int code, String msg){
		this.code = code;
		this.msg = msg;
	}
}
