package com.example.mybatis.mapper;

import com.example.mybatis.entity.Employee;
import com.example.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper  {

    User Sel(int id);

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userName",column = "userName"),
            @Result(property = "passWord",column = "passWord"),
            @Result(property = "realName",column = "realName")
    })
    @Select("select * from user where id = #{id}")
    User getPersonById(Integer id);

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "birth",column = "birth"),
            @Result(property = "department",column = "department"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "order",column = "order")
    })
    @Select("select * from employee where id = #{id}")
    Employee getEmployeeById(Integer id);


    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "birth",column = "birth"),
            @Result(property = "department",column = "department"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "order",column = "order")
    })
    @Select("select * from employee")
    List<Employee> getEmployeeAll();

}
