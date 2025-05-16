package com.pbl3.supermarket.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreationRequest {
    @Size(min = 3, message = "Username must be at least 3 characters")
    String username;
    @Size(min = 8)
    String password;
    @Email
    String email;
    @Size(min = 10)
    String phone;
    String address;
    String firstName;
    String lastName;
    LocalDate birthDate;
}
