-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 05, 2025 at 09:44 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library management system`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `B_ID` int(4) NOT NULL,
  `B_TITLE` varchar(50) NOT NULL,
  `B_AUTHOR` varchar(50) NOT NULL,
  `B_QUANTITY` int(3) NOT NULL,
  `P_ID` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`B_ID`, `B_TITLE`, `B_AUTHOR`, `B_QUANTITY`, `P_ID`) VALUES
(1001, 'PHYSICS MATTERS', 'MARSHAL CAVENDISH', 17, 104),
(1002, 'PRIDE AND PREJUDICE', 'JANE AUSTEN', 12, 102),
(1004, 'TIME MACHINE', 'H.G. WELLS', 12, 101),
(1005, 'THE HARRY POTTER SERIES', 'J.K ROWLING', 27, 103),
(1007, 'GREAT EXPECTATIONS', 'CHARLES DICKENS', 19, 106),
(1008, 'THE ALCHEMIST', 'PAULO COELHO', 14, 102),
(1009, 'A LITTLE LIFE', 'HANYA YANAGIHARA', 10, 104),
(1010, 'LIGHT', 'M JOHN HARRISON', 20, 105),
(1011, 'THINKING, FAST AND SLOW', 'DANIELKAHNEMAN', 10, 108),
(1012, 'DAYS WITHOUT END', 'SEBASTIAN BARRY', 15, 109),
(1013, 'POSTWAR', 'TONY JUDT', 25, 103),
(1014, 'UNDERLAND', 'ROBERT MACFARLENE', 15, 105),
(1015, 'WOMEN AND POWER', 'MARY BEARD', 19, 110),
(1016, 'LEVELS OF LIFE', 'JULIAN BARNES', 9, 106),
(1017, 'SAPIENS', 'YUVAL NOAH', 28, 101),
(1018, 'LIFE AFTER LIFE', 'KATE ATKINSON', 19, 103),
(1019, 'AUTUMN', 'ALI SMITH', 8, 110),
(1020, 'THE KITE RUNNER', 'KHALED HOSSEINI', 22, 107),
(1021, 'A THOUSAND SPLENDID SUNS', 'KHALED HOSSEINI', 21, 107),
(1023, 'HAMLET', 'WILLIAM SHAKESPEAR', 27, 109),
(1025, 'DUNE', 'FRANK HERBERT', 14, 107),
(1029, 'CHEMISTRY MATTERS', 'MARSHALL CAVENDISH', 12, 104),
(1030, 'THE INVISIBLE MAN', 'H.G. WELLS', 10, 102),
(1032, 'CASE HISTORIES', 'KATE ATKINSON', 12, 108),
(1033, 'NEXUS', 'YUVAL NOAH', 20, 101),
(1034, 'THE SECRET SCRIPTURE', 'SEBASTIAN BARRY', 25, 104),
(1035, 'WINTER', 'ALI SMITH', 15, 107),
(1036, 'WHISTLING PSYCHE', 'SEBASTIAN BARRY', 10, 106),
(1037, 'MONEY', 'YUVAL NOAH', 9, 107),
(1038, 'DIRECT DESCENT', 'FRANK HERBERT', 12, 109),
(1039, 'TRANSCRIPTION', 'KATE ATKINSON', 18, 110),
(1040, 'ELEVEN MINUTES', 'PAUKO COELHO', 14, 108);

-- --------------------------------------------------------

--
-- Table structure for table `issue_table`
--

CREATE TABLE `issue_table` (
  `I_ID` int(4) NOT NULL,
  `I_DATE` date NOT NULL DEFAULT curdate(),
  `B_ID` int(4) NOT NULL,
  `S_ID` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `issue_table`
--

INSERT INTO `issue_table` (`I_ID`, `I_DATE`, `B_ID`, `S_ID`) VALUES
(36, '2025-02-02', 1001, 101),
(37, '2025-02-02', 1007, 101),
(38, '2025-02-02', 1004, 102),
(39, '2025-02-02', 1005, 102),
(40, '2025-02-02', 1020, 102),
(41, '2025-02-02', 1021, 104),
(42, '2025-02-02', 1019, 104),
(43, '2025-02-02', 1017, 116),
(44, '2025-02-02', 1019, 116),
(45, '2025-02-02', 1001, 116),
(46, '2025-02-02', 1018, 123),
(47, '2025-02-02', 1021, 123),
(50, '2025-02-02', 1021, 135),
(51, '2025-02-02', 1017, 135),
(53, '2025-02-02', 1004, 135),
(54, '2025-02-04', 1008, 101),
(55, '2025-02-04', 1015, 101),
(56, '2025-02-04', 1016, 101),
(59, '2025-02-04', 1021, 137),
(60, '2025-02-04', 1020, 137),
(62, '2025-02-04', 1025, 137),
(64, '2025-02-05', 1037, 101),
(65, '2025-02-05', 1004, 101),
(66, '2025-02-05', 1020, 101),
(67, '2025-02-05', 1039, 102),
(68, '2025-02-05', 1040, 102),
(69, '2025-02-05', 1039, 143);

-- --------------------------------------------------------

--
-- Table structure for table `librarian`
--

CREATE TABLE `librarian` (
  `L_ID` int(11) NOT NULL,
  `L_NAME` varchar(30) NOT NULL,
  `L_PWD` varchar(20) NOT NULL,
  `L_EMAIL` varchar(50) NOT NULL,
  `L_PHN` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `librarian`
--

INSERT INTO `librarian` (`L_ID`, `L_NAME`, `L_PWD`, `L_EMAIL`, `L_PHN`) VALUES
(101, 'librarian1', 'lib1', 'lib1@mail.com', '01987654212'),
(102, 'librarian2', 'lib2', 'lib2@mail.com', '01987554221'),
(103, 'librarian3', 'lib3', 'lib3@mail.com', '01987454231'),
(104, 'librarian4', 'lib4', 'lib4@mail.com', '01987354241'),
(105, 'librarian5', 'lib5', 'lib5@mail.com', '01312456354'),
(106, 'librarian6', 'lib6', 'lib6@mail.com', '01982637451'),
(116, 'librarian7', 'lib7', 'lib7@mail.com', '01213456451'),
(117, 'librarian8', 'lib8', 'lib8@mail.com', '01513856452'),
(118, 'librarian9', 'lib9', 'lib9@mail.com', '01509627391');

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `M_ID` int(3) NOT NULL,
  `M_NAME` varchar(30) NOT NULL,
  `M_PWD` varchar(20) NOT NULL,
  `M_EMAIL` varchar(30) NOT NULL,
  `M_PHN` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`M_ID`, `M_NAME`, `M_PWD`, `M_EMAIL`, `M_PHN`) VALUES
(111, 'admin', 'admin', 'admin@mail.com', '01234567891');

-- --------------------------------------------------------

--
-- Table structure for table `manager_librarian`
--

CREATE TABLE `manager_librarian` (
  `L_ID` int(3) NOT NULL,
  `M_ID` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `manager_librarian`
--

INSERT INTO `manager_librarian` (`L_ID`, `M_ID`) VALUES
(101, 111),
(102, 111),
(103, 111),
(104, 111),
(105, 111),
(106, 111),
(116, 111),
(117, 111),
(118, 111);

-- --------------------------------------------------------

--
-- Table structure for table `manager_student`
--

CREATE TABLE `manager_student` (
  `S_ID` int(3) NOT NULL,
  `M_ID` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `manager_student`
--

INSERT INTO `manager_student` (`S_ID`, `M_ID`) VALUES
(103, 111),
(104, 111),
(111, 111),
(116, 111),
(119, 111),
(120, 111),
(121, 111),
(122, 111),
(123, 111),
(101, 111),
(102, 111),
(135, 111),
(137, 111),
(143, 111);

-- --------------------------------------------------------

--
-- Table structure for table `publisher`
--

CREATE TABLE `publisher` (
  `P_ID` int(3) NOT NULL,
  `P_NAME` varchar(50) NOT NULL,
  `P_EMAIL` varchar(50) NOT NULL,
  `P_PHN` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `publisher`
--

INSERT INTO `publisher` (`P_ID`, `P_NAME`, `P_EMAIL`, `P_PHN`) VALUES
(101, 'ACADEMIC PRESS', 'academicp@mail.com', '01923456781'),
(102, 'BBC BOOKS', 'bbcbooks@mail.com', '01789123674'),
(103, 'BLOOMSBURY PUBLISHING', 'bloomsburyp@mail.com', '01817234567'),
(104, 'OXFORD PRESS', 'oxfordp@mail.com', '01716678781'),
(105, 'LADYBIRD BOOKS', 'ladybirdb@mail.com', '01910123456'),
(106, 'APPLEWOOD BOOKS', 'applewood@mail.com', '01398160182'),
(107, 'BOOK WORKS', 'bookworks@mail.com', '01781528674'),
(108, 'COLUMBIA PRESS', 'columbiap@mail.com', '01386434567'),
(109, 'EXPRESS PUBLISHING', 'expressp@mail.com', '01988112093'),
(110, 'TITAN BOOKS', 'titanb@mail.com', '01910176398');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `S_ID` int(3) NOT NULL,
  `S_NAME` varchar(30) NOT NULL,
  `S_PWD` varchar(20) NOT NULL,
  `S_EMAIL` varchar(50) NOT NULL,
  `S_PHN` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`S_ID`, `S_NAME`, `S_PWD`, `S_EMAIL`, `S_PHN`) VALUES
(101, 'maisha', 'maisha1', 'maisha@mail.com', '01768265391'),
(102, 'nabil', 'nabil1', 'nabil@mail.com', '01523477283'),
(103, 'tahseen', 'tahseen1', 'mt@mail.com', '0191234567'),
(104, 'adib', 'adib1', 'adib@mail.com', '01527398461'),
(111, 'jake', 'jake1', 'jake@mail.com', '01374782947'),
(116, 'alice', '123', 'alice@mail.com', '01254378561'),
(119, 'kate', 'kate1', 'kate@mail.com', '01819276350'),
(120, 'mary', 'mary1', 'mary@mail.com', '01923647197'),
(121, 'annie', 'annie1', 'annie@mail.com', '01287304121'),
(122, 'blake', 'blake1', 'blake@mail.com', '01923782910'),
(123, 'barney', 'barney1', 'barney@mail.com', '01762890123'),
(135, 'leah', 'leah1', 'leah@mail.com', '01762039088'),
(137, 'ana', 'ana1', 'ana@mail.com', '01652768191'),
(143, 'maishatahseen', '123', 'maisha@mail.com', '01763725361');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`B_ID`);

--
-- Indexes for table `issue_table`
--
ALTER TABLE `issue_table`
  ADD PRIMARY KEY (`I_ID`),
  ADD KEY `B_ID` (`B_ID`),
  ADD KEY `S_ID` (`S_ID`);

--
-- Indexes for table `librarian`
--
ALTER TABLE `librarian`
  ADD PRIMARY KEY (`L_ID`),
  ADD UNIQUE KEY `L_NAME` (`L_NAME`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`M_ID`);

--
-- Indexes for table `manager_librarian`
--
ALTER TABLE `manager_librarian`
  ADD KEY `L_ID` (`L_ID`),
  ADD KEY `M_ID` (`M_ID`);

--
-- Indexes for table `manager_student`
--
ALTER TABLE `manager_student`
  ADD KEY `S_ID` (`S_ID`),
  ADD KEY `M_ID` (`M_ID`);

--
-- Indexes for table `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`P_ID`),
  ADD UNIQUE KEY `P_NAME` (`P_NAME`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`S_ID`),
  ADD UNIQUE KEY `S_NAME` (`S_NAME`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `B_ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1042;

--
-- AUTO_INCREMENT for table `issue_table`
--
ALTER TABLE `issue_table`
  MODIFY `I_ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `librarian`
--
ALTER TABLE `librarian`
  MODIFY `L_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `S_ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `issue_table`
--
ALTER TABLE `issue_table`
  ADD CONSTRAINT `issue_table_ibfk_1` FOREIGN KEY (`B_ID`) REFERENCES `book` (`B_ID`),
  ADD CONSTRAINT `issue_table_ibfk_2` FOREIGN KEY (`S_ID`) REFERENCES `student` (`S_ID`);

--
-- Constraints for table `manager_librarian`
--
ALTER TABLE `manager_librarian`
  ADD CONSTRAINT `manager_librarian_ibfk_1` FOREIGN KEY (`L_ID`) REFERENCES `librarian` (`L_ID`),
  ADD CONSTRAINT `manager_librarian_ibfk_2` FOREIGN KEY (`M_ID`) REFERENCES `manager` (`M_ID`);

--
-- Constraints for table `manager_student`
--
ALTER TABLE `manager_student`
  ADD CONSTRAINT `manager_student_ibfk_1` FOREIGN KEY (`S_ID`) REFERENCES `student` (`S_ID`),
  ADD CONSTRAINT `manager_student_ibfk_2` FOREIGN KEY (`M_ID`) REFERENCES `manager` (`M_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
