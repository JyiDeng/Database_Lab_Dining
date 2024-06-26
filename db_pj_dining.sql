
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE if not exists User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(50) NOT NULL,
    Gender ENUM('Male','Female','Other') NOT NULL,
    EcardID INT NOT NULL,
    Role ENUM('Student','Staff') NOT NULL,
    Age INT,
    Password VARCHAR(50) NOT NULL DEFAULT 12345
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES (1,'Alice1','Female',75,'Student',20,'12345');
INSERT INTO `user` VALUES (2,'Tom1','Male',50,'Student',25,'54321');
INSERT INTO `user` VALUES (3,'Octpus1','Other',25,'Staff',1,'11111');
INSERT INTO `user` (UserName, Gender, EcardID, Role, Age, Password) VALUES
('Bob1', 'Male', 65, 'Student', 22, 'password1'),
('Carol1', 'Female', 45, 'Staff', 30, 'password2'),
('Dave1', 'Male', 35, 'Student', 19, 'password3'),
('Eve', 'Female', 55, 'Staff', 28, 'password4'),
('Frank1', 'Male', 85, 'Student', 24, 'password5'),
('Grace1', 'Female', 95, 'Staff', 35, 'password6'),
('Bob2', 'Male', 247, 'Student', 20, 'password1'),
('Heidi', 'Female', 105, 'Student', 21, 'password7');

