package com.example.c0823l1_be.dto;

import com.example.c0823l1_be.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class StaffDTO {

    private Integer id;

    private User user;


    private String fullName;


    private String address;


    private String dob;


    private String phoneNumber;

}
