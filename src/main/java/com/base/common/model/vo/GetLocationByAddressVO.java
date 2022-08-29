package com.base.common.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Single Minded
 * @create 2022-08-26 09:24:29
 * @Description
 * @since 1.0
 */
@Data
public class GetLocationByAddressVO {

	@ApiModelProperty(value = "经度")
	private Double lon;

	@ApiModelProperty(value = "纬度")
	private Double lat;

	@ApiModelProperty(value = "城市编码")
	private String city;
}
