package com.example.demo.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Models.Product;
import com.example.demo.Models.User;
import com.example.demo.mappers.user.UserMapper;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper mapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

    public int createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.createNewUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.findUserById(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserId() + "",
                user.getPassword(), authorities);
        return userDetails;
    }

}
