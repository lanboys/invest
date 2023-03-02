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

 Date: 02/03/2023 09:54:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for invest_account_fund_detail
-- ----------------------------
DROP TABLE IF EXISTS `invest_account_fund_detail`;
CREATE TABLE `invest_account_fund_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL COMMENT '账户id',
  `clean_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否清仓：0 否 1 是',
  `platform_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台(冗余)',
  `account_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '投资账户(冗余)',
  `portfolio_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '投资组合(冗余)',
  `fund_id` int(11) NOT NULL COMMENT '基金id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '基金简称(冗余)',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '基金代码(冗余)',
  `sector` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '行业(冗余)',
  `total_assert` decimal(10, 2) NULL DEFAULT NULL COMMENT '持仓金额',
  `holding_profit` decimal(10, 2) NULL DEFAULT NULL COMMENT '持仓收益',
  `holding_cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '持仓成本',
  `cust_part` decimal(10, 4) NULL DEFAULT NULL COMMENT '持有份数',
  `plan_part` decimal(10, 0) NULL DEFAULT NULL COMMENT '计划份数',
  `total_share` decimal(10, 2) NULL DEFAULT NULL COMMENT '持有份额',
  `cust_unit_value` decimal(10, 4) NULL DEFAULT NULL COMMENT '持有单价',
  `fund_nav` decimal(10, 4) NULL DEFAULT NULL COMMENT '基金净值(冗余)',
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`, `account_id`) USING BTREE,
  UNIQUE INDEX `fund_id`(`fund_id`, `account_id`) USING BTREE,
  INDEX `account_id`(`account_id`) USING BTREE,
  CONSTRAINT `invest_account_fund_detail_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `invest_account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `invest_account_fund_detail_ibfk_2` FOREIGN KEY (`fund_id`) REFERENCES `invest_fund` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '投资基金详情' ROW_FORMAT =
Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
