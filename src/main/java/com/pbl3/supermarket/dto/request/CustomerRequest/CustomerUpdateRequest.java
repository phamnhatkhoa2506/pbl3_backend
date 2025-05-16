package com.pbl3.supermarket.dto.request.CustomerRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CustomerUpdateRequest {
    @Size(min = 8)
    String password;
    String current_password;
    String address;
    String firstName;
    String lastName;
    String phone;
    String email;
    LocalDate birthDate;
}
