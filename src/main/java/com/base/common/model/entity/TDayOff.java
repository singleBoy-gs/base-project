package com.base.common.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * 节假日表(TDayOff)实体类
 *
 * @author makejava
 * @since 2022-08-30 00:20:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TDayOff implements Serializable {
    private static final long serialVersionUID = -76334664451676279L;

    @ApiModelProperty(value = "唯一标识")
    private Long id;

    @ApiModelProperty(value = "时间")
    private String dayOffDate;

    @ApiModelProperty(value = "时间类型（0-工作日；1-周六、周日；2-元旦；3-春节；4-清明节；5-劳动节；6-端午节；7-中秋节；8国庆节）")
    private Integer dayOffType;

    @ApiModelProperty(value = "是否无效：0-有效；1-无效")
    private Integer dataIsInvalid;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    private String updateUser;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}

