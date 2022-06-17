package com.example.demo.Models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestUser {

     @NotNull
     private  int userId;

//     @NotEmpty(message = "not empty")
     @Email(message = "Email khong hop le")
     private  String email;
}
