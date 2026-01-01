package com.merobazar.ecommerce.service;

import com.merobazar.ecommerce.entity.User;
import com.merobazar.ecommerce.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> findByEmailOrPhone(String emailOrPhone) {
        Optional<User> byEmail = userRepository.findByEmail(emailOrPhone);
        if (byEmail.isPresent()) {
            return byEmail;
        }
        return userRepository.findByPhone(emailOrPhone);
    }

    public User saveUser(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash())); // hash the password before saving it
        return userRepository.save(user);
    }

    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPasswordHash());
    }

    // soft delete
    public boolean deactivateUser(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return false;
        }
        user.setIsActive(false);
        userRepository.save(user);
        return true;

    }
}
