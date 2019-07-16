SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `test_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `capacity` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `class_day`
--

CREATE TABLE `class_day` (
  `id` int(11) NOT NULL,
  `class_day` date NOT NULL,
  `class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `class_day_member`
--

CREATE TABLE `class_day_member` (
  `id` int(11) NOT NULL,
  `class_day_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `type` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `type`) VALUES
(1, 'Tomo', 'OWNER'),
(2, 'Eoghain', 'CLIENT');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`),
  ADD KEY `class_owner` (`user_id`);

--
-- Indexes for table `class_day`
--
ALTER TABLE `class_day`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `class_day_ak_1` (`class_day`,`class_id`) USING BTREE,
  ADD KEY `class_day_class` (`class_id`);

--
-- Indexes for table `class_day_member`
--
ALTER TABLE `class_day_member`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `class_day_member_ak_1` (`class_day_id`,`user_id`),
  ADD KEY `class_day_member_user` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `class_day`
--
ALTER TABLE `class_day`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `class_day_member`
--
ALTER TABLE `class_day_member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_owner` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `class_day`
--
ALTER TABLE `class_day`
  ADD CONSTRAINT `class_day_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`);

--
-- Constraints for table `class_day_member`
--
ALTER TABLE `class_day_member`
  ADD CONSTRAINT `class_day_member_class_day` FOREIGN KEY (`class_day_id`) REFERENCES `class_day` (`id`),
  ADD CONSTRAINT `class_day_member_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

COMMIT;
