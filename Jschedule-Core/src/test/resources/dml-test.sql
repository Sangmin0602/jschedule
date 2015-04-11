-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.0.17-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- Dumping data for table test_scheduledb.attendance: ~0 rows (대략적)
DELETE FROM `attendance`;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;

-- Dumping data for table test_scheduledb.participants: ~0 rows (대략적)
DELETE FROM `participants`;
/*!40000 ALTER TABLE `participants` DISABLE KEYS */;
/*!40000 ALTER TABLE `participants` ENABLE KEYS */;

-- Dumping data for table test_scheduledb.placeevents: ~0 rows (대략적)
DELETE FROM `placeevents`;
/*!40000 ALTER TABLE `placeevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `placeevents` ENABLE KEYS */;

-- Dumping data for table test_scheduledb.places: ~0 rows (대략적)
DELETE FROM `places`;
/*!40000 ALTER TABLE `places` DISABLE KEYS */;
/*!40000 ALTER TABLE `places` ENABLE KEYS */;

-- Dumping data for table test_scheduledb.scheduleevents: ~0 rows (대략적)
DELETE FROM `scheduleevents`;
/*!40000 ALTER TABLE `scheduleevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheduleevents` ENABLE KEYS */;

-- Dumping data for table test_scheduledb.schedulepatterns: ~0 rows (대략적)
DELETE FROM `schedulepatterns`;
/*!40000 ALTER TABLE `schedulepatterns` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedulepatterns` ENABLE KEYS */;

-- Dumping data for table test_scheduledb.schedules: ~0 rows (대략적)
DELETE FROM `schedules`;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;

-- Dumping data for table test_scheduledb.timeevents: ~0 rows (대략적)
DELETE FROM `timeevents`;
/*!40000 ALTER TABLE `timeevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `timeevents` ENABLE KEYS */;

-- Dumping data for table test_scheduledb.userevents: ~0 rows (대략적)
DELETE FROM `userevents`;
/*!40000 ALTER TABLE `userevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `userevents` ENABLE KEYS */;

-- Dumping data for table test_scheduledb.users: ~1 rows (대략적)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`seq`, `nickname`, `email`, `password`, `when_joined`, `deleted`) VALUES
	(1, 'james', 'james@e.mail', '1111', '2015-03-27 23:15:23', 'N');

INSERT INTO `places` (`placename`, `description`, `fk_user`) 
VALUES ('mongchon', '몽촡토성', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
