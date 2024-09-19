package com.example.c0823l1_be.dto;

import com.example.c0823l1_be.entity.Customer;
import com.example.c0823l1_be.entity.ProductItem;
import com.example.c0823l1_be.entity.Staff;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private String id;
    @Valid
    @NotNull(message = "Không để trống")
    CustomerDTO customer;
    @NotEmpty(message = "Đơn hàng phải chứa sản phẩm")
    Integer[] productItemList;
}
