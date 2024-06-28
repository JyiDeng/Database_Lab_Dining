# 核心功能SQL说明
邓景宜 22302010075
吴苏庭 22302010087

本文档为pj中与数据库交互的主要查询、插入、删除等操作的SQL代码及说明。

## DishMapper
- **主要负责**：与*菜品*相关的操作、查询

### 按关键词查询菜品
使用`LIKE`模式匹配。
```sql
SELECT *
FROM dish
WHERE merchantId = #{merchantId}  # 当前商家id
    AND dishName LIKE CONCAT('%', #{keyword}, '%')
```

### 获取菜品平均评分
使用`AVG`聚合函数，计算当前菜品所有评分记录的均分。
```sql
SELECT AVG(rating) AS average_rating
FROM Review
WHERE dishId = #{dishId}  # 当前菜品id
```

### 查询某个商户所有菜品通过排队点餐和在线点餐的销量
通过连接订单、订单项、菜品和商户表，并按菜品ID分组，按`OrderType`分别统计每个菜品的排队和在线订单销量。
```sql
SELECT oi.DishID AS dishId, d.dishName AS DishName, m.MerchantName AS merchantName,
SUM(CASE WHEN o.OrderType = 'Queue' THEN oi.Quantity ELSE 0 END) AS QueueSales,
SUM(CASE WHEN o.OrderType = 'Online' THEN oi.Quantity ELSE 0 END) AS OnlineSales
FROM MyOrder o
JOIN OrderItem oi ON o.OrderID = oi.OrderID
JOIN Dish d ON oi.DishID = d.DishID
LEFT JOIN merchant m on d.merchantId = m.merchantId
GROUP BY oi.DishID, d.dishName
```

### 查询某个商户购买所有菜品次数最多的人
通过连接订单、订单项和菜品表，按照用户ID和菜品ID分组，在指定商户中，计算每个用户购买该菜品的次数，然后按照购买次数降序排列，并限制结果为1条，即购买次数最多的用户。
```sql
SELECT oi.DishID, o.UserID, COUNT(*) as purchaseCount
FROM OrderItem oi
JOIN MyOrder o ON oi.OrderID = o.OrderID
JOIN Dish d ON oi.DishID = d.DishID
WHERE d.MerchantID = #{merchantId}
GROUP BY o.UserID, oi.DishID
ORDER BY purchaseCount DESC
LIMIT 1
```


## FavoriteMapper
- **主要负责**：与*收藏*相关的操作、查询

### 查询用户收藏的所有菜品
在`FavoriteDish`表里查找收藏记录，通过join `Dish`展示所收藏菜品的详细信息。
```sql
SELECT fd.*, d.dishName as dishName
FROM FavoriteDish fd
JOIN dish d ON fd.dishId = d.dishId
WHERE fd.userId = #{userId}  # 当前用户id
```

### 收藏商户
在收藏记录表（`FavoriteMerchant`）里插入新数据。
```sql
INSERT INTO FavoriteMerchant (userId, merchantID, FavoriteDate) VALUES (#{userId}, #{merchantId}, NOW())
```

### 查询某个商户所有菜品的收藏量
使用`COUNT`聚合函数，计算每个菜品被收藏的次数；如果某个菜品没有被收藏，COUNT(f.userId)将为0。通过左连接收藏表与菜品表，指定商家的所有菜品的收藏量都会被返回。
```sql
SELECT d.DishID, d.DishName, COUNT(f.userId) as count
FROM Dish d
LEFT JOIN FavoriteDish f ON d.DishID = f.dishId
WHERE d.MerchantID = #{merchantId}
GROUP BY d.DishID, d.DishName
```

### 用户收藏菜品的销量筛选

