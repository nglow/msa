package com.example.msa.ecommerce.userservice.dto;

import com.example.msa.ecommerce.userservice.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserResponseDto {

    private String email;

    private String userId;

    public static UserResponseDto from(User user) {
        var instance = new UserResponseDto();
        instance.email = user.getEmail();
        instance.userId = user.getUserId();

        return instance;
    }
}
