-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: vidmins
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

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
-- Table structure for table `directory`
--

DROP TABLE IF EXISTS `directory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(140) DEFAULT '',
  `description` text,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `directory_user` (`userId`),
  CONSTRAINT `directory_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directory`
--

LOCK TABLES `directory` WRITE;
/*!40000 ALTER TABLE `directory` DISABLE KEYS */;
/*!40000 ALTER TABLE `directory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directory_videos`
--

DROP TABLE IF EXISTS `directory_videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directory_videos` (
  `directoryId` int(11) NOT NULL,
  `videoId` int(11) NOT NULL,
  PRIMARY KEY (`directoryId`,`videoId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directory_videos`
--

LOCK TABLES `directory_videos` WRITE;
/*!40000 ALTER TABLE `directory_videos` DISABLE KEYS */;
/*!40000 ALTER TABLE `directory_videos` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (1,'MarkI','Here goes no wise man.',0,10000,'2019-01-28 10:43:21',3,2),(4,'Introductions','Covers syllabus, calendar, class projects, introductions.',0,120,'2019-01-29 09:15:49',3,3),(5,'Project Assigned','Description of project 1',0,120,'2019-01-29 09:15:49',3,2),(6,'Sesquicentennial','Ye ole 1976ers.',321,-1,'2019-01-29 10:06:00',3,3),(7,'Project discussion','Dsiscussion about project',120,600,'2019-01-29 10:06:00',3,2),(8,'words','some text',0,0,'2019-02-12 10:57:35',3,2),(9,'nt','nt',0,0,'2019-02-12 11:00:47',2,3),(10,'true','story',0,0,'2019-02-12 11:14:05',2,3),(11,'test','test',0,0,'2019-02-12 11:18:17',2,3),(12,'asdf','asdf',0,0,'2019-02-12 11:21:55',3,2),(13,'tons of notes','literally a great and vast tonnage',0,0,'2019-02-12 11:26:13',3,2),(14,'Debug JSP','Debugging Week1 User display exercise.',0,0,'2019-02-12 11:53:06',3,19),(15,'c:forEach','Debugging forEach JSTL construct to loop over elements.',0,-1,'2019-02-12 12:01:11',3,19),(16,'hgfd','hgfd',0,0,'2019-02-12 12:11:36',3,13),(17,'Leave it working','Things are working a little better now,; need to submit notes without reloading video.',0,0,'2019-02-12 12:14:02',3,12),(18,'Log4J Stuff','Actually just got some JS working...',0,0,'2019-02-12 12:36:00',3,11),(19,'stuf','more stuf',0,0,'2019-02-12 12:40:07',3,8),(20,'total stuf','Lots and lots',0,0,'2019-02-12 12:43:55',3,11),(21,'noting','notes',0,0,'2019-02-12 12:51:56',3,10),(22,'Noticias','Noticias means \"news\" in Spanish.',0,0,'2019-02-12 12:53:56',3,11),(23,'stuff','again',0,0,'2019-02-12 12:55:59',3,11),(24,'test13','testttttttt',0,0,'2019-02-12 12:57:31',3,11),(25,'222222','222222',0,0,'2019-02-12 13:00:12',3,10),(26,'extra','extra',0,0,'2019-02-12 13:02:24',3,11),(27,'deps','mvn deps',292,0,'2019-02-12 13:13:01',3,11),(28,'totally','week 2 info',130,130,'2019-02-12 13:15:35',3,10),(29,'zxz','xzx',321,321,'2019-02-12 13:17:53',3,11),(30,'mas','mas',398,398,'2019-02-12 13:18:43',3,11),(31,'git','stuff',149,149,'2019-02-12 13:19:44',3,12),(32,'UserData','look at the code',276,276,'2019-02-12 13:52:39',3,11),(33,'JUnit Overview','unity testing',81,81,'2019-02-12 13:58:23',3,16),(34,'fdsa','fdsa',0,0,'2019-02-12 14:01:29',3,18),(35,'not','not',0,-1,'2019-02-12 14:27:40',3,12),(36,'asdf','asdf asfdasd fasdfaaf asfda sd as as fdasdfa',0,-1,'2019-02-12 14:34:26',3,12),(37,'EntJ','Testing test',0,-1,'2019-02-12 14:48:15',3,16),(38,'goodies','goodnesses',0,-1,'2019-02-12 15:02:37',3,11),(39,'6543','6543',0,NULL,'2019-02-12 16:38:35',3,12),(40,'asdf','fadsa',0,NULL,'2019-02-12 17:09:09',3,11),(41,'qwerty','oiuyp',0,NULL,'2019-02-14 13:28:06',3,23),(42,'Time Management','Expert',495,NULL,'2019-02-14 13:28:33',3,23),(43,'asdg','her',0,NULL,'2019-02-14 15:21:14',3,17),(44,'All stuf','So much stuf',0,NULL,'2019-02-14 15:30:08',3,8),(45,'http://localhost:8080/loadClient?videoId=12','http://localhost:8080/loadClient?videoId=10',0,NULL,'2019-02-14 15:30:52',3,8),(46,'asdgag','asdffffffffffffffffffff fffffffffffffff  ffffffffffffffffffff fffffffffffffffff fffffffffffff fffffffff fffffffffffff ffffffffffff ffffffffff fffffffffffffff fffffffff ffffffffffffff ffffffffffffffffffff',0,-1,'2019-02-14 15:31:35',3,15),(47,'JUnit Overview','the stuf you gotta know stuf',0,-1,'2019-02-14 15:46:55',3,16),(48,'Junit 5 User Guide','Skip section 2, you won\'t need it this semester.',44,NULL,'2019-02-14 15:48:04',3,16),(49,'JUnit Overview','The stuff you got to know.',0,NULL,'2019-02-16 11:45:09',3,16),(50,'asdgfeeee','here',0,-1,'2019-02-16 12:02:40',3,17),(51,'asdgf3','hereby',0,-1,'2019-02-16 12:06:16',3,17),(52,'qwerty33','oiuyp',0,-1,'2019-02-16 12:43:59',3,17),(53,'djd','fdgjdfgj',0,NULL,'2019-02-16 12:47:52',3,17),(54,'new note','newer newer newer',0,-1,'2019-02-16 13:02:37',3,19),(55,'new note','newer',0,-1,'2019-02-16 13:03:57',3,21),(56,'working w/intellij data sources','Setup and interact with database through intellij.',0,-1,'2019-02-16 13:05:01',3,21),(57,'Week2 Notes','Lecture notes from github.',346,NULL,'2019-02-17 10:21:28',3,12),(58,'Directory Marking','Use directories effectively in IntelliJ IDEA',74,NULL,'2019-02-17 10:28:16',3,15),(59,'More JUnit','Using assertions',0,NULL,'2019-02-18 17:04:37',3,16),(60,'Writing Tests','Add lots of test',154,NULL,'2019-02-18 18:38:29',3,16),(61,'Real','Actual notes',0,-1,'2019-02-18 19:00:55',3,12),(62,'real','sdfg',0,-1,'2019-02-18 19:54:25',3,13);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role` enum('guest','oauth','local','admin','super') DEFAULT 'guest',
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(80) NOT NULL,
  `objectId` int(11) NOT NULL,
  `objectType` varchar(22) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'java',3,'video');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
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
  `userName` varchar(100) DEFAULT NULL,
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
INSERT INTO `user_videos` VALUES (3,2),(3,3),(4,4),(4,5),(4,6),(4,7),(3,8),(3,9),(3,10),(3,11),(3,12),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),(3,21),(3,22),(3,23),(3,24),(3,25),(3,26),(3,27),(3,28),(3,29),(3,30),(3,31),(3,32),(3,33),(3,34),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),(4,8),(4,9),(4,10),(4,11),(4,12),(4,13),(4,14),(4,15),(4,16),(4,17),(4,18),(4,19),(4,20),(4,21),(4,22),(4,23),(4,24),(4,25),(4,26),(4,27),(4,28),(4,29),(4,30),(4,31),(4,32),(4,33),(4,34),(4,35),(4,36),(4,37),(4,38),(4,39),(4,40),(4,41),(4,42),(4,43),(4,44),(4,45),(4,46),(4,47),(4,48),(4,49),(4,50),(4,51),(4,52),(4,53),(4,54),(4,55),(4,56),(4,57),(4,58),(4,59),(4,60),(4,61),(4,62),(4,63),(4,64),(4,65),(4,66),(4,67),(4,68),(4,69),(4,70),(4,71),(4,72),(4,73),(4,74),(4,75),(4,76),(4,77),(4,78),(4,79),(4,80),(4,81),(4,82),(4,84),(4,85),(4,86),(4,87),(4,88),(4,89),(4,90),(4,91),(4,92),(4,93),(4,94),(4,95),(4,96),(4,97),(4,98),(4,99),(4,100),(4,101),(4,102),(4,103),(4,104),(4,105),(4,106),(4,107),(4,108),(4,109),(4,110),(4,111),(4,112),(4,113),(4,114),(4,115),(4,116),(4,117),(4,118),(4,119),(4,120),(4,121),(4,122),(4,123),(4,124),(4,125),(4,126),(4,127),(4,128),(4,129),(4,130),(4,131),(4,132),(4,133),(4,134),(4,135),(4,136),(4,137),(4,138),(4,139),(4,140),(4,141),(4,142),(4,143),(4,144),(4,145),(4,146),(4,147),(4,148),(4,149),(4,150),(4,151);
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
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (2,'4HzWKwExaeo','2019-01-28 10:26:38','Week1Act5',-1),(3,'dF0NWtxRXsg','2019-01-28 16:31:59','PHP Week 1',3842),(4,'4KGGkytxlZM','2019-01-29 16:17:37','Welcome',-1),(5,'LUJ17rugTKs','2019-01-29 16:17:37','Individual Project Overview',-1),(6,'TniysHGAKtE','2019-01-29 16:17:37','Team Project Overview',-1),(7,'0ZR5adX7EWo','2019-01-29 16:17:37','Professional Development Activity Overview',-1),(8,'d6ySoD13XFw','2019-01-30 11:02:28','Git Conflicts',-1),(9,'wVzJ-c_DBSA','2019-01-30 11:02:28','Markdown',-1),(10,'eyMhGVBsO-Q','2019-01-30 11:02:28','Welcome to Week 2',-1),(11,'iETVNC0uLqQ','2019-01-30 11:02:28','Log4J Overview',-1),(12,'GbKr2of3g20','2019-01-30 11:02:28','Git Overview',-1),(13,'mTne97I3MYw','2019-01-30 11:02:28','Student Repo and Week 2 Act 3 Walkthrough',-1),(14,'ie4ydeFQTtg','2019-01-30 11:02:28','Creating your Individual Project in IntelliJ and GitHub (Week 2 Act 4)',-1),(15,'9po-VRmZ6mQ','2019-01-30 11:02:28','Adding Java and Test Directories to your Indie Project',-1),(16,'edGPz776YGs','2019-01-30 11:02:28','jUnit Overview',-1),(17,'UPhNsAojaR0','2019-01-30 11:02:28','jUnit Demo',-1),(18,'PbOAH5iRzVQ','2019-01-30 11:02:28','Week 2 Exercise Overview',-1),(19,'9z7USj0cp_w','2019-01-30 11:02:28','Debugging in a JSP',-1),(20,'7aGOCrW3dNQ','2019-01-30 11:02:28','IntelliJ Plugins - including Javadoc Generator',-1),(21,'cLtHrMJKfeY','2019-01-30 11:02:28','IntelliJ Database View',-1),(22,'ZbwaWZ9b3Tg','2019-01-30 11:02:28','Maven Overview',-1),(23,'KuflISsAmpc','2019-01-30 11:02:28','Week 1 Overview',-1),(24,'KOL4QNmHCJA','2019-01-30 11:02:28','Activity 4 IntelliJ Config Walkthrough',-1),(25,'JCEaIQaCDgs','2019-01-30 11:02:28','Working With Assignments from GitHub',-1),(26,'4HzWKwExaeo','2019-01-30 11:02:28','Activity 5 First Maven Web App Overview',-1),(27,'HeOpTFmUCQo','2019-01-30 11:02:28','Activity 6 The Debugger',-1),(28,'xpVPr3sWMEs','2019-01-30 11:02:28','Exercise 1 Overview',-1),(29,'4KGGkytxlZM','2019-01-30 11:02:28','Welcome',-1),(30,'LUJ17rugTKs','2019-01-30 11:02:28','Individual Project Overview',-1),(31,'TniysHGAKtE','2019-01-30 11:02:28','Team Project Overview',-1),(32,'0ZR5adX7EWo','2019-01-30 11:02:28','Professional Development Activity Overview',-1),(33,'uSlO4MeyXYM','2019-01-30 11:02:28','Using Slack in this Course',-1),(34,'9qSU8kFix5g','2019-01-30 11:02:28','Course Set-up',-1),(35,'PhGyrgTXXhw','2019-02-14 15:09:00','Week 4 Overview',-1),(36,'EshxpNqFA2s','2019-02-14 15:09:00','DAO Overview',-1),(37,'SuENQ5oOZLg','2019-02-14 15:09:00','Hibernate Overview',-1),(38,'1fj5wnyCsnw','2019-02-14 15:09:00','Hibernate Demo Part 1 - Set up and getAllUsers',-1),(39,'5dzHzqSePEo','2019-02-14 15:09:00','Hibernate Demo Part 2 - More Methods and Unit Testing',-1),(40,'7Z_s1Ypt3vk','2019-02-14 15:09:00','Hibernate What Can Go Wrong?!?',-1),(41,'2Y2NprIGErQ','2019-02-14 15:09:00','Hibernate Demo Part 3 - Create, Update, Delete and a Test DB',-1),(42,'w8nijUjVI0M','2019-02-14 15:09:00','Hibernate Demo Part 4 - Logging',-1),(43,'-_aATCJ1Yq4','2019-02-14 15:09:00','Week 3 Overview',-1),(44,'MyOyT3koyWE','2019-02-14 15:09:00','Custom Tag Library Overview',-1),(45,'DzYGUn92YfU','2019-02-14 15:09:00','Custom Tag Library Demo - Week 3 Act 1',-1),(46,'WKxut3MYWpY','2019-02-14 15:09:00','Week 2 Exercise ListTest - One Possible Solution',-1),(47,'fJo2zsRNFz4','2019-02-14 15:09:00','Week 2 Exercise Inverse Captcha - One Possible Solution',-1),(48,'zsGs7K8zxj0','2019-02-18 17:16:59','Week 9 Overview',-1),(49,'Ld21wS1BY9s','2019-02-18 17:16:59','REST Web Services Overview',-1),(50,'EC4NGcWA4UY','2019-02-18 17:16:59','Act 1 Walkthrough',-1),(51,'PWGd_E7Ty8U','2019-02-18 17:16:59','Act 2 Walkthrough',-1),(52,'MZzjIY_dB20','2019-02-18 17:16:59','Act 3 Walkthrough',-1),(53,'wUrlJm_WDNI','2019-02-18 17:16:59','Act 4 Intro and Exercise Intro',-1),(54,'rizqK91UtQc','2019-02-18 17:16:59','Week 8 Overview',-1),(55,'_VPFlFQOXV8','2019-02-18 17:16:59','Web Services and SOAP Overview',-1),(56,'k0-4keXGRtM','2019-02-18 17:16:59','Act 1 Walkthrough',-1),(57,'ifMhRK3wLqI','2019-02-18 17:16:59','Act 2 Walkthrough',-1),(58,'W5Y0Mr-cKjI','2019-02-18 17:16:59','Act 3 Intro',-1),(59,'FACzs3wKNpI','2019-02-18 17:16:59','Week 7 Overview',-1),(60,'F5pc8TLs5h8','2019-02-18 17:16:59','Auth Part 1 - Overview and Set up',-1),(61,'nJzBJ4aMPgE','2019-02-18 17:16:59','Auth Part 2 - Tomcat, JSPs, and XML',-1),(62,'PRxuzxu5kjw','2019-02-18 17:16:59','Week 6 Overview',-1),(63,'LWI_Xi6encU','2019-02-18 17:16:59','AWS Part 2: EC2',-1),(64,'95gd-T4QA74','2019-02-18 17:16:59','AWS Part 3: Elastic IP',-1),(65,'5tca3yR4K7g','2019-02-18 17:16:59','AWS Part 4: SSH, Tomcat, MySql',-1),(66,'EZOFCr8AbX4','2019-02-18 17:16:59','AWS Part 5: Remote Access to MySql',-1),(67,'csT0t8ka8sM','2019-02-18 17:16:59','AWS Part 6: Deploy!',-1),(68,'RRS_NHWejmc','2019-02-18 17:16:59','Comparing Hibernate Entities with .equals',-1),(69,'j19uBRHDH3w','2019-02-18 17:16:59','Generic Hibernate Dao',-1),(70,'nQoFhQpbBuk','2019-02-18 17:16:59','Week 5 Overview',-1),(71,'nn2Nmnn-9XQ','2019-02-18 17:16:59','Hibernate One-To-Many Part 1 - Orders Table, Config, Entities, Dao',-1),(72,'-XfAT7y2A6s','2019-02-18 17:16:59','Hibernate One-To-Many Part 2 - Unit Testing',-1),(73,'okgs3X28D9U','2019-02-18 17:16:59','Hibernate One-To-Many Part 3 - Displaying Results on the JSP',-1),(74,'uD7bKulczMU','2019-02-18 17:16:59','Hibernate One-To-Many - What Could Go Wrong?!?',-1),(75,'htPnL3_DPL8','2019-02-18 17:16:59','Week 4 Exercise Walkthrough',-1),(76,'PhGyrgTXXhw','2019-02-18 17:16:59','Week 4 Overview',-1),(77,'EshxpNqFA2s','2019-02-18 17:16:59','DAO Overview',-1),(78,'SuENQ5oOZLg','2019-02-18 17:16:59','Hibernate Overview',-1),(79,'1fj5wnyCsnw','2019-02-18 17:16:59','Hibernate Demo Part 1 - Set up and getAllUsers',-1),(80,'5dzHzqSePEo','2019-02-18 17:16:59','Hibernate Demo Part 2 - More Methods and Unit Testing',-1),(81,'7Z_s1Ypt3vk','2019-02-18 17:16:59','Hibernate What Can Go Wrong?!?',-1),(82,'2Y2NprIGErQ','2019-02-18 17:16:59','Hibernate Demo Part 3 - Create, Update, Delete and a Test DB',-1),(84,'0ZR5adX7EWo','2019-02-18 18:05:27','Professional Development Activity Overview',-1),(85,'1fj5wnyCsnw','2019-02-18 18:05:27','Hibernate Demo Part 1 - Set up and getAllUsers',-1),(86,'2Y2NprIGErQ','2019-02-18 18:05:27','Hibernate Demo Part 3 - Create, Update, Delete and a Test DB',-1),(87,'4HzWKwExaeo','2019-02-18 18:05:27','Activity 5 First Maven Web App Overview',-1),(88,'4KGGkytxlZM','2019-02-18 18:05:27','Welcome',-1),(89,'5dzHzqSePEo','2019-02-18 18:05:27','Hibernate Demo Part 2 - More Methods and Unit Testing',-1),(90,'5tca3yR4K7g','2019-02-18 18:05:27','AWS Part 4: SSH, Tomcat, MySql',-1),(91,'7aGOCrW3dNQ','2019-02-18 18:05:27','IntelliJ Plugins - including Javadoc Generator',-1),(92,'7Z_s1Ypt3vk','2019-02-18 18:05:27','Hibernate What Can Go Wrong?!?',-1),(93,'95gd-T4QA74','2019-02-18 18:05:27','AWS Part 3: Elastic IP',-1),(94,'9po-VRmZ6mQ','2019-02-18 18:05:27','Adding Java and Test Directories to your Indie Project',-1),(95,'9z7USj0cp_w','2019-02-18 18:05:27','Debugging in a JSP',-1),(96,'-_aATCJ1Yq4','2019-02-18 18:05:27','Week 3 Overview',-1),(97,'cLtHrMJKfeY','2019-02-18 18:05:27','IntelliJ Database View',-1),(98,'csT0t8ka8sM','2019-02-18 18:05:27','AWS Part 6: Deploy!',-1),(99,'d6ySoD13XFw','2019-02-18 18:05:27','Git Conflicts',-1),(100,'DzYGUn92YfU','2019-02-18 18:05:27','Custom Tag Library Demo - Week 3 Act 1',-1),(101,'EC4NGcWA4UY','2019-02-18 18:05:27','Act 1 Walkthrough',-1),(102,'edGPz776YGs','2019-02-18 18:05:27','jUnit Overview',-1),(103,'EshxpNqFA2s','2019-02-18 18:05:27','DAO Overview',-1),(104,'eyMhGVBsO-Q','2019-02-18 18:05:27','Welcome to Week 2',-1),(105,'EZOFCr8AbX4','2019-02-18 18:05:27','AWS Part 5: Remote Access to MySql',-1),(106,'F5pc8TLs5h8','2019-02-18 18:05:27','Auth Part 1 - Overview and Set up',-1),(107,'FACzs3wKNpI','2019-02-18 18:05:27','Week 7 Overview',-1),(108,'fJo2zsRNFz4','2019-02-18 18:05:27','Week 2 Exercise Inverse Captcha - One Possible Solution',-1),(109,'GbKr2of3g20','2019-02-18 18:05:27','Git Overview',-1),(110,'HeOpTFmUCQo','2019-02-18 18:05:27','Activity 6 The Debugger',-1),(111,'w8nijUjVI0M','2019-02-18 18:05:27','Hibernate Demo Part 4 - Logging',-1),(112,'htPnL3_DPL8','2019-02-18 18:05:27','Week 4 Exercise Walkthrough',-1),(113,'ie4ydeFQTtg','2019-02-18 18:05:27','Creating your Individual Project in IntelliJ and GitHub (Week 2 Act 4)',-1),(114,'iETVNC0uLqQ','2019-02-18 18:05:27','Log4J Overview',-1),(115,'ifMhRK3wLqI','2019-02-18 18:05:27','Act 2 Walkthrough',-1),(116,'j19uBRHDH3w','2019-02-18 18:05:27','Generic Hibernate Dao',-1),(117,'JCEaIQaCDgs','2019-02-18 18:05:27','Working With Assignments from GitHub',-1),(118,'k0-4keXGRtM','2019-02-18 18:05:27','Act 1 Walkthrough',-1),(119,'KOL4QNmHCJA','2019-02-18 18:05:27','Activity 4 IntelliJ Config Walkthrough',-1),(120,'KuflISsAmpc','2019-02-18 18:05:27','Week 1 Overview',-1),(121,'Ld21wS1BY9s','2019-02-18 18:05:27','REST Web Services Overview',-1),(122,'LUJ17rugTKs','2019-02-18 18:05:27','Individual Project Overview',-1),(123,'LWI_Xi6encU','2019-02-18 18:05:27','AWS Part 2: EC2',-1),(124,'mTne97I3MYw','2019-02-18 18:05:27','Student Repo and Week 2 Act 3 Walkthrough',-1),(125,'MyOyT3koyWE','2019-02-18 18:05:27','Custom Tag Library Overview',-1),(126,'MZzjIY_dB20','2019-02-18 18:05:27','Act 3 Walkthrough',-1),(127,'nJzBJ4aMPgE','2019-02-18 18:05:27','Auth Part 2 - Tomcat, JSPs, and XML',-1),(128,'nn2Nmnn-9XQ','2019-02-18 18:05:27','Hibernate One-To-Many Part 1 - Orders Table, Config, Entities, Dao',-1),(129,'nQoFhQpbBuk','2019-02-18 18:05:27','Week 5 Overview',-1),(130,'okgs3X28D9U','2019-02-18 18:05:27','Hibernate One-To-Many Part 3 - Displaying Results on the JSP',-1),(131,'PbOAH5iRzVQ','2019-02-18 18:05:27','Week 2 Exercise Overview',-1),(132,'PhGyrgTXXhw','2019-02-18 18:05:27','Week 4 Overview',-1),(133,'PRxuzxu5kjw','2019-02-18 18:05:27','Week 6 Overview',-1),(134,'PWGd_E7Ty8U','2019-02-18 18:05:27','Act 2 Walkthrough',-1),(135,'rizqK91UtQc','2019-02-18 18:05:27','Week 8 Overview',-1),(136,'RRS_NHWejmc','2019-02-18 18:05:27','Comparing Hibernate Entities with .equals',-1),(137,'SuENQ5oOZLg','2019-02-18 18:05:27','Hibernate Overview',-1),(138,'TniysHGAKtE','2019-02-18 18:05:27','Team Project Overview',-1),(139,'uD7bKulczMU','2019-02-18 18:05:27','Hibernate One-To-Many - What Could Go Wrong?!?',-1),(140,'UPhNsAojaR0','2019-02-18 18:05:27','jUnit Demo',-1),(141,'uSlO4MeyXYM','2019-02-18 18:05:27','Using Slack in this Course',-1),(142,'_VPFlFQOXV8','2019-02-18 18:05:27','Web Services and SOAP Overview',-1),(143,'W5Y0Mr-cKjI','2019-02-18 18:05:27','Act 3 Intro',-1),(144,'w8nijUjVI0M','2019-02-18 18:05:27','Hibernate Demo Part 4 - Logging',-1),(145,'WKxut3MYWpY','2019-02-18 18:05:27','Week 2 Exercise ListTest - One Possible Solution',-1),(146,'wUrlJm_WDNI','2019-02-18 18:05:27','Act 4 Intro and Exercise Intro',-1),(147,'wVzJ-c_DBSA','2019-02-18 18:05:27','Markdown',-1),(148,'-XfAT7y2A6s','2019-02-18 18:05:27','Hibernate One-To-Many Part 2 - Unit Testing',-1),(149,'xpVPr3sWMEs','2019-02-18 18:05:27','Exercise 1 Overview',-1),(150,'ZbwaWZ9b3Tg','2019-02-18 18:05:27','Maven Overview',-1),(151,'zsGs7K8zxj0','2019-02-18 18:05:27','Week 9 Overview',-1);
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

-- Dump completed on 2019-02-21 14:12:58
