package com.base.common.service.intf;

import com.base.common.model.dto.GetLocationByAddressDTO;
import com.base.common.model.vo.GetLocationByAddressVO;
import com.base.common.response.R;

/**
 * @author Single Minded
 * @create 2022-08-07 18:52:51
 * @Description
 * @since 1.0
 */
public interface CommonService {

	/**
	 * 存储请求接口日志
	 * @param url    请求地址
	 * @param header 请求头
	 * @param param  请求参数
	 * @param result 请求结果
	 */
	void saveLog(String url, String header, String param, String result);

	/**
	 * 通过地址信息获取经纬度（高德 地理编码API服务）
	 * @param dto
	 * @return
	 */
	R<GetLocationByAddressVO> getLocationByAddress(GetLocationByAddressDTO dto);

	/**
	 * 保存节假日（保存完成后按照国务院节日通知修改day_off_type字段）
	 * @param year
	 * @return
	 */
	R<GetLocationByAddressVO> saveDayOffDate(String year);
}
