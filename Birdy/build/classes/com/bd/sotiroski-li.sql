-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 18, 2020 at 05:54 PM
-- Server version: 5.7.22
-- PHP Version: 7.0.33-0+deb9u6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sotiroski-li`
--

-- --------------------------------------------------------

--
-- Table structure for table `Followers`
--

CREATE TABLE `Followers` (
  `idUser` int(11) NOT NULL,
  `idFollowing` int(11) NOT NULL,
  `dateFriendship` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Session`
--

CREATE TABLE `Session` (
  `idUser` int(11) NOT NULL,
  `sessionKey` varchar(32) NOT NULL,
  `dateLogin` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `idUser` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`idUser`, `email`, `username`, `name`, `surname`, `password`) VALUES
(9, 'filipsotiroski@gmail.com', 'vilipche', 'Filip', 'Sotiroski', '123ABCabc'),
(11, 'toto@gmail.com', 'toto', 'Toto', 'Toti', '123ABCabc');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Followers`
--
ALTER TABLE `Followers`
  ADD PRIMARY KEY (`idUser`,`idFollowing`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `idFollowing` (`idFollowing`);

--
-- Indexes for table `Session`
--
ALTER TABLE `Session`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `key` (`sessionKey`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Followers`
--
ALTER TABLE `Followers`
  ADD CONSTRAINT `Followers_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`),
  ADD CONSTRAINT `Followers_ibfk_2` FOREIGN KEY (`idFollowing`) REFERENCES `User` (`idUser`);

--
-- Constraints for table `Session`
--
ALTER TABLE `Session`
  ADD CONSTRAINT `Session_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
