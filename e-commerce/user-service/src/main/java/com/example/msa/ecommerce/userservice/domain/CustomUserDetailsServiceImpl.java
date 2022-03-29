package com.example.msa.ecommerce.userservice.domain;

import com.example.msa.ecommerce.userservice.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(user.getEmail(), user.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserResponseDto findUserDetailsByEmail(String email) {
        var user= userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Can't find user by email transmitted - " + email));

        return UserResponseDto.from(user);
    }
}
