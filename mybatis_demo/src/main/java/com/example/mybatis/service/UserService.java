package com.example.mybatis.service;

import com.example.mybatis.entity.Employee;
import com.example.mybatis.entity.User;
import com.example.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User Sel(int id){
        return userMapper.Sel(id);
    }

    public User Sel1(Integer id){
        return userMapper.getPersonById(id);
    }

    public Employee Sel2(Integer id){
        return userMapper.getEmployeeById(id);
    }

    public List<Employee> Sel3(){
        return userMapper.getEmployeeAll();
    }
}