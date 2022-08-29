package com.base.common.utils;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Single Minded
 * @create 2022-08-26 11:01:01
 * @Description
 * @since 1.0
 */
public class JsonUtils {

	/**
	 * JSON数组转LIST
	 * @param jsonArray 源数据
	 * @param clazz List 中存储的bean对象
	 * @return
	 */
	public static <T> List<T> jsonArrayToList(JSONArray jsonArray, Class<T> clazz) {
		// 结果集
		List<T> result = new ArrayList<>();
		// 如果入参为空返回空结果
		if (jsonArray==null) {
			return result;
		}

		// 循环转换
		int size = jsonArray.size();
		for (int i=0; i<size; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			T t = BeanUtil.toBean(jsonObject, clazz);
			result.add(t);
		}
		return result;
	}
}
