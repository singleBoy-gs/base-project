package com.base.example.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Single Minded
 * @create 2022-08-07 18:54:07
 * @Description
 * @since 1.0
 */
@Mapper
public interface ExampleMapper {

	/**
	 * 数据库查询
	 * @param id
	 * @return
	 */
	JSONObject datasourceQuery(@Param("id") Integer id);

	/**
	 * 数据库查询
	 * @return
	 */
	List<JSONObject> datasourceQueryList();
}
