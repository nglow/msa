package com.example.msa.ecommerce.userservice.dto;

import com.example.msa.ecommerce.userservice.domain.OrderRecord;
import com.example.msa.ecommerce.userservice.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Execute like graphQL
public class UserFindResponseDto {

    private String mail;
    private String name;
    private String userId;

    private List<OrderResponseDto> orderRecord;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class OrderResponseDto {
        private String productId;
        private Integer qty;
        private Integer unitPrice;
        private Integer totalPrice;
        private LocalDate createdAt;

        private String orderId;

        public static OrderResponseDto from(OrderRecord order) {
            var instance = new OrderResponseDto();
            instance.productId = order.getProductId();
            instance.qty = order.getQty();
            instance.unitPrice = order.getUnitPrice();
            instance.totalPrice = order.getTotalPrice();
            instance.createdAt = order.getCreatedAt();
            instance.orderId = order.getOrderId();

            return instance;
        }
    }

    public static UserFindResponseDto from(User user, List<OrderRecord> orders) {
        var instance = new UserFindResponseDto();
        instance.mail = user.getEmail();
        instance.name = user.getName();
        instance.userId = user.getUserId();

        instance.orderRecord = orders.stream()
                .map(OrderResponseDto::from)
                .collect(Collectors.toList());

        return instance;
    }


}
