CREATE DATABASE  IF NOT EXISTS `rest` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rest`;

DROP TABLE IF EXISTS `points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `points` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `longitude` Decimal(9,6) NOT NULL,
  `latitude` Decimal(9,6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;