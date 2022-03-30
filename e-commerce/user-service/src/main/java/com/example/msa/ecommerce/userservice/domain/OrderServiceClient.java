package com.example.msa.ecommerce.userservice.domain;

import com.example.msa.ecommerce.userservice.dto.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping("/order-service/users/{userId}/orders")
    List<OrderResponseDto> findOrders(@PathVariable("userId") String userId);
}
