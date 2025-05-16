package com.pbl3.supermarket.repository;

import com.pbl3.supermarket.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, String> {
    Optional<Product> findByName(String name);
    boolean existsByName(String name);
    @Query(value = "SELECT DISTINCT p.* FROM product p " +
    "JOIN product_category pc ON p.id = pc.product_id " +
    "WHERE pc.category_id IN (:categoryIds)", nativeQuery = true)
    List<Product> findByCategories(@Param("categoryIds") List<Long> categoryIds);
}
