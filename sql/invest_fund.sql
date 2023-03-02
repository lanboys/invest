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

 Date: 02/03/2023 09:54:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for invest_fund
-- ----------------------------
DROP TABLE IF EXISTS `invest_fund`;
CREATE TABLE `invest_fund`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sector` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '行业',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '基金简称',
  `full_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '基金全称',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '基金代码',
  `nav` decimal(10, 4) NULL DEFAULT NULL COMMENT '资产净值 net asset value',
  `nav_date` date NULL DEFAULT NULL COMMENT '净值日期',
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '投资基金详情' ROW_FORMAT =
Dynamic;

-- ----------------------------
-- Records of invest_fund
-- ----------------------------
INSERT INTO `invest_fund` VALUES (1, '医药', '全指医药', '广发医药卫生联接A', '001180', 1.0239, '2023-02-28', '2023-01-29 09:22:05', '2023-03-01 14:45:53');
INSERT INTO `invest_fund` VALUES (2, '养老', '养老产业', '广发养老指数A', '000968', 0.9903, '2023-02-28', '2023-01-29 09:22:05', '2023-03-01 14:45:53');
INSERT INTO `invest_fund` VALUES (3, '信息', '信息技术', '广发信息技术联接A', '000942', 1.0609, '2023-02-28', '2023-01-29 09:22:05', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (5, '传媒', '中证传媒', '广发中证传媒ETF联接A', '004752', 0.6814, '2023-02-28', '2023-01-29 09:22:05', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (6, '证券', '证券公司', '易方达中证全指证券公司指数(LOF)A', '502010', 1.0756, '2023-02-28', '2023-01-29 09:22:05', '2023-03-01 14:45:53');
INSERT INTO `invest_fund` VALUES (7, '传媒', '文体娱乐', '汇添富文体娱乐混合A', '004424', 2.1304, '2023-02-22', '2023-01-29 09:22:05', '2023-02-23 14:17:26');
INSERT INTO `invest_fund` VALUES (8, '医药', '大摩健康产业', '大摩健康产业混合A', '002708', 2.4950, '2023-02-20', '2023-01-29 09:22:05', '2023-03-01 15:23:42');
INSERT INTO `invest_fund` VALUES (9, '证券', '证券保险', '天弘中证证券保险A', '001552', 0.8608, '2023-02-22', '2023-01-29 09:22:05', '2023-02-23 14:17:26');
INSERT INTO `invest_fund` VALUES (10, '消费', '易方达消费行业', '易方达消费行业', '110022', 4.3150, '2023-02-20', '2023-01-29 09:22:05', '2023-03-01 15:23:46');
INSERT INTO `invest_fund` VALUES (11, '创业板', '广发创业板', '广发创业板ETF联接A', '003765', 1.3069, '2023-02-28', '2023-01-29 09:22:05', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (12, '消费', '富国消费', '富国消费主题混合A', '519915', 2.8920, '2023-02-20', '2023-01-29 09:22:05', '2023-03-01 15:23:43');
INSERT INTO `invest_fund` VALUES (13, '沪深300', '沪深300', '华夏沪深300ETF联接A', '000051', 1.4512, '2023-02-20', '2023-01-29 09:22:05', '2023-03-01 15:23:45');
INSERT INTO `invest_fund` VALUES (14, '恒生', '恒生指数', '华夏恒生ETF联接A', '000071', 1.1834, '2023-02-20', '2023-01-29 09:22:05', '2023-03-01 15:23:38');
INSERT INTO `invest_fund` VALUES (15, '互联网', '恒生科技', '天弘恒生科技指数(QDII)A', '012348', 0.6130, '2023-02-20', '2023-01-29 09:22:05', '2023-03-01 15:23:39');
INSERT INTO `invest_fund` VALUES (16, '医疗', '恒生医疗', '博时恒生医疗保健ETF发起式联接(QDII)A', '014424', 0.9210, '2023-02-28', '2023-01-29 09:22:05', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (17, '纳指', '纳指100', '广发纳指100ETF联接人民币(QDII)A', '270042', 3.6723, '2023-02-17', '2023-01-29 09:22:05', '2023-03-01 15:23:47');
INSERT INTO `invest_fund` VALUES (18, '标普', '标普500', '博时标普500ETF联接A', '050025', 3.1235, '2023-02-17', '2023-01-29 09:22:05', '2023-03-01 15:23:48');
INSERT INTO `invest_fund` VALUES (19, '国债', '7-10国开债', '广发中债7-10年国开债指数A', '003376', 1.2478, '2023-02-28', '2023-01-29 09:22:05', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (20, '医药', '纳斯达克生物科技', '广发生物科技指数(QDII)A', '001092', 1.0780, '2023-02-27', '2023-02-10 18:07:08', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (29, '互联网', '中国海外互联', '交银中证海外中国互联网指数', '164906', 1.0200, '2023-02-17', '2023-02-21 10:28:24', '2023-03-01 15:23:37');
INSERT INTO `invest_fund` VALUES (30, '互联网', '易方达中概互联', '易方达中证海外联接人民币A', '006327', 0.8516, '2023-02-17', '2023-02-21 10:28:29', '2023-03-01 15:23:40');
INSERT INTO `invest_fund` VALUES (31, '金融', '全指金融', '广发中证全指金融地产联接A', '001469', 1.0005, '2023-02-20', '2023-02-21 10:28:29', '2023-03-01 15:23:41');
INSERT INTO `invest_fund` VALUES (32, '信息', '易方达信息产业', '易方达信息产业混合', '001513', 2.4890, '2023-02-28', '2023-02-21 10:59:32', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (33, '中证500', '中证500C类', '广发中证500ETF联接C', '002903', 1.1500, '2023-02-28', '2023-02-21 10:59:32', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (34, '医疗', '融通健康产业', '融通健康产业灵活配置混合A', '000727', 3.5830, '2023-02-22', '2023-02-21 10:59:32', '2023-02-23 14:25:34');
INSERT INTO `invest_fund` VALUES (35, '中证500', '建信500', '建信中证500指数增强A', '000478', 2.8566, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:53');
INSERT INTO `invest_fund` VALUES (36, '红利', '中证红利', '富国中证红利指数增强A', '100032', 0.9620, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:53');
INSERT INTO `invest_fund` VALUES (37, '中证500', '中证500', '华夏中证500ETF联接A', '001052', 0.7344, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:53');
INSERT INTO `invest_fund` VALUES (38, '沪深300', '富国300', '富国沪深300指数增强A', '100038', 1.5410, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:53');
INSERT INTO `invest_fund` VALUES (39, '上证50', '上证50', '华夏上证50ETF联接A', '001051', 0.9347, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (40, '中证500', '富国500', '富国中证500指数增强A', '161017', 2.3260, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (41, '环保', '中证环保', '广发中证环保ETF联接A', '001064', 1.0099, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (42, '创业板', '易方达创业板', '易方达创业板ETF联接A', '110026', 2.4597, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (43, '德国', '德国DAX', '华安德国(DAX)ETF联接A', '000614', 1.2920, '2023-02-27', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (44, '黄金', '黄金', '华安黄金易ETF联接A', '000216', 1.4826, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (45, '石油', '石油基金', '华安标普石油指数A', '160416', 1.4840, '2023-02-27', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (46, '石油', '华宝油气', '华宝标普石油指数A', '162411', 0.7196, '2023-02-27', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (47, '债券', '海外收益债', '华夏海外收益债券A', '001061', 1.3290, '2023-02-27', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (48, '债券', '广发纯债', '广发纯债债券A', '270048', 1.2118, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (49, '债券', '兴全转债', '兴全可转债混合', '340001', 1.0927, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (50, '债券', '长信可转债', '长信可转债债券A', '519977', 1.7055, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (51, '债券', '易方达安心回报债', '易方达安心债券A', '110027', 1.8934, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (52, '债券', '南方通利纯债', '南方通利A', '000563', 1.0923, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');
INSERT INTO `invest_fund` VALUES (53, '债券', '易方达高等级信用债', '易方达高等级信用债A', '000147', 1.0893, '2023-02-28', '2023-02-21 11:02:18', '2023-03-01 14:45:54');

SET FOREIGN_KEY_CHECKS = 1;
