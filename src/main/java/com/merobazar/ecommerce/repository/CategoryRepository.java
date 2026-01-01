package com.merobazar.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merobazar.ecommerce.entity.Category;
import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,UUID> {

    Optional<Category> findByName(String name);

    List<Category> findByIsActiveTrue();

    List<Category> findByParentCategory(Category parentCategory);

    List<Category> findByParentCategoryIsNull();

    List<Category> findByParentCategoryAndIsActiveTrue(Category parentCategory);

    List<Category> findByParentCategoryIsNullAndIsActiveTrue();
}
