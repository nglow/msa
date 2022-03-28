package com.example.msa.ecommerce.orderservice.dto;

import com.example.msa.ecommerce.orderservice.domain.persistence.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderCreateRequestDto {

    // Need not-null
    private String productId;
    private Integer quantity;
    private Integer unitPrice;

    public Order toEntity(String userId) {
        return Order.create(this.productId, this.quantity, this.unitPrice, userId);
    }
}
