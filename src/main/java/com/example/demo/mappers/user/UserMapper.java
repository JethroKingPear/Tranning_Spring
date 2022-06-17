package com.example.demo.mappers.user;

import com.example.demo.Models.Product;
import com.example.demo.Models.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> searchAllUser(HashMap request) ;

    List<Product> searchAllProduct();
}
