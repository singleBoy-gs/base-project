package com.base.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.base.common.model.dto.PageDTO;
import com.base.common.response.R;
import com.base.common.utils.HttpUtils;
import com.base.demo.mapper.DemoMapper;
import com.base.demo.service.intf.DemoService;
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
public class DemoServiceImpl implements DemoService {

	@Resource
	DemoMapper demoMapper;

	@Override
	public R datasourceQuery(Integer id) {

		HttpUtils.getData("http://127.0.0.1:6666/demo/singleParamGet?name=张三");

		JSONObject result = demoMapper.datasourceQuery(id);
		if (result==null) {
			result = new JSONObject();
		}
		return R.success(result);
	}

	@Override
	public R datasourceQueryList(PageDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
		List<JSONObject> cameraList = demoMapper.datasourceQueryList();
		PageInfo pageInfo = new PageInfo(cameraList);
		return R.success(pageInfo);
	}

	@Override
	public R task() {
		log.info("定时任务执行了");
		return R.success();
	}
}
