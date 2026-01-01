package com.merobazar.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.merobazar.ecommerce.entity.Category;
import com.merobazar.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public Category createCategory (Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getActiveCategories(){
        return categoryRepository.findByIsActiveTrue();
    }
    
    public Category getCategoryById(UUID id){
        return categoryRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Category not found"));
    }

    public Category updateCategory(Category category){
        return categoryRepository.save(category);
    }

    public Boolean deactivateCategory(UUID id){
        Category category = categoryRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Category not found."));
        category.setIsActive(false);
        categoryRepository.save(category);
        return true;
    }
    
}
