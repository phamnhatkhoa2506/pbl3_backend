package com.pbl3.supermarket.repository;

import com.pbl3.supermarket.entity.ReceiptProduct;
import com.pbl3.supermarket.entity.ReceiptProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReceiptProductRepository extends JpaRepository<ReceiptProduct, ReceiptProductId> {
}