package com.pbl3.supermarket.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductResponse {
    String id;
    String name;
    String imageUrl;
    int quantity;
    float price;
    String unit_price;
    float discount;
    LocalDate createDate;
    LocalDate expiryDate;

    SupplierResponse supplier;
    List<CategoryResponse> categories;
}
