package com.example.msa.ecommerce.userservice.web;

import com.example.msa.ecommerce.userservice.config.Greeting;
import com.example.msa.ecommerce.userservice.domain.UserService;
import com.example.msa.ecommerce.userservice.dto.UserCreateRequestDto;
import com.example.msa.ecommerce.userservice.dto.UserCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final Greeting greeting;
    private final UserService userService;

    @GetMapping("/health-check")
    public String status() {
        return "It's working in user service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public UserCreateResponseDto createUser(@RequestBody UserCreateRequestDto requestDto) {
        return userService.createUser(requestDto);
    }
}
