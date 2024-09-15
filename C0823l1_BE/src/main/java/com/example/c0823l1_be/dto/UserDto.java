package com.example.c0823l1_be.dto;

  import jakarta.validation.constraints.NotEmpty;
  import jakarta.validation.constraints.Pattern;
  import jakarta.validation.constraints.Size;
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
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Full name must contain only letters")
    private String fullName;
    @NotEmpty
    private String address;
    @NotEmpty
    private String dob;
    @NotEmpty
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must contain only numbers")
    @Size(min = 10, max = 10, message = "Phone number must contain 10 numbers")
    private String phoneNumber;
}
