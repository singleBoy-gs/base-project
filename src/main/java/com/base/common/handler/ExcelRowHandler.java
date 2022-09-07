package com.base.common.handler;

import cn.hutool.poi.excel.sax.handler.RowHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Single Minded
 * @create 2022-09-06 13:17:27
 * @Description
 * @since 1.0
 */
@Slf4j
public class ExcelRowHandler implements RowHandler {

	private int startDataRowNum;
	private static final List<List<Object>> allDataList = new ArrayList<>();
	/**
	 * 构造函数
	 * @param startDataRowNum 数据开始行号，从0开始。
	 */
	public ExcelRowHandler(int startDataRowNum) {
		this.startDataRowNum = startDataRowNum;
		allDataList.clear();
	}

	@Override
	public void handle(int sheetIndex, long rowIndex, List<Object> rowList) {
		log.info("[{}] [{}] {}", sheetIndex, rowIndex, rowList);
		if (rowIndex < startDataRowNum) {
			return;
		} else {
			allDataList.add(rowList);
		}
	}

	public List<List<Object>> getAllDataList() {
		return allDataList;
	}
}
