
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE if not exists User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(50) NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    EcardID INT NOT NULL,
    Role ENUM('Student', 'Staff') NOT NULL,
    Age INT
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES (1,'Alice1','Female',75,'Student',20);
INSERT INTO `user` VALUES (2,'Tom1','Male',50,'Student',25);
INSERT INTO `user` VALUES (3,'Octpus1','Other',25,'Staff',1);

DROP TABLE IF EXISTS `Merchant`;
CREATE TABLE if not exists Merchant(
    MerchantID INT AUTO_INCREMENT PRIMARY KEY,
    MerchantName VARCHAR(50) NOT NULL,
    MainDishes VARCHAR(50) NOT NULL,
    Address VARCHAR(50) NOT NULL,
    Menu VARCHAR(50) NOT NULL,
    AvgRating FLOAT
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `Merchant` VALUES (1,'南苑','黄焖鸡','国年路200号','黄焖鸡，铁板饭',3.9);
insert into `Merchant` VALUES (2,'全家','八喜','政肃路60号西面','八喜，关东煮',4);
insert into `Merchant` VALUES (3,'幻想食堂Topolino pizzeria','PESTO E PATATE','昌平路888-5号','PESTO E PATATE, SPRING SALAD',4.1);

DROP TABLE IF EXISTS `Dish`;
CREATE TABLE if not exists Dish (
    DishID INT AUTO_INCREMENT PRIMARY KEY,
    DishName VARCHAR(50) NOT NULL,
    Price FLOAT NOT NULL,
    Category VARCHAR(50) NOT NULL,
    Description VARCHAR(200),
    Picture VARCHAR(50),
    Flavor VARCHAR(50),
    Ingredients VARCHAR(50),
    Allergens VARCHAR(50),
    NutritionInfo VARCHAR(50),
    AvgRating FLOAT,
    MerchantID INT,
    FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `Dish` VALUES (1,'黄焖鸡',18,'中餐','酱很香','examplePic1.com','原味','鸡肉、青椒、土豆、姜','' ,'鸡肉鲜美，青椒维生素高',4.5,1);
insert into `Dish` VALUES (2,'八喜',13.8,'甜品','经典、小贵','examplePic2.com','巧克力味','牛乳、可可粉、白砂糖、添加剂','牛乳','钙含量高',4.3,2);
insert into `Dish` VALUES (3,'PESTO E PATATE',68,'西餐','经典青酱披萨','examplePic3.com','青酱口味','马苏里拉芝士，青酱，烤甜土豆，迷迭香，大蒜，油浸番茄干','大蒜','营养均衡',4.5,3);

DROP TABLE IF EXISTS `Favorite`;
CREATE TABLE Favorite (
    UserID INT,
    MerchantID INT,
    DishID INT,
    FavoriteDate DATETIME NOT NULL,
    PRIMARY KEY (UserID, MerchantID, DishID),
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID),
    FOREIGN KEY (DishID) REFERENCES Dish(DishID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `Favorite` VALUES (1,2,2,'2024-4-30 5:30:25');
INSERT INTO `Favorite` VALUES (2,1,1,'2024-5-6 7:30:00');
INSERT INTO `Favorite` VALUES (3,3,3,'2024-5-15 23:59:58');


DROP TABLE IF EXISTS `Review`;
CREATE TABLE Review (
    ReviewID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    MerchantID INT,
    DishID INT,
    Rating DECIMAL(2, 1) NOT NULL,
    Content TEXT,
    ReviewDate DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (MerchantID) REFERENCES Merchant(MerchantID),
    FOREIGN KEY (DishID) REFERENCES Dish(DishID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `Review` VALUES (1,3,2,2,4.5,'好味下次还来','2024-5-3 0:25:34');
INSERT INTO `Review` VALUES (2,1,1,1,4,'不错，有点咸','2024-5-16 8:39:10');
INSERT INTO `Review` VALUES (3,2,3,3,5,'非常好披萨，爱来自幻想','2024-5-24 0:39:19');

DROP TABLE IF EXISTS `MyOrder`; # 以免和Order保留字重复
CREATE TABLE MyOrder (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    OrderDate DATETIME NOT NULL,
    Status ENUM('Pending', 'Completed', 'Cancelled') NOT NULL,
    OrderType ENUM('Queue', 'Online'),
    TotalAmount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `MyOrder` VALUES(1,1,'2024-5-1 6:32:29','Completed','Queue',30);
INSERT INTO `MyOrder` VALUES(2,3,'2024-5-8 18:10:10','Cancelled','Queue',99.9);
INSERT INTO `MyOrder` VALUES(3,2,'2024-5-17 13:29:20','Pending','Online',10.24);

DROP TABLE IF EXISTS `OrderItem`;
CREATE TABLE OrderItem (
    OrderID INT,
    DishID INT,
    Quantity INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (OrderID, DishID),
    FOREIGN KEY (OrderID) REFERENCES `MyOrder`(OrderID),
    FOREIGN KEY (DishID) REFERENCES Dish(DishID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `OrderItem` VALUES (1,2,3,4.9);
INSERT INTO `OrderItem` VALUES (2,2,5,8);
INSERT INTO `OrderItem` VALUES (3,3,1,25);

DROP TABLE IF EXISTS `Admin`;
CREATE TABLE Admin (
    AdminID INT PRIMARY KEY,
    Name VARCHAR(100),
    PasswordHash VARCHAR(255)
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
INSERT INTO `message` VALUES(1,1,1,'您的餐品已做好，请取餐！','2024-5-15 22:39:10');
INSERT INTO `message` VALUES(2,2,1,'亲，对于用餐体验如何？点击填写用餐体验->','2024-5-19 21:32:12');
INSERT INTO `message` VALUES(3,3,1,'感谢您的反馈，我们竭诚为您服务。','2024-5-21 19:35:19');

DROP TABLE IF EXISTS `AdminActionLog`;
CREATE TABLE AdminActionLog (
    LogID INT PRIMARY KEY AUTO_INCREMENT,
    AdminID INT,
    ActionType ENUM('ADD_USER', 'DELETE_USER', 'UPDATE_USER', 'ADD_MERCHANT', 'DELETE_MERCHANT', 'UPDATE_MERCHANT'),
    TargetID INT,
    Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (AdminID) REFERENCES Admin(AdminID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `AdminActionLog` VALUES(1,1,'ADD_USER',1,'2004-5-5 21:34:45');
INSERT INTO `AdminActionLog` VALUES(2,1,'UPDATE_MERCHANT',2,'2004-5-23 18:34:59');
INSERT INTO `AdminActionLog` VALUES(3,1,'DELETE_USER',3,'2004-5-26 15:39:51');

