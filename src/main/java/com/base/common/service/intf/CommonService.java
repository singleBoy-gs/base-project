package com.base.common.service.intf;

/**
 * @author Single Minded
 * @create 2022-08-07 18:52:51
 * @Description
 * @since 1.0
 */
public interface CommonService {

	/**
	 * 存储请求接口日志
	 * @param url    请求地址
	 * @param header 请求头
	 * @param param  请求参数
	 * @param result 请求结果
	 */
	void saveLog(String url, String header, String param, String result);
}
