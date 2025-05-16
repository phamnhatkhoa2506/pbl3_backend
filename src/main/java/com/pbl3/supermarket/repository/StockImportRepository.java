package com.pbl3.supermarket.repository;

import com.pbl3.supermarket.entity.StockImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockImportRepository extends JpaRepository<StockImport, String> {

}
