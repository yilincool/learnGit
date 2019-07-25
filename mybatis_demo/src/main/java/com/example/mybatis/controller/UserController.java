package com.example.mybatis.controller;


import com.example.mybatis.entity.User;
import com.example.mybatis.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:0xOO
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/testBoot")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
        return userService.Sel(id).toString();
    }

    @RequestMapping("getById/{id}")
    public String getPersonByid(@PathVariable Integer id){
        return userService.Sel1(id).toString();
    }

    @RequestMapping("getEmpById/{id}")
    public String getEmployeeById(@PathVariable Integer id){
        return userService.Sel2(id).toString();
    }

    @RequestMapping("getEmpAll")
    public String getEmployeeById(){
        return userService.Sel3().toString();
    }
}