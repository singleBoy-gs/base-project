package com.base.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (HkCamera)实体类
 *
 * @author makejava
 * @since 2022-08-14 17:47:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HkCamera implements Serializable {

    private static final long serialVersionUID = 242964784894502281L;
    /**
     * 海康监控点
     */
    private Integer id;
    /**
     * 监控点编号（通用唯一识别码UUID）
     */
    private String cameraindexcode;
    /**
     * 监控点国标编号
     */
    private String gbindexcode;
    /**
     * 监控点名称
     */
    private String name;
    /**
     * 所属设备编号（通用唯一识别码UUID）
     */
    private String deviceindexcode;
    /**
     * 经度（WGS84坐标系）
     */
    private String longitude;
    /**
     * 纬度（WGS84坐标系）
     */
    private String latitude;
    /**
     * 海拔高度（WGS84坐标系，单位：米）
     */
    private String altitude;
    /**
     * 摄像机像素（1-普通像素，2-130万高清，3-200万高清，4-300万高清，取值参考数据字典，typeCode为xresmgr.piexl）
     */
    private Integer pixel;
    /**
     * 监控点类型（0-枪机,1-半球,2-快球,3-带云台枪机,取值参考数据字典，typeCode为xresmgr.camera_type）
     */
    private Integer cameratype;
    /**
     * 监控点类型说明
     */
    private String cameratypename;
    /**
     * 安装位置
     */
    private String installplace;
    /**
     * 矩阵编号
     */
    private String matrixcode;
    /**
     * 通道号
     */
    private Integer channum;
    /**
     * 可视域相关（JSON格式），该字段具体使用方式参考#DOC@监控点可视域字段说明@#。
     */
    private String viewshed;
    /**
     * 能力集（详见数据字典，typeCode为xresmgr.capability_set）
     */
    private String capabilityset;
    /**
     * 能力集说明
     */
    private String capabilitysetname;
    /**
     * 智能分析能力集（详见数据字典，typeCode为xresmgr.intelligent_set）
     */
    private String intelligentset;
    /**
     * 智能分析能力集说明
     */
    private String intelligentsetname;
    /**
     * 录像存储位置（0-中心存储，1-设备存储，取值参考数据字典，typeCode为xresmgr.record_location）
     */
    private String recordlocation;
    /**
     * 录像存储位置说明
     */
    private String recordlocationname;
    /**
     * 云台控制（1-DVR,2-模拟矩阵,3-MU4000,4-NC600，取值参考数据字典，typeCode为xresmgr.ptz_control_type）
     */
    private String ptzcontroller;
    /**
     * 云台控制说明
     */
    private String ptzcontrollername;
    /**
     * 所属设备类型（详见数据字典，typeCode为xresmgr.resource_type）
     */
    private String deviceresourcetype;
    /**
     * 所属设备类型说明
     */
    private String deviceresourcetypename;
    /**
     * 通道子类型（详见数据字典，typeCode为xresmgr.device_type_code.camera）
     */
    private String channeltype;
    /**
     * 通道子类型说明
     */
    private String channeltypename;
    /**
     * 传输协议（0-UDP，1-TCP，取值参考数据字典，typeCode为xresmgr.transType）
     */
    private String transtype;
    /**
     * 传输协议类型说明
     */
    private String transtypename;
    /**
     * 监控点更新时间（ISO8601格式yyyy-MM-dd’T’HH:mm:ss.SSSXXX）
     */
    private String updatetime;
    /**
     * 所属组织编号（通用唯一识别码UUID）
     */
    private String unitindexcode;
    /**
     * 接入协议（详见数据字典，typeCode为xresmgr.protocol_type）
     */
    private String treatytype;
    /**
     * 接入协议类型说明
     */
    private String treatytypename;
    /**
     * 监控点创建时间（ISO8601格式yyyy-MM-dd’T’HH:mm:ss.SSSXXX）
     */
    private String createtime;
    /**
     * 在线状态（0-不在线，1-在线，取值参考数据字典，typeCode为xresmgr.status）
     */
    private Integer status;
    /**
     * 状态说明
     */
    private String statusname;
    /**
     * 同步时间
     */
    private Date synctime;
    
    private String exp1;
    
    private String exp2;
    
    private String exp3;
    
    private String exp4;
    
    private String exp5;
    /**
     * 摄像机来源（1-自建，2-雪亮）
     */
    private Integer camerasource;
}

