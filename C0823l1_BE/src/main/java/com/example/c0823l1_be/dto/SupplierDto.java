package com.example.c0823l1_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {

    private String uid;
    private String name;
    private String address;
    private String phone;
    private String email;

}
