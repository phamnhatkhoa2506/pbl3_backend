package com.pbl3.supermarket.repository;

import com.pbl3.supermarket.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    Optional<Supplier> findById(String supplierID);
    Optional<Supplier> findByName(String name);
    boolean existsByName(String name);
}
