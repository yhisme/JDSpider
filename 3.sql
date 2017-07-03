/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.53 : Database - jd
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jd` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jd`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `Crawling_time` varchar(50) DEFAULT NULL,
  `Goods_ID` bigint(20) NOT NULL,
  `Goods_Name` varchar(500) DEFAULT NULL,
  `Goods_params` varchar(2000) DEFAULT NULL,
  `self_support` tinyint(1) DEFAULT NULL COMMENT '是否自营',
  `Return_Support` tinyint(1) DEFAULT NULL COMMENT '是否支持7天',
  `Goods_brand` varchar(255) DEFAULT NULL,
  `classification` varchar(255) DEFAULT NULL COMMENT '分类',
  `Goods_img` varchar(2000) DEFAULT NULL COMMENT 'img url',
  `comment_count` varchar(20) DEFAULT NULL,
  `comment_goodrate` varchar(20) DEFAULT NULL COMMENT '好评率',
  `Goods_feature` varchar(2000) DEFAULT NULL,
  `Goods_price` varchar(20) DEFAULT NULL,
  `Shop_Name` varchar(50) DEFAULT NULL,
  `Shop_ID` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Goods_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `jdgoodsid` */

DROP TABLE IF EXISTS `jdgoodsid`;

CREATE TABLE `jdgoodsid` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
