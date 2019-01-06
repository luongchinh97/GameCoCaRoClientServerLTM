-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: gamecocaro
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.30-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `banbe`
--

DROP TABLE IF EXISTS `banbe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banbe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idBanBe` int(11) NOT NULL,
  `idNguoiDung` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_nguoidung_idx` (`idNguoiDung`),
  KEY `fk_banbe_idx` (`idBanBe`),
  CONSTRAINT `fk_banbe` FOREIGN KEY (`idBanBe`) REFERENCES `nguoidung` (`idNguoiDung`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_nguoidung` FOREIGN KEY (`idNguoiDung`) REFERENCES `nguoidung` (`idNguoiDung`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banbe`
--

LOCK TABLES `banbe` WRITE;
/*!40000 ALTER TABLE `banbe` DISABLE KEYS */;
INSERT INTO `banbe` VALUES (1,1,8),(2,2,8),(3,3,8),(4,4,8),(5,8,1),(6,8,2),(7,8,3),(8,8,4);
/*!40000 ALTER TABLE `banbe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoidung`
--

DROP TABLE IF EXISTS `nguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nguoidung` (
  `idNguoiDung` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `avata` varchar(45) COLLATE utf8_bin NOT NULL,
  `loaiHang` varchar(20) COLLATE utf8_bin NOT NULL,
  `xepHang` int(11) NOT NULL,
  `soTranThang` int(11) NOT NULL,
  `soTranThua` int(11) NOT NULL,
  `diem` int(11) NOT NULL,
  `tien` int(20) NOT NULL,
  `tinhTrang` int(1) NOT NULL,
  PRIMARY KEY (`idNguoiDung`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidung`
--

LOCK TABLES `nguoidung` WRITE;
/*!40000 ALTER TABLE `nguoidung` DISABLE KEYS */;
INSERT INTO `nguoidung` VALUES (1,'chinhdzvc','chinh123','LuongChinh','nguoiSoi1.png','Hoc vien',1,2,0,10,1200,1),(2,'nuctran','nuc123','TranNuc','nguoiSoi1.png','Hoc vien',2,1,2,10,900,0),(3,'q','q','Quynh Mup Ghe','nguoiSoi1.png','Hoc vien',3,2,1,0,900,0),(4,'a','a','An Am Anh','nguoiSoi1.png','Hoc vien',5,0,0,0,1000,0),(8,'2','2','2 Khôi Hai','nguoiSat1.png','Hoc vien',4,0,0,0,1000,0),(9,'nucbb','nuc123','nucbb','nguoiSoi1.png','Hoc vien',6,0,0,0,1000,0),(10,'3','3','3','nguoiSoi1.png','Hoc vien',7,0,0,0,1000,0),(11,'quyetdaik','quyet123','quyetdaik','nguoiSoi1.png','Hoc vien',8,0,0,0,1000,1);
/*!40000 ALTER TABLE `nguoidung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong`
--

DROP TABLE IF EXISTS `phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phong` (
  `idPhong` int(11) NOT NULL AUTO_INCREMENT,
  `soLuongNguoiChoi` int(11) NOT NULL,
  `soTienCuoc` int(11) NOT NULL,
  `tinhTrang` int(11) NOT NULL,
  PRIMARY KEY (`idPhong`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong`
--

LOCK TABLES `phong` WRITE;
/*!40000 ALTER TABLE `phong` DISABLE KEYS */;
INSERT INTO `phong` VALUES (1,1,100,0),(2,1,500,0),(3,0,0,0),(4,0,0,0),(5,0,0,0),(6,0,0,0),(7,0,0,0);
/*!40000 ALTER TABLE `phong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong_nguoidung`
--

DROP TABLE IF EXISTS `phong_nguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phong_nguoidung` (
  `idPhong` int(11) NOT NULL,
  `idNguoiChoi` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  KEY `fk_nguoichoi_idPhong_idx` (`idPhong`),
  KEY `fk_nguoichoi_idNguoidung_idx` (`idNguoiChoi`),
  CONSTRAINT `fk_nguoichoi_idNguoidung` FOREIGN KEY (`idNguoiChoi`) REFERENCES `nguoidung` (`idNguoiDung`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_nguoichoi_idPhong` FOREIGN KEY (`idPhong`) REFERENCES `phong` (`idPhong`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_nguoidung`
--

LOCK TABLES `phong_nguoidung` WRITE;
/*!40000 ALTER TABLE `phong_nguoidung` DISABLE KEYS */;
/*!40000 ALTER TABLE `phong_nguoidung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gamecocaro'
--

--
-- Dumping routines for database 'gamecocaro'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-31 18:04:56
