package com.example.msa.ecommerce.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor(staticName = "from")
@Getter
public class UserCreateResponseDto {

    private String email;
    private String password;
    private String name;
    private String userId;
    private String createdAt;

    private String encryptedPassword;


}
