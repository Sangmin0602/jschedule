-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema scheduledb
-- -----------------------------------------------------
-- 일정관리 자바 웹 애플리케이션용 데이터베이스입니다.
DROP SCHEMA IF EXISTS `scheduledb` ;

-- -----------------------------------------------------
-- Schema scheduledb
--
-- 일정관리 자바 웹 애플리케이션용 데이터베이스입니다.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `scheduledb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
SHOW WARNINGS;
USE `scheduledb` ;

-- -----------------------------------------------------
-- Table `scheduledb`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheduledb`.`Users` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `scheduledb`.`Users` (
  `seq` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` CHAR(41) NOT NULL,
  `when_joined` TIMESTAMP NULL DEFAULT current_timestamp,
  PRIMARY KEY (`seq`),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `scheduledb`.`Places`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheduledb`.`Places` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `scheduledb`.`Places` (
  `seq` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `placename` VARCHAR(200) NOT NULL,
  `description` VARCHAR(1200) NOT NULL DEFAULT '',
  `fk_user` INT UNSIGNED NOT NULL COMMENT '일정을 등록한 user의 pk',
  PRIMARY KEY (`seq`),
  INDEX `fk_Places_Users1_idx` (`fk_user` ASC),
  CONSTRAINT `fk_Places_Users1`
    FOREIGN KEY (`fk_user`)
    REFERENCES `scheduledb`.`Users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '회원이 등록한 일정의 모임 장소들';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `scheduledb`.`Participants`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheduledb`.`Participants` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `scheduledb`.`Participants` (
  `seq` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fk_creater` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `description` VARCHAR(1200) NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_Participants_Users1_idx` (`fk_creater` ASC),
  CONSTRAINT `fk_Participants_Users1`
    FOREIGN KEY (`fk_creater`)
    REFERENCES `scheduledb`.`Users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '가입회원이 등록한 일정참여자들을 나타냄';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `scheduledb`.`Schedules`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheduledb`.`Schedules` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `scheduledb`.`Schedules` (
  `seq` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fk_creater` INT UNSIGNED NOT NULL COMMENT '스케쥴을 등록한 사람',
  `fk_places` INT UNSIGNED NULL,
  `starts_at` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '이벤트 시작일',
  `when_registered` TIMESTAMP NULL DEFAULT current_timestamp COMMENT '처음 등록한 시간',
  PRIMARY KEY (`seq`),
  INDEX `fk_Schedules_Users_idx` (`fk_creater` ASC),
  INDEX `fk_Schedules_Places1_idx` (`fk_places` ASC),
  CONSTRAINT `fk_Schedules_Users`
    FOREIGN KEY (`fk_creater`)
    REFERENCES `scheduledb`.`Users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Schedules_Places1`
    FOREIGN KEY (`fk_places`)
    REFERENCES `scheduledb`.`Places` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '등록된 일정들 나타냄';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `scheduledb`.`SchedulePatterns`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheduledb`.`SchedulePatterns` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `scheduledb`.`SchedulePatterns` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `fk_schedules` INT UNSIGNED NOT NULL,
  `cronexp` VARCHAR(45) NOT NULL COMMENT 'crom expression string',
  `cnt` INT NULL DEFAULT 1 COMMENT '일정 횟수',
  PRIMARY KEY (`seq`),
  INDEX `fk_SchedulePatterns_Schedules1_idx` (`fk_schedules` ASC),
  CONSTRAINT `fk_SchedulePatterns_Schedules1`
    FOREIGN KEY (`fk_schedules`)
    REFERENCES `scheduledb`.`Schedules` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '일정의 반복주기와 횟수를 기록하는 테이블. 일정의 날짜는 cron expression을 사용함.';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `scheduledb`.`ScheduleEvents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheduledb`.`ScheduleEvents` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `scheduledb`.`ScheduleEvents` (
  `seq` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fk_schedules` INT UNSIGNED NOT NULL,
  `event_type` ENUM('I', 'X', 'C', 'T', 'P', 'U') NOT NULL COMMENT '1. 초기 상태\n- 생성 : 일정이 새로 등록되었음. ( I )\n\n2. 종료 상태\n- 만료: 시간 경과로 만료되었음 ( X )\n- 취소: 취소가 되어서 종료됨. ( C )\n\n3. 변경\n- 시간 : 일정의 앞당겨지거나 늦춰짐 ( T )\n- 장소 : 장소가 변경됨. ( P )\n- 참여자: 참여자가 새로 등록되거나, 빠졌음.(U)\n',
  `fk_caused_by` INT UNSIGNED NOT NULL COMMENT '이벤트를 발생시킨 사람',
  `event_time` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  INDEX `fk_ScheduleEvents_Schedules1_idx` (`fk_schedules` ASC),
  PRIMARY KEY (`seq`),
  INDEX `fk_ScheduleEvents_Participants1_idx` (`fk_caused_by` ASC),
  CONSTRAINT `fk_ScheduleEvents_Schedules1`
    FOREIGN KEY (`fk_schedules`)
    REFERENCES `scheduledb`.`Schedules` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ScheduleEvents_Participants1`
    FOREIGN KEY (`fk_caused_by`)
    REFERENCES `scheduledb`.`Participants` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '일정의 상태 변화를 기록하는 테이블\n상태 변화의 유형\n\n1. 초기 상태\n- 생성 : 일정이 새로 등록되었음.\n\n2. 종료 상태\n- 만료: 시간 경과로 만료되었음\n- 취소: 취소가 되어서 종료됨.\n\n3. 변경 이벤트 종류\n- 시간 : 일정의 앞당겨지거나 늦춰짐\n- 장소 : 장소가 변경됨.\n- 참여자: 참여자가 새로 등록되거나, 빠졌음.';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `scheduledb`.`UserEvents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheduledb`.`UserEvents` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `scheduledb`.`UserEvents` (
  `seq` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ref_event` INT UNSIGNED NOT NULL,
  `event_type` ENUM('JN','EX') NOT NULL COMMENT 'JN : 참가, EX: 불참',
  PRIMARY KEY (`seq`),
  INDEX `fk_UserEvents_ScheduleEvents1_idx` (`ref_event` ASC),
  CONSTRAINT `fk_UserEvents_ScheduleEvents1`
    FOREIGN KEY (`ref_event`)
    REFERENCES `scheduledb`.`ScheduleEvents` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '일정 참가자의 변동을 나타냄(한번에 여러명이 추가되거나 빠질 수 있고 명단은 UserEvents_has_participants 에서 얻어냄)';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `scheduledb`.`PlaceEvents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheduledb`.`PlaceEvents` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `scheduledb`.`PlaceEvents` (
  `seq` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ref_event` INT UNSIGNED NOT NULL,
  `ref_old_place` INT UNSIGNED NOT NULL,
  `ref_new_place` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_PlaceEvents_ScheduleEvents1_idx` (`ref_event` ASC),
  INDEX `fk_PlaceEvents_Places1_idx` (`ref_old_place` ASC),
  INDEX `fk_PlaceEvents_Places2_idx` (`ref_new_place` ASC),
  CONSTRAINT `fk_PlaceEvents_ScheduleEvents1`
    FOREIGN KEY (`ref_event`)
    REFERENCES `scheduledb`.`ScheduleEvents` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PlaceEvents_Places1`
    FOREIGN KEY (`ref_old_place`)
    REFERENCES `scheduledb`.`Places` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PlaceEvents_Places2`
    FOREIGN KEY (`ref_new_place`)
    REFERENCES `scheduledb`.`Places` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '장소변경 이벤트. old_place와 new_place가 같다면 최초 등록 이벤트, 서로 다르다면 장소 변경 이벤트';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `scheduledb`.`TimeEvents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheduledb`.`TimeEvents` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `scheduledb`.`TimeEvents` (
  `seq` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `old_time` TIMESTAMP NULL,
  `adjust_mins` INT NOT NULL,
  `ref_event` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_TimeEvents_ScheduleEvents1_idx` (`ref_event` ASC),
  CONSTRAINT `fk_TimeEvents_ScheduleEvents1`
    FOREIGN KEY (`ref_event`)
    REFERENCES `scheduledb`.`ScheduleEvents` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '일정의 시간변경 이벤트';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `scheduledb`.`Attendance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scheduledb`.`Attendance` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `scheduledb`.`Attendance` (
  `seq` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ref_userevents` INT UNSIGNED NOT NULL,
  `ref_participants` INT UNSIGNED NOT NULL,
  INDEX `fk_UserEvents_has_Participants_Participants1_idx` (`ref_participants` ASC),
  INDEX `fk_UserEvents_has_Participants_UserEvents1_idx` (`ref_userevents` ASC),
  PRIMARY KEY (`seq`),
  CONSTRAINT `fk_UserEvents_has_Participants_UserEvents1`
    FOREIGN KEY (`ref_userevents`)
    REFERENCES `scheduledb`.`UserEvents` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserEvents_has_Participants_Participants1`
    FOREIGN KEY (`ref_participants`)
    REFERENCES `scheduledb`.`Participants` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
