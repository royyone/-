-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: _project
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `award`
--

DROP TABLE IF EXISTS `award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `award` (
  `award_id` int NOT NULL AUTO_INCREMENT,
  `school_name` varchar(60) DEFAULT '',
  `college_name` varchar(60) DEFAULT '' COMMENT '学院',
  `stu_name` varchar(60) NOT NULL DEFAULT '' COMMENT '获奖学生姓名,整支队伍',
  `team_name` varchar(60) DEFAULT '' COMMENT '队伍名称',
  `award` varchar(60) NOT NULL DEFAULT '' COMMENT '获得的名次',
  `status` int NOT NULL DEFAULT '0' COMMENT '奖状生成状态，0未生成，1已生成未盖章，2已盖章',
  `file_path` varchar(100) DEFAULT '' COMMENT '存储的文件路径，提供预览下载链接',
  `adviser` varchar(60) DEFAULT '',
  `game_id` int NOT NULL,
  PRIMARY KEY (`award_id`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存放所有获奖结果';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `award`
--

LOCK TABLES `award` WRITE;
/*!40000 ALTER TABLE `award` DISABLE KEYS */;
INSERT INTO `award` VALUES (52,'杭州师范大学','经亨颐教育学院','尹吴潇 赵政和 戴志炜',NULL,'金奖',2,'certificate_52.pdf','刘春英',1),(53,'杭州师范大学','信息科学与技术学院','樊睿 严子轩 周轩熠',NULL,'金奖',2,'certificate_53.pdf','王灿',1),(54,'杭州师范大学','人文学院','郑顺帆 倪伟聪 黄彬成',NULL,'银奖',2,'certificate_54.pdf','宋炯 韩建民',1),(55,'杭州师范大学','数学学院','李嘉琪 朱文豪 王茹月',NULL,'银奖',2,'certificate_55.pdf','陈叶芳 王晓丽',1),(56,'杭州师范大学','信息科学与技术学院','钟昀铮 肖泽宇 刘哲',NULL,'银奖',2,'certificate_56.pdf','叶海荣 罗强',1),(57,'杭州师范大学','医学院','董政儒 郭胜航 周越',NULL,'参赛奖',2,'certificate_57.pdf','李钧',1),(58,'杭州师范大学','护理学院','郑凯心 侯津涛 汤丰瑜',NULL,'顽强拼搏奖',2,'certificate_58.pdf','张平 刘春英',1),(59,'杭州师范大学','数学学院','张志心 郭一铭 楼沁霈',NULL,'最佳女队',2,'certificate_59.pdf','王灿',1),(235,NULL,'经亨颐教育学院','尹吴潇 赵政和 戴志炜',NULL,'金奖',2,'certificate_235.pdf','刘春英',30),(236,NULL,'信息科学与技术学院','樊睿 严子轩 周轩熠',NULL,'金奖',2,'certificate_236.pdf','王灿',30),(237,NULL,'人文学院','郑顺帆 倪伟聪 黄彬成',NULL,'银奖',2,'certificate_237.pdf','宋炯 韩建民',30),(238,NULL,'数学学院','李嘉琪 朱文豪 王茹月',NULL,'银奖',2,'certificate_238.pdf','陈叶芳 王晓丽',30),(239,NULL,'信息科学与技术学院','钟昀铮 肖泽宇 刘哲',NULL,'银奖',2,'certificate_239.pdf','叶海荣 罗强',30),(240,NULL,'医学院','董政儒 郭胜航 周越',NULL,'参赛奖',2,'certificate_240.pdf','李钧',30),(241,NULL,'护理学院','郑凯心 侯津涛 汤丰瑜',NULL,'顽强拼搏奖',2,'certificate_241.pdf','张平 刘春英',30),(242,NULL,'数学学院','张志心 郭一铭 楼沁霈',NULL,'最佳女队',2,'certificate_242.pdf','王灿',30),(243,NULL,'经亨颐教育学院','尹吴潇 赵政和 戴志炜',NULL,'金奖',1,'certificate_243.pdf','刘春英',1),(244,NULL,'信息科学与技术学院','樊睿 严子轩 周轩熠',NULL,'金奖',2,'certificate_244.pdf','王灿',1),(245,NULL,'人文学院','郑顺帆 倪伟聪 黄彬成',NULL,'银奖',2,'certificate_245.pdf','宋炯 韩建民',1),(246,NULL,'数学学院','李嘉琪 朱文豪 王茹月',NULL,'银奖',2,'certificate_246.pdf','陈叶芳 王晓丽',1),(247,NULL,'信息科学与技术学院','钟昀铮 肖泽宇 刘哲',NULL,'银奖',1,'certificate_247.pdf','叶海荣 罗强',1),(248,NULL,'医学院','董政儒 郭胜航 周越',NULL,'参赛奖',2,'certificate_248.pdf','李钧',1),(249,NULL,'护理学院','郑凯心 侯津涛 汤丰瑜',NULL,'顽强拼搏奖',2,'certificate_249.pdf','张平 刘春英',1),(250,NULL,'数学学院','张志心 郭一铭 楼沁霈',NULL,'最佳女队',2,'certificate_250.pdf','王灿',1);
/*!40000 ALTER TABLE `award` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game` (
  `game_id` int NOT NULL AUTO_INCREMENT,
  `game_type` varchar(30) NOT NULL,
  `game_level` varchar(30) DEFAULT NULL,
  `game_date` date DEFAULT NULL,
  `game_name` varchar(60) NOT NULL,
  `game_author` varchar(60) NOT NULL,
  `game_status` int NOT NULL DEFAULT '0' COMMENT '发布状态',
  `organizer` varchar(60) NOT NULL COMMENT '承办单位',
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'一类','校级','2024-02-01','杭州师范大学程序设计竞赛校赛','潘志程',1,'信息科学与技术学院'),(30,'一类','一类','2024-03-01','0304测试','潘志程',1,'信息科学与技术学院');
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `user_pwd` varchar(30) NOT NULL,
  `isadmin` int NOT NULL,
  `truth_name` varchar(60) NOT NULL COMMENT '真实姓名',
  `user_college` varchar(60) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin',1,'潘志程',''),(2,'teacher','teacher',2,'戴泽耀',''),(3,'student','123',0,'严子轩',''),(12,'awasd','123',2,'liusf','音乐学院');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-19 21:02:16
