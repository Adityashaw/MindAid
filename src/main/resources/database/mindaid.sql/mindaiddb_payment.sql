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
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `name_on_card` varchar(45) DEFAULT NULL,
  `card_number` varchar(45) DEFAULT NULL,
  `mm` varchar(45) DEFAULT NULL,
  `year` varchar(45) DEFAULT NULL,
  `ccv` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `confirm_email` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `doc_id` int DEFAULT NULL,
  `schedule_id` int DEFAULT NULL,
  `schedule_date` varchar(45) DEFAULT NULL,
  `schedule_time` varchar(45) DEFAULT NULL,
  `schedule_duration` int DEFAULT NULL,
  `session_link` varchar(120) DEFAULT NULL,
  `active_status` int DEFAULT NULL,
  `contact_media` varchar(45) DEFAULT NULL,
  `approval` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (36,'Miftahul Zannat Maliha','1111111111','11','2025','111','Miftahul','Maliha','miftahulmaliha35@gmail.com','miftahulmaliha35@gmail.com','+8801882449010',29,1010,2083,'2022-08-15','2:00 am',3,'http://localhost:9090/chat',0,'message','pending'),(37,'Miftahul Zannat Maliha','1111111111','11','2025','111','Miftahul','Maliha','miftahulmaliha35@gmail.com','miftahulmaliha35@gmail.com','+8801882449010',29,1014,2086,'2022-08-15','1:00 am',1,'https://meet.jit.si/mindaidywcfioplzu',0,'live','pending');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
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
