-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: electrogrid
-- ------------------------------------------------------
-- Server version	5.7.31-log

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
-- Table structure for table `billing`
--

DROP TABLE IF EXISTS `billing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billing` (
  `bill_ID` int(11) NOT NULL AUTO_INCREMENT,
  `power_consumption_ID` int(11) NOT NULL,
  `User_name` varchar(45) DEFAULT NULL,
  `NIC` varchar(15) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `month` varchar(30) DEFAULT NULL,
  `monthly_units` int(11) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`bill_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing`
--

LOCK TABLES `billing` WRITE;
/*!40000 ALTER TABLE `billing` DISABLE KEYS */;
INSERT INTO `billing` VALUES (1,4,'Lochana','982645386v','Badulla','April-2022',90,12,1080),(2,5,'Lihini','989835256v','Kandy','April-2022',100,10,1000),(3,6,'Lihini','989835256v','Kandy','March-2022',120,11,1320),(4,7,'Raeesul','989876556v','Anuradhapura','February-2022',100,18,1800),(5,7,'Raeesul','989876556v','Anuradhapura','February-2021',100,15,1500),(6,7,'Raeesul','989876556v','Anuradhapura','February-2021',100,15,2024);
/*!40000 ALTER TABLE `billing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint` (
  `compID` int(11) NOT NULL,
  `userID` varchar(5) NOT NULL,
  `des` varchar(150) NOT NULL,
  `answer` varchar(30) NOT NULL,
  `updatedDate` varchar(30) NOT NULL,
  `compDate` varchar(30) NOT NULL,
  `cstatus` varchar(10) NOT NULL,
  PRIMARY KEY (`compID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
INSERT INTO `complaint` VALUES (1,'C0002','Power Failure','Answered','25-April-2022','22-April-2022','INACTIVE'),(2,'C0001','Issue Regarding Consumption','Answered','15-May-2022','10-May-2022','INACTIVE');
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumption`
--

DROP TABLE IF EXISTS `consumption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumption` (
  `conID` int(11) NOT NULL,
  `userID` varchar(5) NOT NULL,
  `month` varchar(30) NOT NULL,
  `preMonReading` int(11) NOT NULL,
  `curMonReading` int(11) NOT NULL,
  `conUnits` int(11) NOT NULL,
  PRIMARY KEY (`conID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumption`
--

LOCK TABLES `consumption` WRITE;
/*!40000 ALTER TABLE `consumption` DISABLE KEYS */;
INSERT INTO `consumption` VALUES (4,'C0001','April-2022',0,92,92),(5,'C0002','April-2022',0,100,100),(6,'C0002','April-2022',100,210,110);
/*!40000 ALTER TABLE `consumption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monitoring`
--

DROP TABLE IF EXISTS `monitoring`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monitoring` (
  `monitoring_ID` int(11) NOT NULL AUTO_INCREMENT,
  `pay_ID` int(11) NOT NULL,
  `power_consumption_ID` int(11) NOT NULL,
  `month` varchar(45) DEFAULT NULL,
  `units` int(11) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`monitoring_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monitoring`
--

LOCK TABLES `monitoring` WRITE;
/*!40000 ALTER TABLE `monitoring` DISABLE KEYS */;
INSERT INTO `monitoring` VALUES (2,1,5,'April-2022',100,224,'No comment'),(3,2,6,'March-2022',110,0,'No comment'),(4,6,7,'Jul-2022',110,0,'No comment');
/*!40000 ALTER TABLE `monitoring` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `paymentID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` varchar(5) NOT NULL,
  `billID` int(11) NOT NULL,
  `total_amount` double NOT NULL,
  `paid_amount` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `month` varchar(45) NOT NULL,
  `payment_type` varchar(45) DEFAULT NULL,
  `card_no` varchar(45) DEFAULT NULL,
  `paid_Date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`paymentID`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'C0001',4,1800,800,-1000,'February-2022','credit','354349','2022-04-31'),(2,'C0002',5,2000,2000,0,'April-2022','debit','354649','2022-04-31'),(3,'C0002',6,2024,1000,-1024,'March-2022','credit','356749','2022-04-31');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` varchar(5) NOT NULL,
  `name` varchar(45) NOT NULL,
  `dob` varchar(30) NOT NULL,
  `nicNo` varchar(15) NOT NULL,
  `phoneNo` varchar(20) NOT NULL,
  `email` varchar(60) NOT NULL,
  `address` varchar(60) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('C0001','Lochana','12-07-1998','982645386v','0711485236','lochana@gmail.com','Badulla','lochana1234'),('C0002','Lihini','04-06-1998','989835256v','0717520534','lihini@gmail.com','Kandy','lihini1234'),('C0003','Raeesul','25-03-1998','988502639v','0776328550','raeesul@gmail.com','Kalutara','raeesul1234'),('C0010','Nilu','12-03-1997','964533735V','07345375467','nilu@gmail.com','kotugodella','password');
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

-- Dump completed on 2022-05-15 19:00:27