DROP TABLE IF EXISTS `Merchant`;
CREATE TABLE if not exists Merchant(
    MerchantID INT AUTO_INCREMENT PRIMARY KEY,
    MerchantName VARCHAR(50) NOT NULL,
    MainDishes VARCHAR(50) NOT NULL,
    Address VARCHAR(50) NOT NULL,
#     MenuID INT,
    Password VARCHAR(50)
#     FOREIGN KEY (menuID) REFERENCES menu(menuID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `Merchant` VALUES (1,'NanYuan Canteen','Braised Chicken','No. 200 GuoNian Road', '12345');
insert into `Merchant` VALUES (2,'FamilyMart','Hachii','West of No. 60, ZhengSu Road','54321');
insert into `Merchant` VALUES (3,'Fantasy Canteen Topolino pizzeria','PESTO E PATATE','888-5 Changping Road', '11111');
INSERT INTO `Merchant` (MerchantName, MainDishes, Address, Password) VALUES
('Green Garden', 'Veggie Delight', '123 Green St', 'gg123'),
('Sunshine Bakery', 'Pastry Heaven', '456 Sunshine Ave', 'sb123'),
('Ocean Delight', 'Shrimp Feast', '789 Ocean Blvd', 'od123'),
('Mountain Grill', 'BBQ Ribs', '321 Mountain Rd', 'mg123'),
('Spicy Corner', 'Spicy Tofu', '654 Spicy Ln', 'sc123');

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
    FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
Insert into `Dish` VALUES (1,'Braised Chicken','Chinese food','sauce delicious','examplePic1.com','original','chicken, green pepper, potatoes, ginger','','delicious chicken, green pepper vitamin high',  1);
Insert into `Dish` VALUES (2,'Hachii','sweet','classic, small expensive','examplePic2.com','chocolate','milk, cocoa, sugar, additives','milk','high calcium content', 2);
Insert into `Dish` VALUES (3,'PESTO E PATATE','Western food','classic pesto pizza','examplePic3.com','pesto flavor','Mozzarella cheese, pesto sauce, baked sweet potato, rosemary, garlic, Dried tomatoes in oil','garlic','balanced nutrition',3);
INSERT INTO `Dish` (DishName, Category, Description, Picture, Flavor, Ingredients, Allergens, NutritionInfo, MerchantID) VALUES
('Veggie Delight', 'Vegetarian', 'A mix of fresh vegetables', 'examplePic4.com', 'savory', 'broccoli, carrots, bell peppers', '', 'high in fiber', 4),
('Pastry Heaven', 'Pastries', 'Assorted pastries', 'examplePic5.com', 'sweet', 'flour, sugar, butter', 'gluten', 'high in calories', 5),
('Shrimp Feast', 'Seafood', 'Grilled shrimp', 'examplePic6.com', 'savory', 'shrimp, garlic, lemon', 'shellfish', 'high in protein', 6),
('BBQ Ribs', 'BBQ', 'Slow-cooked ribs', 'examplePic7.com', 'smoky', 'pork, BBQ sauce', '', 'high in protein', 7),
('Spicy Tofu', 'Spicy Food', 'Spicy grilled tofu', 'examplePic8.com', 'spicy', 'tofu, chili sauce', '', 'high in protein', 8),

('Veggie Pasta', 'Vegetarian', 'Pasta with vegetables', 'examplePic9.com', 'savory', 'pasta, vegetables', 'gluten', 'high in fiber', 4),
('Cupcake Delight', 'Pastries', 'Assorted cupcakes', 'examplePic10.com', 'sweet', 'flour, sugar, butter', 'gluten', 'high in calories', 5),
('Salmon Fillet', 'Seafood', 'Grilled salmon', 'examplePic11.com', 'savory', 'salmon, lemon', 'fish', 'high in protein', 6),
('Grilled Chicken', 'BBQ', 'Grilled chicken', 'examplePic12.com', 'smoky', 'chicken, BBQ sauce', '', 'high in protein', 7),
('Spicy Noodles', 'Spicy Food', 'Noodles with spicy sauce', 'examplePic13.com', 'spicy', 'noodles, chili sauce', 'gluten', 'high in protein', 8),

('Veggie Burger', 'Vegetarian', 'Burger with vegetables', 'examplePic14.com', 'savory', 'bun, vegetables', 'gluten', 'high in fiber', 4),
('Donut Delight', 'Pastries', 'Assorted donuts', 'examplePic15.com', 'sweet', 'flour, sugar, butter', 'gluten', 'high in calories', 5),
('Lobster Tail', 'Seafood', 'Grilled lobster tail', 'examplePic16.com', 'savory', 'lobster, garlic butter', 'shellfish', 'high in protein', 6),
('BBQ Brisket', 'BBQ', 'Slow-cooked brisket', 'examplePic17.com', 'smoky', 'beef, BBQ sauce', '', 'high in protein', 7),
('Spicy Chicken', 'Spicy Food', 'Spicy fried chicken', 'examplePic18.com', 'spicy', 'chicken, chili sauce', '', 'high in protein', 8),

('Veggie Soup', 'Vegetarian', 'Soup with fresh vegetables', 'examplePic19.com', 'savory', 'vegetables, broth', '', 'high in fiber', 4),
('Muffin Delight', 'Pastries', 'Assorted muffins', 'examplePic20.com', 'sweet', 'flour, sugar, butter', 'gluten', 'high in calories', 5),
('Crab Cakes', 'Seafood', 'Fried crab cakes', 'examplePic21.com', 'savory', 'crab, bread crumbs', 'shellfish', 'high in protein', 6),
('BBQ Sausage', 'BBQ', 'Grilled sausages', 'examplePic22.com', 'smoky', 'sausages, BBQ sauce', '', 'high in protein', 7),
('Spicy Beef', 'Spicy Food', 'Spicy beef stir-fry', 'examplePic23.com', 'spicy', 'beef, chili sauce', '', 'high in protein', 8),

('Veggie Wrap', 'Vegetarian', 'Wrap with fresh vegetables', 'examplePic24.com', 'savory', 'tortilla, vegetables', 'gluten', 'high in fiber', 4),
('Pie Delight', 'Pastries', 'Assorted pies', 'examplePic25.com', 'sweet', 'flour, sugar, butter', 'gluten', 'high in calories', 5),
('Clam Chowder', 'Seafood', 'Creamy clam chowder', 'examplePic26.com', 'savory', 'clams, cream', 'shellfish', 'high in protein', 6),
('BBQ Wings', 'BBQ', 'Grilled chicken wings', 'examplePic27.com', 'smoky', 'chicken wings, BBQ sauce', '', 'high in protein', 7),
('Spicy Pork', 'Spicy Food', 'Spicy pork stir-fry', 'examplePic28.com', 'spicy', 'pork, chili sauce', '', 'high in protein', 8);

# DROP TABLE IF EXISTS `menu`;
# CREATE TABLE menu (
#     menuID INT AUTO_INCREMENT PRIMARY KEY,
#     merchantID INT,
#     FOREIGN KEY (merchantID) REFERENCES merchant(merchantID) ON DELETE CASCADE
# )ENGINE=InnoDB DEFAULT CHARSET=utf8;
# Insert into `menu` VALUES (1,1);
# Insert into `menu` VALUES (2,2);
# Insert into `menu` VALUES (3,3);

DROP TABLE IF EXISTS `menuItem`;
CREATE TABLE menuItem (
    menuItemId INT AUTO_INCREMENT PRIMARY KEY,
    dishID INT,
    FOREIGN KEY (dishID) REFERENCES dish(dishID) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
# Insert into `menuItem` VALUES (1,1,18);
# Insert into `menuItem` VALUES (2,2,13.8);
# Insert into `menuItem` VALUES (3,3,68);
Insert into `menuItem` VALUES (1,1);
Insert into `menuItem` VALUES (2,2);
Insert into `menuItem` VALUES (3,3);
INSERT INTO `menuItem` (dishID) VALUES
(4), (5), (6), (7), (8), (9), (10), (11), (12), (13), (14), (15), (16), (17), (18),
(19), (20), (21), (22), (23), (24), (25), (26), (27), (28);

DROP TABLE IF EXISTS `menuPrice`;
CREATE TABLE menuPrice (
    menuPriceID INT AUTO_INCREMENT PRIMARY KEY,
    menuItemID INT,
    price DECIMAL(10, 2) NOT NULL,
    effectiveDate DATETIME NOT NULL,
    # endDate DATETIME DEFAULT NULL,
    FOREIGN KEY (menuItemID) REFERENCES menuItem(menuItemID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `menuPrice` (menuPriceID,menuItemID, price, effectiveDate) VALUES
(1, 1,18, '2024-05-01 00:00:00'),
(2, 1,19, '2024-05-02 00:00:00'),
(3, 2,13.8, '2024-05-01 00:00:00'),
(4, 2,14.8, '2024-05-05 00:00:00'),
(5, 3,68, '2024-05-01 00:00:00');

INSERT INTO `menuPrice` (menuItemID, price, effectiveDate) VALUES
(4, 20, '2023-01-01 00:00:00'), (4, 22, '2023-06-01 00:00:00'),
(5, 15, '2023-02-01 00:00:00'), (5, 18, '2023-06-02 00:00:00'),
(6, 25, '2023-01-09 00:00:00'), (6, 28, '2023-06-03 00:00:00'),
(7, 30, '2023-01-01 00:00:00'), (7, 32, '2023-06-04 00:00:00'),
(8, 12, '2023-03-01 00:00:00'), (8, 11.5, '2023-06-05 00:00:00'),
(9, 18, '2023-01-01 00:00:00'), (9, 17, '2023-06-06 00:00:00'),
(10, 22, '2023-03-01 00:00:00'), (10, 23.8, '2023-06-07 00:00:00'),
(11, 28, '2023-02-01 00:00:00'), (11, 30, '2023-06-08 00:00:00'),
(12, 32, '2023-01-01 00:00:00'), (12, 34, '2023-06-09 00:00:00'),
(13, 14, '2023-01-01 00:00:00'), (13, 16, '2023-06-10 00:00:00'),
(14, 20, '2023-04-01 00:00:00'), (14, 22, '2023-06-11 00:00:00'),
(15, 18, '2023-01-01 00:00:00'), (15, 20, '2023-06-12 00:00:00'),
(16, 24, '2023-01-01 00:00:00'), (16, 26, '2023-06-13 00:00:00'),
(17, 28, '2023-06-01 00:00:00'), (17, 30, '2024-06-14 00:00:00'),
(18, 12, '2023-01-01 00:00:00'), (18, 14, '2023-06-15 00:00:00'),
(19, 16, '2023-01-01 00:00:00'), (19, 18, '2023-06-16 00:00:00'),
(20, 22, '2023-08-01 00:00:00'), (20, 24, '2023-06-17 00:00:00'),
(21, 28, '2023-07-01 00:00:00'), (21, 30, '2024-06-18 00:00:00'),
(22, 32, '2023-05-01 00:00:00'), (22, 34, '2024-06-16 00:00:00'),
(23, 14, '2023-03-01 00:00:00'), (23, 16, '2023-06-19 00:00:00'),
(24, 20, '2023-09-01 00:00:00'), (24, 22, '2024-06-20 00:00:00'),
(25, 18, '2023-04-03 00:00:00'), (25, 20, '2023-06-21 00:00:00'),
(26, 24, '2023-10-01 00:00:00'), (26, 26, '2023-06-22 00:00:00'),
(27, 28, '2023-01-21 00:00:00'), (27, 30, '2023-06-23 00:00:00'),
(28, 12, '2023-01-01 00:00:00'), (28, 14, '2023-06-24 00:00:00');

# 拆分为收藏商家和收藏菜品
# DROP TABLE IF EXISTS `Favorite`;
# CREATE TABLE Favorite (
#     UserID INT,
#     MerchantID INT,
#     DishID INT,
#     FavoriteDate DATETIME NOT NULL,
#     PRIMARY KEY (UserID, MerchantID, DishID),
#     FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,
#     FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID) ON DELETE CASCADE,
#     FOREIGN KEY (DishID) REFERENCES Dish(DishID) ON DELETE CASCADE
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
    FOREIGN KEY (userID) REFERENCES user(userID) ON DELETE CASCADE,
    FOREIGN KEY (merchantID) REFERENCES merchant(merchantID) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `FavoriteMerchant` VALUES (1,2,2,'2024-4-30 5:30:25');
INSERT INTO `FavoriteMerchant` VALUES (2,1,1,'2024-5-6 7:30:00');
INSERT INTO `FavoriteMerchant` VALUES (3,3,3,'2024-5-15 23:59:58');
INSERT INTO `FavoriteMerchant` (userId,merchantId,favoriteDate)VALUES (2,4,'2024-5-15 23:59:58');
INSERT INTO `FavoriteMerchant` (userId,merchantId,favoriteDate)VALUES (5,4,'2024-5-16 3:59:58');
INSERT INTO `FavoriteMerchant` (userId,merchantId,favoriteDate)VALUES (4,4,'2024-5-25 13:59:58');
INSERT INTO `FavoriteMerchant` (userId,merchantId,favoriteDate)VALUES (3,5,'2024-5-5 23:59:58');

DROP TABLE IF EXISTS `FavoriteDish`;
CREATE TABLE FavoriteDish (
    favoriteDishID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    dishID INT,
    FavoriteDate DATETIME NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID) ON DELETE CASCADE,
    FOREIGN KEY (dishID) REFERENCES Dish(dishID) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `FavoriteDish` VALUES (1,2,1,'2024-5-30 15:30:25');
INSERT INTO `FavoriteDish` VALUES (2,1,1,'2024-6-6 7:50:00');
INSERT INTO `FavoriteDish` VALUES (3,3,3,'2024-6-15 13:59:58');
INSERT INTO `FavoriteDish` (userId,dishId,favoriteDate) VALUES (3,4,'2024-6-15 13:00:58');
INSERT INTO `FavoriteDish` (userId,dishId,favoriteDate) VALUES (5,4,'2024-4-15 23:59:05');
INSERT INTO `FavoriteDish` (userId,dishId,favoriteDate) VALUES (6,4,'2024-6-15 3:59:58');
INSERT INTO `FavoriteDish` (userId,dishId,favoriteDate) VALUES (7,9,'2024-5-15 13:59:00');
INSERT INTO `FavoriteDish` (userId,dishId,favoriteDate) VALUES (6,9,'2024-6-10 13:59:58');
INSERT INTO `FavoriteDish` (userId,dishId,favoriteDate) VALUES (4,9,'2024-6-5 13:59:40');


DROP TABLE IF EXISTS `Review`;
CREATE TABLE Review (
    ReviewID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    MerchantID INT,
    DishID INT,
    Rating DECIMAL(2, 1),
    Content TEXT,
    ReviewDate DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,
    FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID) ON DELETE CASCADE,
    FOREIGN KEY (DishID) REFERENCES Dish(DishID) ON DELETE CASCADE
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
    MerchantID INT,
    OrderDate DATETIME NOT NULL,
    Status ENUM('Pending','Completed','Ended') NOT NULL,
    OrderType ENUM('Queue','Online'),
    TotalPrice DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,
    FOREIGN KEY (merchantID) REFERENCES Merchant(MerchantID) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `MyOrder` VALUES(1,1,1,'2024-5-1 6:32:29','Completed','Queue',0);  # totalPrice应该由查询得出
INSERT INTO `MyOrder` VALUES(2,3,2,'2024-5-8 18:10:10','Ended','Queue',0);
INSERT INTO `MyOrder` VALUES(3,2,3,'2024-5-17 13:29:20','Pending','Online',0);
INSERT INTO MyOrder (UserID, MerchantID, OrderDate, status, OrderType, TotalPrice) VALUES
(1, 1, '2024-06-10 12:00:00','Completed', 'Queue',0),
(1, 1, '2024-06-12 14:00:00','Completed', 'Queue',0),
(1, 1, '2024-06-14 16:00:00','Completed', 'Queue',0),
(1, 1, '2024-06-16 18:00:00','Completed', 'Queue',0),
(1, 1, '2024-06-18 20:00:00','Completed', 'Queue',0);
INSERT INTO MyOrder (UserID, MerchantID, OrderDate, status, OrderType, TotalPrice) VALUES
(2, 2, '2024-06-10 12:00:00','Completed', 'Queue',0),
(2, 2, '2024-06-12 14:00:00','Completed', 'Queue',0),
(2, 2,  '2024-06-14 16:00:00','Completed', 'Online',0),
(2, 2, '2024-06-16 18:00:00','Completed', 'Online',0),
(2, 2, '2024-06-18 20:00:00','Completed', 'Online',0);
INSERT INTO MyOrder (UserID, MerchantID, OrderDate, status, OrderType, TotalPrice) VALUES
(3, 3, '2024-06-10 12:00:00','Completed', 'Queue',0),
(3, 3, '2024-06-12 14:00:00','Completed', 'Queue',0),
(3, 3, '2024-06-14 16:00:00','Completed', 'Queue',0),
(3, 3, '2024-06-16 18:00:00','Completed', 'Queue',0),
(3, 3, '2024-06-18 20:00:00','Completed', 'Queue',0);
INSERT INTO MyOrder (UserID, MerchantID, OrderDate, status, OrderType, TotalPrice) VALUES
(4, 4, '2024-06-10 12:00:00','Completed', 'Online',0),
(4, 4, '2024-06-12 14:00:00','Completed', 'Online',0),
(4, 4, '2024-06-14 16:00:00','Completed', 'Online',0),
(4, 4, '2024-06-16 18:00:00','Completed', 'Online',0),
(4, 4, '2024-06-18 20:00:00','Completed', 'Online',0);
INSERT INTO MyOrder (UserID, MerchantID, OrderDate, status, OrderType, TotalPrice) VALUES
(5, 5, '2024-06-10 12:00:00','Completed', 'Queue',0),
(5, 5, '2024-06-12 14:00:00','Completed', 'Online',0),
(5, 5, '2024-06-14 16:00:00','Completed', 'Online',0),
(5, 5, '2024-06-16 18:00:00','Completed', 'Queue',0),
(5, 5, '2024-06-18 20:00:00','Completed', 'Online',0);


DROP TABLE IF EXISTS `OrderItem`;
CREATE TABLE OrderItem (
    OrderItemID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT,
    DishID INT,
    Quantity INT NOT NULL,
#     Price DECIMAL(10, 2) NOT NULL,
--     PRIMARY KEY (OrderID, DishID),
    FOREIGN KEY (OrderID) REFERENCES MyOrder(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (DishID) REFERENCES Dish(DishID) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
# INSERT INTO `OrderItem` VALUES (1,1,2,3,4.9);
# INSERT INTO `OrderItem` VALUES (2,2,2,5,8);
# INSERT INTO `OrderItem` VALUES (3,3,3,1,25);
INSERT INTO `OrderItem` VALUES (1,1,2,1);  # quantity应该由按钮增加，但这里可以作为效果展示
INSERT INTO `OrderItem` VALUES (2,2,2,1);
INSERT INTO `OrderItem` VALUES (3,3,3,1);

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
    AdminID INT DEFAULT 1,
    Content TEXT NOT NULL,
    MessageDate DATETIME,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,
    FOREIGN KEY (AdminID) REFERENCES Admin(AdminID) ON DELETE CASCADE
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
    FOREIGN KEY (AdminID) REFERENCES Admin(AdminID) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `AdminActionLog` VALUES(1,1,'ADD_USER',1,'2004-5-5 21:34:45');
INSERT INTO `AdminActionLog` VALUES(2,1,'UPDATE_MERCHANT',2,'2004-5-23 18:34:59');
INSERT INTO `AdminActionLog` VALUES(3,1,'DELETE_USER',3,'2004-5-26 15:39:51');

DROP TABLE IF EXISTS `LoyalCustomers`;
CREATE TABLE LoyalCustomers (
    LoyalCustomerID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    MerchantID INT,
    PurchaseCount INT,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,
    FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Reserve`;
CREATE TABLE Reserve (
    ReserveId INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    MerchantID INT,
    ReserveDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,
    FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DELIMITER //

CREATE TRIGGER InsertLoyalCustomer
AFTER INSERT ON MyOrder
FOR EACH ROW
BEGIN
    DECLARE purchase_count INT;

    -- 计算用户在商户的总消费次数
    SELECT COUNT(*) INTO purchase_count
    FROM MyOrder
    WHERE UserID = NEW.UserID AND MerchantID = NEW.MerchantID;

    -- 如果消费次数超过阈值且LoyalCustomers表中没有该记录，则插入
    IF purchase_count >= 5 THEN
        IF NOT EXISTS (
            SELECT 1 FROM LoyalCustomers
            WHERE UserID = NEW.UserID AND MerchantID = NEW.MerchantID
        ) THEN
            INSERT INTO LoyalCustomers (UserID, MerchantID, PurchaseCount)
            VALUES (NEW.UserID, NEW.MerchantID, purchase_count);
        END IF;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER UpdateLoyalCustomer
AFTER INSERT ON MyOrder
FOR EACH ROW
BEGIN
    DECLARE purchase_count INT;

    -- 计算用户在商户的总消费次数
    SELECT COUNT(*) INTO purchase_count
    FROM MyOrder
    WHERE UserID = NEW.UserID AND MerchantID = NEW.MerchantID;

    -- 如果消费次数超过阈值且LoyalCustomers表中有该记录，则更新
    IF purchase_count >= 5 THEN
        UPDATE LoyalCustomers
        SET PurchaseCount = purchase_count
        WHERE UserID = NEW.UserID AND MerchantID = NEW.MerchantID;
    END IF;
END //

DELIMITER ;
