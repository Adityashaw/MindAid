-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: mindaiddb
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `doc_id` int DEFAULT NULL,
  `contact_media` varchar(45) DEFAULT NULL,
  `schedule_day` varchar(45) DEFAULT NULL,
  `schedule_time_start` varchar(120) DEFAULT NULL,
  `fee` int DEFAULT NULL,
  `scheduleday_parameter` varchar(45) DEFAULT NULL,
  `approval` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2089 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (2069,1009,'message','Monday','1~16:00:00',800,'1','approved'),(2070,1009,'live','Monday','1~10:00:00,1~11:00:00,1~12:00:00',500,'1','approved'),(2073,1011,'message','Monday,Wednesday','1~13:00:00,3~13:00:00',800,'13','approved'),(2074,1011,'live','Monday,Wednesday','1~10:00:00,1~11:00:00,1~12:00:00,3~10:00:00,3~11:00:00,3~12:00:00',500,'13','approved'),(2077,1015,'message','Monday,Friday','1~13:00:00,5~19:00:00',800,'15','approved'),(2083,1010,'message','Monday,Tuesday','1~02:00:00,1~10:00:00,1~13:00:00,2~10:00:00',1000,'12','approved'),(2084,1010,'live','Tuesday','2~13:00:00,2~14:00:00,2~15:00:00',400,'2','approved'),(2085,1014,'message','Monday,Wednesday','1~13:00:00,1~16:00:00,3~10:00:00,3~16:00:00',800,'13','approved'),(2086,1014,'live','Monday,Wednesday','1~01:00:00,1~10:00:00,1~11:00:00,3~20:00:00,3~21:00:00',500,'13','approved'),(2087,1012,'message','Sunday,Wednesday,Friday','7~19:00:00,3~10:00:00,3~13:00:00,5~10:00:00,5~13:00:00',1000,'735','approved'),(2088,1012,'live','Sunday,Wednesday,Friday','7~10:00:00,7~11:00:00,3~18:00:00,5~20:00:00,5~21:00:00',400,'735','approved');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-15 11:21:09
