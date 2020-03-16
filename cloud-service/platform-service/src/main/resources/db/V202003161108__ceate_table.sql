/*
 Navicat Premium Data Transfer

 Source Server         : vm
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 10.1.5.68:3306
 Source Schema         : ucenter

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 16/03/2020 11:14:40
*/

SET NAMES utf8mb4;
-- ----------------------------
-- Table structure for ClientDetails
-- ----------------------------
DROP TABLE IF EXISTS `ClientDetails`;
CREATE TABLE `ClientDetails`
(
    `appId`                  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `resourceIds`            varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `appSecret`              varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `scope`                  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `grantTypes`             varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `redirectUrl`            varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `authorities`            varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `access_token_validity`  int(11)                                                  NULL DEFAULT NULL,
    `refresh_token_validity` int(11)                                                  NULL DEFAULT NULL,
    `additionalInformation`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `autoApproveScopes`      varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    PRIMARY KEY (`appId`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`
(
    `company_id`       varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `jdbc_url`         varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `jdbc_username`    varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `jdbc_password`    varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `company_name`     varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '企业全称',
    `company_location` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '企业省市区',
    `state`            varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `company_address`  varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '企业详细地址',
    `create_by`        varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `create_time`      datetime(0)                                                   NULL DEFAULT NULL,
    `update_by`        varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `update_time`      datetime(0)                                                   NULL DEFAULT NULL,
    PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for element
-- ----------------------------
DROP TABLE IF EXISTS `element`;
CREATE TABLE `element`
(
    `id`   varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`
(
    `id`          varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `name`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `url`         varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `size`        int(11)                                                 NULL     DEFAULT NULL,
    `create_time` timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by`   varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`
(
    `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`
(
    `id`   varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `url`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `pid`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `icon` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`
(
    `token_id`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `token`             blob                                                    NULL,
    `authentication_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_name`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `client_id`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `authentication`    blob                                                    NULL,
    `refresh_token`     varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals`
(
    `userId`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `clientId`       varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `scope`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `status`         varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `expiresAt`      timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    `lastModifiedAt` timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `resource_ids`            varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `client_secret`           varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `scope`                   varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `authorized_grant_types`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `authorities`             varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `access_token_validity`   int(11)                                                  NULL DEFAULT NULL,
    `refresh_token_validity`  int(11)                                                  NULL DEFAULT NULL,
    `additional_information`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `autoapprove`             varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`
(
    `token_id`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `token`             blob                                                    NULL,
    `authentication_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_name`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `client_id`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`
(
    `code`           varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `authentication` blob                                                    NULL
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`
(
    `token_id`       varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `token`          blob                                                    NULL,
    `authentication` blob                                                    NULL
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation`
(
    `id`         varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name`       varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `code`       varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `url_prefix` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`             varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name`           varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `type`           tinyint(1)                                             NOT NULL,
    `permission_val` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `uri`            varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `icon`           varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `create_time`    timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission_element
-- ----------------------------
DROP TABLE IF EXISTS `permission_element`;
CREATE TABLE `permission_element`
(
    `permission_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `element_id`    varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`permission_id`, `element_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission_file
-- ----------------------------
DROP TABLE IF EXISTS `permission_file`;
CREATE TABLE `permission_file`
(
    `permission_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `file_id`       varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`permission_id`, `file_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `permission_menu`;
CREATE TABLE `permission_menu`
(
    `permission_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `menu_id`       varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`permission_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission_operation
-- ----------------------------
DROP TABLE IF EXISTS `permission_operation`;
CREATE TABLE `permission_operation`
(
    `permission_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `operation_id`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`
(
    `username`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `series`    varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `token`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `last_used` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `en_code`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `pid`         varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级id',
    `create_by`   varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time` timestamp(0)                                           NULL DEFAULT NULL,
    `update_by`   varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `update_time` timestamp(0)                                           NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `role_id`       varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `permission_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`                      varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `username`                varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `mobile`                  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `password`                varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `enabled`                 tinyint(1)                                              NULL     DEFAULT 1,
    `account_non_locked`      tinyint(1)                                              NULL     DEFAULT 1,
    `account_non_expired`     tinyint(1)                                              NULL     DEFAULT 1,
    `create_time`             timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by`               varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `update_time`             timestamp(0)                                            NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    `update_by`               varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `credentials_no_expired`  tinyint(1)                                              NULL     DEFAULT 1,
    `company_id`              varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '公司id',
    `type`                    tinyint(1)                                              NULL     DEFAULT 0 COMMENT '账户类型',
    `credentials_non_expired` bit(1)                                                  NULL     DEFAULT b'1',
    `is_delete`               tinyint(1)                                              NULL     DEFAULT 0,
    `is_lock`                 tinyint(1)                                              NULL     DEFAULT 0,
    `name`                    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `status`                  tinyint(1)                                              NULL     DEFAULT 1,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group`
(
    `id`          varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `pid`         varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_by`   varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time` timestamp(0)                                           NULL DEFAULT NULL,
    `update_by`   varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `update_time` timestamp(0)                                           NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_group_role
-- ----------------------------
DROP TABLE IF EXISTS `user_group_role`;
CREATE TABLE `user_group_role`
(
    `group_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `role_id`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`group_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;