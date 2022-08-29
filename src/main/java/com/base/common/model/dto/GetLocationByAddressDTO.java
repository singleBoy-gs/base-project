package com.base.common.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Single Minded
 * @create 2022-08-25 16:28:14
 * @Description 通过地址信息获取经纬度（高德 地理编码API服务）DTO
 * @since 1.0
 */
@Data
@Builder
public class GetLocationByAddressDTO {

	@ApiModelProperty(value = "地址信息")
	@NotBlank(message = "地址信息不能为空")
	private String address;

	@ApiModelProperty(value = "指定查询的城市；" +
			"可选输入内容包括：指定城市的中文（如北京）、" +
			"指定城市的中文全拼（beijing）、" +
			"citycode（010）、" +
			"adcode（110000），不支持县级市。当指定城市查询内容为空时，会进行全国范围内的地址转换检索。")
	private String city;

	@ApiModelProperty(value = "高德KEY")
	private String key;
}
