package com.pbl3.supermarket.controller;

import com.pbl3.supermarket.dto.request.StockImportRequest;
import com.pbl3.supermarket.dto.response.ApiResponse;
import com.pbl3.supermarket.dto.response.StockImportResponse;
import com.pbl3.supermarket.service.StockImportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/import")
public class StockImportController {
    private final StockImportService stockImportService;

    public StockImportController(StockImportService stockImportService) {
        this.stockImportService = stockImportService;
    }

    @PostMapping("/product")
    ApiResponse<StockImportResponse> importProduct(@RequestBody StockImportRequest stockImportRequest) {
        return ApiResponse.<StockImportResponse>builder()
                .message("[OK] Import a product")
                .result(stockImportService.importStock(stockImportRequest))
                .build();
    }
}
