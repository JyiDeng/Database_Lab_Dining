package com.example.pj.controller;

import com.example.pj.entity.*;
import com.example.pj.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

//@RestController
// 如果用Controller才能用html加载，如果用RestController才能用文字加载
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    DishMapper dishMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    FavoriteMapper favoriteMapper;


//    @RequestMapping("/id={id}")
//    public User getUserById(@PathVariable Long id) {
//        return userMapper.findByID(id);
//    }

    // 显示特定用户个人信息
    @RequestMapping("/{path}/userSelfInfo")
    public String getUserSelfInfo(@PathVariable Long path,Model model) {
        User user = userMapper.findByID(path);
        model.addAttribute(user);
        return "userSelfInfo";
    }

    @RequestMapping("/{path}/searchMerchant2")
    public String searchMerchant2(@RequestParam String keyword,@PathVariable Long path, Model model) {
        List<Merchant> merchants = merchantMapper.searchMerchant(keyword);
        model.addAttribute("merchants", merchants);
        return "searchMerchant"; // 返回模板文件名

    }

    @RequestMapping("/{path}/searchMerchantDetails")
    public String getMerchantDetails(@RequestParam Long id, Model model,@PathVariable Long path) {
        Merchant merchant = merchantMapper.getMerchantDetails(id);
        model.addAttribute("merchant",merchant);
        model.addAttribute("userId",path);
        Long pendingCount = orderMapper.countPendingOrders(id,path);
        model.addAttribute("hasPendingOrder", pendingCount > 0);
        return "merchantDetail";
    }

    @RequestMapping("/{path}/searchDishes")
    public String searchDishes(@RequestParam Long merchantId, String keyword, Model model,@PathVariable Long path) {
        List<Dish> dishes = dishMapper.getDishByName(keyword,merchantId);
        model.addAttribute("dishes", dishes);
        return "dishSearch";
    }

    @RequestMapping("/{path}/searchDishDetails")
    public String getDishDetails(@RequestParam Long dishId, Model model,@PathVariable Long path) {
        Dish dish = dishMapper.getDishDetails(dishId);
        model.addAttribute("dish",dish);
        return "dishDetail";
    }

    // 收藏菜品
    @RequestMapping("/{userId}/findFavoriteDish")
    public String findFavoriteDish(Model model,@PathVariable Long userId) {
        List<UserFavoriteDish> userFavoriteDishes = favoriteMapper.findAllFavoriteDish(userId);
        model.addAttribute("userFavoriteDishes",userFavoriteDishes);
        return "favoriteDish";
    }

    @RequestMapping("/{userId}/addFavoriteDish")
    public String addFavoriteDish(Model model,@PathVariable Long userId, @RequestParam Long dishId) {
        int count = favoriteMapper.findFavoriteDish(userId,dishId);
        if(count > 0){

            model.addAttribute("message", "您已收藏过该菜品");
        }else{
            model.addAttribute("message", "菜品收藏成功！");
            favoriteMapper.addFavoriteDish(userId, dishId);
        }

        return "favoriteAddSuccess";
    }

//     收藏商户
    @RequestMapping("/{userId}/findFavoriteMerchant")
    public String findFavoriteMerchant(Model model,@PathVariable Long userId) {
        favoriteMapper.findAllFavoriteMerchant(userId);
        List<UserFavoriteMerchant> userFavoriteMerchants = favoriteMapper.findAllFavoriteMerchant(userId);
        model.addAttribute("userFavoriteMerchants",userFavoriteMerchants);
        return "favoriteMerchant";
    }

    @RequestMapping("/{userId}/addFavoriteMerchant")
    public String addFavoriteMerchant(Model model,@PathVariable Long userId, @RequestParam Long merchantId) {
        int count = favoriteMapper.findFavoriteMerchant(userId,merchantId);
        if(count > 0){

            model.addAttribute("message", "您已收藏过该商家");
        }else{
            model.addAttribute("message", "菜品收藏成功！");
            favoriteMapper.addFavoriteMerchant(userId, merchantId);
        }

        return "favoriteAddSuccess";
    }

    @RequestMapping("/{userId}/dishFavoriteCounts")
    public String getDishFavoriteCounts(Model model, @PathVariable Long userId, @RequestParam Long merchantId) {
        List<UserFavoriteDish> dishFavoriteCounts = favoriteMapper.findDishFavoriteCountsByMerchant(merchantId);
        model.addAttribute("dishFavoriteCounts", dishFavoriteCounts);
        return "favoriteDishCount";
    }

    // 查询用户订单
    @RequestMapping("/{userId}/viewOrders")
    public String getOrdersByUserId(@PathVariable Long userId,Model model) {
        List<MyOrder> orders = orderMapper.getOrdersByUserId(userId);
        model.addAttribute("orders",orders);
        return "orderView";
    }

    @RequestMapping("/{path}/orderDetail")
    public String orderDetail(@PathVariable Long path, Model model, @RequestParam Long orderId) {
        List<OrderItem> orderItems = orderMapper.getOrderItemsByOrderId(path,orderId);
        model.addAttribute("orderItems", orderItems);

        return "orderDetail";
    }

    @RequestMapping("/{path}/createOrder")
    public String createOrder2(@PathVariable String path, @RequestParam Long userId, Long merchantId, String status, String orderType) {
        MyOrder newOrder = new MyOrder();
        newOrder.setUserId(userId);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setMerchantId(merchantId);
        newOrder.setStatus(status);
        newOrder.setOrderType(orderType);
        newOrder.setTotalPrice(0F);  // 初始化为0，Float类型
        orderMapper.createOrder(newOrder);

        return "orderCreateSuccess";
//        return "redirect:/orders/" + merchantID;
    }

