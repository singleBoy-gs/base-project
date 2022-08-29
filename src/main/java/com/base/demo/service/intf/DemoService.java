package com.base.demo.service.intf;

import com.base.common.model.dto.PageDTO;
import com.base.common.response.R;

/**
 * @author Single Minded
 * @create 2022-08-07 18:52:51
 * @Description
 * @since 1.0
 */
public interface DemoService {

	/**
	 * 数据库查询
	 * @param id
	 * @return
	 */
	R datasourceQuery(Integer id);

	/**
	 * 数据库查询
	 * @param pageDTO
	 * @return
	 */
	R datasourceQueryList(PageDTO pageDTO);

	/**
	 * 定时任务执行内容
	 * @return
	 */
	R task();
}
