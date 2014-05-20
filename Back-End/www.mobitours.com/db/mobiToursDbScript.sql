-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
-- emulateur android : 10.0.2.2
-- port : 8080
-- Client: 127.0.0.1
-- Généré le : Lundi 27 Mai 2013 à 10:42
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

-- yasmineROOT777

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `mobidb`
--

CREATE DATABASE mobidatabase;

use mobidatabase;

- --------------------------------------------------------
--
-- Structure de la table `ZONE_GEO`
--
CREATE TABLE IF NOT EXISTS `zone_geo` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `zoneGeoId` int(11) NOT NULL UNIQUE,
  `nameZoneGeo` varchar(100) not null,
  `descZoneGeo` varchar(200) not null,
  `etenduZoneGeo` varchar(100) not null,
   `latzone` float (10,6) not null,
  `lngzone` float (10,6) not null,
  `areazone` float not null,
  PRIMARY KEY (`_id`),
 CONSTRAINT UNIQUE(`zoneGeoId`)
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;

insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(1,"Nord-Kivu","Au bord du lac kivu, a cote du volcan nyiragongo","1245Km2",-1.654586,29.220371);
insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(2,"Sud-Kivu","Au bord du lac kivu, zone montagneuse","1845Km2",-2.510259,28.844948);
insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(3,"Maniema","Ville enclaver entre le Sud-Kivu, Nord-kivu et Katanga et riche en minerais","1845Km2",-2.948698,25.950222);
insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(4,"Kasai Oriental","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-2.751018,23.389893);
insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(5,"Kasai Occidental","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-5.123772,21.851807);
insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(6,"Bandundu","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-3.312077,17.385521);
insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(7,"Bas-Congo","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-5.848107,13.056049);
insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(8,"Equateur","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",0.049782,18.255844);
insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(9,"Kinshasa","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-4.318339,15.314255);
insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(10,"Province Orientale","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",0.520642,25.196114);
insert into zone_geo(`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`) values(11,"Katanga","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-11.649546,27.479553);

-- CREATION DE LA TABLE `CITY`
-- 	`kml_file` object,
--
CREATE TABLE IF NOT EXISTS `city` (
        `_id` int(11) NOT NULL AUTO_INCREMENT,
	`idcity` int(11) not null unique,
	`idzone` int(11) not null,
	`namecity` varchar(70) not null unique,
	`desccity` varchar(250),
	`latcity` float(10,6),
	`lngcity` float(10,6),
	`altcity` int,
        `cityurlimage` varchar(250),
        PRIMARY KEY(`_id`),
	CONSTRAINT UNIQUE (`idcity`),
	CONSTRAINT fk_zonegeo FOREIGN KEY(`idzone`) REFERENCES zone_geo(`zoneGeoId`)
		ON UPDATE NO ACTION ON DELETE NO ACTION
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;

--Insertion des villes

insert into city(`idcity`,`idzone`,`namecity`,`desccity`,`latcity`,`lngcity`,`altcity`,`cityurlimage`) 
     values(1,1,"Goma","Ville au bord du lac kivu et sur le flanc du Nyiragongo",-1.654586,29.220371, 1678,"http://192.168.56.1:8080/mobitours/images/test0.jpg");
insert into city(`idcity`,`idzone`,`namecity`,`desccity`,`latcity`,`lngcity`,`altcity`,`cityurlimage`) 
     values(2,1,"Beni","Une des grande ville du Grand-nord au flanc du Mt Ruwenzori",0.490946,29.458981, 1678,"http://192.168.56.1:8080/mobitours/images/cbeni.jpg");
insert into city(`idcity`,`idzone`,`namecity`,`desccity`,`latcity`,`lngcity`,`altcity`,`cityurlimage`)  
     values(3,1,"Butembo","desc desc desc desc",-0.775839,28.913802, 1678,"http://192.168.56.1:8080/mobitours/images/test1.jpg");
insert into city(`idcity`,`idzone`,`namecity`,`desccity`,`latcity`,`lngcity`,`altcity`,`cityurlimage`)  
     values(4,1,"Masisi","desc desc desc desc",-1.398324,28.818303, 1678,"http://192.168.56.1:8080/mobitours/images/test2.jpg");
insert into city(`idcity`,`idzone`,`namecity`,`desccity`,`latcity`,`lngcity`,`altcity`,`cityurlimage`)  
     values(5,1,"Sake","desc desc desc desc",-0.56122,28.705357, 1678,"http://192.168.56.1:8080/mobitours/images/test3.jpg");
insert into city(`idcity`,`idzone`,`namecity`,`desccity`,`latcity`,`lngcity`,`altcity`,`cityurlimage`)  
     values(6,1,"Kitshanga","desc desc desc desc",-0.97284,28.698214, 1678,null);
insert into city(`idcity`,`idzone`,`namecity`,`desccity`,`latcity`,`lngcity`,`altcity`,`cityurlimage`)  
     values(7,1,"Rutshuru","desc desc desc desc",-1.160556,29.3825, 1678,null);
insert into city(`idcity`,`idzone`,`namecity`,`desccity`,`latcity`,`lngcity`,`altcity`,`cityurlimage`)  
     values(8,1,"Bunagana","desc desc desc desc",-1.313035,29.595378, 1678,null);
insert into city(`idcity`,`idzone`,`namecity`,`desccity`,`latcity`,`lngcity`,`altcity`,`cityurlimage`)  
     values(9,1,"Rumangabo","desc desc desc desc",-1.357663,29.369063, 1678,null);

-- --------------------------------------------------------
--
-- Structure de la table `HOTEL`
--
CREATE TABLE IF NOT EXISTS `hotel` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `pointofinterestid` int(11) NOT NULL,
  `idcity` int(11),
  `title` varchar(100) not null,
  `type` varchar(120) not null,
  `url` varchar(120),
  `latitude` float(10,6) not null,
  `longitude` float(10,6) not null,
  `adresse` varchar(250),
  `deschotel` varchar(250),
  `star` int(11),
  `nbroom` int(11),
  `nbroomdispo` int(11),
  `roompricemin` float,
  `roompricemax` float,
  `mail` varchar(60),  
  `pictureurl` varchar(100),
  PRIMARY KEY(`_id`),
  CONSTRAINT UNIQUE (`pointofinterestid`),
  CONSTRAINT city_id_fk FOREIGN KEY(`idcity`) REFERENCES city(`idcity`)
	ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;


insert into hotel(`pointofinterestid`,`idcity`,`title`,`type`,`url`,`latitude`,`longitude`,`adresse`,`deschotel`,`star`,`nbroom`,`roompricemin`,`roompricemax`,`mail`,`pictureurl`,`nbroomdispo`) values
        (1,1,"Grands Lacs Hotel","hotel","www.grandslacshotel.com",-1.690314,29.238444,"Bld Kanyamunga, ville","One of old goma's hotels","2",50,65,200,"","http://192.168.56.1:8080/mobitours/images/goma.jpg",12);
insert into hotel(`pointofinterestid`,`idcity`,`title`,`type`,`url`,`latitude`,`longitude`,`adresse`,`deschotel`,`star`,`nbroom`,`roompricemin`,`roompricemax`,`mail`,`pictureurl`,`nbroomdispo`) values
        (2,1,"New look Hotel","hotel","www.newlookhotel-goma.com",-1.69303,29.238688,"Bld Kanyamunga, ville","Hotel with all you want","2",20,50,125,"","http://192.168.56.1:8080/mobitours/images/chalet_a.jpg",2);
insert into hotel(`pointofinterestid`,`idcity`,`title`,`type`,`url`,`latitude`,`longitude`,`adresse`,`deschotel`,`star`,`nbroom`,`roompricemin`,`roompricemax`,`mail`,`pictureurl`,`nbroomdispo`) values
        (3,1,"Ihusi Hotel","hotel","www.ihusi.com",-1.698727,29.242473,"Bld Kanyamunga, ville","one of the best of goma","4",180,65,500,"","http://192.168.56.1:8080/mobitours/images/chalet_b.jpg",3);
insert into hotel(`pointofinterestid`,`idcity`,`title`,`type`,`url`,`latitude`,`longitude`,`adresse`,`deschotel`,`star`,`nbroom`,`roompricemin`,`roompricemax`,`mail`,`pictureurl`,`nbroomdispo`) values
        (4,1,"Test Hotel","hotel","www.cosyscd.com",-1.69681,29.241811,"Bld Kanyamunga, ville","just for testing 1","4",18,25,200,"","http://192.168.56.1:8080/mobitours/images/hotel_ihusi.jpg",11);
insert into hotel(`pointofinterestid`,`idcity`,`title`,`type`,`url`,`latitude`,`longitude`,`adresse`,`deschotel`,`star`,`nbroom`,`roompricemin`,`roompricemax`,`mail`,`pictureurl`,`nbroomdispo`) values
        (5,1,"Test Big Hotel","hotel","www.mobilex.com",-1.68378,29.234918,"Bld Kanyamunga, ville","just another for testing","4",200,25,700,"","http://192.168.56.1:8080/mobitours/images/chalet_c.jpg",46);
insert into hotel(`pointofinterestid`,`idcity`,`title`,`type`,`url`,`latitude`,`longitude`,`adresse`,`deschotel`,`star`,`nbroom`,`roompricemin`,`roompricemax`,`mail`,`pictureurl`,`nbroomdispo`) values
        (6,1,"Hotel Bassin du Congo","hotel","www.bassincongo.com",-1.694169,29.242376,"Bld Kanyamunga, ville","just another for testing","4",140,30,200,"","http://192.168.56.1:8080/mobitours/images/chalet_d.jpg",100);
insert into hotel(`pointofinterestid`,`idcity`,`title`,`type`,`url`,`latitude`,`longitude`,`adresse`,`deschotel`,`star`,`nbroom`,`roompricemin`,`roompricemax`,`mail`,`pictureurl`,`nbroomdispo`) values
        (7,1,"Centre d\'acceuil Caritas","hotel","www.caritas.cd",-1.700056,29.242473,"Bld Kanyamunga, ville","just another for testing","4",40,25,125,"","",13);


-- --------------------------------------------------------
--
-- Structure de la table `RESTAURANT`
--

CREATE TABLE IF NOT EXISTS `restaurant` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `pointofinterestid` int(11) NOT NULL,
  `idcity` int(11),
  `title` varchar(100) not null,
  `type` varchar(120) not null,
  `url` varchar(120),
  `classification` varchar(75),
  `latitude` float(10,6) not null,
  `longitude` float(10,6) not null,
  `description` varchar(250),
  `mealofday` varchar(250),
  `mealprice` varchar(250),  
  `pictureurl` varchar(100),
  PRIMARY KEY (`_id`),
  CONSTRAINT UNIQUE  (`pointofinterestid`),
  CONSTRAINT city_id_fk FOREIGN KEY(`idcity`) REFERENCES city(`idcity`)
	ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;

-- --------------------------------------------------------
--
-- Structure de la table `SITENATUREL`
--

CREATE TABLE IF NOT EXISTS `sitenaturel` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `idsite` int(11) NOT NULL,
  `idcity` int(11),
  `title` varchar(200) not null,
  `type` varchar(65) not null,
  `area` float(10,6),
  `sitedesc` varchar(100),
  `latitude` float(10,6) not null,
  `longitude` float(10,6) not null,
  `attractourist` varchar(250),
  `largeur` float(10,6) not null,
  `longueur` float(10,6) not null,
  `security` varchar(1), 
  `visitorperan` int(11),
  `site_url_image` varchar(250),
  PRIMARY KEY (`_id`),
  CONSTRAINT UNIQUE (`idsite`),
  CONSTRAINT city_site_id_fk FOREIGN KEY(`idcity`) REFERENCES city(`idcity`)
	ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;

insert into sitenaturel(`_id`,`idsite`,`idcity`,`title`,`type`,`area`,`sitedesc`,`latitude`,`longitude`,`attractourist`,
        `largeur`,`longueur`,`security`,`visitorperan`,`site_url_image`) 
    values(1,1,1,"Parc National des Virunga","Parc","","",-0.383333,29.5,"Un des plus vieux parcs",0,0,"N",255,null);
insert into sitenaturel(`_id`,`idsite`,`idcity`,`title`,`type`,`area`,`sitedesc`,`latitude`,`longitude`,`attractourist`,
        `largeur`,`longueur`,`security`,`visitorperan`,`site_url_image`) 
    values(2,2,1,"Montagnes du Virunga","Site","","",-1.416667,29.5,"Parmis les plus hautes",0,0,"N",35,null);
insert into sitenaturel(`_id`,`idsite`,`idcity`,`title`,`type`,`area`,`sitedesc`,`latitude`,`longitude`,`attractourist`,
        `largeur`,`longueur`,`security`,`visitorperan`,`site_url_image`) 
    values(3,3,1,"Nyamurariga","Site","","",-1.408,29.2,"Un des volcans actifs",0,0,"Y",1245,null);
insert into sitenaturel(`_id`,`idsite`,`idcity`,`title`,`type`,`area`,`sitedesc`,`latitude`,`longitude`,`attractourist`,
        `largeur`,`longueur`,`security`,`visitorperan`,`site_url_image`) 
    values(4,4,1,"Sabyinyo","Site","","",-1.388,29.592,"Un des volcans actifs",0,0,"Y",1545,null);

-- --------------------------------------------------------
--
-- Structure de la table `BOOKING`
--

CREATE TABLE IF NOT EXISTS `booking` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `bookingid` int(11) NOT NULL,
  `idcity` int(11),
  `bookingdesc` varchar(250),
  `datetrip` int(11),
  `duration` int(11),
  `paymentmode` varchar(150),
  `bookingdate` int(11),
  `userid` int(11),
  `courtierid` int(11),
  `pointofinterestid` int(11),
  `idsite` int(11), 
  PRIMARY KEY (`_id`),
  CONSTRAINT UNIQUE (`bookingid`)
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `otherpoi` (
   `_id` int(11) NOT NULL AUTO_INCREMENT,
  `pointofinterestid` int(11) NOT NULL,
  `idcity` int(11),
  `title` varchar(100) not null,
  `type` varchar(120) not null,
  `url` varchar(120),
  `specification` varchar(100),
  `latitude` float(10,6) not null,
  `longitude` float(10,6) not null,
  `description` varchar(250),  
  `pictureurl` varchar(100),
  PRIMARY KEY (`_id`),
  CONSTRAINT UNIQUE (`pointofinterestid`),
  CONSTRAINT city_id_fk FOREIGN KEY(`idcity`) REFERENCES city(`idcity`)
	ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `courtier` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `courtierid` int(11) not null,
  `email` varchar(100) not null,
  `password` varchar(100) not null,
  `title` varchar(100),
  `lastname` varchar(100),
  `firstname` varchar(100),
  `website` varchar(100),
  `city` varchar(100),
  `country` varchar(100),
  `company` varchar(100),
  `phone` varchar(100),
  `biography` varchar(100),
  `specification` varchar(200),
  PRIMARY KEY (`_id`),
  CONSTRAINT UNIQUE (`courtierid`)
  
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;

-- --------------------------------------------------------
--
-- Structure de la table `user`
--
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `userid` int(11) not null ,
  `usern` varchar(15) NOT NULL default '',
  `pswd` varchar(15) NOT NULL default '',
  `type-acc` varchar(15) NOT NULL,
  `email` varchar(100) not null,
  `title` varchar(20),
  `lastname` varchar(100),
  `firstname` varchar(100),
  `website` varchar(100),
  `city` varchar(100),
  `country` varchar(100),
  `company` varchar(100),
  `phone` varchar(100),
  `biography` varchar(200),
  `Uid` varchar(100) unique,
  CONSTRAINT user_pk PRIMARY KEY (`userid`)
) ENGINE = MYISAM ;

-- Quelques insertions des utilisateurs
INSERT INTO `users` VALUES (1,'admin', 'root', 'admin','misamuna@gmail.com','M','Isamuna','Michel','www.mobitours.com','Goma','DRC','Cosys.cd','0811704141','software developer','abc2319yasm88');
INSERT INTO `users` VALUES (2,'michel','yann2013','user','misamuna@wwfcarpo.org','M','Isamuna','Michel','www.mobitours.com','Goma','DRC','Cosys.cd','0811704141','software developer','nicor76hsh');
INSERT INTO `users` VALUES (3,'visitor','visitor0','user','misamuna@wwfcarpo.org','M','Isamuna','Michel','www.mobitours.com','Goma','DRC','Cosys.cd','0811704141','software developer','yauie0746g');


CREATE TABLE IF NOT EXISTS `comments` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  --`commentid` int(11) NOT NULL ,
  `userid` int(11),
  `pointofinterestid` int(11),
  `idsite` int(11),
  `text` varchar(200) NOT NULL default '',
  `date` int(11),
  PRIMARY KEY (`_id`),
  --CONSTRAINT UNIQUE (`commentid`),
  CONSTRAINT user_id_fk FOREIGN KEY(`userid`) REFERENCES users(`userid`)
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `rating` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11),
  `pointofinterestid` int(11),
  `idsite` int(11),
  `rating` float(10,6) not null,
  `date` int(11),
  PRIMARY KEY (`_id`),
  CONSTRAINT user_rat_id_fk FOREIGN KEY(`userid`) REFERENCES users(`userid`)
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;

insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,1,"",5,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,1,"",3,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,3,"",4,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,3,"",5,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,3,"",4,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,4,"",2,"");
--Concernant les sites naturels
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",1,4,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",1,3,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",1,5,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",2,3,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",2,2,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",2,5,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",2,3,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",2,5,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",2,2,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",3,5,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",3,2,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",3,2,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",4,2,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",4,3,"");
insert into rating(`userid`,`pointofinterestid`,`idsite`,`rating`,`date`)
            values(1,"",4,1,"");

CREATE TABLE IF NOT EXISTS `datatype` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `dataid` int(11) NOT NULL,
  `titletype` varchar(30),
  PRIMARY KEY (`_id`),
  CONSTRAINT UNIQUE (`dataid`)
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `types` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `typeid` int(11) NOT NULL,
  `type` varchar(50),
  PRIMARY KEY (`_id`),
  CONSTRAINT UNIQUE (`typeid`)
) ENGINE = MYISAM DEFAULT charset=utf8 AUTO_INCREMENT=1;

insert into datatype(`dataid`,`titletype`) values(1,'hotel');
insert into datatype(`dataid`,`titletype`) values(2,'restaurant');
insert into datatype(`dataid`,`titletype`) values(3,'others');

insert into types(`typeid`,`type`) values(1,'Hotel');
insert into types(`typeid`,`type`) values(2,'Restaurant');
insert into types(`typeid`,`type`) values(3,'Nature');
insert into types(`typeid`,`type`) values(4,'Culture');
insert into types(`typeid`,`type`) values(5,'Atm');
insert into types(`typeid`,`type`) values(6,'Petrol station');
insert into types(`typeid`,`type`) values(7,'Pharmacies');
insert into types(`typeid`,`type`) values(8,'Hospital');
insert into types(`typeid`,`type`) values(9,'Shopping');
insert into types(`typeid`,`type`) values(10,'schools');
-- -----------------------------------------------------------------------
--
-- TEST FOR INSERTING AN IMAGE IN MYSQL
-- name varchar(25) not null default '',
--
create table `image` (
    `id` int(11)  not null AUTO_INCREMENT,
    `pointofinterestid` int(11) not null,
    `name` varchar(200),
    `url` varchar(120),
    `image` blob not null,
    `imagetype` varchar(200) NOT NULL,
    PRIMARY KEY(`id`)
 )ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Image informations' AUTO_INCREMENT=1;