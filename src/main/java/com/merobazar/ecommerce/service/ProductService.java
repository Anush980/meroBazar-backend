package com.merobazar.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.merobazar.ecommerce.entity.Category;
import com.merobazar.ecommerce.entity.Product;
import com.merobazar.ecommerce.entity.Store;
import com.merobazar.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // get all products
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // create new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // get products by id
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // update exisiting product
    public Product updateProduct(UUID id, Product updatedProduct) {
        Product existing = getProductById(id); // reuse method above
        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setBasePrice(updatedProduct.getBasePrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setStore(updatedProduct.getStore());
        existing.setIsActive(updatedProduct.getIsActive());
        return productRepository.save(existing);
    }

    // deactivate product
    public Product deactivateProduct(UUID id) {
        Product product = getProductById(id);
        product.setIsActive(false);
        return productRepository.save(product);
    }

    // reactivate product
    public Product reactivateProduct(UUID id) {
        Product product = getProductById(id);
        product.setIsActive(true);
        return productRepository.save(product);
    }

    // get products of store
    public List<Product> getProductsByStore(Store store) {
        return productRepository.findByStoreIsActiveTrue(store);
    }

    // get products by category
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    // get products by price range
    public List<Product> getProductsByPriceRange(BigDecimal min, BigDecimal max) {
        return productRepository.findByBasePriceBetweenAndIsActiveTrue(min, max);
    }

    // search products by name
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameAndIsActiveTrue(name);
    }

    // sort by ascending order
    public List<Product> getProductsByPriceAscendingOrder() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "basePrice"));
    }

    // sort by descending order
    public List<Product> getProductsByPriceDescendingOrder() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "basePrice"));
    }
    //combined service is pending...
}
