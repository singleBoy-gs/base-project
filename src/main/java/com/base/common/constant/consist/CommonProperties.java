package com.base.common.constant.consist;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Single Minded
 * @create 2022-08-14 09:43:43
 * @Description 公共常量类
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "common")
@PropertySource("classpath:properties/application-common.properties")
public class CommonProperties {

    /**
     * 是否开启日志存储(默认不开启)
     */
    public static Boolean logDbFlag;
    public void setLogDbFlag(Boolean logDbFlag) {
        if (logDbFlag == null) {
            CommonProperties.logDbFlag = Boolean.FALSE;
        }else {
            CommonProperties.logDbFlag = logDbFlag;
        }
    }

    /**
     * key（高德）
     */
    public static String amapKey;
    public void setAmapKey(String amapKey) {
        CommonProperties.amapKey = amapKey;
    }

    /**
     * 通过地址获取经纬度URL（高德）
     */
    public static String urlAmapGeo;
    public void setUrlAmapGeo(String urlAmapGeo) {
        CommonProperties.urlAmapGeo = urlAmapGeo;
    }

    /**
     * 获取哪些城市的坐标，比如：130203（唐山）
     */
    public static String cityCode;
    public void setCityCode(String cityCode) {
        CommonProperties.cityCode = cityCode;
    }

    /**
     * 通过地址获取坐标时的地址前缀
     */
    public static String preAddress;
    public void setPreAddress(String preAddress) {
        CommonProperties.preAddress = preAddress;
    }
}
