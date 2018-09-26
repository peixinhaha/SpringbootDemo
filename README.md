### 样板清单

- [x] 框架，Spring Boot 2.0.4
- [x] 编辑器，IntelliJ IDEA
- [x] 编码风格，阿里巴巴
- [x] 命名风格，阿里巴巴
- [x] API URL 风格，Restful
- [ ] 静态检查，阿里巴巴 P3C
- [x] 分层，Controller，Manager，Service，Mapper（Repository）
- [x] ORM，MyBaits
- [x] 分页插件，PageHelper，pagehelper-spring-boot-starter
- [x] 验证
- [x] 错误处理
- [x] 日志处理
- [x] 统一输出
- [ ] 文档管理
- [x] 事务处理
- [ ] 中间件
- [ ] 数据库迁移

参考：
- [IntelliJ IDEA 2018.2（Ultimate Edition）激活方法](https://blog.csdn.net/m0_38110132/article/details/81383573)
- [IntelliJ IDEA 内存优化最佳实践](http://blog.oneapm.com/apm-tech/426.html)
- [Jet Brains家族破解方法](https://www.jianshu.com/p/f404994e2843)

### Docker MySQL
本地启动：  
```
docker run -p 3306:3306 --name mysql5.6 -v /Volumes/Work/project-java/bp-data/mysql56:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6
```
建表：
```
/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.6
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost:3306
 Source Schema         : spring-boot-bp

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 17/08/2018 11:01:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `username` varchar(30) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '用户账号',
 `password` varchar(100) COLLATE utf8mb4_german2_ci NOT NULL,
 `token` varchar(100) COLLATE utf8mb4_german2_ci NOT NULL,
 `salt` char(4) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '盐',
 `gmt_disabled` datetime NOT NULL COMMENT '禁用时间',
 PRIMARY KEY (`id`),
 KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for arrangement
-- ----------------------------
DROP TABLE IF EXISTS `arrangement`;
CREATE TABLE `arrangement` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `used_total` int(11) unsigned NOT NULL COMMENT '使用总量',
 `year` int(4) unsigned NOT NULL COMMENT '年份',
 `month` varchar(2) NOT NULL COMMENT '月份',
 `gmt_disabled` datetime NOT NULL COMMENT '禁用时间',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='整理表';

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `content` text COLLATE utf8mb4_german2_ci NOT NULL COMMENT '内容',
 `introduction` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '简介',
 `title` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '标题',
 `type_name` varchar(50) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '类别名称',
 `type_id` int(11) NOT NULL COMMENT '类别id',
 `year` tinyint(4) unsigned NOT NULL COMMENT '年份',
 `month` tinyint(2) unsigned NOT NULL COMMENT '月份',
 `gmt_disabled` datetime NOT NULL COMMENT '禁用时间',
 PRIMARY KEY (`id`),
 KEY `idx_type_id` (`type_id`),
 KEY `idx_year` (`year`),
 KEY `idx_month` (`month`),
 KEY `idx_year_month` (`year`,`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='文章表';

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `article_id` int(11) unsigned NOT NULL COMMENT '文章id',
 `tag_id` int(11) unsigned NOT NULL COMMENT '标签id',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='文章与标签关联表';

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `head_portrait` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '头像',
 `title` varchar(50) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '首页标题',
 `banner` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '首页banner',
 `gmt_disabled` datetime NOT NULL COMMENT '禁用时间',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='配置表';

-- ----------------------------
-- Table structure for frandly_link
-- ----------------------------
DROP TABLE IF EXISTS `frandly_link`;
CREATE TABLE `frandly_link` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `name` varchar(50) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '名称',
 `link` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '链接',
 `gmt_disabled` datetime NOT NULL COMMENT '禁用时间',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='友情链接表';

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `content` text COLLATE utf8mb4_german2_ci NOT NULL COMMENT '公告内容',
 `gmt_disabled` datetime NOT NULL COMMENT '禁用时间',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='公告表';

-- ----------------------------
-- Table structure for sociality
-- ----------------------------
DROP TABLE IF EXISTS `sociality`;
CREATE TABLE `sociality` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `thumbnail` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '缩略图',
 `name` varchar(30) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '名称',
 `link` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '链接',
 `gmt_disabled` datetime NOT NULL COMMENT '禁用时间',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='社交表';

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `name` varchar(30) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '标签名',
 `gmt_disabled` datetime NOT NULL COMMENT '禁用时间',
 PRIMARY KEY (`id`),
 KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='标签表';

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `name` varchar(30) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '分类名',
 `used_total` int(11) unsigned NOT NULL COMMENT '使用总量',
 `gmt_disabled` datetime NOT NULL COMMENT '禁用时间',
 PRIMARY KEY (`id`),
 KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='分类表';

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Insert data
-- username: admin
-- password: 123456
-- 密码加密规则 sha1(sha1(password) + salt)
-- ----------------------------

set time_zone = '+8:00';

INSERT INTO admin (gmt_create, gmt_modified, username, password, salt, token, gmt_disabled) VALUES (now(), now(), 'admin', '2da276d67be3642b09f721e429a36126535636f0', '1234', '', '1970-01-01 01:01:01');

-- ----------------------------
-- Insert data
-- name: 前端
-- used_total: 0
-- ----------------------------
INSERT INTO type (gmt_create, gmt_modified, name, used_total, gmt_disabled) VALUES (now(), now(), '前端', 0, '1970-01-01 00:00:00'),(now(), now(), '后端', 0, '1970-01-01 00:00:00'),(now(), now(), '工具', 0, '1970-01-01 00:00:00');
```
