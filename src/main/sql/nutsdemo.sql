/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost
 Source Database       : nutsdemo

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : utf-8

 Date: 05/16/2020 16:28:17 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `cust_api`
-- ----------------------------
DROP TABLE IF EXISTS `cust_api`;
CREATE TABLE `cust_api` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `path` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `gen_sql` varchar(1000) DEFAULT NULL,
  `add_sql` varchar(255) DEFAULT NULL,
  `select_table_info` varchar(1000) DEFAULT NULL,
  `table_joins` varchar(1000) DEFAULT NULL,
  `where_conditions` varchar(1000) DEFAULT NULL,
  `result_columns` varchar(4000) DEFAULT NULL,
  `result_headers` varchar(2000) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `status` bigint(40) DEFAULT NULL,
  `form_html` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `path` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cust_api`
-- ----------------------------
BEGIN;
INSERT INTO `cust_api` VALUES ('3', 'student_save', '插入学生信息接口', '插入学生信息接口', 't_student', null, null, null, '{\"name\":{\"column\":\"name\",\"name\":\"姓名\",\"opt\":\"=\"},\"code\":{\"column\":\"code\",\"name\":\"学号\",\"opt\":\"=\"},\"age\":{\"column\":\"age\",\"name\":\"年龄\",\"opt\":\"=\"},\"sex\":{\"column\":\"sex\",\"name\":\"性别\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"姓名\",\"column_name_alias\":\"name\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"学号\",\"column_name_alias\":\"code\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"age\",\"column_name_cn\":\"年龄\",\"column_name_alias\":\"age\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"sex\",\"column_name_cn\":\"性别\",\"column_name_alias\":\"sex\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"delete_flag\",\"column_name_cn\":\"\",\"column_name_alias\":\"delete_flag\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', null, 'save', '0', null), ('4', 'student_edit', '编辑学生信息接口', '编辑学生信息接口', 't_student', null, null, null, '{\"name\":{\"column\":\"name\",\"name\":\"姓名\",\"opt\":\"=\"},\"code\":{\"column\":\"code\",\"name\":\"学号\",\"opt\":\"=\"},\"age\":{\"column\":\"age\",\"name\":\"年龄\",\"opt\":\"=\"},\"sex\":{\"column\":\"sex\",\"name\":\"性别\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"姓名\",\"column_name_alias\":\"name\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"学号\",\"column_name_alias\":\"code\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"age\",\"column_name_cn\":\"年龄\",\"column_name_alias\":\"age\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"sex\",\"column_name_cn\":\"性别\",\"column_name_alias\":\"sex\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"delete_flag\",\"column_name_cn\":\"\",\"column_name_alias\":\"delete_flag\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', null, 'edit', '0', null), ('6', 'student_delete', '删除学生信息接口', '删除学生信息接口', 't_student', null, null, null, null, '[]', null, 'delete', '0', null), ('7', 'student_list', '查询学生信息接口', '查询学生信息接口', 'select a.id as id,a.student_code as student_code,a.course_code as course_code,a.score as score,a.exam_date as exam_date,b.id as b_id,b.name as name,b.code as code,b.age as age,b.sex as sex,b.delete_flag as delete_flag,c.id as c_id,c.name as c_name,c.code as c_code from t_score as a inner join t_student as b on a.student_code = b.code inner join t_course as c on a.course_code = c.code where a.student_code = @student_code', '', '{\"t_score\":{\"name_cn\":\"comment\",\"alias\":\"a\"},\"t_student\":{\"name_cn\":\"学生表\",\"alias\":\"b\"},\"t_course\":{\"name_cn\":\"comment\",\"alias\":\"c\"}}', '[{\"left_table\":\"t_score\",\"left_column\":\"student_code\",\"join_type\":\"inner join\",\"right_table\":\"t_student\",\"right_column\":\"code\"},{\"left_table\":\"t_score\",\"left_column\":\"course_code\",\"join_type\":\"inner join\",\"right_table\":\"t_course\",\"right_column\":\"code\"}]', '{\"student_code\":{\"column\":\"a.student_code\",\"name\":\"学生编号\",\"opt\":\"=\",\"default_value\":\"1002\"}}', '[{\"status\":true,\"table_name\":\"t_score\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_score\",\"table_alias\":\"a\",\"column_name\":\"student_code\",\"column_name_cn\":\"学生编号\",\"column_name_alias\":\"student_code\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"=\",\"column_test_value\":\"\",\"column_test_value2\":\"\",\"default_value\":\"1002\"},{\"status\":true,\"table_name\":\"t_score\",\"table_alias\":\"a\",\"column_name\":\"course_code\",\"column_name_cn\":\"课程编号\",\"column_name_alias\":\"course_code\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_score\",\"table_alias\":\"a\",\"column_name\":\"score\",\"column_name_cn\":\"成绩\",\"column_name_alias\":\"score\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_score\",\"table_alias\":\"a\",\"column_name\":\"exam_date\",\"column_name_cn\":\"考试日期\",\"column_name_alias\":\"exam_date\",\"column_index\":5,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"b\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"b_id\",\"column_index\":6,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"b\",\"column_name\":\"name\",\"column_name_cn\":\"姓名\",\"column_name_alias\":\"name\",\"column_index\":7,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"b\",\"column_name\":\"code\",\"column_name_cn\":\"学号\",\"column_name_alias\":\"code\",\"column_index\":8,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"b\",\"column_name\":\"age\",\"column_name_cn\":\"年龄\",\"column_name_alias\":\"age\",\"column_index\":9,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"b\",\"column_name\":\"sex\",\"column_name_cn\":\"性别\",\"column_name_alias\":\"sex\",\"column_index\":10,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"b\",\"column_name\":\"delete_flag\",\"column_name_cn\":\"\",\"column_name_alias\":\"delete_flag\",\"column_index\":11,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"c\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"c_id\",\"column_index\":12,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"c\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"c_name\",\"column_index\":13,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"c\",\"column_name\":\"code\",\"column_name_cn\":\"课程编码\",\"column_name_alias\":\"c_code\",\"column_index\":14,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', '[{\"title\":\"编号\",\"data\":\"id\"},{\"title\":\"学生编号\",\"data\":\"student_code\"},{\"title\":\"课程编号\",\"data\":\"course_code\"},{\"title\":\"成绩\",\"data\":\"score\"},{\"title\":\"考试日期\",\"data\":\"exam_date\"},{\"title\":\"编号\",\"data\":\"b_id\"},{\"title\":\"姓名\",\"data\":\"name\"},{\"title\":\"学号\",\"data\":\"code\"},{\"title\":\"年龄\",\"data\":\"age\"},{\"title\":\"性别\",\"data\":\"sex\"},{\"title\":\"delete_flag\",\"data\":\"delete_flag\"},{\"title\":\"编号\",\"data\":\"c_id\"},{\"title\":\"名称\",\"data\":\"c_name\"},{\"title\":\"课程编码\",\"data\":\"c_code\"}]', 'query', '0', null), ('9', 'score_list', '查询课程信息接口', '查询课程信息接口', 'select a.id as a_id,a.student_code as a_student_code,a.course_code as a_course_code,a.score as a_score,a.exam_date as a_exam_date from t_score as a where a.id = @a_id', '', '{\"t_score\":{\"name_cn\":\"comment\",\"alias\":\"a\"}}', '[{\"left_table\":\"t_score\",\"left_column\":\"\",\"join_type\":\"inner join\",\"right_table\":\"\",\"right_column\":\"\"}]', '{\"a_id\":{\"column\":\"a.id\",\"name\":\"编号\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_score\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"a_id\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"=\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_score\",\"table_alias\":\"a\",\"column_name\":\"student_code\",\"column_name_cn\":\"学生编号\",\"column_name_alias\":\"a_student_code\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_score\",\"table_alias\":\"a\",\"column_name\":\"course_code\",\"column_name_cn\":\"课程编号\",\"column_name_alias\":\"a_course_code\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_score\",\"table_alias\":\"a\",\"column_name\":\"score\",\"column_name_cn\":\"成绩\",\"column_name_alias\":\"a_score\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_score\",\"table_alias\":\"a\",\"column_name\":\"exam_date\",\"column_name_cn\":\"考试日期\",\"column_name_alias\":\"a_exam_date\",\"column_index\":5,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', '[{\"title\":\"编号\",\"data\":\"a_id\"},{\"title\":\"学生编号\",\"data\":\"a_student_code\"},{\"title\":\"课程编号\",\"data\":\"a_course_code\"},{\"title\":\"成绩\",\"data\":\"a_score\"},{\"title\":\"考试日期\",\"data\":\"a_exam_date\"}]', 'query', '0', null), ('10', 'courst_list', '查询课程信息接口', '查询课程信息接口', 'select a.id as id,a.name as name,a.code as code from t_course as a where a.name like @name and a.code like @code', ' order by a.id desc', '{\"t_course\":{\"name_cn\":\"comment\",\"alias\":\"a\"}}', '[{\"left_table\":\"t_course\",\"left_column\":\"\",\"join_type\":\"inner join\",\"right_table\":\"\",\"right_column\":\"\"}]', '{\"name\":{\"column\":\"a.name\",\"name\":\"名称\",\"opt\":\"like\"},\"code\":{\"column\":\"a.code\",\"name\":\"课程编码\",\"opt\":\"like\"}}', '[{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":1,\"column_order\":\"desc\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"name\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"like\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"课程编码\",\"column_name_alias\":\"code\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"like\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', '[{\"title\":\"编号\",\"data\":\"id\"},{\"title\":\"名称\",\"data\":\"name\"},{\"title\":\"课程编码\",\"data\":\"code\"}]', 'query', '0', null), ('11', 'course_save', '插入课程信息接口', '插入课程信息接口', 't_course', null, null, null, '{\"name\":{\"column\":\"name\",\"name\":\"名称\",\"opt\":\"=\"},\"code\":{\"column\":\"code\",\"name\":\"课程编码\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"name\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"课程编码\",\"column_name_alias\":\"code\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', null, 'save', '0', null), ('12', 'student_fetch', '查询单个学生信息接口', '查询单个学生信息接口', 'select a.id as id,a.name as name,a.code as code,a.age as age,a.sex as sex from t_student as a where id = @id', '', '{\"t_student\":{\"name_cn\":\"学生表\",\"alias\":\"a\"}}', '[{\"left_table\":\"t_student\",\"left_column\":\"\",\"join_type\":\"inner join\",\"right_table\":\"\",\"right_column\":\"\"}]', '{}', '[{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"a_id\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"姓名\",\"column_name_alias\":\"a_name\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"学号\",\"column_name_alias\":\"a_code\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"age\",\"column_name_cn\":\"年龄\",\"column_name_alias\":\"a_age\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"sex\",\"column_name_cn\":\"性别\",\"column_name_alias\":\"a_sex\",\"column_index\":5,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"delete_flag\",\"column_name_cn\":\"\",\"column_name_alias\":\"a_delete_flag\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', '[{\"title\":\"编号\",\"data\":\"id\"},{\"title\":\"姓名\",\"data\":\"name\"},{\"title\":\"学号\",\"data\":\"code\"},{\"title\":\"年龄\",\"data\":\"age\"},{\"title\":\"性别\",\"data\":\"sex\"}]', 'fetch', '-1', null), ('13', 'student_count_by_sex', '统计男女生人数信息接口', '统计男女生人数信息接口', 'select a.sex as a_sex , count(code) as count_code from t_student as a', ' group by a.sex', '{\"t_student\":{\"name_cn\":\"学生表\",\"alias\":\"a\"}}', '[{\"left_table\":\"t_student\",\"left_column\":\"\",\"join_type\":\"inner join\",\"right_table\":\"\",\"right_column\":\"\"}]', '{}', '[{\"status\":false,\"column_group\":false,\"column_stat\":\"\",\"column_stat_cn\":\"\",\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"a_id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"column_group\":false,\"column_stat\":\"\",\"column_stat_cn\":\"\",\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"姓名\",\"column_name_alias\":\"a_name\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"column_group\":false,\"column_stat\":\"count\",\"column_stat_cn\":\"学号数量\",\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"学号\",\"column_name_alias\":\"a_code\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"column_group\":false,\"column_stat\":\"\",\"column_stat_cn\":\"\",\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"age\",\"column_name_cn\":\"年龄\",\"column_name_alias\":\"a_age\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"column_group\":true,\"column_stat\":\"\",\"column_stat_cn\":\"\",\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"sex\",\"column_name_cn\":\"性别\",\"column_name_alias\":\"a_sex\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"column_group\":false,\"column_stat\":\"\",\"column_stat_cn\":\"\",\"table_name\":\"t_student\",\"table_alias\":\"a\",\"column_name\":\"delete_flag\",\"column_name_cn\":\"\",\"column_name_alias\":\"a_delete_flag\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', '[{\"title\":\"学号数量\",\"data\":\"count_code\"},{\"title\":\"性别\",\"data\":\"a_sex\"}]', 'stat', '0', null), ('14', 'fetchProcess', '查询单个流程信息接口', '查询单个流程信息接口', 'select a.id as id,a.name as name,a.biz_key as biz_key,a.comment as comment from t_process as a where id = @id', '', '{\"t_process\":{\"name_cn\":\"流程表\",\"alias\":\"a\"}}', '[{\"left_table\":\"t_process\",\"left_column\":\"\",\"join_type\":\"\",\"right_table\":\"\",\"right_column\":\"\"}]', '{}', '[{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"a_id\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"a_name\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"biz_key\",\"column_name_cn\":\"biz_key\",\"column_name_alias\":\"a_biz_key\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"comment\",\"column_name_cn\":\"描述\",\"column_name_alias\":\"a_comment\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', '[{\"title\":\"编号\",\"data\":\"id\"},{\"title\":\"名称\",\"data\":\"name\"},{\"title\":\"biz_key\",\"data\":\"biz_key\"},{\"title\":\"描述\",\"data\":\"comment\"}]', 'fetch', '0', null), ('15', 'saveProcess', '插入流程信息接口', '插入流程信息接口', 't_process', null, null, null, '{\"name\":{\"column\":\"name\",\"name\":\"名称\",\"opt\":\"=\"},\"biz_key\":{\"column\":\"biz_key\",\"name\":\"biz_key\",\"opt\":\"=\"},\"comment\":{\"column\":\"comment\",\"name\":\"描述\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"name\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"biz_key\",\"column_name_cn\":\"biz_key\",\"column_name_alias\":\"biz_key\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"comment\",\"column_name_cn\":\"描述\",\"column_name_alias\":\"comment\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', null, 'save', '0', null), ('17', 'deleteProcess', '删除流程信息接口', '删除流程信息接口', 't_process', null, null, null, null, '[]', null, 'delete', '0', null), ('18', 'listProcess', '查询流程信息接口', '查询流程信息接口', 'select a.id as a_id,a.name as a_name,a.biz_key as a_biz_key,a.comment as a_comment from t_process as a', '', '{\"t_process\":{\"name_cn\":\"流程表\",\"alias\":\"a\"}}', '[{\"left_table\":\"t_process\",\"left_column\":\"\",\"join_type\":\"inner join\",\"right_table\":\"\",\"right_column\":\"\"}]', '{}', '[{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"a_id\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"a_name\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"biz_key\",\"column_name_cn\":\"biz_key\",\"column_name_alias\":\"a_biz_key\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_process\",\"table_alias\":\"a\",\"column_name\":\"comment\",\"column_name_cn\":\"描述\",\"column_name_alias\":\"a_comment\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', '[{\"title\":\"编号\",\"data\":\"a_id\"},{\"title\":\"名称\",\"data\":\"a_name\"},{\"title\":\"biz_key\",\"data\":\"a_biz_key\"},{\"title\":\"描述\",\"data\":\"a_comment\"}]', 'query', '0', null), ('19', 'saveLeave', '插入请假信息接口', '插入请假信息接口', 't_leave', null, null, null, '{\"name\":{\"column\":\"name\",\"name\":\"名称\",\"opt\":\"=\"},\"day\":{\"column\":\"day\",\"name\":\"请假天数\",\"opt\":\"=\"},\"reason\":{\"column\":\"reason\",\"name\":\"请假事由\",\"opt\":\"=\"},\"user_id\":{\"column\":\"user_id\",\"name\":\"用户id\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"name\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"day\",\"column_name_cn\":\"请假天数\",\"column_name_alias\":\"day\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"reason\",\"column_name_cn\":\"请假事由\",\"column_name_alias\":\"reason\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"instance_id\",\"column_name_cn\":\"流程实例id\",\"column_name_alias\":\"instance_id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"user_id\",\"column_name_cn\":\"用户id\",\"column_name_alias\":\"user_id\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', null, 'save', '0', '<form role=\"form\" class=\"form-inline\" style=\"margin: 15px 30px;\">\n	<div class=\"col-md-4\">\n		<label style=\"width: 100px; text-align: left; padding-left: 10px;\">名称</label><input type=\"text\" class=\"form-control\" />\n	</div>\n	<div class=\"col-md-4\">\n		<label style=\"width: 100px; text-align: left; padding-left: 10px;\">请假天数</label><input type=\"text\" class=\"form-control\" />\n	</div>\n	<div class=\"col-md-4\">\n		<label style=\"width: 100px; text-align: left; padding-left: 10px;\">请假事由</label><input type=\"text\" class=\"form-control\" />\n	</div>\n	<div class=\"col-md-4\">\n		<label style=\"width: 100px; text-align: left; padding-left: 10px;\">用户id</label><input type=\"text\" class=\"form-control\" />\n	</div>\n</form>'), ('20', 'editLeaveInstanceid', '更新请假流程id接口', '更新请假流程instance_id', 't_leave', null, null, null, '{\"instance_id\":{\"column\":\"instance_id\",\"name\":\"流程实例id\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"name\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"day\",\"column_name_cn\":\"请假天数\",\"column_name_alias\":\"day\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"reason\",\"column_name_cn\":\"请假事由\",\"column_name_alias\":\"reason\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"instance_id\",\"column_name_cn\":\"流程实例id\",\"column_name_alias\":\"instance_id\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"user_id\",\"column_name_cn\":\"用户id\",\"column_name_alias\":\"user_id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', null, 'edit', '0', null), ('21', 'fetchLeave', '查询单个请假信息接口', '查询单个请假信息接口', 'select a.name as name,a.day as day,a.reason as reason,a.instance_id as instance_id from t_leave as a where id = @id', '', '{\"t_leave\":{\"name_cn\":\"请假表\",\"alias\":\"a\"}}', '[{\"left_table\":\"t_leave\",\"left_column\":\"\",\"join_type\":\"\",\"right_table\":\"\",\"right_column\":\"\"}]', '{}', '[{\"status\":false,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"a_id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"a_name\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"day\",\"column_name_cn\":\"请假天数\",\"column_name_alias\":\"a_day\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"reason\",\"column_name_cn\":\"请假事由\",\"column_name_alias\":\"a_reason\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"instance_id\",\"column_name_cn\":\"流程实例id\",\"column_name_alias\":\"a_instance_id\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":false,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"user_id\",\"column_name_cn\":\"用户id\",\"column_name_alias\":\"a_user_id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', '[{\"title\":\"名称\",\"data\":\"name\"},{\"title\":\"请假天数\",\"data\":\"day\"},{\"title\":\"请假事由\",\"data\":\"reason\"},{\"title\":\"流程实例id\",\"data\":\"instance_id\"}]', 'fetch', '0', null), ('22', 'course_edit', '编辑课程信息接口', '编辑课程信息接口', 't_course', null, null, null, '{\"name\":{\"column\":\"name\",\"name\":\"名称\",\"opt\":\"=\"},\"code\":{\"column\":\"code\",\"name\":\"课程编码\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"name\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"课程编码\",\"column_name_alias\":\"code\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', null, 'edit', '0', null), ('23', 'course_delete', '删除课程信息接口', '删除课程信息接口', 't_course', null, null, null, null, '[]', null, 'delete', '0', null), ('24', 'customer_list', '查询客商信息接口', '查询客商信息接口', 'select a.id as id,a.name as name,a.code as code,a.parent_name as parent_name,a.type as type,a.add_time as add_time from t_customer as a where a.name like @name and a.code like @code and a.type = @type', ' order by a.id desc', '{\"t_customer\":{\"name_cn\":\"客商表\",\"alias\":\"a\"}}', '[{\"left_table\":\"t_customer\",\"left_column\":\"\",\"join_type\":\"inner join\",\"right_table\":\"\",\"right_column\":\"\"}]', '{\"name\":{\"column\":\"a.name\",\"name\":\"公司名称\",\"opt\":\"like\",\"default_value\":\"\"},\"code\":{\"column\":\"a.code\",\"name\":\"公司编码\",\"opt\":\"like\",\"default_value\":\"\"},\"type\":{\"column\":\"a.type\",\"name\":\"单位性质\",\"opt\":\"=\",\"default_value\":\"\"}}', '[{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":1,\"column_order\":\"desc\",\"column_query_type\":\"\",\"default_value\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"公司名称\",\"column_name_alias\":\"name\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"like\",\"default_value\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"公司编码\",\"column_name_alias\":\"code\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"like\",\"default_value\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"parent_name\",\"column_name_cn\":\"归属公司\",\"column_name_alias\":\"parent_name\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"default_value\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"type\",\"column_name_cn\":\"单位性质\",\"column_name_alias\":\"type\",\"column_index\":5,\"column_order\":\"\",\"column_query_type\":\"=\",\"default_value\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"add_time\",\"column_name_cn\":\"添加时间\",\"column_name_alias\":\"add_time\",\"column_index\":6,\"column_order\":\"\",\"column_query_type\":\"\",\"default_value\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\",\"column_format\":\"yyyy-MM-dd\"}]', '[{\"title\":\"编号\",\"data\":\"id\"},{\"title\":\"公司名称\",\"data\":\"name\"},{\"title\":\"公司编码\",\"data\":\"code\"},{\"title\":\"归属公司\",\"data\":\"parent_name\"},{\"title\":\"单位性质\",\"data\":\"type\"},{\"title\":\"添加时间\",\"data\":\"add_time\"}]', 'query', '0', null), ('25', 'customer_add', '插入客商信息接口', '插入客商信息接口', 't_customer', null, null, null, '{\"name\":{\"column\":\"name\",\"name\":\"公司名称\",\"opt\":\"=\"},\"code\":{\"column\":\"code\",\"name\":\"公司编码\",\"opt\":\"=\"},\"parent_name\":{\"column\":\"parent_name\",\"name\":\"归属公司\",\"opt\":\"=\"},\"type\":{\"column\":\"type\",\"name\":\"单位性质\",\"opt\":\"=\"},\"add_time\":{\"column\":\"add_time\",\"name\":\"添加时间\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"公司名称\",\"column_name_alias\":\"name\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"公司编码\",\"column_name_alias\":\"code\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"parent_name\",\"column_name_cn\":\"归属公司\",\"column_name_alias\":\"parent_name\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"type\",\"column_name_cn\":\"单位性质\",\"column_name_alias\":\"type\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"add_time\",\"column_name_cn\":\"添加时间\",\"column_name_alias\":\"add_time\",\"column_index\":5,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', null, 'save', '0', ''), ('26', 'customer_delete', '删除客商信息接口', '删除客商信息接口', 't_customer', null, null, null, null, '[]', null, 'delete', '0', null), ('27', 'customer_edit', '编辑客商信息接口', '编辑客商信息接口', 't_customer', null, null, null, '{\"name\":{\"column\":\"name\",\"name\":\"公司名称\",\"opt\":\"=\"},\"code\":{\"column\":\"code\",\"name\":\"公司编码\",\"opt\":\"=\"},\"parent_name\":{\"column\":\"parent_name\",\"name\":\"归属公司\",\"opt\":\"=\"},\"type\":{\"column\":\"type\",\"name\":\"单位性质\",\"opt\":\"=\"},\"add_time\":{\"column\":\"add_time\",\"name\":\"添加时间\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"公司名称\",\"column_name_alias\":\"name\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"公司编码\",\"column_name_alias\":\"code\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"parent_name\",\"column_name_cn\":\"归属公司\",\"column_name_alias\":\"parent_name\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"type\",\"column_name_cn\":\"单位性质\",\"column_name_alias\":\"type\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_customer\",\"table_alias\":\"a\",\"column_name\":\"add_time\",\"column_name_cn\":\"添加时间\",\"column_name_alias\":\"add_time\",\"column_index\":5,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', null, 'edit', '0', null), ('28', 'leave_list', '查询请假信息接口', '查询请假信息接口', 'select a.id as id,a.name as name,a.day as day,a.reason as reason,a.instance_id as instance_id,a.user_id as user_id from t_leave as a where a.day = @day and a.reason like @reason', ' order by a.id desc', '{\"t_leave\":{\"name_cn\":\"请假表\",\"alias\":\"a\"}}', '[{\"left_table\":\"t_leave\",\"left_column\":\"\",\"join_type\":\"inner join\",\"right_table\":\"\",\"right_column\":\"\"}]', '{\"day\":{\"column\":\"a.day\",\"name\":\"请假天数\",\"opt\":\"=\",\"default_value\":\"\"},\"reason\":{\"column\":\"a.reason\",\"name\":\"请假事由\",\"opt\":\"like\",\"default_value\":\"\"}}', '[{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":1,\"column_order\":\"desc\",\"column_query_type\":\"\",\"default_value\":\"\",\"column_format\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"name\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"default_value\":\"\",\"column_format\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"day\",\"column_name_cn\":\"请假天数\",\"column_name_alias\":\"day\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"=\",\"default_value\":\"\",\"column_format\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"reason\",\"column_name_cn\":\"请假事由\",\"column_name_alias\":\"reason\",\"column_index\":4,\"column_order\":\"\",\"column_query_type\":\"like\",\"default_value\":\"\",\"column_format\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"instance_id\",\"column_name_cn\":\"流程实例id\",\"column_name_alias\":\"instance_id\",\"column_index\":5,\"column_order\":\"\",\"column_query_type\":\"\",\"default_value\":\"\",\"column_format\":\"\"},{\"status\":true,\"table_name\":\"t_leave\",\"table_alias\":\"a\",\"column_name\":\"user_id\",\"column_name_cn\":\"用户id\",\"column_name_alias\":\"user_id\",\"column_index\":6,\"column_order\":\"\",\"column_query_type\":\"\",\"default_value\":\"\",\"column_format\":\"\"}]', '[{\"title\":\"编号\",\"data\":\"id\"},{\"title\":\"名称\",\"data\":\"name\"},{\"title\":\"请假天数\",\"data\":\"day\"},{\"title\":\"请假事由\",\"data\":\"reason\"},{\"title\":\"流程实例id\",\"data\":\"instance_id\"},{\"title\":\"用户id\",\"data\":\"user_id\"}]', 'query', '-1', null), ('29', 'getCourseById', '查询单个课程信息接口', '查询单个课程信息接口', 'select a.id as id,a.name as name,a.code as code from t_course as a where id = @id', '', '{\"t_course\":{\"name_cn\":\"comment\",\"alias\":\"a\"}}', '[{\"left_table\":\"t_course\",\"left_column\":\"\",\"join_type\":\"\",\"right_table\":\"\",\"right_column\":\"\"}]', '{}', '[{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"a_id\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"a_name\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"课程编码\",\"column_name_alias\":\"a_code\",\"column_index\":3,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', '[{\"title\":\"编号\",\"data\":\"id\"},{\"title\":\"名称\",\"data\":\"name\"},{\"title\":\"课程编码\",\"data\":\"code\"}]', 'fetch', '0', null), ('30', 'saveCourse', '插入课程信息接口', '插入课程信息接口', 't_course', null, null, null, '{\"name\":{\"column\":\"name\",\"name\":\"名称\",\"opt\":\"=\"},\"code\":{\"column\":\"code\",\"name\":\"课程编码\",\"opt\":\"=\"}}', '[{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"id\",\"column_name_cn\":\"编号\",\"column_name_alias\":\"id\",\"column_index\":\"\",\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"name\",\"column_name_cn\":\"名称\",\"column_name_alias\":\"name\",\"column_index\":1,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"},{\"status\":true,\"table_name\":\"t_course\",\"table_alias\":\"a\",\"column_name\":\"code\",\"column_name_cn\":\"课程编码\",\"column_name_alias\":\"code\",\"column_index\":2,\"column_order\":\"\",\"column_query_type\":\"\",\"column_test_value\":\"\",\"column_test_value2\":\"\"}]', null, 'save', '0', '');
COMMIT;

-- ----------------------------
--  Table structure for `cust_column`
-- ----------------------------
DROP TABLE IF EXISTS `cust_column`;
CREATE TABLE `cust_column` (
  `table_id` int(32) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `name_cn` varchar(50) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `length` int(32) DEFAULT NULL,
  `dict` varchar(128) DEFAULT NULL,
  `pk` tinyint(1) DEFAULT NULL,
  `nullable` tinyint(1) DEFAULT NULL,
  `insertable` tinyint(1) DEFAULT NULL,
  `editable` tinyint(1) DEFAULT NULL,
  `listable` tinyint(1) DEFAULT NULL,
  `queryable` tinyint(1) DEFAULT NULL,
  `queryType` varchar(128) DEFAULT NULL,
  `formType` varchar(128) DEFAULT NULL,
  `sort` int(32) DEFAULT NULL,
  UNIQUE KEY `column_name` (`table_id`,`name`,`name_cn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `cust_form`
