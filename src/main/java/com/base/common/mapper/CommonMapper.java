package com.base.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Single Minded
 * @create 2022-08-15 10:57:31
 * @Description 公共Mapper
 * @since 1.0
 */
@Mapper
public interface CommonMapper {

	/**
	 * 存储请求接口日志
	 * @param url    请求地址
	 * @param header 请求头
	 * @param param  请求参数
	 * @param result 请求结果
	 */
	void saveLog(@Param("url") String url,
				 @Param("header") String header,
				 @Param("param") String param,
				 @Param("result") String result);
}
