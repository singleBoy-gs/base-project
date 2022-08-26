package com.base.common.response;

import com.base.common.constant.enums.ReturnEnums;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author Single Minded
 * @create 2022-08-07 12:53:25
 * @Description 公共响应类
 * @since 1.0
 */
@Getter
@JsonInclude(Include.NON_NULL)
@ApiModel(value = "R对象", description = "通用响应实体")
public class R<T> implements Serializable {

	@ApiModelProperty("响应编码")
	private int code;
	@ApiModelProperty("响应描述")
	private String msg;
	@ApiModelProperty("响应数据")
	private T data;

	private R(int code, String message, T data) {
		this.code = code;
		this.msg = message;
		this.data = data;
	}

	public static <T> R<T> success(){
		return success(null);
	}

	public static <T> R<T> success(T data){
		return success(ReturnEnums.DEFAULT_SUCCESS.getMsg(), data);
	}

	public static <T> R<T> success(String message, T data){
		return getR(ReturnEnums.DEFAULT_SUCCESS.getCode(), message, data);
	}

	public static <T> R<T> error(){
		return error("");
	}

	public static <T> R<T> error(String msg){
		return getR(ReturnEnums.DEFAULT_ERROR.getCode(), msg, null);
	}

	public static <T> R<T> error(ReturnEnums returnEnums){
		return getR(returnEnums, null);
	}

	private static <T>R<T> getR(ReturnEnums returnEnums, T data) {
		return getR(returnEnums.getCode(), returnEnums.getMsg(), data);
	}

	private static <T>R<T> getR(int code, String message, T data) {
		return new R(code, message, data);
	}
}