package com.example.c0823l1_be.dto;

  import jakarta.validation.constraints.NotEmpty;
  import lombok.AllArgsConstructor;
  import lombok.Builder;
  import lombok.Data;
  import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto  {

    private Integer id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String role;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String address;
    @NotEmpty
    private String dob;
    @NotEmpty
    private String phoneNumber;
}
