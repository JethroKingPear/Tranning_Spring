package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.User;
import com.example.demo.Services.UserService;


@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/findAll")
    public ResponseEntity<?> finAllUser(){
            return ResponseEntity.ok(service.getAllUsers());
    }


    @GetMapping("/findProduct")
    public ResponseEntity<?> searchAllProduct(){
        return ResponseEntity.ok(service.getAllProduct());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewUser(@RequestBody User user){
        return ResponseEntity.ok(service.createNewUser(user));
    }

}
