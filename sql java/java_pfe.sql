-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: java
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `pfe`
--

DROP TABLE IF EXISTS `pfe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pfe` (
  `idpfe` int NOT NULL AUTO_INCREMENT,
  `sujet` varchar(45) DEFAULT NULL,
  `idstudent` int DEFAULT NULL,
  `name_s` varchar(45) DEFAULT NULL,
  `idprof` int DEFAULT NULL,
  `entreprise` varchar(45) DEFAULT NULL,
  `cin_s` varchar(9) DEFAULT NULL,
  `contact_prof` varchar(45) DEFAULT NULL,
  `rapporteur` int DEFAULT NULL,
  `etat` varchar(45) DEFAULT 'Sujet non deposer',
  `note` float DEFAULT NULL,
  `nom_prof` varchar(45) DEFAULT NULL,
  `nom_rapporteur` varchar(45) DEFAULT NULL,
  `mail_rapp` varchar(45) DEFAULT NULL,
  `mail_prof` varchar(45) DEFAULT NULL,
  `pfe_s` blob,
  PRIMARY KEY (`idpfe`),
  KEY `idprof_idx` (`idprof`),
  KEY `idstudent_idx` (`idstudent`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pfe`
--

LOCK TABLES `pfe` WRITE;
/*!40000 ALTER TABLE `pfe` DISABLE KEYS */;
/*!40000 ALTER TABLE `pfe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-04  3:22:30
