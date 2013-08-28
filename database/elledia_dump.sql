CREATE DATABASE  IF NOT EXISTS `elledia` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `elledia`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: elledia
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `materiale`
--

DROP TABLE IF EXISTS `materiale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materiale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `colata` varchar(255) NOT NULL,
  `dimensione` varchar(255) NOT NULL,
  `specifica` varchar(255) NOT NULL,
  `id_tipo_materiale` int(11) DEFAULT NULL,
  `id_tipo_dimensione` int(11) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `certificato_id` bigint(20) DEFAULT NULL,
  `tipo_materiale` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2899401E373EBAA2` (`certificato_id`),
  KEY `FK_ID_TIPO_MATERIALE` (`id_tipo_materiale`),
  KEY `FK_ID_TIPO_DIMENSIONE` (`id_tipo_dimensione`),
  CONSTRAINT `FK_ID_CERTIFICATO` FOREIGN KEY (`certificato_id`) REFERENCES `certificato` (`id`),
  CONSTRAINT `FK_ID_TIPO_DIMENSIONE` FOREIGN KEY (`id_tipo_dimensione`) REFERENCES `tipo_dimensione` (`id_tipo_dimensione`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ID_TIPO_MATERIALE` FOREIGN KEY (`id_tipo_materiale`) REFERENCES `tipo_materiale` (`id_tipo_materiale`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiale`
--

LOCK TABLES `materiale` WRITE;
/*!40000 ALTER TABLE `materiale` DISABLE KEYS */;
INSERT INTO `materiale` VALUES (3,'2000','10','PN10',NULL,1,NULL,32,'PN10'),(4,'1000','10','A234',NULL,2,NULL,32,'A234'),(5,'2000','20','PN10',NULL,2,NULL,33,'PN10'),(6,'1000','10','A234',NULL,2,NULL,33,'A234'),(7,'1000','111','1111',NULL,2,NULL,37,'1111'),(8,'10','1','1',NULL,2,NULL,38,'1'),(9,'130','10','1111',NULL,1,NULL,39,'1111'),(10,'120','10','10010',NULL,2,NULL,39,'10010'),(11,'1','1','1',NULL,1,NULL,40,'1'),(12,'1000','uuu','uuu',NULL,2,NULL,41,'uuu'),(13,'1','1','11111',NULL,2,NULL,43,'11111'),(14,'1','11','1',NULL,3,NULL,45,'1'),(15,'10','10','10',NULL,2,NULL,46,'10'),(16,'12312','sadas','asdasda',NULL,1,NULL,47,'asdasda'),(17,'2','wwwww','wwwww',NULL,2,NULL,48,'wwwww');
/*!40000 ALTER TABLE `materiale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_dimensione`
--

DROP TABLE IF EXISTS `tipo_dimensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_dimensione` (
  `id_tipo_dimensione` int(11) NOT NULL,
  `tipo_dimensione` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_dimensione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_dimensione`
--

LOCK TABLES `tipo_dimensione` WRITE;
/*!40000 ALTER TABLE `tipo_dimensione` DISABLE KEYS */;
INSERT INTO `tipo_dimensione` VALUES (1,'Numero'),(2,'Metri'),(3,'Kg'),(4,'Diametro');
/*!40000 ALTER TABLE `tipo_dimensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_materiale`
--

DROP TABLE IF EXISTS `tipo_materiale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_materiale` (
  `id_tipo_materiale` int(11) NOT NULL,
  `tipo_materiale` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_materiale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_materiale`
--

LOCK TABLES `tipo_materiale` WRITE;
/*!40000 ALTER TABLE `tipo_materiale` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_materiale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento`
--

DROP TABLE IF EXISTS `documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `disegno` varchar(255) DEFAULT NULL,
  `installazione` varchar(255) DEFAULT NULL,
  `lotto` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento`
--

LOCK TABLES `documento` WRITE;
/*!40000 ALTER TABLE `documento` DISABLE KEYS */;
/*!40000 ALTER TABLE `documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificato`
--

DROP TABLE IF EXISTS `certificato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `certificato` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_originale` datetime DEFAULT NULL,
  `codice` varchar(255) NOT NULL,
  `version` longblob,
  `content_type` varchar(255) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `id_fornitore` int(11) NOT NULL,
  `data_inserimento` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK745F41A1E26C4085` (`id`),
  KEY `FK_ID_FORNITORE` (`id_fornitore`),
  CONSTRAINT `FK_ID_FORNITORE` FOREIGN KEY (`id_fornitore`) REFERENCES `fornitore` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificato`
--

LOCK TABLES `certificato` WRITE;
/*!40000 ALTER TABLE `certificato` DISABLE KEYS */;
INSERT INTO `certificato` VALUES (0,'2013-08-23 12:15:56','0-2013',NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-08-23 10:15:56'),(32,'2013-08-23 12:17:14','2013',NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-08-23 10:17:15'),(33,'2013-08-23 12:35:16','33-2013',NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-08-23 10:35:16'),(37,'2013-08-23 12:41:55','34-2013',NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-08-23 10:41:55'),(38,'2013-08-23 15:30:26','38-2013',NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-08-23 13:30:26'),(39,'2013-08-23 16:07:19','39-2013',NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-08-23 14:07:19'),(40,'2013-08-23 16:12:30','40-2013',NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-08-23 14:12:30'),(41,'2013-08-23 16:19:26','41-2013',NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-08-23 14:19:26'),(42,'2013-08-24 15:55:53','42-2013',NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-08-24 13:55:53'),(43,'2013-08-28 23:03:50','43-2013',NULL,NULL,NULL,NULL,NULL,NULL,38,'2013-08-28 21:03:50'),(44,'2013-08-28 23:04:13','43-2013',NULL,NULL,NULL,NULL,NULL,NULL,38,'2013-08-28 21:04:13'),(45,'2013-08-28 23:05:52','45-2013',NULL,NULL,NULL,NULL,NULL,NULL,36,'2013-08-28 21:05:52'),(46,'2013-08-28 23:08:28','46-2013',NULL,NULL,NULL,NULL,NULL,NULL,36,'2013-08-28 21:08:28'),(47,'2013-08-28 23:23:28','47-2013',NULL,NULL,NULL,NULL,NULL,NULL,36,'2013-08-28 21:23:28'),(48,'2013-08-28 23:47:23','48-2013',NULL,NULL,NULL,NULL,NULL,NULL,37,'2013-08-28 21:47:23');
/*!40000 ALTER TABLE `certificato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ragione_sociale` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornitore`
--

DROP TABLE IF EXISTS `fornitore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornitore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `piva` varchar(20) DEFAULT NULL,
  `ragione_sociale` varchar(200) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `indirizzo` varchar(45) DEFAULT NULL,
  `cap` varchar(45) DEFAULT NULL,
  `citta` varchar(45) DEFAULT NULL,
  `enabled` int(1) DEFAULT '1',
  `country` varchar(2) DEFAULT 'IT',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornitore`
--

LOCK TABLES `fornitore` WRITE;
/*!40000 ALTER TABLE `fornitore` DISABLE KEYS */;
INSERT INTO `fornitore` VALUES (1,'3242237429','TARTARINI','61612661',NULL,NULL,NULL,NULL,NULL,NULL,1,'IT'),(36,'9012312312','Fiorentini','+39026131293',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(37,'','TEST FORNITORE','',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(38,'','TEST DEL FORNITORE','',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(39,'0','PEPE & CAMILLO','0',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(40,'','CLOE','',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `fornitore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'elledia'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-08-28 23:53:20
