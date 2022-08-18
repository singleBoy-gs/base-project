package com.base.common.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;

/**
 * @author Single Minded
 * @create 2022-08-14 17:38:42
 * @Description
 * @since 1.0
 */
@Setter
public class PageDTO {

	@ApiModelProperty(value = "页码")
	private Integer pageNum;

	@ApiModelProperty(value = "每页显示数量(默认20)")
	private Integer pageSize;

	public int getPageNum() {
		if (pageNum==null) return 1;
		return pageNum;
	}
	public int getPageSize() {
		if (pageSize==null) return 20;
		return pageSize;
	}
}
