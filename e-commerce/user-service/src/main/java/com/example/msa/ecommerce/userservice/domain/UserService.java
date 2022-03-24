package com.example.msa.ecommerce.userservice.domain;

import com.example.msa.ecommerce.userservice.dto.UserCreateRequestDto;
import com.example.msa.ecommerce.userservice.dto.UserCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {
        var userId = UUID.randomUUID().toString();
        var user = User.create(requestDto.getEmail(), requestDto.getName(), userId, passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);

        return UserCreateResponseDto.from(user.getEmail(), requestDto.getPassword(), user.getName(), user.getUserId(), "2020-10-10", user.getEncryptedPassword());
    }
}
