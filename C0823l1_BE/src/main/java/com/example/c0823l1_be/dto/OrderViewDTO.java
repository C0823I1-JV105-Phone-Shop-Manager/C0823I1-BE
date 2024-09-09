package com.example.c0823l1_be.dto;

import java.time.LocalDate;

public interface OrderViewDTO {
        String getId();
        StaffViewDTO getStaff();
        interface StaffViewDTO{
                Integer getId();
                String getFullName();
        }
        CustomerViewDTO getCustomer();

}
