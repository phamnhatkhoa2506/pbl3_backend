package com.pbl3.supermarket.mapper;

import com.pbl3.supermarket.dto.request.ProductCreationRequest;
import org.mapstruct.Mapper;
import com.pbl3.supermarket.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreationRequest productCreationRequest);
}
