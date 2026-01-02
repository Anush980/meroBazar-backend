package com.merobazar.ecommerce.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.merobazar.ecommerce.entity.Category;
import com.merobazar.ecommerce.entity.Product;
import com.merobazar.ecommerce.entity.Store;

import java.util.List;
import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    
    // admin queries
    List<Product> findByName(String name);

    List<Product> findByCategory(Category category);

    List<Product> findByStore(Store store);

    List<Product> findByBasePrice(BigDecimal basePrice);


    // buyers (only active products)
    Page<Product> findByNameContainingIgnoreCaseAndIsActiveTrue(String name,Pageable pageable);

    Page<Product> findByStoreIdAndIsActiveTrue(UUID storeId,Pageable pageable);

      Page<Product> findByCategoryIdAndIsActiveTrue(UUID categoryId,Pageable pageable);

    Page<Product> findByBasePriceBetweenAndIsActiveTrue(BigDecimal minPrice, BigDecimal maxPrice,Pageable pageable);

    Page<Product> findByBasePriceIsActiveTrue(BigDecimal basePrice,Pageable pageable);


    // Sorting
    List<Product> findByCategoryIsActiveTrue(Category category, Sort sort);
}
