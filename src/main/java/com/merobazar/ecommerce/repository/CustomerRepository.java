package com.merobazar.ecommerce.repository;

import com.merobazar.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,UUID> {
   Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhone(String phone);
}
