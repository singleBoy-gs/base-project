/*
 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 127.0.0.1:3306
 Source Schema         : （自行创建数据库）

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 2022-08-18 12:46:53
*/

-- ----------------------------
-- Table structure for log_interface
-- ----------------------------
DROP TABLE IF EXISTS `log_interface`;
CREATE TABLE `log_interface`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求地址',
  `header` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求头',
  `param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求结果',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '请求时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '请求接口日志表' ROW_FORMAT = DYNAMIC;
