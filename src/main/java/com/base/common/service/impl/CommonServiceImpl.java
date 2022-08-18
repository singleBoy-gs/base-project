package com.base.common.service.impl;

import com.base.common.constant.consist.CommonProperties;
import com.base.common.mapper.CommonMapper;
import com.base.common.service.intf.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Single Minded
 * @create 2022-08-07 18:53:32
 * @Description
 * @since 1.0
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

	@Resource
	CommonMapper commonMapper;

	@Override
	public void saveLog(String url, String header, String param, String result) {
		if (CommonProperties.logDbFlag) {
			commonMapper.saveLog(url, header, param, result);
		}
	}
}
