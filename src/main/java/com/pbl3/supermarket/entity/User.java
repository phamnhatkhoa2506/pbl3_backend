package com.pbl3.supermarket.entity;

import com.pbl3.supermarket.enums.ROLES;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(unique = true, nullable = false)
    String username;  // Removed @UniqueElements, @Column(unique = true) is sufficient
    String password;

    @ElementCollection(targetClass = ROLES.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    Set<ROLES> roles;

    String email;
    String phone;
    String address;
    String firstName;
    String lastName;
    LocalDate birthDate;

    LocalDate createdDate;
}
