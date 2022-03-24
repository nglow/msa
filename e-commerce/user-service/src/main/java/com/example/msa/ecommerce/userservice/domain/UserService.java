package com.example.msa.ecommerce.userservice.domain;

import com.example.msa.ecommerce.userservice.dto.UserCreateRequestDto;
import com.example.msa.ecommerce.userservice.dto.UserCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {
        var userId = UUID.randomUUID().toString();
        var user = User.create(requestDto.getEmail(), requestDto.getName(), userId, requestDto.getPassword());
        userRepository.save(user);

        return UserCreateResponseDto.from(user.getEmail(), user.getEncryptedPassword(), user.getName(), user.getUserId(), "2020-10-10", user.getEncryptedPassword());
    }
}
