package com.example.pj.mapper;

import com.example.pj.entity.Menu;
import com.example.pj.entity.MenuItem;
import com.example.pj.entity.MenuPrice;
import com.example.pj.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("SELECT * FROM menu where merchantId = #{merchantId}")
    List<Menu> findAllMenus(Long merchantId);

//    @Insert("INSERT INTO menu (merchantID) VALUES (#{merchantId})")
//    @Options(useGeneratedKeys = true, keyProperty = "menuId")
//    void insertMenu(Menu menu);
    @Select("SELECT * FROM menu WHERE menuID = #{menuId}")
    Menu findMenuById(Long menuId);

    @Insert("INSERT INTO menu (menuId,merchantId) VALUES (#{menuId},#{merchantId})")
    void insertMenu(Long menuId, Long merchantId);

    @Delete("DELETE FROM menu WHERE menuID = #{menuId}")
    void deleteMenu(Long menuId);

    @Select("SELECT * FROM menuItem WHERE menuItemID = #{menuItemId}")
    MenuItem findMenuItemById(Long menuItemId);

    @Select("SELECT * FROM menuItem WHERE menuID = #{menuId}")
    List<MenuItem> findByMenuId(Long menuId);

//    @Insert("INSERT INTO menuItem (menuID, dishID, price) VALUES (#{menuId}, #{dishId}, #{price})")
//    @Options(useGeneratedKeys = true, keyProperty = "menuItemId")
//    void insertMenuItem(MenuItem menuItem);

    @Insert("INSERT INTO MenuItem(menuItemId, menuId, dishId,price) VALUES (#{menuItemId}, #{menuId}, #{dishId}, #{price})")
//    @Options(useGeneratedKeys = true, keyProperty = "menuItemId")
    void insertMenuItem(MenuItem menuItem);

    @Update("UPDATE menuItem SET price = #{price} WHERE menuItemID = #{menuItemId}")
    void updatePrice(@Param("menuItemId") Long menuItemId, @Param("price") Float price);

    @Delete("DELETE FROM menuPrice WHERE menuItemId = #{menuItemId}")
    void deleteMenuPrice(Long menuItemId);

    @Delete("DELETE FROM menuItem WHERE menuItemID = #{menuItemId}")
    void deleteMenuItemId(Long menuItemId);

    @Select("SELECT * FROM menuPrice WHERE menuPriceID = #{menuPriceId}")
    MenuPrice findMenuPriceById(Long menuPriceId);

    @Select("SELECT * FROM menuPrice WHERE menuItemID = #{menuItemId} ORDER BY effectiveDate DESC LIMIT 1")
    MenuPrice findLatestByMenuItemId(Long menuItemId);

    @Insert("INSERT INTO menuPrice (menuItemID, price, effectiveDate, endDate) VALUES (#{menuItemId}, #{price}, #{effectiveDate}, #{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "menuPriceId")
    void insertMenuPrice(MenuPrice menuPrice);

    @Select("SELECT * FROM menuPrice WHERE menuItemId = #{menuItemId} AND endDate IS NULL")
    MenuPrice findLatestPriceByMenuItemId(Long menuItemId);

    @Select("SELECT mp.*, d.DishName AS dishName " +
            "FROM menuPrice mp " +
            "JOIN menuItem mi on mi.menuItemId = mp.menuItemId " +
            "JOIN dish d on d.dishId = mi.dishId " +
            "WHERE mi.menuItemId = #{menuItemId}")
    List<MenuPrice> findAllPriceByMenuItemId(Long menuItemId);

    @Update("UPDATE menuPrice SET endDate = #{endDate} WHERE menuItemID = #{menuItemID} AND endDate IS NULL")
    void updateMenuPriceEndDate(@Param("menuItemID") Long menuItemId, @Param("endDate") String endDate);

    @Select("SELECT mi.*, d.dishName, mp.price " +
            "FROM menuItem mi " +
            "JOIN Dish d ON mi.dishID = d.DishID " +
            "JOIN menuPrice mp on mi.menuItemId = mp.menuItemId " +
            "WHERE d.merchantID = #{merchantId} " +
            "AND mp.endDate IS NULL")
    List<MenuItem> getMenuItemsByMerchantId(Long merchantId);


}
