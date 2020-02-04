-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 04, 2020 at 09:14 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `testdb`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_class` (IN `grade` VARCHAR(45), `name` VARCHAR(45))  BEGIN INSERT INTO class (grade, name) VALUES (grade, name); END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_student` (IN `first_name` VARCHAR(45), `last_name` VARCHAR(45), `address` VARCHAR(45), `class_id` INT, `year` VARCHAR(7))  BEGIN DECLARE newStudentID INT; INSERT INTO student (first_name, last_name, address) VALUES (first_name, last_name, address); SET newStudentID = (SELECT id FROM student ORDER BY id DESC LIMIT 1); INSERT INTO student_class_history (year, student_id, class_id) VALUES (year, newStudentID, class_id); END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_student` (IN `studentId` INT)  BEGIN DELETE FROM student WHERE id = studentId; END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_user_account` (IN `studentId` INT)  BEGIN DELETE FROM user_accounts WHERE student_id = studentId; END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_class_by_id` (IN `classId` INT)  BEGIN SELECT * FROM class WHERE id = classId; END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_student` (IN `studentId` INT, IN `year` VARCHAR(10))  BEGIN SELECT student.id,student.first_name,student.last_name,student.address,student_class_history.year,student_class_history.class_id FROM student INNER JOIN student_class_history ON student_class_history.student_id = student.id WHERE student.id = studentId AND student_class_history.year = year; END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logIn` (IN `studentId` INT, IN `studentPassword` VARCHAR(255), OUT `returnMessage` VARCHAR(255))  BEGIN DECLARE userPassword VARCHAR(255); SELECT password FROM user_accounts WHERE student_id = studentId INTO userPassword; IF (userPassword IS NULL) THEN SET returnMessage='Invalid user id.'; ELSE IF (userPassword = studentPassword) THEN SET returnMessage='Login successfull.'; ELSE SET returnMessage='Incorrect password.'; END IF; END IF;END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `signIn` (IN `studentId` INT, `password` VARCHAR(45), OUT `returnMessage` VARCHAR(255))  BEGIN DECLARE studentRecordCount INT; SELECT COUNT(*) FROM student WHERE id = studentId INTO studentRecordCount; IF (studentRecordCount > 0) THEN INSERT INTO user_accounts (student_id,password,sign_in_date) VALUES (studentId,password,NOW()); SET returnMessage='Student exists and signed successfully.'; ELSE SET returnMessage='Student not exists. Can not signIn.'; END IF;END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update_student` (IN `firstName` VARCHAR(45), IN `lastName` VARCHAR(45), IN `address` VARCHAR(45), IN `studentId` INT)  BEGIN UPDATE student SET first_name = firstName, last_name = lastName, address = address WHERE id = studentId; END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `id` int(11) NOT NULL,
  `grade` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`id`, `grade`, `name`) VALUES
(1, '1', 'Olu'),
(4, '1', 'Nelum'),
(5, '2', 'Nelum'),
(6, '2', 'Olu'),
(7, '3', 'Olu');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `first_name`, `last_name`, `address`) VALUES
(8, 'Chamalka', 'Rathna', 'Maharaga'),
(9, 'Danushi', 'Karunarathne', 'Malabe'),
(10, 'Nuwan', 'Kulasekara', 'Maharagama');

-- --------------------------------------------------------

--
-- Table structure for table `student_class_history`
--

CREATE TABLE `student_class_history` (
  `id` int(11) NOT NULL,
  `year` varchar(255) NOT NULL,
  `student_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_class_history`
--

INSERT INTO `student_class_history` (`id`, `year`, `student_id`, `class_id`) VALUES
(8, '2020', 8, 4),
(9, '2021', 8, 5),
(10, '2020', 9, 6),
(11, '2020', 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_accounts`
--

CREATE TABLE `user_accounts` (
  `student_id` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sign_in_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_accounts`
--

INSERT INTO `user_accounts` (`student_id`, `password`, `sign_in_date`) VALUES
(10, 'sanga', '2020-02-04');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_class_history`
--
ALTER TABLE `student_class_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_history_student` (`student_id`),
  ADD KEY `FK_Student_Class` (`class_id`);

--
-- Indexes for table `user_accounts`
--
ALTER TABLE `user_accounts`
  ADD UNIQUE KEY `student_id` (`student_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `student_class_history`
--
ALTER TABLE `student_class_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `student_class_history`
--
ALTER TABLE `student_class_history`
  ADD CONSTRAINT `FK_Student_Class` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_history_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_accounts`
--
ALTER TABLE `user_accounts`
  ADD CONSTRAINT `FK_UserAccount_Student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
