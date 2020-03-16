-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company`
VALUES ('1232590503586938882',
        'jdbc:mysql://10.1.5.68:3306/tenant_1232590503343169536?useUnicode=true&serverTimezone=UTC&allowMultiQueries=true',
        'xqvm5b', 'Py4328djg*', '第一个公司', '第一个公司位置', '1', '第一个公司地址', '6423369366422683652', '2020-02-26 08:57:31',
        '6423369366422683652', '2020-02-26 09:11:41');
-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence`
VALUES (4);
-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu`
VALUES ('1', '主页', '#/index', '', 'fa fa-home');
INSERT INTO `menu`
VALUES ('2', '系统工具', '#/', NULL, 'fa fa-briefcase');
INSERT INTO `menu`
VALUES ('3', '缓存管理', '#/system/cache', '2', 'fa fa-cloud');
INSERT INTO `menu`
VALUES ('4', '菜单管理', '#/menu', NULL, 'fa fa-bars');

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details`
VALUES ('client', '', '$2a$10$S46K3ck14f4iNlsVQaeox.5/bJk1huhMvtiFuf1unRl4Z/8TpgE56', 'app',
        'authorization_code,refresh_token,password,client_credentials,implicit', 'https://www.baidu.com', NULL, NULL,
        NULL, NULL, 'true');
-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission`
VALUES ('1', '主页菜单权限', 1, 'menu_index', 'index', 'fa-home', '2019-01-03 19:49:19');

-- ----------------------------
-- Records of permission_menu
-- ----------------------------
INSERT INTO `permission_menu`
VALUES ('1', '1');
INSERT INTO `permission_menu`
VALUES ('1', '2');
INSERT INTO `permission_menu`
VALUES ('1', '3');
INSERT INTO `permission_menu`
VALUES ('1', '4');

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES ('1', 'USER', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `role`
VALUES ('1239369023390564353', '游客', 'guest', '1', '13786132605', '2020-03-16 01:52:56', NULL, NULL);

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission`
VALUES ('1', '1');

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES ('1238365941009600513', '张三', '17688702605', '$2a$10$dqYBaFQVXS49uf7pZf/5kOTQl4IbpS0CHVpAi7vz9UtUr/7mUzQxm', 1,
        1, 1, '2020-03-13 00:27:07', NULL, '2020-03-13 00:47:51', NULL, 1, NULL, 0, b'1', 1, 0, NULL, 1);
INSERT INTO `user`
VALUES ('6423369366422683652', 'admin', '13786132605', '$2a$10$c3/3K.BCg6Wsp9udi9Sqz.a8SWUunG.NPgFVkYvmvU7PxR1CPqL3m',
        1, 1, 1, '2019-05-21 01:30:46', 'sys', '2020-02-27 23:18:48', NULL, 1, '1232590503586938882', 1, b'1', 0, 0, '',
        1);

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`
VALUES ('1238365941009600513', '1');
INSERT INTO `user_role`
VALUES ('6423369366422683652', '1');
