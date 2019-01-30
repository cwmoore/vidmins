-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: vidmins
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.16.04.2

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
-- Current Database: `vidmins`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `vidmins` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `vidmins`;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(45) NOT NULL,
  `text` text,
  `start` int(11) DEFAULT NULL,
  `end` int(11) DEFAULT NULL,
  `createDatetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `userId` int(11) NOT NULL,
  `videoId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_1_idx` (`userId`),
  KEY `fk_note_video_1_idx` (`videoId`),
  CONSTRAINT `fk_note_user_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_note_video_1` FOREIGN KEY (`videoId`) REFERENCES `video` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (1,'MarkI','Here goes no wise man.',0,10000,'2019-01-28 10:43:21',3,2),(4,'Introductions','Covers syllabus, calendar, class projects, introductions.',0,120,'2019-01-29 09:15:49',3,3),(5,'Project Assigned','Description of project 1',0,120,'2019-01-29 09:15:49',3,2),(6,'Sesquicentent','Ye ole 1976ers.',321,500,'2019-01-29 10:06:00',3,3),(7,'Project discussion','Dsiscussion about project',120,600,'2019-01-29 10:06:00',3,2);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temporary_keys`
--

DROP TABLE IF EXISTS `temporary_keys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temporary_keys` (
  `userId` int(11) NOT NULL,
  `keyChars` varchar(1001) NOT NULL,
  `timeout` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`,`keyChars`),
  CONSTRAINT `fk_temporary_keys_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporary_keys`
--

LOCK TABLES `temporary_keys` WRITE;
/*!40000 ALTER TABLE `temporary_keys` DISABLE KEYS */;
INSERT INTO `temporary_keys` VALUES (3,'qwertyuiop','2019-01-29 10:30:55');
/*!40000 ALTER TABLE `temporary_keys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `enc_pass` varchar(1001) DEFAULT NULL,
  `joinDate` datetime DEFAULT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `organization` varchar(100) DEFAULT NULL,
  `introduction` varchar(1001) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'cwmoore','password1','2019-01-28 10:30:03','Curtis','Moore','cwmoore@madisoncollege.edu',NULL,NULL,NULL,'1980-01-28'),(4,'cmoore','password2','2019-01-29 16:20:50','Curt','Moore','curtis.mo@gmail.com',NULL,NULL,NULL,'1979-01-01');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_videos`
--

DROP TABLE IF EXISTS `user_videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_videos` (
  `userId` int(11) NOT NULL,
  `videoId` int(11) NOT NULL,
  KEY `userId` (`userId`),
  KEY `videoId` (`videoId`),
  CONSTRAINT `user_videos_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `user_videos_ibfk_2` FOREIGN KEY (`videoId`) REFERENCES `video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_videos`
--

LOCK TABLES `user_videos` WRITE;
/*!40000 ALTER TABLE `user_videos` DISABLE KEYS */;
INSERT INTO `user_videos` VALUES (3,2),(3,3),(4,4),(4,5),(4,6),(4,7),(3,8),(3,9),(3,10),(3,11),(3,12),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),(3,21),(3,22),(3,23),(3,24),(3,25),(3,26),(3,27),(3,28),(3,29),(3,30),(3,31),(3,32),(3,33),(3,34);
/*!40000 ALTER TABLE `user_videos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `youTubeId` varchar(100) DEFAULT '',
  `addDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(255) DEFAULT NULL,
  `duration` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (2,'4HzWKwExaeo','2019-01-28 10:26:38','Week1Act5',-1),(3,'dF0NWtxRXsg','2019-01-28 16:31:59','PHP Week 1',3842),(4,'4KGGkytxlZM','2019-01-29 16:17:37','Welcome',-1),(5,'LUJ17rugTKs','2019-01-29 16:17:37','Individual Project Overview',-1),(6,'TniysHGAKtE','2019-01-29 16:17:37','Team Project Overview',-1),(7,'0ZR5adX7EWo','2019-01-29 16:17:37','Professional Development Activity Overview',-1),(8,'d6ySoD13XFw','2019-01-30 11:02:28','Git Conflicts',-1),(9,'wVzJ-c_DBSA','2019-01-30 11:02:28','Markdown',-1),(10,'eyMhGVBsO-Q','2019-01-30 11:02:28','Welcome to Week 2',-1),(11,'iETVNC0uLqQ','2019-01-30 11:02:28','Log4J Overview',-1),(12,'GbKr2of3g20','2019-01-30 11:02:28','Git Overview',-1),(13,'mTne97I3MYw','2019-01-30 11:02:28','Student Repo and Week 2 Act 3 Walkthrough',-1),(14,'ie4ydeFQTtg','2019-01-30 11:02:28','Creating your Individual Project in IntelliJ and GitHub (Week 2 Act 4)',-1),(15,'9po-VRmZ6mQ','2019-01-30 11:02:28','Adding Java and Test Directories to your Indie Project',-1),(16,'edGPz776YGs','2019-01-30 11:02:28','jUnit Overview',-1),(17,'UPhNsAojaR0','2019-01-30 11:02:28','jUnit Demo',-1),(18,'PbOAH5iRzVQ','2019-01-30 11:02:28','Week 2 Exercise Overview',-1),(19,'9z7USj0cp_w','2019-01-30 11:02:28','Debugging in a JSP',-1),(20,'7aGOCrW3dNQ','2019-01-30 11:02:28','IntelliJ Plugins - including Javadoc Generator',-1),(21,'cLtHrMJKfeY','2019-01-30 11:02:28','IntelliJ Database View',-1),(22,'ZbwaWZ9b3Tg','2019-01-30 11:02:28','Maven Overview',-1),(23,'KuflISsAmpc','2019-01-30 11:02:28','Week 1 Overview',-1),(24,'KOL4QNmHCJA','2019-01-30 11:02:28','Activity 4 IntelliJ Config Walkthrough',-1),(25,'JCEaIQaCDgs','2019-01-30 11:02:28','Working With Assignments from GitHub',-1),(26,'4HzWKwExaeo','2019-01-30 11:02:28','Activity 5 First Maven Web App Overview',-1),(27,'HeOpTFmUCQo','2019-01-30 11:02:28','Activity 6 The Debugger',-1),(28,'xpVPr3sWMEs','2019-01-30 11:02:28','Exercise 1 Overview',-1),(29,'4KGGkytxlZM','2019-01-30 11:02:28','Welcome',-1),(30,'LUJ17rugTKs','2019-01-30 11:02:28','Individual Project Overview',-1),(31,'TniysHGAKtE','2019-01-30 11:02:28','Team Project Overview',-1),(32,'0ZR5adX7EWo','2019-01-30 11:02:28','Professional Development Activity Overview',-1),(33,'uSlO4MeyXYM','2019-01-30 11:02:28','Using Slack in this Course',-1),(34,'9qSU8kFix5g','2019-01-30 11:02:28','Course Set-up',-1);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-30 13:36:33
