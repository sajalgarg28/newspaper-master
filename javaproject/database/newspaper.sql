-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2021 at 06:10 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `newspaper`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `billid` int(11) NOT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `totdays` int(11) DEFAULT NULL,
  `billdate` date DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `payed` int(11) DEFAULT '0',
  `area` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `custommng`
--

CREATE TABLE `custommng` (
  `mobile` varchar(12) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(1500) NOT NULL,
  `area` varchar(2000) NOT NULL,
  `hawker` varchar(30) DEFAULT NULL,
  `paper` varchar(2000) NOT NULL,
  `date` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hawkerdetails`
--

CREATE TABLE `hawkerdetails` (
  `name` varchar(25) NOT NULL,
  `mobile1` varchar(12) DEFAULT NULL,
  `mobile2` varchar(12) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `picname` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hawkerdetails`
--



-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `password`) VALUES
('gargsajal', 'sajal');

-- --------------------------------------------------------

--
-- Table structure for table `paperdetails`
--

CREATE TABLE `paperdetails` (
  `paper` varchar(250) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paperdetails`
--

INSERT INTO `paperdetails` (`paper`, `price`) VALUES
('dainik bhaskar', 3),
('Dainik Jaagran', 4),
('Hind Samachar', 3),
('Hindustan Times', 4),
('Jag Bani', 2.5),
('punjab kesari', 4.5),
('Punjabi Tribune', 5.5),
('The Tribune', 5.5),
('times now', 2.5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`billid`);

--
-- Indexes for table `custommng`
--
ALTER TABLE `custommng`
  ADD PRIMARY KEY (`mobile`);

--
-- Indexes for table `hawkerdetails`
--
ALTER TABLE `hawkerdetails`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `paperdetails`
--
ALTER TABLE `paperdetails`
  ADD PRIMARY KEY (`paper`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `billid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
