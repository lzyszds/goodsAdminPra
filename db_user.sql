/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50738 (5.7.38-log)
 Source Host           : localhost:3306
 Source Schema         : db_user

 Target Server Type    : MySQL
 Target Server Version : 50738 (5.7.38-log)
 File Encoding         : 65001

 Date: 14/05/2023 22:17:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods_list
-- ----------------------------
DROP TABLE IF EXISTS `goods_list`;
CREATE TABLE `goods_list`  (
  `g_id` int(11) NOT NULL AUTO_INCREMENT,
  `g_titleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_price` double NULL DEFAULT NULL,
  `g_number` int(11) NULL DEFAULT NULL,
  `g_goodsType` int(11) NULL DEFAULT NULL,
  `g_status` int(11) NULL DEFAULT NULL,
  `g_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_createTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`g_id`) USING BTREE,
  UNIQUE INDEX `g_titleName`(`g_titleName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_list
-- ----------------------------
INSERT INTO `goods_list` VALUES (13, '烤羊排', 88, 100, 1, 1, 'http://localhost:8083/uploadFile/442615256.png', '2023-05-14 21:09:56');
INSERT INTO `goods_list` VALUES (16, '一龙九吃', 9999, 100, 1, 1, 'http://localhost:8083/uploadFile/445665607.png', '2023-05-14 22:00:46');
INSERT INTO `goods_list` VALUES (17, '灵芝仙珍', 888, 100, 1, 1, 'http://localhost:8083/uploadFile/445698408.png', '2023-05-14 22:01:19');
INSERT INTO `goods_list` VALUES (19, '炸饕餮', 998, 100, 1, 1, 'http://localhost:8083/uploadFile/446037029.png', '2023-05-14 22:06:57');
INSERT INTO `goods_list` VALUES (20, '麒麟炖汤', 998, 100, 1, 1, 'http://localhost:8083/uploadFile/446122498.png', '2023-05-14 22:08:24');
INSERT INTO `goods_list` VALUES (21, '狻猊血羹', 998, 100, 1, 1, 'http://localhost:8083/uploadFile/446310465.png', '2023-05-14 22:12:29');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0 COMMENT '用户状态(0：正常   1：禁用   2：审核中)',
  `power` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `Email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '123', 0, 0, '2023-05-09 13:41:22', '1024327189@gmail.com');
INSERT INTO `tb_user` VALUES (6, 'lzy', '123', 0, 0, '2023-05-09 05:50:05', 'a1024327189@gmail.com');
INSERT INTO `tb_user` VALUES (8, 'lzyszds', '123', 0, 1, '2023-05-09 06:26:04', 'a1024327189@gmail.com');
INSERT INTO `tb_user` VALUES (20, 'item1', '123', 0, 1, '2023-05-09 13:38:52', '123@qq.com');
INSERT INTO `tb_user` VALUES (22, 'item3', '123', 0, 1, '2023-05-09 13:40:10', '123@qq.com');
INSERT INTO `tb_user` VALUES (23, 'item4', '123', 0, 1, '2023-05-09 13:40:12', '123@qq.com');
INSERT INTO `tb_user` VALUES (24, 'item5', '123', 0, 1, '2023-05-09 13:40:14', '123@qq.com');
INSERT INTO `tb_user` VALUES (25, 'item6', '123', 0, 1, '2023-05-09 13:40:16', '123@qq.com');
INSERT INTO `tb_user` VALUES (26, 'item7', '123', 0, 1, '2023-05-09 13:40:18', '123@qq.com');
INSERT INTO `tb_user` VALUES (27, 'item8', '123', 0, 1, '2023-05-09 13:40:19', '123@qq.com');
INSERT INTO `tb_user` VALUES (28, 'item9', '123', 0, 1, '2023-05-09 13:40:21', '123@qq.com');
INSERT INTO `tb_user` VALUES (29, 'item10', '123', 0, 1, '2023-05-09 13:40:24', '123@qq.com');
INSERT INTO `tb_user` VALUES (30, 'user1', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (32, 'user2', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (33, 'user3', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (34, 'user4', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (35, 'user5', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (36, 'user6', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (37, 'user7', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (38, 'user8', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (39, 'user9', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (40, 'user10', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (41, 'child1', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (42, 'child2', '123', 0, 1, '2023-05-09 13:41:22', '123@qq.com');
INSERT INTO `tb_user` VALUES (47, '123456', '1234', 0, 1, '2023-05-10 00:04:22', '10242@qq.com');
INSERT INTO `tb_user` VALUES (48, 'lzyszds1', '123', 0, 1, '2023-05-10 00:10:15', 'a1024327189@gmail.com');
INSERT INTO `tb_user` VALUES (49, 'list', '123', 0, 1, '2023-05-10 00:11:10', '1233@qq.com');
INSERT INTO `tb_user` VALUES (50, 'qwer', 'qwer', 0, 1, '2023-05-10 00:12:12', 'qwer@qq.com');
INSERT INTO `tb_user` VALUES (51, 'lzy123', '123', 0, 1, '2023-05-10 00:21:53', 'item@qq.com');
INSERT INTO `tb_user` VALUES (52, 'lzyaaa', '123', 0, 1, '2023-05-10 00:50:37', '123@qq.com');
INSERT INTO `tb_user` VALUES (53, 'asdfa', '123', 0, 1, '2023-05-10 00:51:11', 'asd@qq');

SET FOREIGN_KEY_CHECKS = 1;
