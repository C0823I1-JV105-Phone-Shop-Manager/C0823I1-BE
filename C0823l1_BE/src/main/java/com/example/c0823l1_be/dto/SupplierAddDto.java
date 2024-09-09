package com.example.c0823l1_be.dto;


import com.example.c0823l1_be.CustomValidator.annotation.UniqueConstraintEmail;
import com.example.c0823l1_be.CustomValidator.annotation.UniqueConstraintPhone;
import com.example.c0823l1_be.CustomValidator.annotation.UniqueConstraintUid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

@Validated
public class SupplierAddDto {
    @NotBlank(message = "Mã số không được để trống")
    @Pattern(regexp = "^MS_[a-zA-Z0-9]{1,7}$", message = "Định dạng Mã số không đúng")
    @UniqueConstraintUid
    private String uid;
    @NotBlank(message = "Tên không được để trống")
    @Pattern(regexp = "^[^~`!@#$%^&*()+={}\\[\\]:;\"'|\\\\<>?]{0,150}$",
            message = "Tên không được chứa ký tự đặc biệt, hoặc ký tự cấm sử dụng, độ dài tối đa 150 ký tự")
    private String name;
    @NotBlank(message = "Địa chỉ không được để trống")
    @Pattern(regexp = "^[^~`!@#$%^&*()+={}\\[\\]:;\"'|\\\\<>?]{0,255}$",
            message = "Địa chỉ không được chứa ký tự đặc biệt, hoặc ký tự cấm sử dụng, độ dài tối đa 255 ký tự")
    private String address;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0(3[2-9]|5[689]|7[06-9]|8[0-689]|9[0-46-9])[0-9]{7,12}",
            message = "Số điện thoại không hợp lệ, chỉ sử dụng số điện thoại Việt Nam và các số tiếp theo, gồm 10-15 chữ số")
    @UniqueConstraintPhone
    private String phone;
    @NotBlank(message = "Email không được để trống")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_.]{2,64}[^._]@[^.][a-zA-Z0-9]{2,64}\\.[0-9a-z-.]{2,63}",
            message = "Email không đúng định dạng, không quá 150 ký tự")
    @UniqueConstraintEmail
    private String email;

    private String description;

    public SupplierAddDto() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
