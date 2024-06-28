# djyTODO


## 剩下要做的内容

+ 高级查询：完成剩下的内容
  + [ ] 菜品数据分析：某个商户所有菜品的评分、销量以及购买该菜品次数最多的人：只做了销量貌似，评分和次数不太确定--Admin
  + [ ] 用户群体特征分析：那个json有点意义不明，需要写一点解释的语句，或者调整成更明白的
  + [ ] 用户群体特征分析：在各个商户的用餐次数
  + [ ] 查询id=4的商户的忠实粉丝在一月内的消费分布这种--Loyal
  + [ ] 查询当前用户活跃度（点餐频率变化趋势、时间段）
  + [x] 查询当前用户活跃度：用户在不同时间段的活跃程度。
  + [ ] 新增设计：查看id=5的商户在一个月内的用户评分趋势变化
+ [x] 管理员增删改商家：这个比较简单，和增删改用户一样的模仿就可以
+ [ ] 索引：syj说只要在sql（？）哪里加一个就好，让gpt帮忙吧
+ [ ] 事务：似乎是加一个service里的东西
+ [ ] 分页：问gpt
+ [x] 修评分


## 订单Order
- [x] 将价格通过查询的方式绑定到查出来的OrderItem上：只用改Mapper http://localhost:8080/user/2/orderDetail?orderId=3

## 菜单

- [x] 把menuItems页面的url.match不是function解决 http://localhost:8080/user/2/menuItems?id=2
- [x] 把updateOrder页面No static resource 404问题、订单没法更新qunatity解决 
+ 现在的问题：增加了数量以后，要一键提交给订单表格更新。但是，没有办法提交数组；一个一个提交，又会不停弹出页面。
  + 现在的解决方案：一个一个提交，在下面弹出弹窗，三秒后消失
+ 如果把quantity的初始值也改为orderItem.quantity or 0 两个传入一个就好办了，后面还方便直接读取！
+ 最后的解决：
  + 增加了订单创建的位置，传递hasPending判断先建订单才能增删菜品；
  + 在fetch中删除json部分才不报错；
  + 避免使用quantity-${menuItemId}这样的编号，会查找不到——>直接删除了表格中显示的quantity，避免复杂的操作（而且有null的Left JOIN有点问题）
  + 删除增删项目的提交按钮，每次按增删都会使用弹窗提示添加成功
- [x] 修复：menuItem导致的 orderDetail页查出来null http://localhost:8080/user/1/menuItems?id=3 http://localhost:8080/user/1/orderDetail?orderId=6
  + "然后日期的查询经常查出来是空"说的应该是订单里面没数据？解决了

## 收藏、评价
- [x] 用户收藏商家、菜品、查看商家下属所有菜品的收藏量
- [x] 用户进行评价：完全实现评价提交、数据库写入评价评星数据
- [ ] 使用新的查询语句完成新的功能

