package com.example.demo.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Models.Product;
import com.example.demo.Models.User;
import com.example.demo.mappers.user.UserMapper;

@Service
public class UserService {

    @Autowired
    UserMapper mapper;
    

    @Transactional(rollbackFor = { SQLException.class })
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        HashMap<String, Object> request = new HashMap<>();
        // request.put("age",12);
        users = mapper.searchAllUser(request);
        return users;
    }

    @Transactional(rollbackFor = { SQLException.class })
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        // request.put("age",12);
        products = mapper.searchAllProduct();
        return products;
    }
}
