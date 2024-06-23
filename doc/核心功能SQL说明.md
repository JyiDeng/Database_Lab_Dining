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
通过连接订单和订单项表，按照用户ID分组，计算每个用户购买该菜品的次数，然后按照购买次数降序排列，并限制结果为1条，即购买次数最多的用户。
```sql
SELECT oi.DishID, o.UserID, COUNT(*) as purchaseCount
FROM OrderItem oi
JOIN MyOrder o ON oi.OrderID = o.OrderID
WHERE oi.DishID = #{dishId}
GROUP BY o.UserID
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

## MessageMapper
- **主要负责**：与*消息*相关的查询

### 查询用户的消息列表
```sql
SELECT * FROM message WHERE user_id = #{userId}
```