-- MySQL dump 10.13  Distrib 5.6.23, for osx10.8 (x86_64)
--
-- Host: localhost    Database: ReservationSystem
-- ------------------------------------------------------
-- Server version	5.6.25

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
-- Table structure for table `Coaches`
--

DROP TABLE IF EXISTS `Coaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Coaches` (
  `CoachCode` int(10) unsigned NOT NULL,
  `CostPerKm` decimal(5,2) DEFAULT '0.00',
  PRIMARY KEY (`CoachCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coaches`
--

LOCK TABLES `Coaches` WRITE;
/*!40000 ALTER TABLE `Coaches` DISABLE KEYS */;
INSERT INTO `Coaches` VALUES (101,101.50),(102,136.00),(103,250.50),(104,366.50);
/*!40000 ALTER TABLE `Coaches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrainCoaches`
--

DROP TABLE IF EXISTS `TrainCoaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrainCoaches` (
  `TrainNo` int(10) unsigned DEFAULT NULL,
  `CoachCode` int(10) unsigned DEFAULT NULL,
  `NoOfSeats` int(10) unsigned DEFAULT NULL,
  KEY `TrainNo` (`TrainNo`),
  KEY `CoachCode` (`CoachCode`),
  CONSTRAINT `traincoaches_ibfk_1` FOREIGN KEY (`TrainNo`) REFERENCES `Trains` (`TrainNo`),
  CONSTRAINT `traincoaches_ibfk_2` FOREIGN KEY (`CoachCode`) REFERENCES `Coaches` (`CoachCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrainCoaches`
--

LOCK TABLES `TrainCoaches` WRITE;
/*!40000 ALTER TABLE `TrainCoaches` DISABLE KEYS */;
INSERT INTO `TrainCoaches` VALUES (10001,101,72),(10001,103,72),(10002,102,72),(10002,103,64),(10002,104,54),(10003,102,72),(10003,102,72),(10003,103,64),(10003,102,72),(10003,103,64),(10003,104,54),(10002,102,72),(10002,102,72),(10003,103,64),(10002,103,64),(10002,104,54),(10001,101,72),(10001,101,72),(10001,103,64);
/*!40000 ALTER TABLE `TrainCoaches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrainRouteMaps`
--

DROP TABLE IF EXISTS `TrainRouteMaps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrainRouteMaps` (
  `RouteId` int(10) unsigned NOT NULL,
  `TrainNo` int(10) unsigned NOT NULL,
  `ArrivalTime` time DEFAULT NULL,
  `DepartureTime` time DEFAULT NULL,
  `DurationInMins` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`RouteId`,`TrainNo`),
  KEY `TrainNo` (`TrainNo`),
  CONSTRAINT `trainroutemaps_ibfk_1` FOREIGN KEY (`RouteId`) REFERENCES `Routes` (`RouteId`),
  CONSTRAINT `trainroutemaps_ibfk_2` FOREIGN KEY (`TrainNo`) REFERENCES `Trains` (`TrainNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrainRouteMaps`
--

LOCK TABLES `TrainRouteMaps` WRITE;
/*!40000 ALTER TABLE `TrainRouteMaps` DISABLE KEYS */;
INSERT INTO `TrainRouteMaps` VALUES (101,10001,'07:30:00','13:15:00',345),(102,10002,'20:45:00','10:30:00',NULL),(103,10003,'22:30:00','15:15:00',465);
/*!40000 ALTER TABLE `TrainRouteMaps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trains`
--

DROP TABLE IF EXISTS `Trains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trains` (
  `TrainNo` int(10) unsigned NOT NULL,
  `TrainName` char(50) NOT NULL DEFAULT 'train',
  PRIMARY KEY (`TrainNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trains`
--

LOCK TABLES `Trains` WRITE;
/*!40000 ALTER TABLE `Trains` DISABLE KEYS */;
INSERT INTO `Trains` VALUES (10001,'Kovai Express'),(10002,'Aleppey Express'),(10003,'Kovai Express');
/*!40000 ALTER TABLE `Trains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `UserId` int(10) unsigned NOT NULL,
  `LoginId` int(10) unsigned NOT NULL,
  `LPassword` varchar(20) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookings` (
  `BookingRefNo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `RouteId` int(10) unsigned DEFAULT NULL,
  `TrainNo` int(10) unsigned DEFAULT NULL,
  `CoachCode` int(10) unsigned DEFAULT NULL,
  `DateOfJourney` date NOT NULL,
  `DateOfBooking` date DEFAULT NULL,
  `NoOfTickets` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`BookingRefNo`),
  KEY `RouteId` (`RouteId`),
  KEY `CoachCode` (`CoachCode`),
  KEY `TrainNo` (`TrainNo`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`RouteId`) REFERENCES `TrainRouteMaps` (`RouteId`),
  CONSTRAINT `bookings_ibfk_3` FOREIGN KEY (`CoachCode`) REFERENCES `Coaches` (`CoachCode`),
  CONSTRAINT `bookings_ibfk_4` FOREIGN KEY (`TrainNo`) REFERENCES `TrainRouteMaps` (`TrainNo`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (101,101,10001,101,'2015-09-23','2015-06-13',4),(102,101,10001,103,'2015-10-23','2015-05-01',3),(110,102,10002,102,'2015-08-13','2015-03-21',2),(111,102,10002,102,'2015-08-13','2015-04-11',5),(112,102,10002,104,'2015-10-25','2015-05-21',2),(113,101,10001,103,'2015-02-22','2014-11-23',8);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `routes` (
  `RouteId` int(10) unsigned NOT NULL,
  `OriginStationId` int(10) unsigned NOT NULL,
  `DestinationStationId` int(10) unsigned NOT NULL,
  `DistanceInKms` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`RouteId`),
  KEY `OriginStationId` (`OriginStationId`),
  KEY `DestinationStationId` (`DestinationStationId`),
  CONSTRAINT `routes_ibfk_1` FOREIGN KEY (`OriginStationId`) REFERENCES `Stations` (`StationId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `routes_ibfk_2` FOREIGN KEY (`DestinationStationId`) REFERENCES `Stations` (`StationId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (101,101,102,497.00),(102,101,103,738.40),(103,102,101,497.00),(104,101,104,543.80);
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stations`
--

DROP TABLE IF EXISTS `stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stations` (
  `StationId` int(10) unsigned NOT NULL,
  `StationCode` varchar(5) NOT NULL,
  PRIMARY KEY (`StationId`),
  UNIQUE KEY `StationCode` (`StationCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stations`
--

LOCK TABLES `stations` WRITE;
/*!40000 ALTER TABLE `stations` DISABLE KEYS */;
INSERT INTO `stations` VALUES (103,'ALP'),(102,'CBE'),(101,'MAS'),(104,'MTPL');
/*!40000 ALTER TABLE `stations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-15 13:28:31
