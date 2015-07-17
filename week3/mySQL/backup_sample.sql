-- MySQL dump 10.13  Distrib 5.6.23, for osx10.8 (x86_64)
--
-- Host: localhost    Database: sample
-- ------------------------------------------------------
-- Server version	5.6.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `mark_view`
--

DROP TABLE IF EXISTS `mark_view`;
/*!50001 DROP VIEW IF EXISTS `mark_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `mark_view` AS SELECT 
 1 AS `year`,
 1 AS `grade`,
 1 AS `student_id`,
 1 AS `quarterly_per`,
 1 AS `half_yearly_per`,
 1 AS `annual_per`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `mark_view2`
--

DROP TABLE IF EXISTS `mark_view2`;
/*!50001 DROP VIEW IF EXISTS `mark_view2`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `mark_view2` AS SELECT 
 1 AS `year`,
 1 AS `grade`,
 1 AS `id`,
 1 AS `quarterly_avg`,
 1 AS `half_yearly_avg`,
 1 AS `annual_avg`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `marks`
--

DROP TABLE IF EXISTS `marks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marks` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(19) NOT NULL,
  `subject_id` bigint(19) NOT NULL,
  `quarterly` int(11) NOT NULL DEFAULT '0',
  `half_yearly` int(11) NOT NULL DEFAULT '0',
  `annual` int(11) NOT NULL DEFAULT '0',
  `year` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `average` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_marks_student_id` (`student_id`),
  CONSTRAINT `fk_marks_student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1126 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marks`
--

LOCK TABLES `marks` WRITE;
/*!40000 ALTER TABLE `marks` DISABLE KEYS */;
INSERT INTO `marks` VALUES (1001,100001,1,0,79,91,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',56.6667),(1002,100002,1,35,49,67,2003,6,'2015-07-17 07:41:37','2015-07-17 11:17:16',50.3333),(1003,100003,1,100,97,95,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',97.3333),(1004,100004,1,38,38,65,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',47),(1005,100005,1,73,40,100,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',71),(1006,100001,2,0,30,76,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',35.3333),(1007,100002,2,37,45,77,2003,6,'2015-07-17 07:41:37','2015-07-17 11:17:16',53),(1008,100003,2,93,91,98,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',94),(1009,100004,2,93,59,63,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',71.6667),(1010,100005,2,34,89,45,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',56),(1011,100001,3,0,86,89,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',58.3333),(1012,100002,3,46,76,38,2003,6,'2015-07-17 07:41:37','2015-07-17 11:17:16',53.3333),(1013,100003,3,46,0,83,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',43),(1014,100004,3,71,74,31,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',58.6667),(1015,100005,3,34,54,36,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',41.3333),(1016,100001,4,0,0,80,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',26.6667),(1017,100002,4,52,43,21,2003,6,'2015-07-17 07:41:37','2015-07-17 11:17:16',38.6667),(1018,100003,4,91,95,99,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',95),(1019,100004,4,46,76,39,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',53.6667),(1020,100005,4,80,41,94,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',71.6667),(1021,100001,5,0,31,88,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',39.6667),(1022,100002,5,33,44,43,2003,6,'2015-07-17 07:41:37','2015-07-17 11:17:16',40),(1023,100003,5,98,92,90,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',93.3333),(1024,100004,5,52,63,63,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',59.3333),(1025,100005,5,56,60,48,2003,6,'2015-07-17 07:41:37','2015-07-17 11:14:49',54.6667),(1026,100001,1,59,34,57,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',50),(1027,100002,1,47,37,84,2004,7,'2015-07-17 07:41:37','2015-07-17 11:17:16',56),(1028,100003,1,47,80,97,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',74.6667),(1029,100004,1,89,43,68,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',66.6667),(1030,100005,1,72,82,47,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',67),(1031,100001,2,44,54,31,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',43),(1032,100002,2,85,96,79,2004,7,'2015-07-17 07:41:37','2015-07-17 11:17:16',86.6667),(1033,100003,2,84,63,57,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',68),(1034,100004,2,83,97,53,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',77.6667),(1035,100005,2,53,30,80,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',54.3333),(1036,100001,3,44,90,54,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',62.6667),(1037,100002,3,53,36,77,2004,7,'2015-07-17 07:41:37','2015-07-17 11:17:16',55.3333),(1038,100003,3,64,55,39,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',52.6667),(1039,100004,3,95,36,54,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',61.6667),(1040,100005,3,66,87,37,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',63.3333),(1041,100001,4,31,65,69,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',55),(1042,100002,4,98,30,84,2004,7,'2015-07-17 07:41:37','2015-07-17 11:17:16',70.6667),(1043,100003,4,43,60,83,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',62),(1044,100004,4,76,79,40,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',65),(1045,100005,4,66,43,75,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',61.3333),(1046,100001,5,58,51,98,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',69),(1047,100002,5,41,92,89,2004,7,'2015-07-17 07:41:37','2015-07-17 11:17:16',74),(1048,100003,5,86,56,43,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',61.6667),(1049,100004,5,35,90,92,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',72.3333),(1050,100005,5,36,35,48,2004,7,'2015-07-17 07:41:37','2015-07-17 11:14:49',39.6667),(1051,100001,1,96,57,94,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',82.3333),(1052,100002,1,68,41,40,2005,8,'2015-07-17 07:41:37','2015-07-17 11:17:16',49.6667),(1053,100003,1,60,83,73,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',72),(1054,100004,1,53,81,97,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',77),(1055,100005,1,64,81,39,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',61.3333),(1056,100001,2,77,82,36,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',65),(1057,100002,2,73,57,42,2005,8,'2015-07-17 07:41:37','2015-07-17 11:17:16',57.3333),(1058,100003,2,38,35,81,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',51.3333),(1059,100004,2,69,46,44,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',53),(1060,100005,2,35,95,39,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',56.3333),(1061,100001,3,32,70,58,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',53.3333),(1062,100002,3,43,48,55,2005,8,'2015-07-17 07:41:37','2015-07-17 11:17:16',48.6667),(1063,100003,3,81,38,90,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',69.6667),(1064,100004,3,88,90,92,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',90),(1065,100005,3,34,95,76,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',68.3333),(1066,100001,4,64,69,87,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',73.3333),(1067,100002,4,62,38,85,2005,8,'2015-07-17 07:41:37','2015-07-17 11:17:16',61.6667),(1068,100003,4,79,49,86,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',71.3333),(1069,100004,4,68,33,33,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',44.6667),(1070,100005,4,72,39,84,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',65),(1071,100001,5,65,77,100,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',80.6667),(1072,100002,5,82,90,23,2005,8,'2015-07-17 07:41:37','2015-07-17 11:17:16',65),(1073,100003,5,76,44,55,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',58.3333),(1074,100004,5,96,76,46,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',72.6667),(1075,100005,5,50,75,49,2005,8,'2015-07-17 07:41:37','2015-07-17 11:14:49',58),(1076,100001,1,67,84,51,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',67.3333),(1077,100002,1,63,68,36,2006,9,'2015-07-17 07:41:37','2015-07-17 11:17:16',55.6667),(1078,100003,1,89,96,31,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',72),(1079,100004,1,86,74,82,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',80.6667),(1080,100005,1,91,49,70,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',70),(1081,100001,2,65,90,67,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',74),(1082,100002,2,77,38,65,2006,9,'2015-07-17 07:41:37','2015-07-17 11:17:16',60),(1083,100003,2,100,99,95,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',98),(1084,100004,2,100,37,40,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',59),(1085,100005,2,85,69,37,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',63.6667),(1086,100001,3,0,0,32,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',10.6667),(1087,100002,3,98,34,21,2006,9,'2015-07-17 07:41:37','2015-07-17 11:17:16',51),(1088,100003,3,65,83,36,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',61.3333),(1089,100004,3,82,75,67,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',74.6667),(1090,100005,3,93,78,39,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',70),(1091,100001,4,83,75,59,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',72.3333),(1092,100002,4,81,56,20,2006,9,'2015-07-17 07:41:37','2015-07-17 11:17:16',52.3333),(1093,100003,4,94,60,68,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',74),(1094,100004,4,59,92,47,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',66),(1095,100005,4,76,82,83,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',80.3333),(1096,100001,5,73,70,87,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',76.6667),(1097,100002,5,50,52,26,2006,9,'2015-07-17 07:41:37','2015-07-17 11:17:16',42.6667),(1098,100003,5,57,96,88,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',80.3333),(1099,100004,5,54,77,51,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',60.6667),(1100,100005,5,86,66,91,2006,9,'2015-07-17 07:41:37','2015-07-17 11:14:49',81),(1101,100001,1,0,0,0,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',0),(1102,100002,1,93,31,69,2007,10,'2015-07-17 07:41:37','2015-07-17 11:17:16',64.3333),(1103,100003,1,81,92,69,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',80.6667),(1104,100004,1,32,31,76,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',46.3333),(1105,100005,1,82,65,87,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',78),(1106,100001,2,62,80,36,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',59.3333),(1107,100002,2,34,49,77,2007,10,'2015-07-17 07:41:37','2015-07-17 11:17:16',53.3333),(1108,100003,2,95,89,89,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',91),(1109,100004,2,50,46,76,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',57.3333),(1110,100005,2,86,87,84,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',85.6667),(1111,100001,3,91,64,80,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',78.3333),(1112,100002,3,52,60,84,2007,10,'2015-07-17 07:41:37','2015-07-17 11:17:16',65.3333),(1113,100003,3,50,81,58,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',63),(1114,100004,3,65,39,46,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',50),(1115,100005,3,44,79,91,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',71.3333),(1116,100001,4,55,96,55,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',68.6667),(1117,100002,4,82,67,84,2007,10,'2015-07-17 07:41:37','2015-07-17 11:17:16',77.6667),(1118,100003,4,85,76,47,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',69.3333),(1119,100004,4,79,89,89,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',85.6667),(1120,100005,4,72,56,57,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',61.6667),(1121,100001,5,93,52,41,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',62),(1122,100002,5,42,63,65,2007,10,'2015-07-17 07:41:37','2015-07-17 11:17:16',56.6667),(1123,100003,5,88,31,43,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',54),(1124,100004,5,57,36,67,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',53.3333),(1125,100005,5,87,99,93,2007,10,'2015-07-17 07:41:37','2015-07-17 11:14:49',93);
/*!40000 ALTER TABLE `marks` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER avg_trigger_insert before INSERT ON marks 
FOR EACH ROW
BEGIN
SET new.average=(new.annual+new.half_yearly+new.quarterly)/3;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER avg_trigger_update before UPDATE ON marks 
FOR EACH ROW
BEGIN
SET new.average=(new.annual+new.half_yearly+new.quarterly)/3;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `medals`
--

DROP TABLE IF EXISTS `medals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medals` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(19) NOT NULL,
  `game_id` bigint(19) NOT NULL,
  `medal_recieved` varchar(10) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `medal_won` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_medals_student_id` (`student_id`),
  CONSTRAINT `fk_medals_student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medals`
--

LOCK TABLES `medals` WRITE;
/*!40000 ALTER TABLE `medals` DISABLE KEYS */;
INSERT INTO `medals` VALUES (101,100003,5,'gold',2003,6,'2015-07-17 07:00:06','2015-07-17 11:56:34','gold'),(102,100001,5,'gold',2003,6,'2015-07-17 07:00:06','2015-07-17 11:55:47','gold'),(103,100002,4,'silver',2003,6,'2015-07-17 07:00:06','2015-07-17 11:56:34','silver'),(104,100003,1,'gold',2003,6,'2015-07-17 07:00:06','2015-07-17 11:56:34','gold'),(105,100003,3,'bronze',2004,7,'2015-07-17 07:00:06','2015-07-17 11:56:34','bronze'),(106,100003,4,'silver',2004,7,'2015-07-17 07:00:06','2015-07-17 11:56:34','silver'),(107,100002,2,'silver',2004,7,'2015-07-17 07:00:06','2015-07-17 11:56:34','silver'),(108,100002,4,'bronze',2004,7,'2015-07-17 07:00:06','2015-07-17 11:56:34','bronze'),(109,100002,5,'gold',2004,7,'2015-07-17 07:00:06','2015-07-17 11:56:34','gold'),(110,100003,3,'bronze',2005,8,'2015-07-17 07:00:06','2015-07-17 11:56:34','bronze'),(111,100001,2,'gold',2005,8,'2015-07-17 07:00:06','2015-07-17 11:56:34','gold'),(112,100001,3,'bronze',2005,8,'2015-07-17 07:00:06','2015-07-17 11:56:34','bronze'),(113,100001,4,'bronze',2005,8,'2015-07-17 07:00:06','2015-07-17 11:56:34','bronze'),(114,100004,3,'gold',2003,6,'2015-07-17 11:54:22','2015-07-17 11:54:22','gold');
/*!40000 ALTER TABLE `medals` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER medal_trigger_insert before INSERT ON medals 
FOR EACH ROW
BEGIN
IF new.medal_won is null THEN
SET new.medal_won=new.medal_recieved;
ELSE
SET new.medal_recieved=new.medal_won;
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER medal_trigger_update before UPDATE ON medals 
FOR EACH ROW
BEGIN
IF new.medal_won is null THEN
SET new.medal_won=new.medal_recieved;
ELSE
SET new.medal_recieved=new.medal_won;
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100009 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (100001,'Thor','2015-07-17 07:00:25','2015-07-17 07:00:25'),(100002,'Hulk','2015-07-17 07:00:25','2015-07-17 07:00:25'),(100003,'Daredevil','2015-07-17 07:00:25','2015-07-17 07:00:25'),(100004,'X Man','2015-07-17 07:00:25','2015-07-17 07:00:25'),(100005,'Ironman','2015-07-17 07:00:25','2015-07-17 07:00:25'),(100006,'Quicksilver','2015-07-17 07:00:25','2015-07-17 07:00:25'),(100007,'Thor2','2015-07-17 07:00:25','2015-07-17 07:46:23'),(100008,'Silver Surfer','2015-07-17 07:48:34','2015-07-17 07:48:34');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_summary`
--

DROP TABLE IF EXISTS `students_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students_summary` (
  `student_id` bigint(19) NOT NULL,
  `student_name` varchar(100) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `percentage` int(11) NOT NULL DEFAULT '0',
  `no_of_medals_received` int(3) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_summary`
--

LOCK TABLES `students_summary` WRITE;
/*!40000 ALTER TABLE `students_summary` DISABLE KEYS */;
INSERT INTO `students_summary` VALUES (100001,'Thor',2003,91,1),(100001,'Thor',2004,57,0),(100001,'Thor',2005,94,3),(100001,'Thor',2006,51,0),(100001,'Thor',2007,0,0),(100002,'Hulk',2003,77,1),(100002,'Hulk',2004,94,3),(100002,'Hulk',2005,50,0),(100002,'Hulk',2006,46,0),(100002,'Hulk',2007,79,0),(100003,'Daredevil',2003,95,2),(100003,'Daredevil',2004,97,2),(100003,'Daredevil',2005,73,1),(100003,'Daredevil',2006,31,0),(100003,'Daredevil',2007,69,0),(100004,'X Man',2003,65,0),(100004,'X Man',2004,68,0),(100004,'X Man',2005,97,0),(100004,'X Man',2006,82,0),(100004,'X Man',2007,76,0),(100005,'Ironman',2003,100,0),(100005,'Ironman',2004,47,0),(100005,'Ironman',2005,39,0),(100005,'Ironman',2006,70,0),(100005,'Ironman',2007,87,0);
/*!40000 ALTER TABLE `students_summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `view4`
--

DROP TABLE IF EXISTS `view4`;
/*!50001 DROP VIEW IF EXISTS `view4`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view4` AS SELECT 
 1 AS `name`,
 1 AS `year`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view5`
--

DROP TABLE IF EXISTS `view5`;
/*!50001 DROP VIEW IF EXISTS `view5`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view5` AS SELECT 
 1 AS `id`,
 1 AS `student_id`,
 1 AS `name`,
 1 AS `year`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `mark_view`
--

/*!50001 DROP VIEW IF EXISTS `mark_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `mark_view` AS select `marks`.`year` AS `year`,`marks`.`grade` AS `grade`,`marks`.`student_id` AS `student_id`,avg(`marks`.`quarterly`) AS `quarterly_per`,avg(`marks`.`half_yearly`) AS `half_yearly_per`,avg(`marks`.`annual`) AS `annual_per` from `marks` group by `marks`.`year`,`marks`.`student_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `mark_view2`
--

/*!50001 DROP VIEW IF EXISTS `mark_view2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `mark_view2` AS select `marks`.`year` AS `year`,`marks`.`grade` AS `grade`,`marks`.`student_id` AS `id`,avg(`marks`.`quarterly`) AS `quarterly_avg`,avg(`marks`.`half_yearly`) AS `half_yearly_avg`,avg(`marks`.`annual`) AS `annual_avg` from `marks` group by `marks`.`year`,`marks`.`student_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view4`
--

/*!50001 DROP VIEW IF EXISTS `view4`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view4` AS select `students`.`name` AS `name`,`medals`.`year` AS `year` from (`students` join `medals` on((`students`.`id` = `medals`.`student_id`))) */
/*!50002 WITH CASCADED CHECK OPTION */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view5`
--

/*!50001 DROP VIEW IF EXISTS `view5`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view5` AS select `medals`.`id` AS `id`,`medals`.`student_id` AS `student_id`,`students`.`name` AS `name`,`medals`.`year` AS `year` from (`students` join `medals` on((`students`.`id` = `medals`.`student_id`))) */
/*!50002 WITH CASCADED CHECK OPTION */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-17 17:48:31
