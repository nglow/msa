package com.example.msa.ecommerce.userservice.domain;

import com.example.msa.ecommerce.userservice.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {

    UserResponseDto findUserDetailsByEmail(String email);
}
