-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 15, 2021 at 09:20 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gesta`
--

-- --------------------------------------------------------

--
-- Table structure for table `cotisation`
--

CREATE TABLE `cotisation` (
  `id_cotisation` int(11) NOT NULL,
  `montant` float NOT NULL,
  `date_cotisation` datetime NOT NULL,
  `id_evenement` int(11) NOT NULL,
  `id_membre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cotisation`
--

INSERT INTO `cotisation` (`id_cotisation`, `montant`, `date_cotisation`, `id_evenement`, `id_membre`) VALUES
(3, 2000, '2021-07-06 00:00:00', 3, 9);

-- --------------------------------------------------------

--
-- Table structure for table `demande`
--

CREATE TABLE `demande` (
  `id_demande` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `date_naissance` date NOT NULL,
  `adresse` varchar(30) NOT NULL,
  `telephone` bigint(8) NOT NULL,
  `email` varchar(30) NOT NULL,
  `cni` varchar(30) NOT NULL,
  `description` varchar(250) NOT NULL,
  `etat` tinyint(1) NOT NULL DEFAULT 0,
  `date_demande` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demande`
--

INSERT INTO `demande` (`id_demande`, `nom`, `prenom`, `date_naissance`, `adresse`, `telephone`, `email`, `cni`, `description`, `etat`, `date_demande`) VALUES
(5, 'Wabo', 'Romaric', '2021-07-01', 'Bandjoun', 695684520, 'wabo@gmail.com', '1FF54FDD', 'je suis un jeune camerounais trÃ¨s dÃ©vouÃ© au travail.				', 0, '2021-07-15'),
(6, 'Ngatchou', 'Christian', '2021-07-01', 'Douala Cameroun', 698906300, 'ndayafranck00@gmail.com', '102001588', 'je suis un jeune camerounais travailleur qui voudrait faire partir des votres.						', 1, '2021-07-15');

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

CREATE TABLE `evenement` (
  `id_evenement` int(11) NOT NULL,
  `intitule` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id_evenement`, `intitule`, `description`, `date_debut`, `date_fin`) VALUES
(3, 'visite de laquintinie', 'simple visite de courtoisie', '2021-07-02', '2021-07-04');

-- --------------------------------------------------------

--
-- Table structure for table `membre`
--

CREATE TABLE `membre` (
  `id_membre` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `adresse` varchar(30) DEFAULT NULL,
  `telephone` bigint(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `cni` varchar(30) DEFAULT NULL,
  `role` varchar(250) DEFAULT 'membre',
  `login` varchar(10) DEFAULT 'null',
  `mdp` varchar(10) DEFAULT 'null'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `membre`
--

INSERT INTO `membre` (`id_membre`, `nom`, `prenom`, `date_naissance`, `adresse`, `telephone`, `email`, `cni`, `role`, `login`, `mdp`) VALUES
(8, 'Levite', 'Ngatchou', '2021-07-02', 'Douala Cameroun', 698906301, 'ndayafranck0@gmail.com', '102001587', 'gestionnaire', 'admin', 'admin'),
(9, 'Ngatchou', 'Christian', '2021-07-01', 'Douala Cameroun', 698906300, 'ndayafranck00@gmail.com', '102001588', 'membre', 'null', 'null');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cotisation`
--
ALTER TABLE `cotisation`
  ADD PRIMARY KEY (`id_cotisation`),
  ADD KEY `id_evenement` (`id_evenement`),
  ADD KEY `id_membre` (`id_membre`);

--
-- Indexes for table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id_demande`);

--
-- Indexes for table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id_evenement`);

--
-- Indexes for table `membre`
--
ALTER TABLE `membre`
  ADD PRIMARY KEY (`id_membre`),
  ADD UNIQUE KEY `telephone` (`telephone`),
  ADD UNIQUE KEY `cni` (`cni`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cotisation`
--
ALTER TABLE `cotisation`
  MODIFY `id_cotisation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `demande`
--
ALTER TABLE `demande`
  MODIFY `id_demande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id_evenement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `membre`
--
ALTER TABLE `membre`
  MODIFY `id_membre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cotisation`
--
ALTER TABLE `cotisation`
  ADD CONSTRAINT `cotisation_ibfk_1` FOREIGN KEY (`id_evenement`) REFERENCES `evenement` (`id_evenement`),
  ADD CONSTRAINT `cotisation_ibfk_2` FOREIGN KEY (`id_membre`) REFERENCES `membre` (`id_membre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
