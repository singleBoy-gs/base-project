package com.base.common.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.common.constant.consist.CommonProperties;
import com.base.common.constant.enums.DayOffEnum;
import com.base.common.constant.enums.MatchLevelEnum;
import com.base.common.constant.enums.ReturnEnums;
import com.base.common.mapper.CommonMapper;
import com.base.common.model.dto.GetLocationByAddressDTO;
import com.base.common.model.entity.TDayOff;
import com.base.common.model.vo.GetLocationByAddressVO;
import com.base.common.response.R;
import com.base.common.service.intf.CommonService;
import com.base.common.utils.CoordinateTransferUtils;
import com.base.common.utils.DateUtils;
import com.base.common.utils.HttpUtils;
import com.base.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Single Minded
 * @create 2022-08-07 18:53:32
 * @Description 公共服务实现类
 * @since 1.0
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

	@Resource
	CommonMapper commonMapper;

	@Override
	public void saveLog(String url, String header, String param, String result) {
		if (CommonProperties.logDbFlag) {
			commonMapper.saveLog(url, header, param, result);
		}
	}

	@Override
	public R<GetLocationByAddressVO> getLocationByAddress(GetLocationByAddressDTO dto) {
		// 入参的城市编码为空则取配置项
		if (StringUtils.isBlank(dto.getCity())) {
			dto.setCity(CommonProperties.cityCode);
		}

		// 入参的key为空则取配置项
		String key = dto.getKey();
		if (StringUtils.isBlank(key)) {
			key = CommonProperties.amapKey;
		}
		// key不能为空
		if (StringUtils.isBlank(key)) {
			return R.error(ReturnEnums.HTTP_KEY_NULL_ERROR);
		}
		// 获取多个key
		List<String> keyList = Arrays.asList(key.split(StringUtils.COMMA));
		// 循环key集合获取坐标
		int keySize = keyList.size();
		GetLocationByAddressVO getLocationByAddressVO = null;
		JSONObject resultJSONObject = null;
		for (int m=0; m<keySize; m++) {
			log.info("开始使用第《{}》个key获取经纬度", (m+1));
			dto.setKey(keyList.get(m));
			resultJSONObject = HttpUtils.getData(CommonProperties.urlAmapGeo, BeanUtil.beanToMap(dto, false, true));
			// 高德接口返回失败
			Integer status = resultJSONObject.getInteger("status");
			if (1 != status) {
				continue;
			}
			// 获取返回接口
			JSONArray geocodesJSONArray = resultJSONObject.getJSONArray("geocodes");
			// 筛选后最终获取到的坐标信息
			JSONObject geocodeJSONObject = null;
			// 筛选后最终获取到的匹配级别
			MatchLevelEnum matchLevelEnum = null;
			// 循环处理
			for (int i = 0; i < geocodesJSONArray.size(); i++) {
				JSONObject jsonObject = geocodesJSONArray.getJSONObject(i);
				// 获取匹配级别
				String level = jsonObject.getString("level");
				// 转换成枚举
				MatchLevelEnum currentMatchLevel = MatchLevelEnum.of(level);
				// 获取匹配级别最高的数据
				if (matchLevelEnum==null || currentMatchLevel.getLevel() > matchLevelEnum.getLevel()) {
					matchLevelEnum = MatchLevelEnum.of(level);
					geocodeJSONObject = jsonObject;
				}
			}

			// 没有获取则继续获取
			if (geocodeJSONObject == null) {
				break;
			}

			// 没有坐标则继续获取
			String location = geocodeJSONObject.getString("location");
			if (StringUtils.isBlank(location)) {
				break;
			}

			// 将坐标转换成wgs84
			String[] locationArray = location.split(StringUtils.COMMA);
			double lon = Double.parseDouble(locationArray[0]);
			double lat = Double.parseDouble(locationArray[1]);
			getLocationByAddressVO = CoordinateTransferUtils.gcj02ToWgs84(lon, lat);
			getLocationByAddressVO.setCity(geocodeJSONObject.getString("adcode"));
			log.info("最终使用第《{}》个key获取到经纬度", (m+1));
			break;
		}

		if (getLocationByAddressVO==null) {
			log.error("key已耗尽，终止查询");
			return R.error(resultJSONObject==null ? "key已耗尽，终止查询" : resultJSONObject.toJSONString());
		}
		// 通过地址获取坐标信息
		return R.success(getLocationByAddressVO);
	}

	@Override
	public R<GetLocationByAddressVO> saveDayOffDate(String year) {
		// 字符串转时间类型
		DateTime yearDate = DateUtils.parse(year, DatePattern.NORM_YEAR_PATTERN);
		// 获取年度开始日期
		DateTime startDate = DateUtils.beginOfYear(yearDate);
		// 获取年度结束日期
		DateTime endDate = DateUtils.endOfYear(yearDate);
		// 初始化结果集
		List<TDayOff> dataList = new ArrayList<>();
		do {
			TDayOff tDayOff = new TDayOff();
			tDayOff.setDayOffDate(DateUtils.formatDate(startDate));
			// 判断是否为周末
			if (DateUtils.isWeekend(startDate)) {
				tDayOff.setDayOffType(DayOffEnum.WEEKEND.getCode());
			} else {
				tDayOff.setDayOffType(DayOffEnum.WORKDAY.getCode());
			}
			dataList.add(tDayOff);
			startDate = startDate.offset(DateField.DAY_OF_YEAR, 1);
		} while (startDate.compareTo(endDate) < 0);

		// 保存数据
		commonMapper.saveDayOffDate(dataList);
		return R.success();
	}
}