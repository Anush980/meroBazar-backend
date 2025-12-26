package com.merobazar.ecommerce.controller;

import com.merobazar.ecommerce.entity.User;
import com.merobazar.ecommerce.service.UserService;
import com.merobazar.ecommerce.dto.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;




@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    //Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    //Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getOne(@PathVariable UUID id){
        User user = service.getUser(id);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ApiResponse<>("User found.",user));
    }

    //Create user
    @PostMapping
    public ResponseEntity<ApiResponse<User>> create(@RequestBody User user){
        User savedUser = service.saveUser(user);
        ApiResponse<User> response = new ApiResponse<>("User created successfully.", savedUser);
        return ResponseEntity.status(201).body(response);
    }

    //Update user 
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
       User existingUser = service.getUser(id);

       if(existingUser==null){
        return ResponseEntity.notFound().build();
       }

       existingUser.setName(updatedUser.getName());
       existingUser.setEmail(updatedUser.getEmail());
       existingUser.setPhone(updatedUser.getPhone());

       User saved = service.saveUser(existingUser);
       return ResponseEntity.ok(new ApiResponse<>("User data updated successfully.",saved));
    }

    //soft delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        boolean deleted = service.deactivateUser(id);

        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("User deleted successfully.");
    }
    
    


  
    
}
