-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 01-03-2021 a las 21:49:25
-- Versión del servidor: 5.7.26
-- Versión de PHP: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbcta`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT,
  `NOM_CLIENTE` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TEL_CLIENTE` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EMAIL_CLIENTE` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_INSTALADOR_CLIENTE_` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cta_corriente`
--

DROP TABLE IF EXISTS `cta_corriente`;
CREATE TABLE IF NOT EXISTS `cta_corriente` (
  `ID_CTACORRIENTE` int(11) NOT NULL AUTO_INCREMENT,
  `FECHA_CIERRE` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  `ID_REMITO` int(11) DEFAULT NULL,
  `ID_OBRA` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_CTACORRIENTE`),
  KEY `ID_CLIENTE` (`ID_CLIENTE`),
  KEY `ID_REMITO` (`ID_REMITO`),
  KEY `ID_OBRA` (`ID_OBRA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obra`
--

DROP TABLE IF EXISTS `obra`;
CREATE TABLE IF NOT EXISTS `obra` (
  `ID_OBRA` int(11) NOT NULL AUTO_INCREMENT,
  `NOM_OBRA` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_OBRA`),
  KEY `ID_CLIENTE` (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `remito`
--

DROP TABLE IF EXISTS `remito`;
CREATE TABLE IF NOT EXISTS `remito` (
  `ID_REMITO` int(11) NOT NULL AUTO_INCREMENT,
  `NUM_REMITO` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FECHA_REMITO` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RUTA_ARCHIVO` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IMPORTE_CIERRE` double DEFAULT NULL,
  `ID_OBRA` int(11) DEFAULT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_REMITO`),
  KEY `ID_OBRA` (`ID_OBRA`),
  KEY `ID_CLIENTE` (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cta_corriente`
--
ALTER TABLE `cta_corriente`
  ADD CONSTRAINT `cta_corriente_ibfk_1` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `cliente` (`ID_CLIENTE`),
  ADD CONSTRAINT `cta_corriente_ibfk_2` FOREIGN KEY (`ID_OBRA`) REFERENCES `obra` (`ID_OBRA`),
  ADD CONSTRAINT `cta_corriente_ibfk_3` FOREIGN KEY (`ID_REMITO`) REFERENCES `remito` (`ID_REMITO`);

--
-- Filtros para la tabla `obra`
--
ALTER TABLE `obra`
  ADD CONSTRAINT `obra_ibfk_1` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `cliente` (`ID_CLIENTE`);

--
-- Filtros para la tabla `remito`
--
ALTER TABLE `remito`
  ADD CONSTRAINT `remito_ibfk_1` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `cliente` (`ID_CLIENTE`),
  ADD CONSTRAINT `remito_ibfk_2` FOREIGN KEY (`ID_OBRA`) REFERENCES `obra` (`ID_OBRA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
