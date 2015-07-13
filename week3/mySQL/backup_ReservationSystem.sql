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
-- Table structure for table `Bookings`
--

DROP TABLE IF EXISTS `Bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bookings` (
  `BookingRefNo` int(10) unsigned NOT NULL,
  `RouteId` int(10) unsigned DEFAULT NULL,
  `TrainNo` int(10) unsigned DEFAULT NULL,
  `CoachCode` int(10) unsigned DEFAULT NULL,
  `DateOfJourney` date NOT NULL,
  `DateOfBooking` date DEFAULT NULL,
  `NoOfTickets` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`BookingRefNo`),
  KEY `RouteId` (`RouteId`),
  KEY `TrainNo` (`TrainNo`),
  KEY `CoachCode` (`CoachCode`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`RouteId`) REFERENCES `TrainRouteMaps` (`RouteId`),
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`TrainNo`) REFERENCES `TrainRouteMaps` (`RouteId`),
  CONSTRAINT `bookings_ibfk_3` FOREIGN KEY (`CoachCode`) REFERENCES `Coaches` (`CoachCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bookings`
--

LOCK TABLES `Bookings` WRITE;
/*!40000 ALTER TABLE `Bookings` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bookings` ENABLE KEYS */;
UNLOCK TABLES;

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
/*!40000 ALTER TABLE `Coaches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Routes`
--

DROP TABLE IF EXISTS `Routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Routes` (
  `RouteId` int(10) unsigned NOT NULL,
  `OriginStationId` int(10) unsigned NOT NULL,
  `DestinationStationId` int(10) unsigned NOT NULL,
  `DistanceInKms` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`RouteId`),
  KEY `OriginStationId` (`OriginStationId`),
  KEY `DestinationStationId` (`DestinationStationId`),
  CONSTRAINT `routes_ibfk_1` FOREIGN KEY (`OriginStationId`) REFERENCES `Stations` (`StationId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `routes_ibfk_2` FOREIGN KEY (`DestinationStationId`) REFERENCES `Stations` (`StationId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Routes`
--

LOCK TABLES `Routes` WRITE;
/*!40000 ALTER TABLE `Routes` DISABLE KEYS */;
/*!40000 ALTER TABLE `Routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stations`
--

DROP TABLE IF EXISTS `Stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stations` (
  `StationId` int(10) unsigned NOT NULL,
  `StationCode` char(5) NOT NULL,
  PRIMARY KEY (`StationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stations`
--

LOCK TABLES `Stations` WRITE;
/*!40000 ALTER TABLE `Stations` DISABLE KEYS */;
/*!40000 ALTER TABLE `Stations` ENABLE KEYS */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-09 17:48:57
