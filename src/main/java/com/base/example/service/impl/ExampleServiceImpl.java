package com.base.example.service.impl;

import cn.hutool.core.util.StrUtil;
import com.base.common.model.dto.PageDTO;
import com.base.common.response.R;
import com.base.example.mapper.ExampleMapper;
import com.base.example.model.entity.HkCamera;
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
	public String datasourceQuery(String cameraIndexCode) {

		if (StrUtil.isBlank(cameraIndexCode)) {
			cameraIndexCode = "27bf11d3827b47a2b4a381004d994ad7";
		}
		String test = null;
		try {
			test = exampleMapper.datasourceQuery(cameraIndexCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}

	@Override
	public R datasourceQueryList(PageDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
		List<HkCamera> cameraList = exampleMapper.datasourceQueryList();
		PageInfo pageInfo = new PageInfo(cameraList);
		return R.success(pageInfo);
	}
}
