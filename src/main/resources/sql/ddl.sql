create database example;
use example;

CREATE TABLE IF NOT EXISTS user
(
    id            BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    create_time   TIMESTAMP                                  NOT NULL COMMENT '创建时间',
    modified_time TIMESTAMP                                  NULL COMMENT '修改时间',
    username      VARCHAR(64)      DEFAULT ''                NOT NULL COMMENT '用户名',
    password      VARCHAR(64)      DEFAULT ''                NOT NULL COMMENT '密码',
    nickname      VARCHAR(64)      DEFAULT ''                NOT NULL COMMENT '昵称',
    email         VARCHAR(64)      DEFAULT ''                NOT NULL COMMENT '邮箱',
    phone         VARCHAR(20)      DEFAULT ''                NOT NULL COMMENT '手机号码',
    type          TINYINT UNSIGNED DEFAULT '0'               NOT NULL COMMENT '用户类型',
    status        TINYINT UNSIGNED DEFAULT '1'               NOT NULL COMMENT '用户状态 1:ENABLED, 2:DISABLED',
    salt          VARCHAR(40)                                NOT NULL COMMENT '盐'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE utf8mb4_unicode_ci
    COMMENT 'user';
CREATE UNIQUE INDEX `uk_user_username` ON `user` (`username`);
CREATE UNIQUE INDEX `uk_user_nickname` ON `user` (`nickname`);
