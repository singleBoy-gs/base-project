package com.base.common.model.dto;

import lombok.Setter;

/**
 * @author Single Minded
 * @create 2022-08-14 17:38:42
 * @Description
 * @since 1.0
 */
@Setter
public class PageDTO {
	private Integer pageNum;
	private Integer pageSize;

	public int getPageNum() {
		if (pageNum==null) return 1;
		return pageNum;
	}
	public int getPageSize() {
		if (pageSize==null) return 10;
		return pageSize;
	}
}
