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
  COMMENT '密码'
)
  COMMENT '用户表';

TRUNCATE TABLE db_javaee.user;

SELECT *
FROM db_javaee.user;

DROP TABLE IF EXISTS db_javaee.student;
CREATE TABLE db_javaee.student (
  id     INT AUTO_INCREMENT PRIMARY KEY
  COMMENT 'ID PK',
  name   VARCHAR(255) NOT NULL
  COMMENT '姓名',
  gender VARCHAR(255) NOT NULL
  COMMENT '性别',
  dob    DATE         NOT NULL
  COMMENT'出生日期'
)COMMENT '学生表';

SELECT *
FROM db_javaee.student;

TRUNCATE TABLE db_javaee.student;

SELECT *
FROM db_1712.ip;

SELECT *
FROM db_1712.ip
WHERE inet_aton('111.222.111.222') BETWEEN inet_aton(min) AND inet_aton(max);


