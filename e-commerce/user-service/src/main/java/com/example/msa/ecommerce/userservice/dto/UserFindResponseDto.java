package com.example.msa.ecommerce.userservice.dto;

import com.example.msa.ecommerce.userservice.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Execute like graphQL
public class UserFindResponseDto {

    private String mail;
    private String name;
    private String userId;

    private List<OrderResponseDto> orders;

    public static UserFindResponseDto from(User user, List<OrderResponseDto> orders) {
        var instance = new UserFindResponseDto();
        instance.mail = user.getEmail();
        instance.name = user.getName();
        instance.userId = user.getUserId();

        instance.orders = orders.stream()
                .map(OrderResponseDto::from)
                .collect(Collectors.toList());

        return instance;
    }


}
