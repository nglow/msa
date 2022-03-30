package com.example.msa.ecommerce.userservice.domain;

import com.example.msa.ecommerce.userservice.dto.OrderResponseDto;
import com.example.msa.ecommerce.userservice.dto.UserCreateRequestDto;
import com.example.msa.ecommerce.userservice.dto.UserCreateResponseDto;
import com.example.msa.ecommerce.userservice.dto.UserFindResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;
    private final RestTemplate restTemplate;
    private final OrderServiceClient orderService;

    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {
        var userId = UUID.randomUUID().toString();
        var user = User.create(requestDto.getEmail(), requestDto.getName(), userId, passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);

        return UserCreateResponseDto.from(user.getEmail(), requestDto.getPassword(), user.getName(), user.getUserId(), "2020-10-10", user.getEncryptedPassword());
    }

    public UserFindResponseDto findUserByUserId(String userId) {
        var user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Use REST Template
//        var orderServiceUrl = String.format(Objects.requireNonNull(environment.getProperty("order_service.url")), userId);
//        log.debug("orderServiceUrl = {}", orderServiceUrl);
//        var ordersResponse = restTemplate.exchange(orderServiceUrl,
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderResponseDto>>() {});
//        var orders = ordersResponse.getBody();

        var orders = orderService.findOrders(userId);

        return UserFindResponseDto.from(user, orders);
    }

    public List<UserFindResponseDto> findAllUsers() {
        var orders = new ArrayList<OrderResponseDto>();

        return userRepository.findAll().stream()
                .map(x -> UserFindResponseDto.from(x, orders))
                .collect(Collectors.toList());
    }
}
