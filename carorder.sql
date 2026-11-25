-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 26 نوفمبر 2026 الساعة 00:30
-- إصدار الخادم: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mysql`
--

-- --------------------------------------------------------

--
-- بنية الجدول `carorder`
--

CREATE TABLE `carorder` (
  `orderID` varchar(20) NOT NULL,
  `uid` varchar(20) DEFAULT NULL,
  `carID` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- إرجاع أو استيراد بيانات الجدول `carorder`
--

INSERT INTO `carorder` (`orderID`, `uid`, `carID`) VALUES
('1', '01', 'Accent'),
('2', '01', 'Elantra'),
('3', '02', 'camry');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carorder`
--
ALTER TABLE `carorder`
  ADD PRIMARY KEY (`orderID`),
  ADD KEY `uid` (`uid`),
  ADD KEY `carID` (`carID`);

--
-- قيود الجداول المُلقاة.
--

--
-- قيود الجداول `carorder`
--
ALTER TABLE `carorder`
  ADD CONSTRAINT `carorder_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `caruser` (`uid`),
  ADD CONSTRAINT `carorder_ibfk_2` FOREIGN KEY (`carID`) REFERENCES `car` (`carID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
