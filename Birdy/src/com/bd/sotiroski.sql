-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 17, 2020 at 08:15 PM
-- Server version: 5.7.22
-- PHP Version: 7.3.11-1~deb10u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sotiroski`
--

-- --------------------------------------------------------

--
-- Table structure for table `Connexion`
--

CREATE TABLE `Connexion` (
  `idUser` int(11) NOT NULL,
  `authentificationKey` varchar(64) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Friends`
--

CREATE TABLE `Friends` (
  `idUser1` int(11) NOT NULL,
  `idUser2` int(11) NOT NULL,
  `dateFriendship` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Friends`
--

INSERT INTO `Friends` (`idUser1`, `idUser2`, `dateFriendship`) VALUES
(11, 9, '2020-02-17 20:10:15');

-- --------------------------------------------------------

--
-- Table structure for table `Messages`
--

CREATE TABLE `Messages` (
  `idUser` int(11) NOT NULL,
  `content` text NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
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
-- Indexes for table `Connexion`
--
ALTER TABLE `Connexion`
  ADD PRIMARY KEY (`idUser`,`authentificationKey`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `Friends`
--
ALTER TABLE `Friends`
  ADD PRIMARY KEY (`idUser1`,`idUser2`),
  ADD KEY `idUser2` (`idUser2`),
  ADD KEY `idUser1` (`idUser1`) USING BTREE;

--
-- Indexes for table `Messages`
--
ALTER TABLE `Messages`
  ADD PRIMARY KEY (`idUser`),
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
-- Constraints for table `Connexion`
--
ALTER TABLE `Connexion`
  ADD CONSTRAINT `Connexion_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`);

--
-- Constraints for table `Friends`
--
ALTER TABLE `Friends`
  ADD CONSTRAINT `Friends_ibfk_1` FOREIGN KEY (`idUser1`) REFERENCES `User` (`idUser`),
  ADD CONSTRAINT `Friends_ibfk_2` FOREIGN KEY (`idUser2`) REFERENCES `User` (`idUser`);

--
-- Constraints for table `Messages`
--
ALTER TABLE `Messages`
  ADD CONSTRAINT `Messages_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
