package com.example.msa.ecommerce.userservice.dto;

import com.example.msa.ecommerce.userservice.domain.OrderRecord;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createdAt;

    private String orderId;

    public static OrderResponseDto from(OrderResponseDto order) {
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
