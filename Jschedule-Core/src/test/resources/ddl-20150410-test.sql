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

-- 테이블 scheduledb의 구조를 덤프합니다. attendance
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE IF NOT EXISTS `attendance` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ref_userevents` int(10) unsigned NOT NULL,
  `ref_participants` int(10) unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `fk_UserEvents_has_Participants_Participants1_idx` (`ref_participants`),
  KEY `fk_UserEvents_has_Participants_UserEvents1_idx` (`ref_userevents`),
  CONSTRAINT `fk_UserEvents_has_Participants_Participants1` FOREIGN KEY (`ref_participants`) REFERENCES `participants` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserEvents_has_Participants_UserEvents1` FOREIGN KEY (`ref_userevents`) REFERENCES `userevents` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scheduledb.attendance: ~0 rows (대략적)
DELETE FROM `attendance`;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;


-- 테이블 scheduledb의 구조를 덤프합니다. participants
DROP TABLE IF EXISTS `participants`;
CREATE TABLE IF NOT EXISTS `participants` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_creater` int(10) unsigned NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `description` varchar(1200) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `fk_Participants_Users1_idx` (`fk_creater`),
  CONSTRAINT `fk_Participants_Users1` FOREIGN KEY (`fk_creater`) REFERENCES `users` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='가입회원이 등록한 일정참여자들을 나타냄';

-- Dumping data for table scheduledb.participants: ~0 rows (대략적)
DELETE FROM `participants`;
/*!40000 ALTER TABLE `participants` DISABLE KEYS */;
/*!40000 ALTER TABLE `participants` ENABLE KEYS */;


-- 테이블 scheduledb의 구조를 덤프합니다. placeevents
DROP TABLE IF EXISTS `placeevents`;
CREATE TABLE IF NOT EXISTS `placeevents` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ref_event` int(10) unsigned NOT NULL,
  `ref_old_place` int(10) unsigned NOT NULL,
  `ref_new_place` int(10) unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `fk_PlaceEvents_ScheduleEvents1_idx` (`ref_event`),
  KEY `fk_PlaceEvents_Places1_idx` (`ref_old_place`),
  KEY `fk_PlaceEvents_Places2_idx` (`ref_new_place`),
  CONSTRAINT `fk_PlaceEvents_Places1` FOREIGN KEY (`ref_old_place`) REFERENCES `places` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PlaceEvents_Places2` FOREIGN KEY (`ref_new_place`) REFERENCES `places` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PlaceEvents_ScheduleEvents1` FOREIGN KEY (`ref_event`) REFERENCES `scheduleevents` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='장소변경 이벤트. old_place와 new_place가 같다면 최초 등록 이벤트, 서로 다르다면 장소 변경 이벤트';

-- Dumping data for table scheduledb.placeevents: ~0 rows (대략적)
DELETE FROM `placeevents`;
/*!40000 ALTER TABLE `placeevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `placeevents` ENABLE KEYS */;


-- 테이블 scheduledb의 구조를 덤프합니다. places
DROP TABLE IF EXISTS `places`;
CREATE TABLE IF NOT EXISTS `places` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `placename` varchar(200) NOT NULL,
  `description` varchar(1200) NOT NULL DEFAULT '',
  `fk_user` int(10) unsigned NOT NULL COMMENT '일정을 등록한 user의 pk',
  PRIMARY KEY (`seq`),
  KEY `fk_Places_Users1_idx` (`fk_user`),
  CONSTRAINT `fk_Places_Users1` FOREIGN KEY (`fk_user`) REFERENCES `users` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='회원이 등록한 일정의 모임 장소들';

-- Dumping data for table scheduledb.places: ~0 rows (대략적)
DELETE FROM `places`;
/*!40000 ALTER TABLE `places` DISABLE KEYS */;
/*!40000 ALTER TABLE `places` ENABLE KEYS */;


-- 테이블 scheduledb의 구조를 덤프합니다. scheduleevents
DROP TABLE IF EXISTS `scheduleevents`;
CREATE TABLE IF NOT EXISTS `scheduleevents` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_schedules` int(10) unsigned NOT NULL,
  `event_type` enum('I','X','C','T','P','U') NOT NULL COMMENT '1. 초기 상태\n- 생성 : 일정이 새로 등록되었음. ( I )\n\n2. 종료 상태\n- 만료: 시간 경과로 만료되었음 ( X )\n- 취소: 취소가 되어서 종료됨. ( C )\n\n3. 변경\n- 시간 : 일정의 앞당겨지거나 늦춰짐 ( T )\n- 장소 : 장소가 변경됨. ( P )\n- 참여자: 참여자가 새로 등록되거나, 빠졌음.(U)\n',
  `fk_caused_by` int(10) unsigned NOT NULL COMMENT '이벤트를 발생시킨 사람',
  `event_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`),
  KEY `fk_ScheduleEvents_Schedules1_idx` (`fk_schedules`),
  KEY `fk_ScheduleEvents_Participants1_idx` (`fk_caused_by`),
  CONSTRAINT `fk_ScheduleEvents_Participants1` FOREIGN KEY (`fk_caused_by`) REFERENCES `participants` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ScheduleEvents_Schedules1` FOREIGN KEY (`fk_schedules`) REFERENCES `schedules` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='일정의 상태 변화를 기록하는 테이블\n상태 변화의 유형\n\n1. 초기 상태\n- 생성 : 일정이 새로 등록되었음.\n\n2. 종료 상태\n- 만료: 시간 경과로 만료되었음\n- 취소: 취소가 되어서 종료됨.\n\n3. 변경 이벤트 종류\n- 시간 : 일정의 앞당겨지거나 늦춰짐\n- 장소 : 장소가 변경됨.\n- 참여자: 참여자가 새로 등록되거나, 빠졌음.';

