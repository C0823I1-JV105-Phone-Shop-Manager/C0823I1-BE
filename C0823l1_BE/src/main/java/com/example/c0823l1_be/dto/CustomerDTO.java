package com.example.c0823l1_be.dto;

import com.example.c0823l1_be.CustomValidator.annotation.ExistedCustomerEmail;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
    private Integer id;
    @NotBlank(message = "Yêu cầu nhập tên khách hàng")
    @Size(min = 1, max = 100, message = "Tên khách hàng không được để trống và nhỏ hơn 100 ký tự")
    private String name;
    @NotBlank(message = "Yêu cầu nhập số điện thoại")
    @Size(min = 9, max = 11, message = "Số điện thoại phải có 9 hoặc 11 số")
    private String phone;
//    @ExistedCustomerEmail
    @NotBlank(message = "Yêu cầu nhập email")
    @Email(message = "Email không đúng định dạng")
    private String email;
    @NotNull(message = "Yêu cầu nhập ngày sinh")
    @PastOrPresent(message = "Ngày sinh không được sau ngày hiện tại")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    @NotBlank(message = "Yêu cầu nhập địa chỉ")
    private String address;
}
