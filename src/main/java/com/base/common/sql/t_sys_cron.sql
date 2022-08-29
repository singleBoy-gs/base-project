-- ----------------------------
-- CRON表达式表
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_cron`;
CREATE TABLE `t_sys_cron`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `cron` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'cron表达式',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'CRON表达式表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_cron
-- ----------------------------
INSERT INTO `t_sys_cron` VALUES (1, '*/5 * * * * ?', '第一个CRON表达式（没5秒执行1次）');
INSERT INTO `t_sys_cron` VALUES (2, '0 0 13 * * ?', '第二个CRON表达式（每天13点执行一次）');