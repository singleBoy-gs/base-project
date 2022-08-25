package com.base.common.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.base.common.constant.enums.ReturnEnums;
import com.base.common.exception.BusinessException;
import com.base.common.service.intf.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author Single Minded
 * @create 2022-08-07 10:39:43
 * @Description 网络请求工具类
 * @since 1.0
 */
@Slf4j
@Component
public class HttpUtils extends HttpUtil{

	static CommonService commonService;
	@Resource
	public void setCommonService(CommonService commonService) {
		HttpUtils.commonService = commonService;
	}

	/**
	 * GET数据
	 * @param url 接口地址
	 * @return
	 */
	public static JSONObject getData(String url) {
		return getData(url, null);
	}

	/**
	 * GET数据
	 * @param url 接口地址
	 * @param paramsMap 调用接口参数
	 * @return
	 */
	public static JSONObject getData(String url, Map<String, Object> paramsMap) {
		return getData(url, paramsMap, null);
	}

	/**
	 * GET数据
	 * @param url 接口地址
	 * @param paramsMap 调用接口参数
	 * @param headerMap 调用接口头信息
	 * @return
	 */
	public static JSONObject getData(String url, Map<String, Object> paramsMap, Map<String, String> headerMap) {
		return requestData(url, paramsMap, headerMap, false);
	}

	/**
	 * POST数据
	 * @param url 接口地址
	 * @param paramsMap 调用接口参数
	 * @return
	 */
	public static JSONObject postData(String url, Map<String, Object> paramsMap) {
		return postData(url, paramsMap, null);
	}

	/**
	 * POST获取数据
	 * @param url 接口地址
	 * @param paramsMap 调用接口参数
	 * @param headerMap 调用接口头信息
	 * @return
	 */
	public static JSONObject postData(String url, Map<String, Object> paramsMap, Map<String, String> headerMap) {
		return requestData(url, paramsMap, headerMap, true);
	}

	/**
	 * 调用接口请求数据
	 * @param url 请求URL
	 * @param paramsMap 调用接口参数
	 * @param headerMap 调用接口头信息
	 * @param isPost 是否POST请求：true-POST；false-GET
	 * @return
	 */
	private static JSONObject requestData(String url, Map<String, Object> paramsMap, Map<String, String> headerMap, boolean isPost) {
		if (StrUtil.isBlank(url)) {
			throw new BusinessException(ReturnEnums.HTTP_URL_NULL_ERROR);
		}

		String mode;
		String body = null;
		HttpRequest httpRequest;
		if (isPost) {
			mode = "POST";
			body = JSONObject.toJSONString(paramsMap);
			httpRequest = createPost(url);
		} else {
			mode = "GET";
			if (MapUtil.isNotEmpty(paramsMap)) {
				StringBuilder bodySb = new StringBuilder();
				paramsMap.forEach((k, v)->{
					bodySb.append("&").append(k).append("=").append(v);
				});
				body = bodySb.substring(1);
			}
			httpRequest = createGet(url);
		}
		String result = httpRequest.addHeaders(headerMap)
								   .body(body)
								   .execute()
								   .body();
		// 日志打印和存储
		String header = JSONObject.toJSONString(headerMap);
		log.info("\n通过《"+ mode +"》方式调用接口《{}》，\n请求头是：\n{}\n入参信息是：\n{}\n返回数据是：{}", url, header, body, result);
		commonService.saveLog(url, header, body, result);

		if (StrUtil.isBlank(result)) {
			return new JSONObject();
		}

		JSONObject resultJsonObject = JSONObject.parseObject(result);
		return resultJsonObject;
	}

	/**
	 * POST获取数据
	 * @param url 接口地址
	 * @param fileUrl 调用接口参数
	 * @return
	 */
	public static JSONObject postFile(String url, String fileUrl) {
		return postFile(url, fileUrl, "");
	}

	/**
	 * POST获取数据
	 * @param url 接口地址
	 * @param fileUrl 调用接口参数
	 * @param formKey 表单key
	 * @return
	 */
	public static JSONObject postFile(String url, String fileUrl, String formKey) {
		return postFile(url, fileUrl, formKey, null, null);
	}

	/**
	 * POST获取数据
	 * @param url 接口地址
	 * @param fileUrl 调用接口参数
	 * @param headerMap 调用接口头信息
	 * @return
	 */
	public static JSONObject postFile(String url, String fileUrl, Map<String, String> headerMap) {
		return postFile(url, fileUrl, null, null, headerMap);
	}

	/**
	 * POST获取数据
	 * @param url 接口地址
	 * @param fileUrl 调用接口参数
	 * @param fileName 文件名
	 * @param headerMap 调用接口头信息
	 * @return
	 */
	public static JSONObject postFile(String url, String fileUrl, String fileName, Map<String, String> headerMap) {
		return postFile(url, fileUrl, null, fileName, headerMap);
	}

	/**
	 * POST获取数据
	 * @param url 接口地址
	 * @param fileUrl 文件url
	 * @param formKey 表单key
	 * @param fileName 文件名
	 * @param headerMap 调用接口头信息
	 * @return
	 */
	public static JSONObject postFile(String url, String fileUrl, String formKey, String fileName, Map<String, String> headerMap) {
		if (StrUtil.isBlank(url)) {
			throw new BusinessException(ReturnEnums.HTTP_URL_NULL_ERROR);
		}
		if (StrUtil.isBlank(fileUrl)) {
			throw new BusinessException(ReturnEnums.HTTP_URL_NULL_ERROR);
		}
		if (StrUtil.isBlank(formKey)) {
			formKey = "file";
		}
		if (StrUtil.isBlank(fileName)) {
			String[] fileUrlArray = fileUrl.split("/");
			if (ArrayUtil.isEmpty(fileUrlArray)) {
				throw new BusinessException(ReturnEnums.HTTP_NAME_NULL_ERROR);
			}
			fileName = fileUrlArray[fileUrlArray.length-1];
			if (!fileName.contains(".")) {
				throw new BusinessException(ReturnEnums.HTTP_NAME_NULL_ERROR);
			}
		}
		String result = createPost(url)
				.addHeaders(headerMap)
				.form(formKey, downloadBytes(fileUrl), fileName)
				.execute()
				.body();

		if (StrUtil.isBlank(result)) {
			return new JSONObject();
		}

		JSONObject resultJsonObject = JSONObject.parseObject(result);
		log.info("\n通过《POST》方式调用接口《{}》\n附件地址是：{}\n表单的key是：{}\n文件名是：{}\n返回数据是：{}", url, fileUrl, formKey, fileName, result);
		return resultJsonObject;
	}

	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
					// 根据网卡取本机配置的IP
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress = inet.getHostAddress();
				}
			}
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
				// = 15
				if (ipAddress.indexOf(",") > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
		} catch (Exception e) {
			ipAddress = "";
		}

		return ipAddress;
	}
}
