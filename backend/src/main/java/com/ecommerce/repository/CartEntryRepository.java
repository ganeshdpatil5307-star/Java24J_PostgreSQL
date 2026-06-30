package com.ecommerce.repository;

import com.ecommerce.model.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartEntryRepository extends JpaRepository<CartEntry, Long> {
    List<CartEntry> findAll();
    Optional<CartEntry> findByProductId(Long productId);
    void deleteByProductId(Long productId);
}
