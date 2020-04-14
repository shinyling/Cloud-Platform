drop table if exists `user_group_user`;
CREATE TABLE `user_group_user`
(
    `group_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_id`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`group_id`, `user_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

drop table if exists `user_group_permission`;
CREATE TABLE `user_group_permission`
(
    `group_id`      varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `permission_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`group_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;