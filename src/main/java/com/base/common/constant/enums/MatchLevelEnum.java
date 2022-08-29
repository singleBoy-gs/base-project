package com.base.common.constant.enums;

import lombok.Getter;

/**
 * @author Single Minded
 * @create 2022-08-25 10:02:47
 * @Description 地理编码匹配级别
 * @since 1.0
 */
@Getter
public enum MatchLevelEnum {
    UNKNOWN(1, "未知"),
    COUNTRY(2, "国家"),
    PROVINCE(3, "省"),
    CITY(4, "市"),
    DISTRICT(5, "区县"),
    AREA(6, "开发区"),
    STREET(7, "乡镇"),
    VILLAGE(8, "村庄"),
    TRADING_AREA(9, "热点商圈"),
    INTEREST_POINT(10, "兴趣点"),
    HOUSE_NUMBER(11, "门牌号"),
    UNIT_NUMBER(12, "单元号"),
    ROAD(8, "道路"),
    ROAD_CROSSROADS(10, "道路交叉路口"),
    STATION(11, "公交站台、地铁站"),
    ;


    private int level;
    private String name;
    MatchLevelEnum(int level, String name) {
        this.level = level;
        this.name = name;
    }

    /**
     * 通过值获取对象
     * @param name 值
     * @return
     */
    public static MatchLevelEnum of(String name) {
        MatchLevelEnum matchLevelEnum = null;
        for (MatchLevelEnum one : MatchLevelEnum.values()) {
            if (one.getName().equals(name)) {
                matchLevelEnum = one;
                break;
            }
        }
        return matchLevelEnum;
    }
}
