DROP DATABASE IF EXISTS db_javaee;
CREATE DATABASE db_javaee;

DROP TABLE IF EXISTS db_javaee.user;
CREATE TABLE db_javaee.user (
  id       INT AUTO_INCREMENT PRIMARY KEY
  COMMENT 'ID PK',
  nick     VARCHAR(255) NOT NULL UNIQUE
  COMMENT '昵称',
  mobile    VARCHAR(255) NOT NULL UNIQUE
  COMMENT '手机',
  password VARCHAR(255) NOT NULL
  COMMENT '密码',
  bobbies VARCHAR(255) COMMENT '爱好',
  cities VARCHAR(255) COMMENT '城市'
)
  COMMENT '用户表';

TRUNCATE TABLE db_javaee.user;

SELECT *
FROM db_javaee.user;