/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 10.1.31-MariaDB : Database - reservasi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`reservasi` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `reservasi`;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `no_ktp_customer` varchar(16) NOT NULL DEFAULT '',
  `nama_customer` varchar(45) DEFAULT NULL,
  `alamat_customer` varchar(100) DEFAULT NULL,
  `no_telp_customer` varchar(15) DEFAULT NULL,
  `email_customer` varchar(50) DEFAULT NULL,
  `jenis_kelamin` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`no_ktp_customer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customer` */

insert  into `customer`(`no_ktp_customer`,`nama_customer`,`alamat_customer`,`no_telp_customer`,`email_customer`,`jenis_kelamin`) values 
('0000000000000000','Wiby','test','123456','test@','Laki-laki'),
('1111111111111111','Vega','Cimahi','1313231332133','asaasa@daadsaaassasa','Laki-laki'),
('1234567890123456','Test','Test','086','test@','Laki-laki'),
('1234567890123458','test2','czxczxczxc','13242423','zxcasdadas@','Perempuan');

/*Table structure for table `jabatan` */

DROP TABLE IF EXISTS `jabatan`;

CREATE TABLE `jabatan` (
  `id_jabatan` int(11) NOT NULL AUTO_INCREMENT,
  `nama_jabatan` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_jabatan`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `jabatan` */

insert  into `jabatan`(`id_jabatan`,`nama_jabatan`) values 
(1,'Manager'),
(2,'Resepsionis'),
(3,'Office Boy');

/*Table structure for table `kamar` */

DROP TABLE IF EXISTS `kamar`;

CREATE TABLE `kamar` (
  `id_kamar` int(11) NOT NULL,
  `kapasitas` int(11) DEFAULT NULL,
  `id_room` int(11) DEFAULT NULL,
  `status` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id_kamar`),
  KEY `id_room` (`id_room`),
  CONSTRAINT `kamar_ibfk_1` FOREIGN KEY (`id_room`) REFERENCES `room` (`id_room`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `kamar` */

insert  into `kamar`(`id_kamar`,`kapasitas`,`id_room`,`status`) values 
(100,1,1,'Available'),
(101,1,1,'Available'),
(102,2,2,'Available'),
(103,2,2,'Available'),
(104,4,3,'Available'),
(105,4,3,'Available');

/*Table structure for table `pegawai` */

DROP TABLE IF EXISTS `pegawai`;

CREATE TABLE `pegawai` (
  `id_pegawai` int(11) NOT NULL AUTO_INCREMENT,
  `nama_pegawai` varchar(50) DEFAULT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gaji` double DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(8) DEFAULT NULL,
  `id_jabatan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pegawai`),
  KEY `id_jabatan` (`id_jabatan`),
  CONSTRAINT `pegawai_ibfk_1` FOREIGN KEY (`id_jabatan`) REFERENCES `jabatan` (`id_jabatan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `pegawai` */

insert  into `pegawai`(`id_pegawai`,`nama_pegawai`,`no_telp`,`alamat`,`email`,`gaji`,`username`,`password`,`id_jabatan`) values 
(1,'Abi','085659156156','Griya Pesantren Indah C-3','abi_vegari@ymail.com',3000000,'manager','123',1),
(2,'Rangga','081223138056','Pesona Taman Burung Blok A2 - 35','rangga.adikusuma@ymail.com',1000000,'resepsionis1','123',2),
(3,'Shony',NULL,NULL,NULL,1000000,'resepsionis2','123',2);

/*Table structure for table `res_detail` */

DROP TABLE IF EXISTS `res_detail`;

CREATE TABLE `res_detail` (
  `rd_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `no_reservasi` int(11) DEFAULT '0',
  `quantity` int(11) DEFAULT NULL,
  `room_price` double DEFAULT '0',
  `tanggal_check_in` date DEFAULT NULL,
  `tanggal_check_out` date DEFAULT NULL,
  `status_reservasi` varchar(11) DEFAULT NULL,
  `id_room` int(11) DEFAULT NULL,
  PRIMARY KEY (`rd_id`),
  KEY `no_reservasi` (`no_reservasi`),
  KEY `id_room` (`id_room`),
  CONSTRAINT `res_detail_ibfk_1` FOREIGN KEY (`no_reservasi`) REFERENCES `reservasi` (`no_reservasi`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `res_detail_ibfk_2` FOREIGN KEY (`id_room`) REFERENCES `room` (`id_room`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;

/*Data for the table `res_detail` */

insert  into `res_detail`(`rd_id`,`no_reservasi`,`quantity`,`room_price`,`tanggal_check_in`,`tanggal_check_out`,`status_reservasi`,`id_room`) values 
(45,30,2,400000,'2018-04-28','2018-04-29','CHECKED-OUT',3),
(46,31,2,400000,'2018-04-28','2018-04-29','CHECKED-OUT',3),
(47,32,2,400000,'2018-04-28','2018-04-29','CHECKED-OUT',3),
(48,33,2,400000,'2018-04-28','2018-04-29','CHECKED-OUT',3),
(49,34,2,400000,'2018-04-28','2018-04-30','CHECKED-OUT',3),
(53,36,2,180000,'2018-04-28','2018-04-30','CHECKED-OUT',2);

/*Table structure for table `reservasi` */

DROP TABLE IF EXISTS `reservasi`;

CREATE TABLE `reservasi` (
  `no_reservasi` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal_reservasi` date DEFAULT NULL,
  `no_ktp_customer` varchar(16) DEFAULT '',
  `total` double DEFAULT NULL,
  `id_pegawai` int(11) DEFAULT NULL,
  PRIMARY KEY (`no_reservasi`),
  KEY `id_customer_FK` (`no_ktp_customer`),
  KEY `id_pegawai` (`id_pegawai`),
  CONSTRAINT `reservasi_ibfk_1` FOREIGN KEY (`no_ktp_customer`) REFERENCES `customer` (`no_ktp_customer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservasi_ibfk_2` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id_pegawai`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

/*Data for the table `reservasi` */

insert  into `reservasi`(`no_reservasi`,`tanggal_reservasi`,`no_ktp_customer`,`total`,`id_pegawai`) values 
(30,'2018-04-28','0000000000000000',800000,1),
(31,'2018-04-28','1234567890123456',800000,1),
(32,'2018-04-28','1234567890123456',800000,1),
(33,'2018-04-28','1234567890123456',800000,1),
(34,'2018-04-28','1234567890123456',1600000,1),
(36,'2018-04-28','1234567890123456',720000,1);

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id_room` int(11) NOT NULL,
  `jenis_room` varchar(20) DEFAULT NULL,
  `harga` double DEFAULT '0',
  `stock_room` int(11) DEFAULT '0',
  `kapasitas` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_room`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `room` */

insert  into `room`(`id_room`,`jenis_room`,`harga`,`stock_room`,`kapasitas`) values 
(1,'Greenhorn',120000,2,1),
(2,'Sweet Honey Room',180000,2,2),
(3,'Family Room',400000,2,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
