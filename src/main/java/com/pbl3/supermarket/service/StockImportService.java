package com.pbl3.supermarket.service;

import com.pbl3.supermarket.dto.request.StockImportRequest;
import com.pbl3.supermarket.dto.response.StockImportResponse;
import com.pbl3.supermarket.entity.Product;
import com.pbl3.supermarket.entity.StockImport;
import com.pbl3.supermarket.exception.AppException;
import com.pbl3.supermarket.exception.ErrorCode;
import com.pbl3.supermarket.repository.ProductRepository;
import com.pbl3.supermarket.repository.StockImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class StockImportService {
    @Autowired
    ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockImportRepository stockImportRepository;

    public StockImportResponse importStock(StockImportRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_ID_NOTFOUND));
        StockImport stockImport = StockImport.builder()
                .product(product)
                .importQuantity(request.getImportQuantity())
                .importDate(request.getImportDate() == null ? LocalDate.now() : request.getImportDate())
                .importTime(request.getImportTime() == null ? LocalTime.now() : request.getImportTime())
                .importPrice(request.getImportPrice())
                .build();

        stockImportRepository.save(stockImport);

        product.increaseQuantity(stockImport.getImportQuantity());
        productRepository.save(product);

        return StockImportResponse.builder()
                .productResponse(product.toProductResponse())
                .importQuantity(stockImport.getImportQuantity())
                .importDate(stockImport.getImportDate())
                .importTime(stockImport.getImportTime())
                .importPrice(stockImport.getImportPrice())
                .totalCost(stockImport.getTotalPrice())
                .build();
    }
}
