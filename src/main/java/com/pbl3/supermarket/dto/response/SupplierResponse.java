package com.pbl3.supermarket.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SupplierResponse {
    String name;
    String phone;
    String email;
    String address;
}
