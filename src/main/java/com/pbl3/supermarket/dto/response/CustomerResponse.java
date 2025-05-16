package com.pbl3.supermarket.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@Setter
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CustomerResponse extends UserResponse {
    int points;
}
