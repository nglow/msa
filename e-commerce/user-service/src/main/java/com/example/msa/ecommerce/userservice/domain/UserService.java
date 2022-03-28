package com.example.msa.ecommerce.userservice.domain;

import com.example.msa.ecommerce.userservice.dto.UserCreateRequestDto;
import com.example.msa.ecommerce.userservice.dto.UserCreateResponseDto;
import com.example.msa.ecommerce.userservice.dto.UserFindResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public UserFindResponseDto findUserByUserId(String userId) {
        var user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var orders = new ArrayList<OrderRecord>();

        return UserFindResponseDto.from(user, orders);
    }

    public List<UserFindResponseDto> findAllUsers() {
        var orders = new ArrayList<OrderRecord>();

        return userRepository.findAll().stream()
                .map(x -> UserFindResponseDto.from(x, orders))
                .collect(Collectors.toList());
    }
}
