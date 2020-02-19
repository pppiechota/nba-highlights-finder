-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: nba
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teams` (
  `id` int(11) NOT NULL,
  `abbreviation` varchar(3) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `conference` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (1,'ATL','Atlanta','East','Atlanta Hawks','Hawks'),(2,'BOS','Boston','East','Boston Celtics','Celtics'),(3,'BKN','Brooklyn','East','Brooklyn Nets','Nets'),(4,'CHA','Charlotte','East','Charlotte Hornets','Hornets'),(5,'CHI','Chicago','East','Chicago Bulls','Bulls'),(6,'CLE','Cleveland','East','Cleveland Cavaliers','Cavaliers'),(7,'DAL','Dallas','West','Dallas Mavericks','Mavericks'),(8,'DEN','Denver','West','Denver Nuggets','Nuggets'),(9,'DET','Detroit','East','Detroit Pistons','Pistons'),(10,'GSW','Golden State','West','Golden State Warriors','Warriors'),(11,'HOU','Houston','West','Houston Rockets','Rockets'),(12,'IND','Indiana','East','Indiana Pacers','Pacers'),(13,'LAC','LA','West','LA Clippers','Clippers'),(14,'LAL','Los Angeles','West','Los Angeles Lakers','Lakers'),(15,'MEM','Memphis','West','Memphis Grizzlies','Grizzlies'),(16,'MIA','Miami','East','Miami Heat','Heat'),(17,'MIL','Milwaukee','East','Milwaukee Bucks','Bucks'),(18,'MIN','Minnesota','West','Minnesota Timberwolves','Timberwolves'),(19,'NOP','New Orleans','West','New Orleans Pelicans','Pelicans'),(20,'NYK','New York','East','New York Knicks','Knicks'),(21,'OKC','Oklahoma City','West','Oklahoma City Thunder','Thunder'),(22,'ORL','Orlando','East','Orlando Magic','Magic'),(23,'PHI','Philadelphia','East','Philadelphia 76ers','76ers'),(24,'PHX','Phoenix','West','Phoenix Suns','Suns'),(25,'POR','Portland','West','Portland Trail Blazers','Blazers'),(26,'SAC','Sacramento','West','Sacramento Kings','Kings'),(27,'SAS','San Antonio','West','San Antonio Spurs','Spurs'),(28,'TOR','Toronto','East','Toronto Raptors','Raptors'),(29,'UTA','Utah','West','Utah Jazz','Jazz'),(30,'WAS','Washington','East','Washington Wizards','Wizards');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-19 13:03:11
