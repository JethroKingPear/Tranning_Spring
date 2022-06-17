package com.example.demo.Models;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    
    private int productId;
    private String productNm;
    private String cateName;
}
