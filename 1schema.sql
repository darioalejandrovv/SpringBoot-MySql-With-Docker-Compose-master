-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbbank
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `clientes`
--
-- Crear una base de datos solo si no existe
CREATE DATABASE IF NOT EXISTS dbbank;

-- Seleccionar la base de datos recién creada o existente
USE dbbank;
DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `direccion` varchar(255) DEFAULT NULL,
                            `edad` int NOT NULL,
                            `genero` varchar(255) DEFAULT NULL,
                            `identificacion` varchar(255) DEFAULT NULL,
                            `nombre` varchar(255) DEFAULT NULL,
                            `telefono` varchar(255) DEFAULT NULL,
                            `cliente_id` varchar(255) DEFAULT NULL,
                            `contrasena` varchar(255) DEFAULT NULL,
                            `estado` bit(1) NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK_nhdbltv7dnhp65s6wjb411goq` (`cliente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `estado` bit(1) NOT NULL,
                           `numero_cuenta` varchar(255) DEFAULT NULL,
                           `saldo_inicial` decimal(38,2) DEFAULT NULL,
                           `tipo_cuenta` varchar(255) DEFAULT NULL,
                           `cliente_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UK_7h7mqvcau3mcl0mbrkdrt7fnh` (`numero_cuenta`),
                           KEY `FK65yk2321jpusl3fk96lqehrli` (`cliente_id`),
                           CONSTRAINT `FK65yk2321jpusl3fk96lqehrli` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `fecha` datetime(6) NOT NULL,
                               `numero_cuenta` varchar(255) DEFAULT NULL,
                               `saldo_disponible` decimal(38,2) DEFAULT NULL,
                               `saldo_inicial` decimal(38,2) DEFAULT NULL,
                               `tipo_movimiento` varchar(255) DEFAULT NULL,
                               `valor` decimal(38,2) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-04 10:23:15
