package com.base.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.base.common.model.dto.PageDTO;
import com.base.common.response.R;
import com.base.common.utils.HttpUtils;
import com.base.example.mapper.ExampleMapper;
import com.base.example.service.intf.ExampleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Single Minded
 * @create 2022-08-07 18:53:32
 * @Description
 * @since 1.0
 */
@Slf4j
@Service
public class ExampleServiceImpl implements ExampleService {

	@Resource
	ExampleMapper exampleMapper;

	@Override
	public R datasourceQuery(Integer id) {

		HttpUtils.getData("http://127.0.0.1:8999/example/singleParamGet?name=张三");

		JSONObject result = exampleMapper.datasourceQuery(id);
		if (result==null) {
			result = new JSONObject();
		}
		return R.success(result);
	}

	@Override
	public R datasourceQueryList(PageDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
		List<JSONObject> cameraList = exampleMapper.datasourceQueryList();
		PageInfo pageInfo = new PageInfo(cameraList);
		return R.success(pageInfo);
	}
}
