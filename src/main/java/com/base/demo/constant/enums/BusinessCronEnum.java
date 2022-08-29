package com.base.demo.constant.enums;

import lombok.Getter;

/**
 * @author Single Minded
 * @create 2022-06-02 10:02:47
 * @Description CRON表达式
 * @since 1.0
 */
@Getter
public enum BusinessCronEnum {
    NETWORK_DECLARE(1, "第一个CRON表达式"),
    DOMESTIC_FOCUS(2, "第二个CRON表达式");

    // 数据库的ID值
    private int value;
    private String name;
    BusinessCronEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
