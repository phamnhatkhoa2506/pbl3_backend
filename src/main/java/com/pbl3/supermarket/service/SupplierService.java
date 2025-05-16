package com.pbl3.supermarket.service;

import com.pbl3.supermarket.dto.request.SupplierCreationRequest;
import com.pbl3.supermarket.entity.Supplier;
import com.pbl3.supermarket.exception.AppException;
import com.pbl3.supermarket.exception.ErrorCode;
import com.pbl3.supermarket.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier createSupplier(SupplierCreationRequest request)
    {
        if(supplierRepository.existsByName(request.getName()))
        {
            throw new AppException(ErrorCode.SUPPLIER_NAME_EXISTED);
        }
        else {

            Supplier supplier = new Supplier();
            supplier.setName(request.getName());
            supplier.setAddress(request.getAddress());
            supplier.setPhone(request.getPhone());
            supplier.setEmail(request.getEmail());

            return supplierRepository.save(supplier);
        }
    }
}
