/*
 Navicat MySQL Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 29/01/2021 13:39:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `first_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `views` int(0) NULL DEFAULT NULL,
  `type_id` bigint(0) NULL DEFAULT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK292449gwg5yf7ocdlmswv9w4j`(`type_id`) USING BTREE,
  INDEX `FK8ky5rrsxh01nkhctmo7d48p82`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES (22, b'1', b'1', '# 这是一级标题\r\n**下面是一些代码**\r\n```java\r\n@PostMapping(\"\")\r\n    public String addBlog(Blog blog, HttpSession session, RedirectAttributes attributes) {\r\n        blog.setUser((User) session.getAttribute(\"user\"));\r\n        blog.setType(typeService.getType(blog.getType().getId()));\r\n        blog.setTags(tagService.listTag(blog.getTagIds()));\r\n\r\n        Blog blog1 = blogService.saveBlog(blog);\r\n        if (blog1 == null) {\r\n            //新增失败\r\n            attributes.addFlashAttribute(\"message\", \"新增失败\");\r\n        }else {\r\n            attributes.addFlashAttribute(\"message\", \"新增成功\");\r\n        }\r\n        return REDIRECT_LIST;\r\n    }\r\n```\r\n\r\n~~删除线~~', '2021-01-12 13:23:42.000000', '第一次尝试', 'https://picsum.photos/800/450', '转载', b'0', b'1', b'1', '第一篇文章', '2021-01-26 12:34:11.173000', 18, 7, 1);
INSERT INTO `t_blog` VALUES (24, b'0', b'0', '订单', '2020-11-12 13:23:48.000000', '2', 'https://picsum.photos/800/450', '转载', b'0', b'0', b'0', '小白文字', '2021-01-26 12:34:19.577000', 9, 3, 1);
INSERT INTO `t_blog` VALUES (26, b'1', b'1', '*面向对象编程的优越性在于可扩展*\r\n下面展示一下其优越性\r\n```html\r\n<head th:replace=\"_fragments :: head(~{::title})\">\r\n  <meta charset=\"UTF-8\">\r\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n  <title>博客详情</title>\r\n  <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css\">\r\n  <link rel=\"stylesheet\" href=\"../static/css/typo.css\">\r\n  <link rel=\"stylesheet\" href=\"../static/css/animate.css\">\r\n  <link rel=\"stylesheet\" href=\"../static/lib/prism/prism.css\">\r\n  <link rel=\"stylesheet\" href=\"../static/lib/tocbot/tocbot.css\">\r\n  <link rel=\"stylesheet\" href=\"../static/css/me.css\">\r\n</head>\r\n```\r\n\r\n**下面展示java代码**\r\n\r\n```java\r\n@Override\r\n    public Blog getAndConvert(Long id) {\r\n        Blog blog = blogRepository.findById(id).get();\r\n\r\n        if (blog.getId() == null) {\r\n            throw new NotFindException(\"博客不存在\");\r\n        }\r\n        Blog b = new Blog();\r\n        BeanUtils.copyProperties(blog, b);\r\n\r\n        b.setContent(MarkdownUtils.markdownToHtmlExtensions(b.getContent()));\r\n        return b;\r\n    }\r\n```\r\n\r\n## 一级标题\r\n- 1\r\n- 2\r\n1. 我们是好朋友\r\n1. 没有艰难了\r\n', '2020-12-10 04:57:48.000000', '韩看的文章', 'https://picsum.photos/800/450', '', b'1', b'1', b'1', '浅谈java', '2021-01-26 13:29:37.389000', 68, 3, 1);
INSERT INTO `t_blog` VALUES (40, b'0', b'1', '**111**\r\n#### 多对多\r\n231', '2021-01-07 05:11:04.456000', '无', 'https://picsum.photos/800/450', '', b'1', b'1', b'1', '对层次', '2021-01-07 13:34:44.000000', 3, 3, 1);
INSERT INTO `t_blog` VALUES (41, b'0', b'0', '111', '2021-01-26 12:59:43.009000', '111', 'https://picsum.photos/800/450', '', b'0', b'0', b'0', '111', '2021-01-26 12:59:43.009000', 1, 3, 1);
INSERT INTO `t_blog` VALUES (42, b'0', b'0', '11', '2021-01-26 13:26:20.201000', 'tagService.insertBlogTag(blog.getId(), blog.getTagIds());', 'https://picsum.photos/800/450', '', b'1', b'0', b'0', 'tagService.insertBlogTag(blog.getId(), blog.getTagIds());', '2021-01-26 13:28:22.935000', 1, 4, 1);
INSERT INTO `t_blog` VALUES (43, b'1', b'1', '11', '2021-01-26 13:34:30.846000', '2222', 'https://picsum.photos/800/450', '', b'1', b'1', b'1', '2222', '2021-01-27 08:04:25.791000', 1, 3, 1);
INSERT INTO `t_blog` VALUES (44, b'1', b'1', '111', '2021-01-27 08:04:49.135000', '333', 'http://note.youdao.com/yws/public/resource/4f4b075a8e744494678d1b5fa2d45bc3/xmlnote/WEBRESOURCE669642dde29656cb00ae41feadfa8538/20', '', b'1', b'1', b'1', '333', '2021-01-27 08:04:49.135000', 5, 5, 1);

-- ----------------------------
-- Table structure for t_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags`  (
  `blogs_id` bigint(0) NOT NULL,
  `tags_id` bigint(0) NOT NULL,
  INDEX `FK5feau0gb4lq47fdb03uboswm8`(`tags_id`) USING BTREE,
  INDEX `FKh4pacwjwofrugxa9hpwaxg6mr`(`blogs_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
INSERT INTO `t_blog_tags` VALUES (22, 19);
INSERT INTO `t_blog_tags` VALUES (24, 19);
INSERT INTO `t_blog_tags` VALUES (26, 19);
INSERT INTO `t_blog_tags` VALUES (40, 19);
INSERT INTO `t_blog_tags` VALUES (41, 19);
INSERT INTO `t_blog_tags` VALUES (42, 19);
INSERT INTO `t_blog_tags` VALUES (43, 19);
INSERT INTO `t_blog_tags` VALUES (44, 19);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `admin_comment` bit(1) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `blog_id` bigint(0) NULL DEFAULT NULL,
  `parent_comment_id` bigint(0) NULL DEFAULT NULL,
  `top` int(0) NULL DEFAULT 0 COMMENT '是否置顶',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKke3uogd04j4jx316m1p51e05u`(`blog_id`) USING BTREE,
  INDEX `FK4jj284r3pb7japogvo6h72q95`(`parent_comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (30, b'0', '/images/avatar.png', '首次评论', '2021-01-06 08:22:16.152000', '1914873077@qq.com', 'aaa', 26, -1, 1);
INSERT INTO `t_comment` VALUES (31, b'0', '/images/avatar.png', '回复aaa的评论', '2021-01-06 08:25:37.662000', '1914873077@qq.com', 'bbb', 26, 30, 0);
INSERT INTO `t_comment` VALUES (32, b'0', '/images/avatar.png', '收到', '2021-01-06 08:25:58.228000', '1914873077@qq.com', 'aaa', 26, 31, 0);
INSERT INTO `t_comment` VALUES (33, b'0', '/images/avatar.png', '1', '2021-01-06 08:41:21.947000', '1914873077@qq.com', 'aaa', 26, 31, 0);
INSERT INTO `t_comment` VALUES (34, b'0', '/images/avatar.png', '第二个评论', '2021-01-06 08:48:12.397000', '1914873077@qq.com', 'xxx', 26, -1, 0);
INSERT INTO `t_comment` VALUES (35, b'0', '/images/avatar.png', '收到', '2021-01-06 08:48:20.739000', '1914873077@qq.com', 'aaa', 26, 34, 0);
INSERT INTO `t_comment` VALUES (36, b'1', 'https://unsplash.it/100/100?image=1005', 'hahah', '2021-01-06 08:58:21.475000', '1914873077@qq.com', 'xs', 26, -1, 0);
INSERT INTO `t_comment` VALUES (37, b'1', 'https://unsplash.it/100/100?image=1005', 'aaa', '2021-01-06 08:58:31.868000', '1914873077@qq.com', 'xsa', 26, -1, 0);
INSERT INTO `t_comment` VALUES (38, b'1', 'https://unsplash.it/100/100?image=1005', '来自管理员的回复', '2021-01-06 09:03:56.076000', '1914873077@qq.com', 'xs', 26, 30, 0);
INSERT INTO `t_comment` VALUES (39, b'1', 'https://unsplash.it/100/100?image=1005', '来自管理员的回复', '2021-01-06 09:04:19.405000', '1914873077@qq.com', 'xs', 26, 34, 0);
INSERT INTO `t_comment` VALUES (41, b'0', '/images/avatar.png', '111', '2021-01-28 09:20:07.217000', '1914873077@qq.com', '111', 26, -1, 0);
INSERT INTO `t_comment` VALUES (45, b'1', 'https://unsplash.it/100/100?image=1005', '5555', '2021-01-28 09:51:23.285000', '1914873077@qq.com', 'xs', 26, -1, 0);
INSERT INTO `t_comment` VALUES (46, b'1', 'https://unsplash.it/100/100?image=1005', '555555', '2021-01-28 09:51:34.128000', '1914873077@qq.com', 'xs', 26, -1, 1);
INSERT INTO `t_comment` VALUES (47, b'0', '/images/avatar.png', '231', '2021-01-28 09:56:12.302000', '1914873077@qq.com', '111', 26, -1, 0);
INSERT INTO `t_comment` VALUES (48, b'0', '/images/avatar.png', '123', '2021-01-28 09:56:20.671000', '1914873077@qq.com', '111', 26, 47, 0);
INSERT INTO `t_comment` VALUES (50, b'0', '/images/avatar.png', '111', '2021-01-28 11:48:54.498000', '1914873077@qq.com', 'dasda', 22, -1, 0);
INSERT INTO `t_comment` VALUES (51, b'0', '/images/avatar.png', 'dadad', '2021-01-28 11:49:00.079000', '1914873077@qq.com', 'dasda', 22, 50, 0);
INSERT INTO `t_comment` VALUES (52, b'1', 'https://unsplash.it/100/100?image=1005', 'dddd', '2021-01-29 02:36:31.553000', '1914873077@qq.com', 'xs', 26, 36, 0);

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (19, '后端222');
INSERT INTO `t_tag` VALUES (20, '前端');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (1, 'aaaa');
INSERT INTO `t_type` VALUES (2, 'adc');
INSERT INTO `t_type` VALUES (3, 'ccc');
INSERT INTO `t_type` VALUES (4, 'c++');
INSERT INTO `t_type` VALUES (5, 'css');
INSERT INTO `t_type` VALUES (7, 'spring');
INSERT INTO `t_type` VALUES (8, 'springmvc');
INSERT INTO `t_type` VALUES (9, 'springboot');
INSERT INTO `t_type` VALUES (10, 'ddd');
INSERT INTO `t_type` VALUES (11, 'ddddd');
INSERT INTO `t_type` VALUES (12, 'ddddddd');
INSERT INTO `t_type` VALUES (13, 'dddd');
INSERT INTO `t_type` VALUES (14, 'dadad');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'https://unsplash.it/100/100?image=1005', '2021-01-01 15:38:20.000000', '1914873077@qq.com', 'xs', '698d51a19d8a121ce581499d7b701668', 1, '2021-01-02 15:39:11.000000', 'xs');

SET FOREIGN_KEY_CHECKS = 1;
