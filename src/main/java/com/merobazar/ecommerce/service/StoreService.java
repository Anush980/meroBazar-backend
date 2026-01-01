package com.merobazar.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.merobazar.ecommerce.entity.Store;
import com.merobazar.ecommerce.repository.StoreRepository;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    // dependency injection (constructor)
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public List<Store> getActiveStores() {
        return storeRepository.findByIsActiveTrue();
    }

    public Store getStoreById(UUID id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

    }

    public List<Store> getStoresByCity(String city) {
        return storeRepository.findByCityAndIsActiveTrue(city);
    }

    public Store updateStore(UUID id, Store updatedStore) {
        Store existing = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        existing.setName(updatedStore.getName());
        existing.setDescription(updatedStore.getDescription());
        existing.setAddress(updatedStore.getAddress());
        existing.setCity(updatedStore.getCity());
        existing.setCountry(updatedStore.getCountry());
        existing.setEmail(updatedStore.getEmail());
        existing.setPhone(updatedStore.getPhone());
        existing.setState(updatedStore.getState());
        return storeRepository.save(existing);
    }

    public Store deactivateStore(UUID id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        store.setIsActive(false);
        return storeRepository.save(store);

    }

    public Store reactivateStore(UUID id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        store.setIsActive(true);
        return storeRepository.save(store);
    }
}
