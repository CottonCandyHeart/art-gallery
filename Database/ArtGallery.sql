-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 02, 2024 at 06:39 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ArtGallery`
--

-- --------------------------------------------------------

--
-- Table structure for table `Artists`
--

CREATE TABLE `Artists` (
  `artist_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(40) NOT NULL,
  `bio` text NOT NULL COMMENT 'Biografia',
  `birth_date` date NOT NULL,
  `death_date` date DEFAULT NULL COMMENT 'opcjonalne'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Artworks`
--

CREATE TABLE `Artworks` (
  `artwork_id` int(11) NOT NULL,
  `title` varchar(40) NOT NULL,
  `artist_id` int(11) NOT NULL,
  `creation_date` date NOT NULL,
  `description` int(11) NOT NULL,
  `location` enum('wystawa','magazyn') NOT NULL COMMENT 'Aktualna lokalizacja (np. na wystawie, w magazynie)',
  `status` enum('dostępne','wypożyczone','konserwacji') NOT NULL COMMENT 'Status dzieła (np. dostępne, wypożyczone, w konserwacji)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Clients`
--

CREATE TABLE `Clients` (
  `client_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(40) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone_number` int(9) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Events`
--

CREATE TABLE `Events` (
  `event_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL COMMENT 'Nazwa wydarzenia',
  `event_date` date NOT NULL,
  `exhibition_id` int(11) DEFAULT NULL COMMENT 'ID wystawy (jeśli dotyczy)',
  `capacity` int(11) NOT NULL COMMENT 'Maksymalna liczba uczestników',
  `event_type` enum('warsztat','oprowadzanie') NOT NULL COMMENT 'Typ wydarzenia (np. warsztat, oprowadzanie)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Exhibitions`
--

CREATE TABLE `Exhibitions` (
  `exhibition_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT 'Nazwa wystawy',
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL COMMENT 'Mogą być stałe wystawy',
  `location` varchar(60) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Marketing`
--

CREATE TABLE `Marketing` (
  `promotion_id` int(11) NOT NULL,
  `content` text NOT NULL COMMENT 'Treść promocyjna',
  `target_audience` enum('młodzież','młodzi dorośli','dorośli','emeryci','wszystkie grupy wiekowe') NOT NULL COMMENT 'Docelowa grupa odbiorców',
  `start_date` date NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Reports`
--

CREATE TABLE `Reports` (
  `report_id` int(11) NOT NULL,
  `report_type` varchar(40) NOT NULL COMMENT 'Typ raportu (np. sprzedaży, odwiedzających)',
  `generated_date` date NOT NULL COMMENT 'Data wygenerowania',
  `details` text NOT NULL COMMENT 'Szczegóły raportu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Transactions`
--

CREATE TABLE `Transactions` (
  `transaction_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `artwork_id` int(11) NOT NULL,
  `sale_date` date NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` enum('administrator','pracownik','klient') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Artists`
--
ALTER TABLE `Artists`
  ADD PRIMARY KEY (`artist_id`);

--
-- Indexes for table `Artworks`
--
ALTER TABLE `Artworks`
  ADD PRIMARY KEY (`artwork_id`),
  ADD KEY `artist_id` (`artist_id`);

--
-- Indexes for table `Clients`
--
ALTER TABLE `Clients`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `Events`
--
ALTER TABLE `Events`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `exhibition_id` (`exhibition_id`);

--
-- Indexes for table `Exhibitions`
--
ALTER TABLE `Exhibitions`
  ADD PRIMARY KEY (`exhibition_id`);

--
-- Indexes for table `Marketing`
--
ALTER TABLE `Marketing`
  ADD PRIMARY KEY (`promotion_id`);

--
-- Indexes for table `Reports`
--
ALTER TABLE `Reports`
  ADD PRIMARY KEY (`report_id`);

--
-- Indexes for table `Transactions`
--
ALTER TABLE `Transactions`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `client_id` (`client_id`,`artwork_id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Artists`
--
ALTER TABLE `Artists`
  MODIFY `artist_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Artworks`
--
ALTER TABLE `Artworks`
  MODIFY `artwork_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Clients`
--
ALTER TABLE `Clients`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Events`
--
ALTER TABLE `Events`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Exhibitions`
--
ALTER TABLE `Exhibitions`
  MODIFY `exhibition_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Marketing`
--
ALTER TABLE `Marketing`
  MODIFY `promotion_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Reports`
--
ALTER TABLE `Reports`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Transactions`
--
ALTER TABLE `Transactions`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
