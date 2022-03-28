package com.example.msa.ecommerce.orderservice.domain.service;

import com.example.msa.ecommerce.orderservice.domain.persistence.Order;
import com.example.msa.ecommerce.orderservice.domain.persistence.OrderRepository;
import com.example.msa.ecommerce.orderservice.dto.OrderCreateRequestDto;
import com.example.msa.ecommerce.orderservice.dto.OrderCreateResponseDto;
import com.example.msa.ecommerce.orderservice.dto.OrderFindResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderCreateResponseDto order(String userId, OrderCreateRequestDto requestDto) {
        var order = requestDto.toEntity(userId);
        orderRepository.save(order);

        return OrderCreateResponseDto.from(order);
    }
    public OrderFindResponseDto findOrderByOrderId(String orderId) {
        var order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order is not found by orderId transmitted - " + orderId));

        return OrderFindResponseDto.from(order);
    }
    public List<OrderFindResponseDto> findOrdersByUserId(String userId) {
        var orders = orderRepository.findByUserId(userId);

        return orders.stream()
                .map(OrderFindResponseDto::from).collect(Collectors.toList());
    }
}
