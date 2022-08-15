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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `user_phone` varchar(45) DEFAULT NULL,
  `user_email` varchar(120) DEFAULT NULL,
  `user_password` varchar(120) DEFAULT NULL,
  `confirm_password` varchar(120) DEFAULT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  `enabled` varchar(45) DEFAULT NULL,
  `reset_password_token` varchar(45) DEFAULT NULL,
  `user_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'TestUser','018222222','abc@gmail.com','1234','1234','xxxxx','1','xxxxx','user'),(10,'Dr. Mithila Zaman','+8801882449010','mithila@gmail.com','1234','1234',NULL,'1',NULL,'doctor'),(13,'Dr.Saidur Rahman','+8801882449010','shams@gmail.com','1234','1234',NULL,'1',NULL,'doctor'),(15,'Dr. Ashfaq Ali','+8801882449010','ashfaq@gmail.com','1234','1234',NULL,'1',NULL,'doctor'),(16,'Dr. Sanjana Mehjabin','+8801882449010','sanjana@gmail.com','1234','1234',NULL,'1',NULL,'doctor'),(18,'Dr. Afia Akter','+8801882449010','afia@gmail.com','1234','1234',NULL,'1',NULL,'doctor'),(25,'Dr. Mesbah Alom','+8801882449010','mesbah@gmail.com','1234','1234',NULL,'1',NULL,'doctor'),(28,'Maliha','0191111111111','mithila@gmail.com','$2a$10$G6dxFpOCU.KXBzfymi62mOjskfuqNk1RFf2fWjJnJSwvuEgQoNWIm','maliha1234','v2ffGWvt7JZVaPy6cn4PV0uYfvRC5ipcnqt3BKCs61khmCdxHzrgdb95vP3yOLlw','0',NULL,'user'),(29,'Maliha','0191111111111','miftahulmaliha35@gmail.com','$2a$10$nPneiRY8.s9YEgK1P3Dap.5wwvsTQ8gauVwFOhjBYwboIP3RPOhl2','12345678',NULL,'1',NULL,'user'),(30,'Admin','019595959','admin@gmail.com','1234','1234',NULL,'1',NULL,'admin');
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

-- Dump completed on 2022-08-15 11:21:09
