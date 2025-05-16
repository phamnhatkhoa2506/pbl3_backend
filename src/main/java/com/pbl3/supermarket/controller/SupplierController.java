package com.pbl3.supermarket.controller;

import com.pbl3.supermarket.dto.request.SupplierCreationRequest;
import com.pbl3.supermarket.dto.response.ApiResponse;
import com.pbl3.supermarket.entity.Supplier;
import com.pbl3.supermarket.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ApiResponse<Supplier> addSupplier(@RequestBody SupplierCreationRequest request) {
        return ApiResponse.<Supplier>builder()
                .message("Supplier added successfully")
                .result(supplierService.createSupplier(request))
                .build();
    }
}