//    @PostMapping("/{path}/updateOrder")
//    public ResponseEntity<?> updateOrder(@RequestBody MyOrder request, @PathVariable String path,@RequestParam Long menuItemId, Long quantity,Long merchantId) {
//        try {
//            updateOrder(menuItemId, quantity,merchantId);
//            return ResponseEntity.ok().body("Order updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating order: " + e.getMessage());
//        }
//    }

//    @RequestMapping("/{path}/updateOrder2")
//    public void updateOrder2( @PathVariable String path,@RequestParam Long menuItemId, Long quantity,Long merchantId) {
////        try {
////            updateOrder(menuItemId, quantity,merchantId);
////            return ResponseEntity.ok().body("Order updated successfully");
////        } catch (Exception e) {
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating order: " + e.getMessage());
////        }
//        Long dishId = orderMapper.findDishIdByMenuItemId(menuItemId);
//        Long orderId = orderMapper.findOrderIdByMerchantId(merchantId);
//        OrderItem orderItem = orderMapper.findOrderItem(orderId, dishId);
//
//        if (orderItem == null) {
//            orderMapper.insertOrderItem(orderId, dishId, quantity);
//        } else {
//            if (quantity > 0) {
//                orderMapper.updateOrderItemQuantity(orderId, dishId, quantity);
//            } else {
//                orderMapper.deleteOrderItem(orderId, dishId);
//            }
//        }
//    }

    @RequestMapping("/{path}/updateOrder3")
    public String updateOrder3( @PathVariable String path,@RequestParam Long dishId,Long merchantId,Long delta) {

//        for(MenuItem menuItem: menuItems){
//            Long menuItemId = menuItem.getMenuItemId();
//            Long dishId = menuItem.getDishId();
        try{
            Long orderId = orderMapper.findOrderIdByMerchantId(merchantId);
            OrderItem orderItem = orderMapper.findOrderItem(orderId, dishId);

            if (delta==1 && orderItem == null) {
                orderMapper.insertOrderItem(orderId, dishId, delta);
            } else {
                // 根据是增还是减讨论：
                if (delta > 0) {
                    orderMapper.updateOrderItemQuantity(orderId, dishId, 1L);
                } else {
                    // 如果减，那么不能超过0
                    if(orderItem.getQuantity()>1) {
                        orderMapper.updateOrderItemQuantity(orderId, dishId, -1L);
                    }else{
                        orderMapper.deleteOrderItem(orderId,dishId);
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "orderUpdateSuccess";
    }
    
    @RequestMapping("/{path}/review/{dishId}")
    public String getDishReview(@PathVariable Long dishId,Model model,@PathVariable Long path){
        List<Review> reviews = dishMapper.dishReview(dishId);
        model.addAttribute("reviews", reviews);
        Float avgRating = dishMapper.getAvgRating(dishId);
        model.addAttribute("rating", (float)(Math.round(avgRating*100))/100);
        return "reviews"; // 返回模板文件名
    }

    @RequestMapping("/{path}/reviewDish")
    public String makeDishReview(@RequestParam Long orderId,Model model,@PathVariable Long path){

        List<OrderItem> orderItems = orderMapper.getOrderItemsNameOnlyByOrderId(path,orderId);
        model.addAttribute("orderItems", orderItems);
        return "reviewDish"; // 返回模板文件名
    }


    // 查询菜品的最新价格
    // or 查询菜品的历史价格变化？
    @RequestMapping("{path}/latestPrice/{menuItemId}")
    public String getLatestPriceByMenuItemId(Model model,@PathVariable Long menuItemId,@PathVariable Long path) {
        // MenuPrice menuprice = menuMapper.findLatestPriceByMenuItemId(menuItemId);
        List<MenuPrice> allMenuPrice = menuMapper.findAllPriceByMenuItemId(menuItemId);
        model.addAttribute("allMenuPrice",allMenuPrice);
        return "latestPrice";
    }


    @RequestMapping("/{path}/menuItems")
    public String enterMenu(@PathVariable Long path, Model model,@RequestParam Long id) {
        List<MenuItem> menuItems = menuMapper.getMenuItemsByMerchantId(id);
        model.addAttribute("menuItems",menuItems);
        model.addAttribute("userId",path);
        model.addAttribute("merchantId",id);
        Long pendingCount = orderMapper.countPendingOrders(id,path);
        model.addAttribute("hasPendingOrder", pendingCount > 0);
//        Long orderId = orderMapper.findOrderIdByMerchantId(id);
//        List<Long> quantities = new ArrayList<>();

        return "menuItems";
    }


    @RequestMapping("/{path}/msg")
    public String getUserMessages( Model model,@PathVariable Long path) {
        List<Message> messages = messageMapper.getMessagesByUserId(path);
        model.addAttribute("messages",messages);
        return "messages";
    }

    @RequestMapping("/{path}/sales")
    public String getSales( Model model,@PathVariable String path) {
        List<Sales> sales = dishMapper.getSales();
        model.addAttribute("sales",sales);
        return "sales";
    }

    @RequestMapping("/{path}/orderSubmitSuccess")
    public String orderSubmit( Model model,@PathVariable String path,@RequestParam Long orderId) {
        orderMapper.orderSubmitUpdateCompleted(orderId);
        return "orderSubmitSuccess";
    }

    @RequestMapping("/{path}/confirmAcceptation")
    public String confirmAcceptation(Model model,@PathVariable String path,@RequestParam Long orderId) {
        orderMapper.orderAcceptUpdateEnded(orderId);
        return "orderEndSuccess";
    }

    @RequestMapping("/{path}/reviewUpload")
    public String reviewSuccess( Model model,@PathVariable Long path,@RequestParam  Long dishId, Float rating, String content) {
        LocalDateTime now = LocalDateTime.now();
        orderMapper.reviewSuccess(path,dishId,rating,content,now);
        return "reviewSuccess";
    }

    @GetMapping("/favorites/sales")
    public ResponseEntity<List<DishSales>> getFavoriteDishSales(
            @RequestParam int userId,
            @RequestParam String timePeriod) {
        List<DishSales> sales = favoriteMapper.getFavoriteDishSales(userId, timePeriod);
        return ResponseEntity.ok(sales);
    }

    // 用户活跃度分析
    @GetMapping("/user-activity/weekly")
    public ResponseEntity<List<UserActivity>> getWeeklyActivity() {
        List<UserActivity> activity = userMapper.getWeeklyActivity();
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/user-activity/monthly")
    public ResponseEntity<List<UserActivity>> getMonthlyActivity() {
        List<UserActivity> activity = userMapper.getMonthlyActivity();
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/user-activity/timeofday")
    public ResponseEntity<List<UserActivity>> getActivityByTimeOfDay() {
        List<UserActivity> activity = userMapper.getActivityByTimeOfDay();
        return ResponseEntity.ok(activity);
    }

    // 用户群体特征分析
    @GetMapping("/user-characteristics/age")
    public ResponseEntity<List<UserCharacteristic>> getCharacteristicsByAge() {
        List<UserCharacteristic> characteristics = userMapper.getCharacteristicsByAge();
        return ResponseEntity.ok(characteristics);
    }

    @GetMapping("/user-characteristics/gender")
    public ResponseEntity<List<UserCharacteristic>> getCharacteristicsByGender() {
        List<UserCharacteristic> characteristics = userMapper.getCharacteristicsByGender();
        return ResponseEntity.ok(characteristics);
    }

    @GetMapping("/user-characteristics/role")
    public ResponseEntity<List<UserCharacteristic>> getCharacteristicsByRole() {
        List<UserCharacteristic> characteristics = userMapper.getCharacteristicsByRole();
        return ResponseEntity.ok(characteristics);
    }

    @GetMapping("/user-characteristics/reviews")
    public ResponseEntity<List<UserReviewCharacteristic>> getReviewCharacteristics() {
        List<UserReviewCharacteristic> characteristics = userMapper.getReviewCharacteristics();
        return ResponseEntity.ok(characteristics);
    }
}
