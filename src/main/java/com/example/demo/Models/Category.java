package com.example.demo.Models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private int cateId;
    private String cateName;
    private List<Product> products;
}
