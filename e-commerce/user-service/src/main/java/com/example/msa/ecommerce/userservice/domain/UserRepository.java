package com.example.msa.ecommerce.userservice.domain;

import com.example.msa.ecommerce.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