-- Dumping data for table scheduledb.scheduleevents: ~0 rows (대략적)
DELETE FROM `scheduleevents`;
/*!40000 ALTER TABLE `scheduleevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheduleevents` ENABLE KEYS */;


-- 테이블 scheduledb의 구조를 덤프합니다. schedulepatterns
DROP TABLE IF EXISTS `schedulepatterns`;
CREATE TABLE IF NOT EXISTS `schedulepatterns` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `fk_schedules` int(10) unsigned NOT NULL,
  `cronexp` varchar(45) NOT NULL COMMENT 'crom expression string',
  `cnt` int(11) DEFAULT '1' COMMENT '일정 횟수',
  PRIMARY KEY (`seq`),
  KEY `fk_SchedulePatterns_Schedules1_idx` (`fk_schedules`),
  CONSTRAINT `fk_SchedulePatterns_Schedules1` FOREIGN KEY (`fk_schedules`) REFERENCES `schedules` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='일정의 반복주기와 횟수를 기록하는 테이블. 일정의 날짜는 cron expression을 사용함.';

-- Dumping data for table scheduledb.schedulepatterns: ~0 rows (대략적)
DELETE FROM `schedulepatterns`;
/*!40000 ALTER TABLE `schedulepatterns` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedulepatterns` ENABLE KEYS */;


-- 테이블 scheduledb의 구조를 덤프합니다. schedules
DROP TABLE IF EXISTS `schedules`;
CREATE TABLE IF NOT EXISTS `schedules` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_creater` int(10) unsigned NOT NULL COMMENT '스케쥴을 등록한 사람',
  `fk_places` int(10) unsigned DEFAULT NULL,
  `starts_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '이벤트 시작일',
  `when_registered` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '처음 등록한 시간',
  PRIMARY KEY (`seq`),
  KEY `fk_Schedules_Users_idx` (`fk_creater`),
  KEY `fk_Schedules_Places1_idx` (`fk_places`),
  CONSTRAINT `fk_Schedules_Places1` FOREIGN KEY (`fk_places`) REFERENCES `places` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Schedules_Users` FOREIGN KEY (`fk_creater`) REFERENCES `users` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='등록된 일정들 나타냄';

-- Dumping data for table scheduledb.schedules: ~0 rows (대략적)
DELETE FROM `schedules`;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;


-- 테이블 scheduledb의 구조를 덤프합니다. timeevents
DROP TABLE IF EXISTS `timeevents`;
CREATE TABLE IF NOT EXISTS `timeevents` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `old_time` timestamp NULL DEFAULT NULL,
  `adjust_mins` int(11) NOT NULL,
  `ref_event` int(10) unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `fk_TimeEvents_ScheduleEvents1_idx` (`ref_event`),
  CONSTRAINT `fk_TimeEvents_ScheduleEvents1` FOREIGN KEY (`ref_event`) REFERENCES `scheduleevents` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='일정의 시간변경 이벤트';

-- Dumping data for table scheduledb.timeevents: ~0 rows (대략적)
DELETE FROM `timeevents`;
/*!40000 ALTER TABLE `timeevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `timeevents` ENABLE KEYS */;


-- 테이블 scheduledb의 구조를 덤프합니다. userevents
DROP TABLE IF EXISTS `userevents`;
CREATE TABLE IF NOT EXISTS `userevents` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ref_event` int(10) unsigned NOT NULL,
  `event_type` enum('JN','EX') NOT NULL COMMENT 'JN : 참가, EX: 불참',
  PRIMARY KEY (`seq`),
  KEY `fk_UserEvents_ScheduleEvents1_idx` (`ref_event`),
  CONSTRAINT `fk_UserEvents_ScheduleEvents1` FOREIGN KEY (`ref_event`) REFERENCES `scheduleevents` (`seq`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='일정 참가자의 변동을 나타냄(한번에 여러명이 추가되거나 빠질 수 있고 명단은 UserEvents_has_participants 에서 얻어냄)';

-- Dumping data for table scheduledb.userevents: ~0 rows (대략적)
DELETE FROM `userevents`;
/*!40000 ALTER TABLE `userevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `userevents` ENABLE KEYS */;


-- 테이블 scheduledb의 구조를 덤프합니다. users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` char(41) NOT NULL,
  `when_joined` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` enum('Y','N') DEFAULT 'N',
  PRIMARY KEY (`seq`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table scheduledb.users: ~1 rows (대략적)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`seq`, `nickname`, `email`, `password`, `when_joined`, `deleted`) VALUES
	(1, 'james', 'james@e.mail', '1111', '2015-03-27 23:15:23', 'N');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
