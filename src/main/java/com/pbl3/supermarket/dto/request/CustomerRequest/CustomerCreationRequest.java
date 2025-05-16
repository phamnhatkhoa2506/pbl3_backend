package com.pbl3.supermarket.dto.request.CustomerRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pbl3.supermarket.dto.request.UserCreationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CustomerCreationRequest extends UserCreationRequest {
    @JsonProperty
    int point = 0;

}
