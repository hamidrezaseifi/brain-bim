-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bimbrain
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `tbladdresses`
--

DROP TABLE IF EXISTS `tbladdresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbladdresses` (
  `ownerid` int(11) NOT NULL,
  `owner_type` varchar(45) NOT NULL,
  `address_name` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `postcode` varchar(15) NOT NULL,
  `street` varchar(45) NOT NULL,
  `street2` varchar(200) DEFAULT NULL,
  `house_number` varchar(10) NOT NULL,
  `comments` varchar(300) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  `created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`ownerid`,`owner_type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbladdresses`
--

LOCK TABLES `tbladdresses` WRITE;
/*!40000 ALTER TABLE `tbladdresses` DISABLE KEYS */;
INSERT INTO `tbladdresses` VALUES (1,'company','Hauptfiliale','Deutschland','Hannover','30419','Fischteichweg',NULL,'22',NULL,1,'2018-06-10 11:01:58.940668','2018-06-10 11:02:31.431829');
/*!40000 ALTER TABLE `tbladdresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcompanies`
--

DROP TABLE IF EXISTS `tblcompanies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcompanies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) NOT NULL,
  `comments` text,
  `status` smallint(6) NOT NULL DEFAULT '1',
  `version` int(11) NOT NULL DEFAULT '1',
  `created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcompanies`
--

LOCK TABLES `tblcompanies` WRITE;
/*!40000 ALTER TABLE `tblcompanies` DISABLE KEYS */;
INSERT INTO `tblcompanies` VALUES (1,'Test Company',NULL,1,1,'2018-06-04 19:31:58.672344','2018-06-04 19:31:58.672344'),(2,'Company 1',NULL,2,1,'2018-06-08 19:10:21.325301','2018-06-08 19:10:21.325301'),(3,'Company 2',NULL,3,1,'2018-06-08 19:10:21.328295','2018-06-08 19:10:21.328295'),(4,'Company 3',NULL,0,1,'2018-06-09 10:13:19.446634','2018-06-10 10:16:34.473327');
/*!40000 ALTER TABLE `tblcompanies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblemails`
--

DROP TABLE IF EXISTS `tblemails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblemails` (
  `ownerid` int(11) NOT NULL,
  `owner_type` varchar(45) NOT NULL,
  `email_name` varchar(45) NOT NULL,
  `emailaddress` varchar(50) NOT NULL,
  `comments` varchar(300) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  `created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`ownerid`,`owner_type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblemails`
--

LOCK TABLES `tblemails` WRITE;
/*!40000 ALTER TABLE `tblemails` DISABLE KEYS */;
INSERT INTO `tblemails` VALUES (1,'company','Info','aaaa@em.de',NULL,1,'2018-06-10 11:05:42.997236','2018-06-10 11:05:42.997236');
/*!40000 ALTER TABLE `tblemails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblprojects`
--

DROP TABLE IF EXISTS `tblprojects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblprojects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` int(11) NOT NULL,
  `project_name` varchar(200) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  `status` smallint(6) GENERATED ALWAYS AS (1) VIRTUAL,
  `created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  KEY `FK_PROJECTS_ACCOUNT_idx` (`account`),
  CONSTRAINT `FK_PROJECTS_ACCOUNT` FOREIGN KEY (`account`) REFERENCES `tblcompanies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblprojects`
--

LOCK TABLES `tblprojects` WRITE;
/*!40000 ALTER TABLE `tblprojects` DISABLE KEYS */;
INSERT INTO `tblprojects` (`id`, `account`, `project_name`, `version`, `created`, `updated`) VALUES (1,1,'Projeckt 1',1,'2018-06-04 19:36:44.310401','2018-06-04 19:36:44.310401'),(2,1,'Projeckt 2',1,'2018-06-04 19:36:44.310863','2018-06-04 19:36:44.310863'),(3,1,'Projeckt 3',1,'2018-06-04 19:36:44.310863','2018-06-04 19:36:44.310863');
/*!40000 ALTER TABLE `tblprojects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltelephones`
--

DROP TABLE IF EXISTS `tbltelephones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltelephones` (
  `ownerid` int(11) NOT NULL,
  `owner_type` varchar(45) NOT NULL,
  `telephone_name` varchar(45) NOT NULL,
  `country_code` varchar(45) NOT NULL,
  `city_code` varchar(45) NOT NULL,
  `subscriber` varchar(15) NOT NULL,
  `comments` varchar(300) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  `created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`ownerid`,`owner_type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltelephones`
--

LOCK TABLES `tbltelephones` WRITE;
/*!40000 ALTER TABLE `tbltelephones` DISABLE KEYS */;
INSERT INTO `tbltelephones` VALUES (1,'company','Empfang','49','115','123456',NULL,1,'2018-06-10 11:04:01.293072','2018-06-10 11:04:01.293072');
/*!40000 ALTER TABLE `tbltelephones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltoolbar`
--

DROP TABLE IF EXISTS `tbltoolbar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltoolbar` (
  `id` varchar(40) NOT NULL,
  `parent` varchar(40) NOT NULL DEFAULT '-',
  `label` varchar(45) NOT NULL DEFAULT '-',
  `url` varchar(100) NOT NULL,
  `image` varchar(45) NOT NULL,
  `status` smallint(6) NOT NULL DEFAULT '1',
  `type` varchar(45) NOT NULL,
  `pos` smallint(6) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltoolbar`
--

LOCK TABLES `tbltoolbar` WRITE;
/*!40000 ALTER TABLE `tbltoolbar` DISABLE KEYS */;
INSERT INTO `tbltoolbar` VALUES ('menu.basicdata','','menu.basicdata','/basics/','glyphicon glyphicon-list',1,'top',10),('menu.basicdata.companies','menu.basicdata','menu.basicdata.companies','/basics/companies/index','glyphicon glyphicon-user',1,'left',5),('menu.mydashboard','','menu.mydashboard','/my/dashboard','glyphicon glyphicon-home',1,'top',5),('menu.mydashboard.dashboard','menu.mydashboard','menu.mydashboard','/my/dashboard','glyphicon glyphicon-home',1,'left',5),('menu.user','','menu.user','/user/changepassword','glyphicon glyphicon-user',1,'top',15),('menu.user.changepassword','menu.user','menu.user.changepassword','/user/changepassword','glyphicon glyphicon-user',1,'left',5);
/*!40000 ALTER TABLE `tbltoolbar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblusers`
--

DROP TABLE IF EXISTS `tblusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblusers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `hash_password` varchar(45) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  `status` smallint(6) GENERATED ALWAYS AS (1) VIRTUAL,
  `created` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  KEY `FK_USERS_ACCOUNT_idx` (`company`),
  CONSTRAINT `FK_USERS_COMPANY` FOREIGN KEY (`company`) REFERENCES `tblcompanies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblusers`
--

LOCK TABLES `tblusers` WRITE;
/*!40000 ALTER TABLE `tblusers` DISABLE KEYS */;
INSERT INTO `tblusers` (`id`, `company`, `username`, `hash_password`, `version`, `created`, `updated`) VALUES (1,1,'administrator','admin',1,'2018-06-04 07:51:54.890891','2018-06-04 19:31:38.938720'),(2,1,'user','user',1,'2018-06-04 07:52:09.815891','2018-06-04 19:31:38.945944'),(3,1,'u2','u2',1,'2018-06-04 07:52:09.815891','2018-06-04 19:31:38.946434');
/*!40000 ALTER TABLE `tblusers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-10 22:57:45
