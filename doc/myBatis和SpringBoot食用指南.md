# myBatis和SpringBoot食用指南


## 结构图和对应功能

PJ1-根目录
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── pj
│   │   │               ├── config：WebConfig一个文件，登录用
│   │   │               ├── controller：切换页面用，譬如AdminController就用`@RequestMapping`规定了是以`/admin`开头的网页，其中按照`@GetMapping`和`RequestMapping`等规定了往后加后缀可以访问什么网页
│   │   │               ├── entity：实体类，譬如User，有userName、userId、gender、role、age、ecardId、password等属性
│   │   │               ├── mapper：通过在`@Select`注解里写SQL语句，实现SQL语句的功能
│   │   │               ├── service：登录用（只用到了LoginService，它会被html调用。至于其他的接口和Implementation没什么用，后续应该也不用实现新的）
│   │   │               └── Pj1Application.java：启动类，点击运行即可启动数据库连接，无需更改
│   │   └── resources
│   │       ├── mapper：前期写的没什么用的映射文件，我们主要使用的是上方那个`Mapper`结尾的类
│   │       ├── static：暂时没用，应该是存css文件之类的，也就是网页调颜色美化用的（？）
│   │       ├── templates：存储了会被访问的html网页，譬如`adminSuccess.html`是admin的管理页。其中的php、以及部分半成品网页暂时应该没什么用
│   │       ├── application.properties: 配置文件（下同），包含spring、DataBase方言、thymeleaf、数据库账号密码等
│   │       ├── application.yml
|   |       ├── db.properties
|   |       └── mybatis-config.xml
├── doc：说明文档
└── pic：说明文档的图片


## Controller实现细节

开头加的注解中，@RestController应该是用于调网页的。@RequestMapping或者@GetMapping的括号里表明当访问到这个开头的网页时，会执行什么内容。
```java
@RestController
@RequestMapping("/admin")
public class AdminController {
    //  
}
```

几个@的区别：
+ @GetMapping和@RequestMapping反正是有区别的，写错了会报错，根据情况改过来
+ @RequestParam是传入了后续会使用的参数，在url里形如`/addUser?password=123&age=`(第一个参数前用?，各个参数之间用&)
+ @PathVariable是传入了只在路径里的参数，在url里形如`/root/allUserList`
```java
public class AdminController{
    @GetMapping("/{path}/allUserList")
    public List<User> userList(@PathVariable String path) {
        return userMapper.findAll();
    }

    @RequestMapping("/{path}/addUser")
    public String addUser(@RequestParam Long id, String userName, String gender, Long ecardId, String role, Integer age,String password, @PathVariable String path) {
        if (userMapper.findByID(id) == null){
            User newUser = new User(id,userName,gender,ecardId,role,age,password);
            userMapper.insert(newUser);
            return "User" + id + " is added successfully!";
        }else{
            return "User" + id + " already exists!";
        }
    }
}
```

## Entity实现细节

+ 在开头加上注解，省去所有的@Getter和@Setter。
+ 属性名称需要和数据库表一样，并且在这里保持小驼峰，否则匹配不上。
```java
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Component
public class Dish {
    //...... 
}
```

## Mapper实现细节

+ 记得开头注解。 
+ sql语句是什么，@的符号就是什么，譬如@Select，@Insert，@Delete。
+ 在sql语法的基础上需要用#{}扩起来，里面填的是Entity类里的属性名称（总之就是首字母小写）

```java
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (UserId,UserName,Gender,EcardId,Role,Age,Password) VALUES (#{userID},#{userName},#{gender},#{ecardID},#{role},#{age},#{password})")
    void insert(User user);

    @Update("UPDATE user SET UserName = #{userName}, Gender = #{gender}, EcardID = #{ecardID}, Role = #{role}, Age = #{age}, Password = #{password} WHERE UserID = #{userID}")
    void update(User user);
}
```


## html实现细节

+ 大部分特殊的元素都是<xx>写在它俩里面</xx>；<br>是换行符，只用写一个，不用写后面闭合的
+ 文字应该写在<body>里的任何地方都行，不用括起来，比较灵活
+ `<button onclick="redirectTo('/allUserList')">View All Users</button>`是按钮，onclick引号里的是写的重定向到新的网页的脚本，View那个时button上显示的字符
+ 在`<script></script>`里写脚本（也就是可以运行的程序，也就是javascript）
+ 总之让gpt干活
