package com.example.msa.ecommerce.userservice.web;

import com.example.msa.ecommerce.userservice.web.config.Greeting;
import com.example.msa.ecommerce.userservice.domain.UserService;
import com.example.msa.ecommerce.userservice.dto.UserCreateRequestDto;
import com.example.msa.ecommerce.userservice.dto.UserCreateResponseDto;
import com.example.msa.ecommerce.userservice.dto.UserFindResponseDto;
import com.example.msa.ecommerce.userservice.dto.UserSignInRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/health-check")
    public String status(HttpServletRequest request) {
        return String.format("It's working in user service on port %s", request.getServerPort());
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

    @GetMapping("/users")
    public ResponseEntity<List<UserFindResponseDto>> findAllUsers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAllUsers());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserFindResponseDto> findByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findUserByUserId(userId));
    }

    @PostMapping("/users/sign-in")
    public void signIn(@RequestBody UserSignInRequestDto requestDto) {
    }

}
