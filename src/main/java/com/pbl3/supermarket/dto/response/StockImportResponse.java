package com.pbl3.supermarket.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class StockImportResponse {
    ProductResponse productResponse;

    int importQuantity;
    float importPrice;

    float totalCost;

    LocalDate importDate;
    LocalTime importTime;
}
