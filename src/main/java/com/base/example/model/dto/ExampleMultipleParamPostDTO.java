package com.base.example.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Single Minded
 * @create 2022-08-14 13:02:10
 * @Description
 * @since 1.0
 */
@Data
public class ExampleMultipleParamPostDTO {

	@ApiModelProperty(value = "时间", required = true)
	@NotBlank(message = "编码不能为空")
	private String code;

	@ApiModelProperty(value = "采集供气量", required = true)
	@NotBlank(message = "名称不能为空")
	private String name;
}
