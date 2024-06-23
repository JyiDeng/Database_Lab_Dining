# djyTODO

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


## 收藏、评价
- [x] 用户收藏商家、菜品、查看商家下属所有菜品的收藏量
- [ ] 评价


## 高级查询