CREATE DATABASE  IF NOT EXISTS `dbbank` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `dbbank`;
-- MySQL dump 10.19  Distrib 10.3.38-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: dbbank
-- ------------------------------------------------------
-- Server version	10.3.37-MariaDB

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `edad` int(11) NOT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `identificacion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `cliente_id` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nhdbltv7dnhp65s6wjb411goq` (`cliente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (2,'Otavalo sn y principal',0,NULL,NULL,'Jose Lema','098254785','2','1234',''),(3,'Amazonas y NNUU',0,NULL,NULL,'Marianela Montalvo','097548965','3','5678',''),(4,'13 junio y Equinoccial',0,NULL,NULL,'Juan Osorio','098874587','4','1245','');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuentas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` bit(1) NOT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo_inicial` decimal(38,2) DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7h7mqvcau3mcl0mbrkdrt7fnh` (`numero_cuenta`),
  KEY `FK65yk2321jpusl3fk96lqehrli` (`cliente_id`),
  CONSTRAINT `FK65yk2321jpusl3fk96lqehrli` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES (1,'','478758',1425.00,'Ahorro',2),(2,'','225487',700.00,'Corriente',3),(3,'','495878',150.00,'Ahorros',4),(4,'','496825',0.00,'Ahorros',3),(5,'','585545',1000.00,'Corriente',2);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimientos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) NOT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo_disponible` decimal(38,2) DEFAULT NULL,
  `saldo_inicial` decimal(38,2) DEFAULT NULL,
  `tipo_movimiento` varchar(255) DEFAULT NULL,
  `valor` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (1,'2023-09-01 20:24:22.000000','478758',1425.00,2000.00,'Retiro',575.00),(2,'2023-02-10 20:25:21.000000','225487',700.00,100.00,'Depósito',600.00),(3,'2023-09-01 20:25:49.000000','495878',150.00,0.00,'Depósito',150.00),(4,'2023-02-08 20:26:09.000000','496825',0.00,540.00,'Retiro',540.00);
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-01 15:55:06
