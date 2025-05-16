package com.pbl3.supermarket.mapper;

import com.pbl3.supermarket.dto.response.CustomerResponse;
import com.pbl3.supermarket.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponse customerToCustomerResponse(Customer customer);
}

