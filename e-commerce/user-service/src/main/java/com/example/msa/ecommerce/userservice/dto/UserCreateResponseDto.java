package com.example.msa.ecommerce.userservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
