package com.example.c0823l1_be.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CustomerViewDTO {
        Integer getId();
        String getName();
        String getPhone();
        String getEmail();
        LocalDate getBirthdate();
        String getAddress();
}
