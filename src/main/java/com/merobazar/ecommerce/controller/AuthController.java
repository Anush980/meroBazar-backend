package com.merobazar.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merobazar.ecommerce.dto.ApiResponse;
import com.merobazar.ecommerce.security.JwtUtil;
import com.merobazar.ecommerce.service.UserService;
import com.merobazar.ecommerce.entity.User;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(201).body(new ApiResponse<>("User registered Successfully.", savedUser));
    }

    // login user
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody Map<String, String> loginRequest) {
        String emailOrPhone = loginRequest.get("emailOrPhone");
        String password = loginRequest.get("passwordHash");

        // find user by email or phone
        User user = userService.findByEmailOrPhone(emailOrPhone)
                .orElseThrow(() -> new RuntimeException("Invalid Credentials."));
        if (!userService.checkPassword(user, password)) {
            throw new RuntimeException("Invalid Credentails.");
        }
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new ApiResponse<>("Login successful", token));
    }

}
