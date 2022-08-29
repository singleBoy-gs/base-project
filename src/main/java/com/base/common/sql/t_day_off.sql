-- ----------------------------
-- 节假日表
-- ----------------------------
DROP TABLE IF EXISTS `t_day_off`;
CREATE TABLE `t_day_off`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `day_off_date` date NULL DEFAULT NULL COMMENT '休息日时间',
  `day_off_type` int NULL DEFAULT 0 COMMENT '休息日类型（0-工作日；1-周六、周日；2-元旦；3-春节；4-清明节；5-劳动节；6-端午节；7-中秋节；8国庆节）',
  `data_is_invalid` int NULL DEFAULT 0 COMMENT '是否无效：0-有效；1-无效',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '节假日表' ROW_FORMAT = Dynamic;
