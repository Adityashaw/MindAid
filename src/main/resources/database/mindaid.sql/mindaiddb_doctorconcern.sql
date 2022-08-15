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
-- Table structure for table `doctorconcern`
--

DROP TABLE IF EXISTS `doctorconcern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctorconcern` (
  `docconcern_id` int NOT NULL AUTO_INCREMENT,
  `concern_id` int DEFAULT NULL,
  `doc_id` int DEFAULT NULL,
  `approval` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`docconcern_id`),
  KEY `doc_id_idx` (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctorconcern`
--

LOCK TABLES `doctorconcern` WRITE;
/*!40000 ALTER TABLE `doctorconcern` DISABLE KEYS */;
INSERT INTO `doctorconcern` VALUES (1,14,1001,'approved'),(2,15,1001,'approved'),(3,2,1002,'approved'),(4,6,1002,'approved'),(5,16,1004,'approved'),(6,5,1005,'approved'),(7,13,1005,'approved'),(8,10,1003,'approved'),(51,1,1007,'approved'),(52,2,1007,'approved'),(53,14,1007,'approved'),(54,15,1007,'approved'),(97,5,1009,'approved'),(98,6,1009,'approved'),(99,14,1009,'approved'),(100,15,1009,'approved'),(106,8,1011,'approved'),(107,10,1011,'approved'),(108,12,1011,'approved'),(109,13,1011,'approved'),(110,17,1011,'approved'),(116,1,1015,'approved'),(117,4,1015,'approved'),(118,10,1015,'approved'),(119,11,1015,'approved'),(120,13,1015,'approved'),(121,17,1015,'approved'),(134,1,1010,'approved'),(135,6,1010,'approved'),(136,14,1010,'approved'),(137,15,1010,'approved'),(144,2,1014,'approved'),(145,5,1014,'approved'),(146,7,1014,'approved'),(147,10,1014,'approved'),(148,11,1014,'approved'),(149,17,1014,'approved'),(150,3,1012,'approved'),(151,4,1012,'approved'),(152,5,1012,'approved'),(153,17,1012,'approved');
/*!40000 ALTER TABLE `doctorconcern` ENABLE KEYS */;
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
