package com.pbl3.supermarket.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockImportRequest {
    String productId;

    int importQuantity;
    float importPrice;

    LocalDate importDate;
    LocalTime importTime;
}
