package com.example.c0823l1_be.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StaffDto {

    private Integer id;

    private String username;

    private String password;

    private String role;

    private String fullName;

    private String address;

    private Date dob;

    private String phoneNumber;

}