-- ----------------------------
DROP TABLE IF EXISTS `cust_form`;
CREATE TABLE `cust_form` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `name_cn` varchar(50) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `status` int(32) DEFAULT NULL,
  `columns` varchar(128) DEFAULT NULL,
  `html` varchar(128) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `cust_table`
-- ----------------------------
DROP TABLE IF EXISTS `cust_table`;
CREATE TABLE `cust_table` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `name_cn` varchar(50) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `status` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `table_name` (`name`,`name_cn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_abc`
-- ----------------------------
DROP TABLE IF EXISTS `t_abc`;
CREATE TABLE `t_abc` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `field1` int(10) DEFAULT NULL COMMENT '字段1',
  PRIMARY KEY (`id`),
  KEY `idx_field1` (`field1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='测试表';

-- ----------------------------
--  Table structure for `t_blog`
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `content` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '课程编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='comment';

-- ----------------------------
--  Records of `t_course`
-- ----------------------------
BEGIN;
INSERT INTO `t_course` VALUES ('2', '英语', '1112'), ('3', '线性代数', '1113'), ('4', '语文', '1114'), ('5', '生物', '2222'), ('37', '11', '11'), ('38', '22', '22'), ('39', '33', '33'), ('40', '55', '55'), ('41', '111', '1111'), ('42', 'zz', '11'), ('43', '222', '232');
COMMIT;

-- ----------------------------
--  Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '公司名称',
  `code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '公司编码',
  `parent_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '归属公司',
  `type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '单位性质',
  `add_time` date DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  KEY `idx_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='客商表';

-- ----------------------------
--  Records of `t_customer`
-- ----------------------------
BEGIN;
INSERT INTO `t_customer` VALUES ('16', '111', '11', '111', '111', '2020-03-05'), ('19', '111', '111', '111', '111', '2020-03-11'), ('20', '222', '22222', '222', '4', '2020-03-18'), ('21', '11', '11', '11', '2', '2020-03-18'), ('22', '555', '555', '555', '2', '2020-03-19');
COMMIT;

-- ----------------------------
--  Table structure for `t_leave`
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `day` int(10) DEFAULT NULL COMMENT '请假天数',
  `reason` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '请假事由',
  `instance_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '流程实例id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  KEY `idx_day` (`day`),
  KEY `idx_reason` (`reason`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='请假表';

-- ----------------------------
--  Records of `t_leave`
-- ----------------------------
BEGIN;
INSERT INTO `t_leave` VALUES ('1', 'zhangsan', '3', '家里有事', '12501', null), ('2', 'zhangsan', '3', '家里有事', null, null), ('3', 'zhangsan', '3', '家里有事', null, null), ('4', 'zhangsan', '3', '家里有事', null, null), ('5', 'zhangsan', '3', '家里有事', null, null), ('6', 'zhangsan', '3', '家里有事', '15001', null), ('7', 'zhangsan', '3', '家里有事', '17501', null), ('8', 'zhangsan', '3', '家里有事', '27501', null), ('9', 'zhangsan', '3', '家里有事', '30001', null), ('10', 'zhangsan', '3', '家里有事', '35001', null), ('11', 'zhangsan', '3', '家里有事', '40001', null), ('12', 'zhangsan', '3', '家里有事', '45001', null), ('13', '张三', '3', '家里有事', '55001', '1001'), ('14', '张三', '3', '家里有事', '60001', '1001'), ('15', '张三', '3', '有事', null, '1001'), ('16', '张三', '3', '有事', '97501', '1001'), ('17', '张三', '3', '有事', '100001', '1001'), ('18', '张三', '3', '有事3', '102501', '1001'), ('19', '张三', '3', '有事', '107501', '1001'), ('20', '张三', '3', '有事', '110005', '1001'), ('21', '张三', '3', '有事', '112501', '1001'), ('22', '张三1', '3', '11有事11', '117501', '1001'), ('23', '张三2', '3', '有事22', '120001', '1001'), ('24', '张三', '2', '有事了', '122505', '1001'), ('25', '张三', '4', '有事4', '122523', '1001'), ('26', '张三', '3', '有事1', '125001', '1001'), ('27', '张三', '3', '有事11', '127501', '1001'), ('28', '张三', '2', '有事2', '130001', '1001'), ('29', '张三', '3', '有事3', '132501', '1001'), ('30', '张三', '3', '有事4', '135001', '1001'), ('31', '张三', '3', '有事4', '135028', '1001'), ('32', '张三', '3', '有事4', '137501', '1001'), ('33', '张三', '3', '有事111', '137532', '1001'), ('34', '张三', '2', '有事222', '137564', '1001'), ('35', '张三', '2', '有事请假2天', '140001', '1001'), ('36', '张三', '4', '有事请假4天', '140021', '1001'), ('37', '11', '11', '11', '11', '11'), ('38', '张三', '3', '有事', null, '1001'), ('39', '张三', '3', '有事', '142501', '1001'), ('40', '张三', '3', '有事', '145001', '1'), ('41', '张三', '3', '有事', '145021', '1'), ('42', '张三', '3', '有事', '145061', '1'), ('43', '张三', '3', '有事', '145090', '1'), ('44', '张三', '3', '有事', '145119', '1'), ('45', '张三', '3', '有事', '145148', '1'), ('46', '张三', '1', '家里有事', '145177', '1'), ('47', '张三', '3', '有事', '145202', '1'), ('48', '张三', '3', '有事', '145225', '1'), ('49', '张三', '1', '有事了', null, null), ('50', '张三', '1', 'sss', null, '1'), ('51', '张三了', '3', '有事了', null, '1'), ('52', '张三三', '2', '有事哈哈', '145283', '1'), ('53', '张三思', '4', '回家', '145307', '1'), ('54', '张三四', '1', '回家有事', '145329', '1'), ('55', 'zhangsan', '1', '有事了', '147501', '1');
COMMIT;

-- ----------------------------
--  Table structure for `t_member`
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `age` int(10) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`),
  KEY `idx_age` (`age`),
  KEY `idx_sex` (`sex`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人';

-- ----------------------------
--  Table structure for `t_person`
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `age` int(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `age` (`age`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_process`
-- ----------------------------
DROP TABLE IF EXISTS `t_process`;
CREATE TABLE `t_process` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `biz_key` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT 'biz_key',
  `comment` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_biz_key` (`biz_key`),
  KEY `idx_name` (`name`),
  KEY `idx_comment` (`comment`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='流程表';

-- ----------------------------
--  Records of `t_process`
-- ----------------------------
BEGIN;
INSERT INTO `t_process` VALUES ('1', 'test', 'test', 'test');
COMMIT;

-- ----------------------------
--  Table structure for `t_score`
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `student_code` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '学生编号',
  `course_code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '课程编号',
  `score` int(10) DEFAULT NULL COMMENT '成绩',
  `exam_date` datetime DEFAULT NULL COMMENT '考试日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='comment';

-- ----------------------------
--  Records of `t_score`
-- ----------------------------
BEGIN;
INSERT INTO `t_score` VALUES ('1', '1001', '1111', '90', '2020-01-29 10:41:16'), ('2', '1002', '1112', '85', '2020-01-29 10:41:31'), ('3', '1001', '1112', '75', '2020-01-29 10:41:47'), ('4', '1002', '1111', '95', '2020-01-29 10:42:01');
COMMIT;

-- ----------------------------
--  Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '学号',
  `age` int(10) DEFAULT NULL COMMENT '年龄',
  `sex` int(10) DEFAULT NULL COMMENT '性别',
  `delete_flag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='学生表';

-- ----------------------------
--  Records of `t_student`
-- ----------------------------
BEGIN;
INSERT INTO `t_student` VALUES ('1', '张三', '1001', '22', '1', null), ('2', '李四', '1002', '22', '0', null), ('3', 'gggg', '1003', '23', '1', null), ('4', 'ddd', '1003', '33', '1', null), ('8', '111', '111', '25', '1', null), ('9', '222', '222', '22', '0', null), ('10', '33', '33', '33', '0', null), ('13', '66', '77', '666', '0', null), ('14', '77', '77', '77', '1', null), ('16', '1111', '1111', '1111', '1', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
