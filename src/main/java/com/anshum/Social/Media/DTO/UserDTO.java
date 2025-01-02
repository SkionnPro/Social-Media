package com.anshum.Social.Media.DTO;

import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be atleast 6 characters long")
    private String password;
}
