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
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `doc_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` longtext,
  `speciality` varchar(45) DEFAULT NULL,
  `education` varchar(45) DEFAULT NULL,
  `experience` varchar(120) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `approval` varchar(45) DEFAULT NULL,
  `login_email` varchar(45) DEFAULT NULL,
  `login_password` varchar(45) DEFAULT NULL,
  `applied_date` varchar(45) DEFAULT NULL,
  `photos` varchar(64) DEFAULT NULL,
  `patient_count` int DEFAULT NULL,
  `ratings` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1017 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1009,'Dr.Saidur Rahman','Hello I am a psychiatrist having completed my MBBS and FCPDS in psychiatry. I treat people and heal their mental issues. If you are having any problem in marital relationship or feeling stressed, having insomnia you can take my therapy and get rid of your mental illness','Relationship Consultant','MBBS, FCPS(psychiatry)','5 yeas of experience at LifeSpring','shams@gmail.com','+8801882449010','45','Male','added','saidur@gmail.com','1234','2022-08-13','doctor_saidur.jpg',0,'0.0'),(1010,'Dr. Mithila Zaman','Hello I am a psychiatrist having completed my MBBS and MCPDS in psychiatry. I treat people and heal their mental issues. If you are having any problem in marital relationship or feeling stressed, having insomnia you can take my therapy and get rid of your mental illness','Relationship Consultant','MBBS, MCPS(Psychiatry)','3 years at Square Hospitals Ltd','mithila@gmail.com','+8801882449010','40','Female','added','mithila@gmail.com','1234','2022-08-13','mithila.jpeg',0,'0.0'),(1011,'Dr. Sanjana Mehjabin','Hello I am a psychiatrist having completed my MBBS and MD in psychiatry. I am working as a clinical psychologist in Enam Medical College. I treat people and heal their mental issues. If you are having any problem with insomnia or feeling stressed, having anger issues, you can take my therapy and get rid of your mental illness','Social Worker','MD(psychiatry),  MBBS','3 years of experience in Enam Medical College','sanjana@gmail.com','+8801882449010','35','Female','added','sanjana@gmail.com','1234','2022-08-14','sanjana.jpg',0,'0.0'),(1012,'Dr. Ashfaq Ali','Hello I am a psychiatrist having completed my MBBS and MD in psychiatry. I am working as a clinical psychologist in Enam Medical College. I treat people and heal their mental issues. If you are having any problem with insomnia or feeling stressed, having anger issues, you can take my therapy and get rid of your mental illness','Psychologist','MD(psychiatry),  MBBS','3 years at Labaid ','ashfaq@gmail.com','+8801882449010','48','Male','added','ashfaq@gmail.com','1234','2022-08-14','ashfaq.jpg',0,'0.0'),(1014,'Dr. Mesbah Alom','Hello I am a psychologist having completed my BSc and MSc in Psychology. I am working as a counsellor in Moner Shusthota last 7 years. I treat people and heal their mental issues. ','Psychologist','BSc(Psychology), Msc(Psychology)','7+ years at Moner Shusthota','mesbah@gmail.com','+8801882449010','42','Male','added','mesbah@gmail.com','1234','2022-08-14','mesbah.jpg',0,'0.0'),(1015,'Dr. Afia Akter','Hello I am a psychologist having completed my BSc and MSc in Psychology. I am working as a counsellor in Lifespring last 2 years. I treat people and heal their mental issues. If you are feeling traumatized or having social anxieties, feel free to talk to me.','Therapist','BSc(Psychology), Msc(Psychology)','2 yeas of experience at LifeSpring','afia@gmail.com','+8801882449010','32','Female','added','afia@gmail.com','1234','2022-08-14','afia.jpg',0,'0.0');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
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
