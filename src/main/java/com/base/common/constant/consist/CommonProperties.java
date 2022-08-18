package com.base.common.constant.consist;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Single Minded
 * @create 2022-08-14 09:43:43
 * @Description 海康公共常量类
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
}
