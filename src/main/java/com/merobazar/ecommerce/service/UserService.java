package com.merobazar.ecommerce.service;

import com.merobazar.ecommerce.entity.User;
import com.merobazar.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    
    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository=repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(UUID id){
        return repository.findById(id).orElse(null);
    }

    public User saveUser(User user){
        return repository.save(user);
    }

    //soft delete 
    public boolean deactivateUser(UUID id){
        User user = repository.findById(id).orElse(null);
        if(user==null){
            return false;
        }
        user.setIsActive(false);
        repository.save(user);
        return true;

    }
}
