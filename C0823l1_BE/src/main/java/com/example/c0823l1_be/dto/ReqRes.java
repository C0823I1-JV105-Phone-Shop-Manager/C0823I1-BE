package com.example.c0823l1_be.dto;

import com.example.c0823l1_be.entity.Staff;
import com.example.c0823l1_be.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {
    private Long id;
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String role;
    private String username;
    private String password;
    private String fullName;
    private String address;
    private String dob;
    private String phoneNumber;
    private User user;
    private Staff staff;
    private UserDto userDto;
    private List<User> userList;
    private List<Staff> staffList;
}
