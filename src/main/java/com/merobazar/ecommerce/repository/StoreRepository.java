package com.merobazar.ecommerce.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merobazar.ecommerce.entity.Store;
import com.merobazar.ecommerce.entity.User;

public interface StoreRepository extends JpaRepository<Store, UUID> {

    // for buyers
    List<Store> findByNameAndIsActiveTrue(String name);

    List<Store> findByCityAndIsActiveTrue(String city);

    List<Store> findByIsActiveTrue();

    // for sellers
    List<Store> findByOwner(User owner);

    // for admin
    List<Store> findByName(String name);

    List<Store> findByCity(String city);

    List<Store> findByEmail(String email);

    List<Store> findByPhone(String phone);

}
