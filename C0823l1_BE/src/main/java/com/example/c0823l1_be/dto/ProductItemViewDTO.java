package com.example.c0823l1_be.dto;

import java.time.LocalDate;

public interface ProductItemViewDTO {
        Integer getId();
        ProductViewDTO getProduct();

        interface ProductViewDTO {
                int getId();
                String getName();
                String getImage();
                Long getPrice();
        };
        String getSerial();
        LocalDate getImportDate();
        LocalDate getExportDate();
}