通过连接收藏表、订单项表和订单表，按照不同点餐方式（Queue和Online）分别统计用户收藏的每个菜品的销量，并按照菜品ID和菜品名称分组。
```sql
SELECT
    fd.UserID,
    YEARWEEK(o.OrderDate, 1) AS period,
    d.DishID,
    d.DishName,
    COALESCE(SUM(CASE WHEN o.OrderType = 'Queue' THEN oi.Quantity ELSE 0 END), 0) AS QueueSales,
    COALESCE(SUM(CASE WHEN o.OrderType = 'Online' THEN oi.Quantity ELSE 0 END), 0) AS OnlineSales
FROM
    FavoriteDish fd
    JOIN Dish d ON fd.DishID = d.DishID
    JOIN OrderItem oi ON d.DishID = oi.DishID
    JOIN MyOrder o ON oi.OrderID = o.OrderID
WHERE
    fd.UserID = #{userId}
    AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL 1 #{timePeriod})  # e.g. timePeriod = MONTH, 即获得近一月内不同点餐方式的销量
GROUP BY
    fd.UserID,
    YEARWEEK(o.OrderDate, 1),
    d.DishID,
    d.DishName
ORDER BY
    fd.UserID,
    YEARWEEK(o.OrderDate, 1),
    d.DishID;
```


## LoginMapper
- **主要负责**：与*登录*相关的操作

### 用户登录验证
在用户表中查询id与密码，结果不为空则验证成功。
```sql
SELECT COUNT(*)
FROM user
WHERE userId = #{userId} AND password = #{password}
```


## OrderMapper
- **主要负责**：与*订单*相关的操作、查询

### 创建订单
```sql
INSERT INTO MyOrder (UserID, merchantID, OrderDate, Status, OrderType, TotalPrice) VALUES (#{userId}, #{merchantId}, #{orderDate}, #{status}, #{orderType}, #{totalPrice})
```

### 获取订单中的每一项以及当前有效的价格
通过查找菜品价格的有效日期`MAX`值（日期值最大即最新），筛选出当前订单中每个菜品的有效价格。
```sql
SELECT oi.*, mp.price AS price,d.dishName as dishName
FROM OrderItem oi
JOIN menuItem mi ON oi.dishID = mi.dishID
left JOIN dish d ON oi.dishID = d.dishID
JOIN menuPrice mp ON mi.menuItemId = mp.menuItemID
WHERE oi.OrderID = #{orderId}
AND mp.effectiveDate =
    (SELECT MAX(effectiveDate)
     FROM menuPrice
     WHERE menuItemId = mi.menuItemId) 
```


## UserMapper
- **主要负责**：与*用户*相关的操作、查询

### 管理员更新用户信息
```sql
UPDATE user
SET UserName = #{userName}, Gender = #{gender}, EcardID = #{ecardID}, Role = #{role}, Age = #{age}, Password = #{password}
WHERE UserID = #{userId}
```

### 用户活跃度分析 - 每月活跃度

通过按用户ID和订单日期的月份分组，计算每个用户距今一个月的线上与线下订单数量，并按照用户ID和月份排序。
```sql
SELECT
    o.UserID,
    YEARWEEK(o.OrderDate, 1) AS period,
    COALESCE(SUM(CASE WHEN o.OrderType = 'Queue' THEN 1 ELSE 0 END), 0) AS QueueCounts,
    COALESCE(SUM(CASE WHEN o.OrderType = 'Online' THEN 1 ELSE 0 END), 0) AS OnlineCounts
FROM MyOrder o 
WHERE
    o.UserID = #{userId}
    AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL #{timePeriod}+1 MONTH)
    AND o.OrderDate < DATE_SUB(NOW(), INTERVAL #{timePeriod} MONTH)
GROUP BY
    o.UserID,
    YEARWEEK(o.OrderDate, 1)
ORDER BY
    o.UserID,
    YEARWEEK(o.OrderDate, 1);
```

### 用户活跃度分析 - 不同时间段活跃度

通过按用户ID和订单时间的时段（早上、下午、晚上）分组，计算每个用户在不同时间段的订单数量，并按照用户ID和时段排序。
```sql
SELECT UserID, 
    CASE 
        WHEN HOUR(OrderDate) BETWEEN 6 AND 12 THEN 'Morning'
        WHEN HOUR(OrderDate) BETWEEN 12 AND 18 THEN 'Afternoon'
        ELSE 'Evening'
    END AS TimeOfDay,
    COUNT(*) AS OrderCount
FROM MyOrder
GROUP BY UserID, TimeOfDay
ORDER BY UserID, TimeOfDay
```

### 用户群体特征分析 - 年龄段

