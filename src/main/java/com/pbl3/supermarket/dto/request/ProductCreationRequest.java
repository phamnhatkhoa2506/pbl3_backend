package com.pbl3.supermarket.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductCreationRequest {
    String name;
    String imageUrl;
    int stockQuantity;
    float price;
    String unit_price;
    float discount;
    LocalDate createDate;
    LocalDate expiryDate;
    String supplierId;
    Long[] categoryId;
}
