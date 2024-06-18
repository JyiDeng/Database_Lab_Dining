
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE if not exists User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(50) NOT NULL,
    Gender ENUM('Male','Female','Other') NOT NULL,
    EcardID INT NOT NULL,
    Role ENUM('Student','Staff') NOT NULL,
    Age INT,
    Password VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES (1,'Alice1','Female',75,'Student',20,'12345');
INSERT INTO `user` VALUES (2,'Tom1','Male',50,'Student',25,'54321');
INSERT INTO `user` VALUES (3,'Octpus1','Other',25,'Staff',1,'11111');

DROP TABLE IF EXISTS `Merchant`;
CREATE TABLE if not exists Merchant(
    MerchantID INT AUTO_INCREMENT PRIMARY KEY,
    MerchantName VARCHAR(50) NOT NULL,
    MainDishes VARCHAR(50) NOT NULL,
    Address VARCHAR(50) NOT NULL,
    MenuID INT,
    Password VARCHAR(50),
    FOREIGN KEY (menuID) REFERENCES menu(menuID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `Merchant` VALUES (1,'NanYuan Canteen','Braised Chicken','No. 200 GuoNian Road', 1, '12345');
insert into `Merchant` VALUES (2,'FamilyMart','Hachii','West of No. 60, ZhengSu Road', 2,'54321');
insert into `Merchant` VALUES (3,'Fantasy Canteen Topolino pizzeria','PESTO E PATATE','888-5 Changping Road', 3,'11111');

DROP TABLE IF EXISTS `Dish`;
CREATE TABLE if not exists Dish (
    DishID INT AUTO_INCREMENT PRIMARY KEY,
    DishName VARCHAR(50) NOT NULL,
#     Price FLOAT NOT NULL,  # price 或许应该在菜单里
    Category VARCHAR(50) NOT NULL,
    Description VARCHAR(200),
    Picture VARCHAR(50),
    Flavor VARCHAR(50),
    Ingredients VARCHAR(100),
    Allergens VARCHAR(50),
    NutritionInfo VARCHAR(50),
    MerchantID INT,
    FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
Insert into `Dish` VALUES (1,'Braised Chicken','Chinese food','sauce delicious','examplePic1.com','original','chicken, green pepper, potatoes, ginger','','delicious chicken, green pepper vitamin high',  1);
Insert into `Dish` VALUES (2,'Hachii','sweet','classic, small expensive','examplePic2.com','chocolate','milk, cocoa, sugar, additives','milk','high calcium content', 2);
Insert into `Dish` VALUES (3,'PESTO E PATATE','Western food','classic pesto pizza','examplePic3.com','pesto flavor','Mozzarella cheese, pesto sauce, baked sweet potato, rosemary, garlic, Dried tomatoes in oil','garlic','balanced nutrition',3);

DROP TABLE IF EXISTS `menu`;
CREATE TABLE menu (
    menuID INT AUTO_INCREMENT PRIMARY KEY,
    merchantID INT,
    FOREIGN KEY (merchantID) REFERENCES merchant(merchantID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
Insert into `menu` VALUES (1,1);
Insert into `menu` VALUES (2,2);
Insert into `menu` VALUES (3,3);

DROP TABLE IF EXISTS `menuItem`;
CREATE TABLE menuItem (
    menuItemID INT AUTO_INCREMENT PRIMARY KEY,
    menuID INT,
    dishID INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (dishID) REFERENCES dish(dishID),
    FOREIGN KEY (menuID) REFERENCES menu(menuID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
Insert into `menuItem` VALUES (1,1,3,18);
Insert into `menuItem` VALUES (2,2,2,13.8);
Insert into `menuItem` VALUES (3,3,1,68);

DROP TABLE IF EXISTS `menuPrice`;
CREATE TABLE menuPrice (
    menuPriceID INT AUTO_INCREMENT PRIMARY KEY,
    menuItemID INT,
    price DECIMAL(10, 2) NOT NULL,
    effectiveDate DATETIME NOT NULL,
    endDate DATETIME DEFAULT NULL,
    FOREIGN KEY (menuItemID) REFERENCES menuItem(menuItemID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `menuPrice` (menuItemID, price, effectiveDate) VALUES
(1, 18, '2024-05-01 00:00:00'),
(2, 13.8, '2024-05-01 00:00:00'),
(3, 68, '2024-05-01 00:00:00');

# 拆分为收藏商家和收藏菜品
# DROP TABLE IF EXISTS `Favorite`;
# CREATE TABLE Favorite (
#     UserID INT,
#     MerchantID INT,
#     DishID INT,
#     FavoriteDate DATETIME NOT NULL,
#     PRIMARY KEY (UserID, MerchantID, DishID),
#     FOREIGN KEY (UserID) REFERENCES User(UserID),
#     FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID),
#     FOREIGN KEY (DishID) REFERENCES Dish(DishID)
# )ENGINE=InnoDB DEFAULT CHARSET=utf8;
# INSERT INTO `Favorite` VALUES (1,2,2,'2024-4-30 5:30:25');
# INSERT INTO `Favorite` VALUES (2,1,1,'2024-5-6 7:30:00');
# INSERT INTO `Favorite` VALUES (3,3,3,'2024-5-15 23:59:58');

DROP TABLE IF EXISTS `FavoriteMerchant`;
CREATE TABLE FavoriteMerchant (
    favoriteMerchantID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    merchantID INT,
    FavoriteDate DATETIME NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID),
    FOREIGN KEY (merchantID) REFERENCES merchant(merchantID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `FavoriteMerchant` VALUES (1,2,2,'2024-4-30 5:30:25');
INSERT INTO `FavoriteMerchant` VALUES (2,1,1,'2024-5-6 7:30:00');
INSERT INTO `FavoriteMerchant` VALUES (3,3,3,'2024-5-15 23:59:58');

DROP TABLE IF EXISTS `FavoriteDish`;
CREATE TABLE FavoriteDish (
    favoriteDishID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    dishID INT,
    FavoriteDate DATETIME NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID),
    FOREIGN KEY (dishID) REFERENCES Dish(dishID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `FavoriteDish` VALUES (1,2,2,'2024-5-30 15:30:25');
INSERT INTO `FavoriteDish` VALUES (2,1,1,'2024-6-6 7:50:00');
INSERT INTO `FavoriteDish` VALUES (3,3,3,'2024-6-15 13:59:58');


DROP TABLE IF EXISTS `Review`;
CREATE TABLE Review (
    ReviewID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    MerchantID INT,
    DishID INT,
    Rating DECIMAL(2, 1),
    Content TEXT,
    ReviewDate DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID),
    FOREIGN KEY (DishID) REFERENCES Dish(DishID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `Review` VALUES (1,3,2,2,4.5,'Good taste, will come again next time','2024-5-3 0:25:34');
INSERT INTO `Review` VALUES (2,1,1,1,4,'Nice. A little salty','2024-5-16 8:39:10');
INSERT INTO `Review` VALUES (3,2,3,3,5,'Very good pizza, love from Fantasy','2024-5-24 0:39:19');
INSERT INTO `Review`VALUES (4, 1, 2, 3,3.5, 'Average taste, nothing special', '2024-6-1 12:34:56');
INSERT INTO `Review`VALUES (5, 2, 3, 2,4.2, 'Quite good, will recommend', '2024-6-2 14:22:10');
INSERT INTO `Review`VALUES (6, 3, 1, 1,4.8, 'Delicious! Best in town!', '2024-6-3 16:10:45');
INSERT INTO `Review`VALUES (7, 1, 3, 3,3.9, 'Good but a bit pricey', '2024-6-4 18:55:20');
INSERT INTO `Review`VALUES (8, 2, 1, 2,4.0, 'Tasty and fresh', '2024-6-5 20:12:30');
INSERT INTO `Review`VALUES (9, 3, 2, 1,4.6, 'Excellent, highly recommended', '2024-6-6 22:05:15');
INSERT INTO `Review`VALUES (10, 1, 2, 3,3.7, 'Not bad, but could be better', '2024-6-7 9:45:50');
INSERT INTO `Review`VALUES (11, 2, 3, 2,4.3, 'Very tasty, good value for money', '2024-6-8 11:30:25');
INSERT INTO `Review`VALUES (12, 3, 1, 1,4.9, 'Superb! Will definitely come back', '2024-6-9 13:20:40');
INSERT INTO `Review`VALUES (13, 1, 3, 3,3.6, 'Decent, but expected more', '2024-6-10 15:05:55');
INSERT INTO `Review`VALUES (14, 2, 1, 2,4.1, 'Good food, nice ambiance', '2024-6-11 17:50:30');
INSERT INTO `Review`VALUES (15, 3, 2, 1,4.7, 'Loved it, will visit again', '2024-6-12 19:35:45');
INSERT INTO `Review`VALUES (16, 1, 2, 3,3.8, 'Satisfactory, but room for improvement', '2024-6-13 21:25:20');
INSERT INTO `Review`VALUES (17, 2, 3, 3,4.4, 'Great taste, nice presentation', '2024-6-14 23:10:35');
INSERT INTO `Review`VALUES (18, 3, 1, 2,5.0, 'Absolutely perfect!', '2024-6-15 10:55:50');
INSERT INTO `Review`VALUES (19, 1, 3, 1,3.5, 'Just okay, nothing memorable', '2024-6-16 12:40:15');
INSERT INTO `Review`VALUES (20, 2, 1, 3,4.2, 'Pretty good, enjoyed the meal', '2024-6-17 14:25:30');
INSERT INTO `Review`VALUES (21, 3, 2, 2,4.8, 'Fantastic! Will tell friends', '2024-6-18 16:10:45');
INSERT INTO `Review`VALUES (22, 1, 2, 1,3.9, 'Decent food, slow service', '2024-6-19 18:55:20');
INSERT INTO `Review`VALUES (23, 2, 3, 2,4.0, 'Good quality, fair price', '2024-6-20 20:12:30');

DROP TABLE IF EXISTS `MyOrder`; # 以免和Order保留字重复
CREATE TABLE MyOrder (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    merchantID INT,
    OrderDate DATETIME NOT NULL,
    Status ENUM('Pending','Completed','Cancelled') NOT NULL,
    OrderType ENUM('Queue','Online'),
    TotalPrice DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (merchantID) REFERENCES merchant(merchantID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `MyOrder` VALUES(1,1,1,'2024-5-1 6:32:29','Completed','Queue',30);
INSERT INTO `MyOrder` VALUES(2,3,2,'2024-5-8 18:10:10','Cancelled','Queue',99.9);
INSERT INTO `MyOrder` VALUES(3,2,3,'2024-5-17 13:29:20','Pending','Online',10.24);

DROP TABLE IF EXISTS `OrderItem`;
CREATE TABLE OrderItem (
    OrderItemID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT,
    DishID INT,
    Quantity INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
--     PRIMARY KEY (OrderID, DishID),
    FOREIGN KEY (OrderID) REFERENCES MyOrder(OrderID),
    FOREIGN KEY (DishID) REFERENCES Dish(DishID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `OrderItem` VALUES (1,1,2,3,4.9);
INSERT INTO `OrderItem` VALUES (2,2,2,5,8);
INSERT INTO `OrderItem` VALUES (3,3,3,1,25);

DROP TABLE IF EXISTS `Admin`;
CREATE TABLE Admin (
    AdminID INT PRIMARY KEY,
    Name VARCHAR(100),
    Password VARCHAR(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `Admin` VALUES (1,'root','root');

DROP TABLE IF EXISTS `message`;
CREATE TABLE Message (
    MessageID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    AdminID INT,
    Content TEXT NOT NULL,
    MessageDate DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (AdminID) REFERENCES Admin(AdminID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `message` VALUES(1,1,1,'Your meal is ready, please take it!','2024-5-15 22:39:10');
INSERT INTO `message` VALUES(2,2,1,'How was your dining experience, dear? Click to fill in the Dining Experience->','2024-5-19 21:32:12');
INSERT INTO `message` VALUES(3,3,1,'Thank you for your feedback, we are happy to serve you.','2024-5-21 19:35:19');

DROP TABLE IF EXISTS `AdminActionLog`;
CREATE TABLE AdminActionLog (
    LogID INT PRIMARY KEY AUTO_INCREMENT,
    AdminID INT,
    ActionType ENUM('ADD_USER','DELETE_USER','UPDATE_USER','ADD_MERCHANT','DELETE_MERCHANT','UPDATE_MERCHANT'),
    TargetID INT,
    Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (AdminID) REFERENCES Admin(AdminID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `AdminActionLog` VALUES(1,1,'ADD_USER',1,'2004-5-5 21:34:45');
INSERT INTO `AdminActionLog` VALUES(2,1,'UPDATE_MERCHANT',2,'2004-5-23 18:34:59');
INSERT INTO `AdminActionLog` VALUES(3,1,'DELETE_USER',3,'2004-5-26 15:39:51');

