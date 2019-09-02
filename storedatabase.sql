-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 02, 2019 at 01:22 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `storedatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
CREATE TABLE IF NOT EXISTS `administrator` (
  `adminId` int(50) NOT NULL,
  `adminName` varchar(100) NOT NULL,
  `adminSurname` varchar(100) NOT NULL,
  `adminPhone` varchar(50) NOT NULL,
  `supplierId` int(50) NOT NULL,
  UNIQUE KEY `pk` (`adminId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`adminId`, `adminName`, `adminSurname`, `adminPhone`, `supplierId`) VALUES
(123, 'Zee', 'Man', '012 345 6789', 469),
(325, 'Mbuzi', 'Doti', '034 567 8912', 4553);

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `stockId` int(50) NOT NULL,
  `stockName` varchar(100) NOT NULL,
  `stcokDescription` varchar(100) NOT NULL,
  `stockDateIn` varchar(50) NOT NULL,
  `stockDateOut` varchar(50) NOT NULL,
  `stockPrice` int(50) NOT NULL,
  `stockQuantity` int(50) NOT NULL,
  `categoryId` int(50) NOT NULL,
  UNIQUE KEY `pk` (`stockId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`stockId`, `stockName`, `stcokDescription`, `stockDateIn`, `stockDateOut`, `stockPrice`, `stockQuantity`, `categoryId`) VALUES
(1515, 'Brandy', 'Special spirits from south kada', '12/08/2019', '15/08/2019', 1500, 12, 145),
(111, 'Whisky', 'Good stuff no phuza face', '13/07/2019', '14/07/2019', 5000, 20, 778);

-- --------------------------------------------------------

--
-- Table structure for table `supply`
--

DROP TABLE IF EXISTS `supply`;
CREATE TABLE IF NOT EXISTS `supply` (
  `SupplierId` int(10) NOT NULL,
  `SupplierName` varchar(50) NOT NULL,
  `SupplierLocation` varchar(100) NOT NULL,
  `SupplierStock` varchar(100) NOT NULL,
  `SupplierStockQuality` int(50) NOT NULL,
  `SupplierPhone` varchar(50) NOT NULL,
  UNIQUE KEY `Id` (`SupplierId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supply`
--

INSERT INTO `supply` (`SupplierId`, `SupplierName`, `SupplierLocation`, `SupplierStock`, `SupplierStockQuality`, `SupplierPhone`) VALUES
(336, 'Johny Walker', 'Mpumalange East  ', 'Johny Walker Brandy', 35, '011 355 1156'),
(466, 'SAB', 'Johannesburg Central', 'Black Label Beer', 150, '016 665 8855');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE IF NOT EXISTS `transaction` (
  `TransactionId` int(50) NOT NULL,
  `ItemName` varchar(50) NOT NULL,
  `ItemPrice` int(50) NOT NULL,
  `CategoryId` int(50) NOT NULL,
  `ItemQuantity` int(50) NOT NULL,
  `ItemTotalPrice` int(50) NOT NULL,
  `StockId` varchar(50) NOT NULL,
  `AdminId` varchar(50) NOT NULL,
  UNIQUE KEY `Id` (`TransactionId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`TransactionId`, `ItemName`, `ItemPrice`, `CategoryId`, `ItemQuantity`, `ItemTotalPrice`, `StockId`, `AdminId`) VALUES
(993, 'Flying Fish', 15, 1311, 150, 700, '336', '123'),
(664, 'Black Label', 1300, 556, 15, 7500, '332', '123');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
