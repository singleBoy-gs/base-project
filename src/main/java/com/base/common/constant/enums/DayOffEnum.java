package com.base.common.constant.enums;

import lombok.Getter;

/**
 * @author Single Minded
 * @create 2022-08-15 10:02:47
 * @Description 是否有效枚举
 * @since 1.0
 */
@Getter
public enum DayOffEnum {
    WORKDAY(0, "工作日"),
    WEEKEND(1, "周末（周六、周日）");

    private int code;
    private String name;
    DayOffEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 通过值获取名
     * @param code 值
     * @return
     */
    public static String of(int code) {
        String name = null;
        for (DayOffEnum one : DayOffEnum.values()) {
            if (one.getCode() == code) {
                name = one.getName();
                break;
            }
        }
        return name;
    }
}
