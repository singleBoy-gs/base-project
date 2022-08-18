package com.base.example.controller;

import com.base.common.model.dto.PageDTO;
import com.base.common.response.R;
import com.base.example.model.dto.ExampleMultipleParamPostDTO;
import com.base.example.service.intf.ExampleService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Single Minded
 * @create 2022-08-07 17:12:16
 * @Description
 * @since 1.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/example")
@Api(value = "示例", tags = {"示例"})
public class ExampleController {

	@Resource
	ExampleService exampleService;

	@GetMapping("/singleParamGet")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "单参数GET请求", notes = "单参数GET请求")
	@ApiImplicitParam(name = "name",value = "名称",example = "张三",paramType = "query",required = true)
	public R singleParamGet(@NotBlank(message = "名称不能为空") String name){
		return R.success(name);
	}

	@GetMapping("/multipleParamGet")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "多参数GET请求", notes = "多参数GET请求")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code",value = "编码",example = "010101",paramType = "query",required = true),
		@ApiImplicitParam(name = "name",value = "名称",example = "张三",paramType = "query",required = true)
	})
	public R multipleParamGet(@NotBlank(message = "编码不能为空") @RequestParam("code") String code,
							  @NotBlank(message = "名称不能为空") @RequestParam("name") String name){
		return R.success("code：" + code + "；name：" + name);
	}

	@PostMapping("/singleParamPost")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "单参数POST请求", notes = "单参数POST请求")
	@ApiImplicitParam(name = "name",value = "名称",example = "张三",paramType = "query",required = true)
	public R singleParamPost(@RequestBody @NotBlank(message = "名称不能为空") String name){
		return R.success(name);
	}

	@PostMapping("/multipleParamPost")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "多参数POST请求", notes = "多参数POST请求")
	public R multipleParamPost(@RequestBody @Valid ExampleMultipleParamPostDTO exampleMultipleParamPostDTO){
		return R.success("code：" + exampleMultipleParamPostDTO.getCode() + "；name：" + exampleMultipleParamPostDTO.getName());
	}

	@GetMapping("/datasourceQuery")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "数据库查询", notes = "数据库查询")
	@ApiImplicitParam(name = "id",value = "唯一标识",example = "1",paramType = "query",required = true)
	public R datasourceQuery(@NotNull(message = "唯一标识不能为空") Integer id){
		return exampleService.datasourceQuery(id);
	}

	@GetMapping("/datasourceQueryList")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "数据库查询", notes = "数据库查询")
	public R datasourceQueryList(PageDTO pageDTO){
		return exampleService.datasourceQueryList(pageDTO);
	}
}