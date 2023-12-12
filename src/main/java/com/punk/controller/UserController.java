package com.punk.controller;


import com.github.pagehelper.PageInfo;
import com.punk.common.Result;
import com.punk.entity.Params;
import com.punk.entity.User;
import com.punk.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

//    查询全部
    @GetMapping
    public Result findAll(){
        List<User> userList = userService.findAll();
        return Result.success(userList);
    }

//    条件查询
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<User> userPageinfo =  userService.findBySearch(params);
        return Result.success(userPageinfo);
    }

//    增加内容
    @PostMapping
    public Result addUser(@RequestBody User user){
        if (user.getId() == null){
            userService.adduser(user);
        }else{
            userService.updateuser(user);
        }
        return Result.success();
    }

//    单条删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.delete(id);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User loginUser = userService.login(user);
        if (loginUser != null){
            return Result.success(loginUser);
        }
        return Result.error("用户名或密码错误");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User admin) {
        userService.adduser(admin);
        return Result.success();
    }

//    批量删除
    @PutMapping("/deleteList")
    public Result deleteList(@RequestBody List<User> list){
        for (User user : list){
            userService.delete(user.getId());
        }
        return Result.success();
    }
}
