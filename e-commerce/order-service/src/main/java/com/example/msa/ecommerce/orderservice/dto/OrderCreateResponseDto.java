package com.example.msa.ecommerce.orderservice.dto;

import com.example.msa.ecommerce.orderservice.domain.persistence.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderCreateResponseDto {

    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createdAt;

    private String orderId;

    public static OrderCreateResponseDto from(Order order) {
        var instance = new OrderCreateResponseDto();
        instance.productId = order.getProductId();
        instance.quantity = order.getQuantity();
        instance.unitPrice = order.getUnitPrice();
        instance.totalPrice = order.getTotalPrice();
        instance.createdAt = order.getCreatedAt();
        instance.orderId = order.getOrderId();

        return instance;
    }
}
