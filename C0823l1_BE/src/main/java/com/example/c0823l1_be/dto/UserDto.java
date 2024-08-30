package com.example.c0823l1_be.dto;

  import com.example.c0823l1_be.entity.User;
  import lombok.AllArgsConstructor;
  import lombok.Builder;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    private String username;

    private String role;

    private String fullName;

    private String address;

    private String dob;

    private String phoneNumber;
}
