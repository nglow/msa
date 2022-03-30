package com.example.msa.ecommerce.userservice.web;

import com.example.msa.ecommerce.userservice.web.config.Greeting;
import com.example.msa.ecommerce.userservice.domain.UserService;
import com.example.msa.ecommerce.userservice.dto.UserCreateRequestDto;
import com.example.msa.ecommerce.userservice.dto.UserCreateResponseDto;
import com.example.msa.ecommerce.userservice.dto.UserFindResponseDto;
import com.example.msa.ecommerce.userservice.dto.UserSignInRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
//@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final Greeting greeting;
    private final UserService userService;
    private final Environment environment;

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's working in user service on port(local.server.port)-%s, " +
                        "on port(server.port)-%s, with token secret-%s, with token time-%s, with test - %s",
                environment.getProperty("local.server.port"),
                environment.getProperty("server.port"),
                environment.getProperty("token.secret"),
                environment.getProperty("token.expiration_time"),
                environment.getProperty("token.test"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<UserCreateResponseDto> createUser(@RequestBody UserCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(requestDto));
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserFindResponseDto>> findAllUsers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAllUsers());
    }

    @GetMapping(value = "/users/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserFindResponseDto> findByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findUserByUserId(userId));
    }

    @PostMapping("/users/sign-in")
    public void signIn(@RequestBody UserSignInRequestDto requestDto) {
    }

}
