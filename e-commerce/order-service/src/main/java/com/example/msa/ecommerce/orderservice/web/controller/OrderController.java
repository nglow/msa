package com.example.msa.ecommerce.orderservice.web.controller;

import com.example.msa.ecommerce.orderservice.domain.service.OrderService;
import com.example.msa.ecommerce.orderservice.dto.OrderCreateRequestDto;
import com.example.msa.ecommerce.orderservice.dto.OrderCreateResponseDto;
import com.example.msa.ecommerce.orderservice.dto.OrderFindResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public OrderCreateResponseDto order(@PathVariable("userId") String userId,
                                        @RequestBody OrderCreateRequestDto requestDto) {
         return orderService.order(userId, requestDto);
    }

    @GetMapping("/users/{userId}/orders")
    public List<OrderFindResponseDto> order(@PathVariable("userId") String userId) {
        return orderService.findOrdersByUserId(userId);
    }

}
