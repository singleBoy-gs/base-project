package com.base.common.controller;

import com.base.common.model.dto.GetLocationByAddressDTO;
import com.base.common.model.vo.GetLocationByAddressVO;
import com.base.common.response.R;
import com.base.common.service.intf.CommonService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author Single Minded
 * @create 2022-08-07 17:12:16
 * @Description 公共 controller
 * @since 1.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/common")
@Api(value = "公共", tags = {"公共"})
public class CommonController {

	@Resource
	CommonService commonService;

	@PostMapping("/getLocationByAddress")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "通过地址信息获取经纬度（高德 地理编码API服务）", notes = "通过地址信息获取经纬度（高德 地理编码API服务）")
	public R<GetLocationByAddressVO> getLocationByAddress(@RequestBody @Valid GetLocationByAddressDTO dto){
		return commonService.getLocationByAddress(dto);
	}

	@PostMapping("/saveDayOffDate")
	@ApiOperationSupport(order = 2)
	@ApiImplicitParam(name = "year",value = "年份",example = "2022",paramType = "query",required = true)
	@ApiOperation(value = "保存节假日（保存完成后按照国务院节日通知修改day_off_type字段）", notes = "保存节假日（保存完成后按照国务院节日通知修改day_off_type字段）")
	public R<GetLocationByAddressVO> saveDayOffDate(@RequestBody @NotBlank(message = "年份不能为空") String year){
		return commonService.saveDayOffDate(year);
	}
}