/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50737 (5.7.37)
 Source Host           : localhost:3306
 Source Schema         : invest

 Target Server Type    : MySQL
 Target Server Version : 50737 (5.7.37)
 File Encoding         : 65001

 Date: 02/03/2023 09:53:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for invest_account
-- ----------------------------
DROP TABLE IF EXISTS `invest_account`;
CREATE TABLE `invest_account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '且慢平台id',
  `platform_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '平台',
  `account_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '投资账户名称',
  `portfolio_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '投资组合',
  `total_assert` decimal(10, 2) NULL DEFAULT NULL COMMENT '持仓金额',
  `holding_profit` decimal(10, 2) NULL DEFAULT NULL COMMENT '持仓收益',
  `holding_cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '持仓成本',
  `cust_part` decimal(10, 4) NULL DEFAULT NULL COMMENT '持有份数',
  `plan_part` decimal(10, 0) NULL DEFAULT NULL COMMENT '计划份数',
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `platform`(`platform_name`, `account_name`, `portfolio_name`) USING BTREE,
  UNIQUE INDEX `account_code`(`account_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '投资基金详情' ROW_FORMAT =
Dynamic;

-- ----------------------------
-- Records of invest_account
-- ----------------------------
INSERT INTO `invest_account` VALUES (1, 'CA7DNLNVK0T85T', '且慢', '赚大钱', 'E大-150', 67739.58, 5507.58, 62232.00, 63.0341, 122, '2023-02-21 10:58:03', '2023-02-21 11:02:00');
INSERT INTO `invest_account` VALUES (2, 'CAB0ZTIOZLMJR8', '且慢', '小金库', 'E大-S', 11437.22, 1140.26, 10296.96, 20.5940, 123, '2023-01-29 09:22:05', '2023-02-21 11:30:58');
INSERT INTO `invest_account` VALUES (3, 'CABFJVIY9AELJJ', '且慢', '小金库', 'E大-150', 8617.53, 624.03, 7993.50, 26.6443, 122, '2023-02-21 10:58:03', '2023-02-21 10:58:44');
INSERT INTO `invest_account` VALUES (4, 'CAC058VJ0SL7ET', '且慢', '黄金坑', 'E大-150', 11723.96, 626.00, 11097.96, 21.9960, 122, '2023-02-21 10:27:04', '2023-02-21 10:27:04');

SET FOREIGN_KEY_CHECKS = 1;
