package com.example.msa.ecommerce.userservice.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UserCreateRequestDto {

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "Password must be equal or greater than 8 characters and less than 16 chracters")
    private String password;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must not be less than two characters")
    private String name;
}
