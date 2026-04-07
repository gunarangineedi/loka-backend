package com.loka.repository;

import com.loka.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByFeatured(Boolean featured);
    List<Product> findByNameContainingIgnoreCase(String name);
}
