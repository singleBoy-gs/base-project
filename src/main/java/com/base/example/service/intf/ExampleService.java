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

	String datasourceQuery(String cameraIndexCode);

	R datasourceQueryList(PageDTO pageDTO);
}
