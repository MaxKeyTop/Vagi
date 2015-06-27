-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: vagi
-- ------------------------------------------------------
-- Server version	5.5.23-log

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
-- Table structure for table `login_history`
--

DROP TABLE IF EXISTS `login_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_history` (
  `ID` varchar(45) NOT NULL COMMENT '主键',
  `USERNAME` varchar(200) NOT NULL COMMENT '登录名',
  `DISPLAYNAME` varchar(45) DEFAULT NULL COMMENT '姓名',
  `MESSAGE` varchar(200) DEFAULT NULL COMMENT '消息',
  `SOURCEIP` varchar(45) DEFAULT NULL COMMENT '操作IP',
  `LOGINTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '认证时间',
  `LOGINTYPE` varchar(45) DEFAULT NULL COMMENT '类型',
  `UID` varchar(45) NOT NULL COMMENT '用户ID',
  `CODE` varchar(45) DEFAULT NULL COMMENT '编码',
  `PROVIDER` varchar(45) DEFAULT NULL COMMENT '第三方',
  `SESSIONID` varchar(45) DEFAULT NULL COMMENT '会话',
  `BROWSER` varchar(45) DEFAULT NULL COMMENT '浏览器版本',
  `PLATFORM` varchar(45) DEFAULT NULL COMMENT '平台',
  `APPLICATION` varchar(45) DEFAULT NULL COMMENT '应用程序',
  `LOGINURL` varchar(450) DEFAULT NULL COMMENT '登录URL',
  `LOGOUTTIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '登出时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_history`
--

LOCK TABLES `login_history` WRITE;
/*!40000 ALTER TABLE `login_history` DISABLE KEYS */;
INSERT INTO `login_history` VALUES ('0003a5c7-1d99-4cec-b877-b95d091121cd','admin','admin','Success','127.0.0.1','2015-06-27 03:05:58','SocialSignOn','12121212','100000','Web','b8aec469-1ae9-49ce-a460-c067b1f7c204','Chrome/44','Windows NT 6.1','Browser','2015-06-27 11:05:58','0000-00-00 00:00:00'),('09ad1a55-fabb-491e-88b2-8cb7c67c65c9','admin','admin','Success','127.0.0.1','2015-06-27 04:10:56','RemeberMe','12121212','100000','Web','e5797926-1690-438d-a104-c3269df504c3','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:10:56','0000-00-00 00:00:00'),('0ad479e1-8cce-4e91-bfbe-b8c8b6735811','admin','admin','Success','127.0.0.1','2015-06-27 04:19:36','RemeberMe','12121212','100000','Web','6b1a7a2e-d90a-4b5c-b0eb-11c8621e7139','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:19:36','0000-00-00 00:00:00'),('17f17eb0-a600-45cd-863c-2aa520c8e8a0','admin','admin','Success','127.0.0.1','2015-06-27 04:14:43','RemeberMe','12121212','100000','Web','c610a613-545a-4c54-a3f1-7206e15eeb24','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:14:43','0000-00-00 00:00:00'),('19517cd3-5325-41aa-98ce-49762d9bdde4','admin','admin','Success','127.0.0.1','2015-06-27 04:10:59','RemeberMe','12121212','100000','Web','102ff859-7970-4c75-be9c-e29d6a88069f','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:10:59','0000-00-00 00:00:00'),('1a6d6fd0-775a-4f78-8ba6-f35001ec0093','admin','admin','Success','127.0.0.1','2015-06-27 03:22:40','WebLogin','12121212','100000','Web','39cfbeac-2998-4a89-a540-98a57af77adb','Chrome/44','Windows NT 6.1','Browser','2015-06-27 11:22:40','0000-00-00 00:00:00'),('22aff8a5-4551-4801-832b-e1da7481e4fd','admin','admin','Success','127.0.0.1','2015-06-26 15:02:46','WebLogin','12121212','100000','Web','9604261d-cead-481e-9123-5081002658ef','Chrome/44','Windows NT 6.1','Browser','2015-06-26 23:02:46','0000-00-00 00:00:00'),('27c2a586-ffe3-4ad8-98db-fb8cbb8367de','admin','admin','Success','127.0.0.1','2015-06-25 05:00:10','WebLogin','12121212','100000','Web','559a079c-a727-4dfa-a347-6a0eda71ad00','Chrome/44','Windows NT 6.1','Browser','2015-06-25 13:00:10','0000-00-00 00:00:00'),('2b7ddd21-4328-4a3b-b194-4b368349a47b','admin','admin','Success','127.0.0.1','2015-06-27 03:04:56','WebLogin','12121212','100000','Web','c688b14d-0bff-4b42-9db3-6c0c9397ee18','Chrome/44','Windows NT 6.1','Browser','2015-06-27 11:04:56','0000-00-00 00:00:00'),('30122064-b63e-4245-ad7b-8fe145103333','admin','admin','Success','127.0.0.1','2015-06-26 14:00:57','WebLogin','12121212','100000','Web','66f27007-1a40-439a-9fee-1c714d162fa9','Chrome/44','Windows NT 6.1','Browser','2015-06-26 22:00:57','0000-00-00 00:00:00'),('3d776970-2fb3-4e46-b643-6692c8e7275e','admin','admin','Success','127.0.0.1','2015-06-27 01:46:08','WebLogin','12121212','100000','Web','1dbb5519-b9fe-48a8-9e9d-7f98d5c7b3ba','Chrome/44','Windows NT 6.1','Browser','2015-06-27 09:46:08','0000-00-00 00:00:00'),('3fdf1917-eabc-4594-8151-d405db6c972f','admin','admin','Success','127.0.0.1','2015-06-27 03:23:34','WebLogin','12121212','100000','Web','da6086a4-a3e4-4c5b-be32-a8ae6312e2c0','Chrome/44','Windows NT 6.1','Browser','2015-06-27 11:23:34','0000-00-00 00:00:00'),('402bca72-d117-4824-ac3c-66fb19b2c64e','admin','admin','Success','127.0.0.1','2015-06-27 04:10:25','RemeberMe','12121212','100000','Web','02203340-da51-46bf-86a1-e2d1e3119307','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:10:25','0000-00-00 00:00:00'),('5a4c6cba-de60-485b-92cc-d771daae3cb2','admin','admin','Success','127.0.0.1','2015-06-27 04:11:02','RemeberMe','12121212','100000','Web','da26a0c4-87e3-4ab3-aab6-2c30d2d6edbb','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:11:02','0000-00-00 00:00:00'),('5abf64a3-f34e-442e-944d-47ddfc13fad9','admin','admin','Success','127.0.0.1','2015-06-27 04:11:35','RemeberMe','12121212','100000','Web','eb7f179e-b3fe-4899-bd8a-97f643e22f0e','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:11:35','0000-00-00 00:00:00'),('5fe14530-f9b3-4392-b76d-6546939f654b','admin','admin','Success','127.0.0.1','2015-06-26 09:04:32','WebLogin','12121212','100000','Web','dec895a9-48db-4550-9bf0-e13c13f6ab3d','Chrome/44','Windows NT 6.1','Browser','2015-06-26 17:04:32','0000-00-00 00:00:00'),('6190f8aa-aafd-4dc3-967c-9d5bbb172f61','admin','admin','Success','127.0.0.1','2015-06-26 08:26:38','WebLogin','12121212','100000','Web','f4237ff4-da6a-433a-893c-64a9f3e2b963','Chrome/44','Windows NT 6.1','Browser','2015-06-26 16:26:38','0000-00-00 00:00:00'),('6497b738-78fb-4a2d-ace0-ae2a321c78b8','admin','admin','Success','127.0.0.1','2015-06-26 16:12:02','WebLogin','12121212','100000','Web','d284e937-c399-4d42-8e50-b1b9f10933fd','Chrome/44','Windows NT 6.1','Browser','2015-06-27 00:12:02','0000-00-00 00:00:00'),('6d680e79-3717-4ecd-a314-867b22c4ea1e','admin','admin','Success','127.0.0.1','2015-06-27 04:39:40','SocialSignOn','12121212','100000','Web','12d793f1-621e-4efa-9160-d244beeeffd5','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:39:40','0000-00-00 00:00:00'),('725ffa33-a3c5-4cc3-8bd7-f906f5267ce1','admin','admin','Success','127.0.0.1','2015-06-27 02:16:00','WebLogin','12121212','100000','Web','5bc005e9-c558-4531-9180-dfec6ff9c177','Chrome/44','Windows NT 6.1','Browser','2015-06-27 10:16:00','0000-00-00 00:00:00'),('8920d74b-c83a-4832-b635-0c0c0eb3eb6e','admin','admin','Success','127.0.0.1','2015-06-27 01:30:40','WebLogin','12121212','100000','Web','71779b43-721d-44c5-813b-a24bb67c363b','Chrome/44','Windows NT 6.1','Browser','2015-06-27 09:30:40','0000-00-00 00:00:00'),('91d42c05-b7de-4007-83fd-026b701dc01c','admin','admin','Success','127.0.0.1','2015-06-26 15:48:43','WebLogin','12121212','100000','Web','2ce47ad3-9840-4270-8610-ff96102ed154','Chrome/44','Windows NT 6.1','Browser','2015-06-26 23:48:43','0000-00-00 00:00:00'),('9c456838-5285-4f54-8ead-610210846e53','admin','admin','Success','127.0.0.1','2015-06-27 04:15:02','RemeberMe','12121212','100000','Web','cbd1d7b3-b5e0-4925-ab70-6a4f4f96ed44','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:15:02','0000-00-00 00:00:00'),('a261b22d-fb3c-449e-b60b-3c2c329f9708','admin','admin','Success','127.0.0.1','2015-06-27 04:14:23','RemeberMe','12121212','100000','Web','41043077-9423-4f98-8b06-62c8bb942be1','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:14:23','0000-00-00 00:00:00'),('a8827421-18a3-46cb-9f7f-8d57c443846f','admin','admin','Success','127.0.0.1','2015-06-27 02:16:36','WebLogin','12121212','100000','Web','bb1e091e-80c2-41ee-87b6-10f51ecaa3a7','Chrome/44','Windows NT 6.1','Browser','2015-06-27 10:16:36','0000-00-00 00:00:00'),('be55042b-acc0-4ab6-9197-ae9f97e15787','admin','admin','Success','127.0.0.1','2015-06-27 04:10:49','RemeberMe','12121212','100000','Web','4e0027d9-3e7b-417f-9721-fd7ee2e8b098','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:10:49','0000-00-00 00:00:00'),('d7f1ee9a-562f-47d7-9cea-8ae73453f556','admin','admin','Success','127.0.0.1','2015-06-27 04:11:06','RemeberMe','12121212','100000','Web','0b55cfb9-84ba-4245-b623-fa84adcd479f','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:11:06','0000-00-00 00:00:00'),('d8932e36-5594-4a23-8e0e-542d741db05a','admin','admin','Success','127.0.0.1','2015-06-27 04:40:07','RemeberMe','12121212','100000','Web','24d3c7e2-4298-4801-b942-420af6a9800f','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:40:07','0000-00-00 00:00:00'),('db4099c2-e5c6-4ee1-93f4-b8a4d8ff1234','admin','admin','Success','127.0.0.1','2015-06-27 04:39:57','WebLogin','12121212','100000','Web','d221a490-5577-4056-96c0-34ce11580b19','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:39:57','0000-00-00 00:00:00'),('dcd39bdb-0d54-47ed-bb4e-81ce25749cac','admin','admin','Success','127.0.0.1','2015-06-26 14:12:29','WebLogin','12121212','100000','Web','3b14b600-41bf-4253-a0e9-2697e4505209','Chrome/44','Windows NT 6.1','Browser','2015-06-26 22:12:29','0000-00-00 00:00:00'),('ddeaaa65-ff0b-4173-bdbf-fd610d096e63','admin','admin','Success','127.0.0.1','2015-06-27 02:59:08','WebLogin','12121212','100000','Web','2b40a88a-1444-4801-9ff2-eb5114ad5969','Chrome/44','Windows NT 6.1','Browser','2015-06-27 10:59:08','0000-00-00 00:00:00'),('e4a002b5-75c0-4bda-af7a-e6caf2151624','admin','admin','Success','127.0.0.1','2015-06-26 09:04:53','SocialSignOn','12121212','100000','Web','23abb990-1df7-40af-a378-77f53b25f554','Chrome/44','Windows NT 6.1','Browser','2015-06-26 17:04:53','0000-00-00 00:00:00'),('e8ea0247-17fa-4ade-9af7-62e0b0d6c556','admin','admin','Success','127.0.0.1','2015-06-26 08:48:20','SocialSignOn','12121212','100000','Web','7a613066-4604-4fa8-a088-cbd5cb82b956','Chrome/44','Windows NT 6.1','Browser','2015-06-26 16:48:20','0000-00-00 00:00:00'),('e97e19ff-4601-4877-8bba-fad394509283','admin','admin','Success','127.0.0.1','2015-06-27 04:19:15','WebLogin','12121212','100000','Web','5ea602cc-9a95-4baa-a81f-7c3de02c3b23','Chrome/44','Windows NT 6.1','Browser','2015-06-27 12:19:15','0000-00-00 00:00:00'),('f559c88d-21cc-4985-8e6b-0d460957a75d','admin','admin','Success','127.0.0.1','2015-06-25 05:01:14','WebLogin','12121212','100000','Web','c8223ddc-941f-4928-810b-476663e2a977','Chrome/44','Windows NT 6.1','Browser','2015-06-25 13:01:14','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `login_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialsignon_users_token`
--

DROP TABLE IF EXISTS `socialsignon_users_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialsignon_users_token` (
  `ID` varchar(45) NOT NULL,
  `UID` varchar(45) NOT NULL COMMENT '用户id',
  `PROVIDER` varchar(45) NOT NULL COMMENT '第三方提供商',
  `SOCIALUSERINFO` text NOT NULL COMMENT '''第三方应用的用户信息''',
  `SOCIALUID` varchar(100) NOT NULL COMMENT '''第三方应用的用户id''',
  `EXATTRIBUTE` text,
  `ACCESSTOKEN` text,
  `CREATEDDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATEDDATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `USERNAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和第三方认证提供商的绑定关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialsignon_users_token`
--

LOCK TABLES `socialsignon_users_token` WRITE;
/*!40000 ALTER TABLE `socialsignon_users_token` DISABLE KEYS */;
INSERT INTO `socialsignon_users_token` VALUES ('7be961be-b8bc-4988-9c3b-11fff3e571a2','12121212','qq','{\"client_id\":\"101225363\",\"openid\":\"F3204D4BFCC776F54D6B6B293E16E05A\"}','F3204D4BFCC776F54D6B6B293E16E05A','{\"expires_in\":\"7776000\",\"refresh_token\":\"77F74D9D2ED371E931EBA2D374C20BDE\",\"access_token\":\"32E1641ED138C883BF5FE9B26BA509AB\"}','{\"rawResponse\":\"access_token=32E1641ED138C883BF5FE9B26BA509AB&expires_in=7776000&refresh_token=77F74D9D2ED371E931EBA2D374C20BDE\",\"responseObject\":{\"expires_in\":\"7776000\",\"refresh_token\":\"77F74D9D2ED371E931EBA2D374C20BDE\",\"access_token\":\"32E1641ED138C883BF5FE9B26BA509AB\"},\"access_token\":\"32E1641ED138C883BF5FE9B26BA509AB\",\"token\":\"32E1641ED138C883BF5FE9B26BA509AB\",\"secret\":\"\",\"refresh_token\":null,\"expires_in\":null,\"signature\":null,\"token_type\":null,\"id_token\":null,\"sub_jwk\":null,\"error\":null,\"error_description\":null,\"empty\":false}','2015-06-26 09:04:53','2015-06-26 09:04:53','admin'),('9e6eaef4-1bc0-4075-b587-95cf5a55c47e','12121212','sinaweibo','{\"id\":2490166710,\"idstr\":\"2490166710\",\"class\":1,\"screen_name\":\"shimingxy\",\"name\":\"shimingxy\",\"province\":\"31\",\"city\":\"1\",\"location\":\"上海 黄浦区\",\"description\":\"专注于云安全的乡下人\",\"url\":\"\",\"profile_image_url\":\"http://tp3.sinaimg.cn/2490166710/50/22818938177/1\",\"profile_url\":\"shimingxy\",\"domain\":\"shimingxy\",\"weihao\":\"\",\"gender\":\"m\",\"followers_count\":8,\"friends_count\":13,\"pagefriends_count\":0,\"statuses_count\":35,\"favourites_count\":0,\"created_at\":\"Mon Oct 24 11:58:04 +0800 2011\",\"following\":false,\"allow_all_act_msg\":false,\"geo_enabled\":true,\"verified\":false,\"verified_type\":-1,\"remark\":\"\",\"status\":{\"created_at\":\"Fri Apr 24 08:27:54 +0800 2015\",\"id\":3835069460706040,\"mid\":\"3835069460706040\",\"idstr\":\"3835069460706040\",\"text\":\"【史上最牛逼的创业团队之一，你想象不到！】史上最牛逼的创业团队之一1921年注册公司，靠共产主义的故事拿到了苏联的天使轮和A轮，历经艰辛打败了西方跨国 http://t.cn/RAWeBDs（分享自 @微口）\",\"source_allowclick\":0,\"source_type\":1,\"source\":\"<a href=\\\"http://app.weibo.com/t/feed/bnsjO\\\" rel=\\\"nofollow\\\">未通过审核应用</a>\",\"favorited\":false,\"truncated\":false,\"in_reply_to_status_id\":\"\",\"in_reply_to_user_id\":\"\",\"in_reply_to_screen_name\":\"\",\"pic_urls\":[{\"thumbnail_pic\":\"http://ww2.sinaimg.cn/thumbnail/946cedb6jw1ergcr6ieqij20nc0fz76q.jpg\"}],\"thumbnail_pic\":\"http://ww2.sinaimg.cn/thumbnail/946cedb6jw1ergcr6ieqij20nc0fz76q.jpg\",\"bmiddle_pic\":\"http://ww2.sinaimg.cn/bmiddle/946cedb6jw1ergcr6ieqij20nc0fz76q.jpg\",\"original_pic\":\"http://ww2.sinaimg.cn/large/946cedb6jw1ergcr6ieqij20nc0fz76q.jpg\",\"geo\":null,\"reposts_count\":0,\"comments_count\":0,\"attitudes_count\":0,\"mlevel\":0,\"visible\":{\"type\":0,\"list_id\":0},\"darwin_tags\":[]},\"ptype\":0,\"allow_all_comment\":true,\"avatar_large\":\"http://tp3.sinaimg.cn/2490166710/180/22818938177/1\",\"avatar_hd\":\"http://tp3.sinaimg.cn/2490166710/180/22818938177/1\",\"verified_reason\":\"\",\"verified_trade\":\"\",\"verified_reason_url\":\"\",\"verified_source\":\"\",\"verified_source_url\":\"\",\"follow_me\":false,\"online_status\":0,\"bi_followers_count\":3,\"lang\":\"zh-cn\",\"star\":0,\"mbtype\":0,\"mbrank\":0,\"block_word\":0,\"block_app\":0,\"credit_score\":80,\"urank\":7}','2490166710','null','{\"rawResponse\":\"{\\\"access_token\\\":\\\"2.00MHUWiCz_dXWB773a2bbbe8rc9biD\\\",\\\"remind_in\\\":\\\"157679999\\\",\\\"expires_in\\\":157679999,\\\"uid\\\":\\\"2490166710\\\",\\\"scope\\\":\\\"follow_app_official_microblog\\\"}\",\"responseObject\":null,\"access_token\":\"2.00MHUWiCz_dXWB773a2bbbe8rc9biD\",\"token\":\"2.00MHUWiCz_dXWB773a2bbbe8rc9biD\",\"secret\":null,\"refresh_token\":null,\"expires_in\":\"157679999\",\"signature\":null,\"token_type\":null,\"id_token\":null,\"sub_jwk\":null,\"error\":null,\"error_description\":null,\"empty\":false}','2015-06-27 04:39:34','2015-06-27 04:39:34','admin');
/*!40000 ALTER TABLE `socialsignon_users_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `ID` varchar(45) NOT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(200) NOT NULL,
  `DISPLAYNAME` varchar(45) DEFAULT NULL,
  `BADPASSWORDCOUNT` smallint(5) unsigned DEFAULT '0',
  `BADPASSWORDTIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `PASSWORDLASTSETTIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '޸ʱ',
  `UNLOCKTIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ISLOCKED` tinyint(3) unsigned DEFAULT NULL,
  `LASTLOGINTIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '޸ʱ',
  `LASTLOGOFFTIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '޸ʱ',
  `LOGINCOUNT` int(10) unsigned DEFAULT '0',
  `LASTLOGINIP` varchar(45) DEFAULT NULL,
  `STATUS` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='û';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES ('12121212','admin','21232f297a57a5a743894a0e4a801fc3','admin',0,'2014-12-31 16:00:00','2014-12-31 16:00:00','2014-12-31 16:00:00',0,'2015-06-27 04:40:07','2014-12-31 16:00:00',35,'127.0.0.1',1);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-27 14:29:53
