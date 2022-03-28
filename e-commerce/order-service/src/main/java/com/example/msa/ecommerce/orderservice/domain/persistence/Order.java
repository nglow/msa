package com.example.msa.ecommerce.orderservice.domain.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String productId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, unique = true)
    private String orderId;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDate createdAt;

    public static Order create(String productId, Integer quantity, Integer unitPrice, String userId) {
        var instance = new Order();
        instance.productId = productId;
        instance.quantity = quantity;
        instance.unitPrice = unitPrice;
        instance.userId = userId;

        instance.totalPrice = instance.unitPrice * instance.getQuantity();
        instance.orderId = UUID.randomUUID().toString();

        return instance;
    }
}
