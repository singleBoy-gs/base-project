package com.base.example.service.intf;

import com.base.common.model.dto.PageDTO;
import com.base.common.response.R;

/**
 * @author Single Minded
 * @create 2022-08-07 18:52:51
 * @Description
 * @since 1.0
 */
public interface ExampleService {

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
}
