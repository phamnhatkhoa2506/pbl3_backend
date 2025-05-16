package com.pbl3.supermarket.entity;

import com.pbl3.supermarket.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Delivery {

    @Id
    @OneToOne
    Receipt bill;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    Staff staff;

    @Builder.Default
    String status = Status.PROCESSING.message;
}
