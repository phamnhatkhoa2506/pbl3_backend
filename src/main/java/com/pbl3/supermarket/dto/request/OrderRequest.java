package com.pbl3.supermarket.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class OrderRequest {
    Map<String, Long> productId_quantity;
    String customerId;

}
