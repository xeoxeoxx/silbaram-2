-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.32 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- silbaram 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `silbaram` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `silbaram`;

-- 테이블 silbaram.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `bdid` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text NOT NULL,
  `memberId` bigint NOT NULL,
  `regDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pageType` char(1) DEFAULT 'R',
  PRIMARY KEY (`bdid`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`memberId`) REFERENCES `member` (`mid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 silbaram.board:~0 rows (대략적) 내보내기
INSERT IGNORE INTO `board` (`bdid`, `title`, `content`, `memberId`, `regDate`, `pageType`) VALUES
	(5002, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5003, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5004, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5005, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5006, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5007, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5008, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5009, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5010, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5011, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5012, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5013, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5014, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5015, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5016, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5017, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5018, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5019, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5020, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5021, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5022, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5023, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5024, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5025, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5026, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5027, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5028, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5029, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5030, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5031, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5032, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5033, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5034, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5035, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5036, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5037, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5038, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5039, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5040, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5041, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5042, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5043, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5044, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5045, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5046, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5047, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5048, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5049, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5050, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5051, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5052, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5053, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5054, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5055, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5056, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5057, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5058, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5059, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5060, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5061, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5062, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5063, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5064, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5065, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5066, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5067, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5068, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5069, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5070, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5071, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5072, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5073, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5074, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5075, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5076, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5077, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5078, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5079, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5080, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5081, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5082, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5083, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5084, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5085, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5086, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5087, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5088, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5089, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5090, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5091, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5092, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5093, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5094, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5095, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5096, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5097, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5098, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5099, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5100, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5101, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q'),
	(5102, 'RequestBookBoardTest', '문의1', 1, '2023-05-10 06:49:03', 'Q');

-- 테이블 silbaram.book 구조 내보내기
CREATE TABLE IF NOT EXISTS `book` (
  `bkid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `writer` varchar(50) NOT NULL,
  `publisher` varchar(50) NOT NULL,
  `price` int NOT NULL,
  `synopsis` text,
  `pages` int NOT NULL,
  `isbnNum` varchar(13) DEFAULT NULL,
  `category` bigint NOT NULL,
  `fileSize` varchar(10) DEFAULT NULL,
  `language` bigint NOT NULL,
  `voiceLength` varchar(150) DEFAULT NULL,
  `bookUrl` varchar(2083) DEFAULT NULL,
  `bookTextUrl` text,
  `bookImage` longblob,
  PRIMARY KEY (`bkid`),
  KEY `category` (`category`),
  KEY `language` (`language`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`category`) REFERENCES `category` (`cid`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`language`) REFERENCES `language` (`lgid`),
  CONSTRAINT `book_ibfk_3` FOREIGN KEY (`category`) REFERENCES `category` (`cid`),
  CONSTRAINT `book_ibfk_4` FOREIGN KEY (`language`) REFERENCES `language` (`lgid`),
  CONSTRAINT `book_ibfk_5` FOREIGN KEY (`category`) REFERENCES `category` (`cid`),
  CONSTRAINT `book_ibfk_6` FOREIGN KEY (`language`) REFERENCES `language` (`lgid`)
) ENGINE=InnoDB AUTO_INCREMENT=2010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 silbaram.book:~0 rows (대략적) 내보내기
INSERT IGNORE INTO `book` (`bkid`, `name`, `writer`, `publisher`, `price`, `synopsis`, `pages`, `isbnNum`, `category`, `fileSize`, `language`, `voiceLength`, `bookUrl`, `bookTextUrl`, `bookImage`) VALUES
	(1, '책이름', '작가', '출판사', 10000, NULL, 10000, NULL, 1, NULL, 1, NULL, NULL, NULL, NULL),
	(2001, '책이름', '작가', '출판사', 10000, '줄거리', 1000, '12345', 1, '10mb', 1, '123', '123', NULL, NULL),
	(2002, 'Moby Dick', 'Herman Melville', 'Public Domain', 0, NULL, 544, NULL, 100, NULL, 201, NULL, NULL, NULL, NULL),
	(2003, 'Little Women; Or, Meg, Jo, Beth, and Amy', 'Louisa May Alcott', 'Public Domain', 0, NULL, 373, NULL, 100, NULL, 201, NULL, NULL, NULL, NULL),
	(2004, 'Metamorphosis', 'Franz Kafka', 'Public Domain', 0, NULL, 80, NULL, 100, NULL, 201, NULL, NULL, NULL, NULL),
	(2005, 'Frankenstein', 'Mary Wollstonecraft Shelley', 'Public Domain', 0, NULL, 500, NULL, 100, NULL, 201, NULL, NULL, NULL, NULL),
	(2006, 'A Doll\'s House', 'Henrik Ibsen', 'Public Domain', 0, NULL, 274, NULL, 100, NULL, 201, NULL, NULL, NULL, NULL),
	(2007, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Public Domain', 0, NULL, 411, NULL, 100, NULL, 201, NULL, NULL, NULL, NULL),
	(2008, 'The Picture of Dorian Gray', 'Oscar Wilde', 'Public Domain', 0, NULL, 189, NULL, 100, NULL, 201, NULL, NULL, NULL, NULL),
	(2009, 'Alice\'s Adventures in Wonderland', 'Lewis Carroll', 'Public Domain', 0, NULL, 899, NULL, 100, NULL, 201, NULL, NULL, NULL, NULL);

-- 테이블 silbaram.cart 구조 내보내기
CREATE TABLE IF NOT EXISTS `cart` (
  `lkid` bigint NOT NULL AUTO_INCREMENT,
  `memberId` bigint NOT NULL,
  `bookId` bigint NOT NULL,
  PRIMARY KEY (`lkid`),
  KEY `bookId` (`bookId`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`bookId`) REFERENCES `book` (`bkid`) ON DELETE CASCADE,
  CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`memberId`) REFERENCES `member` (`mid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 silbaram.cart:~0 rows (대략적) 내보내기

-- 테이블 silbaram.category 구조 내보내기
CREATE TABLE IF NOT EXISTS `category` (
  `cid` bigint NOT NULL AUTO_INCREMENT,
  `cateName` varchar(15) NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cateName` (`cateName`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 silbaram.category:~0 rows (대략적) 내보내기
INSERT IGNORE INTO `category` (`cid`, `cateName`) VALUES
	(112, '가정/생활'),
	(113, '건강/스포츠'),
	(105, '경영/경제'),
	(103, '만화/그래픽노블'),
	(100, '소설/장르문학'),
	(111, '어린이/청소년'),
	(102, '에세이/시'),
	(106, '역사/법'),
	(101, '예술/대중문화'),
	(104, '외국어'),
	(1, '임시'),
	(114, '자기계발'),
	(109, '자연과학'),
	(107, '정치/사회과학'),
	(110, '종교'),
	(108, '컴퓨터/인터넷/기술'),
	(115, '해외도서/잡지');

-- 테이블 silbaram.language 구조 내보내기
CREATE TABLE IF NOT EXISTS `language` (
  `lgid` bigint NOT NULL AUTO_INCREMENT,
  `language` varchar(15) NOT NULL,
  PRIMARY KEY (`lgid`),
  UNIQUE KEY `language` (`language`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 silbaram.language:~0 rows (대략적) 내보내기
INSERT IGNORE INTO `language` (`lgid`, `language`) VALUES
	(204, '스페인어'),
	(206, '아랍어'),
	(201, '영어'),
	(202, '일본어'),
	(203, '중국어'),
	(205, '프랑스어'),
	(1, '한국어');

-- 테이블 silbaram.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `mid` bigint NOT NULL AUTO_INCREMENT,
  `userId` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nickName` varchar(15) NOT NULL,
  `name` varchar(20) NOT NULL,
  `birthDate` date NOT NULL,
  `gender` varchar(2) NOT NULL,
  `phoneNumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `zipcode` varchar(5) DEFAULT NULL,
  `address1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `socialLogin` tinyint(1) DEFAULT '0',
  `isAdmin` tinyint(1) NOT NULL DEFAULT '0',
  `regDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `uuid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mid`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `nickName` (`nickName`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 silbaram.member:~3 rows (대략적) 내보내기
INSERT IGNORE INTO `member` (`mid`, `userId`, `password`, `nickName`, `name`, `birthDate`, `gender`, `phoneNumber`, `email`, `zipcode`, `address1`, `address2`, `socialLogin`, `isAdmin`, `regDate`, `uuid`) VALUES
	(1, 'ssss123', 'asd123123!', 'newNickName', '이름이름', '2023-04-30', 'M', '01012345678', 'ab@example.coma', NULL, NULL, NULL, 0, 0, '2023-05-04 17:06:49', NULL),
	(2, 'ididid1', 'password123', '닉a', 'John Doe', '2000-01-01', '여', '01012345679', 'a@example.coma', NULL, NULL, NULL, 0, 0, '2023-05-04 17:09:30', NULL),
	(3, 'asd123', 'asd123123!', '닉네임', '이름', '1999-01-01', 'M', '01000000014', 'a@naver.com', '02452', '서울 동대문구 경희대로4길 73 (이문동)00', NULL, 0, 0, '2023-05-09 17:20:03', NULL);

-- 테이블 silbaram.orderlist 구조 내보내기
CREATE TABLE IF NOT EXISTS `orderlist` (
  `oid` bigint NOT NULL AUTO_INCREMENT,
  `bookId` bigint NOT NULL,
  `orderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `memberId` bigint NOT NULL,
  `totalPrice` int NOT NULL DEFAULT '0',
  `orderNum` varchar(8) NOT NULL,
  PRIMARY KEY (`oid`),
  UNIQUE KEY `orderNum` (`orderNum`),
  KEY `bookId` (`bookId`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `orderlist_ibfk_1` FOREIGN KEY (`bookId`) REFERENCES `book` (`bkid`) ON DELETE CASCADE,
  CONSTRAINT `orderlist_ibfk_2` FOREIGN KEY (`memberId`) REFERENCES `member` (`mid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 silbaram.orderlist:~0 rows (대략적) 내보내기

-- 테이블 silbaram.review 구조 내보내기
CREATE TABLE IF NOT EXISTS `review` (
  `rid` bigint NOT NULL AUTO_INCREMENT,
  `memberId` bigint NOT NULL DEFAULT '0',
  `title` varchar(50) NOT NULL,
  `reviewLike` tinytext NOT NULL,
  `content` text,
  `regDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bookId` bigint NOT NULL,
  PRIMARY KEY (`rid`),
  KEY `bookId` (`bookId`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`bookId`) REFERENCES `book` (`bkid`) ON DELETE CASCADE,
  CONSTRAINT `review_ibfk_3` FOREIGN KEY (`memberId`) REFERENCES `member` (`mid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 silbaram.review:~0 rows (대략적) 내보내기

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
