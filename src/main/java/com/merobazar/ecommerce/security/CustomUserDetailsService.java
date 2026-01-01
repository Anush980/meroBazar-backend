package com.merobazar.ecommerce.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.merobazar.ecommerce.entity.User;

import org.springframework.stereotype.Service;

import com.merobazar.ecommerce.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(emailOrPhone)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()) // username field here is email
                .password(user.getPasswordHash())
                .authorities("ROLE_" + user.getRole())
                .build();
    }
}
