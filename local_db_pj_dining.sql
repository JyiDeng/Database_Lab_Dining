-- MySQL dump 10.13  Distrib 8.4.0, for Win64 (x86_64)
--
-- Host: localhost    Database: db_pj_dining
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `AdminID` int NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'root','root');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adminactionlog`
--

DROP TABLE IF EXISTS `adminactionlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adminactionlog` (
  `LogID` int NOT NULL AUTO_INCREMENT,
  `AdminID` int DEFAULT NULL,
  `ActionType` enum('ADD_USER','DELETE_USER','UPDATE_USER','ADD_MERCHANT','DELETE_MERCHANT','UPDATE_MERCHANT') DEFAULT NULL,
  `TargetID` int DEFAULT NULL,
  `Timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`LogID`),
  KEY `AdminID` (`AdminID`),
  CONSTRAINT `adminactionlog_ibfk_1` FOREIGN KEY (`AdminID`) REFERENCES `admin` (`AdminID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminactionlog`
--

LOCK TABLES `adminactionlog` WRITE;
/*!40000 ALTER TABLE `adminactionlog` DISABLE KEYS */;
INSERT INTO `adminactionlog` VALUES (1,1,'ADD_USER',1,'2004-05-05 21:34:45'),(2,1,'UPDATE_MERCHANT',2,'2004-05-23 18:34:59'),(3,1,'DELETE_USER',3,'2004-05-26 15:39:51');
/*!40000 ALTER TABLE `adminactionlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish`
--

DROP TABLE IF EXISTS `dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish` (
  `DishID` int NOT NULL AUTO_INCREMENT,
  `DishName` varchar(50) NOT NULL,
  `Category` varchar(50) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Picture` varchar(50) DEFAULT NULL,
  `Flavor` varchar(50) DEFAULT NULL,
  `Ingredients` varchar(100) DEFAULT NULL,
  `Allergens` varchar(50) DEFAULT NULL,
  `NutritionInfo` varchar(50) DEFAULT NULL,
  `MerchantID` int DEFAULT NULL,
  PRIMARY KEY (`DishID`),
  KEY `MerchantID` (`MerchantID`),
  CONSTRAINT `dish_ibfk_1` FOREIGN KEY (`MerchantID`) REFERENCES `merchant` (`MerchantID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish`
--

LOCK TABLES `dish` WRITE;
/*!40000 ALTER TABLE `dish` DISABLE KEYS */;
INSERT INTO `dish` VALUES (1,'Braised Chicken','Chinese food','sauce delicious','examplePic1.com','original','chicken, green pepper, potatoes, ginger','','delicious chicken, green pepper vitamin high',1),(2,'Hachii','sweet','classic, small expensive','examplePic2.com','chocolate','milk, cocoa, sugar, additives','milk','high calcium content',2),(3,'PESTO E PATATE','Western food','classic pesto pizza','examplePic3.com','pesto flavor','Mozzarella cheese, pesto sauce, baked sweet potato, rosemary, garlic, Dried tomatoes in oil','garlic','balanced nutrition',3),(4,'Veggie Delight','Vegetarian','A mix of fresh vegetables','examplePic4.com','savory','broccoli, carrots, bell peppers','','high in fiber',4),(5,'Pastry Heaven','Pastries','Assorted pastries','examplePic5.com','sweet','flour, sugar, butter','gluten','high in calories',5),(6,'Shrimp Feast','Seafood','Grilled shrimp','examplePic6.com','savory','shrimp, garlic, lemon','shellfish','high in protein',6),(7,'BBQ Ribs','BBQ','Slow-cooked ribs','examplePic7.com','smoky','pork, BBQ sauce','','high in protein',7),(8,'Spicy Tofu','Spicy Food','Spicy grilled tofu','examplePic8.com','spicy','tofu, chili sauce','','high in protein',8),(9,'Veggie Pasta','Vegetarian','Pasta with vegetables','examplePic9.com','savory','pasta, vegetables','gluten','high in fiber',4),(10,'Cupcake Delight','Pastries','Assorted cupcakes','examplePic10.com','sweet','flour, sugar, butter','gluten','high in calories',5),(11,'Salmon Fillet','Seafood','Grilled salmon','examplePic11.com','savory','salmon, lemon','fish','high in protein',6),(12,'Grilled Chicken','BBQ','Grilled chicken','examplePic12.com','smoky','chicken, BBQ sauce','','high in protein',7),(13,'Spicy Noodles','Spicy Food','Noodles with spicy sauce','examplePic13.com','spicy','noodles, chili sauce','gluten','high in protein',8),(14,'Veggie Burger','Vegetarian','Burger with vegetables','examplePic14.com','savory','bun, vegetables','gluten','high in fiber',4),(15,'Donut Delight','Pastries','Assorted donuts','examplePic15.com','sweet','flour, sugar, butter','gluten','high in calories',5),(16,'Lobster Tail','Seafood','Grilled lobster tail','examplePic16.com','savory','lobster, garlic butter','shellfish','high in protein',6),(17,'BBQ Brisket','BBQ','Slow-cooked brisket','examplePic17.com','smoky','beef, BBQ sauce','','high in protein',7),(18,'Spicy Chicken','Spicy Food','Spicy fried chicken','examplePic18.com','spicy','chicken, chili sauce','','high in protein',8),(19,'Veggie Soup','Vegetarian','Soup with fresh vegetables','examplePic19.com','savory','vegetables, broth','','high in fiber',4),(20,'Muffin Delight','Pastries','Assorted muffins','examplePic20.com','sweet','flour, sugar, butter','gluten','high in calories',5),(21,'Crab Cakes','Seafood','Fried crab cakes','examplePic21.com','savory','crab, bread crumbs','shellfish','high in protein',6),(22,'BBQ Sausage','BBQ','Grilled sausages','examplePic22.com','smoky','sausages, BBQ sauce','','high in protein',7),(23,'Spicy Beef','Spicy Food','Spicy beef stir-fry','examplePic23.com','spicy','beef, chili sauce','','high in protein',8),(24,'Veggie Wrap','Vegetarian','Wrap with fresh vegetables','examplePic24.com','savory','tortilla, vegetables','gluten','high in fiber',4),(25,'Pie Delight','Pastries','Assorted pies','examplePic25.com','sweet','flour, sugar, butter','gluten','high in calories',5),(26,'Clam Chowder','Seafood','Creamy clam chowder','examplePic26.com','savory','clams, cream','shellfish','high in protein',6),(27,'BBQ Wings','BBQ','Grilled chicken wings','examplePic27.com','smoky','chicken wings, BBQ sauce','','high in protein',7),(28,'Spicy Pork','Spicy Food','Spicy pork stir-fry','examplePic28.com','spicy','pork, chili sauce','','high in protein',8);
/*!40000 ALTER TABLE `dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favoritedish`
--

DROP TABLE IF EXISTS `favoritedish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favoritedish` (
  `favoriteDishID` int NOT NULL AUTO_INCREMENT,
  `userID` int DEFAULT NULL,
  `dishID` int DEFAULT NULL,
  `FavoriteDate` datetime NOT NULL,
  PRIMARY KEY (`favoriteDishID`),
  KEY `userID` (`userID`),
  KEY `dishID` (`dishID`),
  CONSTRAINT `favoritedish_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE,
  CONSTRAINT `favoritedish_ibfk_2` FOREIGN KEY (`dishID`) REFERENCES `dish` (`DishID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritedish`
--

LOCK TABLES `favoritedish` WRITE;
/*!40000 ALTER TABLE `favoritedish` DISABLE KEYS */;
INSERT INTO `favoritedish` VALUES (1,2,1,'2024-05-30 15:30:25'),(2,1,1,'2024-06-06 07:50:00'),(3,3,3,'2024-06-15 13:59:58'),(4,3,4,'2024-06-15 13:00:58'),(5,5,4,'2024-04-15 23:59:05'),(6,6,4,'2024-06-15 03:59:58'),(7,7,9,'2024-05-15 13:59:00'),(8,6,9,'2024-06-10 13:59:58'),(10,1,2,'2024-06-25 20:37:13'),(11,1,4,'2024-06-25 20:38:02');
/*!40000 ALTER TABLE `favoritedish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favoritemerchant`
--

DROP TABLE IF EXISTS `favoritemerchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favoritemerchant` (
  `favoriteMerchantID` int NOT NULL AUTO_INCREMENT,
  `userID` int DEFAULT NULL,
  `merchantID` int DEFAULT NULL,
  `FavoriteDate` datetime NOT NULL,
  PRIMARY KEY (`favoriteMerchantID`),
  KEY `userID` (`userID`),
  KEY `merchantID` (`merchantID`),
  CONSTRAINT `favoritemerchant_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE,
  CONSTRAINT `favoritemerchant_ibfk_2` FOREIGN KEY (`merchantID`) REFERENCES `merchant` (`MerchantID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritemerchant`
--

LOCK TABLES `favoritemerchant` WRITE;
/*!40000 ALTER TABLE `favoritemerchant` DISABLE KEYS */;
INSERT INTO `favoritemerchant` VALUES (1,2,2,'2024-04-30 05:30:25'),(2,1,1,'2024-05-06 07:30:00'),(3,3,3,'2024-05-15 23:59:58'),(4,2,4,'2024-05-15 23:59:58'),(5,5,4,'2024-05-16 03:59:58'),(7,3,5,'2024-05-05 23:59:58'),(8,1,3,'2024-06-25 20:37:26');
/*!40000 ALTER TABLE `favoritemerchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loyalcustomers`
--

DROP TABLE IF EXISTS `loyalcustomers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loyalcustomers` (
  `LoyalCustomerID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `MerchantID` int DEFAULT NULL,
  `PurchaseCount` int DEFAULT NULL,
  PRIMARY KEY (`LoyalCustomerID`),
  KEY `UserID` (`UserID`),
  KEY `MerchantID` (`MerchantID`),
  CONSTRAINT `loyalcustomers_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE,
  CONSTRAINT `loyalcustomers_ibfk_2` FOREIGN KEY (`MerchantID`) REFERENCES `merchant` (`MerchantID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loyalcustomers`
--

LOCK TABLES `loyalcustomers` WRITE;
/*!40000 ALTER TABLE `loyalcustomers` DISABLE KEYS */;
INSERT INTO `loyalcustomers` VALUES (1,1,3,5);
/*!40000 ALTER TABLE `loyalcustomers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `menuID` int NOT NULL AUTO_INCREMENT,
  `merchantID` int DEFAULT NULL,
  PRIMARY KEY (`menuID`),
  KEY `merchantID` (`merchantID`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`merchantID`) REFERENCES `merchant` (`MerchantID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,1),(5,1),(2,2),(3,3);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menuitem`
--

DROP TABLE IF EXISTS `menuitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menuitem` (
  `menuItemId` int NOT NULL AUTO_INCREMENT,
  `dishID` int DEFAULT NULL,
  PRIMARY KEY (`menuItemId`),
  KEY `dishID` (`dishID`),
  CONSTRAINT `menuitem_ibfk_1` FOREIGN KEY (`dishID`) REFERENCES `dish` (`DishID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menuitem`
--

LOCK TABLES `menuitem` WRITE;
/*!40000 ALTER TABLE `menuitem` DISABLE KEYS */;
INSERT INTO `menuitem` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20),(21,21),(22,22),(23,23),(24,24),(25,25),(26,26),(27,27),(28,28);
/*!40000 ALTER TABLE `menuitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menuprice`
--

DROP TABLE IF EXISTS `menuprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menuprice` (
  `menuPriceID` int NOT NULL AUTO_INCREMENT,
  `menuItemID` int DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `effectiveDate` datetime NOT NULL,
  PRIMARY KEY (`menuPriceID`),
  KEY `menuItemID` (`menuItemID`),
  CONSTRAINT `menuprice_ibfk_1` FOREIGN KEY (`menuItemID`) REFERENCES `menuitem` (`menuItemId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menuprice`
--

LOCK TABLES `menuprice` WRITE;
/*!40000 ALTER TABLE `menuprice` DISABLE KEYS */;
INSERT INTO `menuprice` VALUES (1,1,18.00,'2024-05-01 00:00:00'),(2,1,19.00,'2024-05-02 00:00:00'),(3,2,13.80,'2024-05-01 00:00:00'),(4,2,14.80,'2024-05-05 00:00:00'),(5,3,68.00,'2024-05-01 00:00:00'),(6,4,20.00,'2023-01-01 00:00:00'),(7,4,22.00,'2023-06-01 00:00:00'),(8,5,15.00,'2023-02-01 00:00:00'),(9,5,18.00,'2023-06-02 00:00:00'),(10,6,25.00,'2023-01-09 00:00:00'),(11,6,28.00,'2023-06-03 00:00:00'),(12,7,30.00,'2023-01-01 00:00:00'),(13,7,32.00,'2023-06-04 00:00:00'),(14,8,12.00,'2023-03-01 00:00:00'),(15,8,11.50,'2023-06-05 00:00:00'),(16,9,18.00,'2023-01-01 00:00:00'),(17,9,17.00,'2023-06-06 00:00:00'),(18,10,22.00,'2023-03-01 00:00:00'),(19,10,23.80,'2023-06-07 00:00:00'),(20,11,28.00,'2023-02-01 00:00:00'),(21,11,30.00,'2023-06-08 00:00:00'),(22,12,32.00,'2023-01-01 00:00:00'),(23,12,34.00,'2023-06-09 00:00:00'),(24,13,14.00,'2023-01-01 00:00:00'),(25,13,16.00,'2023-06-10 00:00:00'),(26,14,20.00,'2023-04-01 00:00:00'),(27,14,22.00,'2023-06-11 00:00:00'),(28,15,18.00,'2023-01-01 00:00:00'),(29,15,20.00,'2023-06-12 00:00:00'),(30,16,24.00,'2023-01-01 00:00:00'),(31,16,26.00,'2023-06-13 00:00:00'),(32,17,28.00,'2023-06-01 00:00:00'),(33,17,30.00,'2024-06-14 00:00:00'),(34,18,12.00,'2023-01-01 00:00:00'),(35,18,14.00,'2023-06-15 00:00:00'),(36,19,16.00,'2023-01-01 00:00:00'),(37,19,18.00,'2023-06-16 00:00:00'),(38,20,22.00,'2023-08-01 00:00:00'),(39,20,24.00,'2023-06-17 00:00:00'),(40,21,28.00,'2023-07-01 00:00:00'),(41,21,30.00,'2024-06-18 00:00:00'),(42,22,32.00,'2023-05-01 00:00:00'),(43,22,34.00,'2024-06-16 00:00:00'),(44,23,14.00,'2023-03-01 00:00:00'),(45,23,16.00,'2023-06-19 00:00:00'),(46,24,20.00,'2023-09-01 00:00:00'),(47,24,22.00,'2024-06-20 00:00:00'),(48,25,18.00,'2023-04-03 00:00:00'),(49,25,20.00,'2023-06-21 00:00:00'),(50,26,24.00,'2023-10-01 00:00:00'),(51,26,26.00,'2023-06-22 00:00:00'),(52,27,28.00,'2023-01-21 00:00:00'),(53,27,30.00,'2023-06-23 00:00:00'),(54,28,12.00,'2023-01-01 00:00:00'),(55,28,14.00,'2023-06-24 00:00:00'),(56,5,18.00,'2024-06-28 15:02:34'),(57,5,18.00,'2024-06-28 16:00:34');
/*!40000 ALTER TABLE `menuprice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchant` (
  `MerchantID` int NOT NULL AUTO_INCREMENT,
  `MerchantName` varchar(50) NOT NULL,
  `MainDishes` varchar(50) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MerchantID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
INSERT INTO `merchant` VALUES (1,'NanYuan Canteen','Braised Chicken','No. 200 GuoNian Road','12345'),(2,'FamilyMart','Hachii','West of No. 60, ZhengSu Road','54321'),(3,'Fantasy Canteen Topolino pizzeria','PESTO E PATATE','888-5 Changping Road','11111'),(4,'Green Garden','Veggie Delight','123 Green St','gg123'),(5,'Sunshine Bakery','Pastry Heaven','456 Sunshine Ave','sb123'),(6,'Ocean Delight','Shrimp Feast','789 Ocean Blvd','od123'),(7,'Mountain Grill','BBQ Ribs','321 Mountain Rd','mg123'),(8,'Spicy Corner','Spicy Tofu','654 Spicy Ln','sc123');
/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `MessageID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `AdminID` int DEFAULT NULL,
  `Content` text NOT NULL,
  `MessageDate` datetime NOT NULL,
  PRIMARY KEY (`MessageID`),
  KEY `UserID` (`UserID`),
  KEY `AdminID` (`AdminID`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`AdminID`) REFERENCES `admin` (`AdminID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,1,1,'Your meal is ready, please take it!','2024-05-15 22:39:10'),(2,2,1,'How was your dining experience, dear? Click to fill in the Dining Experience->','2024-05-19 21:32:12'),(3,3,1,'Thank you for your feedback, we are happy to serve you.','2024-05-21 19:35:19'),(7,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=29锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-25 19:28:13'),(9,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=8锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-25 19:29:07'),(11,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=30锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-25 19:38:01'),(13,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=31锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-25 19:40:33'),(15,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=32锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-25 19:42:43'),(17,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=33锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-25 19:47:03'),(18,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=33锛夌殑鐘舵€佸凡缁忔洿鏂颁负Ended锛?,'2024-06-25 19:54:40'),(19,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=32锛夌殑鐘舵€佸凡缁忔洿鏂颁负Ended锛?,'2024-06-25 19:55:01'),(21,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=34锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-25 19:55:51'),(23,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=35锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-25 20:02:13'),(24,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=35锛夌殑鐘舵€佸凡缁忔洿鏂颁负Ended锛?,'2024-06-25 20:05:06'),(25,1,NULL,'2','2024-06-25 20:07:08'),(28,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=36锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-25 20:38:48'),(29,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=37锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-26 19:23:02'),(30,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=37锛夌殑鐘舵€佸凡缁忔洿鏂颁负Ended锛?,'2024-06-26 19:23:13'),(32,1,NULL,'鎮ㄥ凡棰勮鎴愬姛椁愬巺锛屽晢鎴稩D=3锛?,'2024-06-26 19:24:50'),(33,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=36锛夌殑鐘舵€佸凡缁忔洿鏂颁负Ended锛?,'2024-06-28 11:19:55'),(35,3,NULL,'鎮ㄧ殑璁㈠崟锛圛D=38锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-28 15:09:44'),(36,3,NULL,'鎮ㄧ殑璁㈠崟锛圛D=38锛夌殑鐘舵€佸凡缁忔洿鏂颁负Ended锛?,'2024-06-28 15:09:47'),(38,3,NULL,'鎮ㄧ殑璁㈠崟锛圛D=39锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-28 15:10:10'),(40,3,NULL,'鎮ㄧ殑璁㈠崟锛圛D=40锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-28 15:10:36'),(42,3,NULL,'鎮ㄧ殑璁㈠崟锛圛D=41锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-28 15:10:53'),(44,3,NULL,'鎮ㄧ殑璁㈠崟锛圛D=42锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-28 15:11:22'),(46,3,NULL,'鎮ㄧ殑璁㈠崟锛圛D=43锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-28 15:11:44'),(48,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=44锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-28 15:16:42'),(49,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=44锛夌殑鐘舵€佸凡缁忔洿鏂颁负Ended锛?,'2024-06-28 15:16:44'),(51,1,NULL,'鎮ㄥ凡棰勮鎴愬姛椁愬巺锛屽晢鎴稩D=2锛?,'2024-06-28 16:01:09'),(53,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=45锛夌殑鐘舵€佸凡缁忔洿鏂颁负Completed锛?,'2024-06-28 16:25:38'),(54,1,NULL,'鎮ㄧ殑璁㈠崟锛圛D=45锛夌殑鐘舵€佸凡缁忔洿鏂颁负Ended锛?,'2024-06-28 16:25:58');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myorder`
--

DROP TABLE IF EXISTS `myorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myorder` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `MerchantID` int DEFAULT NULL,
  `OrderDate` datetime NOT NULL,
  `Status` enum('Pending','Completed','Ended') NOT NULL,
  `OrderType` enum('Queue','Online') DEFAULT NULL,
  `TotalPrice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `UserID` (`UserID`),
  KEY `MerchantID` (`MerchantID`),
  CONSTRAINT `myorder_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE,
  CONSTRAINT `myorder_ibfk_2` FOREIGN KEY (`MerchantID`) REFERENCES `merchant` (`MerchantID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myorder`
--

LOCK TABLES `myorder` WRITE;
/*!40000 ALTER TABLE `myorder` DISABLE KEYS */;
INSERT INTO `myorder` VALUES (1,1,1,'2024-05-01 06:32:29','Completed','Queue',0.00),(2,3,2,'2024-05-08 18:10:10','Ended','Queue',0.00),(3,2,3,'2024-05-17 13:29:20','Pending','Online',0.00),(4,1,1,'2024-06-10 12:00:00','Completed','Queue',0.00),(5,1,1,'2024-06-12 14:00:00','Completed','Queue',0.00),(6,1,1,'2024-06-14 16:00:00','Completed','Queue',0.00),(7,1,1,'2024-06-16 18:00:00','Completed','Queue',0.00),(8,1,1,'2024-06-18 20:00:00','Completed','Queue',0.00),(9,2,2,'2024-06-10 12:00:00','Completed','Queue',0.00),(10,2,2,'2024-06-12 14:00:00','Completed','Queue',0.00),(11,2,2,'2024-06-14 16:00:00','Completed','Online',0.00),(12,2,2,'2024-06-16 18:00:00','Completed','Online',0.00),(13,2,2,'2024-06-18 20:00:00','Completed','Online',0.00),(14,3,3,'2024-06-10 12:00:00','Completed','Queue',0.00),(15,3,3,'2024-06-12 14:00:00','Completed','Queue',0.00),(16,3,3,'2024-06-14 16:00:00','Completed','Queue',0.00),(17,3,3,'2024-06-16 18:00:00','Completed','Queue',0.00),(18,3,3,'2024-06-18 20:00:00','Completed','Queue',0.00),(24,5,5,'2024-06-10 12:00:00','Completed','Queue',0.00),(25,5,5,'2024-06-12 14:00:00','Completed','Online',0.00),(26,5,5,'2024-06-14 16:00:00','Completed','Online',0.00),(27,5,5,'2024-06-16 18:00:00','Completed','Queue',0.00),(28,5,5,'2024-06-18 20:00:00','Completed','Online',0.00),(29,1,3,'2024-06-25 18:19:13','Completed','Online',0.00),(30,1,4,'2024-06-25 19:37:48','Completed','Queue',0.00),(31,1,3,'2024-06-25 19:40:22','Completed','Online',0.00),(32,1,8,'2024-06-25 19:42:33','Ended','Queue',0.00),(33,1,6,'2024-06-25 19:45:46','Ended','Queue',0.00),(34,1,5,'2024-06-25 19:55:34','Completed','Online',0.00),(35,1,5,'2024-06-25 20:02:03','Ended','Queue',0.00),(36,1,3,'2024-06-25 20:20:07','Ended','Queue',0.00),(37,1,3,'2024-06-26 19:22:31','Ended','Queue',0.00),(38,3,1,'2024-06-28 15:09:36','Ended','Queue',0.00),(39,3,5,'2024-06-28 15:09:59','Completed','Queue',0.00),(40,3,6,'2024-06-28 15:10:27','Completed','Online',0.00),(41,3,6,'2024-06-28 15:10:43','Completed','Queue',0.00),(42,3,8,'2024-06-28 15:11:13','Completed','Online',0.00),(43,3,5,'2024-06-28 15:11:34','Completed','Online',0.00),(44,1,4,'2024-06-28 15:16:36','Ended','Queue',0.00),(45,1,3,'2024-06-28 16:25:21','Ended','Online',0.00);
/*!40000 ALTER TABLE `myorder` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `InsertLoyalCustomer` AFTER INSERT ON `myorder` FOR EACH ROW BEGIN
    DECLARE purchase_count INT;

    -- 璁＄畻鐢ㄦ埛鍦ㄥ晢鎴风殑鎬绘秷璐规鏁?
    SELECT COUNT(*) INTO purchase_count
    FROM MyOrder
    WHERE UserID = NEW.UserID AND MerchantID = NEW.MerchantID;

    -- 濡傛灉娑堣垂娆℃暟瓒呰繃闃堝€间笖LoyalCustomers琛ㄤ腑娌℃湁璇ヨ褰曪紝鍒欐彃鍏?
    IF purchase_count >= 5 THEN
        IF NOT EXISTS (
            SELECT 1 FROM LoyalCustomers
            WHERE UserID = NEW.UserID AND MerchantID = NEW.MerchantID
        ) THEN
            INSERT INTO LoyalCustomers (UserID, MerchantID, PurchaseCount)
            VALUES (NEW.UserID, NEW.MerchantID, purchase_count);
        END IF;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `UpdateLoyalCustomer` AFTER INSERT ON `myorder` FOR EACH ROW BEGIN
    DECLARE purchase_count INT;

    -- 璁＄畻鐢ㄦ埛鍦ㄥ晢鎴风殑鎬绘秷璐规鏁?
    SELECT COUNT(*) INTO purchase_count
    FROM MyOrder
    WHERE UserID = NEW.UserID AND MerchantID = NEW.MerchantID;

    -- 濡傛灉娑堣垂娆℃暟瓒呰繃闃堝€间笖LoyalCustomers琛ㄤ腑鏈夎璁板綍锛屽垯鏇存柊
    IF purchase_count >= 5 THEN
        UPDATE LoyalCustomers
        SET PurchaseCount = purchase_count
        WHERE UserID = NEW.UserID AND MerchantID = NEW.MerchantID;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitem` (
  `OrderItemID` int NOT NULL AUTO_INCREMENT,
  `OrderID` int DEFAULT NULL,
  `DishID` int DEFAULT NULL,
  `Quantity` int NOT NULL,
  PRIMARY KEY (`OrderItemID`),
  KEY `OrderID` (`OrderID`),
  KEY `DishID` (`DishID`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `myorder` (`OrderID`) ON DELETE CASCADE,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`DishID`) REFERENCES `dish` (`DishID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES (1,1,2,1),(2,2,2,1),(3,3,3,23),(4,30,4,1),(5,30,9,1),(6,30,19,1),(7,30,24,1),(8,32,8,1),(9,32,13,1),(10,32,18,1),(11,32,23,1),(12,32,28,1),(13,33,6,1),(14,33,11,1),(15,33,16,1),(16,33,21,1),(17,33,26,1),(18,34,5,1),(19,34,10,1),(20,34,20,1),(21,34,25,1),(22,35,5,1),(23,35,15,1),(24,35,20,1),(25,35,25,1),(26,36,3,5),(27,37,3,1),(28,38,1,4),(29,39,5,1),(30,39,10,2),(31,39,15,1),(32,39,20,8),(33,39,25,2),(34,40,26,6),(35,40,16,1),(36,40,11,1),(37,40,6,3),(38,40,21,1),(39,41,6,2),(40,41,11,1),(41,41,16,2),(42,41,21,2),(43,41,26,8),(44,42,8,4),(45,42,13,3),(46,42,18,1),(47,43,5,2),(48,43,10,3),(49,43,15,3),(50,43,20,1),(51,43,25,2),(52,44,9,1),(53,44,14,1),(54,45,3,3);
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserve` (
  `ReserveId` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `MerchantID` int DEFAULT NULL,
  `ReserveDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReserveId`),
  KEY `UserID` (`UserID`),
  KEY `MerchantID` (`MerchantID`),
  CONSTRAINT `reserve_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE,
  CONSTRAINT `reserve_ibfk_2` FOREIGN KEY (`MerchantID`) REFERENCES `merchant` (`MerchantID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
INSERT INTO `reserve` VALUES (1,1,2,'2024-06-25 17:58:23'),(2,1,2,'2024-06-25 18:01:05'),(3,1,2,'2024-06-25 20:07:07'),(4,1,2,'2024-06-25 20:08:36'),(5,1,2,'2024-06-25 20:09:10'),(6,1,2,'2024-06-26 19:24:46'),(7,1,3,'2024-06-26 19:24:49'),(8,1,2,'2024-06-28 15:57:25'),(9,1,2,'2024-06-28 16:01:09');
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `ReviewID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `MerchantID` int DEFAULT NULL,
  `DishID` int DEFAULT NULL,
  `Rating` decimal(2,1) DEFAULT NULL,
  `Content` text,
  `ReviewDate` datetime NOT NULL,
  PRIMARY KEY (`ReviewID`),
  KEY `UserID` (`UserID`),
  KEY `MerchantID` (`MerchantID`),
  KEY `DishID` (`DishID`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`MerchantID`) REFERENCES `merchant` (`MerchantID`) ON DELETE CASCADE,
  CONSTRAINT `review_ibfk_3` FOREIGN KEY (`DishID`) REFERENCES `dish` (`DishID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,3,2,2,4.5,'Good taste, will come again next time','2024-05-03 00:25:34'),(2,1,1,1,4.0,'Nice. A little salty','2024-05-16 08:39:10'),(3,2,3,3,5.0,'Very good pizza, love from Fantasy','2024-05-24 00:39:19'),(4,1,2,3,3.5,'Average taste, nothing special','2024-06-01 12:34:56'),(5,2,3,2,4.2,'Quite good, will recommend','2024-06-02 14:22:10'),(6,3,1,1,4.8,'Delicious! Best in town!','2024-06-03 16:10:45'),(7,1,3,3,3.9,'Good but a bit pricey','2024-06-04 18:55:20'),(8,2,1,2,4.0,'Tasty and fresh','2024-06-05 20:12:30'),(9,3,2,1,4.6,'Excellent, highly recommended','2024-06-06 22:05:15'),(10,1,2,3,3.7,'Not bad, but could be better','2024-06-07 09:45:50'),(11,2,3,2,4.3,'Very tasty, good value for money','2024-06-08 11:30:25'),(12,3,1,1,4.9,'Superb! Will definitely come back','2024-06-09 13:20:40'),(13,1,3,3,3.6,'Decent, but expected more','2024-06-10 15:05:55'),(14,2,1,2,4.1,'Good food, nice ambiance','2024-06-11 17:50:30'),(15,3,2,1,4.7,'Loved it, will visit again','2024-06-12 19:35:45'),(16,1,2,3,3.8,'Satisfactory, but room for improvement','2024-06-13 21:25:20'),(17,2,3,3,4.4,'Great taste, nice presentation','2024-06-14 23:10:35'),(18,3,1,2,5.0,'Absolutely perfect!','2024-06-15 10:55:50'),(19,1,3,1,3.5,'Just okay, nothing memorable','2024-06-16 12:40:15'),(20,2,1,3,4.2,'Pretty good, enjoyed the meal','2024-06-17 14:25:30'),(21,3,2,2,4.8,'Fantastic! Will tell friends','2024-06-18 16:10:45'),(22,1,2,1,3.9,'Decent food, slow service','2024-06-19 18:55:20'),(23,2,3,2,4.0,'Good quality, fair price','2024-06-20 20:12:30'),(24,1,NULL,3,1.0,'1','2024-06-26 19:23:42'),(25,1,NULL,3,1.0,'nice','2024-06-28 11:20:38'),(26,1,NULL,3,1.0,'','2024-06-28 11:54:06'),(27,1,NULL,3,5.0,'nice','2024-06-28 12:01:59'),(28,1,NULL,9,5.0,'goooood','2024-06-28 15:16:51'),(29,1,NULL,9,4.0,'a little too sweeet','2024-06-28 15:17:05'),(30,1,NULL,9,5.0,'too full','2024-06-28 15:17:17'),(31,1,NULL,9,5.0,'great','2024-06-28 15:18:05'),(32,1,NULL,9,5.0,'great','2024-06-28 16:23:55');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `Gender` enum('Male','Female','Other') NOT NULL,
  `EcardID` int NOT NULL,
  `Role` enum('Student','Staff') NOT NULL,
  `Age` int DEFAULT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'JohnDoe','Male',12345,'Staff',30,'12345'),(2,'Tom1','Male',50,'Student',25,'54321'),(3,'Octpus1','Other',25,'Staff',1,'11111'),(5,'Carol1','Female',45,'Staff',30,'password2'),(6,'Dave1','Male',35,'Student',19,'password3'),(7,'Eve','Female',55,'Staff',28,'password4'),(8,'Frank1','Male',85,'Student',24,'password5'),(9,'Grace1','Female',95,'Staff',35,'password6'),(10,'Bob2','Male',247,'Student',20,'password1'),(11,'Heidi','Female',105,'Student',21,'password7');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-07 10:33:28