通过连接用户表、订单表和订单项表，按照用户年龄和菜品ID分组，统计每个年龄段用户购买各个菜品的次数，并按照年龄和菜品ID排序。
```sql
SELECT u.Age, d.DishID, d.DishName, COUNT(oi.Quantity) AS PurchaseCount
FROM User u
JOIN MyOrder o ON u.UserID = o.UserID
JOIN OrderItem oi ON o.OrderID = oi.OrderID
JOIN Dish d ON oi.DishID = d.DishID
GROUP BY u.Age, d.DishID, d.DishName
ORDER BY u.Age, d.DishID
```

### 用户群体特征分析 - 性别

通过连接用户表、订单表和订单项表，按照用户性别和菜品ID分组，统计不同性别用户购买各个菜品的次数，并按照性别和菜品ID排序。
```sql
SELECT u.Gender, d.DishID, d.DishName, COUNT(oi.Quantity) AS PurchaseCount
FROM User u
JOIN MyOrder o ON u.UserID = o.UserID
JOIN OrderItem oi ON o.OrderID = oi.OrderID
JOIN Dish d ON oi.DishID = d.DishID
GROUP BY u.Gender, d.DishID, d.DishName
ORDER BY u.Gender, d.DishID
```

### 用户群体特征分析 - 角色

通过连接用户表、订单表和订单项表，按照用户角色和菜品ID分组，统计不同角色用户购买各个菜品的次数，并按照角色和菜品ID排序。
```sql
SELECT u.Role, d.DishID, d.DishName, COUNT(oi.Quantity) AS PurchaseCount
FROM User u
JOIN MyOrder o ON u.UserID = o.UserID
JOIN OrderItem oi ON o.OrderID = oi.OrderID
JOIN Dish d ON oi.DishID = d.DishID
GROUP BY u.Role, d.DishID, d.DishName
ORDER BY u.Role, d.DishID
```

### 用户评价特征分析

通过连接用户表和评价表，按照用户角色、年龄和性别分组，统计每个用户组的评价数量和平均评分，并按照角色、年龄和性别排序。
```sql
SELECT u.Role, u.Age, u.Gender, COUNT(r.ReviewID) AS ReviewCount, AVG(r.Rating) AS AverageRating
FROM User u
JOIN Review r ON u.UserID = r.UserID
GROUP BY u.Role, u.Age, u.Gender
ORDER BY u.Role, u.Age, u.Gender
```


## MenuMapper
- **主要负责**：与*菜单*相关的操作、查询

### 查询指定菜品的所有历史价格
连接菜单价格、菜单项和菜品表，获得指定菜品的所有历史价格。
```sql
SELECT mp.*, d.DishName AS dishName
FROM menuPrice mp
JOIN menuItem mi on mi.menuItemId = mp.menuItemId
JOIN dish d on d.dishId = mi.dishId
WHERE mi.menuItemId = #{menuItemId}
```

## MerchantMapper
- **主要负责**：与*商家*相关的操作、查询

### 查询商家的详细信息
```sql
SELECT *
FROM merchant
WHERE MerchantID = #{merchantId}
```

### 商户忠实粉丝的消费分布

通过先找出在指定时间段内达到消费次数阈值的忠实粉丝，然后统计这些粉丝在该商户购买的各个菜品的次数，按照菜品ID、菜品名称和用户ID分组。
```sql
WITH LoyalCustomers AS (
    SELECT o.UserID
    FROM MyOrder o
    WHERE o.MerchantID = #{merchantId}
    AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL #{timePeriod})
    GROUP BY o.UserID
    HAVING COUNT(o.OrderID) > #{threshold}
)
SELECT d.DishID, d.DishName, lc.UserID, SUM(oi.Quantity) AS PurchaseCount
FROM LoyalCustomers lc
JOIN MyOrder o ON lc.UserID = o.UserID
JOIN OrderItem oi ON o.OrderID = oi.OrderID
JOIN Dish d ON oi.DishID = d.DishID
WHERE o.MerchantID = #{merchantId}
AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL #{timePeriod})
GROUP BY d.DishID, d.DishName, lc.UserID
ORDER BY d.DishID, lc.UserID
```


## MessageMapper
- **主要负责**：与*消息*相关的查询

### 查询用户的消息列表
```sql
SELECT * FROM message WHERE user_id = #{userId}
```