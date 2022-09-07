package com.base.common.utils;

import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelUtil;
import com.base.common.handler.ExcelRowHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * @author Single Minded
 * @create 2022-09-06 12:41:47
 * @Description excel工具类
 * @since 1.0
 */
@Slf4j
public class ExcelUtils extends ExcelUtil{

	/**
	 * @param pathAndName        文件路径
	 * @param idOrRidOrSheetName Excel中的sheet id或者rid编号或sheet名称，rid必须加rId前缀，例如rId1，如果为-1处理所有编号的sheet
	 * @param startDataRowNum 数据开始行号，从0开始。
	 */
	public static List<List<Object>> readBigExcel(String pathAndName, String idOrRidOrSheetName, int startDataRowNum) {
		Console.log("【{}】 开始读取 ... ", pathAndName);
		ExcelRowHandler excelRowHandler = new ExcelRowHandler(startDataRowNum);
		readBySax(new File(pathAndName), idOrRidOrSheetName, excelRowHandler);
		Console.log("【{}】 读取完成 ... ", pathAndName);
		return excelRowHandler.getAllDataList();
	}

	/**
	 * @param pathAndName        文件路径
	 * @param idOrRidOrSheetName Excel中的sheet id或者rid编号或sheet名称，rid必须加rId前缀，例如rId1，如果为-1处理所有编号的sheet
	 * @param startDataRowNum 数据开始行号，从0开始。
	 */
	public static List<List<Object>> readBigExcel(String pathAndName, int idOrRidOrSheetName, int startDataRowNum) {
		Console.log("【{}】 开始读取 ... ", pathAndName);
		ExcelRowHandler excelRowHandler = new ExcelRowHandler(startDataRowNum);
		readBySax(new File(pathAndName), idOrRidOrSheetName, excelRowHandler);
		Console.log("【{}】 读取完成 ... ", pathAndName);
		return excelRowHandler.getAllDataList();
	}

	public static void main(String[] args) {
		String path = "C:\\Users\\lenovo\\Downloads\\重点人员.xlsx";
		List<List<Object>> allDataList = readBigExcel(path, "重点人群信息表", 3);
		Console.log(allDataList.size());
	}
}